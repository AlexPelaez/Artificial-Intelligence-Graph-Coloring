import java.awt.Color;

public class SimpleBacktracking implements ConstraintSolverStrategy {
    private final static Color colorArray[] = {Color.blue, Color.green, Color.red, Color.yellow};
    private int color[];
    public int count = 0;

    @Override
    public Graph solve(Graph g, int colorNum) {
        color = new int[g.getGraphSize()];
        for (int i = 0; i < color.length; i++) {
            color[i] = 0;
        }
        if (simpleBacktracking(g, color, colorNum, 0)) {
//            for(int i = 0; i < color.length; i++) {
//                g.getNodelist()[i].setC(colorArray[color[i]]);
//            }
        } else {
            System.out.println("No solution");
        }
        System.out.println("Simple Backtracking count:");
        System.out.println(count);
        return null;
    }

    private boolean simpleBacktracking(Graph g, int color[], int colorNum, int vertex) {
        if (vertex == g.getGraphSize()-1) {
            return true;
        }
        count++;

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
