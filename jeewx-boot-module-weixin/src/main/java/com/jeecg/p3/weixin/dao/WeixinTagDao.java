package com.jeecg.p3.weixin.dao;

import com.jeecg.p3.weixin.entity.WeixinTag;
import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import java.util.List;

/**
 * 描述：</b>粉丝标签表<br>
 *
 * @author：
 * @since：2018年08月13日 14时53分22秒 星期一
 * @version:1.0
 */
public interface WeixinTagDao extends GenericDao<WeixinTag> {

    public Integer count(PageQuery<WeixinTag> pageQuery);

    public List<WeixinTag> queryPageList(PageQueryWrapper<WeixinTag> wrapper);

    /**
     * @param jwid
     * @功能：根据jwid清空该公众号创建的标签
     */
    public void deleteTagsByJwid(@Param("jwid") String jwid);

    /**
     * @param jwid
     * @return
     * @功能：根据jwid获取该公众号创建的标签
     */
    public List<WeixinTag> getAllTags(@Param("jwid") String jwid);

    /**
     * @param tagId
     * @param jwid
     * @return
     * @功能：根据tagId和jwid获取标签信息
     */
    public WeixinTag queryByTagIdAndJwid(@Param("tagId") String tagId, @Param("jwid") String jwid);

}

