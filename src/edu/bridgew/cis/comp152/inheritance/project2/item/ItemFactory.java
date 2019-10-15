package edu.bridgew.cis.comp152.inheritance.project2.item;

import edu.bridgew.cis.comp152.inheritance.project2.item.consumable.HealthPotion;
import edu.bridgew.cis.comp152.inheritance.project2.item.consumable.Potion;
import edu.bridgew.cis.comp152.inheritance.project2.item.consumable.StaminaPotion;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.bow.ElvenBow;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.bow.GuardianBow;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.bow.LongBow;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.bow.OldBow;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.bow.PowerBow;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.club.BigTuna;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.club.BronzeLeg;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.club.DeerBone;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.club.ElvenClub;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.club.Firewood;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.club.IronMace;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.club.RobotArm;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.club.RobotLeg;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.club.TreeBranch;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.gun.CoconutLauncher;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.gun.ElvenShotgun;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.gun.GuardianRevolver;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.gun.IonCannon;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.gun.MissleLauncher;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.gun.PlasmataRifle;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.gun.Revolver;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.gun.WaterGun;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword.BambooStick;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword.ElvenSword;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword.ExecutionerBlade;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword.GuardianBlade;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword.HeroBlade;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword.MeteorBlade;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword.SharkStick;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword.SharpStick;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword.Twig;

