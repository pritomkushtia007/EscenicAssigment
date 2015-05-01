package com.assignment;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

import com.assignment.Json.FileOperation;
import com.assignment.Json.JSonPerser;
 
public class Assignment {
    
public static FileOperation mFileOperation;
public static JSonPerser mJSonPerser;
    
    public static void main(String[] args) {
 
    	
    	mJSonPerser = new JSonPerser();
    	mFileOperation  = new FileOperation(mJSonPerser);
    	mFileOperation.ReadFromFile("inputjson.txt");
    }
    
    
    

}