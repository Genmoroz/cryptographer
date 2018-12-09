package com.frost.temp.encrypt.matrix.wrapper;

import com.frost.temp.encrypt.matrix.Matrix;

abstract class MatrixDecorator {

    Matrix matrix;

    MatrixDecorator(Matrix matrix) {
        this.matrix = matrix;
    }
}
