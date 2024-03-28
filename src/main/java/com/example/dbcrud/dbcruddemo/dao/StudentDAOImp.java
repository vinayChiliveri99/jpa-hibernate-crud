package com.example.dbcrud.dbcruddemo.dao;

import com.example.dbcrud.dbcruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImp implements StudentDAO{

    private EntityManager entityManager;

    // injecting using constructor injection.
    @Autowired
    public StudentDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implementing the save method.
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName LIKE:theName", Student.class);
        theQuery.setParameter("theName", lName);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    // adding transactional since it is an update.
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student s = entityManager.find(Student.class, id);
        entityManager.remove(s);
    }
}
