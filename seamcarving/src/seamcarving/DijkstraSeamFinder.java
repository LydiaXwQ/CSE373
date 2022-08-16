package seamcarving;

import graphs.Edge;
import graphs.Graph;
import graphs.shortestpaths.DijkstraShortestPathFinder;
import graphs.shortestpaths.ShortestPathFinder;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public List<Integer> findVerticalSeam(double[][] energies) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }


    private static class Vertex<V> {
        V energy;
        int x;
        int y;

        //public vertex(V energy){
            //this.energy = energy;
        //}
        public Vertex(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    private static class HorizontalGraph implements Graph<Vertex, Edge<Vertex>> {
        //int dimensionCol;
        //int getDimensionRow;

        private final Set<Edge> graph;
        private final Double[][] energies;
        private final int numberOfCol;
        private final int numberOfRow;
        private final Vertex dummyStart;
        private final Vertex dummyEnd;
        public HorizontalGraph(Double[][] energies){
            // this.dimensionCol = col;
            // this.getDimensionRow = row;
            this.graph = new HashSet<>();
            this.energies = energies;
            numberOfCol = energies[0].length;
            numberOfRow = energies.length;
            dummyStart = new Vertex<>(-1, -1);
            dummyEnd = new Vertex<>(-2, -2);
        }

        @Override
        public Collection<Edge<Vertex>> outgoingEdgesFrom(Vertex vertex) {
            int x = vertex.x;
            int y = vertex.y;
            Set<Edge<Vertex>> neighbors  = new HashSet<>();

            if(vertex == dummyStart) {
                for (int i = 0; i < numberOfRow; i++) {
                    neighbors.add(new Edge<>(vertex, new Vertex(0, i), getEnergy(0, i)));
                }
                return neighbors;
            }

            if(vertex == dummyEnd) {
                return neighbors;
            }

            if(x != numberOfCol - 1) {
                if (y > 0) { // target vertex is not in the top row. add up right vertex
                    neighbors.add(new Edge<>(vertex, new Vertex<>(x + 1, y + 1), getEnergy(x + 1, y + 1)));
                }

                if(y < numberOfRow - 1) { // target vertex is not in the bottom row, so add bottom right vertex;
                    neighbors.add(new Edge<>(vertex, new Vertex<>(x + 1, y - 1), getEnergy(x + 1, y - 1)));
                }
                // always add due right vertex
                neighbors.add(new Edge<>(vertex, new Vertex<>(x + 1, y), getEnergy(x + 1, y)));
            } else {
                neighbors.add(new Edge(vertex, dummyEnd, 0)); // target vertex in the last column, add dummy
            }

            return neighbors;
        }

        public Double getEnergy(int x, int y) {
            return energies[x][y];
        }

        public void add(Edge edge) {
            graph.add(edge);
        }

        public void add(Vertex from, Vertex to) {
            graph.add(new Edge<>(from, to, getEnergy(to.x, to.y)));
        }


    }


}
