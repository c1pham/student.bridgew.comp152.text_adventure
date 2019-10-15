package edu.bridgew.cis.comp152.inheritance.project2.item.equipment.bow;

import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;

public abstract class Bow extends Weapon {
	// field for bow
	protected double critRateChange;
	// constructor
	public Bow(String itemName, String element, double damage,
			double atkChange, double critDamageChange, double critRateChange) {
		super(itemName, 100f, 100f, "Bow", 
				damage,
				atkChange,
				critDamageChange,
				"Range", element);
		this.critRateChange = critRateChange;

	}


}
