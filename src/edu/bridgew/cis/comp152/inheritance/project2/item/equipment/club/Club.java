package edu.bridgew.cis.comp152.inheritance.project2.item.equipment.club;

import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;

public abstract class Club extends Weapon {
	// field for club
	protected double speedDecrease;

	public Club(String itemName,double price, double weight, double damage, double atkChange, double critDamageChange,
			double speedDecrease, String element) {
		super(itemName, price, weight, "Sword", damage, atkChange, critDamageChange, "Melee", element);
		this.speedDecrease = speedDecrease;
	}

}
