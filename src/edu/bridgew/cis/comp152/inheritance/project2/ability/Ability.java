package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;

import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

// commented
public abstract class Ability {
	// ability attributes
	protected String abilityName;
	protected double reqStamina;
	protected double abilityStat;
	//  constructor
	public Ability (String abilityName, double reqStamina, double abilityStat) {
		this.abilityName = abilityName;
		this.reqStamina = reqStamina;
		this.abilityStat = abilityStat;
	}

	// use ability 
	public abstract boolean useAbility(Entity self, Entity target);
	// overload in case use ability needs enemy list
	public abstract boolean useAbility(Entity self, ArrayList<Enemy> enemies);	
	// check if entity can use ability
	public abstract boolean checkStamina(Entity entity);

	public abstract String toString();
	// getter and setter
	public String getAbilityName() {
		return abilityName;
	}

	public double getReqStamina() {
		return reqStamina;
	}

}
