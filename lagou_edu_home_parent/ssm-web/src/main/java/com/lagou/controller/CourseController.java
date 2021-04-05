package com.lagou.controller;

import com.lagou.damain.Course;
import com.lagou.damain.CourseVO;
import com.lagou.damain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 查询课程信息 条件查询
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        List<Course> courseList = courseService.findCourseByCondition(courseVO);

        ResponseResult result = new ResponseResult(true,200,"响应成功",courseList);
        return result;

    }
    /**
     * 图片上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断 接受到的上传文件是否为空
        if (file.isEmpty()){
            throw  new RuntimeException();
        }
        //2.获取项目的部署路径
        String realPath = request.getServletContext().getRealPath("/");
        String webappsPath  = realPath.substring(0, realPath.indexOf("ssm_web"));
        //3.获取原文件名
        //ll.jsp
        String filename = file.getOriginalFilename();

        //4.生成新文件名
        //234.jpg
        String newFileName= System.currentTimeMillis() + filename.substring(filename.lastIndexOf("."));

        //5.文件上传
        //D:.../apache-tomcat-8.5.56\...\ upload
        String uploadPath = webappsPath+"upload\\";
        File filePath=new File(uploadPath,newFileName);

        //如果目录不存在就创建目录
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdir();
            System.out.println("创建目录："+filePath);
        }

        //图片进行了真正的上传
        file.transferTo(filePath);

        //6.将文件名和路径返回，进行响应
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);
        return responseResult;
    }

    /**
     * 新增课程信息及讲师信息
     * 新增课程和修改课程写在同一个方法中
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        if (courseVO.getId() == null){
            //调用service
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        }else{
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }
    }

    /**
     * 根据ID获取课程信息及关联的讲师信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id) {
        CourseVO courseVo = courseService.findCourseById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseVo);
        return responseResult;
    }
    /**
     * 课程状态管理
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){

        //调用service。传递参数 完成课程状态变更
        courseService.updateCourseStatus(id,status);
        //响应数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "课程变更成功", map);
        return responseResult;

    }



}
