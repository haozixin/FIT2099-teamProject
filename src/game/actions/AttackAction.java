package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.actors.*;
import game.enums.Abilities;
import game.interfaces.PlayerInter;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action{

	/**
	 * The Actor that is to be attacked
	 * it might be enemy or player
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Execute the Action.
	 * consider all actor might use the same attack action, so I add a judgement process
	 * which contains two cases when the target is going to die - 1,target is enemy / 2, target is player
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();


		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}


		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (!target.isConscious()) {
			Actions dropActions = new Actions();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);

			// some special cases
			// deal with corpse and some target need to be reset
			String result1 = distinguishTarget(actor, map, result);
			if (result1 != null) return result1;

			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	/**
	 * A class to distinguish Targets, because different targets need to do different following operations
	 * @param actor the actor who is going to perform the attack
	 * @param map The map the actor is on.
	 * @param result output massage that is created on executed()
	 * @return
	 */
	private String distinguishTarget(Actor actor, GameMap map, String result) {

		// if the actor who is going to perform the attack is Player
		if (actor instanceof Player){
			PlayerInter player = (Player) actor;

			if (target instanceof Enemy){
				// what will happen if Skeleton(or any other enemy who has the ability) is going to die
				if (target.hasCapability(Abilities.RESURRECT)){
					//on the ResurrectAction, it will show resurrect massage and remove skeleton's this ability
					return result;
				}else{
					// what will happen if Undead is going to die
					// what will happen if LordOfCinder is going to die
					// what will happen if AldrichTheDevourer is going to die
					// what will happen if MimicOrChest is going to die
					Enemy enemy = (Enemy) target;
					player.addSouls(enemy.getSouls());
					map.removeActor(target);
					return result;
				}
			}
		}
		// if other enemies kill the player, it will execute soft-rest functionality
		else{
		}
		return null;
	}

	/**
	 * it will shows message on console
	 * @param actor The actor performing the action.
	 * @return a String that will shows console as menu options
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}



}
