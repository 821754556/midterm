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
		Student student1 = new Student("带", "哥", "个", new Date(1995, 1, 1), eMajor.COMPSI, "weqeqwe",
				"3026099554398", "gdfgfdg@udel.edu");
		Student student2 = new Student("你", "吗", "怕", new Date(2005, 1, 1), eMajor.BUSINESS, "weqwwqwqew",
				"3021111112", "gfdgfdgd@udel.edu");
		Student student3 = new Student("干死你", "傻吊", "干", new Date(1994, 1, 1), eMajor.CHEM, "weqweqw", "3021111113",
				"sddsgsd@udel.edu");
		Student student4 = new Student("擦", "个", "哦", new Date(2001, 1, 1), eMajor.NURSING, "erwerweerwer",
				"3021111114", "fdgfdgfd@udel.edu");
		Student student5 = new Student("嗯", "啪啪啪", "的", new Date(2005, 1, 1), eMajor.PHYSICS, "fgdgfddgfgfd",
				"3021111115", "gsdgdd@udel.edu");
		Student student6 = new Student("的", "人", "人", new Date(2006, 1, 1), eMajor.COMPSI, "fgdgfdgfdgdfg",
				"3021111116", "erwerew@udel.edu");
		Student student7 = new Student("发", "额", "人", new Date(2005, 1, 1), eMajor.BUSINESS, "fdgfdgfgfdgd",
				"3021111117", "hfdhfd@udel.edu");
		Student student8 = new Student("人", "个", "怕", new Date(2009, 1, 1), eMajor.CHEM, "fdgfdgfdgfgdf", "3021111118",
				"rdfgdg@udel.edu");
		Student student9 = new Student("人", "日", "他", new Date(2012, 1, 1), eMajor.NURSING, "fgfdgfdgfgdgf",
				"3021111119", "ewtgdewr@udel.edu");
		Student student10 = new Student("t", "r", "额", new Date(1999, 1, 1), eMajor.PHYSICS, "gdfgdfgdfgdfg",
				"3021111120", "hewerewe@udel.edu");
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
		ArrayList<Section> Year2017 = new ArrayList<Section>();
		for (Semester semester : semesters) {
			for (Course course : courses) {
				for (Section section : sections) {
					section.setSemesterID(semester.getSemesterID());
					section.setStartDate(semester.getStartDate());
					section.setEndDate(semester.getEndDate());
					section.setCourseID(course.getCourseID());
					section.setCourseName(course.getCourseName());
					section.setGradePoints(course.getGradePoints());
					Year2017.add(section);
				}
			}
		}

		for (Section section : Year2017) {
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