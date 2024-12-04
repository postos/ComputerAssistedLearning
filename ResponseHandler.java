//********************************************************************
//
//  Developer:     Paul Ostos
//
//  Program #:     7
//
//  File Name:     ResponseHandler.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      12/11/2024
//
//  Instructor:    Prof. Fred Kumi 
//
//  Java Version:  "17.0.1" 2021-10-19 LTS
//
//  Description:   This class provides functionality for handling user responses 
//                 during the program execution. It randomly selects and prints 
//                 motivational messages for both correct and incorrect answers.
//
//********************************************************************

import java.util.Random;

public class ResponseHandler {
    private final Random random = new Random();

    // ***************************************************************
    //
    // Method:      printCorrectResponse
    //
    // Description: Prints a random motivational response for a correct answer. 
    //              Responses are chosen from a predefined set of encouraging messages.
    //
    // Parameters:  None
    //
    // Returns:     None
    //
    // ***************************************************************

    public void printCorrectResponse() {
        String[] responses = {
            "Excellent!", "Very good!", "Nice work!", 
            "Way to go!", "Keep up the good work!"
        };
        System.out.println(responses[random.nextInt(responses.length)]);
    }

    // ***************************************************************
    //
    // Method:      printIncorrectResponse
    //
    // Description: Prints a random motivational response for an incorrect answer. 
    //              Responses are chosen from a predefined set of encouraging retry messages.
    //
    // Parameters:  None
    //
    // Returns:     None
    //
    // ***************************************************************

    public void printIncorrectResponse() {
        String[] responses = {
            "That is incorrect!", "No. Please try again!", 
            "Wrong, Try once more!", "No. Don't give up!", 
            "Incorrect. Keep trying!"
        };
        System.out.println(responses[random.nextInt(responses.length)]);
    }
}
