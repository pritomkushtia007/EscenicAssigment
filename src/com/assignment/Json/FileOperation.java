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

    /**
     * Constructor
     */
    private FileOperation() {
        mFileData = new ArrayList<>();
    }

    /**
     * Read content of a file
     * 
     * @param fileName
     *            name of the file
     * @return void
     */
    public void ReadFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String sCurrentLine;
            int i = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                mFileData.add(i++, sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write content to a text file
     * 
     * @param FinalOutput
     *            content to be written
     * @param OutputFileName
     *            name of the file
     * @return void
     */
    public void WriteToFile(String FinalOutput, String OutputFileName) {

        String[] sWords = FinalOutput.split("\n");
        try {
            File file = new File(OutputFileName);
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter writer = new BufferedWriter(fw);
            for (String word : sWords) {
                writer.write(word);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Get the File Data ArrayList
     * 
     * @return ArrayList
     */
    public static ArrayList<String> GetFileData() {
        return mFileData;
    }

    /**
     * Get the Singleton Object
     * 
     * @return FileOperation
     */
    public static FileOperation GetInstance() {
        if (mFileOperation == null)
            return new FileOperation();
        return mFileOperation;
    }
}
