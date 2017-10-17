package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eTitle;
import com.cisc181.exception.PersonException;

public class Staff_Test {

	private static final double DELTA = 1e-15;
	@BeforeClass
	public static void setup() {
		
	}

	@Test
	public void AverageSalaryTest() {
		ArrayList<Staff> m = new ArrayList<Staff>();
		Staff s1 = new Staff(eTitle.MRS);
		Staff s2 = new Staff(eTitle.MRS);
		Staff s3 = new Staff(eTitle.MS);
		Staff s4 = new Staff(eTitle.MS);
		Staff s5 = new Staff(eTitle.MR);

		s1.setSalary(1);
		s2.setSalary(2);
		s3.setSalary(3);
		s4.setSalary(4);
		s5.setSalary(5);

		m.add(s1);
		m.add(s2);
		m.add(s3);
		m.add(s4);
		m.add(s5);
		double AverageSalary = 0;
		for (Staff staff : m) {
			AverageSalary += staff.getSalary();
		}
		assertEquals(AverageSalary / m.size() , 3,DELTA);
	}

	@Test(expected = PersonException.class)
	public void InvalidDOBAndPhoneNoTest1() throws PersonException {
			Staff staff = new Staff("sadsasd", "", "sadas",new Date(1900,1,12), "address", "123456789454340", "dassads",
					"2-9", 4, 1, new Date(2000, 12, 12), eTitle.MR);

	}
	@Test(expected = PersonException.class)
	public void InvalidDOBAndPhoneNoTest2() throws PersonException {
			Staff staff = new Staff("fdsfa", "", "efe",new Date(2000, 1, 12), "address", "12345645435232412789", "sdasdsaas",
					"2-9", 4, 1, new Date(2000, 12, 12), eTitle.MR);
}
	@Test(expected = IllegalArgumentException.class)
	public void BornInTheFutureTest() throws IllegalArgumentException, PersonException {

			Staff staff = new Staff("Zuyue", "", "xie",new Date(2099, 1, 12), "address", "1234567890", "sdassaassasa",
					"2-9", 4, 1, new Date(2000, 12, 12), eTitle.MR);

}
}