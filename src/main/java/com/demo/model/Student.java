package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tbl_students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "student_id")
	private int studentId;
	
	@NotBlank(message = "Name cannot be empty")
	@Column(name = "full_name", nullable = false)
	private String fullName;
	
	// 1 12
	@Min(value = 1, message = "Grade should be more than or equal to 1")
	@Max(value = 12, message = "Grade cannot be more than 12")
	@Column(name = "grade", nullable = false)
	private int grade;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", fullName=" + fullName + ", grade=" + grade + "]";
	}
	
	

}
