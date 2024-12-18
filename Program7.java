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
//  Description:    This program is a learning game with three difficulty levels. 
//                  It tracks the player's correct answers, manages level progression, 
//                  and provides a performance summary. The game advises the player 
//                  to seek help if their performance is below 80% on the basic level.
//
//********************************************************************

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Program7 {

    private Scanner scanner = new Scanner(System.in);
    private Logger logger = new Logger(); 
    private int correctBasic = 0;
    private int totalQuestionsBasic = 0;
    private int correctIntermediate = 0;
    private int totalQuestionsIntermediate = 0;
    private int correctAdvanced = 0;
    private int totalQuestionsAdvanced = 0;
    private int level = 1; // always start at basic level 

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
        System.out.println("*****************************************");

    }

    // ***************************************************************
    //
    // Method:      initiateLearning
    //
    // Description: Initializes the game, starts at level 1, and manages
    //              the game flow until the player exits or completes all levels.
    //
    // Parameters:   None
    //
    // Returns:      None
    //
    // ***************************************************************

    public void initiateLearning() {
        logger.initLogFile();
        
        while (level > 0 && level <= 3) {
            level = playLevel(); // play the current level and return the next level (or 0 to exit)
        }
        System.out.println("\n** GAME OVER **");
    }

    // ***************************************************************
    //
    // Method:      playLevel
    //
    // Description: Handles game play for a specific level, tracks correct
    //              answers, progress, and manages level progression or exit.
    //
    // Parameters:  int level - the current level of the game
    //
    // Returns:     int - the next level or 0 to exit the game
    //
    // ***************************************************************

    public int playLevel() {
        int correctAnswersInRow = 0;
        boolean gameRunning = true;
    
        while (gameRunning) {
            String question = generateAndAskQuestion(); // generate and display a question
            int correctAnswer = QuestionGenerator.evaluateQuestion(question); // get correct answer for the question
            boolean gotCorrectOnFirstTry = processStudentAnswers(correctAnswer, question);
    
            if (gotCorrectOnFirstTry) {
                correctAnswersInRow++;
                System.out.println("Level " + level + " Streak: " + correctAnswersInRow);
                
                updateCorrectAnswers(1); // update counter

                int previousLevel = level; // track current level before checking progression
                gameRunning = handleStreak(correctAnswersInRow); // returns true to keep playing/false to jump out of loop
    
                if (level != previousLevel) {
                    correctAnswersInRow = 0; // reset streak on level change
                }
            } else {
                correctAnswersInRow = 1; // restart streak with the first correct answer after an incorrect attempt
                System.out.println("Level " + level + " Streak: " + correctAnswersInRow);
            }
        }
        return 0; // exit game if gameRunning returns false
    }

    // ***************************************************************
    //
    // Method:      generateAndAskQuestion
    //
    // Description: Generates a question based on the current level,
    //              prints it to the console, and returns the question string.
    //
    // Parameters:  int level - the current game level
    //
    // Returns:     String - the generated question
    //
    // ***************************************************************

    public String generateAndAskQuestion() {
        String question = QuestionGenerator.generateQuestion(level);
        System.out.println("\nQuestion: " + question);
        return question;
    }
    
    // ***************************************************************
    // 
    // Method:      processStudentAnswers
    // 
    // Description: Handles the process of checking the student's answer and tracks if it was correct on the first attempt.
    // 
    // Parameters:  int correctAnswer, String question
    // 
    // Returns:     boolean - true if correct on first attempt, false otherwise
    // 
    // ***************************************************************
    
    private boolean processStudentAnswers(int correctAnswer, String question) {
        boolean correct = false;
        boolean firstAttempt = true; // tracks if this is the student's first attempt
    
        while (!correct) {
            System.out.print(">>> ");
            int studentAnswer = getAnswer(); // get the student's answer
            correct = handleAnswer(studentAnswer, correctAnswer, question); // check if it's correct
            updateTotalQuestions(1); // update total questions count
    
            if (!correct && firstAttempt) {
                firstAttempt = false; // mark first attempt as failed
            }
        }
        return firstAttempt; // return true if correct on first attempt, false otherwise
    }

    // ***************************************************************
    //
    // Method:      handleAnswer
    //
    // Description: Checks if the student's answer is correct, logs the result,
    //              and provides feedback based on correctness.
    //
    // Parameters:  int studentAnswer - the student's answer
    //              int correctAnswer - the correct answer to the question
    //              String question - the question being answered
    //
    // Returns:     boolean - true if the answer is correct, false otherwise
    //
    // ***************************************************************

    public boolean handleAnswer(int studentAnswer, int correctAnswer, String question) {
        boolean isCorrect;
        ResponseHandler responseHandler = new ResponseHandler();

        if (studentAnswer == correctAnswer) {
            logger.logCorrectAnswer(question, studentAnswer);
            responseHandler.printCorrectResponse();
            isCorrect = true;
        } else {
            logger.logIncorrectAnswer(question, studentAnswer);
            responseHandler.printIncorrectResponse();
            System.out.println("Question: " + question);
            isCorrect = false;
        }
        return isCorrect;
    }

    // ***************************************************************
    //
    // Method:      updateCorrectAnswers
    //
    // Description: Updates the total number of correct answers for the
    //              given level. The correct answer count is added to
    //              the appropriate level's total based on the input level.
    //
    // Parameters:  int level - the current level (1, 2, or 3)
    //              int correctAnswers - the number of correct answers to add
    //
    // Returns:     None
    //
    // ***************************************************************

    public void updateCorrectAnswers(int correctAnswers) {
        if (level == 1)
            correctBasic += correctAnswers;
        else if (level == 2)
            correctIntermediate += correctAnswers;
        else if (level == 3)
            correctAdvanced += correctAnswers;
    }

    // ***************************************************************
    //
    // Method:      updateTotalQuestions
    //
    // Description: Updates the total number of questions asked for the
    //              given level. The total question count is added to
    //              the appropriate level's total based on the input level.
    //
    // Parameters:  int level - the current level (1, 2, or 3)
    //              int totalQuestions - the number of questions to add
    //
    // Returns:     None
    //
    // ***************************************************************

    public void updateTotalQuestions(int totalQuestions) {
        if (level == 1)
            totalQuestionsBasic += totalQuestions;
        else if (level == 2)
            totalQuestionsIntermediate += totalQuestions;
        else if (level == 3)
            totalQuestionsAdvanced += totalQuestions;
    }
    
    // ***************************************************************
    // 
    // Method:      handleStreak
    // 
    // Description: Checks if the streak of correct answers triggers a level progression.
    // 
    // Parameters:  int correctAnswersInRow
    // 
    // Returns:     boolean - false if game ends, true if the game continues
    // 
    // ***************************************************************
    
    private boolean handleStreak(int correctAnswersInRow) {
        boolean resumeGame = true;
        if (correctAnswersInRow >= 2) {
            level = handleLevelProgression(correctAnswersInRow); // update the level
            if (level == 0) {
                resumeGame = false; // game ends
            }
        }
        return resumeGame; // continue game if no level change occurred
    }

    // ***************************************************************
    //
    // Method:      handleLevelProgression
    //
    // Description: Manages level progression based on the player's
    //              performance. If the player answers enough questions
    //              correctly in a row, they are given the option to
    //              move to the next level or stay at the current level.
    //
    // Parameters:  int correctAnswersInRow - the number of consecutive correct answers
    //              int level - the current level of the game
    //
    // Returns:     int - the updated level or 0 if the player chooses to exit
    //
    // ***************************************************************

    public int handleLevelProgression(int correctAnswersInRow) {
        if (correctAnswersInRow >= 5) {
            int choice = manageLevel();
            if (choice == level) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Continuing at *** Level " + level + " ***");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else if (choice == level + 1) {
                level = choice; // update level here when moving up
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
    // Method:      manageLevel
    //
    // Description: Prompts the user to choose whether to continue at the
    //              current level, move to a more difficult level, or exit
    //              the game. It handles level progression and displays
    //              the appropriate messages based on the current level.
    //
    // Parameters:  int level - the current level of the game
    //
    // Returns:     int - the updated level or 0 to exit the game
    //
    // ***************************************************************

    public int manageLevel() {
        while (true) {
            if (level == 3) {
                System.out.println("You have reached the most advanced level. Keep up the good work. Press (1 or 2) to continue or (0) to exit.");
            } else {
                System.out.println("\nDo you want to (1) Continue at this level, (2) Move to a more difficult level, (0) Exit?");
            }
            int choice = getAnswer(); // get user input
            switch (choice) {
                case 1:
                    logger.logContinueResponse(choice, level); 
                    return level; // stay at the current level
                case 2:
                    logger.logContinueResponse(choice, level);
                    return (level < 3) ? level + 1 : level; // move to the next level or stay at level 3
                case 0:
                    logger.logContinueResponse(choice, level);
                    showSummary();
                    logger.closeLogFile();
                    return 0; // exit the loop and return 0 to quit
                default:
                    System.out.println("Please enter a valid option.");
            }
        }
    }

    // ***************************************************************
    //
    // Method:      getAnswer
    //
    // Description: Prompts the user to enter an integer as their answer,
    //              and ensures the input is a valid integer. If invalid input
    //              is provided, it requests the user to enter a valid number.
    //
    // Parameters:  None
    //
    // Returns:     int - the student's answer input as an integer
    //
    // ***************************************************************

    public int getAnswer() {
        // if an integer is not entered
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer.");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }

    // ***************************************************************
    //
    // Method:      showSummary
    //
    // Description: Displays a performance summary at the end of the game,
    //              showing the number of correct answers and total questions
    //              answered at each level, along with the percentage correct.
    //              If the player's performance is below 80% on the basic level,
    //              it recommends seeking extra help.
    //
    // Parameters:  None
    //
    // Returns:     None
    //
    // ***************************************************************

    public void showSummary() {
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
    // Method:      getPercentage
    //
    // Description: Calculates the percentage of correct answers at a given
    //              level based on the number of correct answers and the total
    //              number of questions asked. Returns 0 if there are no questions.
    //
    // Parameters:  int correct - the number of correct answers
    //              int total - the total number of questions
    //
    // Returns:     int - the percentage of correct answers (0 to 100)
    //
    // ***************************************************************

    public int getPercentage(int correct, int total) {
        if (total == 0)
            return 0; // avoid division by zero
        return (int) ((correct * 100.0) / total);
    }
}
