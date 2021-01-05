/**
 * Class to perform the closest pair of points algorithm
 * This implementation uses a divide-and-conquer algorithm
 * Functionality includes
 * 1. sorting points based on X and Y coordinates
 * 2. finding the median and create partition
 * 3. finding the minimum distance using brute force
 *    and print the result for given points
 * 4. merge the partition and create strip
 *    using the smallest distance in both partition
 * 5. finding the smallest distance in the created strip
 *    The distance between two points is their Euclidean distance.
 * @author Sayali Kudale
 */

import java.util.Arrays;

public class ClosestPairAlgorithm {

    /**
     * This method sorts the array by x coordinates and
     * pass sorted array by x and y coordinate to another method
     * to find the minimum distance in closest pair using divide and conquer method
     * @param pointsSortedByX
     * @throws ArrayIndexOutOfBoundsException which is received from inner methods
     * pre: pointsSortedByX array should be initialised from the given input file which
     *      is unsorted initially
     * post: array get sorted by x and new array initialed with sorted y coordinates
     */

    public void findMinDistanceInPoints(Point[] pointsSortedByX) throws ArrayIndexOutOfBoundsException {

        Arrays.sort(pointsSortedByX, new PointSortByX());

        Point[] pointsSortedByY = getSortedArrayByY(pointsSortedByX);

        findMinDistanceDivideConquer(pointsSortedByX, pointsSortedByY, 0, pointsSortedByX.length - 1);

    }

    /**
     * This method sorts the array by y coordinates
     * @param pointsSortedByX
     * pre: pointsSortedByX array should be initialised
     * post: return array which get initialised with the length of
     *       pointsSortedByX values get sorted by y coordinates
     */
    private Point[] getSortedArrayByY(Point[] pointsSortedByX) {

        Point[] pointsSortedByY = new Point[pointsSortedByX.length];

        for (int i = 0; i < pointsSortedByX.length; i++)
            pointsSortedByY[i] = pointsSortedByX[i];

        Arrays.sort(pointsSortedByY, new PointSortByY());

        return pointsSortedByY;
    }


    /**
     * This method
     * 1. call brute force method if number of points are less than 4
     * 2. find the median of given points
     * 3. partition the arrays based on median
     * 4. call same method recursively with passing left and right partition separately
     * 5. find the minimum distance from the returned value of partition
     * 6. give call to the method which creates the strip
     * 7. call the method which returns the minimum distance in created strip
     * 8. returns the minimum distance between low and high value
     * @param pointsSortedByX
     * @param pointsSortedByY
     * @param low
     * @param high
     * @throws ArrayIndexOutOfBoundsException if partition is not performed according to the size of created arrays
     *pre: arrays should be sorted by x and y coordinates and low and high values should be computed
     *     pointsSortedByX and pointsSortedByY are the same sequence of points
     *     all the x coordinates are distinct
     *post: returns the minimum distance between points low and high of pointsSortedByX array
     *      prints the minimum distance in the given region
     */
    private double findMinDistanceDivideConquer(Point[] pointsSortedByX, Point[] pointsSortedByY, int low, int high)
            throws ArrayIndexOutOfBoundsException {

        int numberOfPoints = pointsSortedByX.length, stripSize, mid, middleIndex;
        double distanceLeft, distanceRight, delta;
        Point[] leftX, rightX, leftY, rightY, strip;
        Point midPoint;

        // in case given array contain less than 4 points then dont perform divide and conquer
        if (numberOfPoints <= 3) {
            return getClosestPairBruteForce(pointsSortedByX, low, high, numberOfPoints);
        }

        mid = (numberOfPoints + 1) / 2;
        middleIndex = low + mid;    // middle index is index of middle point in an array
        midPoint = pointsSortedByX[mid];
        leftX = new Point[mid];
        leftY = new Point[mid];
        rightX = new Point[numberOfPoints - mid];
        rightY = new Point[numberOfPoints - mid];
        strip = new Point[numberOfPoints];

        partitionX(pointsSortedByX, numberOfPoints, leftX, rightX);

        partitionY(pointsSortedByY, numberOfPoints, midPoint, leftY, rightY);

        distanceLeft = findMinDistanceDivideConquer(leftX, leftY, low, middleIndex - 1);

        distanceRight = findMinDistanceDivideConquer(rightX, rightY, middleIndex, high);

        delta = Math.min(distanceLeft, distanceRight);

        stripSize = createStrip(pointsSortedByY, numberOfPoints, midPoint, strip, delta);

        return getMinDistanceInStrip(strip, stripSize, delta, low, high);


    }

