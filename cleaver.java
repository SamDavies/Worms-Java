import java.util.Scanner;

public class cleaver {
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in); //creates a scanner variable
		blade object = new blade(null); //this is a blade object for use to print my name
		
		System.out.println("Enter your name here!");
		String name = input.nextLine(); //takes the next line to use as my name
		
		object.message(name); //uses name my name as argument for message
		
		System.out.println("enter your girlfriends name here");
		
		String gName = input.nextLine(); //creates a variable for Nicola's name
		blade object2 = new blade(gName); //makes new object with nicola's name as constructor
		object2.saying(); //runs saying in object2
		
		input.close(); //closes the scanner
	}
}
