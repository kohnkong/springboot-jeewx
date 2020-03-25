package com.jeecg.p3.goldeneggs.dao;

import com.jeecg.p3.goldeneggs.entity.WxActGoldeneggsAwards;
import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import java.util.List;

/**
 * 描述：</b>WxActGoldeneggsAwardsDao<br>
 *
 * @author：junfeng.zhou
 * @since：2016年06月07日 18时00分26秒 星期二
 * @version:1.0
 */
public interface WxActGoldeneggsAwardsDao extends GenericDao<WxActGoldeneggsAwards> {

    public Integer count(PageQuery<WxActGoldeneggsAwards> pageQuery);

    public List<WxActGoldeneggsAwards> queryPageList(PageQueryWrapper<WxActGoldeneggsAwards> wrapper);

    public List<WxActGoldeneggsAwards> queryAwards(@Param("jwid") String jwid, @Param("createBy") String createBy);

    /**
     * 根据jwid查询奖项
     *
     * @param jwid
     * @return
     */
    public List<WxActGoldeneggsAwards> queryAwards2(@Param("jwid") String jwid);

    /**
     * @param jwid
     * @param createBy
     * @param name
     * @return
     * @功能:通过奖项名称查询奖项
     * @作者:liwenhui
     * @时间:2018-3-28 下午02:38:36
     * @修改：
     */
    public List<WxActGoldeneggsAwards> queryAwardsByName(@Param("jwid") String jwid, @Param("createBy") String createBy, @Param("name") String name);
}

