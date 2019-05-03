package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome result;
    private HashMap<Vertex, Node> map;
    private Set<Vertex> closed;
    private Vertex end;
    private int num;
    private double time;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        map = new HashMap<>();
        closed = new HashSet<>();
        ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();
        this.end = end;

        Stopwatch sw = new Stopwatch();
        pq.add(start, input.estimatedDistanceToGoal(start, end));
        map.put(start, new Node(null, 0));
        while (pq.size() != 0) {
            Vertex current = pq.removeSmallest();
            closed.add(current);
            if (current.equals(end)) {
                time = sw.elapsedTime();
                result = SolverOutcome.SOLVED;
                return;
            }
            num++;

            List<WeightedEdge<Vertex>> neighbors = input.neighbors(current);
            for (WeightedEdge i : neighbors) {
                double g = map.get(current).g + i.weight();
                if (!map.containsKey(i.to())) {
                    map.put((Vertex) i.to(), new Node(current, g));
                    pq.add((Vertex) i.to(),
                            g + input.estimatedDistanceToGoal((Vertex) i.to(), end));
                } else if (!closed.contains(i.to())) {
                    if (g < map.get(i.to()).g) {
                        map.replace((Vertex) i.to(), new Node(current, g));
                        pq.changePriority((Vertex) i.to(), g);
                    }
                }
            }
            if (sw.elapsedTime() > timeout) {
                result = SolverOutcome.TIMEOUT;
                return;
            }
        }
        time = sw.elapsedTime();
        result = SolverOutcome.UNSOLVABLE;
    }

    public SolverOutcome outcome() {
        return result;
    }

    public List<Vertex> solution() {
        if (result == SolverOutcome.SOLVED) {
            LinkedList<Vertex> sol = new LinkedList<>();
            sol.addFirst(end);
            Node curr = map.get(end);
            while (curr.parent != null) {
                sol.addFirst((Vertex) curr.parent);
                curr = map.get(curr.parent);
            }
            return sol;
        } else {
            return List.of();
        }
    }

    public double solutionWeight() {
        if (result == SolverOutcome.SOLVED) {
            return map.get(end).g;
        } else {
            return 0;
        }
    }
    public int numStatesExplored() {
        return num;
    }

    public double explorationTime() {
        return time;
    }

    private class Node<Vertex> {
        private Vertex parent;
        private double g;

        Node(Vertex parent, double g) {
            this.parent = parent;
            this.g = g;
        }

    }
}
