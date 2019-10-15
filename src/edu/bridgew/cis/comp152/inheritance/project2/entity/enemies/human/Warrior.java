package edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.human;

import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.Main;
import edu.bridgew.cis.comp152.inheritance.project2.ability.Ability;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;
import edu.bridgew.cis.comp152.inheritance.project2.entity.player.Player;
import edu.bridgew.cis.comp152.inheritance.project2.item.Item;
import edu.bridgew.cis.comp152.inheritance.project2.item.consumable.Potion;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.bow.LongBow;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword.HeroBlade;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword.SharpStick;


public class Warrior extends Human {

	// constructors
	public Warrior(String name, double level) {
		super(name, "Warrior" , 'M', 1 , "None",
				Database.elementTypeResistance[Database.element.NORMAL.ordinal()][Database.Resistance.FIRE_RESIST.ordinal()],
				Database.elementTypeResistance[Database.element.NORMAL.ordinal()][Database.Resistance.ICE_RESIST.ordinal()],  
				Database.elementTypeResistance[Database.element.NORMAL.ordinal()][Database.Resistance.LIGHTING_RESIST.ordinal()],
				Database.enemyBaseStats[Database.enemies.WARRIOR.ordinal()][Database.statName.ATTACK.ordinal()], 
				Database.enemyBaseStats[Database.enemies.WARRIOR.ordinal()][Database.statName.DEFENSE.ordinal()],
				Database.enemyBaseStats[Database.enemies.WARRIOR.ordinal()][Database.statName.MAGICAL_DEFENSE.ordinal()],
				Database.enemyBaseStats[Database.enemies.WARRIOR.ordinal()][Database.statName.SPEED.ordinal()],
				Database.enemyBaseStats[Database.enemies.WARRIOR.ordinal()][Database.statName.HEALTH.ordinal()],
				Database.enemyBaseStats[Database.enemies.WARRIOR.ordinal()][Database.statName.ACCURACY.ordinal()],
				Database.enemyBaseStats[Database.enemies.WARRIOR.ordinal()][Database.statName.CRITICAL_DAMAGE.ordinal()],
				Database.enemyBaseStats[Database.enemies.WARRIOR.ordinal()][Database.statName.CRITICAL_RATE.ordinal()],
				Database.enemySkills[Database.enemies.WARRIOR.ordinal()],
				Main.iFact.getWeapon(Database.enemyWeapons[Database.enemies.WARRIOR.ordinal()]));
		for (int i = 0; i < (level - 1); i++) {
			levelUp();
		}

	}
	// levels up warrior
	@Override
	public void levelUp() {
		this.level +=1;

		this.origHealth += Database.enemylevelUpStat[Database.enemies.WARRIOR.ordinal()][Database.statName.HEALTH.ordinal()];
		this.currentHealth = this.origHealth;
		this.healthWithChanges = this.origHealth;

		this.origDef += Database.enemylevelUpStat[Database.enemies.WARRIOR.ordinal()][Database.statName.DEFENSE.ordinal()];
		this.currentDef = this.origDef;

		this.origSpeed += Database.enemylevelUpStat[Database.enemies.WARRIOR.ordinal()][Database.statName.SPEED.ordinal()];
		this.currentSpeed = this.origSpeed;

		this.origATK += Database.enemylevelUpStat[Database.enemies.WARRIOR.ordinal()][Database.statName.ATTACK.ordinal()];
		this.currentAtk = this.origATK;

		this.origMDef += Database.enemylevelUpStat[Database.enemies.WARRIOR.ordinal()][Database.statName.MAGICAL_DEFENSE.ordinal()];
		this.currentMDef = this.origMDef;

		this.origAccuracy += Database.enemylevelUpStat[Database.enemies.WARRIOR.ordinal()][Database.statName.ACCURACY.ordinal()];
		this.currentAccuracy = this.origAccuracy;

		this.origCritDmg += Database.enemylevelUpStat[Database.enemies.WARRIOR.ordinal()][Database.statName.CRITICAL_DAMAGE.ordinal()];
		this.currentCritDmg = this.origCritDmg;

		this.origCritRate += Database.enemylevelUpStat[Database.enemies.WARRIOR.ordinal()][Database.statName.CRITICAL_RATE.ordinal()];
		this.currentCritRate = this.origCritRate;

		this.weight += 10;
	}

