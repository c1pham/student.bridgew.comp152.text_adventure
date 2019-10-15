package edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.item.ItemID;

public class BambooStick extends Sword {
	public BambooStick(float level) {
		super("Bamboo Stick", 1f + (level * 1), 1f + (level * 1), 
				Database.swordStatsVar[Database.swordType.BAMBOO_STICK.ordinal()][Database.swordStat.DAMAGE.ordinal()], 
				Database.swordStatsVar[Database.swordType.BAMBOO_STICK.ordinal()][Database.swordStat.ATTACK_CHANGE.ordinal()], 
				Database.swordStatsVar[Database.swordType.BAMBOO_STICK.ordinal()][Database.swordStat.CRIT_DMG_CHANGE.ordinal()],
				Database.swordStatsVar[Database.swordType.BAMBOO_STICK.ordinal()][Database.swordStat.SPEED_INCREASE.ordinal()],
				"None");
		this.level = level;
		createItemID();
	}

	// return string of weapon specs
	@Override
	public String toString() {
		String returnStr = new String("Item Type: " + this.itemID.getSubItemType1() + "\nName: " + this.getItemName()
		+ "\nLevel: " + this.level + "\nDamage: " + this.damage + "\nElement: " + this.element
		+ "\nAttack Stat Change: " + this.atkChange + "\nCrit Damage Change: " + this.critDamageChange 
		+ "\nSpeed Change : " + this.speedIncrease + "\nWeight: " + this.weight + "lbs" + "\nPrice: " 
		+ this.price + " gold");

		if (this.isEquip() == true) {
			returnStr += "\n(This item is equiped)";
		}

		return returnStr;
	}


	// create a unique id for object
	@Override
	protected void createItemID() {
		ItemID itemID = new ItemID("Equipment", "Weapon", "Sword");
		this.itemID = itemID;
	}

	// this is so when item is equip stats can be changes
	@Override
	public void equipStatChanges(Entity entity) {
		entity.setCurrentAtk(entity.getCurrentAtk() + this.atkChange);
		entity.setCurrentCritDmg(entity.getCurrentCritDmg() + this.critDamageChange);
		entity.setCurrentSpeed(entity.getCurrentSpeed() + this.speedIncrease);

		entity.setOrigATK(entity.getOrigATK() + this.atkChange);
		entity.setOrigCritDmg(entity.getOrigCritDmg() + this.critDamageChange);
		entity.setOrigSpeed(entity.getOrigSpeed() + this.speedIncrease);
	}

	// this so when item is de equip stats can be reverted to normal
	@Override
	public void removeStatChanges(Entity entity) {
		entity.setCurrentAtk(entity.getCurrentAtk() - this.atkChange);
		entity.setCurrentCritDmg(entity.getCurrentCritDmg() - this.critDamageChange);
		entity.setCurrentSpeed(entity.getCurrentSpeed() - this.speedIncrease);


		entity.setOrigATK(entity.getOrigATK() - this.atkChange);
		entity.setOrigCritDmg(entity.getOrigCritDmg() - this.critDamageChange);
		entity.setOrigSpeed(entity.getOrigSpeed() - this.speedIncrease);
	}


	// this will allow user to use item when they it, it equips or de equips then changes stats appropriately
	// also keep in mind scenarip like if they don't have weapons
	// so short circuit operators are there to account for that
	@Override
	public void useItem(Entity entity) {
		if (entity.getWeapon() != null && entity.getWeapon().isEquip() == true ) {
			removeStatChanges(entity);
			this.Equip = false;
		} else if (entity.getWeapon() == null || entity.getWeapon().isEquip() == false ) {
			equipStatChanges(entity);
			this.Equip = true;
		}
	}

}
