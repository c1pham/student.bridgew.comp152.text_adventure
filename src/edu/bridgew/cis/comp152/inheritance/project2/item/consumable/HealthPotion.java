package edu.bridgew.cis.comp152.inheritance.project2.item.consumable;

import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.item.ItemID;

public class HealthPotion extends Potion {
	// attribute of potion
	protected double pernamentHealthChange;
	protected double temporaryHealthChange;
	protected double healAmount;
	// constructor


	public HealthPotion(String itemName, double price, double weight, double pernamentHealthChange, double temporaryHealthChange,
			double healAmount) {
		super(itemName, "Health Potion", price, weight);
		this.pernamentHealthChange = pernamentHealthChange;
		this.temporaryHealthChange = temporaryHealthChange;
		this.healAmount = healAmount;
		// item Id must be created so each item has a unique ID to identify it with
		createItemID();
	}


	// this is so when this potion is used. this method can be called within use consumable
	@Override
	public void deleteItemAfterUse(Entity entity) {
		entity.getBag().removeItem(this);
		// might have to delete that 
	}
	// creates a unique Id for the health potion
	@Override
	protected void createItemID() {
		ItemID itemID = new ItemID("Consumable", "Potion", "HealthPotion");
		this.itemID = itemID;
	}
	// represents potion data depending on what they potion stats are.
	@Override
	public String toString() {
		String descriptionString = this.itemName + "\tItem Type: Potion ";
		// change orig hp
		if (pernamentHealthChange != 0) {
			descriptionString += "\nPernament Health Boost: " + this.pernamentHealthChange;
		}
		// change health with changes
		if (temporaryHealthChange != 0) {
			descriptionString += "\nTemporary Health Boost: " + this.temporaryHealthChange;
		}
		// heals hp
		if (healAmount != 0) {
			descriptionString += "\nHeal Amount: " + this.healAmount;
		}

		descriptionString += "\tPrice: " + this.price;
		return descriptionString;
	}
	@Override
	public void useItem(Entity entity) {
		// increase original health if possible
		entity.setOrigHealth(entity.getOrigHealth() + this.pernamentHealthChange);
		// increase health if possible
		entity.setHealthWithChanges(entity.getHealthWithChanges() + this.temporaryHealthChange + this.pernamentHealthChange);
		double healthDifference;
		// the difference between the max health and current heal 
		healthDifference = entity.getHealthWithChanges() - entity.getCurrentHealth();
		// display message depending on potion stat 
		System.out.println(entity.getName() + " used " + this.itemName + ".");
		if (pernamentHealthChange != 0) {
			System.out.println("Health is increased pernamenetly by " + this.pernamentHealthChange + " HP points." );
		}
		if (temporaryHealthChange != 0) {
			System.out.println("Health is temporarily increased by " + this.temporaryHealthChange + " HP points.");
		}
		// this determines how much is healed
		// if the heal amount is over the amount of health lost it will heal the difference between current and current max hp
		// if the heal amount is less than the amount of lost health it will heal full amount
		if (healthDifference >= this.healAmount ) {
			entity.setCurrentHealth(entity.getCurrentHealth() + this.healAmount);
			System.out.println("Health is healed by " + this.healAmount + " HP points." );
		} else if (healthDifference < this.healAmount ) {
			entity.setCurrentHealth(entity.getCurrentHealth() + healthDifference );
			//entity.setCurrentHealth(entity.getCurrentHealth() + healthDifference);

			// if hp is full this happens
			if (healthDifference == 0) {
				System.out.println("Unable to heal anymore HP is at max.");
			} else {
				System.out.println("Health is healed by " + healthDifference + " HP points."  );
			}
		}
	}
}
