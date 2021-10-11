package game.actors;

import edu.monash.fit2099.engine.*;
import game.actions.AttackAction;
import game.actions.BurnAction;
import game.enums.Abilities;
import game.enums.Status;
import game.items.CindersOfLord;
import game.weapons.MeleeWeapon;
import game.weapons.YhormGreatMachete;

/**
 * The boss of Design o' Souls
 */
public class YhormTheGiant extends LordOfCinder {
    public static final int SOULS = 5000;
    private int threshold = 250;
    private boolean stunned = false;

    /**
     * Constructor.
     */
    public YhormTheGiant() {
        super("Yhorm the Giant", 'Y', 500, Status.FROM_YHORM);
        this.addCapability(Abilities.EMBER_FORM);
        bossWeapon = new YhormGreatMachete();
    }



    @Override
    public void hurt(int points) {
        super.hurt(points);
        if (hitPoints<=threshold) {
            ((YhormGreatMachete)bossWeapon).emberForm();
        }
    }

    /**
     *
     * @return SOULS
     */
    public static int getSOULS() {
        return SOULS;
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(this.stunned) {
            this.stunned = false;
            return new DoNothingAction();
        }
        if (this.isConscious() && this.hasCapability(Abilities.EMBER_FORM)) {

        }
        return new DoNothingAction();
    }


    public void setStunned(boolean stunned) {
        this.stunned = stunned;
    }
}
