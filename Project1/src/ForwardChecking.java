import java.awt.Color;

public class ForwardChecking implements ConstraintSolverStrategy {
    private final static Color colorArray[] = {Color.blue, Color.green, Color.red, Color.yellow};
    private int color[];

    @Override
    public Graph solve(Graph g, int colorNum) {
        color = new int[g.getGraphSize()];
        Node nodes[] = g.getNodelist();
        for (int i = 0; i < color.length; i++) {
            color[i] = 0;
        }
        if (fowardChecking(g, color, colorNum, 0)) {
            for (int i = 0; i < color.length; i++) {
                nodes[i].setC(colorArray[color[i]]);
            }
        } else {
            System.out.println("No solution");
        }

        return null;
    }

    private boolean fowardChecking(Graph g, int color[], int colorNum, int vertex) {
        if (vertex == g.getGraphSize() - 1) {
            return true;
        }

        for (int i = 0; i < colorNum; i++) {
            if (checkColor(g.getNeighbors(), color, i, vertex)) {
                color[vertex] = i;
                if(checkColor(g.getNeighbors(), color, i+1, vertex + 1)){
                    if (fowardChecking(g, color, colorNum, vertex + 1)) {
                        return true;
                    }
                }
                color[vertex] = 0;
            }
        }
        return false;
    }

    private boolean checkColor(int adjacencyMatrix[][], int color[], int colorNum, int index) {
        if (index == adjacencyMatrix.length-1) {
            return true;
        }
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
