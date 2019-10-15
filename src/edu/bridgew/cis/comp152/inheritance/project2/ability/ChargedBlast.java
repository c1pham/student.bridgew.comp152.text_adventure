package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

// commented
public class ChargedBlast  extends Ability {
	public ChargedBlast() {
		super("Charged Blast",
				Database.abilityStatD[ Database.ability.CHARGED_BLAST.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.CHARGED_BLAST.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}
	// what happens when it's used
	@Override
	public boolean useAbility(Entity user,Entity target) {
		// reduce stamina
		user.setStaminaBar(user.getStaminaBar() - this.reqStamina);
		boolean isDead = false;
		String battleString;
		// specs for take dmg
		String damageType = user.getWeapon().getDamageType();
		String element = user.getWeapon().getElement();
		//above two variables will be store in this array
		String[] dmgSpecs = new String[2];
		dmgSpecs[0] = damageType;
		dmgSpecs[1] = element;

		double dmgCount = 0;
		// to calculate if you crit or hit
		boolean didCrit = false;
		float critChance;
		float hitChance;
		Random chance = new Random();
		// random number use to generate chance of hitting or criting
		critChance = chance.nextFloat();
		boolean didHit = false;

		// random number use to generate chance of hitting or criting
		hitChance = chance.nextFloat();
		// determines if you hit and set didhit
		if (user.getCurrentAccuracy() >= hitChance) {
			didHit = true;
		} else if (user.getCurrentAccuracy() < hitChance) {
			didHit = false;
		}

		// happens if you hit
		if (didHit == true) {
			// determine if you crit
			if (user.getCurrentCritRate() >= critChance) {
				didCrit = true;
			} else if (user.getCurrentCritRate() < critChance) {
				didCrit = false;
			}
			// calculates dmg
			if (didCrit == true) {
				dmgCount = (user.getCurrentAtk() * this.abilityStat) + user.getWeapon().getDamage()  + user.getCurrentCritDmg();
			} else if (didCrit == false) {
				dmgCount = (user.getCurrentAtk() * this.abilityStat) + user.getWeapon().getDamage();
			}

			battleString = user.getName() + " charged his " + user.getWeapon().getItemName() + " and released a powerful blast.";
			// if crit this will print
			if (didCrit == true) {
				battleString += " It's a critical hit!";
			}

			System.out.println(battleString);
			// will change dmg to make sure it is atleast 1
			isDead = target.takeDmg(dmgSpecs, dmgCount);

			return isDead;

		} else {
			System.out.println(user.getName() + " used Charged Blast and missed.");
			return isDead;
		}
	}
	// checks if entity has enough stamina to use, if not return false
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
				+ this.reqStamina + "\tDescription: " + "Charges weapon then fires a powerful blast.");
	}
	// empty cuz no need to code
	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		return true;
	}


}
