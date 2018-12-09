package com.frost.temp.encrypt.matrix.wrapper;

import com.frost.temp.encrypt.matrix.Matrix;

import java.util.Objects;

public class UniqueCharMatrix extends MatrixDecorator implements Matrix {

    public UniqueCharMatrix(Matrix matrix) {
        super(matrix);
    }

    @Override
    public boolean insertChar(char val, int varX, int varY) {
        if (matrix.contains(val)) return false;
        return matrix.insertChar(val, varX, varY);
    }

    @Override
    public Character getCharInCell(int x, int y) {
        return matrix.getCharInCell(x, y);
    }

    @Override
    public int getCapacity() {
        return matrix.getCapacity();
    }

    @Override
    public int getFreeSize() {
        return matrix.getFreeSize();
    }

    @Override
    public boolean isFull() {
        return matrix.isFull();
    }

    @Override
    public int[] getIndexesOfChar(char c) {
        return matrix.getIndexesOfChar(c);
    }

    @Override
    public int[] getEmptyCell() {
        return matrix.getEmptyCell();
    }

    @Override
    public boolean contains(char c) {
        return matrix.contains(c);
    }

    @Override
    public Matrix clone() {
        return matrix.clone();
    }

    @Override
    public String toString() {
        return matrix.toString();
    }
}
