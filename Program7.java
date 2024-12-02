//********************************************************************
//
//  Developer:     Paul Ostos
//
//  Program #:     7
//
//  File Name:     Program7.java
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Program7{

    public static Scanner scanner = new Scanner(System.in);
    public static int correctBasic = 0;
    public static int totalQuestionsBasic = 0;
    public static int correctIntermediate = 0;
    public static int totalQuestionsIntermediate = 0;
    public static int correctAdvanced = 0;
    public static int totalQuestionsAdvanced = 0;

    // ***************************************************************
    //
    // Method:      main()
    //
    // Description: The main method of the program
    //
    // Parameters:  String array
    //
    // Returns:     N/A
    //
    // **************************************************************

    public static void main(String[] args) {

        Program7 obj = new Program7();
        obj.developerInfo();
        obj.initiateLearning(); 

    }

    // ***************************************************************
    //
    // Method:      developerInfo (Non Static)
    //
    // Description: The developer information method of the program
    //
    // Parameters:  None
    //
    // Returns:     N/A
    //
    // **************************************************************

    public void developerInfo() {
        System.out.println("*****************************************");
        System.out.println("Name:     Paul Ostos");
        System.out.println("Course:   COSC 4301 Modern Programming");
        System.out.println("Program:  7");
        System.out.println("Due Date: 12/11/2024");
        System.out.println("*****************************************\n");

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

    public void startProgram() {
        try {
            Logger.initLogFile();
            initiateLearning(); // Start the program logic
        } catch (IOException e) {
            System.out.println("Error initializing log file.");
            e.printStackTrace();
        } finally {
            Logger.closeLogFile();
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

    public static void initiateLearning() {
        int level = 1; // Always start at the basic level
        while (level > 0 && level <= 3) {
            level = playLevel(level); // Play the current level and return the next level (or 0 if exit)
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

    public static int playLevel(int level) {
        int correctAnswersInRow = 0;
        int totalQuestions = 0;
    
        while (correctAnswersInRow < 5) { // Need 5 correct answers in a row to proceed
            totalQuestions++;
            String question = QuestionGenerator.generateQuestion(level);
            System.out.println("Question: " + question);
            int correctAnswer = QuestionGenerator.evaluateQuestion(question);
    
            boolean correct = false; // Flag to track if the answer is correct
            while (!correct) {
                System.out.print("Your answer: ");
                int studentAnswer = getAnswer();
    
                if (studentAnswer == correctAnswer) {
                    Logger.logCorrectAnswer(question, studentAnswer);
                    ResponseHandler.printCorrectResponse();
                    correctAnswersInRow++;
                    correct = true; // Correct answer, exit the loop
                } else {
                    Logger.logIncorrectAnswer(question, studentAnswer);
                    ResponseHandler.printIncorrectResponse();
                    correctAnswersInRow = 0; // Reset streak if the answer is incorrect
                    // We don't generate a new question; it will be the same question
                    System.out.println("Question: " + question); // Show the same question again
                }
            }
    
            totalQuestions++;
        }
    
        updateCorrectAnswers(level, correctAnswersInRow);
        updateTotalQuestions(level, totalQuestions);
    
        return askLevelChoice(level);
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

    public static int getAnswer() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer.");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
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

    public static void updateCorrectAnswers(int level, int correctAnswers) {
        if (level == 1) correctBasic += correctAnswers;
        else if (level == 2) correctIntermediate += correctAnswers;
        else if (level == 3) correctAdvanced += correctAnswers;
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

    public static void updateTotalQuestions(int level, int totalQuestions) {
        if (level == 1) totalQuestionsBasic += totalQuestions;
        else if (level == 2) totalQuestionsIntermediate += totalQuestions;
        else if (level == 3) totalQuestionsAdvanced += totalQuestions;
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

    public static int askLevelChoice(int level) {
        System.out.println("Do you want to (1) Continue at this level, (2) Move to a more difficult level, (3) Exit?");
        int choice = getAnswer();
        if (choice == 1) {
            return level;
        } else if (choice == 2) {
            return level + 1; // Move to the next level
        } else {
            showSummary();
            return 0; // Exit the program
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

    public static void showSummary() {
        System.out.println("Summary of your performance:");
        System.out.println("Basic Level: " + correctBasic + "/" + totalQuestionsBasic + " (" + getPercentage(correctBasic, totalQuestionsBasic) + "% correct)");
        System.out.println("Intermediate Level: " + correctIntermediate + "/" + totalQuestionsIntermediate + " (" + getPercentage(correctIntermediate, totalQuestionsIntermediate) + "% correct)");
        System.out.println("Advanced Level: " + correctAdvanced + "/" + totalQuestionsAdvanced + " (" + getPercentage(correctAdvanced, totalQuestionsAdvanced) + "% correct)");

        if (getPercentage(correctBasic, totalQuestionsBasic) < 80) {
            System.out.println("Please ask your teacher for extra help.");
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

    public static double getPercentage(int correct, int total) {
        return total == 0 ? 0.0 : ((double) correct / total) * 100;
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



    
}
   