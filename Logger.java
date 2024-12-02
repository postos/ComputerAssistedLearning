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

public class Logger {

    // method to generate a unique filename by incrementing a number
    public static String getUniqueFilename() {
        int fileIndex = 1;  // start with 1
        String filename = "Program7-Output_" + fileIndex + ".txt";
        File file = new File(filename);
        
        // increment the fileIndex until a unique filename is found
        while (file.exists()) {
            fileIndex++;
            filename = "Program7-Output_" + fileIndex + ".txt";
            file = new File(filename);
        }
        return filename;
    }

    // method to log a message to the unique file
    public static void log(String message) {
        // get the unique filename for the current run
        String filename = getUniqueFilename();

        // try-with-resources to handle file writing
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(message + "\n"); 
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
