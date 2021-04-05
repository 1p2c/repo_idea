package com.lagou.service;

import com.lagou.damain.Course;
import com.lagou.damain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /**
     * 根据课程id 查询对应的课程内容（章节+内容）
     */
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id);

    /**
     * 回显章节对应的课程内容
     */
    public Course findCourseByCourseId(Integer courseId);

    /**
     * 新增章节信息
     */
    public void saveSection(CourseSection courseSection);

    //更新章节信息
    public void updateSection(CourseSection courseSection);
    /**
     * 修改章节状态
     */
    public void updateSectionStatus(int id,int status);
}
