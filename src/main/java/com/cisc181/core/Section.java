package com.cisc181.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Section extends Course {

	private UUID semesterID;
	private UUID courseID;
	private UUID sectionID;
	private int roomID;
	private ArrayList<Student> StudentInSection = new ArrayList<Student>();

	public void EnrollStudentInToSection(Student s) {
		StudentInSection.add(s);
	}

	public ArrayList<Student> GetStudentsInSection() {
		return StudentInSection;
	}

	public double setEachGPA(double score) {
		double GPAE = 0;
		if (score <= 100 && score >= 91) {
			GPAE += 4;
		} else if (score < 91 && score >= 81) {
			GPAE += 3.1;
		} else if (score < 81 && score >= 71) {
			GPAE += 2.2;
		} else if (score < 71 && score >= 51) {
			GPAE += 1.3;
		} else {
			GPAE += 0;
		}
		return GPAE;
	}



	public Section() {

	}

	public Section(UUID sectionID, int roomID) {
		this.sectionID = sectionID;
		this.roomID = roomID;
	}

	public Section(UUID semesterID, UUID courseID, UUID sectionID, int roomID) {
		this.semesterID = semesterID;
		this.courseID = courseID;
		this.sectionID = sectionID;
		this.roomID = roomID;

	}

	public Section(UUID SemesterID, Date StartDate, Date EndDate, UUID CourseID, String CourseName, int GradePoints,
			UUID semesterID, UUID courseID, UUID sectionID, int roomID) {
		super(SemesterID, StartDate, EndDate, CourseID, CourseName, GradePoints);
		this.semesterID = semesterID;
		this.courseID = courseID;
		this.sectionID = sectionID;
		this.roomID = roomID;
	}

	public UUID getSemesterID() {
		return semesterID;
	}

	public void setSemesterID(UUID semesterID) {
		this.semesterID = semesterID;
	}

	public UUID getCourseID() {
		return courseID;
	}

	public void setCourseID(UUID courseID) {
		this.courseID = courseID;
	}

	public UUID getSectionID() {
		return sectionID;
	}

	public void setSectionID(UUID sectionID) {
		this.sectionID = sectionID;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
}
