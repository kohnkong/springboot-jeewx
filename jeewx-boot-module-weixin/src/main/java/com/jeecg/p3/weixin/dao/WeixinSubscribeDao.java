package com.jeecg.p3.weixin.dao;

import com.jeecg.p3.weixin.entity.WeixinSubscribe;
import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import java.util.List;

/**
 * 描述：</b>关注欢迎语<br>
 *
 * @author：
 * @since：2018年07月20日 10时12分09秒 星期五
 * @version:1.0
 */
public interface WeixinSubscribeDao extends GenericDao<WeixinSubscribe> {

    public Integer count(PageQuery<WeixinSubscribe> pageQuery);

    public List<WeixinSubscribe> queryPageList(PageQueryWrapper<WeixinSubscribe> wrapper);

    public WeixinSubscribe querySubscribeByJwid(@Param("jwid") String jwid);

}

