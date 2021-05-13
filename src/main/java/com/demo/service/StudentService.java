package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.Student;
import com.demo.repo.StudentRepository;

@Service // spring bean container
public class StudentService {
	
	@Autowired
	private StudentRepository repository;

	public Student getStudentById(int id) {
		return repository.findById(id).orElse(null);
	}

	public Student saveStudent(Student student) {
		return repository.save(student);
	}
	
	public List<Student> getAllStudents(){
		return repository.findAll();
	}
	
	public void deleteStudentById(int studentId) {
		repository.deleteById(studentId);
	}

}
