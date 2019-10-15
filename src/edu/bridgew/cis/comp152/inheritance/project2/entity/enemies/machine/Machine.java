package edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.machine;

import edu.bridgew.cis.comp152.inheritance.project2.ability.Ability;
import edu.bridgew.cis.comp152.inheritance.project2.entity.enemies.Enemy;
import edu.bridgew.cis.comp152.inheritance.project2.item.equipment.Weapon;

public abstract class Machine extends Enemy {
	// attributes of machine
	protected double batteryLife;
	protected double origEnergyDmg;
	protected double currentEnergyDmg;

	//constructors
	public Machine(String name, String entityClass, double level, String element, double origFireResist,
			double origIceResist, double origLightingResist, double origATK, double origDef, double origMDef,
			double origSpeed, double origHealth, double origAccuracy, double origCritDmg,
			double origCritRate, Ability[] abilities, Weapon weapon, double energyDmg) {
		super(name, entityClass , '?', level , element, origFireResist, origIceResist,  
				origLightingResist, origATK, origDef, origMDef, origSpeed, origHealth,
				origAccuracy,origCritDmg, origCritRate, abilities, weapon);
		
		this.batteryLife = 100;
		this.origEnergyDmg = energyDmg;
		this.currentEnergyDmg = energyDmg;
	}

	// machine passive ability 
	public abstract boolean nanobotRepair();
	// getter and setter
	public double getBatteryLife() {
		return batteryLife;
	}

	public void setBatteryLife(double batteryLife) {
		this.batteryLife = batteryLife;
	}

	public double getOrigEnergyDmg() {
		return origEnergyDmg;
	}
	// setter create for origenergy dmg to also change current energy dmg
	public void setOrigEnergyDmg(double origEnergyDmg) {
		this.origEnergyDmg = origEnergyDmg;
	}

	public double getCurrentEnergyDmg() {
		return currentEnergyDmg;
	}

	public void setCurrentEnergyDmg(double currentEnergyDmg) {
		this.currentEnergyDmg = currentEnergyDmg;
	}


}
