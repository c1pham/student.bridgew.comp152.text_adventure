package edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.elves;

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

public class DarkElf extends Elf {
	public DarkElf( String name, double level) {

		// constructor
		super(name, "Dark Elf", 'M' , 1, "Lighting",
				Database.elementTypeResistance[Database.element.LIGHTING.ordinal()][Database.Resistance.FIRE_RESIST.ordinal()],
				Database.elementTypeResistance[Database.element.LIGHTING.ordinal()][Database.Resistance.ICE_RESIST.ordinal()],  
				Database.elementTypeResistance[Database.element.LIGHTING.ordinal()][Database.Resistance.LIGHTING_RESIST.ordinal()],
				Database.enemyBaseStats[Database.enemies.DARK_ELF.ordinal()][Database.statName.ATTACK.ordinal()], 
				Database.enemyBaseStats[Database.enemies.DARK_ELF.ordinal()][Database.statName.DEFENSE.ordinal()],
				Database.enemyBaseStats[Database.enemies.DARK_ELF.ordinal()][Database.statName.MAGICAL_DEFENSE.ordinal()],
				Database.enemyBaseStats[Database.enemies.DARK_ELF.ordinal()][Database.statName.SPEED.ordinal()],
				Database.enemyBaseStats[Database.enemies.DARK_ELF.ordinal()][Database.statName.HEALTH.ordinal()],
				Database.enemyBaseStats[Database.enemies.DARK_ELF.ordinal()][Database.statName.ACCURACY.ordinal()],
				Database.enemyBaseStats[Database.enemies.DARK_ELF.ordinal()][Database.statName.CRITICAL_DAMAGE.ordinal()],
				Database.enemyBaseStats[Database.enemies.DARK_ELF.ordinal()][Database.statName.CRITICAL_RATE.ordinal()],
				Database.enemySkills[Database.enemies.DARK_ELF.ordinal()],
				Main.iFact.getWeapon(Database.enemyWeapons[Database.enemies.DARK_ELF.ordinal()]),
				Database.enemyBaseStats[Database.enemies.DARK_ELF.ordinal()][Database.statName.SHIELD_OR_ENERGY_DMG.ordinal()]);

		for (int i = 0; i < (level - 1); i++) {
			this.levelUp();
		}
	}


	// levels up enemy
	@Override
	public void levelUp() {
		this.level += 1;
		this.origHealth += Database.enemylevelUpStat[Database.enemies.DARK_ELF.ordinal()][Database.statName.HEALTH.ordinal()];
		this.currentHealth = this.origHealth;
		this.healthWithChanges = this.origHealth;

		this.origDef += Database.enemylevelUpStat[Database.enemies.DARK_ELF.ordinal()][Database.statName.DEFENSE.ordinal()];
		this.currentDef = this.origDef;

		this.origSpeed += Database.enemylevelUpStat[Database.enemies.DARK_ELF.ordinal()][Database.statName.SPEED.ordinal()];
		this.currentSpeed = this.origSpeed;

		this.origATK += Database.enemylevelUpStat[Database.enemies.DARK_ELF.ordinal()][Database.statName.ATTACK.ordinal()];
		this.currentAtk = this.origATK;

		this.origMDef += Database.enemylevelUpStat[Database.enemies.DARK_ELF.ordinal()][Database.statName.MAGICAL_DEFENSE.ordinal()];
		this.currentMDef = this.origMDef;

		this.origAccuracy += Database.enemylevelUpStat[Database.enemies.DARK_ELF.ordinal()][Database.statName.ACCURACY.ordinal()];
		this.currentAccuracy = this.origAccuracy;

		this.origCritDmg += Database.enemylevelUpStat[Database.enemies.DARK_ELF.ordinal()][Database.statName.CRITICAL_DAMAGE.ordinal()];
		this.currentCritDmg = this.origCritDmg;

		this.origCritRate += Database.enemylevelUpStat[Database.enemies.DARK_ELF.ordinal()][Database.statName.CRITICAL_RATE.ordinal()];
		this.currentCritRate = this.origCritRate;

		this.origElementalShield += Database.enemylevelUpStat[Database.enemies.DARK_ELF.ordinal()][Database.statName.SHIELD_OR_ENERGY_DMG.ordinal()];
		this.curElementalShield = this.origElementalShield;

		this.weight += 2;
	}

