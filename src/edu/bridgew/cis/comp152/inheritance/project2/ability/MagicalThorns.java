package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

public class MagicalThorns extends Ability {
	public MagicalThorns() {
		super("Magical Thorns",
				Database.abilityStatD[ Database.ability.MAGICAL_THORNS.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.MAGICAL_THORNS.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}
	
	@Override
	public boolean useAbility(Entity user,Entity target) {
		user.setStaminaBar(user.getStaminaBar() - this.reqStamina);
		boolean isDead = false;
		String battleString;
		// specs array for takedmg
		String damageType = "Magic";
		String element = "None";
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
			// calculate dmgcount
			if (didCrit == true) {
				dmgCount = (user.getCurrentAtk() * this.abilityStat)  + user.getCurrentCritDmg();
			} else if (didCrit == false) {
				dmgCount = (user.getCurrentAtk() * this.abilityStat);
			}
			 battleString = user.getName() + " uses Magical Thorns and strangles "+ target.getName() +" and drains some life from him.";
			// if crit this will print
			if (didCrit == true) {
				battleString += " It's a critical hit!";
			}
			
			
			System.out.println(battleString);
			// will change dmg to make sure it is atleast 1
			double healAmount = 0;
			// keep track of change in hp
			double beforeHP =  target.getCurrentHealth();
			isDead = target.takeDmg(dmgSpecs, dmgCount);
			double afterHP =  target.getCurrentHealth();
			double enemyHPDiff = beforeHP - afterHP;
			double userHPDiff = user.getHealthWithChanges() - user.getCurrentHealth();
			// will heal the user but not over hp cap 
			if (userHPDiff >= enemyHPDiff) {
				healAmount = enemyHPDiff/this.abilityStat;
			} else if (userHPDiff < enemyHPDiff) {
				healAmount = userHPDiff/this.abilityStat;
			}
			user.setCurrentHealth(user.getCurrentHealth() + healAmount);
			return isDead;
			
		} else {
			// this happens if you miss
			System.out.println(user.getName() + " used Magical Thorns and missed.");
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
				+ this.reqStamina + "\tDescription: " + "Strangles target with thorns and drains life.");
	}

	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		// TODO Auto-generated method stub
		return false;
	}

}