// commented this object makes items
public class ItemFactory {
	public ItemFactory(){
	}
	// make weapons depending on string then returns potion, it if doesn't match it returns null
	public Weapon getWeapon(String itemName) {
		Weapon weaponCreated = null;
		if (itemName.equalsIgnoreCase("Sharp Stick")) {
			weaponCreated = new SharpStick(1f);
		} else if (itemName.equalsIgnoreCase("Plasmata Rifle")) {
			weaponCreated = new PlasmataRifle(1f);
		} else if (itemName.equalsIgnoreCase("Shark Stick")) {
			weaponCreated = new SharkStick(1f);
		} else if (itemName.equalsIgnoreCase("Hero Blade")) {
			weaponCreated = new HeroBlade(1f);
		} else if (itemName.equalsIgnoreCase("Long Bow")) {
			weaponCreated = new LongBow(1f);
		} else if (itemName.equalsIgnoreCase("Ion Cannon")) {
			weaponCreated = new IonCannon(1f);
		} else if (itemName.equalsIgnoreCase("Power Bow")) {
			weaponCreated = new PowerBow(1f);
		} else if (itemName.equalsIgnoreCase("Bamboo Stick")) {
			weaponCreated = new BambooStick(1f);
		} else if (itemName.equalsIgnoreCase("Executioner Blade")) {
			weaponCreated = new ExecutionerBlade(1f);
		} else if (itemName.equalsIgnoreCase("Tree Branch")) {
			weaponCreated = new TreeBranch(1f);
		} else if (itemName.equalsIgnoreCase("Coconut Launcher")) {
			weaponCreated = new CoconutLauncher(1f);
		} else if (itemName.equalsIgnoreCase("Water Gun")) {
			weaponCreated = new WaterGun(1f);
		} else if (itemName.equalsIgnoreCase("Robot Arm")) {
			weaponCreated = new RobotArm(1f);
		} else if (itemName.equalsIgnoreCase("Elven Club")) {
			weaponCreated = new ElvenClub(1f);
		} else if (itemName.equalsIgnoreCase("Elven Bow")) {
			weaponCreated = new ElvenBow(1f);
		} else if (itemName.equalsIgnoreCase("Elven Sword")) {
			weaponCreated = new ElvenSword(1f);
		} else if (itemName.equalsIgnoreCase("Firewood")) {
			weaponCreated = new Firewood(1f);
		} else if (itemName.equalsIgnoreCase("Big Tuna")) {
			weaponCreated = new BigTuna(1f);
		} else if (itemName.equalsIgnoreCase("Meteor Blade")) {
			weaponCreated = new MeteorBlade(100f);
		} else if (itemName.equalsIgnoreCase("Guardian Blade")) {
			weaponCreated = new GuardianBlade(100f);
		} else if (itemName.equalsIgnoreCase("Twig")) {
			weaponCreated = new Twig(1f);
		} else if (itemName.equalsIgnoreCase("Deer Bone")) {
			weaponCreated = new DeerBone(1f);
		} else if (itemName.equalsIgnoreCase("Robot Leg")) {
			weaponCreated = new RobotLeg(1f);
		} else if (itemName.equalsIgnoreCase("Bronze Leg")) {
			weaponCreated = new BronzeLeg(1f);
		} else if (itemName.equalsIgnoreCase("Missle Launcher")) {
			weaponCreated = new MissleLauncher(1f);
		} else if (itemName.equalsIgnoreCase("Iron Mace")) {
			weaponCreated = new IronMace(1f);
		} else if (itemName.equalsIgnoreCase("Old Bow")) {
			weaponCreated = new OldBow(1f);
		} else if (itemName.equalsIgnoreCase("Revolver")) {
			weaponCreated = new Revolver(1f);
		}  else if (itemName.equalsIgnoreCase("Guardian Bow")) {
			weaponCreated = new GuardianBow(1f);
		} else if (itemName.equalsIgnoreCase("Guardian Revolver")) {
			weaponCreated = new GuardianRevolver(1f);
		} else if (itemName.equalsIgnoreCase("Elven Shotgun")) {
			weaponCreated = new ElvenShotgun(1f);
		} 
		
		return weaponCreated;
	}
	// make potions depending on string then returns potion, it if doesn't match it returns null, 
	public Potion getPotion(String itemName) {
		Potion potionCreated = null;
		if (itemName.equals("Large Health Potion")) {
			potionCreated = new HealthPotion(itemName, 30, 30, 0 , 0 ,1000 );
		} else if (itemName.equals("Small Health Potion")) {
			potionCreated = new HealthPotion(itemName, 20, 20, 0 , 0 ,250 );
		} else if (itemName.equals("Medium Health Potion")) {
			potionCreated = new HealthPotion(itemName, 10, 10, 0 , 0 ,500 );
		} else if (itemName.equals("Small Stamina Potion")) {
			potionCreated = new StaminaPotion(itemName, 20);
		} else if (itemName.equals("Medium Stamina Potion")) {
			potionCreated = new StaminaPotion(itemName, 40);
		} else if (itemName.equals("Large Stamina Potion")) {
			potionCreated = new StaminaPotion(itemName, 60);
		} else if (itemName.equals("Temporary Health Potion")) {
			potionCreated = new HealthPotion(itemName, 10, 10, 0 , 500 ,500 );
		} else if (itemName.equals("Pernament Health Potion")) {
			potionCreated = new HealthPotion(itemName, 10, 10, 200 , 0 ,200 );
		} else if (itemName.equals("Large Temporary Health Potion")) {
			potionCreated = new HealthPotion(itemName, 100, 100, 0 , 2000 , 2000);
		} else if (itemName.equals("Small Pernament Health Potion")) {
			potionCreated = new HealthPotion(itemName, 10, 10, 50 , 0 ,50);
		} else if (itemName.equals("Medium Pernament Health Potion")) {
			potionCreated = new HealthPotion(itemName, 10, 10, 100 , 0 ,100);
		} else if (itemName.equals("Super Health Potion")) {
			potionCreated = new HealthPotion(itemName, 100, 100, 0 , 0 ,5000);
		} else if (itemName.equals("Nanobots")) {
			potionCreated = new HealthPotion(itemName, 100, 100, 0 , 0 , 300);
		} else if (itemName.equals("Support Drive")) {
			potionCreated = new StaminaPotion(itemName, 10);
		} 
		return potionCreated;
	}
}
