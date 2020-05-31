package edu.cis.temp;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        String s = "dog";
            byte[] array=s.getBytes();
        StringBuffer retString = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            retString.append(Integer.toHexString(0x0100 + (array[i] & 0x00FF)).substring(1));
        }
//Now you can distribute this public key string
        String out =retString.toString();
        System.out.println(out);
        byte [] a2= new byte[out.length()/2];
        for (int i=0 ;i <a2.length;i++)
        {
            a2[i]= Byte.parseByte(out.substring(2*i,2*i+2),16);
        }
        System.out.println(Arrays.toString(a2));

    }
}
