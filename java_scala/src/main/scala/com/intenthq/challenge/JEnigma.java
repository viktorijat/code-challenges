package com.intenthq.challenge;

import java.util.*;

public class JEnigma {

    // We have a system to transfer information from one place to another. This system
    // involves transferring only list of digits greater than 0 (1-9). In order to decipher
    // the message encoded in the list you need to have a dictionary that will allow
    // you to do it following a set of rules:
    //    > Sample incoming message: (​1,2,3,7,3,2,3,7,2,3,4,8,9,7,8)
    //    > Sample dictionary (​23->‘N’,234->‘ ’,89->‘H’,78->‘Q’,37 ->‘A’)
    //  - Iterating from left to right, we try to match sublists to entries of the map.
    //    A sublist is a sequence of one or more contiguous entries in the original list,
    //    eg. the sublist (1, 2) would match an entry with key 12, while the sublist (3, 2, 3)
    //    would match an entry with key 323.
    //  - Whenever a sublist matches an entry of the map, it’s replaced by the entry value.
    //    When that happens, the sublist is consumed, meaning that its elements can’t be used
    //    for another match. The elements of the mapping however, can be used as many times as needed.
    //  - If there are two possible sublist matches, starting at the same point, the longest one
    //    has priority, eg 234 would have priority over 23.
    //  - If a digit does not belong to any matching sublist, it’s output as is.
    //
    // Following the above rules, the message would be: “1N73N7 HQ”
    // Check the tests for some other (simpler) examples.

    private final Map<Integer, Character> map;

    private JEnigma(final Map<Integer, Character> map) {
        this.map = map;
    }

    public static JEnigma decipher(final Map<Integer, Character> map) {
        return new JEnigma(map);
    }

    public String deciphe(List<Integer> message) {
        if (message.isEmpty() || map.isEmpty()) {
            return "";
        }

        int length = getIntegerLength(findLongestKey());
        List<String> output = new ArrayList<>();

        for (int i = 0; i < message.size(); i++) {

            String initial = "";
            String longest = "";
            int howManyToGo = (i + length > message.size()) ? message.size() : i + length;

            for (int j = i; j < howManyToGo; j++) {
                initial = initial + message.get(j);
                if (map.containsKey(Integer.parseInt(initial))) {
                    if (initial.length() > longest.length()) {
                        longest = initial;
                    }
                }
            }
            if (longest.length() > 0) {
                output.add(String.valueOf(map.get(Integer.parseInt(longest))));
                i += longest.length() - 1;
            } else {
                output.add(String.valueOf(message.get(i)));
            }
        }
        return String.join("", output);
    }

    private int findLongestKey() {
        Map<Integer, Character> newMap = getSortedMap();
        return newMap.entrySet().stream().findFirst().get().getKey();
    }

    private Map<Integer, Character> getSortedMap() {
        Map<Integer, Character> newMap = new TreeMap<>(Collections.reverseOrder());
        newMap.putAll(map);
        return newMap;
    }

    private int getIntegerLength(Integer number) {
        return (int) (Math.log10(number) + 1);
    }
}