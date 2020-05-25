package edu.cis.Utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class KeysDB {
    //instance variable
    private static Map<String , String > keysMap = new HashMap<String,String>();
    private String fileName;

    //Constructor
   public KeysDB(String fileName)
    {

        this.fileName=fileName;
        readFromFile();
    }

    //Member function
    public void addKey ( String  value)
    {
        String key = "A" + Integer.toString(keysMap.size()+1);
        keysMap.put(key,value);
    }
   public void writeToFile ()
    {
        try {
            BufferedWriter BW = new BufferedWriter(new FileWriter(fileName));
            for (Map.Entry<String,String> entry : keysMap.entrySet())
            {
                BW.write(entry.getKey()+ ", " + entry.getValue() + "\n" );
            }
              BW.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void  readFromFile()
    {
        try {
            Scanner scanner = new Scanner(new FileReader(fileName));
            while (scanner.hasNext())
            {
                String key = scanner.next();
                String value = scanner.nextLine();
                keysMap.put(key,value);
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getValue(String key)
    {

        return keysMap.get(key);
    }
}
