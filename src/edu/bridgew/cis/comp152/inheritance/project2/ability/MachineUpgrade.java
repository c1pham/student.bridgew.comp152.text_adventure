package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

public class MachineUpgrade extends Ability {
	public MachineUpgrade() {
		super("Machine Upgrade",
				Database.abilityStatD[ Database.ability.MACHINE_UPGRADE.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.MACHINE_UPGRADE.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}

	@Override
	public boolean useAbility(Entity user,Entity target) {
		user.setStaminaBar(user.getStaminaBar() - this.reqStamina);
		// loops and levels up the entity
		for ( int i = 0 ; i < this.abilityStat ; i++) {
			user.levelUp();
		}
		System.out.println(user.getName() + " used Machine Upgrade and leveled up " + this.abilityStat + " times.");
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
		return new String("Ability Name: " + this.abilityName + "\tStamina Cost: " 
				+ this.reqStamina + "\tDescription: " + "levels Up " + this.abilityStat +" times.");
	}

	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		// TODO Auto-generated method stub
		return false;
	}

}
