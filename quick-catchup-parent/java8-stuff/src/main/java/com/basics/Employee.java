package com.basics;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private String firstName;
    private String surName;

    public static class Builder {

        private String firstName;

        private String surName;

        public Builder addFirstName(String firstName){

            this.firstName = firstName;
            return this;
        }

        public Builder addSurName(String surName){

            this.surName = surName;
            return this;
        }

        public Employee build(){

            Employee employee = new Employee();
            employee.firstName = this.firstName;
            employee.surName = this.surName;

            return employee;
        }

    }

    private Employee(){

    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public static List<Employee> createEmployees(){

        List<Employee> employees = new ArrayList();

        Employee employee1  = new Builder().addFirstName("John").addSurName("Smith").build();
        Employee employee2 = new Builder().addFirstName("Will").addSurName("Stone").build();
        Employee employee3 = new Builder().addFirstName("Andy").addSurName("Murray").build();

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        return employees;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                '}';
    }
}