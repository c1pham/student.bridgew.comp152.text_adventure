package edu.bridgew.cis.comp152.inheritance.project2.entity.player;

import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.Main;
import edu.bridgew.cis.comp152.inheritance.project2.ability.*;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;
import edu.bridgew.cis.comp152.inheritance.project2.item.Item;
import edu.bridgew.cis.comp152.inheritance.project2.item.consumable.Potion;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;

import java.util.ArrayList;

import java.util.InputMismatchException;


public class Player extends Entity {

	// constructor
	public Player(String name, String entityClass, char gender, double level) {
		super (name, entityClass, gender, 1, "None",
				Database.elementTypeResistance[Database.element.NORMAL.ordinal()][Database.Resistance.FIRE_RESIST.ordinal()],
				Database.elementTypeResistance[Database.element.NORMAL.ordinal()][Database.Resistance.ICE_RESIST.ordinal()],  
				Database.elementTypeResistance[Database.element.NORMAL.ordinal()][Database.Resistance.LIGHTING_RESIST.ordinal()],
				Database.playerBaseStats[Database.playerType.valueOf(entityClass.toUpperCase()).ordinal()][Database.statName.ATTACK.ordinal()],
				Database.playerBaseStats[Database.playerType.valueOf(entityClass.toUpperCase()).ordinal()][Database.statName.DEFENSE.ordinal()],
				Database.playerBaseStats[Database.playerType.valueOf(entityClass.toUpperCase()).ordinal()][Database.statName.MAGICAL_DEFENSE.ordinal()],
				Database.playerBaseStats[Database.playerType.valueOf(entityClass.toUpperCase()).ordinal()][Database.statName.SPEED.ordinal()],
				Database.playerBaseStats[Database.playerType.valueOf(entityClass.toUpperCase()).ordinal()][Database.statName.HEALTH.ordinal()], 
				Database.playerBaseStats[Database.playerType.valueOf(entityClass.toUpperCase()).ordinal()][Database.statName.ACCURACY.ordinal()], 
				Database.playerBaseStats[Database.playerType.valueOf(entityClass.toUpperCase()).ordinal()][Database.statName.CRITICAL_DAMAGE.ordinal()],
				Database.playerBaseStats[Database.playerType.valueOf(entityClass.toUpperCase()).ordinal()][Database.statName.CRITICAL_RATE.ordinal()],
				Database.playerAbilities[Database.playerType.valueOf(entityClass.toUpperCase()).ordinal()],
				Main.iFact.getWeapon(Database.playerClassWeapon[Database.playerType.valueOf(entityClass.toUpperCase()).ordinal()])
				);

		for (int i = 0; i < (level - 1); i++) {
			levelUp();
		}
	}

	// this allows the player to equip a weapon and applies set stuff to player related to the weapon
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

	// this will de equip the players weapon and removes stat buffs from player related to weapon
	@Override
	public void deEquipWeapon() {
		//&& this.weapon.isEquip() == true
		if (this.weapon != null) {
			weapon.useItem(this);
			this.weapon = null;
		}
	}