	// this print out this objects info
	@Override
	public String toString() {
		return new String("Name: " + this.name + "\nClass: " + this.entityClass + "\nGender: " + this.gender + "\nLevel: " + this.level + "\nHealth: " + 
				this.currentHealth + "/" + this.healthWithChanges + "\nStamina Bar: " + this.staminaBar + "/100" + "\nElement: " + this.element + "\nAttack Stat: " + this.currentAtk
				+ "\nDefense Stat: "+ this.currentDef + "\nMagic Defense Stat: "+ this.currentMDef + "\nSpeed Stat: " + this.currentSpeed 
				+ "\nAccuracy: " + this.currentAccuracy*100 + "%" + "\nCrit Damage:  " + this.currentCritDmg + "\nCrit Rate: " + this.currentCritRate*100 + "%"
				+ "\nLighting Resistance: " + this.currentLightingResist + "%" + "\nIce Resistance: " + this.currentIceResist + "%"
				+ "\nFire Resistance: " + this.currentFireResist + "%" + "\nWeapon: " + this.weapon.getItemName());
	}
	


	// warrior takes dmg
	@Override
	public boolean takeDmg(String[] dmgSpecs, double dmgCount) {
		// returns false and prints this if warrior is already dead
		if (this.currentHealth <= 0) {
			System.out.println(this.name + " has already fallen!");
			return true;
		}
		String damageType;
		String elementDmg;
		double dmgTaken = 0;
		// retreive dmg attributes from array
		damageType = dmgSpecs[0];
		elementDmg = dmgSpecs[1];

		// will do damage to warrior base on the damage type and the element of the dmg if any
		if (damageType.equalsIgnoreCase("Melee")) {
			dmgTaken = dmgCount - this.currentDef;
			if (elementDmg.equalsIgnoreCase("None")) {
			} else if (elementDmg.equalsIgnoreCase("Fire")) {
				dmgTaken -= (dmgTaken * (this.currentFireResist/100));
			} else if (elementDmg.equalsIgnoreCase("Ice")) {
				dmgTaken -= (dmgTaken * (this.currentIceResist/100));
			} else if (elementDmg.equalsIgnoreCase("Lighting")) {
				dmgTaken -= (dmgTaken * (this.currentLightingResist/100));
			} else if (elementDmg.equalsIgnoreCase("Energy")) {
				dmgTaken += this.currentDef;
			}
		} else if (damageType.equalsIgnoreCase("Range")) {
			// warriors receive less damage from range attacks
			dmgTaken = (dmgCount/2) - this.currentDef;
			if (elementDmg.equalsIgnoreCase("None")) {
			} else if (elementDmg.equalsIgnoreCase("Fire")) {
				dmgTaken -= (dmgTaken * (this.currentFireResist/100));
			} else if (elementDmg.equalsIgnoreCase("Ice")) {
				dmgTaken -= (dmgTaken * (this.currentIceResist/100));
			} else if (elementDmg.equalsIgnoreCase("Lighting")) {
				dmgTaken -= (dmgTaken * (this.currentLightingResist/100));
			} else if (elementDmg.equalsIgnoreCase("Energy")) {
				dmgTaken += this.currentDef;
			}
		} else if (damageType.equalsIgnoreCase("Magic")) {
			// warrior receive more dmg from magic attacks
			dmgTaken = (dmgCount*2) - this.currentMDef;
			if (elementDmg.equalsIgnoreCase("None")) {
			} else if (elementDmg.equalsIgnoreCase("Fire")) {
				dmgTaken -= (dmgTaken * (this.currentFireResist/100));
			} else if (elementDmg.equalsIgnoreCase("Ice")) {
				dmgTaken -= (dmgTaken * (this.currentIceResist/100));
			} else if (elementDmg.equalsIgnoreCase("Lighting")) {
				dmgTaken -= (dmgTaken * (this.currentLightingResist/100));
			} else if (elementDmg.equalsIgnoreCase("Energy")) {
				dmgTaken += this.currentMDef;
			}
		}
		dmgTaken = Math.round(dmgTaken);
		// this make it so damage has to be atleast 1
		if (dmgTaken <= 0) {
			dmgTaken = 1;
		}
		this.currentHealth -= dmgTaken;

		// it modifies elemental dmg so when the system displays a message it is adjusted to look right, or display the element of dmg

		if (elementDmg.equalsIgnoreCase("None")) {
			elementDmg = " ";
		} else if (elementDmg.equalsIgnoreCase("Fire")) {
			elementDmg = " Fire ";
		} else if (elementDmg.equalsIgnoreCase("Ice")) {
			elementDmg = " Ice ";
		} else if (elementDmg.equalsIgnoreCase("Lighting")) {
			elementDmg = " Lighting ";
		} else if (elementDmg.equalsIgnoreCase("Energy")) {
			elementDmg = " Energy ";
		}
		System.out.println(this.name + " has taken " + dmgTaken + elementDmg + "damage!");
		// returns true if enemy is dead
		if (this.currentHealth <= 0) {
			System.out.println(this.name + " has fallen!");
			return true;
		} else {
			return false;
		}
	}
	// // info to show to user when battle happens. Shows just what user needs to see to battle
	@Override
	public void printBattleInfo() {
		System.out.print("Name:  "+  this.name);
		System.out.print("\tClass: Warrior");
		System.out.print("\tLevel: " + Math.round(this.level));
		System.out.print("\tHealth: " + this.currentHealth + "/" + this.healthWithChanges);
		System.out.print("\tStamina Bar: " + this.staminaBar + "%");
		System.out.println();	
	}
	// warrior equips weapon
	@Override
	public void equipWeapon(Weapon weapon) {
		//  if current weapon is alredy equipped it will dequip current weapon
		if (this.weapon != null) {
			this.deEquipWeapon();
			weapon.useItem(this);
			this.weapon = weapon;
		} else if (this.weapon == null) {
			weapon.useItem(this);
			this.weapon = weapon;
		}
	}
	// de equip team
	@Override
	public void deEquipWeapon() {
		if (this.weapon != null) {
			weapon.useItem(this);
		}
	}
	
