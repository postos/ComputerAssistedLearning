//********************************************************************
//
//  Developer:     Paul Ostos
//
//  Program #:     7
//
//  File Name:     
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      12/11/2024
//
//  Instructor:    Prof. Fred Kumi 
//
//  Java Version:  "17.0.1" 2021-10-19 LTS
//
//  Description:   
//
//********************************************************************


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    public static PrintWriter logWriter;
    public static String logFileName = "Program7-Output"; 
    public static String currentLogFile; // current log file with index

    // ***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:     
    //
    // **************************************************************

    public static String getUniqueLogFileName() {
        int index = 1;  // Start with 1
        File file = new File(logFileName + "_" + index + ".txt");
        
        // increment the index until a unique filename is found
        while (file.exists()) {
            index++;
            file = new File(logFileName + "_" + index + ".txt");
        }

        return logFileName + "_" + index + ".txt";  // return the unique filename
    }

    // ***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:     
    //
    // **************************************************************

    public static void initLogFile(){
        try{
            // get a unique log file name
            currentLogFile = getUniqueLogFileName();
            logWriter = new PrintWriter(new FileWriter(currentLogFile, true)); // open in append mode
            logWriter.println("Program started.");
        } catch (IOException e){
            System.err.println("Error: Initialization failed.");
        }
    }

    // ***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:     
    //
    // **************************************************************

    public static void closeLogFile() {
        if (logWriter != null) {
            logWriter.close(); // close the file writer when done
        }
    }

    // ***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:     
    //
    // **************************************************************

    public static void logCorrectAnswer(String question, int answer) {
        if (logWriter != null) {
            logWriter.println("Correct Answer: " + question + " Your Answer: " + answer);
            logWriter.flush();
        }
    }

    // ***************************************************************
    //
    // Method:      
    //
    // Description: 
    //
    // Parameters:  
    //
    // Returns:     
    //
    // **************************************************************

    public static void logIncorrectAnswer(String question, int answer) {
        if (logWriter != null) {
            logWriter.println("Incorrect Answer: " + question + " Your Answer: " + answer);
            logWriter.flush();
        }
    }
}

