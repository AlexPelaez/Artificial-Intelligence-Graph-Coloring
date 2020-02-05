/**
 * Implementation to solve the graph coloring problem using simple backtracking
 */
public class SimpleBacktracking extends BacktrackingBase implements ConstraintSolverStrategy {
    private int color[];
    /**
     * Set up the simple backtracking algorithm. Set the color of nodes.
     *
     * @param g {@code Graph} representation of the graph
     * @param colorNum {@code int} number of colors being used for this search
     */
    @Override
    public int solve(Graph g, int colorNum) {
        count = 1;
        color = new int[g.getGraphSize()];
        for (int i = 0; i < color.length; i++) {
            color[i] = 0;
        }
        if (simpleBacktracking(g, color, colorNum, 0)) {
            return count;
        }

        return (-1)*count;
    }
    /**
     * Recursive backtracking function.
     *
     * @param g {@code Graph} representation of the graph
     * @param color {@code Graph} representation of the graph
     * @param colorNum {@code int} number of colors being used for this search
     * @param vertex {@code int} the current vertex
     *
     * @return a {@code boolean} that represents if a solution was found
     */
    private boolean simpleBacktracking(Graph g, int color[], int colorNum, int vertex) {
        count++;
        if (vertex == g.getGraphSize()-1) {
            return true;
        }

        for (int i = 0; i < colorNum; i++) {
            count++;
            if(checkColor(g.getNeighbors(), color, i, vertex)) {

                color[vertex] = i;
                if (simpleBacktracking(g, color, colorNum, vertex+1)) {
                    return true;
                }
                color[vertex] = 0;
            }
        }
        return false;
    }
}
