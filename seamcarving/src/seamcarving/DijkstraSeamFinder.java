package seamcarving;

import edu.princeton.cs.algs4.In;
import graphs.Edge;
import graphs.Graph;
import graphs.shortestpaths.DijkstraShortestPathFinder;
import graphs.shortestpaths.ShortestPath;
import graphs.shortestpaths.ShortestPathFinder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DijkstraSeamFinder implements SeamFinder {
    // TODO: replace all 4 references to "Object" on the line below with whatever vertex type
    //  you choose for your graph
    private final ShortestPathFinder<Graph<vertex, Edge<vertex>>, vertex, Edge<vertex>> pathFinder;

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
        List<Integer> list = new ArrayList<>();

        MyGraph graph = new MyGraph(energies);
        DijkstraShortestPathFinder<MyGraph, vertex, Edge<vertex>> shortestPathFinder = new DijkstraShortestPathFinder<>();
        ShortestPath<vertex, Edge<vertex>> traversal = shortestPathFinder.findShortestPath(graph, graph.dummyFirst, graph.dummyLast);
        for(Edge<vertex> idk: traversal.edges()){
            if(idk.to() == graph.dummyLast){
                continue;
            }
            list.add(idk.to().col);
        }

        return list;
    }

    @Override
    public List<Integer> findVerticalSeam(double[][] energies) {
        // TODO: replace this with your code
        throw new UnsupportedOperationException("Not implemented yet.");
    }


    private static class vertex {

        double energy;
        int row;
        int col;
        ArrayList<Edge<vertex>> outgoingVertexes = new ArrayList<>();
        public vertex(double energy, int col, int row){
            this.energy = energy;
            this.row = row;
            this.col = col;
        }
        public vertex(double energy){
            this.energy = energy;
        }

    }

    private class MyGraph implements Graph<vertex, Edge<vertex>> {
        int dimensionCol;
        int dimensionRow;
        vertex dummyFirst = new vertex(-1.0);
        vertex dummyLast = new vertex(-2.0);
        //boolean isHorizontalSeam;

        @Override
        public Collection<Edge<vertex>> outgoingEdgesFrom(vertex vertex) {
            return vertex.outgoingVertexes;
        }

        public MyGraph(double[][] table){
            this.dimensionRow = table.length;
            this.dimensionCol = table[0].length;
            workDone(table);
        }

        private void workDone(double[][] table){

            //vertex<Double> current = dummyFirst;
            vertex[][] motherload = new vertex[dimensionRow][dimensionCol];
            for(int i = 0; i < dimensionRow; i++){
                for(int j = 0; j < dimensionCol; j++){
                    motherload[i][j] = new vertex(table[i][j], j, i);
                }
            }

            for(int i = 0; i < dimensionRow; i++){
                vertex addVertex= motherload[i][0];
                dummyFirst.outgoingVertexes.add(new Edge<>(dummyFirst, addVertex, addVertex.energy));

            }

            for(vertex[] outerLoop: motherload){
                //TODO: if statement for checking last column

                for(vertex element: outerLoop){

                    if(element.col == dimensionCol-1){
                        element.outgoingVertexes.add(new Edge<>(element, dummyLast, 0));
                        continue;
                    }

                    int tempX = element.row;
                    int tempY = element.col;
                    if(tempX == 0){
                        element.outgoingVertexes.add(new Edge<>(element, motherload[tempX][tempY],
                                                                motherload[tempX][tempY].energy ));
                        element.outgoingVertexes.add(new Edge<>(element, motherload[tempX+1][tempY],
                                                                motherload[tempX+1][tempY].energy ));
                    } else if (tempX == dimensionRow-1){
                        element.outgoingVertexes.add(new Edge<>(element, motherload[tempX-1][tempY],
                                                                motherload[tempX-1][tempY].energy ));
                        element.outgoingVertexes.add(new Edge<>(element, motherload[tempX][tempY],
                                                                motherload[tempX][tempY].energy ));
                    } else {
                        element.outgoingVertexes.add(new Edge<>(element, motherload[tempX-1][tempY],
                                                                motherload[tempX-1][tempY].energy ));
                        element.outgoingVertexes.add(new Edge<>(element, motherload[tempX][tempY],
                                                                motherload[tempX][tempY].energy ));
                        element.outgoingVertexes.add(new Edge<>(element, motherload[tempX+1][tempY],
                                                                motherload[tempX+1][tempY].energy ));
                    }
                }
            }

            //-----------------------------------------------------------------

            //current = motherload[0][0];
            // vertex<Double>[] currentTraversal = new vertex[dimensionRow];
            // for(int i = 0; i < dimensionRow; i++){
            //     vertex<Double> temp = new vertex<>(table[i][0], 0, i);
            //     current.outgoingVertexes.add(new Edge<vertex>(current, temp, temp.energy));
            //     currentTraversal[0] = temp;
            // }
            //
            // current = currentTraversal[0];
            // while (current != dummyLast){
            //     vertex<Double>[] toVertexes = new vertex[dimensionRow];
            //     if (current.col != dimensionCol-2){
            //         toVertexes = new vertex[dimensionRow];
            //         for(int i = 0; i < dimensionRow; i++){
            //             toVertexes[i] = new vertex<>(table[i][current.col+1]);
            //         }
            //     } else if (current.col == dimensionCol-1) {
            //
            //     }
            //     int cur = 0;
            //     for(vertex<Double> curVertex: currentTraversal){
            //         if(curVertex.row == 0 || curVertex.row == dimensionRow-1){
            //             curVertex.outgoingVertexes.add(new Edge<>(curVertex, toVertexes[cur],
            //                                             toVertexes[cur].energy));
            //             curVertex.outgoingVertexes.add(new Edge<>(curVertex, toVertexes[cur+1],
            //                 toVertexes[cur+1].energy));
            //         } else {
            //             curVertex.outgoingVertexes.add(new Edge<>(curVertex, toVertexes[cur],
            //                 toVertexes[cur].energy));
            //             curVertex.outgoingVertexes.add(new Edge<>(curVertex, toVertexes[cur+1],
            //                 toVertexes[cur+1].energy));
            //             curVertex.outgoingVertexes.add(new Edge<>(curVertex, toVertexes[cur+2],
            //                 toVertexes[cur+2].energy));
            //             cur++;
            //
            //         }
            //     }
            // }

        }

    }
}