	@Override
	public boolean normalAttack(Entity entity, ArrayList<Enemy> enemies) {
		Random rand = new Random();
		float chance;
		String attackChoice = null;

		chance = rand.nextFloat();
		// will determine whether to do heavy or do weapon hit
		if (chance <= this.level/10) {
			attackChoice = "Weapon";
		} else if (chance > this.level/10) {
			attackChoice = "Heavy Slam";
		}
		double dmgCount = 0;
		boolean didHit = false;
		boolean didCrit = false;
		float critChance;
		float hitChance;
		// random number use to generate chance of hitting or criting
		critChance = rand.nextFloat();
		hitChance = rand.nextFloat();
		// determines if you hit and set didhit
		if (this.currentAccuracy >= hitChance) {
			didHit = true;
		} else if (this.currentAccuracy < hitChance) {
			didHit = false;
		}
		// determines if you crit and set didcrit
		if (this.currentCritRate >= critChance) {
			didCrit = true;
		} else if (this.currentCritRate < critChance) {
			didCrit = false;
		}
		// this happens if attack choice is weapon
		if (attackChoice.equals("Weapon")) {
			String[] dmgSpecs = new String[2];
			// specs for take dmg
			String damageType = this.weapon.getDamageType();
			String element = this.weapon.getElement();
			dmgSpecs[0] = damageType;
			dmgSpecs[1] = element;
			// this keeps track of dmg player does
			// if it hit this will happen
			if (didHit == true) {
				if (didCrit == true) {
					dmgCount = (this.currentAtk + this.weapon.getDamage() * 2) + this.currentCritDmg;
				} else if (didCrit == false) {
					dmgCount = (this.currentAtk + this.weapon.getDamage() * 2);
				}
				// if the dmg is negative or 0 it will set dmg to 1
				if (dmgCount <= 0) {
					dmgCount = 1;
				}
				// dsiplays message does damage to enemy
				System.out.println(this.name + " uses " + weapon.getItemName() + " and hits " + entity.getName() + "!");
				entity.takeDmg(dmgSpecs, dmgCount);
			}
		} else if (attackChoice.equals("Heavy Slam")) {
			String[] dmgSpecs = new String[2];
			// specs for take dmg
			String damageType = "Melee";
			String element = "None";
			dmgSpecs[0] = damageType;
			dmgSpecs[1] = element;
			// this keeps track of dmg player does
			// if it hit this will happen
			if (didHit == true) {
				if (didCrit == true) {
					dmgCount = (this.currentAtk + this.weight/20) * 1.1 + this.currentCritDmg;
				} else if (didCrit == false) {
					dmgCount = (this.currentAtk + this.weight/20) * 1.1 ;
				}
				// if the dmg is negative or 0 it will set dmg to 1
				if (dmgCount <= 0) {
					dmgCount = 1;
				}
				// dsiplays message does damage to enemy
				System.out.println(this.name + " uses Heavy Slam " + entity.getName() + "!");
				entity.takeDmg(dmgSpecs, dmgCount);
			}
		}
		
		if (didHit == false) {
			// this happens if you missed
			System.out.println(this.name + " missed the attack. ");
		}
		return true;
	}


