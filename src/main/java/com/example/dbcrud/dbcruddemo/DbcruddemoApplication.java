package com.example.dbcrud.dbcruddemo;

import com.example.dbcrud.dbcruddemo.dao.StudentDAO;
import com.example.dbcrud.dbcruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DbcruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbcruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			System.out.println("Hello world from command line runner");
			createStudent(studentDAO);
			queryForAllStudents(studentDAO);
//			queryForLastNameMatchList(studentDAO);
//			updateStudent(studentDAO);
//			deleteStudent(studentDAO);
		};
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int id = 13;
		System.out.println("deleting the student with id: " + id);
		studentDAO.delete(id);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// first find the student with the specified id and then update it.
		int studentId = 1;
		System.out.println("getting details of student with :" + studentId);

		Student s1 = studentDAO.findById(studentId);

		s1.setFirstName("Ram");
		studentDAO.update(s1);

		System.out.println("Updated student details are: "+s1);
	}

	private void queryForLastNameMatchList(StudentDAO studentDAO) {
		// get list of students with lastname.
		String lName = "ch";
		List<Student> students =  studentDAO.findByLastName(lName);

		// display the list of matching students with lastname.

		if(students.size() == 0) {
			System.out.println("No students found with lastname: "+ lName);
		} else {
			System.out.println("Last Name match with :" + lName);
			for (Student s : students) {
				System.out.println(s);
			}
		}
	}

	private void queryForAllStudents(StudentDAO studentDAO) {
		// get list of students.
		List<Student> students = studentDAO.findAll();

		// display the list of students.
		System.out.println("The list of Students form db are: ");
		for (Student s : students) {
			System.out.println(s);
		}

	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("creating a new student object..");
		Student s = new Student("vinay", "chiliveri", "vch@gmail.com");

		// saving the student to db.
		System.out.println("Saving the student to the database..");
		studentDAO.save(s);

		// retrieving the id of the saved object from the db.
		System.out.println("saved student to db and generated id is: " + s.getId());


		// retrieving the student with some specific id.
		Student obj = studentDAO.findById(10);
		if (obj == null) {
			System.out.println("no data found with given id");
		} else {
			System.out.println("reading student with id 1:" + obj);
		}
	}
}
