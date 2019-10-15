package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;
// commented
public class Camoflage extends Ability {

	public Camoflage() {
		super("Camoflage",
				Database.abilityStatD[ Database.ability.CAMOFLAGE.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.CAMOFLAGE.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}
	// use ability
	@Override
	public boolean useAbility(Entity self, Entity target) {
		// reduce stamina
		self.setStaminaBar(self.getStaminaBar() - this.reqStamina);
		
		String skillDescript= "";
		
		skillDescript += self.getName() + " used camoflage! ";
		// reduce target's accuracy
		if (target.getCurrentAccuracy() > .25) {
			skillDescript += target.getName() + "'s accuracy is reduced!";
			target.setCurrentAccuracy(target.getCurrentAccuracy()- this.abilityStat);
			// can't reduce under .25
			if (target.getCurrentAccuracy() < .25) {
				target.setCurrentAccuracy(.25);
			}
			// reduce accuracy 
		} else if (self.getCurrentAccuracy() <= .25) {
			skillDescript += target.getName() + "'s accuracy cannot be reduced anymore.";
		}
		
		System.out.println(skillDescript);
		
		return false;
	}
	// checks if entity has enough stamina to use skill, if not return false
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
				+ this.reqStamina + "\tDescription: " + "Reduces target's accuracy (Accuracy cannot be reduce under 20% and cannot miss).");
	}
	// empty cause no need to use
	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		return false;
	}
}
