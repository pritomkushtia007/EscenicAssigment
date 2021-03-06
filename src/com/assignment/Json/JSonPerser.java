package com.assignment.Json;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
    
public class JSonPerser {

    public static final String FIRSTADDITIONALCHARECTER = "{\n\"book\": {"
            + "\n";
    public static final String LASTADDITIONALCHARECTER = " }\n}";

    private String mFinalOutput;

    private Map<String, List<String>> mJsonMap;

    /**
     * Constructor
     */
    public JSonPerser() {
        mFinalOutput = new String();
        mJsonMap = new HashMap<String, List<String>>();
    }

    /**
     * Text to Json
     * 
     * @return String
     */
    public String TextToJSon() {
        mFinalOutput = mFinalOutput + JSonPerser.FIRSTADDITIONALCHARECTER; // added
                                                                            // additional
                                                                            // character
                                                                            // initially
                                                                            // for
                                                                            // JSON
        for (int k = 0; k < FileOperation.GetFileData().size(); k++) {
            String sTempMapKey = new String();
            List<String> sTempMapValue = new ArrayList<String>();
            String sOutputPersingBuffer = new String();
            ArrayList<String> aList = new ArrayList<String>(
                    Arrays.asList(FileOperation.GetFileData().get(k).split(":")));
            for (int i = 0; i < aList.size(); i++) {
                if (i == 0) {
                    sTempMapKey = aList.get(i);
                    sOutputPersingBuffer = "\"";
                    mFinalOutput = mFinalOutput + sOutputPersingBuffer
                            + ((String) aList.get(i) + "\": ");
                } else {
                    ArrayList<String> bList = new ArrayList<String>(
                            Arrays.asList(((String) aList.get(i)).split(",")));
                    if (bList.size() > 2) {
                        mFinalOutput = mFinalOutput + "[";
                        for (int j = 0; j < bList.size(); j++) {
                            sTempMapValue.add(bList.get(j));
                            mFinalOutput = mFinalOutput + sOutputPersingBuffer
                                    + (((String) bList.get(j)));
                            mFinalOutput = mFinalOutput + "\"";
                            mFinalOutput = mFinalOutput.replace("\"\"",
                                    "\", \"");
                            mFinalOutput = mFinalOutput.replace("\" ", "\"");
                        }
                        mFinalOutput = mFinalOutput + "]\n";
                    } else {
                        sTempMapValue.add(aList.get(i));
                        mFinalOutput = mFinalOutput
                                + sOutputPersingBuffer
                                + (((String) aList.get(i))
                                        .replaceAll("\\s", "") + "\"" + "\n"); // removing
                                                                                // space
                    }
                }
                mJsonMap.put(sTempMapKey, sTempMapValue);
            }
        }
        mFinalOutput = mFinalOutput + JSonPerser.LASTADDITIONALCHARECTER; // added
                                                                            // additional
                                                                            // character
                                                                            // lately
                                                                            // for
                                                                            // JSON

        return mFinalOutput;
    }

    /**
     * Json to Text
     * 
     * @return String
     */
    public String JsonToText() {
        for (int k = 0; k < FileOperation.GetFileData().size(); k++) {
            ArrayList<String> aList = new ArrayList<String>(
                    Arrays.asList(FileOperation.GetFileData().get(k).split(":")));
            if (FileOperation.GetFileData().get(k).contains("{")
                    || FileOperation.GetFileData().get(k).contains("}"))
                continue;
            String sTempMapKey = new String();
            List<String> sTempMapValue = new ArrayList<String>();
            for (int i = 0; i < aList.size(); i++) {
                mFinalOutput = mFinalOutput + ((String) aList.get(i));
                if (i == 0) {
                    sTempMapKey = aList.get(i).replace("\"", "");
                    mFinalOutput = mFinalOutput + ":";
                } else {
                    String sTemp = aList.get(i).replace("[", "");
                    sTemp = sTemp.replace("]", "");
                    sTemp = sTemp.replace("\"", "");
                    sTempMapValue.add(sTemp);

                }
                mFinalOutput = mFinalOutput.replace(" ", "");
                mFinalOutput = mFinalOutput.replace(":", ": ");
                mFinalOutput = mFinalOutput.replace(",", ", ");
                mFinalOutput = mFinalOutput.replace("\"", "");

            }
            mFinalOutput = mFinalOutput + "\n";
            mFinalOutput = mFinalOutput.replace("[", "");
            mFinalOutput = mFinalOutput.replace("]", "");

            mJsonMap.put(sTempMapKey, sTempMapValue);
        }

        return mFinalOutput;

    }

    /**
     * Get the final 
     *        Output String
     * 
     * @return String
     */
    public String GetFinalOutput() {
        return mFinalOutput;
    }

    /**
     * Get the Json Map
     * 
     * @return Map
     */
    public Map<String, List<String>> GetJsonMap() {
        return mJsonMap;
    }
}
