package edu.bridgew.cis.comp152.inheritance.project2.entity.enemies;


import java.util.ArrayList;
import java.util.Random;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.Main;
import edu.bridgew.cis.comp152.inheritance.project2.ability.Ability;
import edu.bridgew.cis.comp152.inheritance.project2.item.Item;
import edu.bridgew.cis.comp152.inheritance.project2.item.consumable.Potion;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;

public abstract class Enemy extends Entity {
	protected ArrayList<Weapon> weaponDrops;
	protected ArrayList<Potion> potionDrops;
	// constructor
	public Enemy(String name, String entityClass, char gender, double level, String element, double origFireResist,
			double origIceResist, double origLightingResist, double origATK, double origDef, double origMDef, double origSpeed,
			double origHealth, double origAccuracy, double origCritDmg, double origCritRate, Ability[] abilities, Weapon weapon) {
		super(name, entityClass, gender, level , element, origFireResist, origIceResist,  
				origLightingResist, origATK, origDef, origMDef,
				origSpeed, origHealth, origAccuracy,origCritDmg, origCritRate, abilities, weapon);	
		this.weaponDrops = new ArrayList<Weapon>();
		this.potionDrops = new ArrayList<Potion>();
		// they will be designed to have random drops and receive random items into the inventory
		Random rand = new Random();

		// randomized number of items 
		int randWeapNum = rand.nextInt(2);
		int randPotNum = rand.nextInt(4); int ranIndex;
		// loops and creates weapon items for each enemy then adds to inventory
		for (int i = 0; i < randWeapNum; i++) { 
			ranIndex = rand.nextInt(Database.RandomizeWeaponStrings.length);
			Weapon ranWeapon = Main.iFact.getWeapon(Database.RandomizeWeaponStrings[ranIndex]);
			this.bag.addItem(ranWeapon); 
			this.weaponDrops.add(Main.iFact.getWeapon(Database.RandomizeWeaponStrings[ranIndex]));
		}
		// loops and creates potion items for each enemy then adds to inventory
		for (int i = 0; i < randPotNum; i++) { 
			ranIndex =  rand.nextInt(Database.RandomizePotionStrings.length);
			Potion ranPotion =   Main.iFact.getPotion(Database.RandomizePotionStrings[ranIndex]);
			this.bag.addItem(ranPotion); 
			this.potionDrops.add(Main.iFact.getPotion(Database.RandomizePotionStrings[ranIndex]));
		}
		
	}

	// getter and setter
	public ArrayList<Weapon> getWeaponDrops() {
		return weaponDrops;
	}

	public ArrayList<Potion> getPotionDrops() {
		return potionDrops;
	}

}
