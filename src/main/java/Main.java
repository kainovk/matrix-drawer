import matrix.MatrixParameters;
import matrix.MatrixUI;

public class Main {
    public static void main(String[] args) {
        int numRows = 3;
        int numCols = 5;
        int maxValue = 100;
        MatrixParameters<Integer> params = new MatrixParameters<>(numRows, numCols, maxValue);

        new MatrixUI<>(params).runGroupMatrixExample();
    }
}