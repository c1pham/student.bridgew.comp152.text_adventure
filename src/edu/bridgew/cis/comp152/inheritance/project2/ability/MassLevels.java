package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

public class MassLevels extends Ability {
	public MassLevels() {
		super("Mass Levels",
				Database.abilityStatD[ Database.ability.MASS_LEVEL.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.MASS_LEVEL.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}

	@Override
	public boolean useAbility(Entity self, Entity target) {
		return false;
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
				+ "\tLevel up mechas.");
	}

	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		// reduce stamina
		self.setStaminaBar(self.getStaminaBar() - this.reqStamina);
		boolean isDead = false;
		System.out.println(self.getName() + 
				" used mass level up. All enemies on field leveled up by " + this.abilityStat + ".");

		// levels up all enemies in the move list
		for (int i = 0; i < enemies.size() ; i++) {
			for (int i2 = 0 ; i2 < this.abilityStat; i2 ++) {
				// makes sure that certain stats don't go back to full if status effects were done
				double currentHp = enemies.get(i).getCurrentHealth();
				double currentAcc = enemies.get(i).getCurrentAccuracy();
				double currentAtt = enemies.get(i).getCurrentAtk();
				double currentDef = enemies.get(i).getCurrentDef();
				double currentMDef = enemies.get(i).getCurrentMDef();
				
				enemies.get(i).levelUp();
				enemies.get(i).setCurrentHealth(currentHp);
				enemies.get(i).setCurrentAccuracy((enemies.get(i).getCurrentAccuracy() - (currentAcc)) + currentAcc);
				enemies.get(i).setCurrentAtk((enemies.get(i).getCurrentAtk() - (currentAtt)) + currentAtt);
				enemies.get(i).setCurrentDef((enemies.get(i).getCurrentDef() - (currentDef)) + currentDef);
				enemies.get(i).setCurrentDef((enemies.get(i).getCurrentMDef() - (currentMDef)) + currentMDef);
			}
		}
		return isDead;
	}

}
