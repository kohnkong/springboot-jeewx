package com.jeecg.p3.commonweixin.dao;

import com.jeecg.p3.commonweixin.entity.JwSystemUserJwidVo;
import com.jeecg.p3.commonweixin.entity.JwSystemUserVo;
import org.apache.ibatis.annotations.Param;
import org.jeecgframework.p3.core.utils.persistence.GenericDao;

import java.util.List;

public interface MyJwSystemUserDao extends GenericDao<JwSystemUserVo> {

    /**
     * @param phone
     * @return
     * @功能：根据手机号查询管理员信息
     */
    List<JwSystemUserVo> queryByPhone(@Param("phone") String phone);

    /**
     * @param jwSystemUserJwid
     * @功能：分配管理员权限
     */
    void authManager(@Param("jwSystemUserJwid") JwSystemUserJwidVo jwSystemUserJwid);

    /**
     * @param jwid
     * @return
     * @功能：根据jwid获取已分配管理员信息
     */
    List<JwSystemUserJwidVo> queryByJwid(@Param("jwid") String jwid);

    /**
     * @param id
     * @功能：根据id删除用户公众号关联表信息
     */
    void deleteById(@Param("id") String id);

}

