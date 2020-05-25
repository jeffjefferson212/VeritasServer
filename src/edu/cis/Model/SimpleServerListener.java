package edu.cis.Model;

import edu.cis.Model.Request;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface SimpleServerListener
{
    String requestMade(Request req) throws IOException, ClassNotFoundException, NoSuchAlgorithmException;
}
