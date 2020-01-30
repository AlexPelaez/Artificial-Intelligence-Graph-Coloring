public class Main {


    public static void main(String[] args) {
        Graph graphs[] = new Graph[10];
        Context c = new Context(new MinConflict());

//        for(int i = 10; i <= 100; i+=10) {
//            graphs[(i / 10 - 1)] = new Graph(i);
//        }

        graphs[0] = new Graph(10);
        graphs[0].printNodeList();
        graphs[0].printAdjacencyMatrix();

    }
}
