/**
 * This Class is the starting point of program
 * Functionality includes:
 * 1. read inputs from given file
 * 2. validate inputs
 * 3. exception handling
 * 4. create data from given inputs and pass to the method to compute result
 * @author Sayali Kudale
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ClosestPairOfPoints {

    /**
     * This main method
     * 1. prepare the Coordinate array from given input file
     * 2. print the error message if any
     * 3. Pass the array to method to find minimum distance between points
     * @param args
     * pre: none
     * post: print error to the console if input is not valid otherwise pass input to the ClosestPair class
     */

    public static void main(String[] args) {
        final String inputFileName = "program2trivialdata.txt";
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        int numberOfRecords = 0;
        try {
            fileReader = new FileReader(inputFileName);
            bufferedReader = new BufferedReader(fileReader);
            numberOfRecords = Integer.parseInt(bufferedReader.readLine().trim());

            //special case for 0 input
            if (numberOfRecords == 0)
                throw new IllegalArgumentException("File is empty");

            //special case for 1 input
            if (numberOfRecords == 1)
                throw new IllegalArgumentException("Number of coordinates should be " +
                        "at least two to find distance");

            Point[] points = readInputs(bufferedReader, numberOfRecords);
            ClosestPairAlgorithm closestPair = new ClosestPairAlgorithm();
            closestPair.findMinDistanceInPoints(points);

        } catch (FileNotFoundException fnfe) {
            System.err.println(inputFileName + " file not found !!");
        } catch (IOException ioe) {
            System.err.println("Error in Input Data!! \n" + ioe.getMessage());
        } catch (NumberFormatException nfe) {
            System.err.println("Coordinates are not in number format " + nfe.getMessage());
        } catch (NullPointerException npe) {
            System.err.println("Input data is Invalid: " + npe.getMessage());
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            System.err.println("Input data is Invalid: " + aiobe.getMessage());
        }catch (IllegalArgumentException iae) {
        System.err.println("Input data is Invalid: " + iae.getMessage());
        } finally {
            try {
                if (bufferedReader != null && fileReader != null) {
                    bufferedReader.close();
                    fileReader.close();
                }
            } catch (IOException ioe) {
                System.err.println("Error in InputStream close(): " + ioe);
            }
        }
    }

    /**
     * This method
     * 1. reads Coordinates from the given file
     * 2. if input contains extra spaces then ignores it
     * 3. Initialized the Point object using the coordinates
     * 4. creates the array of point object
     * @param bufferedReader
     * @param numberOfRecords
     * @throws IOException IOException, NumberFormatException if input is incorrect
     * pre: bufferedReader should be initialised
     * post: returns array of Point object
     */
    private static Point[] readInputs(BufferedReader bufferedReader, int numberOfRecords) throws IOException,
            NumberFormatException {

        Point[] points = new Point[numberOfRecords];

        for (int i = 0; i < numberOfRecords; i++) {
            String[] arr = bufferedReader.readLine().trim().split("\\s+");
            double x = Double.parseDouble(arr[0]);
            double y = Double.parseDouble(arr[1]);
            points[i] = new Point(x, y);
        }

        return points;
    }
}

