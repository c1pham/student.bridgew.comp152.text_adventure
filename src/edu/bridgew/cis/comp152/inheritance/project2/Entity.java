package edu.bridgew.cis.comp152.inheritance.project2;

import java.util.ArrayList;

import edu.bridgew.cis.comp152.inheritance.project2.ability.*;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;;


// commented
public abstract class Entity {
	// attributes of entity
	protected String name;
	protected String entityClass;
	protected char gender;
	protected double level;
	protected double weight;
	// fields related to battle
	// it has the orginal stats to reset to
	// it has current stats in case weapon or future armor does anything to it 
	protected String element;
	protected double origFireResist;
	protected double currentFireResist;
	protected double origIceResist;
	protected double currentIceResist;
	protected double origLightingResist;
	protected double currentLightingResist;
	protected double origATK;
	protected double currentAtk;
	protected double origDef;
	protected double currentDef;
	protected double origMDef;
	protected double currentMDef;
	protected double origSpeed;
	protected double currentSpeed;
	protected double origHealth;
	protected double healthWithChanges;
	protected double currentHealth;
	protected double origAccuracy;
	protected double currentAccuracy;
	protected double origCritDmg;
	protected double currentCritDmg;
	protected double origCritRate;
	protected double currentCritRate;	
	protected double staminaBar;
	protected ArrayList<Ability> abilityList;
	protected Inventory bag;
	protected Weapon weapon;
	
