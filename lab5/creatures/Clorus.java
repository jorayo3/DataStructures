package creatures;

import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    private int r, g, b;

    public Clorus(double e) {
        super("Clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    public void move() {
        energy -= 0.03;
        if (energy < 0) {
            energy = 0;
        }
    }

    public void stay() {
        energy -= 0.01;
        if (energy < 0) {
            energy = 0;
        }
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        energy *= 0.5;
        return new Clorus(energy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.addLast(key);
            } else if (neighbors.get(key).name().equals("plip")) {
                plipNeighbors.addLast(key);
            }
        }
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        // Rule 2
        // HINT: randomEntry(emptyNeighbors)
        if (plipNeighbors.size() > 0) {
            Direction dir = HugLifeUtils.randomEntry(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, dir);
        }
        // Rule 3
        Direction dir = HugLifeUtils.randomEntry(emptyNeighbors);
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, dir);
        }
        // Rule 4
        return new Action(Action.ActionType.MOVE, dir);
    }
}
