package edu.bridgew.cis.comp152.inheritance.project2;

import java.util.ArrayList;

import edu.bridgew.cis.comp152.inheritance.project2.item.Item;
import edu.bridgew.cis.comp152.inheritance.project2.item.consumable.Potion;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;

public class Inventory {
	// commented
	// all items are held here
	private ArrayList<Item> itemList;
	private int currentNumItems;
	// items are also stored here but as different objects like weapon or potion so we can retreive a weapon or potion in the object form of a weapon
	private ArrayList<Weapon> weaponList;
	private ArrayList<Potion> potionList;

	// constructors
	public Inventory () {
		itemList = new ArrayList<Item>();
		weaponList = new ArrayList<Weapon>();
		potionList = new ArrayList<Potion>();
		updateCurrentNumItems();
	}
	// getter and setter
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	public int getCurrentNumItems() {
		return currentNumItems;
	}
	// this constructor will create the item already at a certain size
	public Inventory (int inventorySize) {
		itemList = new ArrayList<Item>(inventorySize);
		weaponList = new ArrayList<Weapon>();
		potionList = new ArrayList<Potion>();
		updateCurrentNumItems();
	}
	// adds a item to itemlist
	public void addItem(Item item) {
		this.itemList.add(item);
		this.currentNumItems += 1;
	}

	//  adds a weapon to the itemlist and weapon list
	public void addItem(Weapon weapon) {
		this.itemList.add(weapon);
		this.weaponList.add(weapon);
		this.currentNumItems += 1;
	}
	// adds a potion to itemlist and potion  list
	public void addItem(Potion potion) {
		this.itemList.add(potion);
		this.potionList.add(potion);
		this.currentNumItems += 1;
	}

	// takes items ID code and finds where is it's equivalent in weapons list then returns the equivalent to the item that that is a weapon 
	public Weapon getMatchingWeapon(Item item) {
		for (Weapon w: weaponList) {
			if (w.equals(item)) {
				return w;
			}
		}
		System.out.println("No item found.");
		return null;
	}
	
	// takes items ID code and finds where is it's equivalent in potion list then returns the equivalent to the item that that is a potion
	public Potion getMatchingPotion(Item item) {
		for (Potion p: potionList) {
			if (p.equals(item)) {
				return p;
			}
		}
		System.out.println("No item found.");
		return null;
	}

	// prints out all items
	public void displayInventory() {
		if (this.itemList.isEmpty()) {
			System.out.println("This inventory is empty.");
		} else {
			System.out.println("Inventory");
			System.out.println("________________________________________");
			System.out.println();
			for ( int i = 0; i< this.itemList.size(); i++) {
				System.out.println((i+1) + ". " + this.itemList.get(i).toString());
				System.out.println();
			}
		}
	}
	
	// this is method that will take a list of items then if you put in the number of the item on the list after it's been displayed
	// then you can pick the item and it'll return you that item
	public Item findItem(String itemNum, ArrayList<Item> matchList) {
		String refinedInput = itemNum.replaceAll(" ", "");
		Item foundItem = null;

		int[] intArray = new int[1000];

		String[] stringNumArray;

		String[] intSpelledArray = new String[1000];

		int numIndex = -5;

		// this creates array of integer 0 -999
		for (int i = 0; i < intArray.length ; i++ ) {
			intArray[i] = i;
		}
		
		stringNumArray = spellIntArray(intArray);
		// this creates an array of numbers spelled out in case the user puts in words for numbers
		for (int i = 0; i < intArray.length; i++) {
			intSpelledArray[i] = transformNumToWord(intArray[i]);
		}
		// this looks through the list and will assign a int value number to numindex
		// this is basically where it could be access in matchlist
		for ( int i = 0; i < matchList.size() + 1; i++) {
			if (refinedInput.equalsIgnoreCase(stringNumArray[i])) {
				numIndex = intArray[i] - 1;

			} else if (refinedInput.equalsIgnoreCase(intSpelledArray[i])) {
				numIndex = intArray[i] - 1;	
			}
		}
		// if no number was assigned or the user put in zero or 0 then they won't get a item
		if (numIndex == -5 || refinedInput.equalsIgnoreCase("0") || refinedInput.equalsIgnoreCase("zero")) {
			System.out.println("Please pick a different number this is not a number on the list.");	
		} else {
			// gets the item  wanted from the number given
			Item entryFound = matchList.get(numIndex);
			// compares all itemIDCodes then will return the item  with the matching code
			for ( int i = 0; i < this.itemList.size(); i++) {
				Item listItem = itemList.get(i);
				
				if (listItem.equals(entryFound)) {
					foundItem = listItem;
				}
			}
		}	
		return foundItem;
	}

