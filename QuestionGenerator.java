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

import java.security.SecureRandom;

public class QuestionGenerator {

    private static SecureRandom random = new SecureRandom();

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
    public static String generateQuestion(int level) {
        int numOperands = level + 1; // basic: 2 operands, intermediate: 3 operands, advanced: 4 operands
        StringBuilder question = new StringBuilder();

        // generate operands and random operators
        for (int i = 0; i < numOperands; i++) {
            if (i > 0) {
                question.append(" ");
                question.append(getRandomOperator()); // add random operator
                question.append(" ");
            }
            question.append(random.nextInt(9) + 1); // generate random operand (1-9)
        }

        return question.toString();
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

    public static String getRandomOperator() {
        String[] operators = {"+", "-", "*", "%"};
        return operators[random.nextInt(operators.length)];
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

    public static int evaluateQuestion(String question) {
        String[] tokens = question.split(" ");  // split question by spaces
        int result = Integer.parseInt(tokens[0]);  // start with the first operand

        // iterate through tokens and apply the operators
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            int nextOperand = Integer.parseInt(tokens[i + 1]);
            result = applyOperator(result, nextOperand, operator);  // apply operator
        }

        return result;
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

    public static int applyOperator(int operand1, int operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "%":
                return operand1 % operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
    