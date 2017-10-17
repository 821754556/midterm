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
		semesters.add(new Semester(UUID.randomUUID(), new Date(2017, 9, 1), new Date(2017, 12, 24)));
		semesters.add(new Semester(UUID.randomUUID(), new Date(2018, 2, 8), new Date(2018, 5, 27)));
		courses.add(new Course(UUID.randomUUID(), "CHIN", 10));
		courses.add(new Course(UUID.randomUUID(), "CHEM", 10));
		courses.add(new Course(UUID.randomUUID(), "MATH", 10));
		Section section1 = new Section(UUID.randomUUID(), 221);
		Section section2 = new Section(UUID.randomUUID(), 222);
		sections.add(section1);
		sections.add(section2);
		Student student1 = new Student("sad", "Bs", "Ca", new Date(1999, 1, 11), eMajor.COMPSI, "dgfdgfgdfgg",
				"3021111111", "1ytyytA@udel.edu");
		Student student2 = new Student("2Bs", "Bs", "Ca", new Date(1998, 1, 21), eMajor.BUSINESS, "fgfgdg",
				"3021111112", "2rrB@udel.edu");
		Student student3 = new Student("3sC", "Ba", "Ca", new Date(1997, 1, 13), eMajor.CHEM, "Gfgdfgd", "3021411113",
				"3C@udel.edu");
		Student student4 = new Student("4gdD", "Bg", "Ceref", new Date(2004, 1, 14), eMajor.NURSING, "Gefdgdgd04",
				"3021111114", "4trD@udel.edu");
		Student student5 = new Student("5dE", "Bd", "dC", new Date(2004, 1, 12), eMajor.PHYSICS, "Gefoff", "3021111118",
				"8H@udel.edu");
		Student student6 = new Student("d6F", "Bd", "Cd", new Date(2006, 1, 12), eMajor.COMPSI, "GefforfgeRffadff206",
				"3021111116", "6F@udel.edu");
		Student student7 = new Student("7dG", "Bd", "derererC", new Date(2007, 1, 12), eMajor.BUSINESS, "GeorgeffReffd20f7",
				"3021111117", "7G@udel.edu");
		Student student8 = new Student("8Hd", "Bd", "Cererd", new Date(2008, 1, 13), eMajor.CHEM, "GeorgeRead208", "3021111118",
				"8H@udel.edu");
		Student student9 = new Student("9Ids", "Bd", "Cd", new Date(2009, 1, 15), eMajor.NURSING, "GeorddfsfsReadsdf", "3021111118",
				"8H@udel.edu");
		Student student10 = new Student("0dJ", "dB", "dC", new Date(2010, 1, 13), eMajor.PHYSICS, "GeorgeRfdssfeasfsfd210",
				"3021111120", "10ddJ@udel.edu");
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
		 
		setrow(9);
		// test GPA
		assertEquals(GPA, GPA2, DELTA);
		 
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
		// test average GPA
		assertEquals(average_grade, average_gradeb, DELTA);
	}

	 
}