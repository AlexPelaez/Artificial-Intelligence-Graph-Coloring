

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

}
