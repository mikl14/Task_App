package com.example.test_ui;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class sql_connect extends AsyncTask<Void,Void,String> { //SELECT * FROM tasks


    @Override
    protected String doInBackground(Void... voids) {
        String str ;

        Socket s;
        DataOutputStream dos;
        PrintWriter pw;
        InputStreamReader inputRead ;
        BufferedReader bufread;
       // DataInputStream dIn ;
        try{
            str = "SELECT * FROM testbd"; //tasks
// Creating new socket connection to the IP (first parameter) and its opened port (second parameter)
            s = new Socket("62.140.233.174", 1337);
            inputRead = new InputStreamReader(s.getInputStream());
            bufread = new BufferedReader(inputRead);

            pw = new PrintWriter(s.getOutputStream());
         //   dIn = new DataInputStream(s.getInputStream());
            pw.write(str);

            pw.flush();
         //   str = dIn.readUTF();
            int character;
            StringBuilder output = new StringBuilder();
            int i = 10;
            while ((character = inputRead.read()) != '!') {

                output.append((char) character);
            }
            str = output.toString();



            pw.close();
         //   dIn.close();
            inputRead.close();
            s.close();
            System.out.println("ssssssssssssssssss");
            System.out.println(output);
// Initialize output stream to write message to the socket stream
            return str;
        }

        catch(Exception ex){
            System.out.println("ssssssssssssssssss");
            System.out.println(ex.toString());

            return "error";
        }

    }
    protected void onPostExecute(String result)
    {

    }
}
