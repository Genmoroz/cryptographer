package com.frost.temp.encrypt.double_matrix_encrypt.impl;

import com.frost.temp.encrypt.double_matrix_encrypt.Cryptographer;
import com.frost.temp.encrypt.double_matrix_encrypt.Decoder;
import com.frost.temp.encrypt.matrix.Matrix;
import lombok.Getter;

public class DoubleMatrixDecoder implements Decoder {

    private Matrix firstMatrix;
    private Matrix secondMatrix;

    @Getter
    String code;

    public DoubleMatrixDecoder(Matrix firstMatrix, Matrix secondMatrix, String code) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.code = code;
    }

    @Override
    public String decode() {
        return Cryptographer.encryptDoubleMatrix(firstMatrix, secondMatrix, code);
    }

    @Override
    public Matrix[] getMatrices() {
        return new Matrix[]{
                firstMatrix,
                secondMatrix
        };
    }
}