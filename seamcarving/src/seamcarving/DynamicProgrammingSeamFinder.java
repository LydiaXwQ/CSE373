package seamcarving;


import java.util.ArrayList;

import java.util.List;

/**
 * Dynamic programming implementation of the {@link SeamFinder} interface.
 *
 * @see SeamFinder
 * @see SeamCarver
 */
public class DynamicProgrammingSeamFinder implements SeamFinder {
    private int height;
    private int width;
    private double intermediate = 0;
    private double x;
    private double left;
    private double upleft;
    private double bottomleft;


    ArrayList<Integer> n = new ArrayList<>();

    //push change
    @Override
    public List<Integer> findHorizontalSeam(double[][] energies) {
        height = energies[0].length;
        width = energies.length;
        double[][] leastEnergy = new double[height][width];
        int[][] index = new int[height][width];


        for (int i = 0; i < height; i++) {
            leastEnergy[i][0] = energies[0][i];
            index[i][0] = i;
        }

        //if it is at the first row, we do x, y-1; x+1, y-1

        for (int i = 1; i < width; i++) {
            for (int j = 0; j < height; j++) {

                //at the top row
                if (j == 0) {
                    //x=1,0 left = 0, 0 downleft = 0,1
                    x = energies[i][j];
                    left = leastEnergy[j][i - 1];
                    bottomleft = leastEnergy[j + 1][i - 1];
                    //left small
                    if (x + left < x + bottomleft) {
                        leastEnergy[j][i] = x + left;
                        index[j][i] = j;
                        //bottom left small
                    } else {
                        leastEnergy[j][i] = x + bottomleft;
                        index[j][i] = j + 1;
                    }
                    //at the bottom row
                } else if (j == height - 1) {
                    //x=1,5 left = 0, 5 upleft = 0,4
                    x = energies[i][j];
                    left = leastEnergy[j][i - 1];
                    upleft = leastEnergy[j - 1][i - 1];
                    //left small
                    if (x + left < x + upleft) {
                        leastEnergy[j][i] = x + left;
                        index[j][i] = j;
                        //upleft small
                    } else {
                        leastEnergy[j][i] = x + upleft;
                        index[j][i] = j - 1;
                    }
                    //at the middle
                } else {
                    // x=1,2 left = 0, 2, upleft = 0,1 downleft = 0, 3
                    x = energies[i][j];
                    left = leastEnergy[j][i - 1];
                    upleft = leastEnergy[j - 1][i - 1];
                    bottomleft = leastEnergy[j + 1][i - 1];
                    double minVal = Math.min(left, upleft);
                    //left small
                    if (left == minVal) {
                        index[j][i] = j;
                        intermediate = left;
                        //upleft small
                    } else {
                        index[j][i] = j - 1;
                        intermediate = upleft;
                    }
                    //use the smaller value(left/upleft) to compare with bottomleft
                    double minimumVal = Math.min(intermediate, bottomleft);
                    // pops up a left/upleft/bottomleft small
                    leastEnergy[j][i] = x + minimumVal;
                    if (minimumVal == bottomleft) {
                        index[j][i] = j + 1;
                        //if left/upleft is smaller
                    } else if (minimumVal == left) {
                        index[j][i] = j;
                        //if upleft is smaller
                    } else {
                        index[j][i] = j - 1;
                    }
                }
            }
        }
        //if it is at the bottom row, we do x, y-1; x-1, y-1
        // Otherwise, we do x-1, y-1; x, y-1; x+1, y-1
        //find the minimum value in the last col:
        int minIndex = getMinIndex(leastEnergy, width, height);

        //put minIndex at the last col, and search for predecessor in Index matrix:
        //
        n.add(minIndex);
        //predecessor=(1,5) stored value: (1,4)
        int predecessor = index[minIndex][width - 1];

        for (int i = width - 2; i >= 0; i--) {
            //(5,1; 4,1; add 3,2)
            //predecessor = (3,2)
            n.add(predecessor);
            predecessor = index[predecessor][i];

        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = n.size() - 1; i >= 0; i--) {
            //(5,1; 4,1; add 3,2)
            //predecessor = (3,2)
            result.add(n.get(i));
        }

        return result;
    }


