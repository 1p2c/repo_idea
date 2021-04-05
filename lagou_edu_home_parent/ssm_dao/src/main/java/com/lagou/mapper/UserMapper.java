package com.lagou.mapper;

import com.lagou.damain.*;

import java.util.List;

public interface UserMapper {
    /**
     * 用户分页和多条件组合查询
     */
    public List<User> findAllUserByPage(UserVo userVo);
    /**
     * 修改用户状态
     */
    public void updateUserStatus(User user);
    /**
     * 用户登录（根据用户名查询具体的用户信息）
     */
    public  User login(User user);

    /**
     * 根据用户关系清空中间表
     */
    public void deleteUserContextRole(Integer id );
    /**
     * 分配角色
     */
    public void UserContextRole(User_Role_relation user_role_relation);
    /**
     * 根据用户id查询关联的角色信息
     */
    public List<Role> findUserRelationRoleById(Integer id);

    /**
     * 2.根据角色id查询角色拥有的角色菜单（-1）
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
    /**
     * 3.根据pid查询子菜单
     */
    public List<Menu> findSubMenuByPid(Integer pid);
    /**
     * 4.获取用户拥有的权限信息
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);
     public List<Resource> findResourceByRoleId2(List<Integer> ids);

    public void test11();
     public void test12();
     public void test13();
     public void test14();
     public void test15();
     public void test16();
     public void test17();

}
