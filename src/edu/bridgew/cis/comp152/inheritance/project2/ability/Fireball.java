package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

public class Fireball extends Ability {
	public Fireball() {
		super("Fireball",
				Database.abilityStatD[ Database.ability.FIRE_BALL.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.FIRE_BALL.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}
	
	@Override
	public boolean useAbility(Entity user,Entity target) {
		user.setStaminaBar(user.getStaminaBar() - this.reqStamina);
		boolean isDead = false;
		String battleString;
		// specs for take dmg
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
		// determine if entity crit
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
		
		if (didHit == true) {
			// determines dmg for this attack
			if (didCrit == true) {
				dmgCount = (user.getCurrentAtk() * this.abilityStat)  + user.getCurrentCritDmg();
			} else if (didCrit == false) {
				dmgCount = (user.getCurrentAtk() * this.abilityStat);
			}
			 battleString = user.getName() + " uses Fireball on "+ target.getName() +"!";
			// if crit this will print
			if (didCrit == true) {
				battleString += " It's a critical hit!";
			}
			System.out.println(battleString);
			// determines dfmg you taken
			isDead = target.takeDmg(dmgSpecs, dmgCount);
			
			return isDead;
			// this happens if entity miss
		} else {
			System.out.println(user.getName() + " used Fireball and missed.");
			return false;
		}

	}
	// checks if entity has stamina to use skill. if not returns false
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
				+ this.reqStamina + "\tDescription: A magical thunder bolt is summoned and hits the target.");
	}

	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		return false;
	}

}
