package edu.bridgew.cis.comp152.inheritance.project2.item.consumable;

import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.item.ItemID;

public class StaminaPotion extends Potion {
	protected double staminaRecAmount;

	public StaminaPotion(String itemName, double staminaRecAmount) {
		super(itemName, "Stamina Potion", 100, 5);
		this.staminaRecAmount = staminaRecAmount;
		createItemID();
	}
	// deletes item after use from inventory
	@Override
	public void deleteItemAfterUse(Entity entity) {
		entity.getBag().removeItem(this);
	}

	@Override
	protected void createItemID() {
		ItemID itemID = new ItemID("Consumable", "Potion", "StaminaPotion");
		this.itemID = itemID;
	}

	@Override
	public String toString() {
		return new String(this.itemName + "\tItem Type: " + this.itemType + "\nStamina Recovery Amount: " 
				+ this.staminaRecAmount + "\tPrice: " + this.price);
	}

	@Override
	public void useItem(Entity entity) {
		double currentStam = entity.getStaminaBar();
		double lostStam = 100 - currentStam;
		String healAmount = "";
		// heals stamina depending on how much is currently lost
		if (lostStam >= this.staminaRecAmount) {
			entity.setStaminaBar(entity.getStaminaBar() + this.staminaRecAmount);
			healAmount += this.staminaRecAmount;
		} else if (lostStam < this.staminaRecAmount) {
			// if their is more stamina from potion to heal than stamina difference it will heal the difference
			entity.setStaminaBar(entity.getStaminaBar() + lostStam);
			healAmount += lostStam;
		}
		System.out.println(this.getItemName() + " is used and " + entity.getName() 
		+ " has recovered " + healAmount + " stamina points.");
	}
}
