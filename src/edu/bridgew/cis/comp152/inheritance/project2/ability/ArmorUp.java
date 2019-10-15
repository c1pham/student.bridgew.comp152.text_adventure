package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

// commented
public class ArmorUp extends Ability {
	public ArmorUp() {
		super("Armor Up",
				Database.abilityStatD[ Database.ability.ARMOR_UP.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.ARMOR_UP.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}
	// use ability increase defense and magic defense of target
	@Override
	public boolean useAbility(Entity self, Entity target) {
		self.setStaminaBar(self.getStaminaBar() - this.reqStamina);
		boolean isDead = false;
		
		System.out.println(self.getName() + " uses Armor Up "+ target.getName() +"'s and Defense and Magical Defense increased.");
		// increase target's defense
		target.setCurrentDef(self.getCurrentDef() * this.abilityStat);
		target.setCurrentMDef(self.getCurrentMDef() * this.abilityStat);
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
