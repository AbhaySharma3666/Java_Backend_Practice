package com.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {
	@Id
	private int id;
	@Column(name = "Full_Name")
	private String name;
	private String studentClass;
	private Marks student_marks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public Marks getStudent_marks() {
		return student_marks;
	}

	public void setStudent_marks(Marks student_marks) {
		this.student_marks = student_marks;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", studentClass=" + studentClass + ", student_marks="
				+ student_marks + "]";
	}

}
