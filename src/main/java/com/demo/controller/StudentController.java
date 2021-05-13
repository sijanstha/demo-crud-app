package com.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.Student;
import com.demo.repo.StudentRepository;
import com.demo.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	/**
	 * Input validation : client side using js / server side 
	 * Service Layer :: Unit testing
	 * Error and Exception handling
	 * Search functionality :: student id search student data html page ::
	 * js/rest api
	 * data jpa / hibernate :: mapping :: one to many, one to one
	 * db level :: mysql mysql workbench
	 * spring security
	 * post-comment
	 */
	
	@GetMapping(value = "")
	public ModelAndView showAllStudents(ModelAndView modelAndView) {
		List<Student> students = studentService.getAllStudents();
		modelAndView.setViewName("index");
		modelAndView.addObject("studentList", students);
		return modelAndView;
	}
	
	@GetMapping(value = "/add-student")
	public ModelAndView showAddStudentForm(ModelAndView modelAndView) {
		modelAndView.setViewName("addStudentForm");
		modelAndView.addObject("student", new Student());
		return modelAndView;
	}
	
	@PostMapping(value = "/add-student")
	public String processStudentData(@Valid @ModelAttribute Student student, BindingResult result){
		if(result.hasErrors()) {
			return "addStudentForm";
		}
		studentService.saveStudent(student);
		return "redirect:/students/";
	}
	
	@GetMapping(value = "/edit-student/{id}")
	public ModelAndView showStudentEditPage(@PathVariable int id, ModelAndView modelAndView) {
		Student fetchedStudent = studentService.getStudentById(id);
		if(fetchedStudent == null) {
			modelAndView.addObject("message", "No student found under this id: " + id);
			modelAndView.setViewName("error-page");
			return modelAndView;
		}
		modelAndView.setViewName("editStudentForm");
		modelAndView.addObject("student", fetchedStudent);
		return modelAndView;
	}
	
	@PostMapping(value = "/edit/student")
	public String processEditStudentForm(@ModelAttribute Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping(value = "/delete-student/{id}")
	public ModelAndView processDeleteStudent(@PathVariable int id, ModelAndView modelAndView) {
		Student fetchedStudent = studentService.getStudentById(id);
		if(fetchedStudent == null) {
			modelAndView.addObject("message", "No student found under this id: " + id);
			modelAndView.setViewName("error-page");
			return modelAndView;
		}
		studentService.deleteStudentById(id);
		List<Student> students = studentService.getAllStudents();
		modelAndView.setViewName("index");
		modelAndView.addObject("studentList", students);
		return modelAndView;
	}

}
