package com.frost.temp.encrypt.matrix;

public interface Matrix extends Cloneable {

    boolean insertChar(char val, int x, int y);

    Character getCharInCell(int x, int y);

    int getCapacity();

    int getFreeSize();

    boolean isFull();

    int[] getIndexesOfChar(char c);

    int[] getEmptyCell();

    boolean contains(char c);

    Matrix clone();
}
