package edu.bridgew.cis.comp152.inheritance.project2;
// Please allow console to have a lot of space so you can see the text, enjoy
import java.util.ArrayList;
import java.util.Scanner;

import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.EnemyFactory;
import edu.bridgew.cis.comp152.inheritance.project2.entity.player.Player;
import edu.bridgew.cis.comp152.inheritance.project2.item.ItemFactory;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.bow.GuardianBow;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.gun.GuardianRevolver;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword.GuardianBlade;

import java.util.Random;

// commented
public class Main {
	public static Scanner in = new Scanner(System.in);
	// factories used to generate objects
	public static ItemFactory iFact = new ItemFactory();
	public static EnemyFactory eFact = new EnemyFactory();

	public static void main(String[] args) {
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();

		Scanner in = new Scanner(System.in);
		boolean playGame = true;
		Entity noob = null;

		String userInput = "";
		// rooms
		Room currentRoom = null; 
		Room nextRoom = null;
		Room beachshore = null;
		Room wreckage = null;
		Room beachVillage;
		Room battleField;
		Room mountain;
		Room archerCampEnt;
		Room mainTent;
		Room field;
		Room pinewoodForest;
		Room elvenForest ;
		Room shireWoods ;
		Room frontLines;
		Room stronghold;

		// rooms generated
		beachshore = new Room("Beachshore");
		wreckage = new Room("Wreckage");	
		beachVillage = new Room("Beach Village");
		battleField = new Room("Battlefield");
		mountain = new Room("Mountain");
		archerCampEnt = new Room("Archer War Camp");
		mainTent = new Room("Main Tent");
		field = new Room("Field");
		pinewoodForest = new Room("Pinewood Forest");
		elvenForest = new Room("Elven Forest");
		shireWoods = new Room("Shire Woods");
		frontLines = new Room("Front Lines");
		stronghold = new Room("Golden Army Stronghold");
		// setting up what room is next to what room
		//Room north, Room south,  Room east, Room west
		wreckage.setRooms(beachshore, null, null, null);
		beachshore.setRooms(null, wreckage, null, beachVillage);
		beachVillage.setRooms(battleField, null, beachshore, null);
		battleField.setRooms(elvenForest, beachVillage, shireWoods, mountain);
		mountain.setRooms(pinewoodForest, null, battleField, archerCampEnt);
		archerCampEnt.setRooms(null, null, mountain, mainTent);
		mainTent.setRooms(null, null, archerCampEnt, field);
		field.setRooms(null, null, mainTent, null);
		pinewoodForest.setRooms(null, mountain, elvenForest, null);
		elvenForest.setRooms(null, battleField, frontLines, pinewoodForest);
		shireWoods.setRooms(frontLines, null, null, battleField);
		frontLines.setRooms(stronghold, shireWoods, null, elvenForest);
		stronghold.setRooms(null, frontLines, null, null);

		currentRoom = beachshore;
		// introduction
		System.out.println("Welcome to D-Legendz");
		in.nextLine();
		boolean pickClass = true;
		System.out.println("Pick a class to play.");
		System.out.println("Enter letter [W] for warrior");
		System.out.println("Enter letter [A] for archer");
		System.out.println("Enter letter [G] for gunner");
		String userClass;
		while (pickClass == true) {
			userClass = in.nextLine().strip().toUpperCase();
			if (userClass.equals("W")) {
				pickClass = false;
				noob = new Player("Tidus", "Warrior", 'M', 20);
			} else if (userClass.equals("A")) {
				noob = new Player("Tidus", "Archer", 'M', 10);
				pickClass = false;
			} else if (userClass.equals("G")) {
				noob = new Player("Tidus", "Gunner", 'M', 10);
				pickClass = false;
			} else {
				System.out.println("Please choose again.");
			}
		}
		noob.equipWeapon(new GuardianBlade(1f));
		// player starter items
		noob.bag.addItem(iFact.getPotion("Medium Health Potion"));
		noob.bag.addItem(iFact.getPotion("Medium Health Potion"));
		noob.bag.addItem(iFact.getPotion("Medium Health Potion"));
		noob.bag.addItem(iFact.getPotion("Medium Stamina Potion"));


		// play cutscene
		currentRoom.playEnterCutscene();
		// game loop
		while (playGame == true) {
			// get enemies from room
			enemies = currentRoom.getEnemiesInRoom();

			if (!enemies.isEmpty()) {
				boolean keepBattling = true;
				// information to determine first turn, however this will be coded to handle this at a later point
				Random rand = new Random();
				Enemy ranEnemy = enemies.get(rand.nextInt(enemies.size()));
				double playerSpeed = noob.getCurrentSpeed();
				double enemySpeed = ranEnemy.getCurrentSpeed();
				// determines first turn
				int playerTurnInd = 0;
				int enemyTurnInd = 0;
				// player speed is faster than ran enemy they get first turn
				if (playerSpeed >= enemySpeed) {
					playerTurnInd = 0;
					enemyTurnInd = 1;
					// if enemy is faster he gets first turn
				} else if (playerSpeed < enemySpeed) {
					playerTurnInd = 1;
					enemyTurnInd = 0;
				}

				System.out.println("Battle Begins!");
				noob.printBattleInfo();
				System.out.println();
				// this will loop continual while the battle is still going on
				int turnCount = 0;
				// items are dropped
				currentRoom.moveItemsDrops();
				// battle loop
				while (keepBattling == true) {
					// modulus is used so what happens is, the player and enemy alternates turns like how odd and even number alternate
					if (turnCount%2 == playerTurnInd) {
						System.out.println("Enemies:");
						System.out.println("______________________________________________________"
								+ "____________________________________________________________________");
						// print enemies info
						for (int i =0 ; i < enemies.size(); i++) {
							System.out.print((i + 1) + ". ");
							enemies.get(i).printBattleInfo();
							System.out.println();
						}
						// player move
						noob.combatAction(noob, enemies);
						System.out.println();
					}
					//  modulus is used so what happens is, the player and enemy alternates turns like how odd and even number alternate
					if (turnCount%2 == enemyTurnInd) {
						// all enemies do moves
						int counter = 0;
						for ( int i = 0; i < enemies.size(); i++) {
							Enemy curEnemy = enemies.get(i);
							System.out.println("Enemy " + ++counter + " Turn:" );
							curEnemy.combatAction(noob, enemies);;
							System.out.println("");
						}
					}
					noob.printBattleInfo();
					// increase turn counter
					turnCount +=1;

					if (noob.getCurrentHealth() <= 0) {
						System.out.println(noob.getName() + " has been defeated.");
						// reset stats
						noob.resetBattleStats();
						boolean askPlayAgain = true;
						System.out.println("Enter letter [Y] if you want to respawn.");
						System.out.println("Enter letter [X] if you want to stop playing.");
						// prompt user to make decision
						String playerDecision;

						while (askPlayAgain == true) {
							playerDecision = in.nextLine().strip().toUpperCase();
							// respawns player at beach
							if (playerDecision.equals("Y")) {
								currentRoom = beachshore;
								askPlayAgain = false;
								keepBattling = false;
								System.out.println("You respawned at Beachshore.");
								System.out.println("Battle experience allowed you to level up.");
								noob.levelUp();
								currentRoom = beachshore;
								// ends game
							} else if (playerDecision.equals("X")) {
								System.out.println("Have a good day. Thanks for playing.");
								in.close();
								System.exit(0);
								keepBattling = false;
								askPlayAgain = false;
							} else {
								// in case type invalid input
								System.out.println("please type either Y or X.");
							}
						}
					}
					// if enemies size is 0 they won, now another cutscene will happen if it's real
					if (enemies.size() == 0) {
						System.out.println();
						currentRoom.afterBattleCutScene();
						System.out.println();
						System.out.println("Victory" + "\n");
						System.out.println("You leveled up.");
						System.out.println();
						noob.levelUp();
						// items dropped
						currentRoom.takeEnemyDrops(noob);
						System.out.println(currentRoom.toString());
						noob.resetBattleStats();
						keepBattling = false;
					}
				}
			}
			//take userr input for dungeon interaction
			userInput = in.nextLine().strip();
			// splits into array with max size of two
			String[] dungeonInteract = userInput.split(" ", 2);
			String command = dungeonInteract[0];
			String input = null;
			// automatically sets userinput if player didn't type enouugh
			if (dungeonInteract.length > 1) {
				input = dungeonInteract[1];
			} else {
				input = "";
			}
			// if it is C is will print use info
			if (command.equalsIgnoreCase("stats")) {
				System.out.println(noob.toString());
				// if it is I it will open inventory
			} else if (command.equalsIgnoreCase("open")) {
				System.out.println();
				currentRoom.open(input);
				// look will get room description
			} else if (command.equalsIgnoreCase("look")) {
				System.out.println();
				currentRoom.look();
				// search will search a container
			} else if (command.equalsIgnoreCase("search")) {
				System.out.println();
				currentRoom.search(input); 
				// take will take a object from the room
			} else if (command.equalsIgnoreCase("take")) {
				System.out.println();
				// if it returns false it will let them know they fail
				if (currentRoom.take(input, noob) == false) {
					System.out.println("You didn't find what you wanted to take.");
				}
				// open inventory for non battle mode
			} else if (command.equalsIgnoreCase("Inventory")) {
				noob.useInventory(noob, enemies, false);
				// go is for moving to rooms
			} else if (command.equalsIgnoreCase("go")) {
				nextRoom = currentRoom.move(input);
				// if next room is null they won't move but if it isn't they will move and cutscene will happen 
				if (nextRoom != null) { 
					currentRoom = nextRoom;
					currentRoom.playEnterCutscene();
					System.out.println();
					System.out.println(currentRoom.toString());
				} else {
					System.out.println(noob.getName() + " tried but couldn't go " + input + "."); 
				}
				// end game
			} else if (command.equalsIgnoreCase("exit")) {
				in.close();
				System.out.println("Goodbye have a great day.");
				playGame = false;
				System.exit(0);
				// helps get instructions
			} else if (command.equalsIgnoreCase("help")) {
				System.out.println("Commands (enter whats in [])");
				System.out.println("______________________________________________________________________________");
				System.out.println("1. Enter [go] then a [direction] to travel.");
				System.out.println("2. Enter [look] to get a description of the area.");
				System.out.println("3. Enter [open] then a [name of What you're trying to open] to open something.");
				System.out.println("4. Enter [search] then a [name of What you want to open] to search.");
				System.out.println("5. Enter [take] then a [object name] to take a item.");
				System.out.println("6. Enter [help] for instructions.");
				System.out.println("7. Enter [inventory] to open instruction.");
				System.out.println("8. enter [stats] to check stats.");
				System.out.println("9. enter [exit] to exit game.");
				// hacks
			} else if (command.equalsIgnoreCase("Gamemode0")) {
				boolean rightInput = false;
				if (input.strip().equalsIgnoreCase("level up")) {
					int usedInt = 0;
					// prompt for how much they want to level up
					System.out.println("Type in how many times you want to level up.");
					while (rightInput == false)
						// account for errors
						try {
							usedInt = Integer.parseInt(Main.in.nextLine());
							rightInput = true;
						} catch (NullPointerException ex) {
							System.out.println("Invalid Input");
						} catch (NumberFormatException ex2) {
							System.out.println("Invalid Input");
						}
					System.out.println("You have leveled up " + usedInt + " times.");
					// level up depending on number they want
					for (int i =0 ; i < usedInt ; i++) {
						noob.levelUp();

					}

				} else if (input.strip().equalsIgnoreCase("health")) {
					double usedDouble = 0;
					while (rightInput == false) {
						// prompt for amount of health to change to
						System.out.println("Type in the number of health you want");
						// account for errors
						try {
							usedDouble = Double.parseDouble(Main.in.nextLine());
							rightInput = true;
						} catch (NullPointerException ex) {
							System.out.println("Invalid Input");
						} catch (NumberFormatException ex2) {
							System.out.println("Invalid Input");
						}
						System.out.println("Health is changed to " + usedDouble +".");
						// change health
						noob.setHealthWithChanges(usedDouble);
						noob.setCurrentHealth(usedDouble);
						noob.setOrigHealth(usedDouble);
					}
				} else if (input.strip().equalsIgnoreCase("attack")) {
					double usedDouble = 0;
					while (rightInput == false) {
						// prompt for attack
						System.out.println("Type in the number of attack you want.");
						// account for error
						try {
							usedDouble = Double.parseDouble(Main.in.nextLine());
							rightInput = true;
						} catch (NullPointerException ex) {
							System.out.println("Invalid Input");
						} catch (NumberFormatException ex2) {
							System.out.println("Invalid Input");
						}

					}
					// change attack
					System.out.println("Attack is changed to " + usedDouble +".");
					noob.setCurrentAtk(usedDouble);
					noob.setOrigATK(usedDouble);
				} else if (input.strip().equalsIgnoreCase("defense")) {
					double usedDouble = 0;
					while (rightInput == false) {
						// prompt defense
						System.out.println("Type in the number of defense you want.");
						// account for error
						try {
							usedDouble = Double.parseDouble(Main.in.nextLine());
							rightInput = true;
						} catch (NullPointerException ex) {
							System.out.println("Invalid Input");
						} catch (NumberFormatException ex2) {
							System.out.println("Invalid Input");
						}
					}
					// change defense
					System.out.println("Defense is changed to " + usedDouble +".");
					noob.setCurrentDef(usedDouble);
					noob.setOrigDef(usedDouble);

				} else if (input.strip().equalsIgnoreCase("speed")) {
					double usedDouble = 0;
					while (rightInput == false) {
						// prompt defense
						System.out.println("Type in the number of speed you want.");
						// account for error
						try {
							usedDouble = Double.parseDouble(Main.in.nextLine());
							rightInput = true;
						} catch (NullPointerException ex) {
							System.out.println("Invalid Input");
						} catch (NumberFormatException ex2) {
							System.out.println("Invalid Input");
						}
					}
					// change defense
					System.out.println("Speed is changed to " + usedDouble +".");
					noob.setCurrentSpeed(usedDouble);
					noob.setOrigSpeed(usedDouble);
				} 
			}
		}
	}
}







