import java.util.ArrayList;

public class Genetic implements ConstraintSolverStrategy {

    private int population[][];
    private int color[];
    @Override
    public Graph solve(Graph g, int colorNum) {
        int k = 2*((int)(Math.random()*49)+1);
        color = new int[g.getGraphSize()];
        population = new int[k][g.getGraphSize()];
        for (int i = 0; i < k; i++) {
            for (int j=0; j <color.length; j++) {
                population[i][j] = (int) (Math.random() * colorNum);
            }
        }
        geneticAlgorithm(g.getNeighbors(), colorNum, k);
        return null;
    }



    private boolean geneticAlgorithm(int[][] adjacencyMatrix, int colorNum, int k) {
        ArrayList<int[]> nPop = new ArrayList();
        double temp = 1000;
        int numSteps = 0;
        while(temp != 0) {
            System.out.println("here");
            for(int i = 0; i < k; i++ ) {
                if (checkConstraints(adjacencyMatrix, population[i])) {
                    return true;
                }
            }

            for(int i = 0; i < k/2; i++) {

                int a[] = tournamentSelection(adjacencyMatrix, k);
                int[][] n = crossover(a[0], a[1]);

                n[2] =  mutate(n[2], colorNum);
                n[3] = mutate(n[3], colorNum);
                nPop.add(n[2]);
                nPop.add(n[3]);
            }
//            population = (int[]) nPop.toArray();
            numSteps++;
            temp = updateTemp(temp, numSteps);

        }
        return true;
    }

    private int[] mutate(int[] ints, int colorNum) {
        double probability = 1/color.length;

        for(int i = 0; i < color.length; i++) {
            double k = Math.random();
            if(k < probability) {
                int randColor = ((int)(Math.random()*4));
                ints[i] = randColor;
            }
        }
        return ints;
    }

    private int[][] crossover(int a1, int a2) {
        int rand = ((int)(Math.random()*color.length)+1);
        int[][] tempPopulation = new int[4][color.length];
        tempPopulation[0] = population[a1];
        tempPopulation[1] = population[a2];
        for(int i = 0; i < rand; i++){
            tempPopulation[2][i] = tempPopulation[0][i];
            tempPopulation[3][i] = tempPopulation[1][i];
        }
        for(int i = rand; i < color.length; i++){
            tempPopulation[2][i] = tempPopulation[0][i];
            tempPopulation[3][i] = tempPopulation[1][i];
        }
        return tempPopulation;
    }

    private int[] tournamentSelection(int[][] adjacencyMatrix, int k) {
        int q = ((int) (Math.random()*k)+2);
        int[] selectedIndividuals = new int[q];
        int randTemp = 0;
        int countTempMain = 0;
        int countIndexMain = 0;
        int countTempSec = 0;
        int countIndexSec = 0;
        int []countIndex = new int[2];
        for(int i = q-1; i >= 0; i--) {
            randTemp = ((int) Math.random()*i);
            selectedIndividuals[i] = randTemp;
        }
        int[] counter = new int[q];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] == 1) {
                    for(int m = 0; m < selectedIndividuals.length; m++){
                        if(population[selectedIndividuals[m]][i] == population[selectedIndividuals[m]][j]){
                            counter[m]++;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < counter.length; i++) {
            if(countTempMain > counter[i]) {
                countIndexSec = countIndexMain;
                countTempMain = counter[i];
                countIndexMain = i;
            }
        }
        countIndex[0] = countIndexMain;
        countIndex[1] = countIndexSec;
        return countIndex;
    }

    private double updateTemp(double t, int numSteps){
        double aplha  = .95;
        double newTemp = (t * aplha) / (t + numSteps);
        return newTemp;
    }

    private boolean checkConstraints(int[][] adjacencyMatrix, int population[]) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if (j>i && adjacencyMatrix[i][j] == 1) {
                    if(color[i]==color[j]){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
