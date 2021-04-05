package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.damain.ResponseResult;
import com.lagou.damain.Role;
import com.lagou.damain.User;
import com.lagou.damain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 申明用户分页和多条件查询的方法
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){

        PageInfo<User> allUserByPage = userService.findAllUserByPage(userVo);
        return new ResponseResult(true,200,"多条件分页成功",allUserByPage);

    }
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id ,String status){

        userService.updateUserStatus(id,status);
//        Map<String,String> map = new HashMap<>();
//        map.put("status",status);
        return new ResponseResult(true,200,"状态响应成功",status);
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user2 = userService.login(user);
        if (user2 !=null ){
            //保存用户id及access_token到session中
            HttpSession session=request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user2.getId());
            //将查询到的信息响应给前台
            Map<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user2.getId());

            //将查询出来的信息传递给前台
            map.put("user",user2);

            return new ResponseResult(true,1,"登录成功",map);
        }else {
            return new ResponseResult(true,400,"用户名或密码错误",null);
        }
    }

    /**
     * 分配角色回显
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> userRelationRoleById = userService.findUserRelationRoleById(id);

        return new ResponseResult(true,200,"分配角色信息成功",userRelationRoleById);

    }

    /**
     * 分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){

        userService.userContextRole(userVo);
        return new ResponseResult(true,200,"分配角色成功",null);

    }
    /**
     * 获取用户权限，进行菜单动态展示
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //1.获取请求头中的token
        String header_token = request.getHeader("Authorization");
        //2.获取session中的token
        HttpSession session = request.getSession();
        String session_token = (String) session.getAttribute("access_token");
        //3.判断 token是否一致
        if (header_token.equalsIgnoreCase(session_token)){
            //获取用户的id
            Integer user_id = (Integer)request.getSession().getAttribute("user_id");
            //调用service，进行菜单信息查询
            ResponseResult userPermissions = userService.getUserPermissions(user_id);
            return userPermissions;
        }else{
            //token 不一致
           return  new ResponseResult(false,400,"获取菜单信息失败",null);
        }
    }

}
