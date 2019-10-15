package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

public class FireArrow extends Ability {


	public FireArrow() {
		super("Fire Arrow",
				Database.abilityStatD[ Database.ability.FIRE_ARROW.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.FIRE_ARROW.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}

	@Override
	public boolean useAbility(Entity user,Entity target) {
		user.setStaminaBar(user.getStaminaBar() - this.reqStamina);
		boolean isDead = false;
		String battleString;
		// specs array for take dmg
		String damageType = "Melee";
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
		// determine if entity crit or not
		if (user.getCurrentCritRate() >= critChance) {
			didCrit = true;
		} else if (user.getCurrentCritRate() < critChance) {
			didCrit = false;
		}
		float hitChance;
		boolean didHit = false;
		// random number use to generate chance of hitting or criting
		hitChance = chance.nextFloat();
		// determines if you hit and set didhit
		if (user.getCurrentAccuracy() >= hitChance) {
			didHit = true;
		} else if (user.getCurrentAccuracy() < hitChance) {
			didHit = false;
		}
		// this happens if you crit
		if (didHit == true) {
			// determines dmg
			if (didCrit == true) {
				dmgCount = (user.getCurrentAtk() * this.abilityStat) + target.getCurrentDef() + user.getWeapon().getDamage()  + user.getCurrentCritDmg();
			} else if (didCrit == false) {
				dmgCount = (user.getCurrentAtk() * this.abilityStat) + target.getCurrentDef() + user.getWeapon().getDamage();
			}
			battleString = user.getName() + " shoots a fire arrow and it hits.";
			// if crit this will print
			if (didCrit == true) {
				battleString += " It's a critical hit!";
			}

			System.out.println(battleString);
			// will change dmg to make sure it is atleast 1
			isDead = target.takeDmg(dmgSpecs, dmgCount);

			return isDead;

		} else {
			// this happens if you miss
			System.out.println(user.getName() + " used Fire Arrow and missed.");
			return false;
		}

	}
	// checks if entity can use the skill
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
				+ this.reqStamina + "\tDescription: " + "Shoots a fire arrow and ignores defense.");
	}

	// this is not coded because it's not needed to be coded
	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		return false;
	}
}
