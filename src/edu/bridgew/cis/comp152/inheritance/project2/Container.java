package edu.bridgew.cis.comp152.inheritance.project2;
import java.util.ArrayList;

import edu.bridgew.cis.comp152.inheritance.project2.item.consumable.Potion;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;

// commented
public class Container {
	// attributes of container
	private String name;
	private String description;
	private ArrayList<Weapon> weaponsContained;
	private ArrayList<Potion> potionsContained;
	private boolean needOpened;
	private boolean searched;
	// constructor
	public Container (String name) {
		this.name = name;
		searched = false;
		String enumString = name.toUpperCase().replaceAll(" ", "_");
		this.description = Database.containerDescript[Database.containers.valueOf(enumString).ordinal()];
		this.needOpened = Database.containerNeedOpen[Database.containers.valueOf(enumString).ordinal()];
		this.weaponsContained = new ArrayList<Weapon>();
		this.potionsContained = new ArrayList<Potion>();
		setItems(Database.containerItem[Database.containers.valueOf(enumString).ordinal()]);
	}

	public String toString() {
		return this.description;
	}
	// base on strings given will make weapons and items and add to arraylist of weapon and potions
	public void setItems(String[] containerItems) {
		// list of weapon names
		String[] weaponName = {"Long Bow", "Shark Stick", "Plasmata Rifle", "Ion Cannon",
				"Sharp Stick", "Hero Blade", "Power Bow", "Executioner Blade",  "Bamboo Stick",
				"Tree Branch", "Coconut Launcher", "Water Gun", "Robot Arm", "Elven Club", "Elven Bow",
				"Elven Sword", "Firewood", "Big Tuna", "Guardian Blade", "Meteor Blade", "Twig", "Deer Bone",
				"Robot Leg", "Bronze Leg", "Revolver", "Old Bow", "Guardian Bow", "Guardian Revolver", "Iron Mace",
				"Elven Shotgun", "Missle Launcher"};
		// list of portion names
		String[] potionName = {
				"Small Health Potion", "Medium Health Potion",
				"Large Health Potion", "Temporary Health Potion",
				"Pernament Health Potion", "Small Stamina Potion",
				"Medium Stamina Potion", "Large Stamina Potion",
				"Large Temporary Health Potion", "Super Health Potion"
		};
		// loops through container items and if the name is equal to the names of weapons or potions it will add it to the weapon or potion list
		for (int i = 0; i < containerItems.length; i++) {
			for (int i2 = 0; i2 < weaponName.length; i2++) {
				if (weaponName[i2].equals(containerItems[i])) {
					this.weaponsContained.add(Main.iFact.getWeapon(weaponName[i2]));
				}
			}

			for (int i3 = 0; i3 < potionName.length; i3++) {
				if (potionName[i3].equals(containerItems[i])) {
					this.potionsContained.add(Main.iFact.getPotion(potionName[i3]));
				}
			}
		}
	}

	// opens container if it needs to be
	public void openContainer() {	
		if (this.needOpened == true) {
			System.out.println(this.name + " is opened.");
			this.needOpened = false;
		} else if (this.needOpened == false) {
			System.out.println(this.name +" does not need to be opened.");
		}
	}

	// searches container once it's opened, then moves items to room
	public boolean searchContainer(Room room) {
		boolean didSearch = false;
		// this in case you already searched it
		if (this.searched == true) {
			System.out.println("You already searched this.");
			return false;
		}
		// this is what happens if it's needs to be opened
		if (this.needOpened == true) {
			System.out.println("This " + this.name + " is closed and you can't search it yet.");
			didSearch = false;
			// if it doesn't need to be open it'll open and give items
		} else if (this.needOpened == false) {
			System.out.println(this.name + " is searched.");
			System.out.println("You searched and found");
			System.out.println("_________________________________");
			int currentItem = 1;
			// displays to user what they found
			for ( int i = 0; i < this.weaponsContained.size(); i++) {
				System.out.println( currentItem++ + ". " + this.weaponsContained.get(i).getItemName());
			}
			for ( int i = 0; i < this.potionsContained.size(); i++) {
				System.out.println(currentItem++ + ". " + this.potionsContained.get(i).getItemName());
			}
			// makes it so it's searched is true
			this.searched = true;
			takeItemsOut(room);
			didSearch = true;
		}
		// returns to user so you know it's used
		return didSearch;
	}

	// this takes items out of container and adds it to the room
	public void takeItemsOut(Room room) {
		// adds weapons to rooms
		for (int i = 0; i < this.weaponsContained.size(); i++) {
			room.getWeaponInRoom().add(this.weaponsContained.get(i)); 
		}
		// adds potions to room
		for (int i = 0; i < this.potionsContained.size(); i++) {
			room.getPotionInRoom().add(this.potionsContained.get(i)); 
		}
		// makes these list cleared since items are moved
		this.weaponsContained.clear();
		this.potionsContained.clear();
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Weapon> getWeaponsContained() {
		return weaponsContained;
	}

	public ArrayList<Potion> getPotionsContained() {
		return potionsContained;
	}


	public boolean isNeedOpened() {
		return needOpened;
	}

	public void setNeedOpened(boolean needOpened) {
		this.needOpened = needOpened;
	}


}
