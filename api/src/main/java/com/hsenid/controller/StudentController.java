package com.hsenid.controller;

import com.hsenid.Student;
import com.hsenid.StudentServiceImpl;
import com.hsenid.SuccessMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Vidushka on 20/06/17.
 */

@RestController
@RequestMapping("/mongoApi/")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> getAllStudents() {
        List<Student> students;
        students = studentServiceImpl.getStudents();

        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setStatus("success");
        successMessage.setCode(HttpStatus.OK.value());

        if (students.size() > 0) {
            successMessage.setMessage(students.size() + " records found.");
            for (int i = 0; i < students.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("userName", students.get(i).getUserName());
                    jsonObject.put("firstName", students.get(i).getFirstName());
                    jsonObject.put("lastName", students.get(i).getLastName());
                    jsonObject.put("age", students.get(i).getAge());
                    jsonObject.put("city", students.get(i).getCity());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                successMessage.addData(jsonObject);
            }
        } else {
            successMessage.setMessage("No records found.");
        }
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> insertStudent(@RequestBody Student student) {
        boolean status = studentServiceImpl.addStudent(student);
        SuccessMessage successMessage = new SuccessMessage();
        if (status) {
            successMessage.setStatus("success");
            successMessage.setCode(HttpStatus.CREATED.value());
            successMessage.setMessage("new student added.");
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("userName", student.getUserName());
                jsonObject.put("firstName", student.getFirstName());
                jsonObject.put("lastName", student.getLastName());
                jsonObject.put("age", student.getAge());
                jsonObject.put("city", student.getCity());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            successMessage.addData(jsonObject);
        }
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userName}", method = RequestMethod.DELETE, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> deleteStudent(@PathVariable("userName") String userName) {
        int affectedRows = studentServiceImpl.deleteStudent(userName);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setStatus("success");
        successMessage.setCode(HttpStatus.OK.value());
        if (affectedRows > 0) {
            successMessage.setMessage("student record deleted.");
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("userName", userName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            successMessage.addData(jsonObject);
        } else {
            successMessage.setMessage("no matching records found to delete.");
        }
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {"application/json"}, consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity<Object> updateStudent(@RequestBody Student student) {
        int affecteRows = studentServiceImpl.updateStudent(student);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setStatus("success");
        successMessage.setCode(HttpStatus.OK.value());
        if (affecteRows > 0) {
            successMessage.setMessage("student record updated.");
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("userName", student.getUserName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            successMessage.addData(jsonObject);
        } else {
            successMessage.setMessage("no matching records found to update.");
        }
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }
}
