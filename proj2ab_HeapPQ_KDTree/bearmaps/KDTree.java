package bearmaps;

import java.util.List;

public class KDTree {
    private Node root;

    public KDTree(List<Point> points) {
        root = new Node(points.get(0), true);
        for (Point q : points) {
            Node n = root;
            while (true) {
                boolean compare;
                if (Double.compare(q.getX(), n.p.getX()) == 0
                        && Double.compare(q.getY(), n.p.getY()) == 0) {
                    break;
                } else if (n.lr) {
                    compare = Double.compare(q.getX(), n.getX()) < 0;
                } else {
                    compare = Double.compare(q.getY(), n.getY()) < 0;
                }
                if (compare) {
                    if (n.left == null) {
                        n.left = new Node(q, !n.lr);
                        break;
                    } else {
                        n = n.left;
                    }
                } else {
                    if (n.right == null) {
                        n.right = new Node(q, !n.lr);
                        break;
                    } else {
                        n = n.right;
                    }
                }
            }
        }
    }

    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        return nearestHelper(root, goal, root).getP();
    }

    private Node nearestHelper(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        } else if (n.calcDistance(goal) < best.calcDistance(goal)) {
            best = n;
        }
        double foo;
        if (n.lr) {
            foo = goal.getX() - n.getX();
        } else {
            foo = goal.getY() - n.getY();
        }
        Node good;
        Node bad;
        if (foo > 0) {
            good = n.right;
            bad = n.left;
        } else {
            good = n.left;
            bad = n.right;
        }
        best = nearestHelper(good, goal, best);

        if (n.lr) {
            if (best.calcDistance(goal) > (Math.abs(goal.getX() - n.getX()))) {
                best = nearestHelper(bad, goal, best);
            }
        } else {
            if (best.calcDistance(goal) > (Math.abs(goal.getY() - n.getY()))) {
                best = nearestHelper(bad, goal, best);
            }
        }
        return best;
    }

    private class Node {
        Point p;
        private boolean lr;
        private Node left;
        private Node right;

        Node(Point p, boolean lr) {
            this.p = p;
            this.lr = lr;
            left = null;
            right = null;
        }

        public Point getP() {
            return p;
        }

        public double getX() {
            return p.getX();
        }

        public double getY() {
            return p.getY();
        }

        private double calcDistance(Point q) {
            return Math.sqrt(Math.pow((p.getX() - q.getX()), 2)
                    + Math.pow((p.getY() - q.getY()), 2));
        }
    }
}
