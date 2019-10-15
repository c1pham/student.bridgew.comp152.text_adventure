package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

public class Restore extends Ability {

	public Restore() {
		super("Recover",
				Database.abilityStatD[ Database.ability.RESTORE.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.RESTORE.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}

	@Override
	public boolean useAbility(Entity self, Entity target) {
		// reduce stamina
		self.setStaminaBar(self.getStaminaBar() - this.reqStamina);
		boolean isDead = false;
		
		double healthDifference = target.getHealthWithChanges() - target.getCurrentHealth();
		String skilldescript = self.getName() + " used restore";
		// heals depending on health difference
		if (healthDifference != 0) {
			// it heals a portion of the healthdifference
			target.setCurrentHealth( target.getCurrentHealth() + (target.getHealthWithChanges() * this.abilityStat));
			if (target.getCurrentHealth() > target.getHealthWithChanges()) {
				// if current heal is over hp cap then hp will be reduced
				target.setCurrentHealth(target.getHealthWithChanges());
			}
			skilldescript += " and healed a portion of " + target.getName() + " health.";
		}
		System.out.println(skilldescript);
		
		return isDead;
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
				+ this.reqStamina + "\tDescription: " + "Restores a portion of target lost health.");

	}

	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		// TODO Auto-generated method stub
		return false;
	}

}