	// this will remove the item, from a list with the num of the item as a parameter
	public boolean removeItem(String itemNumRemove , ArrayList<Item> matchList) {
		// removes all spaces
		String refinedInput = itemNumRemove.replaceAll(" ", "");
		boolean foundEntry = false;
		// arrays used to figure out the number value of what the user typed  in
		int[] intArray = new int[1000];
		String[] stringNumArray;
		String[] intSpelledArray = new String[1000];
		// this is set to negative 5 so if nothing is found it will be a infidcator that nothing is found
		int numIndex = -5;

		// this creates array of integer 0 -999
		for (int i = 0; i < intArray.length ; i++ ) {
			intArray[i] = i;
		}
		stringNumArray = spellIntArray(intArray);
		// this creates an array of numbers spelled out in case the user puts in words for numbers
		for (int i = 0; i < intArray.length; i++) {
			intSpelledArray[i] = transformNumToWord(intArray[i]);
		}
		// this will loop through the list of ints in string version and ints spelled out
		// and if it finds match it wil assign a value to numindex which will be where the item could be found on the matchlist
		for ( int i = 0; i < matchList.size() + 1; i++) {
			if (refinedInput.equalsIgnoreCase(stringNumArray[i])) {
				numIndex = intArray[i] - 1;

			} else if (refinedInput.equalsIgnoreCase(intSpelledArray[i])) {
				numIndex = intArray[i] - 1;
			}
		}
		// if the player puts in the wrong input then they will be told to pick a different number and a boolean if returned
		// so program can determine a entry was not found
		if ( numIndex == -5 || refinedInput.equalsIgnoreCase("0") || refinedInput.equalsIgnoreCase("zero")) {
			System.out.println("Please pick a different number.");
			foundEntry = false;
		} else {
			// if numindex was assigned a value then it will figure out and find what item to delete then delete
			Item entryFound = matchList.get(numIndex);
			//loops though all items in itemlist to find item and then determine if it's a weapon  or potion
			for ( int i = 0; i < this.itemList.size(); i++) {
				Item listItem = itemList.get(i);

				if (listItem.equals(entryFound)) {

					if (listItem.getItemID().getSubItemType1().equalsIgnoreCase("Weapon")) {
						// it will search the weapon list and removes the found item from the weaponlist if it's a weapon
						for ( int i2 = 0; i2 < this.weaponList.size(); i2++) {
							Weapon weaponFL;
							weaponFL = this.weaponList.get(i2);
							if (listItem.equals(weaponFL)) {
								this.weaponList.remove(i2);

							}
						}
					} else if (listItem.getItemID().getSubItemType1().equalsIgnoreCase("Potion")) {
						// it will search the potion list and removes the found item from the potion if it's weapon
						for ( int i2 = 0; i2 < this.potionList.size(); i2++) {
							Potion potionFL;
							potionFL = this.potionList.get(i2);
							if (listItem.equals(potionFL)) {
								this.potionList.remove(i2);
							}
						}
					}
					// removes item from itemlist
					this.removeItem(listItem);
					foundEntry = true;
				}
			}
		}	
		return foundEntry;
	}
	
	
	// this code will take a array of integers then make a array of numbers in string form
	public String[] spellIntArray(int[] numArray) {
		String[] spellIntsArray = new String[1000];
		for (int i = 0; i < numArray.length ; i++ ) {
			spellIntsArray[i] = Integer.toString(numArray[i]);
		}

		return spellIntsArray;
	}
	// this code will take a integer then create the written word version of it like one or twenty two
	public String transformNumToWord (int i) {
		String returnString = "";
		// these are arrays used to take number and turn it into a word version of it
		String[] nums = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven",
				"twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
		String[] tensPlace = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
		String[] hundredthPlace = {"one hundred", "two hundred", "three hundred", "four hundred" , "five hundred", "six hundred", 
				"seven hundred", "eight hundred", "nine hundred"};
		String[] onesPlace = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		String numString;
		// digit array is used to break up string number and look at each digit
		String[] digitArray;
		numString = Integer.toString(i);
		// this splits it so isolate each digit
		digitArray = numString.split("");

		// this will applie to numbers 0-99
		if (numString.length() < 3) { 
			// this sequence is ran if the number is between 0 and 19, this helps since the tens digits are like fifteen and sixteen, rather than twenty two 
			// or twenty one
			if (i < 20) {
				returnString = nums[i];
				// this will make the spelled out version of number if the number is between 20 qnd 99
			} else if ( i > 19 && i < 100) {
				String tensP;
				int tensIndex;
				tensP = digitArray[0];
				tensIndex = Integer.valueOf(tensP) - 1 ;
				String oneDPlace;
				int onesDigitIndex;
				oneDPlace = digitArray[1];
				onesDigitIndex = Integer.valueOf(oneDPlace);
				returnString = tensPlace[tensIndex - 1] + " " + onesPlace[onesDigitIndex];
			}
			// this assigns value if the number has 3 digits to create string of the number spelled out
		}else if (numString.length() == 3) {
			String hundredP;
			int hundredIndex;
			hundredP = digitArray[0];
			hundredIndex = Integer.valueOf(hundredP) - 1 ;

			returnString = returnString + hundredthPlace[hundredIndex];

			String tensP;
			int tensIndex;
			tensP = digitArray[1];
			tensIndex = Integer.valueOf(tensP) - 1 ;

			String twoOneDigits;
			int numsIndex;
			twoOneDigits = numString.substring(1);
			numsIndex = Integer.valueOf(twoOneDigits);
			// this accounts for the tens place having the teens like sixteen
			if ((tensIndex == -1 || tensIndex == 0) && numsIndex < 20 ) {
				returnString = returnString + " " + nums[numsIndex];
				// this accounts for 20s place
			} else if (tensIndex > 0) {
				String oneDigit;
				int onesDigitIndex;
				oneDigit = digitArray[2];
				onesDigitIndex = Integer.valueOf(oneDigit);
				returnString = returnString + " " + tensPlace[tensIndex - 1] + " " + nums[onesDigitIndex];
			}	
		}
		return returnString;	
	}
	
