package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public abstract class Ability {

	private String name;
	private int manaCost;
	private int requiredActionPoints;
	private int castRange;
	private int baseCooldown;
	private int currentCooldown;
	private AreaOfEffect castArea;

	public Ability(String name, int manaCost, int baseCooldown, int castRange, AreaOfEffect castArea,
			int actionsRequired) {
		this.name = name;
		this.manaCost = manaCost;
		this.requiredActionPoints = actionsRequired;
		this.castRange = castRange;
		this.baseCooldown = baseCooldown;
		this.castArea = castArea;
	}

	public String toString(){
		String s="";
		
		s+= "Mana Cost: " + manaCost +"\n" +"Required Action Points: " + requiredActionPoints +"\n" +
		"Cast Range: "+ castRange+"\n" + "Base Cooldown: " + baseCooldown+"\n" +"Current Cooldown: " + currentCooldown
		+"\n" + "Cast Area: " + castArea + "/n";
		
		return s;
	}
	public int getCurrentCooldown() {
		return currentCooldown;
	}

	public void setCurrentCooldown(int currentCooldown) {
		this.currentCooldown = currentCooldown;
		if (currentCooldown > baseCooldown)
			this.currentCooldown = baseCooldown;
	}

	public String getName() {
		return name;
	}

	public int getManaCost() {
		return manaCost;
	}

	public int getRequiredActionPoints() {
		return requiredActionPoints;
	}

	public int getCastRange() {
		return castRange;
	}

	public int getBaseCooldown() {
		return baseCooldown;
	}

	public AreaOfEffect getCastArea() {
		return castArea;
	}
	
	public abstract void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException;

}
