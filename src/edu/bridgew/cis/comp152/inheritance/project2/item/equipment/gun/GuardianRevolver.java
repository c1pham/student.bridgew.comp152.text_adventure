package edu.bridgew.cis.comp152.inheritance.project2.item.equipment.gun;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.item.ItemID;

public class GuardianRevolver extends Gun {
	// special attribute of this gun
	protected double defenseIncrease;
	protected double healthIncrease;
	public GuardianRevolver(double level) {

		super("Guardian Revolver", "None",
				Database.gunStatsVar[Database.gunType.GUARDIAN_REVOLVER.ordinal()][Database.gunStats.DAMAGE.ordinal()],
				Database.gunStatsVar[Database.gunType.GUARDIAN_REVOLVER.ordinal()][Database.gunStats.ATTACK_CHANGE.ordinal()],
				Database.gunStatsVar[Database.gunType.GUARDIAN_REVOLVER.ordinal()][Database.gunStats.CRIT_DMG_CHANGE.ordinal()],
				Database.gunStatsVar[Database.gunType.GUARDIAN_REVOLVER.ordinal()][Database.gunStats.SCOPE_AC.ordinal()]);
		this.defenseIncrease = Database.gunStatsVar[Database.gunType.GUARDIAN_REVOLVER.ordinal()][Database.swordStat.OPTIONAL_DEFENSE.ordinal()];
		this.healthIncrease = Database.gunStatsVar[Database.gunType.GUARDIAN_REVOLVER.ordinal()][Database.swordStat.OPTIONAL_HEALTH.ordinal()];
		//Create way to increase stats by level
		this.level = level;
		createItemID();
	}




	@Override
	public String toString() {
		String returnStr = new String("Item Type: Gun" + "\nName: " + this.itemName + "\nDamage: " + this.damage + "\nElement: " + this.element
				+ "\nAttack Stat Change: " + this.atkChange + "\nCrit Damage Change: " + this.critDamageChange + "\nScope Accuracy: " + this.scopeAccuracy
				+ "\nDefense Increase : " + this.defenseIncrease + "\nHealth Increase: " + this.healthIncrease 
				+	"\nWeight: " + this.weight + "lbs" + "\nPrice: " 
				+ this.price + " gold");
		if (this.isEquip() == true) {
			returnStr += "\n(This item is equiped)";
		}
		return returnStr;
	}


	// this changes entity stats according to fields
	@Override
	public void equipStatChanges(Entity entity) {
		entity.setCurrentAtk(entity.getCurrentAtk() + this.atkChange);
		entity.setCurrentCritDmg(entity.getCurrentCritDmg() + this.critDamageChange);
		entity.setOrigATK(entity.getOrigATK() + this.atkChange);
		entity.setOrigCritDmg(entity.getOrigCritDmg() + this.critDamageChange);
		entity.setCurrentDef(entity.getCurrentDef() + this.defenseIncrease);

		entity.setCurrentHealth(entity.getCurrentHealth() + this.healthIncrease);
		entity.setHealthWithChanges(entity.getHealthWithChanges() + this.healthIncrease);
		entity.setOrigHealth(entity.getOrigHealth() + this.healthIncrease);

		entity.setOrigAccuracy(entity.getOrigAccuracy() + this.scopeAccuracy);
		entity.setCurrentAccuracy(entity.getCurrentAccuracy() + this.scopeAccuracy);
		entity.setOrigDef(entity.getOrigDef() + this.defenseIncrease);
	}
	// this removes stat changes from entity from weapon
	@Override
	public void removeStatChanges(Entity entity) {
		entity.setCurrentAtk(entity.getCurrentAtk() - this.atkChange);
		entity.setCurrentCritDmg(entity.getCurrentCritDmg() - this.critDamageChange);
		entity.setOrigATK(entity.getOrigATK() - this.atkChange);
		entity.setOrigCritDmg(entity.getOrigCritDmg() - this.critDamageChange);
		entity.setCurrentDef(entity.getCurrentDef() - this.defenseIncrease);

		entity.setCurrentHealth(entity.getCurrentHealth() - this.healthIncrease);
		entity.setHealthWithChanges(entity.getHealthWithChanges() - this.healthIncrease);
		entity.setOrigHealth(entity.getOrigHealth() - this.healthIncrease);

		entity.setOrigAccuracy(entity.getOrigAccuracy() - this.scopeAccuracy);
		entity.setCurrentAccuracy(entity.getCurrentAccuracy() - this.scopeAccuracy);
		entity.setOrigDef(entity.getOrigDef() - this.defenseIncrease);
	}

	// this creates unique Id for player
	@Override
	protected void createItemID() {
		ItemID itemID = new ItemID("Equipment", "Weapon", "Gun");
		this.itemID = itemID;
	}

	// this has short circuit operators to account for bugs like if the entity has a weapon or not
	// will also choose to unequip or deequip base of current conditions like if the item is equiped or not and if there is a item equiped at all
	@Override
	public void useItem(Entity entity) {
		if ( entity.getWeapon() != null && entity.getWeapon().isEquip() == true ) {
			removeStatChanges(entity);
			this.Equip = false;
		} else if (entity.getWeapon() == null ||  entity.getWeapon().isEquip() == false  ) {
			equipStatChanges(entity);
			this.Equip = true;
		}
	}



}
