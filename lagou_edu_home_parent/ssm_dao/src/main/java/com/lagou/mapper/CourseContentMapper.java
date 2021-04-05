package com.lagou.mapper;

import com.lagou.damain.Course;
import com.lagou.damain.CourseSection;

import java.util.List;

public interface CourseContentMapper {

    /**
     * 根据课程id查询关联的章节信息，以及关联的课时信息
     */

    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);
    /**
     * 回显章节 对应的课程信息
     */
    public Course findCourseByCourseId(Integer courseId);
    /**
     * 保存章节
     */
    public void saveSection(CourseSection courseSection);
    //更新章节
    public void updateSection(CourseSection courseSection);
    /**
     * 修改章节状态
     */
    public void updateSectionStatus(CourseSection courseSection);


}
