import java.awt.*;

/**
 * {@code BacktrackingBase} is an abstract class that
 * will be extended by {@code SimpleBacktracking},
 * {@code FowardChecking}, and {@code ArcConsistency}.
 *
 */
public abstract class BacktrackingBase {
    public final static Color colorArray[] = new Color[]{Color.blue, Color.green, Color.red, Color.yellow};
    /**
     * Determines if a given vertex and color will have any
     * conflicting constraints.
     *
     * @param adjacencyMatrix {@code int[][]} 2d array of neighbors
     * @param color {@code int[][]} array of current colors for all vertexes
     * @param colorNum {@code int[][]} number representing the color to check
     * @param index {@code int[][]} index of the vertex to be checked
     *
     * @return a {@code boolean} true if the color can be added to that node
     * false if there is a conflicting constraint
     */
    public boolean checkColor(int adjacencyMatrix[][], int color[], int colorNum, int index) {
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
