package com.jeecg.p3.weixin.web.core;

import com.jeecg.p3.commonweixin.entity.MyJwWebJwid;
import com.jeecg.p3.config.PropertiesConfig;
import com.jeecg.p3.core.annotation.SkipAuth;
import com.jeecg.p3.core.enums.SkipPerm;
import com.jeecg.p3.system.service.MyJwWebJwidService;
import com.jeecg.p3.weixin.service.WechatService;
import com.jeecg.p3.weixin.util.MessageUtil;
import com.jeecg.p3.weixin.util.SignUtil;
import com.jeecg.p3.weixin.util.WeixinUtil;
import com.jeecg.p3.weixin.vo.resp.TextMessageResp;
import net.sf.json.JSONObject;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 微信客户端，请求处理核心类
 *
 * @author zhangdaihao
 */
@Controller
@RequestMapping("/wechatController")
@SkipAuth(auth = SkipPerm.SKIP_SIGN)
public class WechatController {

    public final static Logger log = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private WechatService wechatService;

    @Autowired
    private MyJwWebJwidService webJwidService;

    /**
     * 多域名授权
     *
     * @param request
     * @param response
     * @param url      域名授权回调地址
     * @throws IOException
     */
    @RequestMapping(value = "authorize", method = RequestMethod.GET)
    public void oauthAuthorize(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam("url") String url) throws IOException {
        Optional<String> optionalUrl = Optional.ofNullable(url);
        if (optionalUrl.isPresent()) {
            String redirectUri = PropertiesConfig.getCertOAuthDomain() + "wechatController/authorize/redirect?url=" + url;
            String webOauthUrl = WeixinUtil.web_oauth_url.replace("APPID", PropertiesConfig.getCertAppId()).replace("REDIRECT_URI", redirectUri).replace("SCOPE", "snsapi_userinfo");
            response.sendRedirect(webOauthUrl);
        }
    }

    /**
     * 第三方授权后获取CODE返回调用方
     *
     * @param request
     * @param response
     * @param code
     * @param state
     * @param url      调用方回调地址
     * @throws IOException
     */
    @RequestMapping(value = "authorize/redirect", method = RequestMethod.GET)
    public void oauthRedirect(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam(value = "code", required = false) String code,
                              @RequestParam(value = "state", required = false) String state,
                              @RequestParam("url") String url) throws IOException {
        String requetUrl = WeixinUtil.web_oauth_accesstoken_url.replace("APPID", "wxec2009860e67972b").replace("SECRET", "d78c4fed023278804c21a71cfd30a2ce").replace("CODE", code);
        JSONObject resultJson = WeixinUtil.httpRequest(requetUrl, "GET", null);
        String refreshToken = (String) resultJson.get("refresh_token");
        requetUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
        requetUrl = requetUrl.replace("APPID", "wxec2009860e67972b").replace("REFRESH_TOKEN", refreshToken);
        resultJson = WeixinUtil.httpRequest(requetUrl, "GET", null);
        String openid = (String) resultJson.get("openid");
        response.sendRedirect(url + "?code=" + code);
    }

    @RequestMapping(params = "wechat", method = RequestMethod.GET)
    public void wechatGet(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam(value = "signature") String signature,
                          @RequestParam(value = "timestamp") String timestamp,
                          @RequestParam(value = "nonce") String nonce,
                          @RequestParam(value = "echostr") String echostr) throws IOException {

        log.info("-------------------微信公众号响应消息------------wechat------");
        List<MyJwWebJwid> myJwWebJwids = webJwidService.queryAll();

        if (myJwWebJwids != null && myJwWebJwids.size() > 0) {
            log.info("--------------myJwWebJwids------------size------" + myJwWebJwids.size());
            for (MyJwWebJwid myJwWebJwid : myJwWebJwids) {
                if (SignUtil.checkSignature(myJwWebJwid.getToken(), signature, timestamp, nonce)) {
                    try {
                        response.getWriter().print(echostr);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @RequestMapping(params = "wechat", method = RequestMethod.POST)
    public void wechatPost(HttpServletResponse response,
                           HttpServletRequest request) throws IOException {
        log.info("------------------微信公众号响应消息-------------wechatPost-----");
        log.info("---------------------" + request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "------------------");
        String respMessage = null;
        try {
            respMessage = wechatService.coreService(request);
        } catch (Exception e) {
            e.printStackTrace();
            if (oConvertUtils.isEmpty(respMessage)) {
                respMessage = getWeixinMsg(request);
            }
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }


    /**
     * 异常情况下，回复此消息
     *
     * @param request
     * @return
     */
    private String getWeixinMsg(HttpServletRequest request) {
        // 异常情况下，回复此文本消息
        TextMessageResp textMessage = new TextMessageResp();
        try {
            // 默认返回的文本消息内容
            String respContent = "系统网络繁忙，请稍后再试！";
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            //消息id
            String msgId = requestMap.get("MsgId");
            //消息内容
            String content = requestMap.get("Content");
            //多媒体ID
            String mediaId = requestMap.get("MediaId");

            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(System.currentTimeMillis());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setContent(respContent);
        } catch (Exception e) {
        }
        // 将文本消息对象转换成xml字符串
        return MessageUtil.textMessageToXml(textMessage);
    }

}
