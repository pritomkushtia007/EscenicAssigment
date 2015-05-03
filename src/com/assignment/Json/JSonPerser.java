package com.assignment.Json;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSonPerser {
	
	public static final String mFirstAdditionalStringForTextToJSon = "{\n\"book\": {"+"\n";
	public static final String mLastAdditionalStringForTextToJSon = " }\n}";

    private String  mFinalOutput;
    
    private Map<String, List<String>> mJsonMap;

    
    public JSonPerser() {
    	mFinalOutput = new String();  
    	mJsonMap = new HashMap<String, List<String>>();
	}

	public  String TextToJSon( ) {
		mFinalOutput = mFinalOutput + JSonPerser.mFirstAdditionalStringForTextToJSon;
		for(int k = 0; k<FileOperation.GetFileData().size();k++)
		{
			String sTempMapKey = new String();
			List<String> sTempMapValue = new ArrayList<String>();
	        String sOutputPersingBuffer = new String();
	        ArrayList<String> aList= new ArrayList<String>(Arrays.asList(FileOperation.GetFileData().get(k).split(":")));
	        for(int i=0;i<aList.size();i++)
	        {
	            if(i == 0){
	            	sTempMapKey = aList.get(i);
	                sOutputPersingBuffer = "\"";
	                mFinalOutput = mFinalOutput + sOutputPersingBuffer+((String) aList.get(i)+"\": ");
	            }else{
	                ArrayList<String> bList= new ArrayList<String>(Arrays.asList(((String) aList.get(i)).split(",")));
	                if(bList.size()>2){
	                	 mFinalOutput = mFinalOutput+"[";
	                    for(int j=0;j<bList.size();j++)
	                    {
	                    	sTempMapValue.add(bList.get(j));
	                        mFinalOutput = mFinalOutput + sOutputPersingBuffer+(((String) bList.get(j)));
	                        mFinalOutput = mFinalOutput+"\"";
	                        mFinalOutput = mFinalOutput.replace("\"\"" ,"\", \"");
	                        mFinalOutput = mFinalOutput.replace("\" " ,"\"");
	                    }
	                    mFinalOutput = mFinalOutput+"]\n";
	                }
	                else{
	                	sTempMapValue.add(aList.get(i));
	                    mFinalOutput = mFinalOutput + sOutputPersingBuffer+(((String) aList.get(i)).replaceAll("\\s","")+"\""+"\n");
	                }
	            }
	            mJsonMap.put(sTempMapKey, sTempMapValue);
	        }
		}
		for(String key: mJsonMap.keySet())
            System.out.println(key + " - " + mJsonMap.get(key));
		mFinalOutput = mFinalOutput + JSonPerser.mLastAdditionalStringForTextToJSon;

        return mFinalOutput;
    }
    
    
    
    public  String JsonToText(){
    
    	for(int k = 0; k<FileOperation.GetFileData().size();k++)
		{
	    	ArrayList<String> aList= new ArrayList<String>(Arrays.asList(FileOperation.GetFileData().get(k).split(":")));
	    	if(FileOperation.GetFileData().get(k).contains("{") ||FileOperation.GetFileData().get(k).contains("}"))
	    		continue;
	    	String sTempMapKey = new String();
			List<String> sTempMapValue = new ArrayList<String>();
	        for(int i=0;i<aList.size();i++)
	        {
	        	mFinalOutput = mFinalOutput+((String) aList.get(i));
	        	if(i == 0){
	        		sTempMapKey = aList.get(i).replace("\"", "");
	        		mFinalOutput = mFinalOutput+ ":";
	        	}
	        	else
	        	{
	        		String sTemp = aList.get(i).replace("[", "");
	        		sTemp = sTemp.replace("]", "");
	        		sTemp = sTemp.replace("\"", "");
	        		sTempMapValue.add(sTemp);
	        		
	        	}
	        	mFinalOutput = mFinalOutput.replace(" ","");
	        	mFinalOutput = mFinalOutput.replace(":",": ");
	        	mFinalOutput = mFinalOutput.replace(",",", ");
	        	mFinalOutput = mFinalOutput.replace("\"", "");
	        	
	        }
	        for(String key: mJsonMap.keySet())
	            System.out.println(key + " - " + mJsonMap.get(key));
	        mFinalOutput = mFinalOutput + "\n";
	        mFinalOutput = mFinalOutput.replace("[", "");
	        mFinalOutput = mFinalOutput.replace("]", "");
	        
	        mJsonMap.put(sTempMapKey, sTempMapValue);
		}
		
    	return mFinalOutput;
		
    }
    
   public String GetFinalOutput() {
	   return mFinalOutput;
}
   
   public Map<String, List<String>> GetJsonMap(){
	   return mJsonMap;
   }
}
