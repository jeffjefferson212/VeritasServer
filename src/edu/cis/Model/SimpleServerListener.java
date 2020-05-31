package edu.cis.Model;

import edu.cis.Model.Request;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public interface SimpleServerListener
{
    String requestMade(Request req) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, SignatureException, InvalidKeyException;
}
