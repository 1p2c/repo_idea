package com.lagou.mapper;

import com.lagou.damain.Role;
import com.lagou.damain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {
    /**
     * 查询所有角色&条件查询
     */
    public List<Role> findAllRole(Role role);
    /**
     * 根据角色id 查询关联的菜单信息 id
     */
    public List<Integer> findMenuByRoleId(Integer roleid);
    /**
     * 根据roleid 清空中间表的关联关系
     */
    public void deleteRoleContextMenu(Integer rid);
    /**
     *为角色分配菜单信息
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);
    /**
     * 删除角色
     */
    public void deleteRole(Integer roleid);
}
