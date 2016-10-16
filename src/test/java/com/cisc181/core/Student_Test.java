package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eMajor;
import com.cisc181.exception.PersonException;

public class Student_Test {
	private static ArrayList<Semester> semesters = new ArrayList<Semester>();
	private static ArrayList<Course> courses = new ArrayList<Course>();
	private static ArrayList<Section> sections = new ArrayList<Section>();
	private static ArrayList<Student> students = new ArrayList<Student>();
	private static final double DELTA = 1e-15;
	private static int row = 0;
	private static int col = 0;

	public int setrow(int newrow) {
		row = newrow;
		return row;
	}

	public int setcol(int newcol) {
		col = newcol;
		return col;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws PersonException {
		semesters.add(new Semester(UUID.randomUUID(), new Date(2012, 9, 1), new Date(2016, 12, 24)));
		semesters.add(new Semester(UUID.randomUUID(), new Date(2012, 2, 8), new Date(2016, 5, 27)));
		courses.add(new Course(UUID.randomUUID(), "MATH", 0));
		courses.add(new Course(UUID.randomUUID(), "CHEM", 0));
		courses.add(new Course(UUID.randomUUID(), "JAPN", 0));
		Section section1 = new Section(UUID.randomUUID(), 221);
		Section section2 = new Section(UUID.randomUUID(), 222);
		sections.add(section1);
		sections.add(section2);
		Student student1 = new Student("1A", "B", "C", new Date(2001, 1, 1), eMajor.COMPSI, "GeorgeRead201",
				"3021111111", "1A@udel.edu");
		Student student2 = new Student("2B", "B", "C", new Date(2002, 1, 1), eMajor.BUSINESS, "GeorgeRead202",
				"3021111112", "2B@udel.edu");
		Student student3 = new Student("3C", "B", "C", new Date(2003, 1, 1), eMajor.CHEM, "GeorgeRead203", "3021111113",
				"3C@udel.edu");
		Student student4 = new Student("4D", "B", "C", new Date(2004, 1, 1), eMajor.NURSING, "GeorgeRead204",
				"3021111114", "4D@udel.edu");
		Student student5 = new Student("5E", "B", "C", new Date(2005, 1, 1), eMajor.PHYSICS, "GeorgeRead205",
				"3021111115", "5E@udel.edu");
		Student student6 = new Student("6F", "B", "C", new Date(2006, 1, 1), eMajor.COMPSI, "GeorgeRead206",
				"3021111116", "6F@udel.edu");
		Student student7 = new Student("7G", "B", "C", new Date(2007, 1, 1), eMajor.BUSINESS, "GeorgeRead207",
				"3021111117", "7G@udel.edu");
		Student student8 = new Student("8H", "B", "C", new Date(2008, 1, 1), eMajor.CHEM, "GeorgeRead208", "3021111118",
				"8H@udel.edu");
		Student student9 = new Student("9I", "B", "C", new Date(2009, 1, 1), eMajor.NURSING, "GeorgeRead209",
				"3021111119", "9I@udel.edu");
		Student student10 = new Student("0J", "B", "C", new Date(2010, 1, 1), eMajor.PHYSICS, "GeorgeRead210",
				"3021111120", "10J@udel.edu");
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		students.add(student5);
		students.add(student6);
		students.add(student7);
		students.add(student8);
		students.add(student9);
		students.add(student10);
	}

	@Test
	public void StudentEnrollmentTest() {
		ArrayList<Section> Year2016 = new ArrayList<Section>();
		for (Semester semester : semesters) {
			for (Course course : courses) {
				for (Section section : sections) {
					section.setSemesterID(semester.getSemesterID());
					section.setStartDate(semester.getStartDate());
					section.setEndDate(semester.getEndDate());
					section.setCourseID(course.getCourseID());
					section.setCourseName(course.getCourseName());
					section.setGradePoints(course.getGradePoints());
					Year2016.add(section);
				}
			}
		}

		for (Section section : Year2016) {
			for (Student st : students) {
				section.EnrollStudentInToSection(st);
			}
		}
		// 10 students in total,each of them have to take 12 sections' courses
		// set the grade by random number from 1 - 100
		// and calculate the GPA of him/her.
		// all the course are considered the same credit
		// Standard is 91-100A 81-90B 71-80C 51-70D 0-50F
		// A is 4.0 B is 3.1 C is 2.2 D is 1.3, F is 0
		// the GPA formula i use is the point they get / 12

		// the score of students are given below
		int[][] matrix = new int[10][12];
		for (int row = 0; row <= 9; row++) {
			for (int col = 0; col <= 11; col++) {
				matrix[row][col] = ThreadLocalRandom.current().nextInt(50, 99 + 1);
			}
		}
		int total = 0;
		for (int col = 0; col <= 11; col++) {
			if (matrix[row][col] <= 100 && matrix[row][col] >= 91) {
				total += 4;
			} else if (matrix[row][col] < 91 && matrix[row][col] >= 81) {
				total += 3.1;
			} else if (matrix[row][col] < 81 && matrix[row][col] >= 71) {
				total += 2.2;
			} else if (matrix[row][col] < 71 && matrix[row][col] >= 51) {
				total += 1.3;
			} else {
				total += 0;
			}
		}
		float GPA = total / 12;
		total = 0;
		int totalb = 0;
		Section newsection = new Section();
		for (int col1 = 0; col1 <= 11; col1++) {
			totalb += newsection.setEachGPA(matrix[row][col1]);
		}
		float GPA2 = totalb / 12;
		totalb = 0;
		// people can do different assert test
		// based on this method
		setrow(9);
		assertEquals(GPA, GPA2, DELTA);
		// end of the GPA test

		// start of the course grade test
		int totalc = 0;
		for (int row0 = 0; row0 <= 9; row0++) {
			if (matrix[row0][col] <= 100 && matrix[row0][col] >= 91) {
				totalc += 4;
			} else if (matrix[row0][col] < 91 && matrix[row0][col] >= 81) {
				totalc += 3.1;
			} else if (matrix[row0][col] < 81 && matrix[row0][col] >= 71) {
				totalc += 2.2;
			} else if (matrix[row0][col] < 71 && matrix[row0][col] >= 51) {
				totalc += 1.3;
			} else {
				totalc += 0;
			}
		}
		float average_grade = totalc / 10;

		int total_grade = 0;
		for (int row1 = 0; row1 <= 9; row1++) {
			total_grade += newsection.setEachGPA(matrix[row1][col]);
		}
		float average_gradeb = total_grade / 10;
		total_grade = 0;
		setcol(7);
		assertEquals(average_grade, average_gradeb, DELTA);
	}

	@Test
	public void ChangeMajorTest1() {
		assertEquals(students.get(0).getMajor(), eMajor.COMPSI);
		students.get(0).setMajor(eMajor.BUSINESS);
		assertEquals(students.get(0).getMajor(), eMajor.BUSINESS);
	}

	@Test
	public void ChangeMajorTest2() {
		assertEquals(students.get(6).getMajor(), eMajor.BUSINESS);
		students.get(6).setMajor(eMajor.NURSING);
		assertEquals(students.get(6).getMajor(), eMajor.NURSING);
	}

}