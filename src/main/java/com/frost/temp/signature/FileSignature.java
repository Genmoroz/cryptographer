package com.frost.temp.signature;

import com.frost.temp.logging.Logger;
import com.google.common.base.Stopwatch;
import sun.security.rsa.RSASignature;

import java.io.*;
import java.lang.reflect.Array;
import java.security.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class FileSignature {

    public static interface CryptoAlgorithm {
        String SHA1_WITH_DSA = "SHA1withDSA";
        String SHA1_WITH_RSA = "SHA1withRSA";
        String SHA256_WITH_RSA = "SHA256withRSA";
    }

    private KeyPairGenerator generator;
    private SecureRandom random;
    private Signature signature;
    private PublicKey publicKey;
    private PrivateKey privateKey;

    private FileSignature(String cryptoAlgorithm, String hashingAlgorithm) throws NoSuchAlgorithmException {
        generator = KeyPairGenerator.getInstance(cryptoAlgorithm);
        random = SecureRandom.getInstance(hashingAlgorithm);
    }

    public static FileSignature newInstance(String cryptoAlgorithm, String hashingAlgorithm) throws NoSuchAlgorithmException {
        return new FileSignature(cryptoAlgorithm, hashingAlgorithm);
    }

    private void createKeys(int keySize) {
        generator.initialize(keySize, random);
        KeyPair keyPair = generator.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey  = keyPair.getPublic();
        Logger.logAndPrint(Level.INFO, "The public key " + Arrays.toString(publicKey.getEncoded()) + " is generated successfully", this.getClass());
    }

    public void writePrivateKey(File file, PrivateKey key) {
        writeKey(file, key);
    }

    public void writePublicKey(File file, PrivateKey key) {
        writeKey(file, key);
    }

    private void writeKey(File file, Object key) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(key);
            Logger.logAndPrint(Level.INFO, "The file " + file.getName() + " is signed successfully", this.getClass());
        } catch (IOException e) {
            Logger.logAndPrint(Level.SEVERE, e, "The signing file " + file.getName() + " failed", this.getClass());
            e.printStackTrace();
        }
    }

    private Object readKey(File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private byte[] readKey(File file) {
//        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
//            byte[] signature = new byte[bis.available()];
//            bis.read(signature);
//            return signature;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    private static SignedObject createSignedObject(String message, PrivateKey key) throws InvalidKeyException,
            SignatureException, IOException, NoSuchAlgorithmException {
        Signature signature = Signature.getInstance(key.getAlgorithm());
        return new SignedObject(message, key, signature);
    }

    private static boolean verifySignedObject(SignedObject signedObject, PublicKey key)
            throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
        Signature signature = Signature.getInstance(key.getAlgorithm());
        return signedObject.verify(key, signature);
    }
}
