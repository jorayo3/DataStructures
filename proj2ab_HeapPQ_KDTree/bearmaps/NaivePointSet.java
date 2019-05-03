package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    List<Point> list;

    public NaivePointSet(List<Point> points) {
        list = points;
    }

    public Point nearest(double x, double y) {
        if (list.isEmpty()) {
            return null;
        }
        Point p = null;
        double best = Double.MAX_VALUE;
        for (Point i : list) {
            double foo = calcDistance(x, y, i);
            if (Double.compare(foo, best) < 0) {
                best = foo;
                p = i;
            }
        }
        return p;
    }

    private double calcDistance(double x, double y, Point p) {
        return Math.sqrt(Math.pow((p.getX() - x), 2)
                + Math.pow((p.getY() - y), 2));
    }
}
