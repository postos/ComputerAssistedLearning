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

public class Program7 {

    public static Scanner scanner = new Scanner(System.in);
    public static int correctBasic = 0;
    public static int totalQuestionsBasic = 0;
    public static int correctIntermediate = 0;
    public static int totalQuestionsIntermediate = 0;
    public static int correctAdvanced = 0;
    public static int totalQuestionsAdvanced = 0;

    // ***************************************************************
    //
    // Method: main()
    //
    // Description: The main method of the program
    //
    // Parameters: String array
    //
    // Returns: N/A
    //
    // **************************************************************

    public static void main(String[] args) {

        Program7 obj = new Program7();
        obj.developerInfo();
        obj.initiateLearning();

    }

    // ***************************************************************
    //
    // Method: developerInfo (Non Static)
    //
    // Description: The developer information method of the program
    //
    // Parameters: None
    //
    // Returns: N/A
    //
    // **************************************************************

    public void developerInfo() {
        System.out.println("*****************************************");
        System.out.println("Name:     Paul Ostos");
        System.out.println("Course:   COSC 4301 Modern Programming");
        System.out.println("Program:  7");
        System.out.println("Due Date: 12/11/2024");
        System.out.println("*****************************************");

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
        Logger.initLogFile();
        int level = 1; // always start at the basic level
        while (level > 0 && level <= 3) {
            level = playLevel(level); // play the current level and return the next level (or 0 to exit)
        }
        System.out.println("\n** GAME OVER **");
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
        int previousLevel = level; // Track the previous level
        
        while (true) {
            totalQuestions++;
            String question = generateAndAskQuestion(level);  // Get the question for the current level
            int correctAnswer = QuestionGenerator.evaluateQuestion(question);
    
            boolean correct = handleStudentAnswer(question, correctAnswer);
    
            if (correct) {
                correctAnswersInRow++;
                handleCorrectAnswer(level, correctAnswersInRow);
            } else {
                correctAnswersInRow = 0;  // Reset streak on wrong answer
                updateTotalQuestions(level, 1);  // Still increment total questions for incorrect answer
            }
    
            if (shouldProgressLevel(correctAnswersInRow)) {
                level = handleLevelProgression(correctAnswersInRow, level);
                if (level == 0) {
                    return 0;  // Exit game
                }
                correctAnswersInRow = resetStreakIfNeeded(level, previousLevel, correctAnswersInRow);
                previousLevel = level;  // Update previous level to current level
            }
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
    
    private static boolean handleStudentAnswer(String question, int correctAnswer) {
        boolean correct = false;
        while (!correct) {
            System.out.print(">>> ");
            int studentAnswer = getAnswer();  // Get the student's answer
            correct = handleAnswer(studentAnswer, correctAnswer, question);  // Check if correct
        }
        return correct;
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
    
    private static void handleCorrectAnswer(int level, int correctAnswersInRow) {
        System.out.println("Correct answers in a row: " + correctAnswersInRow);
        updateCorrectAnswers(level, 1);  // Update correct answers count
        updateTotalQuestions(level, 1);  // Update total questions count
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
    
    private static boolean shouldProgressLevel(int correctAnswersInRow) {
        return correctAnswersInRow >= 2;  // Only check progression after answering 2 correct questions
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
    
    private static int resetStreakIfNeeded(int level, int previousLevel, int correctAnswersInRow) {
        // Reset streak if level has changed
        return (level != previousLevel) ? 0 : correctAnswersInRow;
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
    
    private static String generateAndAskQuestion(int level) {
        String question = QuestionGenerator.generateQuestion(level);
        System.out.println("\nQuestion: " + question);
        return question;
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

    private static boolean handleAnswer(int studentAnswer, int correctAnswer, String question) {
        boolean isCorrect;

        if (studentAnswer == correctAnswer) {
            Logger.logCorrectAnswer(question, studentAnswer);
            ResponseHandler.printCorrectResponse();
            isCorrect = true;
        } else {
            Logger.logIncorrectAnswer(question, studentAnswer);
            ResponseHandler.printIncorrectResponse();
            System.out.println("Question: " + question);
            isCorrect = false;
        }
        return isCorrect; 
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

    private static int handleLevelProgression(int correctAnswersInRow, int level) {
        if (correctAnswersInRow >= 2) {
            int choice = askLevelChoice(level);
            if (choice == level) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Continuing at *** Level " + level + " ***");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (choice == level + 1) {
                level = choice; // Update level here when moving up
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Moving to *** Level " + level + " ***");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (choice == 0) {
                return 0; // Exit the program
            }
        }
        return level; // Return updated level or the same level
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
        if (level == 1)
            correctBasic += correctAnswers;
        else if (level == 2)
            correctIntermediate += correctAnswers;
        else if (level == 3)
            correctAdvanced += correctAnswers;
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
        if (level == 1)
            totalQuestionsBasic += totalQuestions;
        else if (level == 2)
            totalQuestionsIntermediate += totalQuestions;
        else if (level == 3)
            totalQuestionsAdvanced += totalQuestions;
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

        while (true) {
            if (level == 3) {
                System.out.println("You have reached the most advanced level. Keep up the good work. Press (1) to continue or (0) to exit.");
            } else {
                System.out.println("\nDo you want to (1) Continue at this level, (2) Move to a more difficult level, (0) Exit?");
            }
            int choice = getAnswer(); // get user input

            switch (choice) {
                case 1:
                    return level; // stay at the current level
                case 2:
                    if (level < 3) {
                        return level + 1; // move to the next level
                    } else {
                        System.out.println("You are already at the most advanced level.");
                        return level; // max level reached
                    }
                case 0:
                    showSummary(); 
                    Logger.closeLogFile();
                    return 0; // exit the program
                default:
                    System.out.println("Invalid entry. Please enter an option from above.");
            }
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
        System.out.println("\nPERFORMANCE SUMMARY: ");
        System.out.println("---------------------------------");
        System.out.println("Basic Level: " + correctBasic + "/" + totalQuestionsBasic + " ("
                + getPercentage(correctBasic, totalQuestionsBasic) + "% correct)");
        System.out.println("Intermediate Level: " + correctIntermediate + "/" + totalQuestionsIntermediate + " ("
                + getPercentage(correctIntermediate, totalQuestionsIntermediate) + "% correct)");
        System.out.println("Advanced Level: " + correctAdvanced + "/" + totalQuestionsAdvanced + " ("
                + getPercentage(correctAdvanced, totalQuestionsAdvanced) + "% correct)");

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

    public static int getPercentage(int correct, int total) {
        if (total == 0) return 0; // avoid division by zero
        return (int) ((correct * 100.0) / total);
    }
}




// public static int playLevel(int level) {
    //     int correctAnswersInRow = 0;
    //     int totalQuestions = 0;
    
    //     while (true) {
    //         totalQuestions++;
    //         String question = generateAndAskQuestion(level);  // Get the question for the current level
    //         int correctAnswer = QuestionGenerator.evaluateQuestion(question);
    
    //         boolean correct = false;
    //         while (!correct) {
    //             System.out.print(">>> ");
    //             int studentAnswer = getAnswer();  // Get the student's answer
    
    //             correct = handleAnswer(studentAnswer, correctAnswer, question);
    //             if (correct) {
    //                 correctAnswersInRow++;  // Increment correct answers streak
    //                 System.out.println("Correct answers in a row: " + correctAnswersInRow);
    //                 updateCorrectAnswers(level, 1);  // Update correct answers count
    //                 updateTotalQuestions(level, 1);  // Update total questions count
    
    //                 // Only check progression after answering 2 correct questions
    //                 if (correctAnswersInRow >= 2) {
    //                     int previousLevel = level;  // Track the current level before progression
    //                     level = handleLevelProgression(correctAnswersInRow, level);
    //                     if (level == 0) {
    //                         return 0;  // Exit the game if level 0 is chosen
    //                     }
    
    //                     // Only reset the streak if the level has actually changed
    //                     if (level != previousLevel) {
    //                         correctAnswersInRow = 0;  // Reset streak only if level has changed
    //                     }
    //                 }
    //             } else {
    //                 correctAnswersInRow = 0;  // Reset streak if incorrect answer
    //                 updateTotalQuestions(level, 1);  // Increment total questions count even for incorrect answers
    //             }
    //         }
    //     }
    // }





 // public static int playLevel(int level) {
    //     int correctAnswersInRow = 0;
    //     int totalQuestions = 0;

    //     while (true) { // keep asking questions until the user decides to exit
    //         totalQuestions++;
    //         String question = QuestionGenerator.generateQuestion(level);
    //         System.out.println("\nQuestion: " + question);
    //         int correctAnswer = QuestionGenerator.evaluateQuestion(question);

    //         boolean correct = false; // flag to track if the answer is correct
    //         while (!correct) {
    //             System.out.print(">>> ");
    //             int studentAnswer = getAnswer();

    //             if (studentAnswer == correctAnswer) {
    //                 Logger.logCorrectAnswer(question, studentAnswer); // log correct response
    //                 ResponseHandler.printCorrectResponse();
    //                 correctAnswersInRow++;
    //                 System.out.println("Correct answers in a row: " + correctAnswersInRow);
    //                 correct = true; // correct answer, exit the inner loop

    //                 // update counters for correct answers and total questions
    //                 updateCorrectAnswers(level, 1); // increment correct answer count
    //                 updateTotalQuestions(level, 1); // increment total questions count

    //                 if (correctAnswersInRow >= 2) {
    //                     int choice = askLevelChoice(level); // get user's choice
    //                     if (choice == level) {
    //                         // stay at the same level
    //                         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    //                         System.out.println("Continuing at *** Level " + level + " ***");
    //                         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    //                     } else if (choice == level + 1) {
    //                         // m+ove to the next level
    //                         level = choice; // set to the next level
    //                         correctAnswersInRow = 0; // reset streak for the new level
    //                         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    //                         System.out.println("Moving to *** Level " + level + " ***");
    //                         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

    //                     } else if (choice == 0) {
    //                         return choice; // exit the program
    //                     }
    //                 }
    //             } else {
    //                 Logger.logIncorrectAnswer(question, studentAnswer); // log incorrect response
    //                 ResponseHandler.printIncorrectResponse();
    //                 correctAnswersInRow = 0; // reset streak if the answer is incorrect
    //                 System.out.println("Question: " + question); // show the same question again

    //                 // update total questions count even on incorrect answers
    //                 updateTotalQuestions(level, 1); // increment total questions count
    //             }
    //         }
    //     }
    // }
