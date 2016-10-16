package com.cisc181.exception;

import com.cisc181.core.Person;

public class PersonException extends Exception{
	private Person person;
	
	public PersonException(Person person){
		super();
		this.person = person;
	}
	public Person getPerson(){
		return person;
	}
}
