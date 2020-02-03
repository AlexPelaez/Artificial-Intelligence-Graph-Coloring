import java.awt.Color;
/**
 * Implementation to solve the graph coloring problem using simple backtracking
 */
public class SimpleBacktracking extends BacktrackingBase implements ConstraintSolverStrategy {

    private int color[];
    public int count = 0;
    /**
     * Set up the simple backtracking algorithm. Set the color of nodes.
     *
     * @param g {@code Graph} representation of the graph
     * @param colorNum {@code int} number of colors being used for this search
     */
    @Override
    public Graph solve(Graph g, int colorNum) {
        color = new int[g.getGraphSize()];
        for (int i = 0; i < color.length; i++) {
            color[i] = 0;
        }
        if (simpleBacktracking(g, color, colorNum, 0)) {
            for(int i = 0; i < color.length; i++) {
                g.getNodelist()[i].setC(colorArray[color[i]]);
            }
        } else {
            System.out.println("No solution");
        }
        for(int i = 0; i < color.length; i++) {
            System.out.println("Node "+i+": "+color[i]);
        }
        System.out.println("Simple Backtracking count:");
        System.out.println(count);
        return null;
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
