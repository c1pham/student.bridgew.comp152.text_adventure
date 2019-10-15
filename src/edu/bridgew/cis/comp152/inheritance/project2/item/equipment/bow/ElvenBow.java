package edu.bridgew.cis.comp152.inheritance.project2.item.equipment.bow;

import edu.bridgew.cis.comp152.inheritance.project2.Database;
import edu.bridgew.cis.comp152.inheritance.project2.Entity;
import edu.bridgew.cis.comp152.inheritance.project2.item.ItemID;

public class ElvenBow extends Bow {
	public ElvenBow(double level) {
		super("Elven Bow", "Fire",
				Database.bowStatsVar[Database.bowType.LONG_BOW.ordinal()][Database.bowStats.DAMAGE.ordinal()],
				Database.bowStatsVar[Database.bowType.LONG_BOW.ordinal()][Database.bowStats.ATTACK_CHANGE.ordinal()],
				Database.bowStatsVar[Database.bowType.LONG_BOW.ordinal()][Database.bowStats.CRIT_DMG_CHANGE.ordinal()],
				Database.bowStatsVar[Database.bowType.LONG_BOW.ordinal()][Database.bowStats.CRIT_RATE_CHANGE.ordinal()]);
		createItemID();
	}


	@Override
	public String toString() {
		String returnStr = new String("Item Type: Bow" + "\nName: " + this.itemName + "\nDamage: " + this.damage + "\nElement: " + this.element
				+ "\nAttack Stat Change: " + this.atkChange + "\nCrit Damage Change: " + this.critDamageChange 
				+ "\nWeight: " + this.weight + "lbs" + "\nPrice: " + this.price + " gold");
		if (this.isEquip() == true) {
			returnStr += "\n(This item is equiped)";
		}
		return returnStr;
	}

	// create unique item id for when needed in code
	@Override
	protected void createItemID() {
		ItemID itemID = new ItemID("Equipment", "Weapon", "Bow");
		this.itemID = itemID;
	}

	// changes stats of entity
	@Override
	public void equipStatChanges(Entity entity) {
		entity.setOrigATK(entity.getOrigATK() + this.atkChange);
		entity.setOrigCritDmg(entity.getOrigCritDmg() + this.critDamageChange);
		entity.setOrigCritRate(entity.getOrigCritRate() + this.critRateChange);


		entity.setCurrentAtk(entity.getCurrentAtk() + this.atkChange);
		entity.setCurrentCritDmg(entity.getCurrentCritDmg() + this.critDamageChange);
		entity.setCurrentCritRate(entity.getCurrentCritRate() + this.critRateChange);

	}

	// remove stat changes from enemy
	@Override
	public void removeStatChanges(Entity entity) {
		entity.setOrigATK(entity.getOrigATK() - this.atkChange);
		entity.setOrigCritDmg(entity.getOrigCritDmg() - this.critDamageChange);
		entity.setOrigCritRate(entity.getOrigCritRate() - this.critRateChange);

		entity.setCurrentAtk(entity.getCurrentAtk() - this.atkChange);
		entity.setCurrentCritDmg(entity.getCurrentCritDmg() - this.critDamageChange);
		entity.setCurrentCritRate(entity.getCurrentCritRate() - this.critRateChange);
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
