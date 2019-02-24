package com.intenthq.challenge;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JEnigmaTest {

    @Test
    public void shouldDecodeString1() {

        Map<Integer, Character> map = new HashMap<>();
        map.put(23, 'N');
        map.put(234, ' ');
        map.put(89, 'H');
        map.put(78, 'Q');
        map.put(37, 'A');

        JEnigma e = JEnigma.decipher(map);

        assertThat(e.deciphe(Arrays.asList(1, 2, 3, 7, 3, 2, 3, 7, 2, 3, 4, 8, 9, 7, 8)), is("1N73N7 HQ"));
    }

    @Test
    public void shouldDecodeString2() {

        Map<Integer, Character> map = new HashMap<>();
        map.put(123, 'A');
        map.put(9, 'C');
        map.put(4567, 'B');
        map.put(12, '0');
        map.put(14, 'F');
        map.put(34, 'G');
        map.put(13, 'H');

        JEnigma e = JEnigma.decipher(map);

        assertThat(e.deciphe(Arrays.asList(1, 2, 3, 6, 7, 9, 1, 4, 2, 3, 4, 9, 3, 4, 5, 6, 7, 6, 1, 3, 9, 4, 3, 4)),
                is("A67CF2GCG5676HC4G"));
    }
}