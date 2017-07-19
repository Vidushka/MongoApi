package com.hsenid;

import java.util.List;

/**
 * Created by Vidushka on 06/07/17.
 */
public interface StudentService {

    List<Student> getStudents();

    boolean addStudent(Student student);

    int deleteStudent(String stdId);

    int updateStudent(Student student);

}
