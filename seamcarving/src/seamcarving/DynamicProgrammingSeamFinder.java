package seamcarving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.lang.Math;
import java.util.Set;

/**
 * Dynamic programming implementation of the {@link SeamFinder} interface.
 *
 * @see SeamFinder
 * @see SeamCarver
 */
public class DynamicProgrammingSeamFinder implements SeamFinder {

    private static class Node{
        public int preRow;
        public int preCol;
        public double totalEnergy;
        public double energy;

        public Node(int preRow, int preCol, double totalEnergy) {
            this.preRow = preRow;
            this.preCol = preCol;
            this.totalEnergy = totalEnergy;
        }
        public Node(double totalEnergy) {
            this.totalEnergy = totalEnergy;
        }

    }

    // System.out.println("Given");
    // for(int i = 0; i < numberOfRow; i++) { //col
    //     for(int j  = 0; j < numberOfCol; j++) {// row
    //         System.out.print(energies[i][j] + " ");
    //     }
    //     System.out.println();
    // }
    // System.out.println("Expected");
    // for(int i = 0; i < numberOfCol; i++) { //col
    //     for(int j  = 0; j < numberOfRow; j++) {// row
    //         System.out.print(energies[j][i] + " ");
    //     }
    //     System.out.println();
    // }

    @Override
    public List<Integer> findHorizontalSeam(double[][] energies) {
        int numberOfCol = energies[0].length;
        int numberOfRow = energies.length;
        Node[][] table = new Node[numberOfCol][numberOfRow];
        Set<Double> processed = new HashSet<>();
        for(int i = 0; i < numberOfCol; i++) {
            table[i][0] = new Node(i, 0, energies[0][i]);
        }


        for(int row = 0; row < numberOfRow - 1; row++) {
            for(int col = 0; col < numberOfCol; col++) {
                if(col == 0) {
                    double bottomEnergy = energies[row + 1][col];
                    if(!processed.contains(bottomEnergy) || table[col][row].totalEnergy + bottomEnergy < table[col][row + 1].totalEnergy) {
                        table[col][row + 1] = new Node(col, row, table[col][row].totalEnergy + bottomEnergy);
                        processed.add(bottomEnergy);
                    }

                    double bottomRightEnergy = energies[row + 1][col + 1];
                    if(!processed.contains(bottomRightEnergy) || table[col][row].totalEnergy + bottomRightEnergy < table[col + 1][row + 1].totalEnergy) {
                        table[col + 1][row + 1] = new Node(col, row, table[col][row].totalEnergy + bottomRightEnergy);
                        processed.add(bottomRightEnergy);
                    }
                } else if (col == numberOfCol - 1) {
                    double leftBottomEnergy = energies[row + 1][col - 1];
                    if(!processed.contains(leftBottomEnergy) || table[col][row].totalEnergy + leftBottomEnergy < table[col - 1][row + 1].totalEnergy) {
                        table[col - 1][row + 1] = new Node(col, row, table[col][row].totalEnergy + leftBottomEnergy);
                        processed.add(leftBottomEnergy);
                    }

                    double bottomEnergy = energies[row + 1][col];
                    if(!processed.contains(bottomEnergy) || table[col][row].totalEnergy + bottomEnergy < table[col][row + 1].totalEnergy) {
                        table[col][row + 1] = new Node(col, row, table[col][row].totalEnergy + bottomEnergy);
                        processed.add(bottomEnergy);
                    }
                } else {

                    double leftBottomEnergy = energies[row + 1][col - 1];
                    if(!processed.contains(leftBottomEnergy) || table[col][row].totalEnergy + leftBottomEnergy < table[col - 1][row + 1].totalEnergy) {
                        table[col - 1][row + 1] = new Node(col, row, table[col][row].totalEnergy + leftBottomEnergy);
                        processed.add(leftBottomEnergy);
                    }

                    double bottomEnergy = energies[row + 1][col];
                    if(!processed.contains(bottomEnergy) || table[col][row].totalEnergy + bottomEnergy < table[col][row + 1].totalEnergy) {
                        table[col][row + 1] = new Node(col, row, table[col][row].totalEnergy + bottomEnergy);
                        processed.add(bottomEnergy);
                    }

                    double bottomRightEnergy = energies[row + 1][col + 1];
                    if(!processed.contains(bottomRightEnergy) || table[col][row].totalEnergy + bottomRightEnergy < table[col + 1][row + 1].totalEnergy) {
                        table[col + 1][row + 1] = new Node(col, row, table[col][row].totalEnergy + bottomRightEnergy);
                        processed.add(bottomRightEnergy);
                    }
                }

            }
        }

        List<Integer> result = new ArrayList<>(numberOfRow);
        Node min = table[0][numberOfRow - 1];
        result.add(0, 0);
        for(int i = 1; i < numberOfCol; i++) {
            if(min.totalEnergy > table[i][numberOfRow - 1].totalEnergy) {
                min =  table[i][numberOfRow - 1];
                result.set(0, i);
            }
        }

        for(int i = 1; i < numberOfRow; i++) {
            result.add(min.preRow);
            min = table[min.preRow][min.preCol];
        }
        Collections.reverse(result);
        return result;

    }