	// player special move. you will select a skill, select a target then special move is done
	@Override
	public boolean specialMove(Entity entity, ArrayList<Enemy> enemies) {

		Entity selectedEntity = null;
		int numIndex = -1;
		boolean targetFound = false;
		boolean validInput;

		boolean skillPicked = false;
		boolean validInput2 = false;
		int skillIndex = -1;
		Ability skillFound = null;
		boolean isDead;

		System.out.println("Ability List:");
		for ( int i = 0; i < this.abilityList.size(); i++) {
			System.out.println( (i+1) + ". " + abilityList.get(i).toString());
			System.out.println();
		}

		boolean askAgain5 = true;
		// prompt user for decision
		while (askAgain5 == true) {
			String userDecision4;
			System.out.println("Enter letter [X] if you don't want to do special move.");
			System.out.println("(You will be sent to battle scene.)");
			System.out.println("Press letter [Y] to move forward.");
			System.out.println();
			userDecision4 = Main.in.nextLine().strip().toLowerCase();

			if (userDecision4.equals("x")) {
				return false;
			} else if (userDecision4.equals("y")) {
				askAgain5 = false;
			} else {
				System.out.println("Please enter either X or Y.");
			}

		}

		// prompt pick skill
		System.out.println("Select the number of the skill.");
		System.out.println("Enter -1 to leave this screen.");

		while (skillPicked == false) {

			validInput2 = false;
			while (validInput2 == false) {
				// in case user puts in wrong input
				try {
					skillIndex = Main.in.nextInt();
					validInput2 = true;
				} catch (InputMismatchException ex) {
					Main.in.nextLine();
					break;
				}
			}
			// in case user picks wrong number it's handled
			if (skillIndex == -1) {
				Main.in.nextLine();
				return false;
			} else if (skillIndex < -1) {
				System.out.println("Please type in a number.");
			} else if (skillIndex > 0 && this.abilityList.size() >= skillIndex) {
				validInput2 = true;
				skillFound = this.abilityList.get(skillIndex - 1);

				// checks if user has enough stamina
				if (skillFound.checkStamina(this)) {
					skillPicked = true;
				} else if (skillFound.checkStamina(this) == false) {
					System.out.println("Please choose again you don't have enough stamina.");
				}

			} 
		}
		System.out.println("Enemies:");
		System.out.println("______________________________________________________"
				+ "____________________________________________________________________");

		// print enemies
		for (int i =0 ; i < enemies.size(); i++) {
			System.out.print((i + 1) + ". ");
			enemies.get(i).printBattleInfo();
			System.out.println();
		}
		// prompt to pick target
		System.out.println("Pick a enemy.");
		System.out.println("Enter 0 to target yourself.");

		while (targetFound == false) {

			validInput = false;
			while (validInput == false) {
				try {
					numIndex = Main.in.nextInt();
					validInput = true;
				} catch (InputMismatchException ex) {
					Main.in.nextLine();
					break;
				}
			}

			// prompt to pick enemy
			// it it doesn't pick valid it will ask again , if pick is valid it will store that index
			if (numIndex < 0) {
				System.out.println("Please type number of a enemy.");

			} else if (numIndex == 0) {
				targetFound = true;
				selectedEntity = this;

			} else if (numIndex > 0 && enemies.size() >= numIndex) {
				targetFound = true;
				selectedEntity = enemies.get(numIndex - 1);
			} else if (numIndex > enemies.size()) {
				System.out.println("Please pick a number of a enemy.");
			}
		}

		// do special mode
		isDead = skillFound.useAbility(this, selectedEntity);

		// is dead remove from list 
		if (isDead == true) {
			if (selectedEntity.equals(this) == false) {
				enemies.remove(selectedEntity);
			}
		}

		return true;
	}

