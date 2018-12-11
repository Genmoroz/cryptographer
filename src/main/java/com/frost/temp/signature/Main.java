package com.frost.temp.signature;

import java.io.File;
import java.io.IOException;
import java.security.*;

public class Main {

    public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException, IOException, SignatureException, InvalidKeyException {

        FileSignature signer = FileSignature.newInstance("DSA", "SHA1PRNG");
        File file = new File("genmor.sign");
        file.createNewFile();
        signer.writePrivateKey(new File("private.key"));
        PublicKey publicKey = signer.sign(file, FileSignature.CryptoAlgorithm.SHA1_WITH_DSA, 1024, "Gennady");
        signer.verify(file, publicKey, "Gennady");
        file.delete();
    }
}