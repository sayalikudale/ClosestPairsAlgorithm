/**
 * This class is a custom comparator class
 * Functionality includes
 * sorting the given point objects by comparing only Y coordinates
 * @author Sayali Kudale
 *
 */

import java.util.Comparator;

public class PointSortByY implements Comparator<Point> {

    /**
     * Compares two Y cordinates values of Points numerically.
     * @param  p1 the first Point { int Y} to compare
     * @param  p2 the second Point { int Y} to compare
     * @return the value {0} if { p1.Y == p2.Y};
     *         a value less than {0} if {p1.Y < p2.Y}; and
     *         a value greater than {0} if {p1.Y > p2.Y}
     *
     */
    @Override
    public int compare(Point p1, Point p2) {
        return Double.compare(p1.getY(), p2.getY());
    }
}
