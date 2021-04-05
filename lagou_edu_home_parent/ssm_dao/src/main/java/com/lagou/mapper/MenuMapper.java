package com.lagou.mapper;

import com.lagou.damain.Menu;

import java.util.List;

public interface MenuMapper {
    /**
     * 查询所有父子菜单的信息
     */
    public List<Menu> findSubMenuListByPid(int pid );
    /**
     * 查询所有菜单列表
     */
    public List<Menu> findAllMenu();

    public Menu findMenuById(Integer id);
}
