package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.Main;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

public class MechanicalReproduction extends Ability {
	public MechanicalReproduction() {
		super("Mechanical Reproduction",
				Database.abilityStatD[ Database.ability.MECHANICAL_REPRODUCTION.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.MECHANICAL_REPRODUCTION.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
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
				+ "\tSpawn more mechas.");
	}

	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		// reduces entity stamina
		self.setStaminaBar(self.getStaminaBar() - this.reqStamina);

		System.out.println(self.getName() + " used mechanical reproduction.");
		Random rand = new Random();
		// random generate the amount of level ups
		int mechaNum = rand.nextInt((int)this.abilityStat);
		for (int i = 0; i < (mechaNum + 1) ; i++) {
			// will alternate between spawning hatchlings and juveniles
			if (i%2 == 1) {
				enemies.add(Main.eFact.getEnemy("hatchling@1"));
			} else if (i%2 == 0) {
				enemies.add(Main.eFact.getEnemy("juvenile@5"));
			}
		}
		return false;
	}

}
