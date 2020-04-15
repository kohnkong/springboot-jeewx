package com.jeecg.p3.weixin.web;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.p3.core.annotation.SkipAuth;
import com.jeecg.p3.core.enums.SkipPerm;
import com.jeecg.p3.weixin.util.FastdfsUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.jsf.FacesContextUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Mr.Kong
 * @Date: 2020/3/24 15:27
 * @Description:
 */
@RestController
@RequestMapping("image")
@SkipAuth(auth = SkipPerm.SKIP_ALL)
public class UploadController {

    @PostMapping("dragUpload")
    public Map<String, Object> upload(@RequestParam("storePath") String storePath, @RequestParam("file") MultipartFile multipartFile) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("storePath", storePath);
        String result = FastdfsUtils.httpClientUploadFile(multipartFile, params);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.getInnerMap();
    }

    @PostMapping("localUpload")
    public void upload(@RequestParam("storePath") String storePath, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("storePath", storePath);
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("upfile");
        String result = FastdfsUtils.httpClientUploadFile(multipartFile, params);
        String callback = request.getParameter("callback");
        if (callback == null) {
            response.getWriter().write(result);
        } else {
            response.getWriter().print("<script>" + callback + "(" + result + ")</script>");
        }
    }
}
