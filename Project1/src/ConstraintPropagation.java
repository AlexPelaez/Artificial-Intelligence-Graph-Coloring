import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Alex on 1/28/20.
 */
public class ConstraintPropagation implements ConstraintSolverStrategy {
    private Queue<Arc> q;
    private int colorNum;
    private Graph g;
    public ConstraintPropagation() {
    }

    @Override
    public Graph solve(Graph g, int colorNum) {
        this.colorNum = colorNum;
        this.g = g;
        q = new LinkedList<>();
        addArcs(g.getNeighbors());
        return g;
    }

    private boolean constraintPropogation(Graph g, int color[], int colorNum, int vertex){
        do {
            Arc temp = q.remove();


        }while(!q.isEmpty());


        return true;
    }

    private boolean revise(int adjacencyMatrix[][], int color[], Arc x){
        boolean revised = false;
        for(int i = 0; i < color.length; i++){
            if()

        }
    return true;
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

    private void addArcs(int adjacencyMatrix[][]){
        for(int i = 0; i < adjacencyMatrix.length; i++){
            for(int j = 0; j < adjacencyMatrix.length;j++){
                if(adjacencyMatrix[i][j] == 1){
                   q.add(new Arc(i, colorNum));
                }
            }
        }
    }
}
