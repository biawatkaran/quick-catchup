package com.lambda.exercise;

import com.basics.Employee;

import java.util.List;
import java.util.function.Predicate;

public class PredicateExercise {

    public static void main(String[] args) {

        List<Employee> employees = Employee.createEmployees();

        Predicate<Employee> employeeNameStartsWithA = employee -> employee.getFirstName().startsWith("A");
        printByCondition(employees, employeeNameStartsWithA);
    }

    public static void printByCondition(List<Employee> employees, Predicate<Employee> employeeNameStartsWithA){

        employees.forEach(employee -> {
            if(employeeNameStartsWithA.test(employee)){
                System.out.println(employee);
            }
        });
    }


}
