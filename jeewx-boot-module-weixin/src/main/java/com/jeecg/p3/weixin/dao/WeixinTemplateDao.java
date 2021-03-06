package com.jeecg.p3.weixin.dao;

import com.jeecg.p3.weixin.entity.WeixinTemplate;
import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import java.util.List;

/**
 * 描述：</b>图文样式库表<br>
 *
 * @author：
 * @since：2018年07月24日 20时01分05秒 星期二
 * @version:1.0
 */
public interface WeixinTemplateDao extends GenericDao<WeixinTemplate> {

    public Integer count(PageQuery<WeixinTemplate> pageQuery);

    public List<WeixinTemplate> queryPageList(PageQueryWrapper<WeixinTemplate> wrapper);

    /**
     * @param type
     * @return
     * @功能：获取单个样式库
     */
    public List<WeixinTemplate> queryByType(@Param("type") String type);

}

