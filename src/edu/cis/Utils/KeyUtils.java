package edu.cis.Utils;

import edu.cis.Controller.CISbookServer;

import java.security.*;
import java.util.HashMap;
import java.util.Map;

public class KeyUtils {
    // instance variables
     public KeysDB keysDB;

    KeyPairGenerator keyGen;
    // constructor
    public KeyUtils (String filename) throws NoSuchAlgorithmException {
        keysDB = new KeysDB(filename);
        keyGen = KeyPairGenerator.getInstance("RSA");
    }
    PublicKey generateKey() throws NoSuchAlgorithmException {

            keyGen.initialize(512);
            KeyPair keypair = keyGen.genKeyPair();
            PrivateKey privateKey = keypair.getPrivate();
            PublicKey publicKey = keypair.getPublic();
            return publicKey;
        }
        public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException, KeyStoreException {

            KeyUtils keyUtils = new KeyUtils("keysDBFile");
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            KeyPair keypair = keyGen.genKeyPair();
            PrivateKey privateKey = keypair.getPrivate();
            PublicKey publicKey = keypair.getPublic();
            System.out.println(  keypair.getPublic().toString());
            System.out.println(keypair.getPublic().getAlgorithm());
            System.out.println(keypair.getPublic().getFormat());
            System.out.println(keypair.getPrivate().toString());
            System.out.println(keypair.getPrivate().getAlgorithm());
            System.out.println(keypair.getPrivate().getFormat());
            /////////////////////////////////////
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

            // get user password and file input stream
            char[] password = "password".toCharArray();

//            try (FileInputStream fis = new FileInputStream("keyStoreName")) {
//                ks.load(fis, password);
//                ks.
//            }
            /////////////////////////////////////

            byte[] publicKeyByteArray = keypair.getPublic().getEncoded();
            byte[] privateKeyByteArray = keypair.getPrivate().getEncoded();
            System.out.println(publicKeyByteArray.length + "\t" + privateKeyByteArray.length);
            StringBuffer retString = new StringBuffer();
            for (int i = 0; i < publicKeyByteArray.length; ++i) {
                retString.append(Integer.toHexString(0x0100 + (publicKeyByteArray[i] & 0x00FF)).substring(1));
            }
            System.out.println(retString);
            System.out.println(retString.length() /2);


            retString = new StringBuffer();
            for (int i = 0; i < privateKeyByteArray.length; ++i) {
                retString.append(Integer.toHexString(0x0100 + (privateKeyByteArray[i] & 0x00FF)).substring(1));
            }
            System.out.println(retString);
            System.out.println(retString.length()/2 );
            System.out.println("====================================================");
            /////////////////////////
            
            Provider[] providers =  Security.getProviders();
            for (Provider provider :providers )
            {
                System.out.println(provider);
            }
            System.out.println("====================================================");
            String keyAlgo =privateKey.getAlgorithm();
            System.out.println(keyAlgo);
            String sigAlgo ="MD2with" + keyAlgo;
            System.out.println(sigAlgo);

            Signature sig = Signature.getInstance(sigAlgo);


            System.out.println(sig);
            sig.initSign(privateKey);

            System.out.println(sig);
            String message = "1" ;
            System.out.println(message);
            byte[] data = message.getBytes();
            retString = new StringBuffer();
            for (int i = 0; i < data.length; ++i) {
                retString.append(Integer.toHexString(0x0100 + (data[i] & 0x00FF)).substring(1));
            }
            System.out.println(retString);
            sig.update(data);
            byte[] signedData = sig.sign();
            retString = new StringBuffer();
            for (int i = 0; i < signedData.length; ++i) {
                retString.append(Integer.toHexString(0x0100 + (signedData[i] & 0x00FF)).substring(1));
            }

            for (int i=0; i < 5; i++)
            {
              String s =keyUtils.generateKey().toString();
                System.out.println(s);
                keyUtils.keysDB.addKey(s);


            }

            keyUtils.keysDB.writeToFile();



        }
    }



