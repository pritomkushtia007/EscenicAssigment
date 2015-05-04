package com.assignment.Json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperation {

    private static ArrayList<String> mFileData; 
    private static FileOperation mFileOperation;
    
    public FileOperation() {
        mFileData = new ArrayList<>();
    }
    public void ReadFromFile(String fileName) {
         try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
         {
             String sCurrentLine;
             int i =0;
             while ((sCurrentLine = br.readLine()) != null) {
                 mFileData.add(i++,sCurrentLine);
             }
         } catch (IOException e) {
             e.printStackTrace();
         } 
    } 
    
    
     public void WriteToFile(String FinalOutput, String OutputFileName){
            
            String[] sWords = FinalOutput.split("\n");
            try {
                File file = new File(OutputFileName);
                FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
                BufferedWriter writer = new BufferedWriter(fw);
                for (String word: sWords) {
                    writer.write(word);
                    writer.newLine();
                }
                writer.close();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            
        }
     
     public static ArrayList<String> GetFileData() {
        return mFileData;
    }
     public static FileOperation GetInstance()
     {
    	 if(mFileOperation == null)
    		 return new FileOperation();
    	 return mFileOperation;
     }
}
