package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.damain.ResponseResult;
import com.lagou.damain.Role;
import com.lagou.damain.User;
import com.lagou.damain.UserVo;

import java.util.List;

public interface UserService {

    /**
     * 用户分页和多条件查询
     */
    public PageInfo<User> findAllUserByPage(UserVo userVo);

    /**
     * 修改用户状态
     */
    public void updateUserStatus(int id,String status);
    /**
     * 用户登录
     */
    public User login(User user) throws Exception;
    /**
     * 分配角色（回显）
     */
    public List<Role> findUserRelationRoleById(Integer id );
    /**
     * 用户关联角色
     */
    public void userContextRole(UserVo userVo);
    /**
     * 获取用户权限，进行菜单动态展示
     */
    public ResponseResult getUserPermissions(Integer userid);



}
