package org.dashia18.service.interviews;

import java.util.Arrays;
import java.util.List;

/*
Given a list of integers, use the Stream API to filter out all odd numbers and collect the results into a new list.
 */
public class StreamTasks {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //your solution
        List<Integer> evenNumbers = numbers.stream()
                .filter(el -> el % 2 == 0)
                .toList();

        System.out.println("Even numbers: " + evenNumbers);
    }
}
