import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ArcConsistency implements ConstraintSolverStrategy {
    private final static Color colorArray[] = {Color.blue, Color.green, Color.red, Color.yellow};
    private int color[];
    private Queue<Edge> q = new LinkedList<Edge>();
    private ArrayList<Integer>[] domain;
    public int count = 0;

    @Override
    public Graph solve(Graph g, int colorNum) {
        color = new int[g.getGraphSize()];

        for(int i = 0; i < g.getNeighbors().length; i++) {
            for(int j = 0; j < g.getNeighbors().length; j++) {
                if (g.getNeighbors()[i][j] == 1) {
                    if (i>j){
                        q.add(new Edge(i, j));
                    }
                }
            }
        }
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
        while (q.size() != 0) {
            removeInconsistentValues(g, color, q.peek().getVertex1(), q.poll().getVertex2());
        }
        if (checkArc(g, color, colorNum, 0)) {
            for(int i = 0; i < color.length; i++) {
                g.getNodelist()[i].setC(colorArray[color[i]]);
            }
        } else {
            System.out.println("No solution");
        }
        for(int i = 0; i < color.length; i++) {
            System.out.println(color[i]);
        }
        System.out.println("Edge Consistency count:");
        System.out.println(count);

        return null;
    }

    private boolean checkArc(Graph g, int color[], int colorNum, int v) {
        count++;

        int vertex = v; //remember to remove this - just for debug

        for (int i = 0; i < domain[vertex].size(); i++) {
            if(checkColor(g.getNeighbors(), color, domain[vertex].get(i), vertex)) {
                color[vertex] = domain[vertex].get(i);
                if(vertex != g.getGraphSize()-1) {
                    if (checkArc(g, color, colorNum, vertex+1)) {
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

    private boolean removeInconsistentValues(Graph g, int[] color, int n1, int n2) {
        boolean hasDomain = true;
        ArrayList<Integer> tempDomain;
        domain[n1] = generateDomain(g, n1, color);
        if (color[n1] != -1 || color[n2] != -1) {
           return true;
        }
        for(int i = 0; i < domain[n1].size(); i++) {
            for(int j = 0; j < domain[n2].size(); j++) {
                color[n1] = domain[n1].get(i);
                tempDomain = generateDomain(g, n2, color);
                if (tempDomain.size() == 0) {
                    System.out.println("hereee");
                    domain[n1].remove(i);
                    hasDomain = false;
                }
                color[n1] = -1;
            }
        }
        if (!hasDomain) {
            for (int i = 0; i < g.getNeighbors().length; i++) {
                if (g.getNeighbors()[n1][i] == 1 && n1 > i) {
                    q.add(new Edge(n1,i));
                }
            }
        }
        return true;
    }

    private ArrayList<Integer> generateDomain(Graph g, int vertex, int[] color) {
        ArrayList<Integer> currentDomain = new ArrayList<Integer>();
        for (int i = 0; i < domain[vertex].size(); i++) {
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
