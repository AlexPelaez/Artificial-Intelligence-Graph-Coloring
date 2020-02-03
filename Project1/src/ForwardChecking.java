import java.awt.Color;
import java.util.ArrayList;

/**
 * Implementation to solve the graph coloring problem using backtracking with forward checking
 */
public class ForwardChecking extends BacktrackingBase implements ConstraintSolverStrategy {
    private final static Color colorArray[] = {Color.blue, Color.green, Color.red, Color.yellow};
    private int color[];
    private ArrayList<Integer>[] domain;
    public int count = 0;
    /**
     * Set up backtracking with forward checking.
     *
     * @param g {@code Graph} representation of the graph
     * @param colorNum {@code int} number of colors being used for this search
     */
    @Override
    public Graph solve(Graph g, int colorNum) {
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
        if (forwardChecking(g, color, colorNum, 0)) {
            for(int i = 0; i < color.length; i++) {
                g.getNodelist()[i].setC(colorArray[color[i]]);
            }
        } else {
            System.out.println("No solution");
        }
        for(int i = 0; i < color.length; i++) {
            System.out.println("Node "+i+": "+color[i]);
        }
        System.out.println("Forward checking count:");
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
     * @return a {@code boolean} that represents if the solution was found.
     */
    private boolean forwardChecking(Graph g, int color[], int colorNum, int vertex) {
        count++;

        for (int i = 0; i < domain[vertex].size(); i++) {
            if(checkColor(g.getNeighbors(), color, domain[vertex].get(i), vertex)) {
                color[vertex] = domain[vertex].get(i);
                if(vertex != g.getGraphSize()-1) {
                    domain[vertex+1] = generateDomain(g, vertex+1, color);
                    if (forwardChecking(g, color, colorNum, vertex+1)) {
                        return true;
                    }
                    else {
                        color[vertex] = -1;
                    }
                }
            }
        }

        if (vertex == g.getGraphSize()-1) {
            return true;
        }
        return false;
    }
    /**
     * Forward constraint checking.
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
