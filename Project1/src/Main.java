public class Main {

    // The issue might be caused by floating point numbers not being accurate enough in the equation that checks the intersection
    public static void main(String[] args) {
        final ConstraintSolverStrategy[] backtrackingSolvers = {new ForwardChecking(), new SimpleBacktracking(), new ArcConsistency()};
        Graph backtrackingGraphs[][] = new Graph[3][10];
        Context c = new Context();
        c.setStrategy((new SimulatedAnnealing()));
        backtrackingGraphs[0][0] = new Graph(10);
        c.strategyOperation(backtrackingGraphs[0][0], 2);
//        for(int i = 0; i < 3; i++) {
//            for(int j = 0; j < 10; j++) {
//                backtrackingGraphs[i][j] = new Graph((10*(i+1)));
//            }
//        }
//        int count = 0;
//        for(int i = 0; i < 3; i++) {
//            c.setStrategy(backtrackingSolvers[i]);
//            System.out.println(backtrackingSolvers[i].getClass().getName());
//            for(int j = 0; j < 10; j++) {
//                count = count + c.strategyOperation(backtrackingGraphs[i][j], 4);
//            }
//            count = count/10;
//            System.out.println(count);
//        }

//        for (int j = 0; j < 3; j++) {
//            System.out.println("Graph : " + (j + 1) * 10);
//            for (int i = 0; i < solvers.length; i++) {
//                System.out.println("Strategy: " + solvers[i].getClass().getName());
//                c.setStrategy(solvers[i]);
//                c.strategyOperation(graphs[j], 4);
//            }
//            System.out.println();
//        }

//        for(int i = 10; i <= 100; i+=10) {
//            graphs[(i / 10 - 1)] = new Graph(i);
//        }
/*
(0.2407, 0.9712)
(0.5686, 0.5975)
(0.7973, 0.8415)
(0.2935, 0.1026)
(0.6951, 0.8155)
(0.7172, 0.6195)
(0.7849, 0.0543)
(0.4253, 0.3342)
(0.1019, 0.6077)
(0.6277, 0.8892)


    0  1  2  3  4  5  6  7  8  9
 0  -  1  0  0  0  1  0  0  1  1
 1  1  -  0  0  0  1  1  1  1  0
 2  0  0  -  0  1  1  1  0  0  1
 3  0  0  0  -  0  0  1  1  1  0
 4  0  0  1  0  -  1  0  0  0  1
 5  1  1  1  0  1  -  1  0  0  1
 6  0  1  1  1  0  1  -  1  0  0
 7  0  1  0  1  0  0  1  -  1  0
 8  1  1  0  1  0  0  0  1  -  0
 9  1  0  1  0  1  1  0  0  0  -
 */


    }
}




