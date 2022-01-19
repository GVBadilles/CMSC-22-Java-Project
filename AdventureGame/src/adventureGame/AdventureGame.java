package adventureGame;

import java.io.*;

/**
 * 
 * Generic cave-inspired text-based RPG.
 * @author Gabriel Venz Badilles
 * @version 2021-09 (4.21.0)
 * @since 4.21.0
 */

public class AdventureGame {
	
	/**
	 * 
	 * The method to convert the given direction into
	 * integer type
	 * 
	 * @param input		the direction user wants to go
	 * @return 			the specified direction
	 */
	
	private int convDirection(String input) {
		char dir = input.charAt(0);
		int theDirection = 9999;
		switch(dir) {
		case 'n': case 'N': theDirection = 0; break;
		case 's': case 'S': theDirection = 1; break;
		case 'w': case 'W': theDirection = 2; break;
		case 'e': case 'E': theDirection = 3; break;
		case 'u': case 'U': theDirection = 4; break;
		case 'd': case 'D': theDirection = 5; break;
		default: System.out.println("Received invalid input\n");
		}
		return theDirection;
	}
	
	/**
	 * 
	 * pickUpItem method gets the item the user wants
	 * to pick up
	 * 
	 * @param p				the player
	 * @param keyBr			reads character-input string
	 * @return				the new inventory of the player
	 * @throws IOException	if input/output exception occurred
	 */
	
	private Item pickUpItem(Player p, BufferedReader keyBr) throws IOException {
		Item[] contentsArray = (p.getLocation()).getRoomContents();
		String inputString = "preparing";
		int myChoice = -1;
		
		do {
			System.out.println("The room have: ");
			for (int i = 0; i < contentsArray.length; i++) {
				System.out.println((i + 1) + ": " + contentsArray[i].getDescription());
			}
			System.out.println("Enter item to grab: ");
			inputString = keyBr.readLine();
			System.out.println('\n');
			if (inputString.equals("")) inputString = " ";
			try {
				myChoice = Integer.parseInt(inputString);
			}	catch (NumberFormatException e) {
				System.out.println("Received invalid input.\n");
				myChoice = -1;
			}
			
			if (myChoice < 0 || myChoice > contentsArray.length) {
				System.out.println("Item is either non-existent or not present in the room.\n");
			}
		} while (myChoice < 0 || myChoice > contentsArray.length);
		
		return contentsArray[myChoice-1];
	}
	
	/**
	 * 
	 * dropItem method gets the item input that the user
	 * wants to drop
	 * 
	 * @param p				the player
	 * @param keyBr			reads character-input string
	 * @return				the new inventory of the player
	 * @throws IOException	if input/output exception occurred
	 */
	private int dropItem(Player p, BufferedReader keyBr) throws IOException {
		String inputString = "preparing";
		int myChoice = -1;
		
		do {
			System.out.println("Currently carrying" + p.showMyItems() + '\n');
			System.out.println("Enter item to drop: ");
			inputString = keyBr.readLine();
			try {
				myChoice = Integer.parseInt(inputString);
			} catch (NumberFormatException e) {
				System.out.println("Received invalid input.\n");
				myChoice = -1;
			}
			
			if (myChoice < 0 || myChoice > p.numItemsCarry()) {
				System.out.println("Received invalid choice.\n");
			} 
		}	while (myChoice < 0 || myChoice > p.numItemsCarry());
		
		return myChoice;
	}
	
	/**
	 * 
	 * startQuest method does the control of the game
	 * 
	 * @throws IOException	if input/output exception occurred
	 */
	
	public void startQuest() throws IOException {
		Player thePlayer = new Player();
		Adventure theCave = new Adventure();
		Room startRoom = theCave.adventureCreate();
		thePlayer.setRoom(startRoom);
		
		BufferedReader keybrd = new BufferedReader(new InputStreamReader(System.in));
		String inputString = "preparing";
		
		/* The main loop of the program that receive,
		 * interpret, and execute the commands of the
		 * player. 
		 */		
		
		do {
			System.out.println(thePlayer.look());
			System.out.println("You are carrying: " + thePlayer.showMyItems() + '\n');
			
			int direction = 9;
			System.out.println("Where to go? North (n) South (s) East (e) West (w) Up (u) Down (d) or"
					+ "\npick up (p) or release(r) an item,"
					+ "\nor quit (q)?"
					+ "\nEnter the letter of your choice: ");
			inputString = keybrd.readLine();
			System.out.println('\n');
			if (inputString.equals("")) inputString = " ";
			char key = inputString.charAt(0);
			switch (key) {
			
			// go to the direction 
			case 'n': case 'N': case 's': case 'S':
			case 'e': case 'E': case 'w': case 'W':
			case 'u': case 'U': case 'd': case 'D':
				direction = convDirection(inputString);
				thePlayer.go(direction);
				break;
			
			// pick up item
			case 'p': case 'P':
				if (thePlayer.handsFull()) {
					System.out.println("Both hands are full.\n");
				}
				else if (thePlayer.getLocation().roomEmpty()) {
					System.out.println("The room is empty.\n");
				} else {
					Item itemToGrab = pickUpItem(thePlayer, keybrd);
					thePlayer.pickUp(itemToGrab);
					(thePlayer.getLocation()).removeItem(itemToGrab);
				}
				break;
			
			// release/drop item
			case 'r': case 'R':
				if (thePlayer.handsEmpty()) {
					System.out.println("No item to drop. Hands are empty.\n");
				} else {
					int itemToToss = dropItem(thePlayer, keybrd);
					thePlayer.dropItem(itemToToss);
				}
				break;
				
			case 'q': case 'Q':
				System.out.println("Quitting.");
			
			// default case
			default:
				System.out.println(". . .\n");
			}
		} while (inputString.charAt(0) != 'q');
	}
	
	/** 
	 * Main method
	 * @param args default method parameter
	 * @throws IOException	if input/output exception occurred
	 */
	public static void main(String args[]) throws IOException {
		System.out.println("Welcome to my very own adventure game!\n"
				+ "Inspired by the cave game in generic text-based adventures!\n"
				+ "Author: Gabriel Venz Badilles\n\n");
		AdventureGame theGame = new AdventureGame();
		theGame.startQuest();
	}
}
