package seamcarving;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

/**
 * Dynamic programming implementation of the {@link SeamFinder} interface.
 *
 * @see SeamFinder
 * @see SeamCarver
 */
public class DynamicProgrammingSeamFinder implements SeamFinder {
    @Override
    public List<Integer> findHorizontalSeam(double[][] energies) {
        double[][] motherload = new double[energies.length][energies[0].length];
        int[][] index = new int[energies.length][energies[0].length];
        for (int i = 0; i < energies[0].length; i++) {
            motherload[i][0] = energies[i][0];
            index[i][0] = 0;
        }
        for (int i = 0; i < energies.length-1; i++) {
            for (int j = 1; j < energies[0].length; j++) {

                if(i==0){
                    if(energies[i][j-1] < energies[i+1][j-1]) {
                        index[i][j] = i;
                    } else {
                        index[i][j] = i+1;
                    }
                    motherload[i][j] = Math.min(energies[i][j-1], energies[i+1][j-1]) + energies[i][j];
                } else if (i == energies[0].length-1) {
                    if(energies[i][j-1] < energies[i-1][j-1]) {
                        index[i][j] = i;
                    } else {
                        index[i][j] = i-1;
                    }
                    motherload[i][j] = Math.min(energies[i][j-1], energies[i-1][j-1]) + energies[i][j];
                } else {
                    if(energies[i][j-1] < energies[i-1][j-1]){
                        index[i][j] = i;
                    } else {
                        index[i][j] = i-1;
                    }

                    if(energies[index[i][j]][j] > energies[i+1][j-1]){
                        index[i][j] = i+1;
                    }

                    double temp = Math.min(energies[i][j-1], energies[i-1][j-1]);
                    motherload[i][j] = Math.min(temp, energies[i+1][j-1]) + energies[i][j];
                }

            }
        }
        double curMin = motherload[1][energies[0].length-1];
        int x = 1;
        for (int i = 1; i < energies[0].length; i++) {
            if(motherload[i][energies[0].length-1] < curMin){
                curMin = motherload[i][energies[0].length-1];
                x = i;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(x);
        for(int i = energies[0].length-1; i>0; i-- ){
            list.add(index[x][i]);
            x = index[x][i];
        }

        return list;
    }

    @SuppressWarnings({"checkstyle:WhitespaceAfter", "checkstyle:EmptyBlock"})
    @Override
    public List<Integer> findVerticalSeam(double[][] energies) {
        // replace this with your code

        double[][] motherload = new double[energies.length][energies[0].length];
        int[][] index = new int[energies.length][energies[0].length];
        for (int i = 0; i < energies[0].length; i++) {
            motherload[i][0] = energies[i][0];
            index[i][0] = 0;
        }
        for (int i = 0; i < energies.length-1; i++) {
            for (int j = 1; j < energies[0].length; j++) {

                if(i==0){
                    if(energies[i][j-1] < energies[i+1][j-1]) {
                        index[i][j] = i;
                    } else {
                        index[i][j] = i+1;
                    }
                    motherload[i][j] = Math.min(energies[i][j-1], energies[i+1][j-1]) + energies[i][j];
                } else if (i == energies[0].length-1) {
                    if(energies[i][j-1] < energies[i-1][j-1]) {
                        index[i][j] = i;
                    } else {
                        index[i][j] = i-1;
                    }
                    motherload[i][j] = Math.min(energies[i][j-1], energies[i-1][j-1]) + energies[i][j];
                } else {
                    if(energies[i][j-1] < energies[i-1][j-1]){
                        index[i][j] = i;
                    } else {
                        index[i][j] = i-1;
                    }

                    if(energies[index[i][j]][j] > energies[i+1][j-1]){
                        index[i][j] = i+1;
                    }

                    double temp = Math.min(energies[i][j-1], energies[i-1][j-1]);
                    motherload[i][j] = Math.min(temp, energies[i+1][j-1]) + energies[i][j];
                }

            }
        }
        double curMin = motherload[1][energies[0].length-1];
        int x = 1;
        for (int i = 1; i < energies[0].length; i++) {
            if(motherload[i][energies[0].length-1] < curMin){
                curMin = motherload[i][energies[0].length-1];
                x = i;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(x);
        for(int i = energies[0].length-1; i>=0; i-- ){
            list.add(index[x][i]);
            x = index[x][i];
        }

       return list;
    }


}
