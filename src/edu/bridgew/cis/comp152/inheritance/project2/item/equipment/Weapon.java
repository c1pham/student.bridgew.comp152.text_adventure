package edu.bridgew.cis.comp152.inheritance.project2.item.equipment;

import edu.bridgew.cis.comp152.inheritance.project2.Entity;

public abstract class Weapon extends Equipment {
	// attribute of weapon
	protected String weaponType;
	protected double damage;
	protected double atkChange;
	protected double critDamageChange;
	protected String damageType;
	protected String element;



	public Weapon(String itemName, double price, double weight, String weaponType, double damage, double atkChange, double critDamageChange,
			String damageType, String element) {
		super(itemName, "Equipment", price, weight, "Weapon");
		this.weaponType = weaponType;
		this.damage = damage;
		this.atkChange = atkChange;
		this.critDamageChange = critDamageChange;
		this.damageType = damageType;
		this.element = element;
	}

	

	// getter and setters
	public String getDamageType() {
		return damageType;
	}

	public String getElement() {
		return element;
	}


	public String getWeaponType() {
		return weaponType;
	}

	public double getDamage() {
		return damage;
	}

	public double getAtkChange() {
		return atkChange;
	}

	public double getCritDamageChange() {
		return critDamageChange;
	}
}
