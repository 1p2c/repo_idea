package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.damain.Course;
import com.lagou.damain.CourseVO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    public List<Course> findCourseByCondition(CourseVO courseVO);
    /**
     * 添加课程及讲师信息
     */

    public void saveCourseOrTeacher(CourseVO courseVo) throws InvocationTargetException, IllegalAccessException;

    /**
     * 根据ID查询课程信息
     */
    public CourseVO findCourseById(Integer id);
    /**
     * 更新课程及讲师信息
     */

    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;
    /**
     * 更新课程状态
     */
    public void updateCourseStatus(int courseid ,int status);
}
