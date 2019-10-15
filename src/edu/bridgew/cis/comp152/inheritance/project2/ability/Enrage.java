package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;


public class Enrage extends Ability {

	public Enrage() {
		super("Enrage",
				Database.abilityStatD[ Database.ability.ENRAGE.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.ENRAGE.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}

	@Override
	public boolean useAbility(Entity self, Entity target) {
		// reduce stamina
		self.setStaminaBar(self.getStaminaBar() - this.reqStamina);
		boolean isDead = false;
		// change user stats
		System.out.println(self.getName() + " uses ENRAGED!" +" Attack increased by 200%. Defense decrease by 50%. Magic Defense decrease by 50%.");
		self.setCurrentAtk(self.getOrigATK() * this.abilityStat);
		self.setCurrentDef(self.getCurrentDef() / this.abilityStat);
		self.setCurrentMDef(self.getCurrentMDef() / this.abilityStat);
		
		System.out.println();
		// change target stats
		System.out.println(target.getName() + " is caution and his attack has decrease.");
		target.setCurrentAtk(target.getCurrentAtk() - (target.getLevel() * this.abilityStat));

		return isDead;
	}

	// determines if entity has enough stamina to use skill
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
				+ "\tDescription: Boost the users ATK, decrease DEF, and MAG DEF. Decrease the targets attack.");

	}

	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		// empty cuz no need to code this one
		return false;
	}

}
