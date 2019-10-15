package edu.bridgew.cis.comp152.inheritance.project2.entity.enemies;

import java.lang.Integer;

import edu.bridgew.cis.comp152.inheritance.project2.Main;
import edu.bridgew.cis.comp152.inheritance.project2.ability.Camoflage;
import edu.bridgew.cis.comp152.inheritance.project2.ability.Combustion;
import edu.bridgew.cis.comp152.inheritance.project2.ability.FinalStorm;
import edu.bridgew.cis.comp152.inheritance.project2.ability.MachineUpgrade;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.elves.DarkElf;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.elves.ShireElf;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.human.Archer;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.human.Warrior;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.machine.Droid;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.machine.MechaAnimal;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.machine.TitaniumHydra;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword.MeteorBlade;

public class EnemyFactory {
	// commented
	public EnemyFactory() {
		
	}
	// returns a enemy
	public Enemy getEnemy(String enemyName) {
		// take string and breaks it apart into a number and a type
		String[] enemySpecs = enemyName.split("@");
		String enemyType = enemySpecs[0].toLowerCase();
		// used to determine which type of enemy to generate
		String enemyLevelS = enemySpecs[1];
		// used to determine level of enemy
		int enemyLevelI = Integer.valueOf(enemyLevelS);
		// if str is emppty then it returns null
		if(enemyType.equals(null)) {
			return null;
		}
		// creates enemy base on string and level given then returns it
		if(enemyType.equals("warrior")) {
			return new Warrior("Warrior Scout", enemyLevelI);
			
		} else if (enemyType.equals("archer")) {
			return new Archer("Archer Scout", enemyLevelI);
			
		} else if (enemyType.equals("mecha animal")) {
			return new MechaAnimal("Mecha Lion", enemyLevelI, false);
			
		} else if (enemyType.equals("mecha animalf")) {
			return new MechaAnimal("Mecha Eagle", enemyLevelI, true);
			
		} else if (enemyType.equals("droid")) {
			return new Droid("T-94", enemyLevelI);
			
		} else if (enemyType.equals("shire elf")) {
			return new ShireElf("Elven Scout", enemyLevelI);
			
		} else if (enemyType.equals("dark elf")) {
			return new DarkElf("Elven Scourge", enemyLevelI);
		} else if (enemyType.equals("thief")) {
			return new Archer("Thief", enemyLevelI);
		} else if (enemyType.equals("thiefw")) {
			return new Warrior("Thief", enemyLevelI);
		} else if (enemyType.equals("high elf zeref")) {
			return new ShireElf("Elven Scout", enemyLevelI);
			
		} else if (enemyType.equals("high elf meredith")) {
			return new ShireElf("High Elf Meredith", enemyLevelI);
			
		} else if (enemyType.equals("high elf zeref")) {
			return new ShireElf("High Elf Zeref", enemyLevelI);
			
		} else if (enemyType.equals("maximus")) {
			Enemy customArcher = new Archer("General Maximus", enemyLevelI);
			customArcher.getAbilityList().add(new FinalStorm());
			return customArcher;
		} else if (enemyType.equals("general")) {
			Enemy customWarrior = new Warrior("The General", enemyLevelI);
			customWarrior.getAbilityList().add(new Camoflage());		
			customWarrior.setCurrentDef(customWarrior.getCurrentDef() + 800);
			customWarrior.setOrigDef(customWarrior.getOrigDef() + 800);
			customWarrior.setOrigHealth(1800);
			customWarrior.setCurrentHealth(1800);
			customWarrior.setHealthWithChanges(1800);
			customWarrior.equipWeapon(new MeteorBlade(1f));
			return customWarrior;
		} else if (enemyType.equals("hydra")) {
			return new TitaniumHydra("Mother Hydra", enemyLevelI);
		} else if (enemyType.equals("hatchling")) {
			Enemy CustomHydra = new MechaAnimal("Hatchling Hydra", enemyLevelI, false);
			CustomHydra.getAbilityList().add(new MachineUpgrade());
			CustomHydra.getAbilityList().add(new Combustion());
			CustomHydra.getBag().emptyInventory();
			return CustomHydra;
		} else if (enemyType.equals("juvenile")) {
			Enemy CustomHydra = new MechaAnimal("Juvenile Mecha Hydra", enemyLevelI, true);
			CustomHydra.getAbilityList().add(new MachineUpgrade());
			CustomHydra.getBag().emptyInventory();
			CustomHydra.getBag().addItem(Main.iFact.getPotion("Support Drive"));
			return CustomHydra;
		} 
		
		return null; 
	}
}
