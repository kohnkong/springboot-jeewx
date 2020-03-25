package com.jeecg.p3.commonweixin.service;

import java.util.List;

import com.jeecg.p3.commonweixin.entity.JwSystemUserJwidVo;
import com.jeecg.p3.commonweixin.entity.JwSystemUserVo;

public interface MyJwSystemUserService {

    /**
     * @param phone
     * @return
     * @功能：根据手机号查询管理员信息
     */
    List<JwSystemUserVo> queryByPhone(String phone);

    /**
     * @param jwSystemUserJwid
     * @功能：分配管理员权限
     */
    void authManager(JwSystemUserJwidVo jwSystemUserJwid);

    /**
     * @param jwid
     * @return
     * @功能：根据jwid获取已分配管理员信息
     */
    List<JwSystemUserJwidVo> queryByJwid(String jwid);

    /**
     * @param id
     * @功能：根据id删除用户公众号关联表信息
     */
    void deleteById(String id);


}

