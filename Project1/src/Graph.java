import java.math.BigDecimal;

/**
 * Created by Alex on 1/28/20.
 */
public class Graph {
    private int n;
    private Node nodelist[];
    private int neighbors[][];

    public Graph(int n) {
        this.n = n;
        populateNodelist();
        populateAdjacencyMatrix();
    }

    private void populateNodelist() {
        float x;
        float y;

        nodelist = new Node[n];
        for(int i= 0; i < nodelist.length; i++) {
            do {
                x = formatFloat((float) Math.random());
                y = formatFloat((float) Math.random());
            } while(x == 0 || y == 0);
            nodelist[i] = new Node(x,y,null);
        }
    }

    private void populateAdjacencyMatrix() {
        int randNode;
        int closestNode;

        neighbors = new int[n][n];
        for(int i= 0; i < nodelist.length; i++) {
            randNode = (int)(Math.random()*n);
            closestNode = closestNeighbor(randNode);
            neighbors[randNode][closestNode] = 1;
            neighbors[closestNode][randNode] = 1;
        }
    }

    private int closestNeighbor(int node) {
        float closestDistance;
        float currentDistance;
        int closestIndex;
        closestDistance = 100;
        closestIndex = 2;

        System.out.println(node+"  ");
        for(int i= 0; i < nodelist.length; i++) {
            if (i != node) {
                currentDistance = calculateDistance(nodelist[node], nodelist[i]);
                if (closestDistance > currentDistance && neighbors[node][i] == 0) {
                    if (caculateIntersection(nodelist[node], nodelist[i])) {
                        closestDistance = currentDistance;
                        closestIndex = i;
                    }
                }
            }
        }
        return closestIndex;
    }

    private boolean caculateIntersection(Node n1, Node n2) {
        final float increment;
        float slope1;
        float slope2;
        float const1;
        float const2;
        float count;

        increment = 0.000001F;
        slope1 = calculateSlope(n1, n2);
        const1 = -slope1*(n1.getX())+n1.getY();
        for(int i = 0; i < neighbors.length; i++) {
            for(int j = 0; j < neighbors.length; j++){
                if(neighbors[i][j] == 1) {
                    count = Math.min(nodelist[i].getX(), nodelist[j].getX());
                    slope2 = calculateSlope(nodelist[i], nodelist[j]);
                    const2 = -slope2*(nodelist[i].getX())+nodelist[i].getY();
                    do {
//                        System.out.println(count);
                        count =  count + .000001F;
                    }while(count != Math.max(nodelist[i].getX(), nodelist[j].getX()));
                }
            }
        }
        return true;
    }

//    private float calculateDomain(float x1, float x2) {
//        return (n2.getY()-n1.getY())/(n2.getX()-n1.getX());
//    }

    private float calculateSlope(Node n1, Node n2) {
        return (n2.getY()-n1.getY())/(n2.getX()-n1.getX());
    }

    private float calculateDistance(Node n1, Node n2) {
        float dx = n1.getX() - n2.getX();
        float dy = n1.getY() - n2.getY();

        return (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    private float formatFloat(float f) {
        BigDecimal bd = new BigDecimal(f);
        bd = bd.setScale(6, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public void printNodeList() {
        for(int i = 0; i < nodelist.length; i++) {
            System.out.println("X: " + nodelist[i].getX());
            System.out.println("Y: " + nodelist[i].getY());
        }
    }

    public void printAdjacencyMatrix() {
        for(int i = 0; i < neighbors.length; i++) {
           for(int j = 0; j < neighbors.length; j++){
               System.out.print(" " + neighbors[i][j] + " ");
           }
           System.out.println();
        }
    }

    public int getN() {
        return n;
    }

    public Node[] getNodelist() {
        return nodelist;
    }

    public int[][] getNeighbors() {
        return neighbors;
    }

}
