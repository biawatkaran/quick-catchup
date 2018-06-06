package com.lambda.exercise;

import java.util.ArrayList;
import java.util.List;

/*
 * Builder Pattern:
 * 1. create nested static Builder class
 * 2. this should have same number of elements as actual object
 * 3. create method inside Builder class for every field and return this (Builder type)
 * 4. have public build() that returns actual object
 * 5. create private constructor of actual object that will accept builder object and this
 *    constructor will be called from step4.
 */
public class Person {

	private final String givenName;
	private final String surName;
	private final int age;
	private final String eMail;
	private final String phone;
	private final String address;

	public static class Builder {
		private String givenName;
		private String surName;
		private int age;
		private String eMail;
		private String phone;
		private String address;

		public Builder givenName(String givenName) {
			this.givenName = givenName;
			return this;
		}

		public Builder surName(String surName) {
			this.surName = surName;
			return this;
		}

		public Builder age(int age) {
			this.age = age;
			return this;
		}

		public Builder eMail(String eMail) {
			this.eMail = eMail;
			return this;
		}

		public Builder phone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder address(String address) {
			this.address = address;
			return this;
		}

		public Person build() {
			return new Person(this);
		}
	}

	private Person(Builder builder) {
		this.givenName = builder.givenName;
		this.surName = builder.surName;
		this.age = builder.age;
		this.eMail = builder.eMail;
		this.phone = builder.phone;
		this.address = builder.address;
	}

	public String getGivenName() {
		return givenName;
	}

	public String getSurName() {
		return surName;
	}

	public int getAge() {
		return age;
	}

	public String geteMail() {
		return eMail;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public static List<Person> createPersonList(){
		List<Person> persons = new ArrayList<Person>();
		
		persons.add(new Builder().givenName("Bob1")
             .surName("Baker")
             .age(10)
             .eMail("bob.baker@example.com")
             .phone("201-121-4678")
             .address("44 4th St, Smallville, KS 12333")
             .build() );
		
		persons.add(new Builder().givenName("Charlie2")
	             .surName("Baker")
	             .age(19)
	             .eMail("bob.baker@example.com")
	             .phone("201-121-4678")
	             .address("44 4th St, Smallville, KS 12333")
	             .build() );
		
		persons.add(new Builder().givenName("John3")
	             .surName("Baker")
	             .age(5)
	             .eMail("bob.baker@example.com")
	             .phone("201-121-4678")
	             .address("44 4th St, Smallville, KS 12333")
	             .build() );
		
		return persons;
	}
	
	@Override
	public String toString() {
		return "Person [givenName=" + givenName + ", surName=" + surName
				+ ", age=" + age + ", eMail=" + eMail + ", phone=" + phone
				+ ", address=" + address + "]";
	}
}