import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Alex on 1/28/20.
 */

public class SimulatedAnnealing implements ConstraintSolverStrategy {
    private int color[];
    private int steps = 0;
    private boolean hasSolution = false;
    Random random = new Random();


    @Override
    public int solve(Graph g, int colorNum) {



        color = new int[g.getGraphSize()];
        for (int i = 0; i < color.length; i++) {
            color[i] = random.nextInt(colorNum);
        }

        if(simulatedAnnealing(color,  g.getNeighbors(), colorNum))
        {
            return steps;

        }
        else {
            return steps * (-1);
        }
    }

    public boolean simulatedAnnealing(int c[],  int neighbors[][], int colorNum) {
        int[] current = c;
        double T  = 10000000;

        double t = 1.0;



        int randNode = -1;
        int next[] = new int[c.length];

//        for (int i = 0; i < c.length; i++) {
//            System.out.println("C: " + c[i]);
//        }


        while (T!=0) {

            if (checkConstraints(neighbors, current)){
                for(int i = 0; i < current.length; i++){
                    System.out.println(current[i]);
                }

                return true;

            }


            boolean conflict = true;

            boolean conflictNodes[];
            boolean newConflictNodes[];


            T = updateSchedule(T, steps, t);
//            System.out.println("T: " + t);

//            if (T <= 0) {
////                System.out.println("Done");
//                for(int i = 0; i < current.length; i++){
//                    System.out.println(current[i]);
//                }
//                break;
//            }




            conflictNodes = checkConflict(neighbors, current);



                randNode = random.nextInt(conflictNodes.length);
                if (conflictNodes[randNode] == true) {

                    next[randNode] = minCost(neighbors, colorNum, current, randNode);
                }


            newConflictNodes = checkConflict(neighbors, next);

            int deltaEnergy =  value(newConflictNodes) - value(conflictNodes);
//            System.out.println("Delta Energy: " + deltaEnergy);
            double k = 1.380649 * Math.pow(10, -23);
            System.out.println("Steps: " + steps);

            double randChance = (Math.random()*100) + 1;

            if (deltaEnergy <= 0) {
                current = next;
                steps++;
            } else {
                double probability = (double)Math.exp(deltaEnergy / k * t);
                if(randChance < probability){
                    current = next;
                    steps++;
                }


            }


        }
        return false;


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

    private double updateSchedule(double t, int numSteps, double alpha){
        return  (t * alpha) / (alpha + numSteps);




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

    private boolean checkConstraints(int[][] adjacencyMatrix, int [] a) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if(i != j){
                    if (adjacencyMatrix[i][j] == 1) {
                        if (a[i] == a[j]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

}
