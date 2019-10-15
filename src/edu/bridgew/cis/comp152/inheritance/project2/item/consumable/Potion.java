package edu.bridgew.cis.comp152.inheritance.project2.item.consumable;


public abstract class Potion extends Consumable{
	// constructors
	public Potion(String itemName, String potionType, double price, double weight) {
		super(itemName, "Potion", price, weight);
	}
}
