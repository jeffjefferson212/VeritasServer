/*
 * File: FacePamphletServer.java
 * ------------------------------
 * When it is finished, this program will implement a basic
 * social network management server.  Remember to update this comment!
 */

package edu.cis.Controller;

import acm.program.*;
import edu.cis.Model.CISConstants;
import edu.cis.Model.Request;
import edu.cis.Model.SimpleServerListener;
import edu.cis.Utils.Serialization;
import edu.cis.Utils.SimpleServer;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;


public class CISbookServer extends ConsoleProgram
        implements SimpleServerListener
{

    /* The internet port to listen to requests on */
    private static final int PORT = 8000;

    /* The server object. All you need to do is start it */
    private SimpleServer server = new SimpleServer(this, PORT);

    /**
     * Starts the server running so that when a program sends
     * a request to this server, the method requestMade is
     * called.
     */
    public void run()
    {
        println("Starting server on port " + PORT);
        server.start();
    }

    /**
     * When a request is sent to this server, this method is
     * called. It must return a String.
     */

    public String requestMade(Request request) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        String cmd = request.getCommand();
        println(request.toString());

        // your code here.
        if (request.getCommand().equals(CISConstants.PING))
        {
            final String PING_MSG = "Hello, internet";
            println("   => " + PING_MSG);
            return PING_MSG;
        }

        if (request.getCommand().equals(CISConstants.GET_PUBKEY))
        {
            System.out.println("accepted public key");
            String serialtag =request.getParam(CISConstants.SERIALTAG);
            int serialNum =Integer.parseInt(request.getParam(CISConstants.SERIALNUM));
            System.out.println(Serialization.getPublicKey(serialtag,serialNum));
            return Serialization.getPublicKey(serialtag,serialNum).toString();

        }

        if (request.getCommand().equals(CISConstants.ADD_PUBKEY))
        {
            String serialtag =request.getParam(CISConstants.SERIALTAG);
            int numberOfGoods =Integer.parseInt(request.getParam(CISConstants.NUMBEROFGOODS));
            Serialization.write(serialtag,numberOfGoods);
        }
        if (request.getCommand().equals(CISConstants.PING))
        {
            final String PING_MSG = "Hello, internet";
            println("   => " + PING_MSG);
            return PING_MSG;
        }
        if (request.getCommand().equals(CISConstants.SIGN))
        {
            String serialtag =request.getParam(CISConstants.SERIALTAG);
            String message=request.getParam(CISConstants.NUMBERTOBESIGNED);
            String serialNum =request.getParam(CISConstants.SERIALNUM);

            return Serialization.sign(serialtag,serialNum,message);

        }


        return "Error: Unknown command " + cmd + ".";
    }



    public static void main(String[] args)
    {
        CISbookServer f = new CISbookServer();
        f.start(args);

    }
}
