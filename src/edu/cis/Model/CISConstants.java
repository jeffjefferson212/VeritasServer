package edu.cis.Model;

public class CISConstants
{
    //return strings
    public static final String PROF_EXIST_ERR = "Error: You Fool.";
    public static final String PROF_NOTPRES_ERR = "Error: that profile doesn't exist.";

    public static final String SUCCESS = "success";
    public static final String TRUE_RET = "true";
    public static final String FALSE_RET = "false";

    //Parameters for requests

    public static final String SERIALTAG = "serialTag";
    public static final String SERIALNUM = "serialNum";
    public static final String NUMBEROFGOODS = "#ofgoods";
    public static final String NUMBERTOBESIGNED = "number";



    //Commands
    public static final String PING = "ping";
    public static final String ADD_PROF = "addProfile";
    public static final String GET_PUBKEY = "getPublicKey";
    public static final String ADD_PUBKEY = "addPublicKey";
    public static final String SIGN = "sign";


    //Errors
    public static final String PORT_UNAVAIL = "is not available, likely because \nit's already being used by another " +
            "Java program running. \nClose all your server windows and try again.";

}