	public double getWeight() {
		return weight;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	// constructor
	public Entity(String name, String entityClass, char gender, double level, String element, double origFireResist,
			double origIceResist, double origLightingResist, double origATK, double origDef, double origMDef, double origSpeed,
			double origHealth, double origAccuracy, double origCritDmg, double origCritRate, Ability[] abilities, Weapon weapon) {
		this.name = name;
		this.entityClass = entityClass;
		this.gender = gender;
		this.level = level;
		this.element = element;
		this.origFireResist = origFireResist;
		this.currentFireResist = this.origFireResist;
		this.origIceResist = origIceResist;
		this.currentIceResist = this.origIceResist;
		this.origLightingResist = origLightingResist;
		this.currentLightingResist = this.origLightingResist;
		this.origATK = origATK;
		this.currentAtk = this.origATK;
		this.origDef = origDef;
		this.currentDef = this.origDef;
		this.origMDef = origMDef;
		this.currentMDef = this.origDef;
		this.origSpeed = origSpeed;
		this.currentSpeed = this.origSpeed;
		this.origHealth = origHealth;
		this.healthWithChanges = origHealth;
		this.currentHealth = origHealth;
		this.origAccuracy = origAccuracy;
		this.currentAccuracy = this.origAccuracy;
		this.origCritDmg = origCritDmg;
		this.currentCritDmg = this.origCritDmg;
		this.origCritRate = origCritRate;
		this.currentCritRate = this.origCritRate;
		this.staminaBar = 100;
		// instantiate special settings
		this.abilityList = new ArrayList<Ability>();
		this.bag = new Inventory();
		// add abilities
		if (abilities.length > 0) {
			for (int i = 0; i < abilities.length; i++) {
				abilityList.add(abilities[i]);
			}
		}
		// equip weapon
		equipWeapon(weapon);
		// add weapon to inventory
		this.bag.addItem(weapon);
	}
	
	public ArrayList<Ability> getAbilityList() {
		return abilityList;
	}

	public void setAbilityList(ArrayList<Ability> abilityList) {
		this.abilityList = abilityList;
	}
	// methods that each entity should do 
	// all entity equip item
	public abstract void equipWeapon(Weapon weapon);
	// all entity de equip item
	public abstract void deEquipWeapon();
	// all entity should level up
	public abstract void levelUp();
	// all entity should take dmg
	// this is abstract because I have some classes that take damage differently, for example
	// mecha animals can fly if they are flying they take more dmg from range attacks
	public abstract boolean takeDmg(String[] dmgSpecs, double dmgCount);
	// all entity should be able to attack
	public abstract boolean normalAttack(Entity entity, ArrayList<Enemy> enemies);
	// all entity should be able to uyse abilities
	public abstract boolean specialMove(Entity entity, ArrayList<Enemy> enemies);
	// most entity should be able to resetbattle stats
	public abstract void combatAction(Entity entity, ArrayList<Enemy> enemies);
	// reset stats after battle
	public abstract void resetBattleStats();
	// give string with entity info
	public abstract String toString();
	// print info for them in battle
	public abstract void printBattleInfo();
	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public double getLevel() {
		return level;
	}

	public void setLevel(double level) {
		this.level = level;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public double getOrigFireResist() {
		return origFireResist;
	}

	public void setOrigFireResist(double origFireResist) {
		this.origFireResist = origFireResist;

	}

	public double getCurrentFireResist() {
		return currentFireResist;
	}

	public void setCurrentFireResist(double currentFireResist) {
		this.currentFireResist = currentFireResist;
	}

	public double getOrigIceResist() {
		return origIceResist;
	}

	public void setOrigIceResist(double origIceResist) {
		this.origIceResist = origIceResist;

	}

	public double getCurrentIceResist() {
		return currentIceResist;
	}

	public void setCurrentIceResist(double currentIceResist) {
		this.currentIceResist = currentIceResist;
	}

	public double getOrigLightingResist() {
		return origLightingResist;
	}

	public void setOrigLightingResist(double origLightingResist) {
		this.origLightingResist = origLightingResist;

	}

	public double getCurrentLightingResist() {
		return currentLightingResist;
	}

	public void setCurrentLightingResist(double currentLightingResist) {
		this.currentLightingResist = currentLightingResist;
	}

	public double getOrigATK() {
		return origATK;
	}

	public void setOrigATK(double origATK) {
		this.origATK = origATK;

	}

	public double getCurrentAtk() {
		return currentAtk;
	}

	public void setCurrentAtk(double currentAtk) {
		this.currentAtk = currentAtk;
	}

	public double getOrigDef() {
		return origDef;
	}

	public void setOrigDef(double origDef) {
		this.origDef = origDef;

	}

	public double getCurrentDef() {
		return currentDef;
	}

	public void setCurrentDef(double currentDef) {
		this.currentDef = currentDef;
	}

	public double getOrigMDef() {
		return origMDef;
	}

	public void setOrigMDef(double origMDef) {
		this.origMDef = origMDef;

	}

	public double getCurrentMDef() {
		return currentMDef;
	}

	public void setCurrentMDef(double currentMDef) {
		this.currentMDef = currentMDef;
	}

	public double getOrigSpeed() {
		return origSpeed;
	}

	public void setOrigSpeed(double origSpeed) {
		this.origSpeed = origSpeed;

	}

	public double getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public double getOrigHealth() {
		return origHealth;
	}

	// set to change multiple stats that correspond to health
	public void setOrigHealth(double origHealth) {
		this.origHealth = origHealth;
	}

	public double getHealthWithChanges() {
		return healthWithChanges;
	}

	public void setHealthWithChanges(double healthWithChanges) {
		this.healthWithChanges = healthWithChanges;
	}

	public double getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(double currentHealth) {
		this.currentHealth = currentHealth;
	}

	public double getOrigAccuracy() {
		return origAccuracy;
	}

	public void setOrigAccuracy(double origAccuracy) {
		this.origAccuracy = origAccuracy;
	}

	public double getCurrentAccuracy() {
		return currentAccuracy;
	}

	public void setCurrentAccuracy(double currentAccuracy) {
		this.currentAccuracy = currentAccuracy;
	}

	public double getOrigCritDmg() {
		return origCritDmg;
	}

	public void setOrigCritDmg(double origCritDmg) {
		this.origCritDmg = origCritDmg;
	}

	public double getCurrentCritDmg() {
		return currentCritDmg;
	}

	public void setCurrentCritDmg(double currentCritDmg) {
		this.currentCritDmg = currentCritDmg;
	}

	public double getOrigCritRate() {
		return origCritRate;
	}

	public void setOrigCritRate(double origCritRate) {
		this.origCritRate = origCritRate;
	}

	public double getCurrentCritRate() {
		return currentCritRate;
	}

	public void setCurrentCritRate(double currentCritRate) {
		this.currentCritRate = currentCritRate;
	}

	public double getStaminaBar() {
		return staminaBar;
	}

	public void setStaminaBar(double staminaBar) {
		this.staminaBar = staminaBar;
	}

	public Inventory getBag() {
		return bag;
	}

	public void setBag(Inventory bag) {
		this.bag = bag;
	}

	// all entity should use inventory
	protected abstract boolean useInventory(Entity entity ,ArrayList<Enemy> enemies, boolean condition);
	
}
