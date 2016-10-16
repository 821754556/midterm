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
		ArrayList<Staff> StaffMember = new ArrayList<Staff>();
		Staff staff1 = new Staff(eTitle.MR);
		Staff staff2 = new Staff(eTitle.MRS);
		Staff staff3 = new Staff(eTitle.MS);
		Staff staff4 = new Staff(eTitle.MR);
		Staff staff5 = new Staff(eTitle.MRS);

		staff1.setSalary(1);
		staff2.setSalary(2);
		staff3.setSalary(3);
		staff4.setSalary(4);
		staff5.setSalary(5);

		StaffMember.add(staff1);
		StaffMember.add(staff2);
		StaffMember.add(staff3);
		StaffMember.add(staff4);
		StaffMember.add(staff5);
		double AverageSalary = 0;
		for (Staff staff : StaffMember) {
			AverageSalary += staff.getSalary();
		}
		assertEquals(AverageSalary / StaffMember.size() , 3,DELTA);
	}

	@Test(expected = PersonException.class)
	public void InvalidDOBAndPhoneNoTest1() throws PersonException {
			Staff staff = new Staff("Zuyue", "", "xie",new Date(1900,1,12), "address", "1234567890", "emailaddress",
					"2-9", 4, 1, new Date(2000, 12, 12), eTitle.MR);

	}
	@Test(expected = PersonException.class)
	public void InvalidDOBAndPhoneNoTest2() throws PersonException {
			Staff staff = new Staff("Zuyue", "", "xie",new Date(2000, 1, 12), "address", "123456789", "emailaddress",
					"2-9", 4, 1, new Date(2000, 12, 12), eTitle.MR);
}
	@Test(expected = IllegalArgumentException.class)
	public void BornInTheFutureTest() throws IllegalArgumentException, PersonException {

			Staff staff = new Staff("Zuyue", "", "xie",new Date(2099, 1, 12), "address", "1234567890", "emailaddress",
					"2-9", 4, 1, new Date(2000, 12, 12), eTitle.MR);

}
}