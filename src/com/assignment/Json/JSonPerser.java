package com.assignment.Json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class JSonPerser {
	
	public static final String mFirstAdditionalStringForTextToJSon = "{\n\"book\": {"+"\n";
	public static final String mLastAdditionalStringForTextToJSon = " }\n}";

    private String  mFinalOutput;

    
    public  int mLineNumber = 0;

    public JSonPerser() {
    	mFinalOutput = new String();    	 
	}

	public  String TextToJSon( ) {
		mFinalOutput = mFinalOutput + JSonPerser.mFirstAdditionalStringForTextToJSon;
		for(int k = 0; k<FileOperation.GetFileData().size();k++)
		{
	        String sOutputPersingBuffer = new String();
	        ArrayList<String> aList= new ArrayList<String>(Arrays.asList(FileOperation.GetFileData().get(k).split(":")));
	        for(int i=0;i<aList.size();i++)
	        {
	            if(i == 0){
	                sOutputPersingBuffer = "\"";
	                mFinalOutput = mFinalOutput + sOutputPersingBuffer+((String) aList.get(i)+"\": ");
	            }else{
	                ArrayList<String> bList= new ArrayList<String>(Arrays.asList(((String) aList.get(i)).split(",")));
	                if(bList.size()>=2){
	                	 mFinalOutput = mFinalOutput+"[";
	                    for(int j=0;j<bList.size();j++)
	                    {
	                        mFinalOutput = mFinalOutput + sOutputPersingBuffer+(((String) bList.get(j)));
	                        mFinalOutput = mFinalOutput+"\"";
	                        mFinalOutput = mFinalOutput.replace("\"\"" ,"\", \"");
	                        mFinalOutput = mFinalOutput.replace("\" " ,"\"");
	                    }
	                    mFinalOutput = mFinalOutput+"]\n";
	                }
	                else{
	                    mFinalOutput = mFinalOutput + sOutputPersingBuffer+(((String) aList.get(i)).replaceAll("\\s","")+"\""+"\n");
	                }
	            }
	        }
		}
		mFinalOutput = mFinalOutput + JSonPerser.mLastAdditionalStringForTextToJSon;
        return mFinalOutput;
    }
    
    
    
    public  String JsonToText(){
    
    	for(int k = 0; k<FileOperation.GetFileData().size();k++)
		{
	    	ArrayList<String> aList= new ArrayList<String>(Arrays.asList(FileOperation.GetFileData().get(k).split(":")));
	    	if(FileOperation.GetFileData().get(k).contains("{") ||FileOperation.GetFileData().get(k).contains("}"))
	    		continue;
	        for(int i=0;i<aList.size();i++)
	        {
	        	mFinalOutput = mFinalOutput+((String) aList.get(i));
	        	if(i == 0){
	        		mFinalOutput = mFinalOutput+ ":";
	        	}
	        	mFinalOutput = mFinalOutput.replace(" ","");
	        	mFinalOutput = mFinalOutput.replace(":",": ");
	        	mFinalOutput = mFinalOutput.replace(",",", ");
	        	mFinalOutput = mFinalOutput.replace("\"", "");
	        	
	        }
	        mFinalOutput = mFinalOutput + "\n";
	        mFinalOutput = mFinalOutput.replace("[", "");
	        mFinalOutput = mFinalOutput.replace("]", "");
		}
	    
    	return mFinalOutput;
		
    }
    
   public String GetFinalOutput() {
	   return mFinalOutput;
}
	
}
