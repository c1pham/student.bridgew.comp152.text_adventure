package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

public class Combustion extends Ability {
	public Combustion() {
		super("Combustion",
				Database.abilityStatD[ Database.ability.COMBUSTION.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.COMBUSTION.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}

	@Override
	public boolean useAbility(Entity user,Entity target) {
		// reduce entity stamina
		user.setStaminaBar(user.getStaminaBar() - this.reqStamina);
		boolean isDead = false;
		String battleString;
		// for take dmg specs
		String damageType = "Magic";
		String element = "Fire";
		//above two variables will be store in this array
		String[] dmgSpecs = new String[2];
		dmgSpecs[0] = damageType;
		dmgSpecs[1] = element;

		double dmgCount = 0;
		boolean didCrit = false;
		float critChance;
		Random chance = new Random();
		// random number use to generate chance of hitting or criting
		critChance = chance.nextFloat();
		// determine if you crit
		if (user.getCurrentCritRate() >= critChance) {
			didCrit = true;
		} else if (user.getCurrentCritRate() < critChance) {
			didCrit = false;
		}

		float hitChance;
		// random number use to generate chance of hitting or criting
		boolean didHit = false;
		// random number use to generate chance of hitting or criting
		hitChance = chance.nextFloat();
		// determines if you hit and set didhit
		if (user.getCurrentAccuracy() >= hitChance) {
			didHit = true;
		} else if (user.getCurrentAccuracy() < hitChance) {
			didHit = false;
		}
		// if hit do this
		if (didHit == true) {
			// these if then statements determine dmg
			if (didCrit == true) {
				dmgCount = (user.getCurrentAtk() * this.abilityStat)  + user.getCurrentCritDmg();
			} else if (didCrit == false) {
				dmgCount = (user.getCurrentAtk() * this.abilityStat);
			}
			battleString = user.getName() + " uses combustion and started burning and ran into "+ target.getName() +"!";
			// if crit this will print
			if (didCrit == true) {
				battleString += " It's a critical hit!";
			}
			// reduce user health
			user.setCurrentHealth(user.getCurrentHealth() * .1);
			System.out.println(battleString);	
			// dmg enemy
			isDead = target.takeDmg(dmgSpecs, dmgCount);
			return isDead;

		} else {
			// happens if you missed
			System.out.println(user.getName() + " used Combustion and missed. " + user.getName() + " is burning and barely survived.");
			return false;
		}

	}
	// checks if entity can use this stamina if  yes return true
	@Override
	public boolean checkStamina(Entity entity) {
		if (entity.getStaminaBar() >= this.reqStamina) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return new String("Ability Name: " + this.abilityName + "\tStamina Cost: " 
				+ this.reqStamina + "\tDescription: The user breaks out in flames and does tremendous fire damage to the target but damages himself.");
	}

	// empty cause theres no need to code this one
	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		return false;
	}

}
