import java.awt.*;

/**
 * Created by Alex on 1/28/20.
 */
public class Graph {
    private int n;
    private Node nodelist[];
    private int neighbors[][];

    public Graph(int n){
        this.n = n;
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
        for(int i= 0; i <= nodelist.length; i++) {
            nodelist[i].setX(10);
            nodelist[i].setY(10);
            nodelist[i].setC(Color.black);

        }
    }

    public void printNodeList(){
        for(int i= 0; i <= nodelist.length; i++) {
            System.out.println("X: " + nodelist[i].getX());
            System.out.println("Y: " + nodelist[i].getY());


        }
    }

}
