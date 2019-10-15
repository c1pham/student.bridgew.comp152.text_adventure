package edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.animal;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.ability.Ability;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;

public abstract class Animal extends Enemy {
	protected boolean tamed;
	protected boolean canTame;
	protected Entity rider;

	public Animal(String name, String entityClass, char gender, double level, String element, double origFireResist,
			double origIceResist, double origLightingResist, double origATK, double origDef, double origMDef,
			double origSpeed, double origHealth, double origAccuracy, double origCritDmg, double origCritRate,
			Ability[] abilities, Weapon weapon) {
		super(name, entityClass, gender, level, element, origFireResist, origIceResist, origLightingResist, origATK, origDef,
				origMDef, origSpeed, origHealth, origAccuracy, origCritDmg, origCritRate, abilities, weapon);
	}
	
	abstract void increaseRiderStats();
}