	// takes an array and calculates damage the elf takes, and then will decrease elf HP
	@Override
	public boolean takeDmg(String[] dmgSpecs, double dmgCount) {
		// if entity is already dead it will print this
		if (this.currentHealth <= 0) {
			System.out.println(this.name + " has already fallen!");
			return true;
		}
		String damageType;
		String elementDmg;
		double dmgTaken = 0;
		// specs to determine how they take dmg
		damageType = dmgSpecs[0];
		elementDmg = dmgSpecs[1];
		// this happens if attack is elemental, dmg will be absorbed
		if (elementDmg.equals("None") == false) {
			if (this.curElementalShield >= 1) {
				this.curElementalShield -=1;
				System.out.println(this.name + " elemental shield protected him.");
				return false;
			}
		}
		// the folllowing if and else if statements
		// does damage to elf base on the damage type, then base on if the damage has a elemental attribute to it
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
		//  if damage is negative or 0 then dmg taken will be changed to 1
		if (dmgTaken <= 0) {
			dmgTaken = 1;
		}
		// reduce current hp
		this.currentHealth -= dmgTaken;

		// it modifies elementalDmg so when the system displays a message it is adjusted to look right, or display the element of dmg
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
		// will return whether entity has died or not
		if (this.currentHealth <= 0) {
			System.out.println(this.name + " has fallen!");
			return true;
		} else {
			return false;
		}
	}

	// this is elf tostring
	@Override
	public String toString() {
		return new String("Name: " + this.name + "\nClass: " + this.entityClass + "\nGender: " + this.gender + "\nLevel: " + Math.round(this.level) + "\nHealth: " + 
				this.currentHealth + "/" + this.healthWithChanges + "\nStamina Bar: " + this.staminaBar + "/100" + "\nElement: " + this.element + "\nAttack Stat: " + this.currentAtk
				+ "\nDefense Stat: "+ this.currentDef + "\nMagic Defense Stat: "+ this.currentMDef + "\nSpeed Stat: " + this.currentSpeed 
				+ "\nAccuracy: " + this.currentAccuracy*100 + "%" + "\nCrit Damage:  " + this.currentCritDmg + "\nCrit Rate: " + this.currentCritRate*100 + "%"
				+ "\nLighting Resistance: " + this.currentLightingResist + "%" + "\nIce Resistance: " + this.currentIceResist + "%"
				+ "\nFire Resistance: " + this.currentFireResist + "%" + "\nWeapon: " + this.weapon.getItemName());
	}

