public class Main {

// The issue might be caused by floating point numbers not being accurate enough in the equation that checks the intersection
    public static void main(String[] args) {
        final ConstraintSolverStrategy[] solvers = {new ForwardChecking(), new SimpleBacktracking()};
        Context c = new Context();
        Graph graphs[] = new Graph[10];

        graphs[0] = new Graph(5);
        graphs[0].printNodeList();
        graphs[0].printAdjacencyMatrix();

        for (int i = 0; i < solvers.length; i++) {
            System.out.println();
            System.out.println();
            c.setStrategy(solvers[i]);
            c.strategyOperation(graphs[0], 3);
            System.out.println();
            System.out.println();

        }













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




