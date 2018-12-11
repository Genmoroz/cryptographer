package com.frost.temp.signature;

import java.io.File;
import java.io.IOException;
import java.security.*;

public class Main {

    public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException, IOException, SignatureException, InvalidKeyException {

        FileSignature signer = FileSignature.newInstance("DSA", "SHA1PRNG");
        signer.createKeys(1024).writePublicKey(new File("public.key")).writePrivateKey(new File("private.key"));;

        SignedObject object = FileSignature.createSignedObject("Gennady", (PrivateKey) signer.readKey(new File("private.key")));
        boolean isVerify = FileSignature.verifySignedObject(object, (PublicKey) signer.readKey(new File("public.key")));
        System.out.println(isVerify);
    }
}