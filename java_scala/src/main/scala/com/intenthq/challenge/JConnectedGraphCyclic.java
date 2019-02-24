package com.intenthq.challenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class JConnectedGraphCyclic {

    static boolean run(JNode source, JNode target) {
        return hasACyclicConnection(source, target, new ArrayList<>());
    }

    private static boolean hasACyclicConnection(JNode a, JNode b, List<JNode> descendants) {

        if (descendants.contains(a)) {
            return false;
        } else if (a.equals(b)) {
            return true;
        } else {
            descendants.add(a);
            return a.edges.stream().anyMatch(i -> hasACyclicConnection(i, b, descendants));
        }
    }

    static class JNode {
        final int value;

        //I removed the final in order to be able to add a setter, for testing purposes
        List<JNode> edges;

        JNode(final int value, List<JNode> edges) {
            this.value = value;
            this.edges = edges;
        }

        JNode(final int value) {
            this.value = value;
            this.edges = new ArrayList<>();
        }


        /*
         * The inner class was not able to create a cyclic graph, so I added these methods in order to test
         * that scenario.
         * */
        void addEdge(JNode edge) {
            List<JNode> newEdges = this.getEdges();
            newEdges.add(edge);
            this.setEdges(newEdges);
        }

        List<JNode> getEdges() {
            return this.edges;
        }
        
        void setEdges(List<JNode> edges) {
            this.edges = edges;
        }
    }

}
