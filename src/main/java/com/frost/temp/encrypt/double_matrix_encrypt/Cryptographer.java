package com.frost.temp.encrypt.double_matrix_encrypt;

import com.frost.temp.encrypt.matrix.Matrix;

public interface Cryptographer {

    void encrypt();

    Matrix[] getMatrices();

    String getCode();

    /**
     * The private-package static method that encrypt any message,
     * but be careful, this method does not contain any checking block for your matrices,
     * make sure you have the valid matrices, and message for them.
     *
     * @param firstMatrix  the left matrix for encrypt
     * @param secondMatrix the right matrix for encrypt
     * @param message      the message that will be encrypted
     * @return the encrypted code in StringBuilder object
     */
    static String encryptDoubleMatrix(Matrix firstMatrix, Matrix secondMatrix, String message) {
        StringBuilder code = new StringBuilder();
        char[] characters = message.toCharArray();

        for (int index = 0; index < message.length() - 1; index += 2) {

            int[] indexesFirstLetter = firstMatrix.getIndexesOfChar(characters[index]);
            int[] indexesSecondLetter = secondMatrix.getIndexesOfChar(characters[index + 1]);

            code.append(secondMatrix.getCharInCell(indexesFirstLetter[0], indexesSecondLetter[1]));
            code.append(firstMatrix.getCharInCell(indexesSecondLetter[0], indexesFirstLetter[1]));
        }
        return code.toString();
    }
}