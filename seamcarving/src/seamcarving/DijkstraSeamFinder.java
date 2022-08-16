package seamcarving;

import graphs.Edge;
import graphs.Graph;
import graphs.shortestpaths.DijkstraShortestPathFinder;
import graphs.shortestpaths.ShortestPathFinder;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class DijkstraSeamFinder implements SeamFinder {
    // TODO: replace all 4 references to "Object" on the line below with whatever vertex type
    //  you choose for your graph
    private final ShortestPathFinder<Graph<Object, Edge<Object>>, Object, Edge<Object>> pathFinder;

    public DijkstraSeamFinder() {
        this.pathFinder = createPathFinder();
    }

    protected <G extends Graph<V, Edge<V>>, V> ShortestPathFinder<G, V, Edge<V>> createPathFinder() {
        /*
        We override this during grading to test your code using our correct implementation so that
        you don't lose extra points if your implementation is buggy.
        */
        return new DijkstraShortestPathFinder<>();
    }

    public static void main(String[] args){

    }

    @Override
    public List<Integer> findHorizontalSeam(double[][] energies) {
        // TODO: replace this with your code


        vertex<Integer> startingDummy = new vertex<>(-1);

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public List<Integer> findVerticalSeam(double[][] energies) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }


    private static class vertex<V> {

        V energy;
        int row;
        int col;
        public vertex(V energy, int col, int row){
            this.energy = energy;
            this.row = row;
            this.col = col;
        }
        public vertex(V energy){
            this.energy = energy;
        }

    }

    private class MyGraph implements Graph<vertex, Edge<vertex>> {
        int dimensionCol;
        int getDimensionRow;
        boolean isHorizontalSeam;


        @Override
        public Collection<Edge<vertex>> outgoingEdgesFrom(vertex vertex) {


            return null;
        }

        public MyGraph(int col, int row, boolean isHorSeam){
            this.dimensionCol = col;
            this.getDimensionRow = row;
            this.isHorizontalSeam = isHorSeam;
        }

    }
}
