//********************************************************************
//
//  Developer:     Paul Ostos
//
//  Program #:     7
//
//  File Name:     ArithmeticOperations.java
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

import java.security.SecureRandom;

public class ArithmeticOperations{

    public static SecureRandom random = new SecureRandom(); 

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

    public static String basicLevelDifficulty(){
        System.out.println("Basic expression: ");

        int num1 = random.nextInt(9) + 1; // random positive one-digit integer between 1 and 9
        int num2 = random.nextInt(9) + 1; // random positive one-digit integer between 1 and 9
        String operator = getRandomOperator(); 

        String basicLevelQuestion = num1 + " " + operator + " " + num2; 

        return basicLevelQuestion; 

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

    public static String intermediateLevelDifficulty(){
        System.out.println("Intermediate expression: ");

        int num1 = random.nextInt(9) + 1; // random positive one-digit integer between 1 and 9
        int num2 = random.nextInt(9) + 1; // random positive one-digit integer between 1 and 9
        int num3 = random.nextInt(9) + 1; // random positive one-digit integer between 1 and 9

        String operator1 = getRandomOperator(); 
        String operator2 = getRandomOperator(); 

        String intermediateLevelQuestion = num1 + " " + operator1 + " " + num2 + " " + operator2 + " " + num3; 

        return intermediateLevelQuestion; 

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

    public static String advancedLevelDifficulty(){
        System.out.println("Advanced expression: ");

        int num1 = random.nextInt(9) + 1; // random positive one-digit integer between 1 and 9
        int num2 = random.nextInt(9) + 1; // random positive one-digit integer between 1 and 9
        int num3 = random.nextInt(9) + 1; // random positive one-digit integer between 1 and 9
        int num4 = random.nextInt(9) + 1; // random positive one-digit integer between 1 and 9

        String operator1 = getRandomOperator(); 
        String operator2 = getRandomOperator(); 
        String operator3 = getRandomOperator(); 

        String advancedLevelQuestion = num1 + " " + operator1 + " " + num2 + " " + operator2 + " " + num3 + " " + operator3 + " " + num4; 

        return advancedLevelQuestion; 
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

    public static String getRandomOperator(){
        int operator = random.nextInt(4);
        switch (operator){
            case 0: 
                return "+"; 
            case 1: 
                return "-"; 
            case 2: 
                return "*";
            case 3: 
                return "%";

            default: return "+"; 
        }
    }
}