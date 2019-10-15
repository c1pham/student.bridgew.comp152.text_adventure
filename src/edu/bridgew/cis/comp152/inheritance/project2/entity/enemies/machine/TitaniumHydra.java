package edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.machine;

import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.Main;
import edu.bridgew.cis.comp152.inheritance.project2.ability.Ability;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;
import edu.bridgew.cis.comp152.inheritance.project2.item.Item;
import edu.bridgew.cis.comp152.inheritance.project2.item.consumable.Potion;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.gun.PlasmataRifle;

public class TitaniumHydra extends Machine {

	public TitaniumHydra(String name, double level) {
		// constructor
		super(name, "Titanium Hydra", 1 , "Lighting",
				Database.elementTypeResistance[Database.element.LIGHTING.ordinal()][Database.Resistance.FIRE_RESIST.ordinal()],
				Database.elementTypeResistance[Database.element.LIGHTING.ordinal()][Database.Resistance.ICE_RESIST.ordinal()],  
				Database.elementTypeResistance[Database.element.LIGHTING.ordinal()][Database.Resistance.LIGHTING_RESIST.ordinal()],
				Database.enemyBaseStats[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.ATTACK.ordinal()], 
				Database.enemyBaseStats[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.DEFENSE.ordinal()],
				Database.enemyBaseStats[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.MAGICAL_DEFENSE.ordinal()],
				Database.enemyBaseStats[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.SPEED.ordinal()],
				Database.enemyBaseStats[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.HEALTH.ordinal()],
				Database.enemyBaseStats[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.ACCURACY.ordinal()],
				Database.enemyBaseStats[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.CRITICAL_DAMAGE.ordinal()],
				Database.enemyBaseStats[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.CRITICAL_RATE.ordinal()],
				Database.enemySkills[Database.enemies.TITANIUM_HYDRA.ordinal()],
				Main.iFact.getWeapon(Database.enemyWeapons[Database.enemies.TITANIUM_HYDRA.ordinal()]),
				Database.enemyBaseStats[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.SHIELD_OR_ENERGY_DMG.ordinal()]);

		for (int i = 0; i < (level - 1); i++) {
			this.levelUp();
		}

	}

	// increase stats and level up titanium hydra
	@Override
	public void levelUp() {
		this.level +=1;
		this.origHealth += Database.enemylevelUpStat[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.HEALTH.ordinal()];
		this.currentHealth = this.origHealth;
		this.healthWithChanges = this.origHealth;

		this.origDef += Database.enemylevelUpStat[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.DEFENSE.ordinal()];
		this.currentDef = this.origDef;

		this.origSpeed += Database.enemylevelUpStat[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.SPEED.ordinal()];
		this.currentSpeed = this.origSpeed;

		this.origATK += Database.enemylevelUpStat[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.ATTACK.ordinal()];
		this.currentAtk = this.origATK;

		this.origEnergyDmg += Database.enemylevelUpStat[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.SHIELD_OR_ENERGY_DMG.ordinal()];
		this.currentEnergyDmg = this.origEnergyDmg;

		this.origMDef += Database.enemylevelUpStat[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.MAGICAL_DEFENSE.ordinal()];
		this.currentMDef = this.origMDef;

		this.origAccuracy += Database.enemylevelUpStat[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.ACCURACY.ordinal()];
		this.currentAccuracy = this.origAccuracy;

		this.origCritDmg += Database.enemylevelUpStat[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.CRITICAL_DAMAGE.ordinal()];
		this.currentCritDmg = this.origCritDmg;

		this.origCritRate += Database.enemylevelUpStat[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.CRITICAL_RATE.ordinal()];
		this.currentCritRate = this.origCritRate;

		this.origEnergyDmg += Database.enemylevelUpStat[Database.enemies.TITANIUM_HYDRA.ordinal()][Database.statName.SHIELD_OR_ENERGY_DMG.ordinal()];
		this.currentEnergyDmg = this.origEnergyDmg;

		this.weight += 50;
	}
	// this takes a array of ints and does dmg to the hydra
	@Override
	public boolean takeDmg(String[] dmgSpecs, double dmgCount) {
		// returns true if already dead
		if (this.currentHealth <= 0) {
			System.out.println(this.name + "'s armor is breaking.");
			return true;
		}

		String damageType;
		String elementDmg;
		double dmgTaken = 0;
		// it takes these strings to determine what to do in damage calculation
		damageType = dmgSpecs[0];
		elementDmg = dmgSpecs[1];

		// the if and else if statement it will damage the enemy based on the type of damage 
		// it will manipulate the damage base on attribute for elemental
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
			// adjust damage base on if it's range and it's element
		} else if (damageType.equalsIgnoreCase("Range")) {
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
			// adjust damage base on if it's magic and it's element
		} else if (damageType.equalsIgnoreCase("Magic")) {
			dmgTaken = dmgCount - this.currentMDef;
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
		// if damage is negative or 0 it will change it to 1
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
		// returns true if hydra is dead
		if (this.currentHealth <= 0) {
			System.out.println(this.name + "'s armor cracked.");
			return true;
		} else {
			return false;
		}
	}

