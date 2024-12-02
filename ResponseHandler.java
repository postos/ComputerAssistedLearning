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

import java.util.Random;

public class ResponseHandler {
    private static final Random random = new Random();

    public static void printCorrectResponse() {
        String[] responses = {
            "Excellent!", "Very good!", "Nice work!", 
            "Way to go!", "Keep up the good work!"
        };
        System.out.println(responses[random.nextInt(responses.length)]);
    }

    public static void printIncorrectResponse() {
        String[] responses = {
            "That is incorrect!", "No. Please try again!", 
            "Wrong, Try once more!", "No. Don't give up!", 
            "Incorrect. Keep trying!"
        };
        System.out.println(responses[random.nextInt(responses.length)]);
    }
}
