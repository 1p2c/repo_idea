package com.lagou.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.damain.*;
import com.lagou.mapper.UserMapper;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageInfo<User> findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());

        List<User> list = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {

        //1.封装数据
        User user= new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());
        //调用mapper
        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) throws Exception {
        //1.调用mapper
        User user1 = userMapper.login(user);
        if (user1 != null && Md5.verify(user.getPassword(),"lagou",user1.getPassword())){
            return user1;
        }else{
            return null;
        }
    }

    /**
     * 分配角色 回显
     * @param id
     * @return
     */
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> userRelationRoleById = userMapper.findUserRelationRoleById(id);

        return userRelationRoleById;
    }

    @Override
    public void userContextRole(UserVo userVo) {
        //根据用户id清空中间表关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());
        //2重新建立关联关系
        List<Integer> roleIdList = userVo.getRoleIdList();
        for (Integer roleId : roleIdList) {
            //封装数据
            User_Role_relation user_role_relation =new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.UserContextRole(user_role_relation);

        }
        
    }

    @Override
    public ResponseResult getUserPermissions(Integer userid) {

        //1.根据当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userid);
        //2.获取角色id 保存到list集合中
        ArrayList<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }
        //3.根据角色id查询父菜单
        List<Menu> parentMenuList = userMapper.findParentMenuByRoleId(roleIds);
        //4.查询封装父菜单关联的子菜单
        for (Menu menu : parentMenuList) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }
        //5.获取该用户拥有的资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);
        //6.封装数据并返回
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenuList);
        map.put("resourceList",resourceList);
        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }

}
