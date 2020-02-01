public class Arc {
    private int constraintMatrix[][];
    private int nodeIndex;
    private int colorNum;

    public Arc(int nodeIndex, int colorNum){
        this.nodeIndex = nodeIndex;
        this.colorNum = colorNum;
        populateConstraint();
    }

    private void  populateConstraint(){
        int count = 0;
        constraintMatrix = new int[((colorNum * colorNum) - colorNum)][2];
        for(int i =0; i < colorNum; i++){
            for(int j = 0; j < colorNum; j++){
                if(i != j) {
                    constraintMatrix[count][0] = i;
                    constraintMatrix[count][1] = j;
                    count++;
                }
            }
        }
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    public int[][] getConstraintMatrix() {
        return constraintMatrix;
    }

    public void setConstraintMatrix(int[][] constraintMatrix) {
        this.constraintMatrix = constraintMatrix;
    }

    public void printConstraintMatrix(){
        for(int i =0; i < ((colorNum * colorNum) -  colorNum); i++){
                System.out.print(constraintMatrix[i][0]);
                System.out.print(constraintMatrix[i][1]);
            System.out.println();
        }
    }
}
