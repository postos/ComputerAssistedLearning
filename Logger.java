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

    private PrintWriter logWriter;

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
        String logFileName = "Program7-Output"; 

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
        String currentLogFile; // current log file with index
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

    public void closeLogFile() {
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

    public void logCorrectAnswer(String question, int answer) {
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

    public void logIncorrectAnswer(String question, int answer) {
        if (logWriter != null) {
            logWriter.println("Question: " + question);
            logWriter.println("Your Incorrect Answer: " + answer);
            logWriter.println("-----------------------------------");
            logWriter.flush();
        }
    }

    // ***************************************************************
    //
    // Method:      logContinueResponse
    //
    // Description: Logs the students response when they are asked if they 
    //              wish to continue.     
    //
    // Parameters:  int choice
    //              int level
    //
    // Returns:     None
    //
    // **************************************************************

    public void logContinueResponse(int choice, int level){
        if (level == 3) {
            logWriter.println("You have reached the most advanced level. Keep up the good work. Press (1 or 2) to continue or (0) to exit.");
            logWriter.println("Choice: " + choice);
            logWriter.println("-----------------------------------");
        } else {
            logWriter.println("Do you want to (1) Continue at this level, (2) Move to a more difficult level, (0) Exit?");
            logWriter.println("Choice: " + choice);
            logWriter.println("-----------------------------------");
        }
    }
}
