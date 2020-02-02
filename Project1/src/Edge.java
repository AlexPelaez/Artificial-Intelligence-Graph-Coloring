public class Edge {
    private int vertex1;
    private int vertex2;

    public Edge(int vertex1, int vertex2){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public int getVertex1() {
        return vertex1;
    }

    public int getVertex2() {
        return vertex2;
    }

    public void setVertex1(int vertex1) {
        this.vertex1 = vertex1;
    }

    public void setVertex2(int vertex2) {
        this.vertex2 = vertex2;
    }
}
