package edu.bridgew.cis.comp152.inheritance.project2.item.equipment.gun;

import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;

public abstract class Gun extends Weapon {
	// gun specific attribute
	protected double scopeAccuracy;
	
	
	public Gun(String itemName,String element, double damage, double atkChange, double critDamageChange, double scopeAccuracy) {
		super(itemName, (100*damage)/5, (100*damage)/5, "Gun", damage, atkChange, critDamageChange, "Range", element);
		this.scopeAccuracy = scopeAccuracy;
	}
	
	
}
