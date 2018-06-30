package com.lambda.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionInterfaceExercise {

    public static void main(String[] args) {

        List<String> names = Arrays.asList(
                "John Smith", "Will Stone",
                "Andy Carpenter", "Nick Waite");

        Function<String, String> getSecondName =  s -> s.substring(s.indexOf(' ') +1);
        Function<String, String> toUpperCase =  s -> s.toUpperCase();

        functionUsageToPrintSecondName(names, getSecondName);
        concatTwoFunctionUsage(names, getSecondName, toUpperCase);
        composeTwoFunctionUsage(names, getSecondName, toUpperCase);
    }

    private static void functionUsageToPrintSecondName(List<String> names, Function<String, String> getSecondName) {

        System.out.println("PRINTINGS SECOND NAME");
        System.out.println("=====================");
        names.forEach( name -> System.out.println(getSecondName.apply(name)));
    }

    private static void concatTwoFunctionUsage(List<String> names, Function<String, String> getSecondName, Function<String, String> toUpperCase) {

        System.out.println("PRINTINGS IN CAPS");
        System.out.println("=================");
        names.forEach( name -> System.out.println(getSecondName.andThen(toUpperCase).apply(name)));
    }

    private static void composeTwoFunctionUsage(List<String> names, Function<String, String> getSecondName, Function<String, String> toUpperCase) {

        System.out.println("USING COMPOSE FUNCTIONS");
        System.out.println("=================");
        names.forEach( name -> System.out.println(toUpperCase.compose(getSecondName).apply(name)));
    }
}
