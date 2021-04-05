package com.lagou.controller;

import com.lagou.damain.Menu;
import com.lagou.damain.ResponseResult;
import com.lagou.damain.Role;
import com.lagou.damain.RoleMenuVo;
import com.lagou.mapper.MenuMapper;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    /**
     * 查询所有角色（条件）
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);

        return new ResponseResult(true,200,"响应成功",allRole);

    }
    /**
     * 查询所有的父子菜单信息（分配菜单的第一个接口）
     */
    @Autowired
    private MenuMapper menuMapper;
    @RequestMapping("/findAllMenu")
     public ResponseResult findSubMenuListByPid(){
        //-1 查询所有的父子级菜单数据
        List<Menu> menuList = menuMapper.findSubMenuListByPid(-1);
        //响应数据
        Map<String,Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);
        ResponseResult result=new ResponseResult(true,200,"响应成功",map);
        return result;

    }
    /**
     * 根据角色id查询对应的菜单id信息
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){
        List<Integer> RoleIds = roleService.findMenuByRoleId(roleId);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", RoleIds);
        return responseResult;

    }
    /**
     * 为角色分配菜单
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);
        return responseResult;


    }
    /**
     * 删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){

        roleService.deleteRole(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色成功", null);
        return responseResult;
    }
}
