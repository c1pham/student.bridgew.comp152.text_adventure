package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

public class SmackDown extends Ability {
	public SmackDown() {
		super("Smack Down",
				Database.abilityStatD[ Database.ability.SMACK_DOWN.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.SMACK_DOWN.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}

	@Override
	public boolean useAbility(Entity self, Entity target) {
		self.setStaminaBar(self.getStaminaBar() - this.reqStamina);
		boolean isDead = false;

		String battleString;
		// specs for take dmg
		String damageType = "Melee";
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

		if (self.getCurrentCritRate() >= critChance) {
			didCrit = true;
		} else if (self.getCurrentCritRate() < critChance) {
			didCrit = false;
		}

		float hitChance;

		// random number use to generate chance of hitting or criting

		boolean didHit = false;

		// random number use to generate chance of hitting or criting
		hitChance = chance.nextFloat();
		// determines if you hit and set didhit
		if (self.getCurrentAccuracy() >= hitChance) {
			didHit = true;
		} else if (self.getCurrentAccuracy() < hitChance) {
			didHit = false;
		}

		if (didHit == true) {
			// determines dmgcount
			if (didCrit == true) {
				dmgCount = (self.getCurrentAtk() * this.abilityStat) + self.getWeapon().getDamage()  + self.getCurrentCritDmg();
			} else if (didCrit == false) {
				dmgCount = (self.getCurrentAtk() * this.abilityStat) + self.getWeapon().getDamage();
			}
			battleString = self.getName() + " uses Smack Down on " + target.getName() + " his Def and Magic Def is decreased.";
			// if crit this will print
			if (didCrit == true) {
				battleString += " It's a critical hit!";
			}

			System.out.println(battleString);
			// will change dmg to make sure it is atleast 1
			isDead = target.takeDmg(dmgSpecs, dmgCount);
			target.setCurrentDef(target.getCurrentDef() * (1 - this.abilityStat/10));
			target.setCurrentMDef(target.getCurrentMDef() * (1 - this.abilityStat/10));
			return isDead;

		} else {
			// this happens if you miss 
			System.out.println(self.getName() + " used Smack Down and missed.");
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
		return new String("Ability Name: " + this.abilityName + "\tStamina Cost: " + this.reqStamina 
				+ "\tDescription: Damages the target and reduces target's decrease DEF, and MAG DEF.");

	}

	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		// TODO Auto-generated method stub
		return false;
	}

}
