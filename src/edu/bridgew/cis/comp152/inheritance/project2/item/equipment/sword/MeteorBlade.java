package edu.bridgew.cis.comp152.inheritance.project2.item.equipment.sword;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.item.ItemID;

public class MeteorBlade extends Sword {

	public MeteorBlade(float level) {
		super("Meteor Blade", 600f + (level * 2), 45f + (level * 2), 
				Database.swordStatsVar[Database.swordType.METEOR_BLADE.ordinal()][Database.swordStat.DAMAGE.ordinal()], 
				Database.swordStatsVar[Database.swordType.METEOR_BLADE.ordinal()][Database.swordStat.ATTACK_CHANGE.ordinal()], 
				Database.swordStatsVar[Database.swordType.METEOR_BLADE.ordinal()][Database.swordStat.CRIT_DMG_CHANGE.ordinal()],
				Database.swordStatsVar[Database.swordType.METEOR_BLADE.ordinal()][Database.swordStat.SPEED_INCREASE.ordinal()],
				"Fire");
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

	// when use item you equip or dequip it changes whether it is equiped or not then change stats of player
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
