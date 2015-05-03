package com.assignment;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Properties;

import com.assignment.Json.FileOperation;
import com.assignment.Json.JSonPerser;
 
public class Assignment {
    
public static FileOperation mFileOperation;
public static JSonPerser mJSonPerser;
    
    public static void main(String[] args) throws IOException {
    	
    	mJSonPerser = new JSonPerser();
    	mFileOperation  = new FileOperation(mJSonPerser);
    	mFileOperation.ReadFromFile("inputtext.txt");
    	mJSonPerser.TextToJSon();
//        mJSonPerser.JsonToText();
    	FileOperation.WriteToFile(mJSonPerser.GetFinalOutput(),"example.txt");
    	
    	
//    	try {
//			File file = new File("book-info-converter.properties");
//			FileInputStream fileInput = new FileInputStream(file);
//			Properties properties = new Properties();
//			properties.load(fileInput);
//			fileInput.close();
//
//			Enumeration enuKeys = properties.keys();
//			while (enuKeys.hasMoreElements()) {
//				String key = (String) enuKeys.nextElement();
//				String value = properties.getProperty(key);
//				System.out.println(key + ": " + value);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    	
    	
    }
    
    
    

}