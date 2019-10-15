package edu.bridgew.cis.comp152.inheritance.project2;

import java.util.ArrayList;

import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;
import edu.bridgew.cis.comp152.inheritance.project2.item.consumable.Potion;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;

// commented
public class Room {
	// attributes of room
	// cutscenes
	private boolean enterCutsceneHappened;
	private String[] enterCutscene;
	private String[] afterBattleCutScene;
	private String name;
	private String description;
	// items and enemies
	private ArrayList<Container> containersInRoom;
	private ArrayList<Weapon> weaponInRoom;
	private ArrayList<Potion> potionInRoom;
	private ArrayList<Enemy> enemiesInRoom;
	// whether or not something needs to be open
	private boolean northNeedOpen;
	private boolean southNeedOpen;
	private boolean westNeedOpen;
	private boolean eastNeedOpen;
	// string needed to go a direction if it needs to be open 
	private String northOpenString;
	private String southOpenString;
	private String eastOpenString;
	private String westOpenString;
	// rooms in that direction
	private Room north;
	private Room east;
	private Room south;
	private Room west;

	// constructor
	public Room(String name) {
		this.containersInRoom = new ArrayList<Container>();
		this.enemiesInRoom = new ArrayList<Enemy>();
		this.weaponInRoom = new ArrayList<Weapon>();
		this.potionInRoom = new ArrayList<Potion>();
		this.name = name;
		String nameEnum = name.toUpperCase().replaceAll(" ", "_");
		this.description = Database.roomDescript[Database.roomNames.valueOf(nameEnum).ordinal()];

		this.northNeedOpen = Database.roomNeedOpen[Database.roomNames.valueOf(nameEnum).ordinal()][Database.directions.NORTH.ordinal()];
		if (this.northNeedOpen == true) {
			this.northOpenString = Database.roomOpenString[Database.roomNames.valueOf(nameEnum).ordinal()][Database.directions.NORTH.ordinal()];
		}

		this.southNeedOpen = Database.roomNeedOpen[Database.roomNames.valueOf(nameEnum).ordinal()][Database.directions.SOUTH.ordinal()];
		if (this.southNeedOpen == true) {
			this.southOpenString = Database.roomOpenString[Database.roomNames.valueOf(nameEnum).ordinal()][Database.directions.SOUTH.ordinal()];
		}

		this.eastNeedOpen = Database.roomNeedOpen[Database.roomNames.valueOf(nameEnum).ordinal()][Database.directions.EAST.ordinal()];
		if (this.eastNeedOpen == true) {
			this.eastOpenString = Database.roomOpenString[Database.roomNames.valueOf(nameEnum).ordinal()][Database.directions.EAST.ordinal()];
		}

		this.westNeedOpen = Database.roomNeedOpen[Database.roomNames.valueOf(nameEnum).ordinal()][Database.directions.WEST.ordinal()];
		if (this.westNeedOpen == true) {
			this.westOpenString = Database.roomOpenString[Database.roomNames.valueOf(nameEnum).ordinal()][Database.directions.WEST.ordinal()];
		}
		// set up containers and enemies
		setContainer(Database.roomContainers[Database.roomNames.valueOf(nameEnum).ordinal()]);
		setEnemies(Database.roomEnemies[Database.roomNames.valueOf(nameEnum).ordinal()]);

		this.enterCutscene = Database.cutscene[Database.roomNames.valueOf(nameEnum).ordinal()];
		this.enterCutsceneHappened = false;
		this.afterBattleCutScene = Database.cutscene2[Database.roomNames.valueOf(nameEnum).ordinal()];
	}

	// link rooms in each direction of this room
	public void setRooms(Room north, Room south, 
			Room east, Room west) {
		this.north = north;
		this.east = east;
		this.south = south;
		this.west = west;
	}

