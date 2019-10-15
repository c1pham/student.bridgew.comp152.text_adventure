package edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.elves;

import edu.bridgew.cis.comp152.inheritance.project2.ability.Ability;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;

public abstract class Elf extends Enemy {
	// special attribute of elf
	protected double curElementalShield;
	protected double origElementalShield;
	// constructor
	public Elf(String name, String entityClass, char gender, double level, String element, double origFireResist,
			double origIceResist, double origLightingResist, double origATK, double origDef, double origMDef, double origSpeed,
			double origHealth, double origAccuracy, double origCritDmg, double origCritRate,
			Ability[] abilities, Weapon weapon, double elementalShield) {
		
		super(name, entityClass , gender, level , element, origFireResist, origIceResist,  
				origLightingResist, origATK, origDef, origMDef, origSpeed, origHealth,
				origAccuracy,origCritDmg, origCritRate, abilities, weapon);
		
		this.curElementalShield = elementalShield;
		this.origElementalShield = elementalShield;
	
	}
}