	// print info 
	@Override
	public String toString() {
		return new String("Name: " + this.name + "\nClass: " + this.entityClass + "\nGender: " + this.gender + "\nLevel: " + Math.round(this.level) + "\nHealth: " + 
				this.currentHealth + "/" + this.healthWithChanges + "\nBattery Life: " + this.batteryLife + "%" + "\nElement: " + this.element + "\nAttack Stat: " + this.currentAtk
				+ "\nDefense Stat: "+ this.currentDef + "\nMagic Defense Stat: "+ this.currentMDef + "\nSpeed Stat: " + this.currentSpeed 
				+ "\nAccuracy: " + this.currentAccuracy*100 + "%" + "\nCrit Damage:  " + this.currentCritDmg + "\nCrit Rate: " + this.currentCritRate*100 + "%"
				+ "\nLighting Resistance: " + this.currentLightingResist + "%" + "\nIce Resistance: " + this.currentIceResist + "%"
				+ "\nFire Resistance: " + this.currentFireResist + "%" + "\nWeapon: " + this.weapon.getItemName() + "\nEnergy Dmg:" + this.currentEnergyDmg);
		// add energy dmg
	}


	// print info needed info during battle
	@Override
	public void printBattleInfo() {
		System.out.print("Name:  "+  this.name);
		System.out.print("\tClass: Titanium Hydra");
		System.out.print("\tLevel: " + Math.round(this.level));
		System.out.print("\tHealth: " + this.currentHealth + "/" + this.healthWithChanges);
		System.out.print("\tStamina Bar: " + this.staminaBar + "%");
		System.out.print("\tBattery Life: " + this.batteryLife + "%");
		System.out.println();
	}

	// machine passive ability 
	// you will be created to be place into restore ability. this is class specific to machines
	@Override
	public boolean nanobotRepair() {
		// needs full battery life and low health to activate this part
		if (batteryLife == 100 && this.currentHealth < this.healthWithChanges/3) {
			this.batteryLife -= 100;
			this.staminaBar = 100;
			this.currentHealth = this.healthWithChanges;
			System.out.println(this.name + " used advance nanobot repair and using up all battery life to regain HP to max.");
			return true;
		} else {
			// if condition are not meet minor heals are done
			System.out.println(this.name + " nanobots did minor repairs.");
			this.batteryLife += 5;
			this.staminaBar += 10;
			// if entity has more stamina or battery than cap it's reduce to 100
			if (this.staminaBar > 100) {
				this.staminaBar = 100;
			}
			if (this.batteryLife > 100) {
				this.batteryLife = 100;
			}
			return false;
		}
	}

	// reset stats of hydra
	@Override
	public void resetBattleStats() {
		this.batteryLife = 100;
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
		this.currentEnergyDmg = this.origEnergyDmg;
	}


	// to equip item and de equip item
	// equip item, if nothing is equip, if item is equip de equip current item then equip new
	@Override
	public void equipWeapon(Weapon weapon) {
		if (this.weapon != null) {
			this.deEquipWeapon();
			weapon.useItem(this);
			this.weapon = weapon;
		} else if (this.weapon == null) {
			weapon.useItem(this);
			this.weapon = weapon;
		}
	}

	// allows droid to un equip a item
	@Override
	public void deEquipWeapon() {
		if (this.weapon != null) {
			weapon.useItem(this);
			this.weapon = new PlasmataRifle(1f);
		}

	}


