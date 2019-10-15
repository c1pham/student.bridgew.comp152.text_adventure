package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

public class Cleanse extends Ability {
	public Cleanse() {
		super("Cleanse",
				Database.abilityStatD[ Database.ability.CLEANSE.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.CLEANSE.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}
	// use ability to reset target's stats
	@Override
	public boolean useAbility(Entity self, Entity target) {
		self.setStaminaBar(self.getStaminaBar() - this.reqStamina);
		boolean isDead = false;
		System.out.println(self.getName() + " cleanse "+ target.getName() +"'s stat changes are remove.");
		// increase target's defense
		double beforeHp = target.getCurrentHealth();
		target.resetBattleStats();
		target.setCurrentHealth(beforeHp);
		return isDead;
	}
	// checks if entity has enough stamina to use ability
	@Override
	public boolean checkStamina(Entity entity) {
		// if enough stamina return true
		if (entity.getStaminaBar() >= this.reqStamina) {	
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return new String("Ability Name: " + this.abilityName + "\tStamina Cost: " + this.reqStamina 
				+ "\tDescription: Boost the DEF on the user.");
	}
	// empty don't need to use
	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		// TODO Auto-generated method stub
		return false;
	}

}