	// play a cutscene
	public void playEnterCutscene() {
		// checks if the cutscene happen of not, and also if array is empty
		if (this.enterCutsceneHappened == false && this.enterCutscene.length != 0) {
			System.out.println(this.name);
			System.out.println("(Press enter to start)");
			// runs through array list and prints message then prompts to press enter
			for (int i = 0; i < this.enterCutscene.length; i++) {
				Main.in.nextLine();
				autoPrintln(this.enterCutscene[i]);
				System.out.println("(Press enter)");
			}
			this.enterCutsceneHappened = true;
			Main.in.nextLine();
		}

	}

	public void afterBattleCutScene() {
		// plays only if cutscene is not empty array
		if (this.afterBattleCutScene.length != 0) {
			System.out.println(this.name + " Cutscene.");
			System.out.println("(Press enter to start)");
			// runs through array list and prints message then prompts to press enter
			for (int i = 0; i < this.afterBattleCutScene.length; i++) {
				Main.in.nextLine();
				autoPrintln(this.afterBattleCutScene[i]);
				System.out.println("(Press enter)");
			}
			Main.in.nextLine();
		}	
	}

	// returns back room in that direction
	public Room move(String direction) {
		String direction1 = direction.toLowerCase().strip();
		// checks if this area is open to go to yet
		if (direction1.equals("north") && this.northNeedOpen == false) {
			return this.north;
		} else if (direction1.equals("south") && this.southNeedOpen == false) {
			return this.south;
		} else if (direction1.equals("east") && this.eastNeedOpen == false) {
			return this.east;
		} else if (direction1.equals("west") && this.westNeedOpen == false) {
			return this.west;
		} else {
			// if it doesn't match to these scenarios it returns null
			return null;
		}
	}


	public String toString() {
		return new String("You are at the " + this.name + ".");
	}

	// allows player to open a container, or something that needs to be open to go through
	public boolean open(String str) {
		boolean didOpen = false;
		// if it matches up to string to open this end it will make it so the north end is open
		if (this.northNeedOpen == true) {
			if (str.strip().equalsIgnoreCase(this.northOpenString)) {
				System.out.println("You opened the " + this.northOpenString + ".");
				this.northNeedOpen =false;
				didOpen = true;
			}
			// if it matches up to string to open this end it will make it so the south end is open
		}
		if (this.southNeedOpen == true) {
			if (str.strip().equalsIgnoreCase(this.southOpenString)) {
				System.out.println("You opened the " + this.southOpenString + ".");
				this.southNeedOpen =false;
				didOpen = true;
			}

		}
		// if it matches up to string to open this end it will make it so the east end is open
		if (this.eastNeedOpen == true) {
			if (str.strip().equalsIgnoreCase(this.eastOpenString)) {
				System.out.println("You opened the " + this.eastOpenString + ".");
				this.eastNeedOpen =false;
				didOpen = true;
			}

		}
		// if it matches up to string to open this end it will make it so the west end is open
		if (this.westNeedOpen == true) {
			if (str.strip().equalsIgnoreCase(this.westOpenString)) {
				System.out.println("You opened the " + this.westOpenString + ".");
				this.westNeedOpen =false;
				didOpen = true;
			}

		}
		// this will check to see if the string matches up a container
		for ( int i = 0; i < this.containersInRoom.size(); i++ ) {
			Container curContainer = this.containersInRoom.get(i);
			if (str.strip().equalsIgnoreCase(curContainer.getName())) {
				curContainer.openContainer();
				didOpen = true;
			}
		}
		// if it's a empty string send this
		if (str.equals("")) {
			System.out.println("Sorry you need to type in the object you want to open.");
		} else if (didOpen == false) {
			// if it matches to nothing it sends this and they didn't sent blank strirng
			System.out.println("You didn't find what you wanted to open.");
		}
		return didOpen;

	}

	// allows player to search room 
	public boolean search(String containerName) {
		// goes through container list and if the input it matches the name it will search the container
		for ( int i = 0; i < this.containersInRoom.size(); i++ ) {
			Container curContainer = this.containersInRoom.get(i);
			if (containerName.strip().equalsIgnoreCase(curContainer.getName())) {
				boolean searchSuccess;
				searchSuccess = curContainer.searchContainer(this);
				// if it matches it will take out the items and put it into room 
				if (searchSuccess == true) {
					curContainer.takeItemsOut(this);
				}
			}
		}
		return false;
	}

