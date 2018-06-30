package com.lambda.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class StreamExercise {

    public static void main(String[] args) {

        List<String> names = Arrays.asList(
                "John Smith", "Will Stone",
                "Andy Carpenter", "Nick Waite");

        Predicate<String> surnameWithSFilter = fullName -> fullName.substring(fullName.indexOf(' ') + 1).startsWith("S");

        names.stream()
                .map(String :: toUpperCase)
                .filter(surnameWithSFilter)
                .sorted()
                .forEach(System.out :: println);

    }
}
