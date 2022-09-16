package com.kenzie.app;

// import necessary libraries


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.dto.CluesDTO;
import com.kenzie.app.http.CustomHttpClient;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static final String URL = "https://jservice.kenzie.academy/api/clues";

    public Main() throws JsonProcessingException {
    }

    /* Java Fundamentals Capstone project:
           - Define as many variables, properties, and methods as you decide are necessary to
           solve the program requirements.
           - You are not limited to only the class files included here
           - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
             definition provided
           - Your program execution must run from the main() method in Main.java
           - The rest is up to you. Good luck and happy coding!

         */


    public static void main(String[] args) throws JsonProcessingException {
        //Write main execution code here

        String httpResponse;
        httpResponse = CustomHttpClient.sendGET(URL);

        ObjectMapper objectMapper = new ObjectMapper();
        CluesDTO cluesObj;
        cluesObj = objectMapper.readValue(httpResponse, CluesDTO.class);

        Scanner scanner = new Scanner(System.in);

        String answer = "";
        int questionNumber = 0;
        int currentTotalPoints = 0;


        System.out.println("Welcome to Jeopardy!!!");
        System.out.println("You will be asked 10 questions one at a time\n");

            for (int i = randomQuestion(0);  i < cluesObj.getClues().size(); i++) {

                if (questionNumber < 10) {
                    questionNumber++;

                System.out.println("Category: " + cluesObj.getClues().get(i).getCategory().getTitle());
                System.out.println("Question Number " + questionNumber + ": " + cluesObj.getClues().get(i).getQuestion());
                answer = cluesObj.getClues().get(i).getAnswer();

                String response = scanner.nextLine();

                if (response.equalsIgnoreCase(answer)) {
                    currentTotalPoints++;
                    System.out.println("Congratulations, that is Correct!");
                } else if (response.equals("")) {
                    System.out.println("Please enter a valid response.");
                    questionNumber--;
                    
                } else {
                    System.out.println("Incorrect.  The correct answer is: " + answer);
                }
                    System.out.println("Total Points: " + currentTotalPoints + "\n");
                }
        }
    }

    public static int randomQuestion(int number) {
        //   https://study.com/academy/lesson/java-generate-random-number-between-1-100.html
        int max = 100;
        int min = 1;
        Random randomNum = new Random();
        int i = min + randomNum.nextInt(max);

        return i;
    }
}




















