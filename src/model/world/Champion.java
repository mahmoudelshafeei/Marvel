package model.world;

import java.awt.Point;
import java.util.ArrayList;

import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.effects.Effect;

public abstract class Champion implements Comparable, Damageable{

	private String name;
	private int mana;
	private int attackRange;
	private int attackDamage;
	private int speed;
	private int maxHP;
	private int currentHP;
	private ArrayList<Ability> abilities;
	private ArrayList<Effect> appliedEffects;
	private Point location;
	private Condition condition;
	private int maxActionPointsPerTurn;
	private int currentActionPoints;

	public String ToString()
	{
		String s="Name: " + name + "\n";
		if(this instanceof Hero)
			s+= "Type: Hero";
		if(this instanceof Villain)
			s+="Type: Villain";
		if( this instanceof AntiHero)
			s+="Type: AntiHero";
		s +=   "\n" + "Speed: " + speed +"\n" + "Attack Damage: " + attackDamage +"\n" 
				+ "Attack Range: " + attackRange + "\n" + "Current HP: "+ currentHP + "\n" + "Mana: " + mana
				+ "\n" + "MAP: " + maxActionPointsPerTurn + "\n" + "CAP: " + currentActionPoints +"\n";
		return s;
	}
	public String toString()
	{
		String s="";
		for(int i=0 ; i< abilities.size() ; i++)
		{
			int x= i+1;
			s+= "Ability" + x + ": "+ abilities.get(i).getName() + "\n";
		}
		return s;
	}
	public String TheAbilities()
	{
		String s="";
		int i=1;
		for(int k=0 ; k<abilities.size(); k++)
		{
			Ability a= abilities.get(k);
			s+=  "Ability "+ i +": "+ "\n________\n"  + a.getName() + "\n" + "Cast Range: " +a.getCastRange() + "\n" 
					+ "CCD: "+ a.getCurrentCooldown() +"\n"+ "BCD: "+ a.getBaseCooldown() +"\n" +
					"Mana Cost: "+a.getManaCost() +
					"\n"+"Required Points: "+a.getRequiredActionPoints() +"\n" +"Cast Area: " +  a.getCastArea()
					+"\n" ;
			if(!(a instanceof CrowdControlAbility))
				s+="\n";
			else
				s+= "Effect: "+ ((CrowdControlAbility) a).getEffect().getName() +"\n" +"\n";
			
			i++;
		}
		return s;
	}
	public String TheEffects(){
		String s="";
		int i =1;
		for(Effect e : appliedEffects)
		{
			s+="Effect" +i+ ": " + "\n________\n"+ "Name: " + e.getName() +"\n" + "Duration: " + e.getDuration()
					+ "\n" + "Type: "+ e.getType()+ "\n";
			i++;
		}
		return s;
	}
	public Champion(String name, int maxHP, int mana, int maxActionsPerTurn, int speed, int attackRange,
			int attackDamage) {
		this.name = name;
		this.mana = mana;
		this.attackRange = attackRange;
		this.attackDamage = attackDamage;
		this.speed = speed;
		this.maxHP = maxHP;
		this.maxActionPointsPerTurn = maxActionsPerTurn;
		this.currentActionPoints = maxActionsPerTurn;
		this.currentHP = maxHP;
		this.abilities = new ArrayList<Ability>();
		this.appliedEffects = new ArrayList<Effect>();
		this.condition = Condition.ACTIVE;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
		if (currentHP <= 0) {
			this.currentHP = 0;
			this.condition = Condition.KNOCKEDOUT;
		}
		else if (currentHP > maxHP)
			this.currentHP = maxHP;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condtion) {
		this.condition = condtion;
	}

	public int getMaxActionPointsPerTurn() {
		return maxActionPointsPerTurn;
	}

	public void setMaxActionPointsPerTurn(int maxActionsPerTurn) {
		this.maxActionPointsPerTurn = maxActionsPerTurn;
	}

	public String getName() {
		return name;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public ArrayList<Effect> getAppliedEffects() {
		return appliedEffects;
	}

	public int getCurrentActionPoints() {
		return currentActionPoints;
	}

	public void setCurrentActionPoints(int currentActionPoints) {
		this.currentActionPoints = currentActionPoints;
		if (currentActionPoints > maxActionPointsPerTurn)
			this.currentActionPoints = maxActionPointsPerTurn;
		if (currentActionPoints < 0)
			this.currentActionPoints = 0;
	}
	
	public abstract void useLeaderAbility(ArrayList<Champion> targets);

	public int compareTo(Object o) {
		if(o instanceof Champion) {
			Champion c = (Champion) o;
			if(this.speed != c.speed)
				return c.speed - this.speed;
			else
				return this.name.compareTo(c.name);
		}
		return 0;
	}
	

}