	// this is elf info with neccesary info for battle
	@Override
	public void printBattleInfo() {
		System.out.print("Name:  "+  this.name);
		System.out.print("\tClass: " + this.entityClass);
		System.out.print("\tLevel: " + Math.round(this.level));
		System.out.print("\tHealth: " + this.currentHealth + "/" + this.origHealth);
		System.out.print("\tStamina Bar: " + this.staminaBar + "%");
		System.out.println();
	}
	// resets battle stats
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
		this.curElementalShield = this.origElementalShield;
	}

	// to equip item and de equip item
	@Override
	public void equipWeapon(Weapon weapon) {

		// if you have weapon equip if will de equip then equip new weapon
		if (this.weapon != null) {
			this.deEquipWeapon();
			weapon.useItem(this);
			this.weapon = weapon;
			// if you have no weapon at all, weapon will equip
		} else if (this.weapon == null) {
			weapon.useItem(this);
			this.weapon = weapon;
		}
	}

	// equips the weapon
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
		// chance is used to determine attack or kick
		chance = rand.nextFloat();
		// determines whether you do attack or kick
		if (chance <= this.level/10) {
			attackChoice = "Weapon";
		} else if (chance > this.level/10) {
			attackChoice = "Kick";
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
			// gets specs for take dmg
			String damageType = this.weapon.getDamageType();
			String element = this.weapon.getElement();
			dmgSpecs[0] = damageType;
			dmgSpecs[1] = element;
			// this keeps track of dmg player does
			// if it hit this will happen
			if (didHit == true) {
				// determines dmgcount
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
		} else if (attackChoice.equals("Kick")) {
			String[] dmgSpecs = new String[2];
			// determine specs for take dmg
			String damageType = "Melee";
			String element = "None";
			dmgSpecs[0] = damageType;
			dmgSpecs[1] = element;
			// this keeps track of dmg player does
			// if it hit this will happen
			if (didHit == true) {
				// calculates dmg count
				if (didCrit == true) {
					dmgCount = this.currentAtk/2 + this.currentCritDmg;
				} else if (didCrit == false) {
					dmgCount = (this.currentAtk/2);
				}
				// if the dmg is negative or 0 it will set dmg to 1
				if (dmgCount <= 0) {
					dmgCount = 1;
				}
				// dsiplays message does damage to enemy
				System.out.println(this.name + " kicked " + entity.getName() + "!");
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
		// goes through list of ability and determine what ability can be used then adds it to a list
		for ( int i = 0 ; i < this.abilityList.size(); i++) {
			Ability a = this.abilityList.get(i);
			if (a.checkStamina(this) == true)  {
				availableAbility.add(a);
			}
			// if you don't find a ability it will return to entity to know they failed
			if ( i == this.abilityList.size() - 1 && availableAbility.size() == 0) {
				executeAbility = false;
			}
		}
		// if they have available abilities it will remainly pick one 
		//to do then also return to user they successful used the right abiliy
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
	public boolean useInventory(Entity entity, ArrayList<Enemy> enemies, boolean useHP) {
		boolean usedBattleItem = false;
		// if their are no items return false
		if (this.bag.getCurrentNumItems() == 0) {
			return false;
		} else {
			if (useHP == true) {
				// if usehp is true use a health potion but if it doesn't find one the method will return false
				// if it does find one to use it returns true
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
				// if usehp use a stamina potion but if it doesn't find one the method will return false
				// if it does find one to use it returns true
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
	// enemy action loop
	@Override
	public void combatAction(Entity entity, ArrayList<Enemy> enemies) {
		boolean didBattleAction = false;
		boolean tryHpPot = false;
		boolean tryStaminaPot = false;
		boolean tryAttack = false;
		// this loops continues until the entity has done a combat action
		while (didBattleAction == false) {
			if (tryHpPot == false) {
				//  if you hadn't tried to use hp portion it checks if someone has low hp
				// if someone has low hp it will use potion
				for (Enemy e : enemies) {
					if (e.getCurrentHealth() < e.getHealthWithChanges()/3) {
						didBattleAction = this.useInventory(e, enemies, true);				
					}
				}
				tryHpPot = true;
			} else if (tryStaminaPot == false) {
				//  if you hadn't tried to use stamina portion it checks if someone has low hp
				// if someone doesn't have enough stamina to use lowest costing move it will use stamina portion on target
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
				// if entity hasn't attack it tries this
				Random rand = new Random();
				float chance;
				float chance2;
				String attackChoice = null;

				chance = rand.nextFloat();
				chance2 = rand.nextFloat();
				// determine whether you do normal  or special  attack
				if (chance <= chance2) {
					attackChoice = "Special";
				} else if (chance > chance2) {
					attackChoice = "Normal";
				}
				// does special move but if you can't do one it does normal attack
				if (attackChoice.equals("Special")) {
					didBattleAction = this.specialMove(entity, enemies);
					if (didBattleAction == false ) {
						didBattleAction = this.normalAttack(entity, enemies);
					}
					// does normal attack
				} else if (attackChoice.equals("Normal")) {
					didBattleAction = this.normalAttack(entity, enemies);

				}
				tryAttack = true;
			} 
			// now add the hitting portion of this logic 
		}


	}
}
