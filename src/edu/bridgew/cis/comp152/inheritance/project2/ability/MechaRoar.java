package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

public class MechaRoar extends Ability {
	
	public MechaRoar() {
		super("Mecha Roar",
				Database.abilityStatD[ Database.ability.MECHA_ROAR.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.MECHA_ROAR.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
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
				+ this.reqStamina + "\tDescription: " + "Releases a energy beam that cannot miss, and boost ATK, Crit Rate, Crit Dmg.");

	}

	@Override
	public boolean useAbility(Entity self, Entity target) {
		String[] dmgSpecs = new String[2];
		dmgSpecs[0] = "Range";
		dmgSpecs[1] = "Energy";
		// take dmg specs
		double dmgCount = self.getCurrentAtk();
		boolean didCrit = false;
		float critChance;
		Random chance = new Random();
		// random number use to generate chance of hitting or criting
		critChance = chance.nextFloat();
		// determines if you crit
		if (self.getCurrentCritRate() >= critChance) {
			didCrit = true;
		} else if (self.getCurrentCritRate() < critChance) {
			didCrit = false;
		}
		if (didCrit == true) {
			dmgCount += self.getCurrentCritDmg();
		}
		dmgCount *= this.abilityStat;
		
		System.out.println(self.getName() + " charges up increasing stats then releases an energy wave");
		// increase stats 
		self.setCurrentAtk(self.getCurrentAtk() + self.getCurrentAtk()*this.abilityStat);
		self.setCurrentCritRate(self.getCurrentCritRate() + self.getCurrentCritRate()*this.abilityStat);
		self.setCurrentCritDmg(self.getCurrentCritDmg() + self.getCurrentCritDmg()*this.abilityStat);
		// takes dmg
		boolean isDead = target.takeDmg(dmgSpecs, dmgCount);
		return isDead;
	}

	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		// TODO Auto-generated method stub
		return false;
	}
}
