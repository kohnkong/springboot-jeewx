package com.jeecg.p3.weixin.service;

import org.jeecgframework.p3.core.utils.common.PageList;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import com.jeecg.p3.weixin.entity.WeixinReceptMsg;

/**
 * 描述：</b>客服消息记录表<br>
 *
 * @author：
 * @since：2018年10月18日 19时35分31秒 星期四
 * @version:1.0
 */
public interface WeixinReceptMsgService {


    public void doAdd(WeixinReceptMsg weixinReceptMsg);

    public void doEdit(WeixinReceptMsg weixinReceptMsg);

    public void doDelete(String id);

    public WeixinReceptMsg queryById(String id);

    public PageList<WeixinReceptMsg> queryPageList(PageQuery<WeixinReceptMsg> pageQuery);

    public void doAddAnswer(WeixinReceptMsg weixinReceptMsg);

    public String sendAnswerAgain(WeixinReceptMsg weixinReceptMsg);
}

