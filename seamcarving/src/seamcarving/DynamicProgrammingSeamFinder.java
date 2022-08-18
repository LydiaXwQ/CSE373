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
        public double energy;

        public Node(int preRow, int preCol, double energy) {
            this.preRow = preRow;
            this.preCol = preCol;
            this.energy = energy;
        }
        public Node(double energy) {
            this.energy = energy;
        }

    }


    @Override
    public List<Integer> findHorizontalSeam(double[][] energies) {
        //
        // for(int i = 0; i < energies.length; i++) {
        //     for(int j  = 0; j < energies[0].length; j++) {
        //         System.out.print(energies[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        // int numberOfCol = energies[0].length;
        // int numberOfRow = energies.length;
        // Node[][] table = new Node[numberOfRow][numberOfCol];
        // Set<Double> processed = new HashSet<>();
        //
        // for(int i = 0; i < numberOfRow; i++) {
        //     table[i][0] = new Node(i, 0, energies[i][0]);
        // }
        // for(int col = 0; col < numberOfCol - 1; col++){
        //     for(int row = 0; row < numberOfRow; row++){
        //         double tempEnergy = table[row][col].energy;
        //         if (row == 0) {
        //             double rightEnergy = energies[row][col + 1];
        //             if (!processed.contains(rightEnergy) || tempEnergy + rightEnergy < table[row][col + 1].energy) {
        //                 table[row][col + 1] = new Node(row, col, tempEnergy + rightEnergy);
        //                 processed.add(rightEnergy);
        //             }
        //
        //             double bottomRightEnergy = energies[row + 1][col + 1];
        //             if(!processed.contains(bottomRightEnergy) || tempEnergy + bottomRightEnergy < table[row + 1][col + 1].energy) {
        //                 table[row + 1][col + 1] = new Node(row, col, tempEnergy + bottomRightEnergy);
        //                 processed.add(bottomRightEnergy);
        //             }
        //
        //         } else if (row == numberOfRow - 1) {
        //             double rightEnergy = energies[row][col + 1];
        //             if (!processed.contains(rightEnergy) || tempEnergy + rightEnergy < table[row][col + 1].energy) {
        //                 table[row][col + 1] = new Node(row, col, tempEnergy + rightEnergy);
        //                 processed.add(rightEnergy);
        //             }
        //
        //             double topRightEnergy = energies[row - 1][col + 1];
        //             if(!processed.contains(topRightEnergy) || tempEnergy + topRightEnergy < table[row - 1][col + 1].energy) {
        //                 table[row - 1][col + 1] = new Node(row, col, tempEnergy + topRightEnergy);
        //                 processed.add(topRightEnergy);
        //             }
        //         } else {
        //             double rightEnergy = energies[row][col + 1];
        //             if (!processed.contains(rightEnergy) || tempEnergy + rightEnergy < table[row][col + 1].energy) {
        //                 table[row][col + 1] = new Node(row, col, tempEnergy + rightEnergy);
        //                 processed.add(rightEnergy);
        //             }
        //
        //             double topRightEnergy = energies[row - 1][col + 1];
        //             if(!processed.contains(topRightEnergy) || tempEnergy + topRightEnergy < table[row - 1][col + 1].energy) {
        //                 table[row - 1][col + 1] = new Node(row, col, tempEnergy + topRightEnergy);
        //                 processed.add(topRightEnergy);
        //             }
        //
        //             double bottomRightEnergy = energies[row + 1][col + 1];
        //             if(!processed.contains(bottomRightEnergy) || tempEnergy + bottomRightEnergy < table[row + 1][col + 1].energy) {
        //                 table[row + 1][col + 1] = new Node(row, col, tempEnergy + bottomRightEnergy);
        //                 processed.add(bottomRightEnergy);
        //             }
        //         }
        //     }
        // }
        // System.out.println();
        // for(int i = 0; i < energies.length; i++) {
        //     for(int j  = 0; j < energies[0].length; j++) {
        //         System.out.print(table[i][j].energy + " ");
        //     }
        //     System.out.println();
        // }
        //
        // List<Integer> result = new ArrayList<>(numberOfCol);
        // Node min = table[0][numberOfCol - 1];
        // result.add(0, 0);
        // for(int i = 1; i < numberOfRow; i++) {
        //     if(min.energy > table[i][numberOfCol - 1].energy) {
        //         min =  table[i][numberOfCol - 1];l
        //         result.set(0, i);
        //     }
        // }
        //
        //
        // for(int i = 1; i < numberOfCol; i++) {
        //     result.add(min.preRow);
        //     min = table[min.preRow][min.preCol];
        // }
        // return result;
        return null;
    }

    @SuppressWarnings({"checkstyle:WhitespaceAfter", "checkstyle:EmptyBlock"})
    @Override
    public List<Integer> findVerticalSeam(double[][] energies) {

        // for(int i = 0; i < energies.length; i++) {
        //     for(int j  = 0; j < energies[0].length; j++) {
        //         System.out.print(energies[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        int numberOfCol = energies[0].length;
        int numberOfRow = energies.length;
        Node[][] table = new Node[numberOfRow][numberOfCol];
        Set<Double> processed = new HashSet<>();

        for(int i = 0; i < numberOfRow; i++) {
            table[i][0] = new Node(i, 0, energies[i][0]);
        }
        for(int col = 0; col < numberOfCol - 1; col++){
            for(int row = 0; row < numberOfRow; row++){
                double tempEnergy = table[row][col].energy;
                if (row == 0) {
                    double rightEnergy = energies[row][col + 1];
                    if (!processed.contains(rightEnergy) || tempEnergy + rightEnergy < table[row][col + 1].energy) {
                        table[row][col + 1] = new Node(row, col, tempEnergy + rightEnergy);
                        processed.add(rightEnergy);
                    }

                    double bottomRightEnergy = energies[row + 1][col + 1];
                    if(!processed.contains(bottomRightEnergy) || tempEnergy + bottomRightEnergy < table[row + 1][col + 1].energy) {
                        table[row + 1][col + 1] = new Node(row, col, tempEnergy + bottomRightEnergy);
                        processed.add(bottomRightEnergy);
                    }

                } else if (row == numberOfRow - 1) {
                    double rightEnergy = energies[row][col + 1];
                    if (!processed.contains(rightEnergy) || tempEnergy + rightEnergy < table[row][col + 1].energy) {
                        table[row][col + 1] = new Node(row, col, tempEnergy + rightEnergy);
                        processed.add(rightEnergy);
                    }

                    double topRightEnergy = energies[row - 1][col + 1];
                    if(!processed.contains(topRightEnergy) || tempEnergy + topRightEnergy < table[row - 1][col + 1].energy) {
                        table[row - 1][col + 1] = new Node(row, col, tempEnergy + topRightEnergy);
                        processed.add(topRightEnergy);
                    }
                } else {
                    double rightEnergy = energies[row][col + 1];
                    if (!processed.contains(rightEnergy) || tempEnergy + rightEnergy < table[row][col + 1].energy) {
                        table[row][col + 1] = new Node(row, col, tempEnergy + rightEnergy);
                        processed.add(rightEnergy);
                    }

                    double topRightEnergy = energies[row - 1][col + 1];
                    if(!processed.contains(topRightEnergy) || tempEnergy + topRightEnergy < table[row - 1][col + 1].energy) {
                        table[row - 1][col + 1] = new Node(row, col, tempEnergy + topRightEnergy);
                        processed.add(topRightEnergy);
                    }

                    double bottomRightEnergy = energies[row + 1][col + 1];
                    if(!processed.contains(bottomRightEnergy) || tempEnergy + bottomRightEnergy < table[row + 1][col + 1].energy) {
                        table[row + 1][col + 1] = new Node(row, col, tempEnergy + bottomRightEnergy);
                        processed.add(bottomRightEnergy);
                    }
                }
            }
        }

        // System.out.println();
        // for(int i = 0; i < energies.length; i++) {
        //     for(int j  = 0; j < energies[0].length; j++) {
        //         System.out.print(table[i][j].energy + " ");
        //     }
        //     System.out.println();
        // }

        List<Integer> result = new ArrayList<>(numberOfCol);
        Node min = table[0][numberOfCol - 1];
        result.add(0, 0);
        for(int i = 1; i < numberOfRow; i++) {
            if(min.energy > table[i][numberOfCol - 1].energy) {
                min =  table[i][numberOfCol - 1];
                result.set(0, i);
            }
        }

        for(int i = 1; i < numberOfCol; i++) {
            result.add(min.preRow);
            min = table[min.preRow][min.preCol];
        }
        Collections.reverse(result);
        return result;
    }


}
