package Homework3;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Analyzer class that acts as the main method of the program
 * @author Avish Parmar
 * SBUID: 112647892
 * Email: avish.parmar@stonybrook.edu
 * Course: CSE214
 * Recitation: Section 01
 */
public class Analyzer {
	
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		System.out.println("Welcome to the Elevator simulator!");
		double probability = 0;
		System.out.print("Please enter the probablity of arrival for Requests: ");
		try {
			probability = stdin.nextDouble();
		}catch(InputMismatchException e) {
			System.out.print("Enter a double value please: ");
			stdin.nextLine();
			probability = stdin.nextDouble();
		}
		if(probability < 0 || probability > 1) {
			while(probability < 0 || probability > 1) {
				System.out.print("Probability cannot be lower than 0 or greater than 1! Please re-enter: ");
				probability = stdin.nextDouble();
			}	
		}
		int floors = 0;	
		System.out.print("Please enter the number of floors: ");
		try {
			floors = stdin.nextInt();
		}catch(InputMismatchException e) {
			System.out.print("Please enter an int value for floors: ");
			stdin.nextLine();
			floors = stdin.nextInt();
		}
		
		if(floors <= 1) {
			while(floors <= 1) {
				System.out.print("Number of floors cannot be less than or equal to 1! Please re-enter: ");
				floors = stdin.nextInt();
			}
			
		}
		int numElevators;
		System.out.print("Please enter the number of elevators: ");
		try {
			numElevators = stdin.nextInt();
		}catch(InputMismatchException e) {
			System.out.print("Please enter an int value for number of elevators: ");
			stdin.nextLine();
			numElevators = stdin.nextInt();
		}
		
		if(numElevators <= 0) {
			while(numElevators <= 0) {
				System.out.print("Must have atleast 1 elevator! Please re-enter: ");
				numElevators = stdin.nextInt();
			}
		}
		int simulationLength;
		System.out.print("Please enter the length of the simulation (int time units): ");
		try {
			simulationLength = stdin.nextInt();
		}catch(InputMismatchException e) {
			System.out.print("Enter an int value for simulation length: ");
			stdin.nextLine();
			simulationLength = stdin.nextInt();
		}
		
		if(simulationLength <= 0) {
			while(simulationLength <= 0) {
				System.out.print("Simulation length must be more than 0! Please re-enter: ");
				simulationLength = stdin.nextInt();
			}
		}
		stdin.nextLine();
		System.out.print("\nWould you like to trace the simulation? (y/n): ");
		String trace = stdin.nextLine();
	
		if(!(trace.equalsIgnoreCase("Y")) && !(trace.equalsIgnoreCase("N"))) {
			while(!(trace.equalsIgnoreCase("Y")) && !(trace.equalsIgnoreCase("N"))) {
				System.out.print("Please enter a Y or N: ");
				trace = stdin.nextLine();
			}
		}
		
		if(trace.equalsIgnoreCase("Y")) {
			OptimalSimulator.DEBUG = true;
			Simulator.DEBUG = true;
		}
		
		System.out.print("Would you like to run an Optimal Simulation? (y/n): ");
		String selection = stdin.nextLine();
		
		if(!(selection.equalsIgnoreCase("Y")) && !(selection.equalsIgnoreCase("N"))) {
			while(!(selection.equalsIgnoreCase("Y")) && !(selection.equalsIgnoreCase("N"))) {
				System.out.print("Please enter a Y or N: ");
				selection = stdin.nextLine();
			}
		}
		if(selection.equalsIgnoreCase("Y")) {
			System.out.println("\nRunning OPTIMAL SIMULATION");
			OptimalSimulator.simulate(probability, floors, numElevators, simulationLength);
		}
		else if(selection.equalsIgnoreCase("N")) {
			System.out.println("\nRunning NORMAL SIMULATION");
			Simulator.simulate(probability, floors, numElevators, simulationLength);
		}
		
		stdin.close();
	}
}
