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


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static PrintWriter logWriter;

    public static void initLogFile() throws IOException {
        logWriter = new PrintWriter(new FileWriter("Program7-Output.txt", true));
        logWriter.println("Program started.");
    }

    public static void closeLogFile() {
        if (logWriter != null) {
            logWriter.close();
        }
    }

    public static void logCorrectAnswer(String question, int answer) {
        logWriter.println("Correct Answer: " + question + " Your Answer: " + answer);
    }

    public static void logIncorrectAnswer(String question, int answer) {
        logWriter.println("Incorrect Answer: " + question + " Your Answer: " + answer);
    }
}
