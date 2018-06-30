package com.basics;

import java.util.HashMap;
import java.util.Map;

public class PrintMaxOccurenceInAlphaOrder {

    private static Integer maxOccurence = 0;
    private static Character finalChar = '0';

    public static void main(String[] args) {

        String hello = "aabbbccccddddd";
        Map<Character , Integer> mapByLetterCounts = new HashMap<>();

        System.out.println("Input: " + hello);

        hello.chars().forEach(c -> populateCounts(mapByLetterCounts, (char) c));

        mapByLetterCounts.forEach((k,v) -> checkForMaxOccurence(k,v));
        System.out.println("Letter= " + finalChar + " , " + maxOccurence + " times");
    }

    private static void populateCounts(Map<Character, Integer> mapByLetterCounts, Character key) {

        Integer count = mapByLetterCounts.get(key);
        if(mapByLetterCounts.containsKey(key))
            mapByLetterCounts.put(key, ++count);
        else
            mapByLetterCounts.put(key, 1);
    }

    private static void checkForMaxOccurence(Character key, Integer currentOccurence) {

        if(currentOccurence > maxOccurence){
            maxOccurence = currentOccurence;
            finalChar = key;
        }
    }
}
