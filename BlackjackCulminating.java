/*
 * Samik Bhatia
 * 12 January, 2022
 * 
 */
//importing required libraries
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class BlackjackCulminating {
	
	//procedure type method to display the rules of the game
	public static void rules() {
		
		//try catch statement to read the contents of the file
		try {
		      File file = new File("rules.txt");
		      Scanner reader = new Scanner(file);
		      
		      //while loop to print contents
		      while (reader.hasNextLine()) {
		        String r = reader.nextLine();
		        System.out.println(r);
		      }
		      
		      reader.close();
		      
		    //catch statement to output error message
		    } catch (FileNotFoundException e) {
		    	
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		      
		    }
		
	}

	//main method
	public static void main(String[] args) {
		
		//declaring a string array to store the messages to be displayed after rounds
		String[] messages = new String[] {"Blackjack!!", "Bust!", "You win!!", "You lose!", "Push!"};
		
		//creating a scanner
		Scanner scan = new Scanner(System.in);
		
		//introducing the game to the user/player and collecting input on whether they want the rules shown
		System.out.println("\nWelcome to blackjack!");
		System.out.print("\nWould you like the rules to be explained?: ");
		String rulesDisplay = scan.nextLine();
		
		//if statement to run the procedure method on the condition user says "yes"
		if (rulesDisplay.equalsIgnoreCase("Yes")) {
			rules();
		}
		
		//asking user if they want to start
		System.out.print("\nReady to start?: ");
		String start = scan.nextLine();
		
		//if statement to continue game/program based on input
		if (!start.equalsIgnoreCase("No")) {
			System.out.println("\n----------\n\nLet's get into it!\n\n---------");
		} else {
			System.out.print("\nThank you for checking this game out! Come back next time.");
			System.exit(0);
		}
		
		//assigning the amount of money available for player to use/bet with
		int bankroll = 1000;
		System.out.print("\nYou have: $" + bankroll);
		
		//setting 'play again' condition as yes to initiate while loop
		String redo = "Yes";
		
		while (!redo.equalsIgnoreCase("No")) {
			
			//asking user for how much they want to bet
			System.out.print("\nEnter bet amount: $");
			
			//converting string input to integer
			int bet = Integer.parseInt(scan.nextLine());
			
			//checking to see if bet is valid (above bank value or less than equal to 0)
			if(bet <= 0 || bet > bankroll) {
				System.out.println("\nInvalid input. Please try again.");
				System.exit(0);
			}
			
			//initializing player and dealer values
			int dealer = (int) (9*Math.random() + 1);
			int player = (int) (9*Math.random() + 1) + (int) (9*Math.random() + 1);
			
			//outputting first hand
			System.out.print("\nDealer's hand: " + dealer);
			System.out.print("\nYour hand: " + player);
			
			//asking user for action
			System.out.print("\n\nSelect your action: ");
			String action = scan.nextLine();
			
			//do while loop initiated on 'hit' action
			while (action.equalsIgnoreCase("Hit")) {
				
				//increasing value and displaying player hand
				player = player + (int) (9*Math.random() + 1);
				System.out.println("\nYour hand: " + player);
				
				//checking whether player's hand is playable
				if (player < 21) {
					System.out.print("\nSelect your action: ");
					
				//outputting blackjack message and exiting loop
				} else if (player == 21) {
					System.out.println("\n--- " + messages[0] + " ---");
					bankroll = bankroll + bet;
					System.out.println("\nRemaining: $" + bankroll);
					break;
					
				//outputting bust message and exiting loop
				} else if (player > 21) {
					System.out.println("\n--- " + messages[1] + " ---");
					bankroll = bankroll - bet;
					System.out.println("\nRemaining: $" + bankroll);
					break;
				}
				
				//updating action value
				action = scan.nextLine();
				
			}
			
			//do while loop based on player 'stand' action
			while (action.equalsIgnoreCase("Stand") && dealer < 17) {
				
				//increasing dealer hand
				dealer = dealer + (int) (9*Math.random() + 1);
				
			}
			
			//if statements to determine win or loss for player and dealer
			if (dealer > 21) {
				
				//outputting final hand values and "you win" message
				System.out.print("\nDealer's hand: " + dealer);
				System.out.println("\nYour hand: " + player);
				System.out.println("\n--- " + messages[2] + " ---");
				bankroll = bankroll + bet;
				System.out.println("\nRemaining: $" + bankroll);
				
			} else if (dealer > player) {
				
				//outputting final hand values and "you lose" message
				System.out.print("\nDealer's hand: " + dealer);
				System.out.println("\nYour hand: " + player);
				System.out.println("\n--- " + messages[3] + " ---");
				bankroll = bankroll - bet;
				System.out.println("\nRemaining: $" + bankroll);
				
			} else if (dealer < player && player < 21) {
				
				//outputting final hand values and "you lose" message
				System.out.print("\nDealer's hand: " + dealer);
				System.out.println("\nYour hand: " + player);
				System.out.println("\n--- " + messages[2] + " ---");
				bankroll = bankroll + bet;
				System.out.println("\nRemaining: $" + bankroll);
				
			} else if (dealer == player) {
				
				//outputting final hand values and "push" message
				System.out.print("\nDealer's hand: " + dealer);
				System.out.println("\nYour hand: " + player);
				System.out.println("\n--- " + messages[4] + " ---");
				bankroll = bankroll - bet;
				System.out.println("\nRemaining: $" + bankroll);
			}
			
			//if statement to output message if bank balance is 0
			if(bankroll == 0) {
				System.out.println("\nThanks for playing. Better luck next time.");
				System.exit(0);
			}
			
			//asking user if they want to play again and collecting input accordingly
			System.out.print("\nDo you want to play again?: ");
			redo = scan.nextLine();
		
		}
		
		//outputting end game message
		System.out.println("\nThanks for playing!");
		
	}

}