	// allows player to take out items
	public boolean take(String str, Entity entity) {
		boolean itemFound = false;
		// does this if weaponlist in room isn't empty
		if (this.weaponInRoom.isEmpty() == false) {
			// looks through weapon list and if it matches the object is put into there inventory and removed from room
			for (int i = 0; i < this.weaponInRoom.size(); i++) {
				Weapon curWeapon = this.weaponInRoom.get(i);
				if (str.strip().equalsIgnoreCase(curWeapon.getItemName())) {
					System.out.println(entity.getName() + " took " + curWeapon.getItemName() + ".");
					entity.bag.addItem(curWeapon);
					this.weaponInRoom.remove(curWeapon);
					itemFound = true;
					break;
				}
			}
		}
		// does this if potionlist in room isn't empty
		if (this.potionInRoom.isEmpty() == false) {
			for (int i = 0; i < this.potionInRoom.size(); i++) {
				Potion curPotion = this.potionInRoom.get(i);
				// looks through potion list and if it matches the object is put into there inventory and removed from room
				if (str.strip().equalsIgnoreCase(curPotion.getItemName())) {
					System.out.println(entity.getName() + " took " + curPotion.getItemName() + ".");
					entity.bag.addItem(curPotion);
					this.potionInRoom.remove(curPotion);
					itemFound = true;
					break;
				}
			}
		}
		return itemFound;
	}

	// returns string with the room description and all the containers description
	public void look() {
		String str;
		str = this.description;

		for (int i = 0; i < this.containersInRoom.size(); i++) {
			str += containersInRoom.get(i).toString();
		}
		// can delete all this
		
		if (!this.weaponInRoom.isEmpty()) {
			str += " There are weapons in the room you can see which are a ";
			for (int i =0 ; i < this.weaponInRoom.size(); i++) {
				if (i == this.weaponInRoom.size() -1) {
					str += "and a " + this.weaponInRoom.get(i).getItemName().toLowerCase() + ".";
					break;
				} 
				str += this.weaponInRoom.get(i).getItemName().toLowerCase() + ", ";
			}
			
		}
		
		if (!this.potionInRoom.isEmpty()) {
			str += " There are also potions in the room which are a ";
			for (int i =0 ; i < this.potionInRoom.size(); i++) {
				if (i == this.potionInRoom.size() -1) {
					str += "and a " + this.potionInRoom.get(i).getItemName().toLowerCase() + ".";
					break;
				} 
				str += this.potionInRoom.get(i).getItemName().toLowerCase() + ", ";
			}
		}
		
		autoPrintln(str);
	}

	// this will print the given string but will move to nextline if string exceeds a cetain length
	private void autoPrintln(String string) {

		String[] strs = string.split(" ");

		int lnLevel = 60;
		int currentLengthInLine = 0;

		for (int i = 0; i < strs.length; i++) {
			currentLengthInLine += strs[i].length() + 1; 
			System.out.print(strs[i] + " ");

			if (currentLengthInLine/lnLevel > 1) {
				currentLengthInLine = 0;
				System.out.println();
			}
		}
	}
	// will take container strings and make containers then add to container listt
	private void setContainer(String[] containers) {
		for ( int i = 0; i < containers.length; i++) {
			this.containersInRoom.add(new Container(containers[i]));
		}
	}
	// will take enemy strrings and make enemies then add to enemy list
	private void setEnemies(String[] enemies) {
		if (enemies.length > 0) {
			for ( int i = 0; i < enemies.length; i++) {
				this.enemiesInRoom.add(Main.eFact.getEnemy(enemies[i]));
			}
		} 
	}

	// getter and setters
	public ArrayList<Weapon> getWeaponInRoom() {
		return weaponInRoom;
	}


