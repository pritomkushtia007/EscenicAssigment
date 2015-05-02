package com.assignment.Json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileOperation {

    private boolean mTextToJnos;
    private boolean mJsonToText;
    private JSonPerser mJSonPerser;
    
    private static ArrayList<String> mFileData; 
    
    public FileOperation(JSonPerser jSonPerser) {
    	mJsonToText = false;
    	mJsonToText = false;
    	mJSonPerser = jSonPerser;
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
	
	
	 public static void WriteToFile(String FinalOutput){
	    	
	    	String[] words = FinalOutput.split("\n");
	    	try {
	            File file = new File("example.txt");
	            FileWriter fw = new FileWriter(file.getAbsoluteFile());
	            BufferedWriter writer = new BufferedWriter(fw);
	            for (String word: words) {
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
}
