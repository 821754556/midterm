package com.cisc181.core;

import java.util.Date;
import java.util.UUID;

public class Course extends Semester{
	
	private UUID CourseID;
	private String CourseName;
	private int GradePoints;
	
	public Course(){
		
	}
	
	public Course( UUID courseID, String courseName, int gradePoints) {
		CourseID = courseID;
		CourseName = courseName;
		GradePoints = gradePoints;
	}
	
	public Course(UUID SemesterID, Date StartDate, Date EndDate, UUID CourseID, String CourseName, int GradePoints) {
		super(SemesterID,StartDate,EndDate);
		this.CourseID = CourseID;
		this.CourseName = CourseName;
		this.GradePoints = GradePoints;
	}
	public UUID getCourseID() {
		return CourseID;
	}

	public void setCourseID(UUID courseID) {
		CourseID = courseID;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public int getGradePoints() {
		return GradePoints;
	}

	public void setGradePoints(int gradePoints) {
		GradePoints = gradePoints;
	}
}
