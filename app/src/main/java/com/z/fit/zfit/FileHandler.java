package com.z.fit.zfit;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Zacc on 2016-05-05.
 */
public class FileHandler {
    String filename = "zFitLevel.txt";
    Context context;
    FileHandler(Context c){
        context = c;
    }
    public void incrementLevel()  {
        writeToFile(String.valueOf(getLevel() + 1));
    }
    public int getLevel(){
        return Integer.valueOf(readFromFile().get(0));
    }

    private void writeToFile(String data) {
        System.out.println("Beginning method writeToFile()");
        try {
            File file = new File(context.getFilesDir(),filename);
            if (!file.exists()) {
                createFile();
            }
                FileWriter fOut = new FileWriter(file);
                fOut.write(data);
                fOut.close();
            System.out.println("Successfully wrote to file");

        }
        catch (IOException e) {
            System.out.println("Failure..1.");
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void createFile(){
        System.out.println("Beginning method createFile()");
        File file = new File(context.getFilesDir(), filename);
        try {
            file.createNewFile();
            System.out.println("Successfully created file");
        }catch(Exception e){
            System.out.println("Failure..2.");
            Log.e("Exception", "File Create Failed: " + e.toString());
        }
    }

    private ArrayList<String> readFromFile() {
        System.out.println("Beginning method readFromFile()");
        ArrayList<String> sReturn = new ArrayList<>();

        try {
            File file = new File(context.getFilesDir(),filename);
            if (!file.exists()) {
                writeToFile("0");
            }
            FileReader fIn = new FileReader(file);
            BufferedReader textReader = new BufferedReader(fIn);
            String sNextLine;
            while((sNextLine = textReader.readLine()) != null){
                sReturn.add(sNextLine);
            }
            System.out.println("Successfully read from file {" + sReturn.get(0) +"}");
        }
        catch (Exception e) {
            System.out.println("Failure..3.");
            Log.e("Exception", "File Read Failed: " + e.toString());
        }

        return sReturn;
    }
}