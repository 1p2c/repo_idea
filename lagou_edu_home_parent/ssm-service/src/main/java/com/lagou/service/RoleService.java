package com.lagou.service;

import com.lagou.damain.Role;
import com.lagou.damain.RoleMenuVo;
import com.lagou.damain.Role_menu_relation;

import java.util.List;

public interface RoleService {
    /**
     * 查询所有角色
     */
    public List<Role> findAllRole(Role role);
    /**
     * 根据角色id 查询关联的菜单信息 id
     */
    public List<Integer> findMenuByRoleId(Integer roleid);
    /**
     *为角色分配菜单信息
     */
    public void roleContextMenu(RoleMenuVo roleMenuVo);
    /**
     * 删除角色
     */
    public void deleteRole(Integer roleid);
}
