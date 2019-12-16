package com.gootschool.education.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gootschool.pojo.education.Course;
import com.gootschool.pojo.education.dto.CoursePublishVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 课程 Mapper 接口
 *
 * @author Revan Wang
 * @since 2019-12-13
 */
public interface ICourseMapper extends BaseMapper<Course> {

    @Select("SELECT c.title, c.cover, c.lesson_num AS lessonNum, c.price, s1.title AS subjectLevelOne, s2.title AS subjectLevelTwo, t.`name` AS teacherName " +
            "FROM t_course c LEFT JOIN t_subject s1 ON c.subject_id1 = s1.id " +
            "LEFT JOIN t_subject s2 ON c.subject_id2 = s2.id " +
            "LEFT JOIN t_teacher t ON c.teacher_id = t.id WHERE c.id = #{courseId}")
    public CoursePublishVO coursePublishInfo(String courseId);

}
