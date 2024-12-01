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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program7{

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
    public void initiateLearning(){
        correctAnswerResponses();
        incorrectAnswerResponses();
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

    public void correctAnswerResponses(){
        // create and add correct responses to an array
        List<String> correctResponses = new ArrayList<>(); 

        correctResponses.add("Excellent!");  
        correctResponses.add("Very good!");        
        correctResponses.add("Nice work!");        
        correctResponses.add("Way to go!");        
        correctResponses.add("Keep up the good work!");  
        
        displayResponse(correctResponses);

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
    public void incorrectAnswerResponses(){
        // create and add incorrect responses to an array
        List<String> incorrectResponses = new ArrayList<>(); 

        incorrectResponses.add("That is incorrect!");  
        incorrectResponses.add("No. Please try again!");        
        incorrectResponses.add("Wrong, try once more!");        
        incorrectResponses.add("No. Don't give up!");        
        incorrectResponses.add("Incorrect. Keep trying!");      
        
        displayResponse(incorrectResponses);
    
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
    public void displayResponse(List<String> responses){
        // catch an error with responses
        if (responses.isEmpty()){
            System.out.println("Error generating response. My bad.");
        }

        // display randomly selected correct responses to user
        Random random = new Random(); 
        int randomIndex = random.nextInt(responses.size()); 
        System.out.println(responses.get(randomIndex));

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
   