    /**
     * This method sorts the array by x coordinates and
     * pass sorted array by x and y coordinate to another method
     * to find the minimum distance in closest pair using divide and conquer method
     * @param pointsByY
     * @param numberOfPoints
     * @param median
     * @param strip
     * @param delta
     * pre: minimum distance delta is calculated and pointsByY sorted by y coordinates
     *      median is selected within pointsByY, strip size is initialised
     * post: strip size is returned
     */
    private int createStrip(Point[] pointsByY, int numberOfPoints, Point median, Point[] strip, double delta) {
        int stripSize = 0;

        for (int i = 0; i < numberOfPoints; i++)

            if (Math.abs(pointsByY[i].getX() - median.getX()) < delta) {
                strip[stripSize] = pointsByY[i];
                stripSize++;
            }

        return stripSize;
    }


    /**
     * This method partition the sorted y points into two arrays using the median value x
     * for sorted array of x coordinates equivalent points get sorted by y coordinates
     * @param pointsByY
     * @param numberOfPoints
     * @param median
     * @param leftY
     * @param rightY
     * pre: median is calculated and pointsByY sorted by Y coordinates
     *      leftY and rightY array is initialised
     * post:leftY and rightY arrays get filled with values from pointsByY
     *      if median x coordinate is not distinct then method throws ArrayIndexOutOfBoundsException
     */
    private void partitionY(Point[] pointsByY, int numberOfPoints, Point median, Point[] leftY, Point[] rightY)
            throws ArrayIndexOutOfBoundsException {

        int leftIndex = 0, rightIndex = 0;

        for (int i = 0; i < numberOfPoints; i++) {
            if (pointsByY[i].getX() < median.getX() && leftIndex < leftY.length)
                leftY[leftIndex++] = pointsByY[i];
            else
                rightY[rightIndex++] = pointsByY[i];
        }
    }


    /**
     * This method partition the sorted x points into two arrays
     * @param pointsByX
     * @param numberOfPoints
     * @param left
     * @param right
     * pre:  pointsByX sorted by x coordinates
     *       left and right array is initialised
     * post: left and right arrays get filled with values from pointsByX
     */
    private void partitionX(Point[] pointsByX, int numberOfPoints, Point[] left, Point[] right) {

        for (int i = 0; i < numberOfPoints; i++) {
            if (i < left.length)
                left[i] = pointsByX[i];
            else
                right[i - left.length] = pointsByX[i];
        }
    }


    /**
     * This method finds the minimum distance using the brute force algorithm
     * Assumptions is maximum 3 points will be given input to this method so that
     * overall complexity will not be greater than nlogn
     * @param low
     * @param P
     * @param high
     * @param numberOfPoints
     * pre: points P[] array is initialised
     * post: prints and returns the minimum distance in the given points
     */
    private double getClosestPairBruteForce(Point P[], int low, int high, int numberOfPoints) {
        double min = Double.POSITIVE_INFINITY;

        for (int i = 0; i < numberOfPoints; ++i)
            for (int j = i + 1; j < numberOfPoints; ++j) {
                double distance = getMinimumDistance(P[i], P[j]);
                if (distance < min) {
                    min = distance;
                }
            }

        System.out.println("D[" + low + "," + high + "]: " + String.format("%.4f", min));

        return min;
    }

    /**
     * This method finds Euclidean distance between between points
     * @param p1
     * @param p2
     * pre: none
     * post: returns Euclidean distance between points using mathematical formula
     */
    private double getMinimumDistance(Point p1, Point p2) {

        double distance = Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) +
                                    (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));

        return distance;
    }


    /**
     * This method finds the minimum distance in the strip using the brute force algorithm
     * In worst case this method iterates and finds minimum distance for maximum 7 points
     * @param strip
     * @param size
     * @param delta
     * @param low
     * @param high
     * pre: points strip[] array is initialised
     * post: prints and returns the lowest distance in the given strip points
     */
    private double getMinDistanceInStrip(Point strip[], int size, double delta, int low, int high) {
        double min = delta;

        for (int i = 0; i < size; ++i)
            for (int j = i + 1; j < size && (strip[j].getY() - strip[i].getY()) < min; ++j)

                if (getMinimumDistance(strip[i], strip[j]) < min) {
                    min = getMinimumDistance(strip[i], strip[j]);
                }

        System.out.println("D[" + low + "," + high + "]: " + String.format("%.4f", min));

        return min;
    }

}
