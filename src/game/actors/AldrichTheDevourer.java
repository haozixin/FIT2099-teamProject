package game.actors;

import edu.monash.fit2099.engine.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.interfaces.Resettable;
import game.items.CindersOfLord;
import game.weapons.DarkmoonLongbow;
import game.weapons.MeleeWeapon;

public class AldrichTheDevourer extends LordOfCinder implements Resettable {

    /**
     * SOULS - how many souls the skeleton could yield when it's killed / how many souls the player could get
     * from the skeleton after killing it
     */
    public static final int SOULS = 5000;
    private CindersOfLord cindersOfLord;

    /**
     * Constructor.
     */
    public AldrichTheDevourer(Actor target) {
        super("Aldrich the Devourer", 'A', 350,target);
        this.bossWeapon = new DarkmoonLongbow();
        cindersOfLord = new CindersOfLord();
        this.addItemToInventory(cindersOfLord);
    }


    @Override
    public void resetInstance() {

    }

    @Override
    public boolean isExist() {
        return false;
    }

    /**
     * override toString to show some basic information for each Undead, such as hitPoints, weapon that the skeleton holds and so on
     * @return
     */
    @Override
    public String toString() {
        return name+" ("+hitPoints+"/"+maxHitPoints+")"+" (holding "+bossWeapon+")";
    }

    public static int getSOULS() {
        return SOULS;
    }
}
