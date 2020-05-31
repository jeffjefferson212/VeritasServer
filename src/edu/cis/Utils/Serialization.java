package edu.cis.Utils;

import java.io.*;
import java.security.*;

public class Serialization {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, ClassNotFoundException, SignatureException, InvalidKeyException {
        if (!(new File("private.key").exists()))
        {
            write("bag", 10);
        }

        //get pubic test
       // PublicKey publicKey = getPublicKey("bag", 5);
        //    PrivateKey privateKey = getPrivateKey();
//        System.out.println(privateKey);
        //Digital signature verification testing
//        System.out.println("==================================================");
//        System.out.println(publicKey);
//        System.out.println("==================================================");
//        String message = "The quick brown fox jumped over the lazy dogs";
//        Signature sig = Signature.getInstance("SHA256withRSA");
//        sig.initSign(privateKey);
//        for (char c : message.toCharArray())
//        {
//            byte b = (byte) c;
//            sig.update(b);
//        }
//        byte [] messageSignature = sig.sign()

        //Transmit message and messageSignature together from server to client
//
//        String message2 = "The quick brown fox jumped over the lazy digs";
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
    public  static String sign (String name, String number, String randomNum) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        StringBuffer retString = new StringBuffer();
        int serialnum=Integer.parseInt(number);
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(getPrivateKey(name,serialnum));
        System.out.println((getPrivateKey(name,serialnum)));

        for (char c : randomNum.toCharArray())
        {
            byte b = (byte) c;
            sig.update(b);
        }
        byte [] messageSignature = sig.sign();
        for (int i = 0; i < messageSignature.length; ++i) {
            retString.append(Integer.toHexString(0x0100 + (messageSignature[i] & 0x00FF)).substring(1));
        }
        return  retString.toString();
    }
    public static PublicKey getPublicKey(String name, int number) throws IOException, ClassNotFoundException {
        String serialNum = name + number;

        File publicKeyFile = new File("public.key");
        FileInputStream fisPublic = new FileInputStream(publicKeyFile);
        ObjectInputStream oisPublic = new ObjectInputStream(fisPublic);
        try {


            while (true) {
                String serial = (String) oisPublic.readObject();
                // System.out.println(serial);
                PublicKey key = (PublicKey) oisPublic.readObject();

                if (serialNum.equals(serial)) {
                    oisPublic.close();
                    return key;
                }
            }
        } catch (EOFException e) {
            oisPublic.close();
            return null;
        }

    }

    public static PrivateKey getPrivateKey(String name, int number) throws IOException, ClassNotFoundException {
        String serialNum = name + number;

        File privateKeyFile = new File("private.key");
        FileInputStream fisPrivate = new FileInputStream(privateKeyFile);
        ObjectInputStream oisPrivate = new ObjectInputStream(fisPrivate);
        try {


            while (true) {
                String serial = (String) oisPrivate.readObject();
                // System.out.println(serial);
                PrivateKey key = (PrivateKey) oisPrivate.readObject();

                if (serialNum.equals(serial)) {
                    oisPrivate.close();
                    return key;
                }
            }
        } catch (EOFException e) {
            oisPrivate.close();
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
        ObjectOutputStream oosPublic = new ObjectOutputStream(fosPublic);
        ObjectOutputStream oosPrivate = new ObjectOutputStream(fosPrivate);
        for (int i = 0; i < number; i++) {


            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            KeyPair keypair = keyGen.genKeyPair();
            PrivateKey privateKey = keypair.getPrivate();
            PublicKey publicKey = keypair.getPublic();

            String serial = name + i;
            oosPublic.writeObject(serial);
            oosPrivate.writeObject(serial);
            oosPublic.writeObject(publicKey);
            oosPrivate.writeObject(privateKey);
        }
        oosPublic.close();
    }
}
