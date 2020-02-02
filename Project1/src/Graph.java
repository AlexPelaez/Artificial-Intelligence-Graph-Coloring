import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * The {@code Graph} is used to generate and store randomized
 * planar graphs of any size. First an array of {@code Node} are
 * instantiated with random (x,y) coordinates within the unit square.
 * The adjacency matrix is then generated by choosing nodes at
 * random, finding their closest neighbor, and determining if the new
 * edge would intersect with any other pre-existing edges.
 * @see     java.math.BigDecimal
 * @see     Node
 * @author  Alexander Pelaez
 * @author  Ethan Miller
 */
public class Graph {
    private int n;
    private int neighbors[][];
    private Node nodelist[];
    /**
     * Constructor for the {@code Graph}.
     *
     * @param n {@code int} lets {@code Graph} know the number of nodes
     * that it is required to generate.
     */
    public Graph(int n) {
        this.n = n;
//        populateNodelist();
//        populateAdjacencyMatrix();
        neighbors = new int[10][10];
        nodelist = new Node[] {(new Node(0.2407f, 0.9712f, null)), (new Node(0.5686f, 0.5975f, null)), (new Node(0.7973f, 0.8415f, null)), (new Node(0.2935f, 0.1026f, null)), (new Node(0.6951f, 0.8155f, null)), (new Node(0.7172f, 0.6195f, null)), (new Node(0.7849f, 0.0543f, null)), (new Node(0.4253f, 0.3342f, null)), (new Node(0.1019f, 0.6077f, null)), (new Node(0.6277f, 0.8892f, null))};
        neighbors[0] = new int[] {0,  1,  0,  0,  0,  1,  0,  0,  1,  1 };
        neighbors[1] = new int[] {1,  0,  0,  0,  0,  1,  1,  1,  1,  0 };
        neighbors[2] = new int[] {0,  0,  0,  0,  1,  1,  1,  0,  0,  1 };
        neighbors[3] = new int[] {0,  0,  0,  0,  0,  0,  1,  1,  1,  0 };
        neighbors[4] = new int[] {0,  0,  1,  0,  0,  1,  0,  0,  0,  1 };
        neighbors[5] = new int[] {1,  1,  1,  0,  1,  0,  1,  0,  0,  1 };
        neighbors[6] = new int[] {0,  1,  1,  1,  0,  1,  0,  1,  0,  0 };
        neighbors[7] = new int[] {0,  1,  0,  1,  0,  0,  1,  0,  1,  0 };
        neighbors[8] = new int[] {1,  1,  0,  1,  0,  0,  0,  1,  0,  0 };
        neighbors[9] = new int[] {1,  0,  1,  0,  1,  1,  0,  0,  0,  0 };
    }
    /**
     * Translates a character array representation of a
     * {@code BigDecimal} into a {@code BigDecimal}, accepting the
     * same sequence of character
     */
    private void populateNodelist() {
        float x;
        float y;

        nodelist = new Node[n];
        for(int i= 0; i < nodelist.length; i++) {
            do {
                x = formatFloat((float) Math.random());
                y = formatFloat((float) Math.random());
            } while (x == 0 || y == 0);
            nodelist[i] = new Node(x,y,null);
        }
    }
    /**
     * Generates the adjacency matrix and stores it in a {@code int} array.
     */
    private void populateAdjacencyMatrix() {
        int randNode;
        int closestNode;

        neighbors = new int[n][n];
        for(int i= 0; i < 10*nodelist.length; i++) {
            randNode = (int)(Math.random()*n);
            closestNode = closestNeighbor(randNode);
            if (closestNode != -1) {
//                printAdjacencyMatrix();
                neighbors[randNode][closestNode] = 1;
                neighbors[closestNode][randNode] = 1;

            }
        }
    }
    /**
     * Finds the closest neighboring {@code Node} to the
     * {@code Node} that is passed in args.
     *
     * @param node {@code int} array that is the source of characters.
     *
     * @return a {@code int} that represents the index of the closest
     * neighboring node in the nodelist
     */
    private int closestNeighbor(int node) {
        float closestDistance = 100;
        float currentDistance;
        int closestIndex = -1;

        for(int i= 0; i < nodelist.length; i++) {
            if (i != node && neighbors[node][i] == 0) {
                if (checkIntersections(nodelist[node], nodelist[i])) {
                    currentDistance = calculateDistance(nodelist[node], nodelist[i]);
                    if (closestDistance > currentDistance) {
                        closestDistance = currentDistance;
                        closestIndex = i;
                    }
                }
            }
        }
        System.out.println(closestIndex);
        return closestIndex;
    }
    /**
     * Checks if the a new edge between two {@code Node} intersects
     * with any of the pre-existing edges. When a collision is found
     * the method returns.
     *
     * @param n1 {@code Node} point one.
     * @param n2 {@code Node} point two.
     *                        
     * @return a {@code boolean} that is true if the new edge we are trying 
     * to construct between n1 and n2 does not intersect with any pre-existing lines.
     */
    private boolean checkIntersections(Node n1, Node n2) {
        final int ratio = 1000;
        final float increment = 1;
        float slope1 = 0;
        float slope2 = 0;
        float const1 = 0;
        float const2 = 0;
        float count;

        slope1 = calculateSlope(n1, n2);
        const1 = -slope1*(n1.getX())+n1.getY();
        for (int i = 0; i < neighbors.length; i++) {
            for (int j = 0; j < neighbors.length; j++){
                if (neighbors[i][j] == 1) {
                    count = Math.min(nodelist[i].getX(), nodelist[j].getX());
                    slope2 = calculateSlope(nodelist[i], nodelist[j]);
                    const2 = -slope2*(nodelist[i].getX())+nodelist[i].getY();
                    do {
                        if (formatFloat(slope1*count+const1) == formatFloat(slope2*count+const2)) {
                            return false;
                        }
                        count = formatFloat(ratio*(count)+ratio*(increment));
                        count = formatFloat(count/ratio);
                    } while (count != Math.max(nodelist[i].getX(), nodelist[j].getX()));
                }
            }
        }
        return true;
    }
    /**
     * Calculates the slope between two given {@code Node}.
     *
     * @param n1 {@code Node} point one.
     * @param n2 {@code Node} point two.
     *
     * @return a {@code float} that is the slope or 'm' in
     * the equation 'y=m(x)+b'.
     */
    private float calculateSlope(Node n1, Node n2) {
        return (n2.getY()-n1.getY())/(n2.getX()-n1.getX());
    }
    /**
     * Calculates the distance between two given {@code Node}.
     *
     * @param n1 {@code Node} point one.
     * @param n2 {@code Node} point two.
     *
     * @return a {@code float} that is the distance
     * between n1 and n2.
     */
    private float calculateDistance(Node n1, Node n2) {
        float dx = n1.getX() - n2.getX();
        float dy = n1.getY() - n2.getY();

        return (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
    /**
     * Formats {@code float} to the correct number of decimal points.
     *
     * @param f {@code float} float to be formatted.
     *
     * @return a {@code float} properly formatted float.
     */
    private float formatFloat(float f) {
        BigDecimal bd = new BigDecimal(f);
        bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    public ArrayList<Integer> getNeighborsForVertex(int vertex) {
        ArrayList<Integer> neighborIndexs = new ArrayList<Integer>();
        for(int i = 0; i < neighbors.length; i++){
            if(neighbors[vertex][i] == 1) {
                neighborIndexs.add(i);
            }
        }
        return neighborIndexs;
    }
    /**
     * Prints the nodelist[] to the terminal.
     */
    public void printNodeList() {
        for(int i = 0; i < nodelist.length; i++) {
            System.out.println("(" + nodelist[i].getX()+", "+ nodelist[i].getY()+")");
        }
    }
    /**
     * Prints the AdjacencyMatrix[][] to the terminal.
     */
    public void printAdjacencyMatrix() {
        System.out.print("   ");
        for(int i = 0; i < neighbors.length; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();

        for(int i = 0; i < neighbors.length; i++) {
            System.out.print(" " + i + " ");
            for (int j = 0; j < neighbors.length; j++){
                if(i == j){
                    System.out.print(" " + "-" + " ");
                }
                else {
                    System.out.print(" " + neighbors[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public int getGraphSize() {
        return n;
    }

    public Node[] getNodelist() {
        return nodelist;
    }

    public int[][] getNeighbors() {
        return neighbors;
    }



}