	// this removes a item from the itemlist and weapon or potion from just the item itself
	// it will use the items itemID to determine what to remove
	public void removeItem(Item removeItem) {
		boolean foundItem = false;
		// if inventory is empty this msg will display
		if (this.itemList.size() == 0 ) {
			System.out.println("Nothing is in the inventory to delete.");
		} else {
			// this will loop the itemlist to find a match to the parameter item
			for (int i = 0; i< this.itemList.size(); i++) {
				Item listItemCID = this.itemList.get(i);
				Item removeItemCID = removeItem;
				// once it finds a match it will see if the item is equip or not
				if (listItemCID.equals(removeItemCID)) {
					// if the item is equipped it'll tell the user to take off the item first
					if (this.itemList.get(i).isEquip() == true) {
						foundItem = true;
						// TODO create this feature in all forms of item deletion for weapon.
						System.out.println("This item is equipped and cannot be removed until it is de-equiped.");
					} else {
						// if the item is not equip then the item will be removed
						foundItem = true;
						// if it's weapon it'll be remove from potion list
						if (removeItem.getItemID().getSubItemType1().equalsIgnoreCase("Weapon")) {
							for ( int i2 = 0; i2 < this.weaponList.size(); i2++) {
								Weapon weaponFL;
								weaponFL = this.weaponList.get(i2);
								if (removeItem.equals(weaponFL)) {
									this.weaponList.remove(i2);
								}
							}
							// if it is a potion it will be remove from  potion list
						} else if (removeItem.getItemID().getSubItemType1().equalsIgnoreCase("Potion")) {
							for ( int i2 = 0; i2 < this.potionList.size(); i2++) {
								Potion potionFL;
								potionFL = this.potionList.get(i2);
								if (removeItem.equals(potionFL)) {
									this.potionList.remove(i2);
								}
							}
						}
						// item is removed
						this.itemList.remove(i);
						updateCurrentNumItems();
					}
				}
			}
			// if not item is found this displays
			if (foundItem == false) {
				System.out.println("No Item is found to remove.");
			}
		}
	}


	// updates the current number of items in item list
	public void updateCurrentNumItems() {
		if (itemList.isEmpty()) {
			currentNumItems  = 0;
		} else {
			for (int i = 0; i< this.itemList.size(); i++) {
				this.currentNumItems = itemList.size();
			}
		}
	}

