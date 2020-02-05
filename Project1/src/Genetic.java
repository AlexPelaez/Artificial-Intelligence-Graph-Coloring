import java.lang.reflect.Array;
import java.util.ArrayList;

public class Genetic implements ConstraintSolverStrategy {

    private int color[];
    private int finalColor[];
    private int counter= 0;
    private int numSteps = 0;

    @Override
    public int solve(Graph g, int colorNum) {
        color = new int[g.getGraphSize()];
        if(geneticAlgorithm(g.getNeighbors(), colorNum)){
            return numSteps;

        }

        return numSteps * -1 ;
    }


    private boolean geneticAlgorithm(int[][] adjacencyMatrix, int colorNum) {
        ArrayList<int[]> pop = new ArrayList();
        int k = 2 * ((int) (Math.random() * 49) + 1);

        double temp = 10000;
        int numSteps = 0;

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < color.length; j++) {
                color[j] = (int) (Math.random() * 4);
            }
            pop.add(color);
        }


        while (temp != 0) {
            for (int i = 0; i < pop.size() ;i++) {
                if (checkConstraints(adjacencyMatrix, pop.get(i))) {
                    finalColor = pop.get(i);

                    return true;
                }
            }

            ArrayList<int[]> nPop = new ArrayList();

//            for(int i = 0; i < k; i++ ) {
//                if (checkConstraints(adjacencyMatrix, population[i])) {
//                    return true;
//                }
//            }

            for (int i = 0; i < k / 2; i++) {

                int a1[] = tournamentSelection(adjacencyMatrix, pop, temp, k);
                int a2[] = tournamentSelection(adjacencyMatrix, pop, temp, k);
                ArrayList<int[]> crossed= crossover(a1,a2);

                int[] m1 = mutate(crossed.remove(crossed.size() - 1), colorNum);
                int[] m2 = mutate(crossed.remove(crossed.size() - 1), colorNum);

                nPop.add(m1);
                nPop.add(m2);
            }
            pop = nPop;

            numSteps++;
            temp = updateTemp(temp, numSteps);

        }

        int minimumConstraints = 1000;
        int minimumConstraintIndex = -1;

        for(int i = 0; i <pop.size(); i++){
            int consNum = countConstraints(pop.get(i), adjacencyMatrix);
            if (consNum < minimumConstraints){
                minimumConstraints = consNum;
            }
            minimumConstraintIndex = i;
        }
        finalColor = pop.get(minimumConstraintIndex);
        return false;
    }

    private int[] mutate(int[] ints, int colorNum) {
        double probability = 1.0 / color.length;

        for (int i = 0; i < color.length; i++) {
            double k = Math.random();
            if (k < probability) {
                int randColor = ((int) (Math.random() * 4));
                ints[i] = randColor;
            }
        }
        return ints;
    }

    private ArrayList<int[]> crossover(int[] a1, int[] a2) {
        int rand = ((int) (Math.random() * color.length) + 1);
        ArrayList<int[]> crossPop = new ArrayList<>();
//        int[][] tempPopulation = new int[4][color.length];
        int[] tempIndividual1 = a1;
        int[] tempIndividual2 = a2;
        for (int i = 0; i < rand; i++) {
            tempIndividual1[i] = a1[i];
            tempIndividual2[i] = a2[i];
        }
        for (int i = rand; i < color.length; i++) {
            tempIndividual1[i] = a2[i];
            tempIndividual2[i] = a1[i];
        }
        crossPop.add(tempIndividual1);
        crossPop.add(tempIndividual2);
        return crossPop;
    }

    private int[] tournamentSelection(int[][] adjacencyMatrix, ArrayList<int[]> pop, double T, int k) {

        ArrayList<int[]> selectedIndividuals = new ArrayList<>();
        int q = ((int) (Math.random() * (k - 2 + 1)) + 2);
        int randTemp = 0;
        int minConstraints = 1000;
        int minConstraintIndex = -1;

        for (int i = q - 1; i >= 0; i--) {
            randTemp = ((int) Math.random() * i);
            selectedIndividuals.add(pop.get(randTemp));
        }

        for(int i = 0; i <selectedIndividuals.size(); i++){
            int temp = countConstraints(selectedIndividuals.get(i), adjacencyMatrix);
            if (temp < minConstraints){
                minConstraints = temp;
            }
            minConstraintIndex = i;
        }

        return selectedIndividuals.get(minConstraintIndex);


    }


    private int countConstraints(int[] a, int[][] adjacencyMatrix){
        int count = 0;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (j>i && adjacencyMatrix[i][j] == 1) {
                    if(a[i]==a[j]){
                        count++;

                    }
                }
            }
        }
        return count;
    }

    private double updateTemp(double t, int numSteps){
        double alpha  = 5;
        double newTemp = (t * alpha) / (alpha + numSteps);
        return newTemp;
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
