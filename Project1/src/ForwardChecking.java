import java.awt.Color;
import java.util.ArrayList;

public class ForwardChecking implements ConstraintSolverStrategy {
    private final static Color colorArray[] = {Color.blue, Color.green, Color.red, Color.yellow};
    private int color[];
    private ArrayList<Integer>[] domain;
    public int count = 0;

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
        System.out.println("Forward checking count:");
        System.out.println(count);

        return null;
    }

    private boolean forwardChecking(Graph g, int color[], int colorNum, int v) {
        count++;
        int vertex = v;
        
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

    private ArrayList<Integer> generateDomain(Graph g, int vertex, int[] color) {
        ArrayList<Integer> currentDomain = new ArrayList<Integer>();
        for (int i = 0; i < colorArray.length; i++) {
            if (checkColor(g.getNeighbors(), color, i, vertex)) {
                currentDomain.add(i);
            }
        }
        return currentDomain;
    }

    private boolean checkColor(int adjacencyMatrix[][], int color[], int colorNum, int index) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[index][i] == 1) {
                if (colorNum == color[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
