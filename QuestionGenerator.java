//********************************************************************
//
//  Developer:     Paul Ostos
//
//  Program #:     7
//
//  File Name:     QuestionGenerator.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      12/11/2024
//
//  Instructor:    Prof. Fred Kumi 
//
//  Java Version:  "17.0.1" 2021-10-19 LTS
//
//  Description:   This program generates random mathematical questions based on a specified 
//                 difficulty level, evaluates those questions, and supports operations 
//                 such as addition, subtraction, multiplication, and modulus with proper precedence.
//
//********************************************************************

import java.security.SecureRandom;
import java.util.Stack;

public class QuestionGenerator {

    private static SecureRandom random = new SecureRandom();

    // ***************************************************************
    // 
    // Method:      generateQuestion
    // 
    // Description: Generates a random math question based on the specified difficulty level. 
    //              The question consists of a series of operands and operators.
    // 
    // Parameters:  level - An integer representing the difficulty level (basic, intermediate, advanced). 
    //              Determines the number of operands in the question.
    // 
    // Returns:     A String representing the generated math question.
    // 
    // **************************************************************

    public static String generateQuestion(int level) {

        int numberOfOperands = level + 1; // basic: 2 operands, intermediate: 3 operands, advanced: 4 operands
        StringBuilder question = new StringBuilder();

        // generate operands and random operators
        for (int i = 0; i < numberOfOperands; i++) {
            if (i > 0) {
                question.append(" ");
                question.append(getRandomOperator()); // add random operator
                question.append(" ");
            }
            question.append(random.nextInt(2) + 1); // generate random operand (1-9)
            //question.append(random.nextInt(9) + 1); // generate random operand (1-9)
        }

        return question.toString();
    }

    // ***************************************************************
    // 
    // Method:      getRandomOperator
    // 
    // Description: Selects and returns a random mathematical operator from the set of 
    //              operators { "+", "-", "*", "%" }.
    // 
    // Parameters:  None
    // 
    // Returns:     A String representing a randomly selected operator.
    // 
    // ***************************************************************

    public static String getRandomOperator() {
        String[] operators = { "+", "-", "*", "%" };
        return operators[random.nextInt(operators.length)];
    }

    // ***************************************************************
    // 
    // Method:      evaluateQuestion
    // 
    // Description: Evaluates a mathematical question represented as a String. The question 
    //              consists of operands and operators separated by spaces. Supports operations with correct 
    //              precedence and parentheses (if applicable).
    // 
    // Parameters:  question - A String containing the mathematical question to evaluate.
    // 
    // Returns:     An integer representing the result of the evaluated question.
    // 
    // ***************************************************************

    public static int evaluateQuestion(String question) {
        String[] tokens = question.split(" "); // split question into components (numbers and operators)
        Stack<Integer> numbers = new Stack<>(); // stack to hold numbers
        Stack<String> operators = new Stack<>(); // stack to hold operators
    
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i]; // current token (either number or operator)
    
            if (isNumber(token)) {
                // push numbers onto the numbers stack
                numbers.push(Integer.parseInt(token));
            } else if (isOperator(token)) {
                // process operators with higher or equal precedence
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    int num2 = numbers.pop(); // pop the top two numbers
                    int num1 = numbers.pop();
                    String op = operators.pop(); // pop the operator
                    numbers.push(applyOperator(num1, num2, op)); // compute and push the result
                }
                // push the current operator onto the stack
                operators.push(token);
            }
        }
        // process remaining operators in the stack
        while (!operators.isEmpty()) {
            int num2 = numbers.pop(); // pop the top two numbers
            int num1 = numbers.pop();
            String op = operators.pop(); // pop the operator
            numbers.push(applyOperator(num1, num2, op)); // compute and push the result
        }
        return numbers.pop(); // the final result is the last number in the stack
    }

    // ***************************************************************
    // 
    // Method:      isNumber
    // 
    // Description: Determines whether the given token is a valid integer.
    // 
    // Parameters:  token - A String to check if it represents an integer value.
    // 
    // Returns:     A boolean value: true if the token is a valid integer, false otherwise.
    // 
    // ***************************************************************

    public static boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // ***************************************************************
    // 
    // Method:      isOperator
    // 
    // Description: Checks if the given token is a valid mathematical operator 
    //              from the set { "+", "-", "*", "%" }.
    // 
    // Parameters:  operator - A String to check if it matches one of the allowed operators.
    // 
    // Returns:     A boolean value: true if the token is a valid operator, false otherwise.
    // 
    // ***************************************************************

    public static boolean isOperator(String operator) {
        return operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("%");
    }

    // ***************************************************************
    // 
    // Method:      precedence
    // 
    // Description: Determines the precedence level of a given mathematical operator. 
    //              Operators "*" and "%" have higher precedence, while "+" and "-" have lower precedence.
    // 
    // Parameters:  operator - A String representing the operator whose precedence is to be evaluated.
    // 
    // Returns:     An integer representing the precedence level of the operator.
    // 
    // ***************************************************************

    public static int precedence(String operator) {
        switch (operator) {
            case "*":
            case "%":
                return 2; // higher precedence
            case "+":
            case "-":
                return 1; // lower precedence
            default:
                return 0; // should not happen
        }
    }

    // ***************************************************************
    // 
    // Method:      applyOperator
    // 
    // Description: Applies the given mathematical operator to two operands and returns the result.
    //              Supports addition, subtraction, multiplication, and modulus operations.
    // 
    // Parameters:  operand1 - The first operand as an integer.
    //              operand2 - The second operand as an integer.
    //              operator - A String representing the operator to apply ("+", "-", "*", "%").
    // 
    // Returns:     An integer representing the result of the operation.
    // 
    // ***************************************************************

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
