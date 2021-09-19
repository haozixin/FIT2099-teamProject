package game.actors;


import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.weapons.PlayerIntrinsicWeapon;

import java.util.ArrayList;

/**
 * An undead minion.
 */
public class Undead extends Actor {
	int souls;


	// Will need to change this to a collection if Undeads gets additional Behaviours.
	private ArrayList<Behaviour> behaviours = new ArrayList<>();

	/** 
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 * @param name the name of this Undead
	 */
	public Undead(String name) {
		super(name, 'u', 50);
		behaviours.add(new WanderBehaviour());
		setSouls(50);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.FOLLOWED);
	}

	public void setSouls(int souls) {
		this.souls = souls;
	}

	/**
	 * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
	 * You can do something else with this method.
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();

		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this,direction));
		}
		return actions;
	}

	/**
	 * Figure out what to do next.
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// loop through all behaviours

		for(Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}


	@Override
	public void hurt(int points) {
		hitPoints -= points;
		hitPoints = Math.max(hitPoints, 0);
	}

	@Override
	public String toString() {
		return name+" ("+hitPoints+"/"+maxHitPoints+")"+" (no weapons)";
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new PlayerIntrinsicWeapon(20, "punches","fist");

	}

}
