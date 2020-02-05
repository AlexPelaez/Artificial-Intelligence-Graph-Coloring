import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Implementation to solve the graph coloring problem using backtracking with arc consistency.
 */
public class ArcConsistency extends BacktrackingBase implements ConstraintSolverStrategy {
    private int color[];
    private Queue<Edge> q = new LinkedList<Edge>();
    private ArrayList<Integer>[] domain;
    public int count = 0;
    /**
     * Set up backtracking with arc consistency.
     *
     * @param g {@code Graph} representation of the graph.
     * @param colorNum {@code int} number of colors being used for this search.
     */
    @Override
    public int solve(Graph g, int colorNum) {
        color = new int[g.getGraphSize()];
        domain = (ArrayList<Integer>[]) new ArrayList[g.getGraphSize()];
        for (int i = 0; i < color.length; i++) {
            color[i] = -1;
        }
        for(int d = 0; d < domain.length; d++) {
            ArrayList<Integer> tempColors = new ArrayList<Integer>();
            for(int c =0; c < colorNum; c++) {
                tempColors.add(c);
            }
            domain[d] = tempColors;
        }
        if (checkArcBacktrack(g, color, colorNum, 0)) {
            return count;
        } else {
            System.out.println("No solution");
        }

        return count*(-1);

//        for(int i = 0; i < color.length; i++) {
//            System.out.println(color[i]);
//        }

    }
    /**
     * Recursive function similar to SimpleBacktracking.java with added check to
     * ensure that the graph is arc consistent.
     *
     * @param g {@code Graph} representation of the graph.
     * @param color {@code int[]} representation of the graph.
     * @param colorNum {@code int} the number of possible colors in the domain.
     * @param vertex {@code int} the current vertex.

     *
     * @return a {@code boolean} that returns true if a solution was found.
     */
    private boolean checkArcBacktrack(Graph g, int color[], int colorNum, int vertex) {
        count++;

        for (int i = 0; i < domain[vertex].size(); i++) {
            count++;
            if(checkColor(g.getNeighbors(), color, domain[vertex].get(i), vertex)) {
                color[vertex] = domain[vertex].get(i);
                if(vertex != g.getGraphSize()-1) {
                    arcConsistency(g);
                    if (checkArcBacktrack(g, color, colorNum, vertex+1)) {
                        return true;
                    }
                    color[vertex] = -1;

                }
            }
        }

        if (vertex == g.getGraphSize()-1) {
            return true;
        }
        return false;
    }
    /**
     * Add all edges to a queue. Then loop through until the queue
     * removing inconsistent values until it is empty.
     *
     * @param g {@code Graph} representation of the graph.
     */
    private void arcConsistency(Graph g) {
        for(int i = 0; i < g.getNeighbors().length; i++) {
            for(int j = 0; j < g.getNeighbors().length; j++) {
                if (g.getNeighbors()[i][j] == 1 && color[i]==-1&& color[j]==-1) {
                    if (i>j){
                        q.add(new Edge(i, j));
                    }
                }
            }
        }

        while (q.size() != 0) {
            int vertex1 = q.peek().getVertex1();
            int vertex2 = q.poll().getVertex2();
            if(removeInconsistentValues(g, color, vertex1, vertex2)) {
                for(int i = 0; i < g.getNeighbors().length; i++){
                    if (g.getNeighbors()[vertex1][i] == 1 && color[vertex1] == -1 && color[vertex2] == -1) {
                        q.add(new Edge(i, vertex1));
                    }
                }
            }

        }
    }
    /**
     * Remove values from the domain of nodes that will no longer be arc consistent.
     *
     * @param g {@code Graph} representation of the graph.
     * @param color {@code int[]} current representation of colors on graph.
     * @param n1 {@code int} index of node 1.
     * @param n2 {@code int} index of node 2.
     *
     * @return a {@code boolean} that represents if a value has been removed.
     */
    private boolean removeInconsistentValues(Graph g, int[] color, int n1, int n2) {
        boolean removed = false;
        boolean hasDomain = false;
        ArrayList<Integer> tempDomain;

        if (color[n1] != -1 || color[n2] != -1) {
           return false;
        }
        for(int i = domain[n1].size()-1; i > 0; i--) {
            color[n1] = domain[n1].get(i);
            tempDomain = generateDomain(g, n2, color);
            if (!tempDomain.isEmpty()) {
                break;
            } else if (!hasDomain) {
                domain[n1].remove(i);
                removed = true;
                hasDomain = false;
            }
        }
        color[n1] = -1;

        return removed;
    }
    /**
     * Generate the domain for a given vertex and color array.
     *
     * @param g {@code Graph} representation of the graph
     * @param vertex {@code int} the current vertex
     * @param color {@code int[]} representation of the graph
     *
     * @return a {@code ArrayList<Integer>} that represents the domain
     * of a given node.
     */
    private ArrayList<Integer> generateDomain(Graph g, int vertex, int[] color) {
        ArrayList<Integer> currentDomain = new ArrayList<Integer>();
        for (int i = 0; i < domain[vertex].size(); i++) {
            if (checkColor(g.getNeighbors(), color, i, vertex)) {
                currentDomain.add(i);
            }
        }
        return currentDomain;
    }
}