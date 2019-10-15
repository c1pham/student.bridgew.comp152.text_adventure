package edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword;

import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;

public abstract class Sword extends Weapon {
	// special attribute for sword
	protected double speedIncrease;
	// constructors

	public Sword(String itemName,double price, double weight, double damage, double atkChange, double critDamageChange,
			double speedIncrease, String element) {
		super(itemName, price, weight, "Sword", damage, atkChange, critDamageChange, "Melee", element);
		this.atkChange = atkChange;
		this.critDamageChange = critDamageChange;
		this.speedIncrease = speedIncrease;
	}
}