	@Override
	public boolean specialMove(Entity entity, ArrayList<Enemy> enemies) {
		boolean executeAbility = false;
		ArrayList<Ability> availableAbility = new ArrayList<Ability>();
		// finds and stores all usable abilities
		for ( int i = 0 ; i < this.abilityList.size(); i++) {
			Ability a = this.abilityList.get(i);
			if (a.checkStamina(this) == true)  {
				availableAbility.add(a);
			}
			if ( i == this.abilityList.size() - 1 && availableAbility.size() == 0) {
				executeAbility = false;
			}
		}
		// if there are usable abilities it will do a random one
		if (availableAbility.size() >= 1) {
			Random rand = new Random();
			int ranIndex = rand.nextInt((availableAbility.size()));
			Ability a2 = availableAbility.get(ranIndex);
			a2.useAbility(this, entity);	
			executeAbility = true;
		}
		
		return executeAbility;
	}



	@Override
	public void combatAction(Entity entity, ArrayList<Enemy> enemies) {
		boolean didBattleAction = false;
		boolean tryHpPot = false;
		boolean tryStaminaPot = false;
		boolean tryAttack = false;
		// this loops until a battle action is done
		while (didBattleAction == false) {
			// loop through enemies if one's health is low it will attempt to use hp potion on them
			if (tryHpPot == false) {
				for (Enemy e : enemies) {
					if (e.getCurrentHealth() < e.getHealthWithChanges()/3) {
						didBattleAction = this.useInventory(e, enemies, true);				
					}
				}
				tryHpPot = true;
			} else if (tryStaminaPot == false) {
				// will loop through  enemies and if one can't use a skill due to lack of stamina it will attempt
				// to use stamina potion on them
				for (Enemy e : enemies) {
					double lowestCostSkill = 200;

					if (e.getAbilityList().size() != 0) {
						for (Ability a: e.getAbilityList()) {
							if (a.getReqStamina() < lowestCostSkill) {
								lowestCostSkill = a.getReqStamina();
							}
						}
					}
					if (e.getStaminaBar() < lowestCostSkill ) {
						didBattleAction = this.useInventory(e, enemies, false);
					}
				}
				tryStaminaPot = true;
			} else if (this.currentHealth < this.healthWithChanges/10) {
				// rest if health is low
				rest();
				didBattleAction = true;
				
			} else if (tryAttack == false) {
				// attempts to do attack
				Random rand = new Random();
				float chance;
				float chance2;
				String attackChoice = null;
				// chance used to determine to do special move or not
				chance = rand.nextFloat();
				
				chance2 = rand.nextFloat();
				// compares chance to determine what battle action to take
				if (chance <= chance2) {
					attackChoice = "Special";
				} else if (chance > chance2) {
					attackChoice = "Normal";
				}
				// does special if this is case
				if (attackChoice.equals("Special")) {
					didBattleAction = this.specialMove(entity, enemies);
					// if couldn't do special will try normal attack 
					if (didBattleAction == false) {
						didBattleAction = this.normalAttack(entity, enemies);
					}
				} else if (attackChoice.equals("Normal")) {
					didBattleAction = this.normalAttack(entity, enemies);
				}
				tryAttack = true;
			} 
		}
	}

