/**
 * Created by Alex on 1/28/20.
 */
public class Context {
    private ConstraintSolverStrategy strategy;

    public Context() {}

    public Context(ConstraintSolverStrategy s) {
        strategy = s;
    }

    public Graph strategyOperation(Graph g, int colorNum) {
        return strategy.solve(g, colorNum);
    }

    public void setStrategy(ConstraintSolverStrategy s) {
        strategy = s;
    }

}
