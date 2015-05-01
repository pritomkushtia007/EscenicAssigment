package com.assignment.Json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {

    public boolean mTextToJnos;
    public boolean mJsonToText;
    private JSonPerser mJSonPerser;
    
    public FileOperation(JSonPerser jSonPerser) {
    	mJsonToText = false;
    	mJsonToText = false;
    	mJSonPerser = jSonPerser;
	}
	public void ReadFromFile(String fileName) {
		 try (BufferedReader br = new BufferedReader(new FileReader(fileName)))//"inputtext.txt")))
         {
             String sCurrentLine;
             
             while ((sCurrentLine = br.readLine()) != null) {
                 if(!mJsonToText && !mTextToJnos && !sCurrentLine.equals("{")){
                     mTextToJnos = true;
                     mJsonToText = false;
                     mJSonPerser.mFinalOutput = mJSonPerser.mFinalOutput + JSonPerser.mFirstAdditionalStringForTextToJSon;
                     }
                 if(!mJsonToText && !mTextToJnos && sCurrentLine.equals("{")){
                     mTextToJnos = false;
                     mJsonToText = true;
                     }
                     
                 if(mJsonToText){
                	 mJSonPerser.JsonToText(sCurrentLine);
                     }
                
                 if(mTextToJnos){
                	 mJSonPerser.TextToJSon(sCurrentLine);
                 }
                 
             }
             if(mTextToJnos)
            	 mJSonPerser.mFinalOutput = mJSonPerser.mFinalOutput + JSonPerser.mLastAdditionalStringForTextToJSon;
             System.out.println(mJSonPerser.mFinalOutput);
             WriteToFile(mJSonPerser.mFinalOutput);
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
}
