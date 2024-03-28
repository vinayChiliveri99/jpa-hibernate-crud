package com.example.dbcrud.dbcruddemo.dao;

import com.example.dbcrud.dbcruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    // method to save the student to the db.
    void save(Student theStudent);

    // method to get a student with specified id.
    Student findById(Integer id);

    // method to find all or get all the students in the db.
    List<Student> findAll();

    // method to find a student by lastname.
    List<Student> findByLastName(String lName);

    // method to update a student.
    void update(Student theStudent);

    // method to delete a student.
    void delete(Integer id);
}