	// first slot of dmg specs should be melee range or magic
	// 2rd slot of dmg specs should be none, fire, ice, lighting, energy, 
	@Override
	public boolean takeDmg(String[] dmgSpecs, double dmgCount) {
		String damageType;
		String elementDmg;
		double dmgTaken = 0;
		//  uses these to determine type of attack , and if it has elemental dmg
		damageType = dmgSpecs[0];
		elementDmg = dmgSpecs[1];

		// if the attack is melee it will do this
		if (damageType.equalsIgnoreCase("Melee")) {
			dmgTaken = dmgCount - this.currentDef;
			// melee type dmg is reduced by defense
			// dmg is affected by elemental resistance unless it's energy
			// energy ignores all defense
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
			// if attack is range it'll do this
		} else if (damageType.equalsIgnoreCase("Range")) {
			// range attack is reduce by defense  unless it's energy
			// elemental resistance affect dmg taken if the dmg has a element
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
			// dmg is reduced  by magic defense unless it's a energy base attack
			// if the damage has a element it is affected by elemental resistance
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
		// if dmg is negative or 0 it will change to 1
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

		// if dead returns true
		if (this.currentHealth <= 0) {
			return true;
		} else {
			return false;
		}
	}

	// print info for battle
	@Override
	public void printBattleInfo() {
		System.out.println("Player Name: " + this.name + "\tLevel: " + Math.round(this.level) 
		+ "\tHealth:" + this.currentHealth + "/" + 
		this.healthWithChanges + "\tStamina: " + this.staminaBar + "%");

	}

	// stats increases for level up
	@Override
	public void levelUp() {
		this.level +=1;

		this.origHealth += Database.playerLevelUpStat[Database.playerType.valueOf(this.entityClass.toUpperCase()).ordinal()][Database.statName.HEALTH.ordinal()];
		this.currentHealth = this.origHealth;
		this.healthWithChanges = this.origHealth;

		this.origDef += Database.playerLevelUpStat[Database.playerType.valueOf(this.entityClass.toUpperCase()).ordinal()][Database.statName.DEFENSE.ordinal()];
		this.currentDef = this.origDef;

		this.origSpeed += Database.playerLevelUpStat[Database.playerType.valueOf(this.entityClass.toUpperCase()).ordinal()][Database.statName.SPEED.ordinal()];
		this.currentSpeed = this.origSpeed;

		this.origATK += Database.playerLevelUpStat[Database.playerType.valueOf(this.entityClass.toUpperCase()).ordinal()][Database.statName.ATTACK.ordinal()];
		this.currentAtk = this.origATK;

		this.origMDef += Database.playerLevelUpStat[Database.playerType.valueOf(this.entityClass.toUpperCase()).ordinal()][Database.statName.MAGICAL_DEFENSE.ordinal()];
		this.currentMDef = this.origMDef;

		this.origAccuracy += Database.playerLevelUpStat[Database.playerType.valueOf(this.entityClass.toUpperCase()).ordinal()][Database.statName.ACCURACY.ordinal()];
		this.currentAccuracy = this.origAccuracy;

		this.origCritDmg += Database.playerLevelUpStat[Database.playerType.valueOf(this.entityClass.toUpperCase()).ordinal()][Database.statName.CRITICAL_DAMAGE.ordinal()];
		this.currentCritDmg = this.origCritDmg;

		this.origCritRate += Database.playerLevelUpStat[Database.playerType.valueOf(this.entityClass.toUpperCase()).ordinal()][Database.statName.CRITICAL_RATE.ordinal()];
		this.currentCritRate = this.origCritRate;

		this.weight += 10;
	}


	@Override
	public String toString() {
		String returnStr = new String("Name: " + this.name + "\nClass: " + this.entityClass + "\nGender: " + this.gender + "\nLevel: " + Math.round(this.level) + "\nHealth: " + 
				this.currentHealth + "/" + this.healthWithChanges + "\nStamina Bar: " + this.staminaBar + "/100" + "\nElement: " + this.element + "\nAttack Stat: " + this.currentAtk
				+ "\nDefense Stat: "+ this.currentDef + "\nMagic Defense Stat: "+ this.currentMDef + "\nSpeed Stat: " + this.currentSpeed 
				+ "\nAccuracy: " + this.currentAccuracy*100 + "%" + "\nCrit Damage:  " + this.currentCritDmg + "\nCrit Rate: " + this.currentCritRate*100 + "%"
				+ "\nLighting Resistance: " + this.currentLightingResist + "%" + "\nIce Resistance: " + this.currentIceResist + "%"
				+ "\nFire Resistance: " + this.currentFireResist + "%");
		if (this.weapon != null) {
			returnStr += "\nWeapon: " + this.weapon.getItemName();
		} else {
			returnStr += "\nWeapon: None Equiped";
		}
		return returnStr;
	}

	// player normal attack
	@Override
	public boolean normalAttack(Entity entity, ArrayList<Enemy> enemies) {
		Entity selectedEntity = null;

		int numIndex = -1;
		boolean targetFound = false;
		boolean validInput;
		boolean askAgain = true;
		boolean isDead = false;

		// prompt user to choose
		System.out.println("Press letter [K] to kick.");
		System.out.println("Press letter [A] to use weapon.");
		System.out.println("Press letter [X] to cancel.");

		String userChoice;
		String battleChoice = null;

		// will ask until right choice is done
		while (askAgain == true) {
			userChoice = Main.in.nextLine().toLowerCase().strip();
			if (userChoice.equalsIgnoreCase("K")) {
				battleChoice = "KICK";
				askAgain = false;
			} else if (userChoice.equalsIgnoreCase("A")) {
				battleChoice = "WEAPON";
				askAgain = false;
			} else if (userChoice.equalsIgnoreCase("X")) { 
				return false;
			}else {
				System.out.println("Sorry please type either K or A.");

			}
		}
		// prompt to pick a target
		System.out.println("Pick a enemy.");
		System.out.println("Enter 0 to target yourself.");

		while (targetFound == false) {

			validInput = false;
			while (validInput == false) {
				// try cstch in case of wrong input
				try {
					numIndex = Main.in.nextInt();
					validInput = true;
				} catch (InputMismatchException ex) {
					Main.in.nextLine();
					break;
				}
			}
			// will decide whether to ask again or it'll choose a enemy or you 
			if (numIndex < 0) {
				System.out.println("Please type number of a enemy.");

			} else if (numIndex == 0) {
				targetFound = true;
				selectedEntity = this;

			} else if (numIndex > 0 && enemies.size() >= numIndex) {
				targetFound = true;
				selectedEntity = enemies.get(numIndex - 1);
			} else if (numIndex > enemies.size()) {
				System.out.println("Please pick a number of a enemy.");
			}
		}

		double dmgCount = 0;
		// next four variables are used to determine whether you hit or crit
		boolean didHit = false;
		boolean didCrit = false;
		float critChance;
		float hitChance;

		Random chance = new Random();
		// random number use to generate chance of hitting or criting
		critChance = chance.nextFloat();
		hitChance = chance.nextFloat();
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
		// specs for attack
		String[] dmgSpecs = new String[2];
		String damageType;
		String element;


		if (battleChoice.equals("WEAPON")) {
			// does this if user has no weapon throw a object
			if (this.weapon == null) {
				System.out.println(this.name + " remembers he doesn't have a weapon equiped so he uses quick thinking.");
				System.out.println("He grabs the nearest throwable object and throw it.");
				damageType = "Range";
				element = "None";
				dmgSpecs[0] = damageType;
				dmgSpecs[1] = element;

				double dmgRange;
				dmgRange = chance.nextFloat();

				isDead = selectedEntity.takeDmg(dmgSpecs, this.currentAtk * dmgRange);
				return true;
				// does this if user has weapon
			} else if (this.weapon != null) {
				damageType = this.weapon.getDamageType();
				element = this.weapon.getElement();
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
					System.out.println(this.name + " uses " + weapon.getItemName() + " and attacks " + selectedEntity.getName() + "!");
					isDead = selectedEntity.takeDmg(dmgSpecs, dmgCount);
				} 
			}
			// does this if player picked kick
		} else if (battleChoice.equals("KICK")) {
			damageType = "Melee";
			element = "None";
			dmgSpecs[0] = damageType;
			dmgSpecs[1] = element;
			if (didHit == true) {
				if (didCrit == true) {
					dmgCount = this.currentAtk + this.currentCritDmg;
				} else if (didCrit == false) {
					dmgCount = this.currentAtk;
				}
				// if the dmg is negative or 0 it will set dmg to 1
				if (dmgCount <= 0) {
					dmgCount = 1;
				}
				// dsiplays message does damage to enemy
				System.out.println(this.name + " kicked " + selectedEntity.getName() + "!");
				isDead = selectedEntity.takeDmg(dmgSpecs, dmgCount);
			} 
		}
		// if enemy is dead they are removed from list
		if (isDead == true) {
			if (selectedEntity.equals(this) == false) {
				enemies.remove(selectedEntity);
			}
		}
		// if didn't hit this happens
		if (didHit == false) {
			System.out.println(this.name + " missed the attack. ");
		}

		return true;
	}

