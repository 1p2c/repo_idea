package com.lagou.service.Impl;

import com.github.pagehelper.PageInfo;
import com.lagou.damain.Course;
import com.lagou.damain.CourseVO;
import com.lagou.damain.Teacher;
import com.lagou.mapper.CourseMapper;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVO courseVO) {

        List<Course> courseList = courseMapper.findCourseByCondition(courseVO);
        return courseList;
    }

    @Override
    public void saveCourseOrTeacher(CourseVO courseVo) throws InvocationTargetException, IllegalAccessException {
        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVo);

        //补全课程信息
        Date date =new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        //保存课程
        courseMapper.saveCourse(course);
        //获取新插入数据的ID值
        int id = course.getId();
        //封装teacher信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);
        //补全讲师信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);
        //保存讲师
        courseMapper.saveTeacher(teacher);


    }

    @Override
    public CourseVO findCourseById(Integer id) {
        return courseMapper.findCourseById(id);

    }

    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {

        //封装课程信息

        Course course = new Course();
        BeanUtils.copyProperties(course,courseVO);
        //补全信息
        Date date = new Date();
        course.setUpdateTime(date);
        //更新课程
        courseMapper.updateCourse(course);
        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVO);
        //补全信息
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);
        //更新讲师
        courseMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(int courseid, int status) {
        //1.封装数据
        Course course = new Course();
        course.setId(courseid);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        //2.调用mapper
        courseMapper.updateCourseStatus(course);

    }
}
