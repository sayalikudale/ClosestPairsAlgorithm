/**
 * Class to represent a Point
 * The Point is represented by X and Y coordinates
 * Functionality includes initialising the Point object
 * @author Sayali Kudale
 *
 */
public class Point {

    private double x;
    private double y;

    /**
     * constructor
     * pre: none
     * post: instance variables are initialized
     * */
    public Point(double x ,double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * accessor for X coordinate
     * pre: none
     * post: returns value of X coordinate  */
    public double getX() {
        return x;
    }

    /**
     * accessor for Y coordinate
     * pre: none
     * post: returns value of Y coordinate */
    public double getY() {
        return y;
    }

}