	@Override
	public boolean useInventory(Entity entity, ArrayList<Enemy> enemies, boolean useHP) {
		boolean usedBattleItem = false;
		// returns false if bag is empty
		if (this.bag.getCurrentNumItems() == 0) {
			return false;
		} else {
			if (useHP == true) {
				// goes through inventory and if you can use a hp potion it will be used
				// then deleted from inventory 
				for (int i = 0 ; i < this.bag.getCurrentNumItems(); i++) {
					Item curItem = this.bag.getItemList().get(i);
					if (curItem.getItemID().getSubItemType2().equalsIgnoreCase("HealthPotion")) {
						Potion HpPotion = this.bag.getMatchingPotion(curItem);
						HpPotion.useItem(entity);
						HpPotion.deleteItemAfterUse(this);
						return true;
					}
				}	
			}
			if (useHP == false) {
				// goes through inventory and if you can use a stamina potion it will be used
				// then deleted from inventory 
				for (int i = 0 ; i < this.bag.getCurrentNumItems(); i++) {
					Item curItem = this.bag.getItemList().get(i);
					if (curItem.getItemID().getSubItemType2().equalsIgnoreCase("StaminaPotion")) {
						Potion stamPotion = this.bag.getMatchingPotion(curItem);
						stamPotion.useItem(entity);
						stamPotion.deleteItemAfterUse(this);
						return true;
					}
				}	
			}
		}
		// returns  true if item is used, if not returns false
		return usedBattleItem;
	}
	// rest will restore health and stamina
	@Override
	public void rest() {
		this.staminaBar += 20;
		this.currentHealth += (this.getHealthWithChanges() - this.currentHealth)/5;
		if (this.staminaBar > 100) {
			double differenceFrom100;
			differenceFrom100 = this.staminaBar - 100;
			this.staminaBar -= differenceFrom100;
		}
		System.out.println(this.name + " rested and a portion of his lost HP and stamina is recovered.");
		System.out.println();
	}
	// resets stats
	@Override
	public void resetBattleStats() {
		this.healthWithChanges = this.origHealth;
		this.currentHealth = this.origHealth;
		this.currentSpeed = this.origSpeed;
		this.currentDef = this.origDef;
		this.currentMDef = this.origDef;
		this.currentAtk = this.origATK;
		this.currentCritDmg = this.origCritDmg;
		this.currentCritRate = this.origCritRate;
		this.currentAccuracy = this.origAccuracy;
		this.staminaBar = 100;
		
	}
}
