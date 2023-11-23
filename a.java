package Project;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
	Scanner scanner = new Scanner(System.in); //using java built-in function to take user input.
	
	int score = 0; //stating a variable that the program will add up, Initialize to zero currently.
	
	String[] questions = { //stating five questions that users have to answer.
			
		    "1. What does 'Java' stand for?",
		    "2. Which company owns 'Java' currently?",
		    "3. What does JDK stand for?",
		    "4. What does JRE stand for?",
		    "5. Which is the responsive data type in the following?"
		};

	String[] options = { //stating four options for each question.
			
		    "A. Just Another Vague Acronym\t B. Joint Application Venture Architecture\t C. Java Application Virtual Architecture\t D. None of the above",
		    "A. IBM\t B. Sun Microsystems\t C. Oracle\t D. Open AI",
		    "A. Java Design Kit\t B. Java Development Kit\t C. Java Developer Kernelm\t D. Justified Developing Knowledge",
		    "A. Java Runtime Environment\t B. Java Readable Execution\t C. Just Reliable Execution\t D. Java Runtime Engine",
		    "A. int\t B. String\t C. float\t D. long"
		};
	
	char[] correctAnswer = { //correct answer among the options
			
		    'D', // Correct answer for question 1
		    'C', // Correct answer for question 2
		    'B', // Correct answer for question 3
		    'A', // Correct answer for question 4
		    'B'  // Correct answer for question 5
		};
	
	// Displaying the questions and options, getting the user inputs, and calculating the scores
	for(int i = 0; i < questions.length; i++) { 
		
		System.out.println(questions[i]);
		System.out.println(options[i]);
		System.out.println("Choose the correct answer");
		
		//convert to upper-case if the user enter a lower case and take only the first character.
		char userInput = scanner.next().toUpperCase().charAt(0);
		
		//checking the answer
		if(userInput == correctAnswer[i]) {
			score++; //increase if the answer is correct
		} 
		
	}
	//calculating the percentage score
	double percentageScore = (score * 100)/questions.length;
	
	//displaying the result
	System.out.println("\nYou score: " + score + " out of " + questions.length);
    System.out.println("Total percentage Score: " + percentageScore + "%");
    
    //closing the scanner
    scanner.close(); 
	}
}
