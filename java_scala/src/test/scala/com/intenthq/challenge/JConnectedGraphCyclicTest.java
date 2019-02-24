package com.intenthq.challenge;


import org.junit.Test;

import java.util.Collections;

import static com.intenthq.challenge.JConnectedGraphCyclic.JNode;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class JConnectedGraphCyclicTest {

    /*
     *
     * a2 <- a3
     *  |     |
     * a1 <-- +
     *  |
     *  +-> a4 <- a5
     *
     * */
    @Test
    public void shouldFindGraphConnectionsExample2() {

        JNode a1 = new JNode(1);
        JNode a2 = new JNode(2, Collections.singletonList(a1));
        JNode a3 = new JNode(3, Collections.singletonList(a2));
        a1.addEdge(a3);

        JNode a4 = new JNode(4);
        a1.addEdge(a4);
        JNode a5 = new JNode(5, Collections.singletonList(a4));

        assertThat(JConnectedGraphCyclic.run(a1, a3), is(true));
        assertThat(JConnectedGraphCyclic.run(a3, a2), is(true));
        assertThat(JConnectedGraphCyclic.run(a2, a1), is(true));
        assertThat(JConnectedGraphCyclic.run(a2, a3), is(true));
        assertThat(JConnectedGraphCyclic.run(a2, a4), is(true));
        assertThat(JConnectedGraphCyclic.run(a3, a4), is(true));
        assertThat(JConnectedGraphCyclic.run(a1, a4), is(true));
        assertThat(JConnectedGraphCyclic.run(a1, a5), is(false));
        assertThat(JConnectedGraphCyclic.run(a5, a4), is(true));
        assertThat(JConnectedGraphCyclic.run(a5, a1), is(false));
    }
}