package game.weapons;

import edu.monash.fit2099.engine.IntrinsicWeapon;

public class PlayerIntrinsicWeapon extends IntrinsicWeapon {
    private String name;

    public PlayerIntrinsicWeapon(int damage, String verb, String name) {
        super(damage, verb);
        setName(name);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