	@Override
	public boolean useInventory(Entity entity, ArrayList<Enemy> enemies, boolean inBattle) {
		ArrayList<Item> matchList = null;

		String userDecision;
		boolean useBag = true;
		boolean battleActionExecuted = false;
		System.out.println("Bag is opened.");
		System.out.println("(Press enter to continue)");
		Main.in.nextLine();
		// loop to use inventory
		while (useBag == true) {
			boolean askUser2 = true;

			// loop for outside battle
			if (inBattle == false) {
				// prompt user to decide
				System.out.println("Press letter [D] to look at all items in bag.");
				System.out.println("Press letter [S] to search bag.");
				System.out.println("Press letter [A] to access a item or remove it.");
				System.out.println("Press letter [X] to close bag.");

				userDecision = Main.in.nextLine().strip().toLowerCase();
				// will do stuff depending on input
				if (userDecision.equals("d")) {
					this.bag.displayInventory();
				} else if (userDecision.equals("s")) {
					String searchInput;
					// prompt for search criteria
					System.out.println("Search either by item type, or name");
					System.out.println(("(Item types are equipment, consumable, weapon, and potion)."));
					searchInput = Main.in.nextLine().strip().toLowerCase();
					this.bag.searchInventory(searchInput);

				} else if (userDecision.equals("a")) {
					Item accessedItem = null;
					boolean itemFound = false;
					String userDecision2;
					String searchInput;
					while (askUser2 == true) {
						// prompt for search choice
						System.out.println("Press letter [D] if you want to display all items to select.");
						System.out.println("Press letter [S] if know name or type of item you want to select.");
						userDecision2 = Main.in.nextLine().strip().toLowerCase();

						if (userDecision2.equals("d")) {
							// display whole inventory
							this.bag.displayInventory();
							matchList = this.bag.getItemList();
							askUser2 = false;
						} else if (userDecision2.equals("s")) {
							// prompt for search criteria
							System.out.println("Search either by item type, or name");
							System.out.println(("(Item types are equipment, consumable, weapon, and potion)."));
							while (matchList == null || matchList.size() == 0) {
								searchInput = Main.in.nextLine().strip().toLowerCase();
								matchList = this.bag.searchInventory(searchInput);
								if (matchList.size() == 0) {
									// if match list is empty it'll say try something else
									System.out.println("Please search using different input.");
								} else {
									askUser2 = false;
								}
								if (matchList.size() == 0) {
									String userDecision3;
									// prompt to leave or search
									System.out.println("Press letter [X] if you want don't want to search anymore.");
									System.out.println("(This will display the inventory)");
									System.out.println("Press anything else if you to continue searching by name or type.");
									userDecision3 = Main.in.nextLine().strip().toLowerCase();
									if (userDecision3.equals("x")) {
										// done using inventory
										this.bag.displayInventory();
										matchList = this.bag.getItemList();
										askUser2 = false;
									} else {
										System.out.println("Search either by item type, or name");
										System.out.println(("(Item types are equipment, consumable, weapon, and potion)."));
									}
								}		
							}		
						} else {
							System.out.println("Sorry please type either D or S.");
						}
					}
					String userNum;
					System.out.println("Type in the number of the item you want to access or remove.");
					// try to access item
					while (itemFound == false) {
						// ask until item is not null
						userNum = Main.in.nextLine().strip().toLowerCase();
						accessedItem = this.bag.findItem(userNum, matchList);
						if (accessedItem != null) {
							itemFound = true;

						}
					}
					System.out.println(accessedItem.toString());
					System.out.println();
					boolean isEquipment = false;
					boolean isPotion = true;
					// if equipment prompt to access equipment or consumable
					if (accessedItem.getItemID().getItemType().equalsIgnoreCase("Equipment")) {
						isEquipment = true;
						boolean isEquip = accessedItem.isEquip();
						if (isEquip == true ) {
							System.out.println("Press letter [U] if you want to unequip this item.");
						} else if (isEquip == false) {
							System.out.println("Press letter [E] if you want to equip this item.");
						}
					} else if (accessedItem.getItemID().getItemType().equalsIgnoreCase("Consumable")) {
						isPotion = true;
						System.out.println("Press letter [U] if you want to use this item.");
					}
					System.out.println("Press letter [R] if you want to throw away " + accessedItem.getItemName() + ".");
					System.out.println();
					String accessedOption = Main.in.nextLine().strip().toLowerCase();
					// accessed item based on user input
					if (accessedOption.equals("u") && isPotion == true) {
						accessedItem.useItem(this);
						// deequip 
					} else if ( accessedOption.equals("u") && accessedItem.isEquip() == true && isEquipment == true) {
						System.out.println(this.weapon.getItemName() + " is unequiped.");
						deEquipWeapon();
						// equip
					} else if ( accessedOption.equals("e") && accessedItem.isEquip() == false) {
						equipWeapon(this.bag.getMatchingWeapon(accessedItem));
						System.out.println(this.weapon.getItemName() + " is equiped.");
						// remove item
					} else if ( accessedOption.equals("r")) {
						this.bag.removeItem(accessedItem);
					} 
				} else if (userDecision.equals("x")) {
					useBag = false;
				} else {
					System.out.println("Sorry please type letter D, S, A, or X.");
				}
			} else if (inBattle == true) {
				// loop for inside battle
				String userNum2;
				Item accessedItem = null;
				// only potions accesible
				this.bag.displayConsumables();
				matchList = this.bag.consumableList();
				// prompt to pick a item
				System.out.println("Enter the number of the item you want to use.");
				System.out.println("Enter letter [X] if you want to exit.");
				while (accessedItem == null) {
					userNum2 = Main.in.nextLine().strip().toLowerCase();
					if (userNum2.equals("x")) {
						return false;
					}
					accessedItem = this.bag.findItem(userNum2, matchList);
				}
				Entity selectedEntity2 = null;
				int numIndex = -5;
				boolean targetFound = false;
				boolean validInput2 = false;
				// prompt to pick enemy
				System.out.println("Pick a enemy.");
				System.out.println("Enter 0 to target yourself.");

				while (targetFound == false) {
					validInput2 = false;
					while (validInput2 == false) {
						try {
							numIndex = Main.in.nextInt();
							validInput2 = true;
						} catch (InputMismatchException ex) {
							Main.in.nextLine();
							break;
						}
					}
					if (numIndex < 0) {
						System.out.println("Please type number of a enemy.");

					} else if (numIndex == 0) {
						targetFound = true;
						selectedEntity2 = this;

					} else if (numIndex > 0 && enemies.size() >= numIndex) {
						targetFound = true;
						selectedEntity2 = enemies.get(numIndex - 1);
					}
				}
				// use item
				Potion foundPotion = this.bag.getMatchingPotion(accessedItem);
				foundPotion.useItem(selectedEntity2);
				// it's because the player throws away the potion bottle after use
				// because it's useless
				foundPotion.deleteItemAfterUse(this);
				battleActionExecuted = true;
				useBag = false;
			}
		}
		return battleActionExecuted;

	}
	// combat loop for player
	@Override
	public void combatAction(Entity entity, ArrayList<Enemy> enemies) {
		boolean didBattleAction = false;
		String battleDecision = null;

		while (didBattleAction == false) {
			// prompt user to decide
			System.out.println();
			System.out.println("Player Turn:");
			System.out.println("Press letter [E] to see all enemy battle info.");
			System.out.println("Press letter [C] to check stats.");
			System.out.println("Press letter [A] for normal attack.");
			System.out.println("Press letter [S] for special attack.");
			System.out.println("Press letter [P] to use potion");
			System.out.println();
			battleDecision = Main.in.nextLine().strip();
			// does action base on userinput
			if (battleDecision.equalsIgnoreCase("C")) {
				System.out.println(this.toString());
			} else if (battleDecision.equalsIgnoreCase("A")) {
				didBattleAction = normalAttack(this, enemies);
				// ran enemy doesn't matter here cuz of targeting system
			} else if (battleDecision.equalsIgnoreCase("S")) {
				didBattleAction = specialMove(this, enemies);

			} else if (battleDecision.equalsIgnoreCase("P")) {
				didBattleAction = useInventory(this, enemies, true);	
				// print all enemy information
			} else if (battleDecision.equalsIgnoreCase("E")) {
				System.out.println("Enemies:");
				System.out.println("______________________________________________________"
						+ "_________________________________________________");

				for (int i =0 ; i < enemies.size(); i++) {
					System.out.print((i + 1) + ". ");
					enemies.get(i).printBattleInfo();
					System.out.println();
				}
			}
		}
	}
	// reset battle stats
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
