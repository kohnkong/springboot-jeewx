package com.jeecg.p3.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.common.PageQuery;
import org.jeecgframework.p3.core.utils.common.PageQueryWrapper;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import com.jeecg.p3.weixin.entity.WeixinMenu;

/**
 * 描述：</b>微信菜单表<br>
 *
 * @author：
 * @since：2018年07月12日 13时58分38秒 星期四
 * @version:1.0
 */
public interface WeixinMenuDao extends GenericDao<WeixinMenu> {

    public Integer count(PageQuery<WeixinMenu> pageQuery);

    public List<WeixinMenu> queryPageList(PageQueryWrapper<WeixinMenu> wrapper);

    //update-begin--Author:zhangweijian Date:20181017 for：添加jwid参数

    /**
     * @param orders
     * @param jwid
     * @return
     * @功能：根据orders获取父级id
     */
    public String getFatherIdByorders(@Param("orders") String orders, @Param("jwid") String jwid);

    /**
     * @param orders
     * @param jwid
     * @return
     * @功能：根据orders查询菜单信息
     */
    public WeixinMenu queryByOrders(@Param("orders") String orders, @Param("jwid") String jwid);
    //update-end--Author:zhangweijian Date:20181017 for：添加jwid参数

    /**
     * @param id
     * @return
     * @功能：根据fatherId查询其子级菜单
     */
    public int getSonMenuByFatherId(String fatherId);

    //update-begin--Author:zhangweijian  Date: 20180723 for：获取一级菜单

    /**
     * @param query
     * @return
     * @功能：获取一级菜单
     */
    public List<WeixinMenu> queryMenusByJwid(@Param("jwid") String jwid, @Param("fatherId") String fatherId);
    //update-end--Author:zhangweijian  Date: 20180723 for：获取一级菜单

    /**
     * 根据菜单KEY和JWID查询到菜单信息
     *
     * @author LeeShaoQing
     */
    public List<WeixinMenu> queryMenuByKeyAndJwid(@Param("key") String key, @Param("jwid") String jwid);

}