	@Override
	public boolean specialMove(Entity entity, ArrayList<Enemy> enemies) {
		boolean executeAbility = false;
		ArrayList<Ability> availableAbility = new ArrayList<Ability>();
		// finds and stores all usable abilities to available ability
		for ( int i = 0 ; i < this.abilityList.size(); i++) {
			Ability a = this.abilityList.get(i);
			if (a.checkStamina(this) == true)  {
				availableAbility.add(a);
			}

			if ( i == this.abilityList.size() - 1 && availableAbility.size() == 0) {
				executeAbility = false;
			}
		}
		// if there are usable abilities do a random one
		if (availableAbility.size() > 0) {
			Random rand = new Random();
			int ranIndex = rand.nextInt(availableAbility.size());
			Ability a2 = availableAbility.get(ranIndex);
			this.currentAtk += this.currentEnergyDmg;
			a2.useAbility(this, enemies);
			this.currentAtk -= this.currentEnergyDmg;
			executeAbility = true;
		}
		// returns true if you used ability returns false if you didn't
		return executeAbility;
	}

	@Override
	public boolean normalAttack(Entity entity, ArrayList<Enemy> enemies) {
		Random rand = new Random();
		float chance1;
		float chance2;
		String attackChoice = null;

		chance1 = rand.nextFloat();
		chance2 = rand.nextFloat();

		// determine whether you do energy breathe or use weapon
		if (chance1 <= chance2) {
			attackChoice = "Weapon";
		} else if (chance1 > chance2) {
			attackChoice = "Energy Breathe";
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

		if (attackChoice.equals("Weapon")) {
			String[] dmgSpecs = new String[2];
			String damageType = this.weapon.getDamageType();
			String element = this.weapon.getElement();
			// specs for take dmg
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
		} else if (attackChoice.equals("Energy Breathe")) {
			String[] dmgSpecs = new String[2];
			String damageType = "Range";
			String element = "Energy";
			dmgSpecs[0] = damageType;
			dmgSpecs[1] = element;
			// this keeps track of dmg player does
			// if it hit this will happen
			if (didHit == true) {
				if (didCrit == true) {
					dmgCount = this.currentAtk/15 + this.currentCritDmg;
				} else if (didCrit == false) {
					dmgCount = (this.currentAtk/15);
				}
				// if the dmg is negative or 0 it will set dmg to 1
				if (dmgCount <= 0) {
					dmgCount = 1;
				}
				// dsiplays message does damage to enemy
				System.out.println(this.name + " unleashes it's Energy Breathe on " + entity.getName() + "!");
				entity.takeDmg(dmgSpecs, dmgCount);
			}
		}

		if (didHit == false) {
			// this happens if you miss
			System.out.println(this.name + " missed the attack. ");
		}

		return true;
	}

	@Override
	public void combatAction(Entity entity, ArrayList<Enemy> enemies) {
		boolean didBattleAction = false;
		boolean tryHpPot = false;
		boolean tryStaminaPot = false;
		boolean tryAttack = false;
		boolean tryNanobot = false;

		// enemy combat loop will loop until entity does a enemy action 
		while (didBattleAction == false) {
			if (tryNanobot == false) {
				nanobotRepair();
				tryNanobot = true;
			}

			if (tryHpPot == false) {
				// loop through enemies if one's health is low it will attempt to use hp potion on them
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
			} else if (tryAttack == false) {
				// try attack
				Random rand = new Random();
				float chance;
				float chance2;
				String attackChoice = null;

				chance = rand.nextFloat();

				chance2 = rand.nextFloat();
				// determine kind of attack 
				if (chance <= chance2) {
					attackChoice = "Special";
				} else if (chance > chance2) {
					attackChoice = "Normal";
				}

				if (attackChoice.equals("Special")) {
					didBattleAction = this.specialMove(entity, enemies);
					// if it doesn't do a special move it will do normal attack 
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

	// this makes him use a item depending on condition given
	// item can be healed to heal other enemies
	// if it finds a item to use it will return true if it doesn't it will return false
	@Override
	public boolean useInventory(Entity entity, ArrayList<Enemy> enemies, boolean useHP) {
		boolean usedBattleItem = false;
		if (this.bag.getCurrentNumItems() == 0) {
			return false;
		} else {
			if (useHP == true) {
				// if usehp is true use a potion if it has one
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
				// if use hp is false if will try to use a stamina potion
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
		return usedBattleItem;
	}


}
