package edu.bridgew.cis.comp152.inheritance.project2.item.consumable;

import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.item.Item;

public abstract class Consumable extends Item {

	// attribute of consumable
	protected String consumableType;
	// constructor


	public Consumable(String itemName, String typeConsumable, double price, double weight) {
		super(itemName, "Consumable", price, weight);
		this.consumableType = typeConsumable;
		this.canEquip = false;
		this.Equip = false;
	}
	// this is suppose to be effect of usingItem

	// this is suppose to be called so when item is used it disappears when suppose to
	public abstract void deleteItemAfterUse(Entity entity);

}
