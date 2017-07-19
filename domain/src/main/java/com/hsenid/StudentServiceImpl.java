package com.hsenid;

import com.mongodb.WriteResult;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Vidushka on 06/07/17.
 */

@Component
public class StudentServiceImpl implements StudentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Student> getStudents() {
        List<Student> studentList;
        studentList = mongoTemplate.findAll(Student.class, "newStudent");
        return studentList;
    }

    @Override
    public boolean addStudent(Student student) {
        boolean status;
        mongoTemplate.insert(student, "newStudent");
        status = true;
        return status;
    }

    @Override
    public int deleteStudent(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        WriteResult writeResult = mongoTemplate.remove(query, Student.class, "student");
        return writeResult.getN();
    }

    @Override
    public int updateStudent(Student student) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(student.getUserName()));
        Update update = new Update();
        if (!Strings.isNullOrEmpty(student.getFirstName())) {
            update.set("firstName", student.getFirstName());
        }
        if (!Strings.isNullOrEmpty(student.getLastName())) {
            update.set("lastName", student.getLastName());
        }
        if (!Strings.isNullOrEmpty(student.getCity())) {
            update.set("city", student.getCity());
        }
        if (student.getAge() != 0) {
            update.set("age", student.getAge());
        }
        WriteResult writeResult = mongoTemplate.updateMulti(query, update, Student.class, "newStudent");
        return writeResult.getN();
    }
}
