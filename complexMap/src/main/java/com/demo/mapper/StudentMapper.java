package com.demo.mapper;

import com.demo.pojo.Student;
import com.demo.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
//    Teacher getTeacher(@Param("tid") int id);

    List<Student> getStudents();

    List<Student> getStudents2();
}
