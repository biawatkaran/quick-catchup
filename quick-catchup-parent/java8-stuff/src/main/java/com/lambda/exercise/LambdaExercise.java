package com.lambda.exercise;

import com.basics.Employee;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaExercise {

    public static void main(String[] args) {

        List<Employee> employees = Employee.createEmployees();

        System.out.println("before sorting.... ");
        printList(employees);

        // normal way
        Comparator<Employee> employeeFirstNameComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getFirstName().compareTo(employee2.getFirstName());
            }
        };

        // lambda way
        Comparator<Employee> employeeLambdaComparator = (e1, e2) -> e1.getFirstName().compareTo(e2.getFirstName());

        // functional way
        Comparator<Employee> employeeComparator = Comparator.comparing(Employee::getFirstName);

        Collections.sort(employees, employeeFirstNameComparator);

        System.out.println("after sorting.... ");
        printList(employees);


    }

    private static void printList(List<Employee> employees) {

        employees.forEach(employee -> System.out.println(employee));
    }
}
