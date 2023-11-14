package matrix.decorator;

import matrix.AbstractMatrix;
import matrix.Matrix;
import vector.Vector;

import java.util.ArrayList;
import java.util.List;

public class EnumeratedMatrixDecorator<T extends Number> implements Matrix<T> {

    private final AbstractMatrix<T> originalMatrix;
    private final List<Integer> swappedRows;
    private final List<Integer> swappedColumns;

    public EnumeratedMatrixDecorator(AbstractMatrix<T> originalMatrix) {
        this.originalMatrix = originalMatrix;
        this.swappedRows = new ArrayList<>();
        this.swappedColumns = new ArrayList<>();
    }

    @Override
    public T get(int row, int col) {
        row = getMappedRow(row);
        col = getMappedColumn(col);
        return originalMatrix.get(row, col);
    }

    @Override
    public void set(int row, int col, T value) {
        row = getMappedRow(row);
        col = getMappedColumn(col);
        originalMatrix.set(row, col, value);
    }

    @Override
    public int getRowCount() {
        return originalMatrix.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return originalMatrix.getColumnCount();
    }

    public void swapRows(int row1, int row2) {
        swappedRows.add(row1);
        swappedRows.add(row2);
    }

    public void swapColumns(int col1, int col2) {
        swappedColumns.add(col1);
        swappedColumns.add(col2);
    }

    public void restoreOriginalMatrix() {
        swappedRows.clear();
        swappedColumns.clear();
    }

    private int getMappedRow(int row) {
        if (swappedRows.contains(row)) {
            int index = swappedRows.indexOf(row);
            return swappedRows.get((index % 2) == 0 ? index + 1 : index - 1);
        }
        return row;
    }

    private int getMappedColumn(int col) {
        if (swappedColumns.contains(col)) {
            int index = swappedColumns.indexOf(col);
            return swappedColumns.get((index % 2) == 0 ? index + 1 : index - 1);
        }
        return col;
    }

    public AbstractMatrix<T> getOriginalMatrix() {
        return originalMatrix;
    }

    public AbstractMatrix<T> getDecoratedMatrix() {
        return new AbstractMatrix<>(originalMatrix.getRowCount(), originalMatrix.getColumnCount(), originalMatrix.getDrawer()) {

            @Override
            public T get(int row, int col) {
                row = getMappedRow(row);
                col = getMappedColumn(col);
                return originalMatrix.get(row, col);
            }

            @Override
            public void set(int row, int col, T value) {
                row = getMappedRow(row);
                col = getMappedColumn(col);
                originalMatrix.set(row, col, value);
            }

            @Override
            public Vector<T> createVector(int size) {
                return originalMatrix.createVector(size);
            }
        };
    }
}