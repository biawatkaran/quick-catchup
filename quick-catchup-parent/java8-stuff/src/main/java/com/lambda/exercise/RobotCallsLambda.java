package com.lambda.exercise;

import java.util.List;
import java.util.function.Predicate;

public class RobotCallsLambda {

	public void phoneContacts(List<Person> persons, Predicate<Person> predicate) {
		for (Person person : persons) {
			if (predicate.test(person)) {
				call(person);
			}
		}
	}
	
	public void emailContacts(List<Person> persons, Predicate<Person> predicate) {
		for (Person person : persons) {
			if (predicate.test(person)) {
				email(person);
			}
		}
	}
	
	public void mailContacts(List<Person> persons, Predicate<Person> predicate) {
		for (Person person : persons) {
			if (predicate.test(person)) {
				mail(person);
			}
		}
	}

	private void call(Person p) {
        System.out.println("Calling " + p.getGivenName() + " " + p.getSurName()
				+ " age " + p.getAge() + " at " + p.getPhone());
	}

	private void email(Person p) {
		System.out.println("Emailing " + p.getGivenName() + " " + p.getSurName()
				+ " age " + p.getAge() + " at " + p.getPhone());
	}
	
	private void mail(Person p) {
		System.out.println("Mailing " + p.getGivenName() + " " + p.getSurName()
				+ " age " + p.getAge() + " at " + p.getPhone());
	}
}