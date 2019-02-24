package com.intenthq.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JNiceStrings {
    // From http://adventofcode.com/day/5
    //  --- Day 5: Doesn't He Have Intern-Elves For This? ---
    //
    //  Santa needs help figuring out which strings in his text file are naughty or nice.
    //
    //    A nice string is one with all of the following properties:
    //
    //    It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
    //  It contains at least one letter that appears twice in a row, like xx, abcdde (dd), or aabbccdd (aa, bb, cc, or dd).
    //    It does not contain the strings ab, cd, pq, or xy, even if they are part of one of the other requirements.
    //    For example:
    //
    //    ugknbfddgicrmopn is nice because it has at least three vowels (u...i...o...), a double letter (...dd...), and none of the disallowed substrings.
    //  aaa is nice because it has at least three vowels and a double letter, even though the letters used by different rules overlap.
    //    jchzalrnumimnmhp is naughty because it has no double letter.
    //    haegwjzuvuyypxyu is naughty because it contains the string xy.
    //    dvszwmarrgswjxmb is naughty because it contains only one vowel.
    //    How many strings are nice?

    private static List<String> vowels = new ArrayList<>(Arrays.asList("a", "e", "i", "o", "u"));
    private static List<String> unacceptedSubStrings = new ArrayList<>(Arrays.asList("ab", "cd", "pq", "xy"));

    private static boolean stringHasThreeVowels(String inputString) {
        return Math.toIntExact(Stream.of(inputString.split(""))
                .filter(elem -> vowels.contains(elem)).count()) >= 3;
    }

    private static boolean stringDoesNotContainUnacceptedSubStrings(String inputString) {
        return unacceptedSubStrings.stream().noneMatch(inputString::contains);
    }

    private static boolean hasADoubleLetter(String inputString) {
        for (int i = 0; i < inputString.toCharArray().length - 1; i++) {
            if (inputString.charAt(i) == inputString.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isStringNice(String toInspect) {
        return stringHasThreeVowels(toInspect) && stringDoesNotContainUnacceptedSubStrings(toInspect)
                && hasADoubleLetter(toInspect);
    }

    public static int nice(List<String> xs) {
        return Math.toIntExact(xs.stream().filter(JNiceStrings::isStringNice).count());
    }
}