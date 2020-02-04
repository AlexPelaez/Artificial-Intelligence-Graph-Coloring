import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Alex on 1/28/20.
 */

public class SimulatedAnnealing implements ConstraintSolverStrategy {
    private double temp;
    private int steps;
    private int color[];
    Random random = new Random();


    @Override
    public Graph solve(Graph g, int colorNum) {

        steps = 0;
        temp = 1000000;


        color = new int[g.getGraphSize()];
        for (int i = 0; i < color.length; i++) {
            color[i] = random.nextInt(colorNum);
        }

        simulatedAnnealing(color, temp, steps, g.getNeighbors(), colorNum);

        return null;
    }

    public void simulatedAnnealing(int c[], double t, int steps, int neighbors[][], int colorNum) {
        int[] current = c;


        int randNode = -1;
        int next[] = new int[c.length];

        for (int i = 0; i < c.length; i++) {
            System.out.println("C: " + c[i]);
        }


        while (t > 0) {

            boolean conflict = true;

            boolean conflictNodes[];
            boolean newConflictNodes[];


            t = updateSchedule(t, steps);
            System.out.println("T: " + t);
            if (t == 0) {
                System.out.println("Done");
                break;
            }

            


            conflictNodes = checkConflict(neighbors, current);



                randNode = random.nextInt(conflictNodes.length);
                if (conflictNodes[randNode] == true) {
                    next[randNode] = minCost(neighbors, colorNum, current, randNode);
                }

            newConflictNodes = checkConflict(neighbors, next);

            int deltaEnergy =  value(newConflictNodes) - value(conflictNodes);
            System.out.println("Delta Energy: " + deltaEnergy);
            double k = 1.380649 * Math.pow(10, -23);
            System.out.println("Steps: " + steps);

            if (deltaEnergy < 0) {
                current = next;
            } else {
                double probability = (double)Math.exp(deltaEnergy / k * t);
                steps++;
                System.out.println("prob: " + probability);
            }

        }


    }

    private int minCost(int [][]neighbors,int colorNum, int [] current, int randNode) {

        int currentMinimum = 100;
        int tempMinimum = 0;
        int currentIndex = 0;
        for (int i = 0; i < colorNum; i++) {
            if (checkColor(neighbors, current, i, randNode)) {
                return i;
                //next = minimizeConflict(i, randNode, current);
            } else {
                tempMinimum = calculateConflicts(neighbors, randNode, i, current);
                if (currentMinimum > tempMinimum) {
                    currentMinimum = tempMinimum;
                    currentIndex = i;

                }
            }
        }
//        next = minimizeConflict(currentIndex, randNode, current);
       return currentIndex;
    }


    private int calculateConflicts(int [][] adjacencyMatrix, int randNode, int currentColor, int [] current) {
        int counter= 0;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[i][randNode] == 1) {
                if (currentColor == current[i]) {
                    counter++;
                }
            }
        }
        return counter;


    }

    private int value(boolean a[]){
        int counter = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] == true){
                counter++;
            }
        }
        return counter;
    }

    private double updateSchedule(double t, int numSteps){
        double aplha  = .95;
        double newTemp = (t * aplha) / (t + numSteps);
        return newTemp;



    }
    public boolean[] checkConflict(int[][] adjacencyMatrix, int a[]) {
        boolean[] conflict = new boolean[a.length];
        for(int i = 0; i <conflict.length;i++){
            conflict[i] = false;
        }


        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for(int j = 0; j < adjacencyMatrix.length; j++){
                if (i > j && adjacencyMatrix[i][j] == 1) {
                    if (a[i] == a[j]) {
                        conflict[i] = true;
                        conflict[j] = true;

                    }
                }
            }
        }
        return conflict;
    }
    private int[] changeColor(int colorIndex, int index, int colors[]){

        int [] temp = colors;
        temp[index] = colorIndex;
        return  temp;

    }

    private boolean checkColor(int adjacencyMatrix[][], int a[], int colorNum, int index) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[i][index] == 1) {
                if (colorNum == a[i]) {
                    return false;
                }
            }
        }
        return true;
    }

}
