

/**
 * Created by Alex on 1/28/20.
 */
public class Graph {
    private int n;
    private Node nodelist[];
    private int neighbors[][];

    public Graph(int n){
        this.n = n;
        populateNodelist();
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

    public void populateNodelist(){
        nodelist = new Node[n];
        for(int i= 0; i < nodelist.length; i++) {
            float x = (float) Math.random();
            float y = (float) Math.random();

            while(x == 0 || y == 0) {
                x = (float) Math.random();
                y = (float) Math.random();

            }
            nodelist[i] = new Node(x,y,null);



        }
    }

    public void printNodeList(){
        for(int i= 0; i < nodelist.length; i++) {
            System.out.println("X: " + nodelist[i].getX());
            System.out.println("Y: " + nodelist[i].getY());


        }
    }

    public float getDistance(float x1, float y1, float x2, float y2)
    {
        float dx = x1 - x2;
        float dy = y1 - y2;
        float dist = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        return dist;
    }

}