    @Override
    public List<Integer> findVerticalSeam(double[][] energies) {
        height = energies[0].length;
        width = energies.length;

        double[][] leastEnergy = new double[height][width];
        int[][] index = new int[height][width];


        for (int i = 0; i < height; i++) {
            //fill the top row
            leastEnergy[0][i] = energies[i][0];
            index[0][i] = i;
        }

        //if it is at the first row, we do x, y-1; x+1, y-1

        for (int i = 1; i < height; i++) {
            for (int j = 0; j < width; j++) {

                //at the top row
                if (j == 0) {
                    //x=1,0 left = 0, 0 downleft = 0,1
                    x = energies[j][i];
                    left = leastEnergy[i - 1][j];
                    bottomleft = leastEnergy[i - 1][j + 1];
                    //left small
                    if (x + left < x + bottomleft) {
                        leastEnergy[i][j] = x + left;
                        index[i][j] = j;
                        //bottom left small
                    } else {
                        leastEnergy[i][j] = x + bottomleft;
                        index[i][j] = j + 1;
                    }
                    //at the bottom row
                } else if (j == width - 1) {
                    //x=1,5 left = 0, 5 upleft = 0,4
                    x = energies[i][j];
                    left = leastEnergy[i - 1][j];
                    upleft = leastEnergy[i - 1][j - 1];
                    //left small
                    if (x + left < x + upleft) {
                        leastEnergy[i][j] = x + left;
                        index[i][j] = j;
                        //upleft small
                    } else {
                        leastEnergy[i][j] = x + upleft;
                        index[i][j] = j - 1;
                    }
                    //at the middle
                } else {
                    // x=1,2 left = 0, 2, upleft = 0,1 downleft = 0, 3
                    x = energies[i][j];
                    left = leastEnergy[i - 1][j];
                    upleft = leastEnergy[i - 1][j - 1];
                    bottomleft = leastEnergy[i - 1][j + 1];
                    double minVal = Math.min(left, upleft);
                    //left small
                    if (left == minVal) {
                        index[i][j] = j;
                        intermediate = left;
                        //upleft small
                    } else {
                        index[i][j] = j - 1;
                        intermediate = upleft;
                    }
                    //use the smaller value(left/upleft) to compare with bottomleft
                    double minimumVal = Math.min(intermediate, bottomleft);
                    // pops up a left/upleft/bottomleft small
                    leastEnergy[i][j] = x + minimumVal;
                    if (minimumVal == bottomleft) {
                        index[i][j] = j + 1;
                        //if left/upleft is smaller
                    } else if (minimumVal == left) {
                        index[i][j] = j;
                        //if upleft is smaller
                    } else {
                        index[i][j] = j - 1;
                    }
                }
            }
        }
        //if it is at the bottom row, we do x, y-1; x-1, y-1
        // Otherwise, we do x-1, y-1; x, y-1; x+1, y-1
        //find the minimum value in the last col:
        int minIndex = getMinIndex(leastEnergy, height, width);

        //put minIndex at the last col, and search for predecessor in Index matrix:
        //
        n.add(minIndex);
        //predecessor=(1,5) stored value: (1,4)
        int predecessor = index[minIndex][height - 1];

        for (int i = height - 2; i >= 0; i--) {
            //(5,1; 4,1; add 3,2)
            //predecessor = (3,2)
            n.add(predecessor);
            predecessor = index[i][predecessor];

        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = n.size() - 1; i >= 0; i--) {
            //(5,1; 4,1; add 3,2)
            //predecessor = (3,2)
            result.add(n.get(i));
        }

        return result;

    }

    // private static int getMinIndex(double[][] leastEnergy, int height, int width) {
    //     double minVal = leastEnergy[height-1][0];
    //     int minIndex = 0;
    //
    //     for (int i = 1; i < width; i++) {
    //         if (leastEnergy[height-1][i] < minVal) {
    //             minVal = leastEnergy[height-1][i];
    //             minIndex = i;
    //         }
    //     }
    //
    //     return minIndex;
    // }
    private static int getMinIndex(double[][] leastEnergy, int width, int height) {
        double minVal = leastEnergy[0][width - 1];
        int minIndex = 0;

        for (int i = 1; i < height; i++) {
            if (leastEnergy[i][width - 1] < minVal) {
                minVal = leastEnergy[i][width - 1];
                minIndex = i;
            }
        }

        return minIndex;
    }


}
