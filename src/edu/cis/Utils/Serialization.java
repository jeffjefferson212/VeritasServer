package edu.cis.Utils;

import java.io.*;
import java.security.*;

public class Serialization {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ClassNotFoundException, SignatureException, InvalidKeyException {
        if (!(new File("private.key").exists()))
        {
            write("bag",10);
        }
        PublicKey publicKey = getPublicKey("bag",5);
    //    PrivateKey privateKey = getPrivateKey();
//        System.out.println(privateKey);
        //Digital signature verification testing
//        System.out.println("==================================================");
//        System.out.println(publicKey);
//        System.out.println("==================================================");
//        String message = "The quick brown fox jumped over the lazy dogs";
//        String message2 = "The quick brown fox jumped over the lazy digs";
//        Signature sig = Signature.getInstance("SHA256withRSA");
//        sig.initSign(privateKey);
//        for (char c : message.toCharArray())
//        {
//            byte b = (byte) c;
//            sig.update(b);
//        }
//
//        byte [] messageSignature = sig.sign();
//        Signature sig = Signature.getInstance("SHA256withRSA");
//        sig2.initVerify(publicKey);
//        for (char c : message2.toCharArray())
//        {
//            byte b = (byte) c;
//            sig2.update(b);
//        }
//
//        boolean valid = sig2.verify(messageSignature);
//        System.out.println("valid? " + valid);
    }

    public static PublicKey getPublicKey(String name , int number) throws IOException, ClassNotFoundException {
        String serialNum= name +number;

        File publicKeyFile = new File("public.key");
        FileInputStream fisPublic = new FileInputStream(publicKeyFile);
        ObjectInputStream oisPublic =  new ObjectInputStream(fisPublic);
        try {


        while (true)
        {
        String serial = (String) oisPublic.readObject();
       // System.out.println(serial);
        PublicKey key = (PublicKey) oisPublic.readObject();

        if (serialNum.equals(serial)){

            oisPublic.close();
            return key;
        }
        }
        }
        catch (EOFException e){
            oisPublic.close();
            return null;
        }

    }
//    public static PrivateKey getPrivateKey() throws IOException, ClassNotFoundException {
//        File privateKeyFile = new File("private.key");
//        FileInputStream fisPrivate = new FileInputStream(privateKeyFile);
//        ObjectInputStream oisPrivate =  new ObjectInputStream(fisPrivate);
//        PrivateKey key = (PrivateKey) oisPrivate.readObject();
//        return key;
//    }

    public static void write(String name, int number) throws NoSuchAlgorithmException, IOException {
        File publicKeyFile = new File("public.key");
        File privateKeyFile = new File("private.key");
        FileOutputStream fosPublic = new FileOutputStream(publicKeyFile);
        FileOutputStream fosPrivate = new FileOutputStream(privateKeyFile);
        ObjectOutputStream oosPublic =  new ObjectOutputStream(fosPublic);
        ObjectOutputStream oosPrivate =  new ObjectOutputStream(fosPrivate);
        for(int i=0; i<number; i++){


        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        KeyPair keypair = keyGen.genKeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        String serial = name + Integer.toString(i);
        oosPublic.writeObject(serial);
        oosPublic.writeObject(publicKey);
     //   oosPrivate.writeObject(privateKey);
        }
        oosPublic.close();
    }
}
