package com.example.reactor.base.security.rsa;

import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;

import java.security.*;
import java.security.interfaces.*;
import java.util.Base64;

public class RsaTest {

    @Test
    void testGenerator() throws Exception {

        String publicKey = null;
        String privateKey = null;

        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        byte[] keyBs = rsaPublicKey.getEncoded();
        publicKey = encodeBase64(keyBs);
        System.out.println("base64 public key : \r\n" + publicKey);

        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        keyBs = rsaPrivateKey.getEncoded();
        privateKey = encodeBase64(keyBs);
        System.out.println("base64 public key : \r\n" + privateKey);

    }

    static String encodeBase64(byte[] source) throws Exception{
        return new String(Base64Utils.decode(source), "UTF-8");
    }

}
