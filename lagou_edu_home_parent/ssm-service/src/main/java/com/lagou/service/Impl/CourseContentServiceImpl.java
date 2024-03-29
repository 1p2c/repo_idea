package com.lagou.service.Impl;

import com.lagou.damain.Course;
import com.lagou.damain.CourseSection;
import com.lagou.mapper.CourseContentMapper;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseContentServiceImpl implements CourseContentService {
    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer id) {
        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(id);

        return list;
    }

    @Override
    public Course findCourseByCourseId(Integer courseId) {

        Course course = courseContentMapper.findCourseByCourseId(courseId);

        return course;
    }

    @Override
    public void saveSection(CourseSection courseSection) {
        //1.补全信息
        Date date= new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        //2.调用mapper方法
        courseContentMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {
        //1.补全信息
        courseSection.setUpdateTime(new Date());
        //2.调用mapper方法
        courseContentMapper.updateSection(courseSection);
    }

    @Override
    public void updateSectionStatus(int id, int status) {
        //1.封装数据
        CourseSection courseSection = new CourseSection();
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        courseSection.setId(id);

        //2.调用mapper
        courseContentMapper.updateSectionStatus(courseSection);
    }
}
