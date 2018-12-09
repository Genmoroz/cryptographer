package com.frost.temp.encrypt.double_matrix_encrypt.impl;

import com.frost.temp.encrypt.double_matrix_encrypt.Cryptographer;
import com.frost.temp.encrypt.matrix.FactoryMatrix;
import com.frost.temp.encrypt.matrix.Matrix;
import com.frost.temp.logging.Logger;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public class DoubleMatrixCryptographer implements Cryptographer {

    private static final String LETTERS = " АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVXYZ1234567890-_=+/?.>,<!@#$%^&*()\\\"~`|\\/':;";

    private Matrix firstMatrix;
    private Matrix secondMatrix;

    @Getter
    private String code;
    private String message;
    private Set<Character> uniqueCharacters;

    public DoubleMatrixCryptographer(String message) {
        if (message.isEmpty())
            throw new IllegalArgumentException();

        Set<Character> set = new HashSet<>();
        for (Character character : message.toCharArray())
            set.add(character);

        int size = set.size();
        if (size % 2 != 0) {
            size++;
        }

        size = (int) (Math.sqrt(size) + 1);

        this.message = message;
        uniqueCharacters = set;
        firstMatrix = FactoryMatrix.newInstance().createUniqueCharMatrix(size);
        secondMatrix = FactoryMatrix.newInstance().createUniqueCharMatrix(size);
        fillMatrices();
    }

    @Override
    public void encrypt() {
        message = (message.length() % 2 != 0) ? (message + " ") : message;
        code = Cryptographer.encryptDoubleMatrix(firstMatrix, secondMatrix, message);
    }

    @Override
    public Matrix[] getMatrices() {
        return new Matrix[]{
                firstMatrix.clone(),
                secondMatrix.clone()
        };
    }

    private void fillMatrices() {
        Character[] chars = uniqueCharacters.toArray(new Character[0]);

        for (int index = 0; index < uniqueCharacters.size(); index++) {
            insertChar(chars[index], firstMatrix);
            insertChar(chars[(uniqueCharacters.size() - 1) - index], secondMatrix);
        }

        fillRandomValueMatrix(firstMatrix);
        fillRandomValueMatrix(secondMatrix);

        System.out.println(firstMatrix);
        System.out.println(secondMatrix);
        Logger.log(Level.INFO, firstMatrix.toString(), this.getClass());
        Logger.log(Level.INFO, secondMatrix.toString(), this.getClass());
    }

    private void fillRandomValueMatrix(Matrix matrix) {
        while (!matrix.isFull()) {
            int[] indexes = matrix.getEmptyCell();
            matrix.insertChar(
                    LETTERS.toCharArray()[(int) (Math.random() * 1000) % LETTERS.length()],
                    indexes[0], indexes[1]
            );
        }
    }

    private void insertChar(char c, Matrix matrix) {
        for (int x = 0; x < matrix.getCapacity(); x++)
            for (int y = 0; y < matrix.getCapacity(); y++)
                if (matrix.insertChar(c, x, y))
                    return;
    }
}
