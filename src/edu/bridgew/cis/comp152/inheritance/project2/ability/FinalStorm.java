package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

// commented
public class FinalStorm extends Ability {
	public FinalStorm() {
		super("Final Storm",
				Database.abilityStatD[ Database.ability.ELEMENTAL_BARRAGE.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.ELEMENTAL_BARRAGE.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
	}

	// determines if entity can use this skill returns true if it does
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
				+ "\tDescription: Does many many arrow attacks.");

	}

	@Override
	public boolean useAbility(Entity self, Entity target) {
		self.setStaminaBar(self.getStaminaBar() - this.reqStamina);
		boolean isDead = false;
		// specs for each attack
		String[] normalHitSpecs1 = new String[2];
		normalHitSpecs1[0] = "Range";
		normalHitSpecs1[1] = "Fire";
		String[] normalHitSpecs2 = new String[2];
		normalHitSpecs2[0] = "Range";
		normalHitSpecs2[1] = "Ice";	
		String[] normalHitSpecs3 = new String[2];
		normalHitSpecs3[0] = "Range";
		normalHitSpecs3[1] = "Lighting";
		String[] normalHitSpecs4 = new String[2];
		normalHitSpecs4[0] = "Range";
		normalHitSpecs4[1] = "Energy";

		float hitChance1;
		float critChance1;
		float hitChance2;
		float critChance2;
		float hitChance3;
		float critChance3;
		float hitChance4;
		float critChance4;

		Random chance = new Random();
		// random number use to generate chance of hitting or criting
		critChance1 = chance.nextFloat();
		critChance2 = chance.nextFloat();
		critChance3 = chance.nextFloat();
		critChance4 = chance.nextFloat();
		hitChance1 = chance.nextFloat();
		hitChance2 = chance.nextFloat();
		hitChance3 = chance.nextFloat();
		hitChance4 = chance.nextFloat();


		boolean didHit1 = false;
		boolean didHit2 = false;
		boolean didHit3 = false;
		boolean didHit4 = false;
		boolean didCrit1 = false;
		boolean didCrit2 = false;
		boolean didCrit3 = false;
		boolean didCrit4 = false;
		// store boolean for it crit or not
		boolean[] crits = new boolean[4];
		crits[0] = didCrit1;
		crits[1] = didCrit2;
		crits[2] = didCrit3;
		crits[3] = didCrit4;
		// store chance for crit
		float[] critC = new float[4];
		critC[0] = critChance1;
		critC[1] = critChance2;
		critC[2] = critChance3;
		critC[3] = critChance4;	
		// stores boolean for it hit or not
		boolean[] hits = new boolean[4];
		hits[0] = didHit1;
		hits[1] = didHit2;
		hits[2] = didHit3;
		hits[3] = didHit4;
		// stores random chance to be compared to accuracy 
		float[] hitC = new float[4];
		hitC[0] = hitChance1;
		hitC[1] = hitChance2;
		hitC[2] = hitChance3;
		hitC[3] = hitChance4;
		// string to display when move is done
		String[] atkDescript = new String[4];
		atkDescript[0] = "100 Fire Arrows!";
		atkDescript[1] = "100 Ice Arrows!";
		atkDescript[2] = "100 Lighting Arrows!";
		atkDescript[3] = "100 Energy Arrows!";
		// store specs for each attack
		String[][] dmgSpecs = new String[4][];
		dmgSpecs[0] = normalHitSpecs1;
		dmgSpecs[1] = normalHitSpecs2;
		dmgSpecs[2] = normalHitSpecs3;
		dmgSpecs[3] = normalHitSpecs4;

		System.out.println(self.getName() + " uses Final Storm and unleashed a swarm of arrows.");
		System.out.println();
		for (int i = 0; i < 4; i++) {
			double dmgCount = self.getCurrentAtk() * this.abilityStat;
			// determines if hit or not
			if (self.getCurrentAccuracy() >= hitC[i]) {
				hits[i] = true;
			} else if (self.getCurrentAccuracy() < hitC[i]) {
				hits[i] = false;
			}
			System.out.println(atkDescript[i]);
			// if it does hit
			if (hits[i] == true) {
				// determines if it crit and determines dmgcount
				if (self.getCurrentCritRate() >= critC[i]) {
					crits[i] = true;
				} else if (self.getCurrentCritRate() < critC[i]) {
					crits[i] = false;
				}
				if (crits[i] == true) {
					dmgCount += self.getCurrentCritDmg();
				}
				isDead = target.takeDmg(dmgSpecs[i], dmgCount);
				System.out.println();
				// happens if you miss
			} else if (hits[i] == false) {
				System.out.println("it missed!");
			}
		}
		return isDead;
	}

	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		// no need to code
		return false;
	}


}
