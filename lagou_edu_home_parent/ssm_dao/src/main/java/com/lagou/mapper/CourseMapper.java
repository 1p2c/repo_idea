package com.lagou.mapper;

import com.lagou.damain.Course;
import com.lagou.damain.CourseVO;
import com.lagou.damain.Teacher;

import java.util.List;

public interface CourseMapper {

    /**
     * 多条件查询
     *
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);
    /**
     * 新增课程信息
     */
    public void saveCourse(Course course);
    /**
     * 新增讲师信息
     */
    public void saveTeacher(Teacher teacher);
    /**
     * 根据ID查询对应的课程信息及关联的讲师信息
     */
    public CourseVO findCourseById(Integer id);
    /**
     * 更新课程信息
     */
    public void updateCourse(Course course);
    /**
     * 更新讲师信息
     */
    public void updateTeacher(Teacher teacher);

    /**
     * 课程状态更新
     */
    public void updateCourseStatus(Course course);
}
