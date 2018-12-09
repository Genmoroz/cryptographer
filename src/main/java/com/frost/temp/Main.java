package com.frost.temp;

import com.frost.temp.encrypt.double_matrix_encrypt.Cryptographer;
import com.frost.temp.encrypt.double_matrix_encrypt.Decoder;
import com.frost.temp.encrypt.double_matrix_encrypt.impl.DoubleMatrixCryptographer;
import com.frost.temp.encrypt.double_matrix_encrypt.impl.DoubleMatrixDecoder;
import com.frost.temp.encrypt.matrix.Matrix;

import java.io.IOException;
import java.util.logging.LogManager;

public class Main {

    static {
        try {
            LogManager.getLogManager().readConfiguration(
                    Main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
    }

    public static void main(String... args) {
        Cryptographer cryptographer = new DoubleMatrixCryptographer("Gennady is the best developer!!! ХУЙ ЗАЛУПА ПОШЁЛ НАХУЙ ПИИИИДОOOOOOOOР я ебал твоё тёлку я ебал твою тёлку yyyyyyy");
        Matrix[] matrices = cryptographer.getMatrices();
        cryptographer.encrypt();
        System.out.println(cryptographer.getCode());
        Decoder decoder = new DoubleMatrixDecoder(matrices[1], matrices[0], cryptographer.getCode());

        System.out.println(decoder.decode());
    }
}