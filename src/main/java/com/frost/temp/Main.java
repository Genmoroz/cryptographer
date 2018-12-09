package com.frost.temp;

import com.frost.temp.encrypt.double_matrix_encrypt.Cryptographer;
import com.frost.temp.encrypt.double_matrix_encrypt.Decoder;
import com.frost.temp.encrypt.double_matrix_encrypt.impl.DoubleMatrixCryptographer;
import com.frost.temp.encrypt.double_matrix_encrypt.impl.DoubleMatrixDecoder;
import com.frost.temp.encrypt.matrix.Matrix;
import com.frost.temp.logging.Logger;

import java.io.IOException;
import java.util.logging.Level;
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
        if (args.length == 0) {
            Logger.logAndPrint(Level.WARNING, "Message is empty, repeat attempt again.", Main.class);
            return;
        }
        String message = "";
        for (String str : args)
            message += " " + str;

        Cryptographer cryptographer = new DoubleMatrixCryptographer(message);

        Matrix[] matrices = cryptographer.getMatrices();
        cryptographer.encrypt();
        Logger.logAndPrint(Level.INFO, "Message: " + message, Main.class);
        Logger.logAndPrint(Level.INFO, "Encrypted message: " + cryptographer.getCode(), Main.class);
        Decoder decoder = new DoubleMatrixDecoder(matrices[1], matrices[0], cryptographer.getCode());
        Logger.logAndPrint(Level.INFO, "Decoded message: " + decoder.decode(), Main.class);
    }
}