    @Override
    public List<Integer> findVerticalSeam(double[][] energies) {

        int numberOfCol = energies[0].length; //5
        int numberOfRow = energies.length; // 6
        Node[][] table = new Node[numberOfCol][numberOfRow];

        Set<Double> processed = new HashSet<>();
        for(int i = 0; i < numberOfRow; i++) {
            table[0][i] = new Node(0, i, energies[i][0]);
        }


         for(int col = 0; col < numberOfCol - 1; col++) {
             for(int row = 0; row < numberOfRow; row++) {
                if(row == 0) {
                    double rightEnergy = energies[row][col + 1];
                    if(!processed.contains(rightEnergy) || table[col][row].totalEnergy + rightEnergy < table[col + 1][row].totalEnergy) {
                        table[col + 1][row] = new Node(col, row, table[col][row].totalEnergy + rightEnergy);
                        processed.add(rightEnergy);
                    }

                    double bottomRightEnergy = energies[row + 1][col + 1];
                    if(!processed.contains(bottomRightEnergy) || table[col][row].totalEnergy + bottomRightEnergy < table[col + 1][row + 1].totalEnergy) {
                        table[col + 1][row + 1] = new Node(col, row, table[col][row].totalEnergy + bottomRightEnergy);
                        processed.add(bottomRightEnergy);
                    }
                } else if (row == numberOfRow - 1) {
                    double rightEnergy = energies[row][col + 1];
                    if(!processed.contains(rightEnergy) || table[col][row].totalEnergy + rightEnergy < table[col + 1][row].totalEnergy) {
                        table[col + 1][row] = new Node(col, row, table[col][row].totalEnergy + rightEnergy);
                        processed.add(rightEnergy);
                    }

                    double topRightEnergy = energies[row - 1][col + 1];
                    if(!processed.contains(topRightEnergy) || table[col][row].totalEnergy + topRightEnergy < table[col + 1][row - 1].totalEnergy) {
                        table[col + 1][row - 1] = new Node(col, row, table[col][row].totalEnergy + topRightEnergy);
                        processed.add(topRightEnergy);
                    }
                } else {
                    double topRightEnergy = energies[row - 1][col + 1];
                    if(!processed.contains(topRightEnergy) || table[col][row].totalEnergy + topRightEnergy < table[col + 1][row - 1].totalEnergy) {
                        table[col + 1][row - 1] = new Node(col, row, table[col][row].totalEnergy + topRightEnergy);
                        processed.add(topRightEnergy);
                    }

                    double rightEnergy = energies[row][col + 1];
                    if(!processed.contains(rightEnergy) || table[col][row].totalEnergy + rightEnergy < table[col + 1][row].totalEnergy) {
                        table[col + 1][row] = new Node(col, row, table[col][row].totalEnergy + rightEnergy);
                        processed.add(rightEnergy);
                    }

                    double bottomRightEnergy = energies[row + 1][col + 1];
                    if(!processed.contains(bottomRightEnergy) || table[col][row].totalEnergy + bottomRightEnergy < table[col + 1][row + 1].totalEnergy) {
                        table[col + 1][row + 1] = new Node(col, row, table[col][row].totalEnergy + bottomRightEnergy);
                        processed.add(bottomRightEnergy);
                    }
                }

            }
        }

        List<Integer> result = new ArrayList<>(numberOfCol);
        Node min = table[numberOfCol - 1][0];
        result.add(0, 0);
        for(int i = 1; i < numberOfCol; i++) {
            if(min.totalEnergy > table[numberOfCol - 1][i].totalEnergy) {
                min =  table[numberOfCol - 1][i];
                result.set(0, i);
            }
        }

        for(int i = numberOfCol - 2; i >= 0; i--) {
            result.add(min.preCol);
            min = table[min.preRow][min.preCol];

        }
        Collections.reverse(result);
        return result;
    }


}
