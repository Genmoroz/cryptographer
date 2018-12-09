package com.frost.temp.encrypt.matrix.impl;

import com.frost.temp.logging.Logger;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.logging.Level;

public class Matrix implements com.frost.temp.encrypt.matrix.Matrix {

    private final int capacity;
    private final Character[][] cells;

    public Matrix(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("The capacity cannot be less or equal zero.");
        this.capacity = capacity;
        cells = new Character[capacity][capacity];
    }

    public boolean insertChar(char val, int x, int y) {
        if (!validIndex(x, y) || !emptyCell(x, y)) {
            Logger.log(Level.INFO, "The adding char [" + val + "] was failed to index [" + x + ":" + y + "].", this.getClass());
            return false;
        }
        cells[x][y] = Character.toUpperCase(val);
        Logger.log(Level.INFO, "The char [" + val + "]  was successfully added to index [" + x + ":" + y + "].", this.getClass());
        return true;
    }

    public Character getCharInCell(int x, int y) {
        if (!validIndex(x, y))
            return null;
        return cells[x][y];
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getFreeSize() {
        int freeSize = 0;
        for (int x = 0; x < capacity; x++) {
            for (int y = 0; y < capacity; y++)
                if (Objects.isNull(cells[x][y]))
                    ++freeSize;
        }
        return freeSize;
    }

    @Override
    public boolean isFull() {
        return getFreeSize() == 0;
    }

    @Override
    public int[] getIndexesOfChar(char c) {
        return applyBooleanAndGetIndexes(Objects::equals, Character.toUpperCase(c));
    }

    @Override
    public int[] getEmptyCell() {
        return applyBooleanAndGetIndexes(Objects::equals, null);
    }

    private int[] applyBooleanAndGetIndexes(BiPredicate<Character, Object> function, Object target) {
        for (int x = 0; x < capacity; x++) {
            for (int y = 0; y < capacity; y++)
                if (function.test(cells[x][y], target))
                    return new int[]{
                            x, y
                    };
        }
        return null;
    }

    @Override
    public boolean contains(char c) {
        for (int x = 0; x < capacity; x++) {
            for (int y = 0; y < capacity; y++)
                if (Objects.equals(getCharInCell(x, y), Character.toUpperCase(c)))
                    return true;
        }
        return false;
    }

    private boolean emptyCell(int x, int y) {
        return Objects.isNull(cells[x][y]);
    }

    private boolean validIndex(int x, int y) {
        return (x < capacity && x >= 0 && y < capacity && y >= 0);
    }

    @Override
    public Matrix clone() {
        try {
            return (Matrix) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder matrix = new StringBuilder();
        matrix.append("\n Matrix " + capacity + "x" + capacity + '\n');
        for (int x = 0; x < capacity; x++) {
            for (int y = 0; y < capacity; y++)
                matrix.append(Objects.isNull(getCharInCell(x, y)) ? "null " : getCharInCell(x, y) + "    ");
            matrix.append('\n');
        }
        return matrix.toString();
    }
}
