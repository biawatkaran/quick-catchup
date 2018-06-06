package com.lambda.exercise;

import java.util.List;
import java.util.function.Predicate;


public class LambdaExample {

	public static void main(String[] args) {

		List<Person> persons = Person.createPersonList();
		
		Predicate<Person> allDrivers = p -> {
            return p.getAge() >= 16;
        };
		Predicate<Person> allDraftees = p -> p.getAge() >= 18 && p.getAge() <= 25;
		Predicate<Person> allPilots = p -> p.getAge() >= 23 && p.getAge() <= 65;
		
		RobotCallsLambda robot = new RobotCallsLambda();
		robot.phoneContacts(persons, allDrivers);
		robot.emailContacts(persons, allDraftees);
		robot.mailContacts(persons, allPilots);
		
		//can be mixed as well
		robot.phoneContacts(persons, allPilots);
	}
}
