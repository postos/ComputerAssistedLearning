//********************************************************************
//
//  Developer:     Paul Ostos
//
//  Program #:     7
//
//  File Name:     Logger.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      12/11/2024
//
//  Instructor:    Prof. Fred Kumi 
//
//  Java Version:  "17.0.1" 2021-10-19 LTS
//
//  Description:   This class manages logging for the program, creating unique log files 
//                 and recording both correct and incorrect answers to questions.
//                 It also ensures proper file handling and prevents overwriting existing logs.
//
//********************************************************************

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    public static PrintWriter logWriter;
    public String logFileName = "Program7-Output"; 
    public String currentLogFile; // current log file with index

    // ***************************************************************
    //
    // Method:      getUniqueLogFileName
    //
    // Description: Generates a unique file name for the log file by appending an index to 
    //              the base name. Ensures no existing file is overwritten.
    //
    // Parameters:  None
    //
    // Returns:     A String representing the unique log file name.
    //
    // **************************************************************

    public String getUniqueLogFileName() {
        int index = 1;  // start with 1
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
    // Method:      initLogFile
    //
    // Description: Initializes the log file by creating a unique file name and opening a 
    //              PrintWriter in append mode. Writes an initial message indicating the program has started.
    //
    // Parameters:  None
    //
    // Returns:     None
    //
    // **************************************************************

    public void initLogFile(){
        try{
            // get a unique log file name
            currentLogFile = getUniqueLogFileName();
            logWriter = new PrintWriter(new FileWriter(currentLogFile, true)); // open in append mode
            logWriter.println("Program started...\n");
        } catch (IOException e){
            System.err.println("Error: Initialization failed.");
        }
    }

    // ***************************************************************
    //
    // Method:      closeLogFile
    //
    // Description: Closes the log file if it is open, ensuring all pending data is written 
    //              and resources are released properly.
    //
    // Parameters:  None
    //
    // Returns:     None
    //
    // **************************************************************

    public static void closeLogFile() {
        if (logWriter != null) {
            logWriter.close(); // close the file writer when done
        }
    }

    // ***************************************************************
    //
    // Method:      logCorrectAnswer
    //
    // Description: Logs a correctly answered question and the user's answer to the log file. 
    //              Appends the information with formatting for clarity.
    //
    // Parameters:  question - A String representing the question.
    //              answer - An integer representing the correct answer provided by the user.
    //
    // Returns:     None
    //
    // **************************************************************

    public static void logCorrectAnswer(String question, int answer) {
        if (logWriter != null) {
            logWriter.println("Question: " + question);
            logWriter.println("Your Correct Answer: " + answer);
            logWriter.println("-----------------------------------");
            logWriter.flush();
        }
    }

    // ***************************************************************
    //
    // Method:      logIncorrectAnswer
    //
    // Description: Logs an incorrectly answered question and the user's answer to the log file. 
    //              Appends the information with formatting for clarity.
    //
    // Parameters:  question - A String representing the question.
    //              answer - An integer representing the incorrect answer provided by the user.
    //
    // Returns:     None
    //
    // **************************************************************

    public static void logIncorrectAnswer(String question, int answer) {
        if (logWriter != null) {
            logWriter.println("Question: " + question);
            logWriter.println("Your Incorrect Answer: " + answer);
            logWriter.println("-----------------------------------");
            logWriter.flush();
        }
    }
}
