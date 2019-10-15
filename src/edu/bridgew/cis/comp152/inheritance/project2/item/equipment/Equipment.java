package edu.bridgew.cis.comp152.inheritance.project2.item.equipment;

import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.item.Item;

public abstract class Equipment extends Item {
	protected String equipmentType;
	protected double level = 1;
	// constructors
	
	public Equipment(String itemName, String itemType, double price, double weight, String equipmentType) {
		super(itemName, itemType, price, weight);
		this.equipmentType = equipmentType;
		this.canEquip = true;
		this.Equip = false;
	}
	// to equip the item
	protected abstract void equipStatChanges(Entity entity);
	// to remove the stat changes when de equip
	protected abstract void removeStatChanges(Entity entity);
	// setter
	public void setEquip (boolean isEquip) {
		this.Equip = isEquip;
	}
}
