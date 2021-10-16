package game.items;

import game.interfaces.Soul;

/**
 * The Token of Souls
 * It will be used in soft-reset part
 */
public class TokenOfSouls extends PortableItem implements Soul {
    /**
     * the number of souls the token has/represents
     */
    int souls;

    public TokenOfSouls(int souls) {
        super("SoulsToken", '$');
        //souls maybe null or some invalid value
        this.souls = souls;
    }

    public TokenOfSouls() {
        super("SoulsToken", '$');
        this.souls = 0;
    }

    /**
     * setter - set the number of souls
     *
     * @param souls souls number
     */
    public void setSouls(int souls) {
        this.souls = souls;
    }

    /**
     * Transfer current instance's souls to another Soul instance.
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {
        //the soulObject should be Play type object, then it will go to addSouls() that is implemented in Player class,
        // otherwise the function will return false
        //when player pick up the item(souls), it should transfer Souls instance to player's souls(attribute)
        soulObject.addSouls(souls);
    }

    /**
     * Increase souls to current instance's souls.
     *
     * @param souls number of souls to be incremented.
     * @return transaction status. True if addition successful, otherwise False.
     */
    @Override
    public boolean addSouls(int souls) {
        boolean isValid = false;
        if (souls > 0) {
            this.souls += souls;
            isValid = true;
        }
        return isValid;
    }

    /**
     * Allow other classes to deduct the number of this instance's souls
     *
     * @param souls number souls to be deducted
     * @return transaction status. True if subtraction successful, otherwise False.
     */
    @Override
    public boolean subtractSouls(int souls) {
        boolean isValid = false;
        if (souls > 0) {
            this.souls -= souls;
            isValid = true;
        }
        return isValid;
    }
}
