package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

public class Taunt extends Ability {
	public Taunt() {
		super("Taunt",
				Database.abilityStatD[ Database.ability.ENRAGE.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.ENRAGE.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}

	@Override
	public boolean useAbility(Entity self, Entity target) {
		// reduces stamina
		self.setStaminaBar(self.getStaminaBar() - this.reqStamina);
		boolean isDead = false;
	
		System.out.println(self.getName() + " uses Taunt! " + target.getName()  +" is angry and his Attack increased but defense and magic defense decrease.");
		// change target's stats
		target.setCurrentAtk(target.getCurrentAtk() * (1 + this.abilityStat));
		// reduces attack if atk is increased too much
		if ( target.getCurrentAtk() > (target.getOrigATK() * 1.5)) {
			target.setCurrentAtk(target.getOrigATK() * 1.5);
		}
		target.setCurrentDef(target.getCurrentDef() * this.abilityStat);
		target.setCurrentMDef(target.getCurrentMDef() * this.abilityStat);
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
		return new String("Ability Name: " + this.abilityName + "\tStamina Cost: " + this.reqStamina 
				+ "\tDescription: Boost the target ATK, but target's decrease DEF, and MAG DEF.");

	}

	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		// TODO Auto-generated method stub
		return false;
	}

}
