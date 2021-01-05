
/**
 * This class is a custom comparator class
 * Functionality includes
 * sorting the given point objects by comparing only x coordinates
 * @author Sayali Kudale
 *
 */

import java.util.Comparator;

public class PointSortByX implements Comparator<Point> {

    /**
     * Compares two X coordinates values of Points numerically.
     * @param  p1 the first Point { int X} to compare
     * @param  p2 the second Point { int X} to compare
     * @return the value {0} if { p1.x == p2.x};
     *         a value less than {0} if {p1.x < p2.x}; and
     *         a value greater than {0} if {p1.x > p2.x}
     *
     */

    @Override
    public int compare(Point p1, Point p2) {
        return Double.compare(p1.getX(), p2.getX());
    }
}