	public ArrayList<Potion> getPotionInRoom() {
		return potionInRoom;
	}

	public ArrayList<Enemy> getEnemiesInRoom() {
		return enemiesInRoom;
	}

	// moves all drops from container to room
	public void moveItemsDrops() {
		for (int i = 0; i < this.enemiesInRoom.size(); i++) {
			Enemy currentEnemy = this.enemiesInRoom.get(i);

			for (int i2 = 0; i2 < currentEnemy.getWeaponDrops().size(); i2++) {
				this.weaponInRoom.add(currentEnemy.getWeaponDrops().get(i2));
			}

			for (int i3 = 0; i3 < currentEnemy.getPotionDrops().size(); i3++) {
				this.potionInRoom.add(currentEnemy.getPotionDrops().get(i3));
			}
		}
	}

	// allows player to take drops after battle
	public void annouceEnemyDrops() {
		int count = 0;
		System.out.println("Enemy Drops: ");
		// print tostring on all items in room
		for (int i = 0; i < this.weaponInRoom.size(); i++) {
			System.out.println(++count + ". " + this.weaponInRoom.get(i).getItemName());
		}

		for (int i = 0; i < this.potionInRoom.size(); i++) {
			System.out.println(++count + ". " + this.potionInRoom.get(i).getItemName());
		}
	}

	// prompt user to take drops 
	public void takeEnemyDrops(Entity entity) {
		boolean askAgain = true;
		String userChoice;
		System.out.println("Now you get to pick from the spoils.");
		System.out.println("(Press enter to continue)");
		System.out.println();
		Main.in.nextLine();

		while (askAgain == true) {
			annouceEnemyDrops();
			// prompt user to decide
			System.out.println();
			System.out.println("You don't have to type [] just what's in [].");
			System.out.println("Type [All] to receive all drops.");
			System.out.println("Type [take ] then [name of drop] to take individiual drop.");
			System.out.println("Type [exit] the exit.");
			System.out.println("(Then all drops will be added to the room)");
			System.out.println();
			userChoice = Main.in.nextLine().strip().toUpperCase();
			// input is all , all drops are added to inventory 
			if (userChoice.equals("ALL")) {
				System.out.println("All drops are added to your inventory.");
				// moves weapons to entity inventory
				if (this.weaponInRoom.isEmpty() == false) {
					for (int i = 0; i < this.weaponInRoom.size(); i++) {
						Weapon curWeapon = this.weaponInRoom.get(i);
						entity.bag.addItem(curWeapon);
					}
				}
				// moves potions to entity inventory
				if (this.potionInRoom.isEmpty() == false) {
					for (int i = 0; i < this.potionInRoom.size(); i++) {
						Potion curPotion = this.potionInRoom.get(i);
						entity.bag.addItem(curPotion);
					}
				}
				// this happens if there are no drops
				if (this.weaponInRoom.isEmpty() && this.potionInRoom.isEmpty()) {
					System.out.println("There are no items in drop list");
				}
				this.weaponInRoom.clear();
				this.potionInRoom.clear();
				askAgain = false;
				// to exit this
			} else if (userChoice.equals("EXIT")) {
				askAgain = false;
				// if it isn't any of the above it will make the player take it
			} else if (!userChoice.equals("ALL")) {
				
				// splits into array with max size of two
				String[] dungeonInteract = userChoice.split(" ", 2);
				String command = dungeonInteract[0];
				String input = null;
				
				if (dungeonInteract.length > 1) {
					input = dungeonInteract[1].strip();
				} else {
					input = "";
				}
				// checks if player said take
				if (command.equalsIgnoreCase("take")) {
					// will take item if string matches
					if (this.take(input, entity) == false) {
						System.out.println("That is not a drop on the list.");
					}	
				}
			}
		}
	}

	public String getName() {
		return name;
	}


	public void setEnemiesInRoom(ArrayList<Enemy> enemiesInRoom) {
		this.enemiesInRoom = enemiesInRoom;
	}



}
