package com.frost.temp.encrypt.matrix;

import com.frost.temp.encrypt.matrix.wrapper.UniqueCharMatrix;

public class FactoryMatrix {

    private FactoryMatrix() {
    }

    public Matrix createMatrix(int capacity) {
        return new com.frost.temp.encrypt.matrix.impl.Matrix(capacity);
    }

    public Matrix createUniqueCharMatrix(int capacity) {
        return new UniqueCharMatrix(createMatrix(capacity));
    }

    private static class FactorySingletonHolder {
        private static final FactoryMatrix FACTORY_MATRIX = new FactoryMatrix();
    }

    public static FactoryMatrix newInstance() {
        return FactorySingletonHolder.FACTORY_MATRIX;
    }
}
