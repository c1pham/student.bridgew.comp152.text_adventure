package edu.bridgew.cis.comp152.inheritance.project2.ability;

import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;

public class MagicalArrowBarrage extends Ability {
	public MagicalArrowBarrage() {
		super("Magical Arrow Barrage",
				Database.abilityStatD[ Database.ability.MAGICAL_ARROW_BARRAGE.ordinal()][Database.abilityStat.REQUIRE_STAMINA.ordinal()],
				Database.abilityStatD[ Database.ability.MAGICAL_ARROW_BARRAGE.ordinal()][Database.abilityStat.ABILITY_STAT.ordinal()]);
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
				+ "\tDescription: Shoot magical arrows does elemental damage.");
	}


	@Override
	public boolean useAbility(Entity self, Entity target) {
		self.setStaminaBar(self.getStaminaBar() - this.reqStamina);
		boolean isDead = false;
		// specs for attack dmg
		String[] normalHitSpecs1 = new String[2];
		normalHitSpecs1[0] = "Magic";
		normalHitSpecs1[1] = "Fire";
		String[] normalHitSpecs2 = new String[2];
		normalHitSpecs2[0] = "Magic";
		normalHitSpecs2[1] = "Ice";
		String[] normalHitSpecs3 = new String[2];
		normalHitSpecs3[0] = "Magic";
		normalHitSpecs3[1] = "Lighting";	
		String[] normalHitSpecs4 = new String[2];
		normalHitSpecs4[0] = "Magic";
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
		
		// boolean that say whether you hit or crit
		boolean didHit1 = false;
		boolean didHit2 = false;
		boolean didHit3 = false;
		boolean didHit4 = false;
		boolean didCrit1 = false;
		boolean didCrit2 = false;
		boolean didCrit3 = false;
		boolean didCrit4 = false;
		
		// stores booleans for it crit or not
		boolean[] crits = new boolean[4];
		crits[0] = didCrit1;
		crits[1] = didCrit2;
		crits[2] = didCrit3;
		crits[3] = didCrit4;
		// stores chances for crit
		float[] critC = new float[4];
		critC[0] = critChance1;
		critC[1] = critChance2;
		critC[2] = critChance3;
		critC[3] = critChance4;
		// stores boolean to say whether you hit or not
		boolean[] hits = new boolean[4];
		hits[0] = didHit1;
		hits[1] = didHit2;
		hits[2] = didHit3;
		hits[3] = didHit4;
		// stores chances for hit
		float[] hitC = new float[4];
		hitC[0] = hitChance1;
		hitC[1] = hitChance2;
		hitC[2] = hitChance3;
		hitC[3] = hitChance4;
		// stores  description when attack happens
		String[] atkDescript = new String[4];
		atkDescript[0] = "Fire Arrow!";
		atkDescript[1] = "Ice Arrow!";
		atkDescript[2] = "Lighting Arrow!";
		atkDescript[3] = "Energy Arrow!";
		// stores specs for dmg
		String[][] dmgSpecs = new String[4][];
		dmgSpecs[0] = normalHitSpecs1;
		dmgSpecs[1] = normalHitSpecs2;
		dmgSpecs[2] = normalHitSpecs3;
		dmgSpecs[3] = normalHitSpecs4;

		// random number use to generate chance of hitting or criting
		
		// determines if you hit and set didhit
		System.out.println(self.getName() + " uses Magical Arrow Barrage and unleashes four elemental attacks.");
		System.out.println();
		// loops and does four attacks
		for (int i = 0; i < 4; i++) {
			// determines dmgcount
			double dmgCount = self.getCurrentAtk() * this.abilityStat;
			// determines if you hit
			if (self.getCurrentAccuracy() >= hitC[i]) {
				hits[i] = true;
			} else if (self.getCurrentAccuracy() < hitC[i]) {
				hits[i] = false;
			}
			System.out.println(atkDescript[i]);
			
			if (hits[i] == true) {
				// determines if you crit
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
			} else if (hits[i] == false) {
				// if you missed
				System.out.println("It missed!");
			}
		}
		return isDead;
	}

	@Override
	public boolean useAbility(Entity self, ArrayList<Enemy> enemies) {
		// TODO Auto-generated method stub
		return false;
	}


}
