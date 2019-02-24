package com.intenthq.challenge;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.intenthq.challenge.JConnectedGraph.JNode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class JConnectedGraphTest {

    @Test
    public void shouldFindGraphConnectionsExample1() {

        JNode ten = new JNode(10, Collections.emptyList());
        JNode nine = new JNode(9, Collections.emptyList());
        JNode two = new JNode(2, Collections.emptyList());
        JNode eight = new JNode(8, Collections.singletonList(nine));
        JNode three = new JNode(3, Arrays.asList(eight, ten));
        JNode eleven = new JNode(11, Arrays.asList(two, nine, ten));
        JNode seven = new JNode(7, Arrays.asList(eleven, eight));
        JNode five = new JNode(5, Collections.singletonList(eleven));

        assertThat(JConnectedGraph.run(ten, nine), is(false));
        assertThat(JConnectedGraph.run(three, ten), is(true));
        assertThat(JConnectedGraph.run(three, two), is(false));
        assertThat(JConnectedGraph.run(eight, ten), is(false));
        assertThat(JConnectedGraph.run(eight, nine), is(true));
        assertThat(JConnectedGraph.run(seven, two), is(true));
        assertThat(JConnectedGraph.run(three, nine), is(true));
        assertThat(JConnectedGraph.run(five, eleven), is(true));
        assertThat(JConnectedGraph.run(five, nine), is(true));
        assertThat(JConnectedGraph.run(five, two), is(true));
    }

    @Test
    public void shouldFindGraphConnectionsExample2() {

        // a1 -+-> b2 -> c3 -> e5
        //    |                |
        //    +-> d4 ->  f6 -> g7

        JNode g = new JNode(7);
        JNode f = new JNode(6, Collections.singletonList(g));
        JNode e = new JNode(5, Collections.singletonList(g));
        JNode c = new JNode(3, Collections.singletonList(e));
        JNode b = new JNode(2, Collections.singletonList(c));
        JNode d = new JNode(4);
        JNode a = new JNode(1, Arrays.asList(b, d));

        assertThat(JConnectedGraph.run(a, a), is(true));
        assertThat(JConnectedGraph.run(a, b), is(true));
        assertThat(JConnectedGraph.run(b, d), is(false));
        assertThat(JConnectedGraph.run(a, e), is(true));
        assertThat(JConnectedGraph.run(e, b), is(false));
        assertThat(JConnectedGraph.run(e, d), is(false));
        assertThat(JConnectedGraph.run(e, g), is(true));
        assertThat(JConnectedGraph.run(f, g), is(true));
        assertThat(JConnectedGraph.run(a, g), is(true));
        assertThat(JConnectedGraph.run(c, g), is(true));
    }
}