	// this can search by name, or item types, will return a list of all that matches
	public ArrayList<Item> searchInventory(String userInput) {
		ArrayList<Item> matchList = new ArrayList<Item>();
		String compareItemName;

		if (userInput.equalsIgnoreCase("Equipment")) {
			matchList = equipmentList();
		} else if (userInput.equalsIgnoreCase("Consumable")) {
			matchList = consumableList();
		} else if (userInput.equalsIgnoreCase("Weapon")) {
			String currentItemType1;
			// find all weapons then then adds it to match list
			for (int i = 0; i < this.itemList.size(); i++) {
				currentItemType1 = this.itemList.get(i).getItemID().getSubItemType1();
				if (currentItemType1.equalsIgnoreCase("Weapon")) {
					matchList.add(this.itemList.get(i));
				}
			}
			// find all potion then adds it to match list
		} else if (userInput.equalsIgnoreCase("Potion")) {
			String currentItemType1;
			for (int i = 0; i < this.itemList.size(); i++) {
				currentItemType1 = this.itemList.get(i).getItemID().getSubItemType1();
				if (currentItemType1.equalsIgnoreCase("Potion")) {
					matchList.add(this.itemList.get(i));
				}
			}
		} else {
			// it isn't a defined item type then it'll will match it by name
			for (int i = 0; i < this.itemList.size(); i++) {
				compareItemName = this.itemList.get(i).getItemName();
				if (findPartialMatch(userInput, compareItemName)) {
					matchList.add(this.itemList.get(i));
				}
			}
		}
		if (matchList.size() == 0 ) {
			System.out.println("There are no matches.");
		} else {
			// displays item found
			System.out.println("Items Found:");
			System.out.println("________________________________________");
			System.out.println();
			for ( int i = 0; i < matchList.size(); i++) {
				System.out.println((i + 1) + ". " + matchList.get(i).toString());
				System.out.println();
			}
		}
		return matchList;
	}

	// this will compare two strings and see if there are any partial matches
	public boolean findPartialMatch(String userInput, String compareString ) {
		int userInt = userInput.length();
		int compareInt = compareString.length();
		// base cases
		if (userInt >= compareInt) {
			// if input is greater size string being compared
			return userInput.equalsIgnoreCase(compareString);
		} else if (userInput.equalsIgnoreCase(compareString.substring(0, userInt))) {
			return true;
		} else {
			// if it doesn't either any of these cases call recursive again , it will compare the userinput to the string but 
			// one index over
			return findPartialMatch(userInput, compareString.substring(1));
		}
	}
	
	// give list of all consumables
	public ArrayList<Item> consumableList() {
		ArrayList<Item> consumableList = new ArrayList<Item>();
		// loop through itemlist then add to consumable list
		for ( int i = 0; i< this.itemList.size(); i++ ) {
			if (this.itemList.get(i).getItemID().getItemType().equalsIgnoreCase("Consumable")) {
				consumableList.add(this.itemList.get(i));
			}
		}
		if (consumableList.size() == 0) {
			return null;
		} else {
			return consumableList;
		}
	}
	// returns a list of all equipment
	public ArrayList<Item> equipmentList() {
		ArrayList<Item> equipmentList = new ArrayList<Item>();
		for ( int i = 0; i< this.itemList.size(); i++ ) {
			if (this.itemList.get(i).getItemID().getItemType().equalsIgnoreCase("Equipment")) {
				equipmentList.add(this.itemList.get(i));
			}
		}
		if (equipmentList.size() == 0) {
			return null;
		} else {
			return equipmentList;
		}
	}
	//displays all items that are equipment
	public void displayEquipment () {
		int numEquipment = 0;
		System.out.println("Equipment List: ");
		System.out.println("________________________________________");
		System.out.println();
		// goes through item list finds equipment and displays it
		for ( int i = 0; i< this.itemList.size(); i++ ) {
			if (this.itemList.get(i).getItemID().getItemType().contentEquals("Equipment")) {
				System.out.println((numEquipment + 1) + ". " + itemList.get(i).toString());
				System.out.println();
				numEquipment +=1;
			}
		}
		if (numEquipment == 0 ) {
			System.out.println("There is currently no equipment in inventory.");
		}
	}
	// displays all items that are consumable
	public void displayConsumables () {
		int numConsumable = 0;
		System.out.println("Consumables List: ");
		System.out.println("________________________________________");
		System.out.println();
		// go through item list find all consumable and present it
		for ( int i = 0; i< this.itemList.size(); i++ ) {
			if (this.itemList.get(i).getItemID().getItemType().contentEquals("Consumable")) {
				System.out.println((numConsumable+1) + ". " + itemList.get(i).toString());
				System.out.println();
				numConsumable +=1;
			}
		}
		
		if (numConsumable == 0) {
			System.out.println("There is currently no consumables in inventory.");
		}
	}
	
	// clear all arraylist fields
	public void emptyInventory() {
		this.itemList.clear();
		this.weaponList.clear();
		this.potionList.clear();
		updateCurrentNumItems();
	}
}
