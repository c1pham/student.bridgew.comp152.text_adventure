package edu.bridgew.cis.comp152.inheritance.project2.item;

import edu.bridgew.cis.comp152.inheritance.project2.Entity;

public abstract class Item {
	// attributes of item
	protected String itemType;
	protected String itemName;
	protected double weight;
	protected double price;
	protected ItemID itemID;
	protected boolean canEquip;
	protected boolean Equip;

	// constructor
	public Item(String itemName, String itemType, double price, double weight) {
		this.itemType = itemType;
		this.itemName = itemName;
		this.price = price;
		this.weight = weight;
	}

	// this must be called in any instantiated class
	// will be used to search for items in inventory
	abstract protected void createItemID();

	// getter and setter
	public String getItemType() {
		return itemType;
	}

	public String getItemName() {
		return itemName;
	}

	public double getWeight() {
		return weight;
	}

	public double getPrice() {
		return price;
	}

	public abstract String toString();

	public ItemID getItemID() {
		return itemID;
	}
	
	public boolean isCanEquip() {
		return canEquip;
	}
	
	public boolean isEquip() {
		return Equip;
	}
	public void setEquip(boolean equip) {
		Equip = equip;
	}
	
	public abstract void useItem(Entity entity);
}
