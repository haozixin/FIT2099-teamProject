package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.interfaces.PlayerInterface;
import game.items.CindersOfLord;
import game.weapons.MeleeWeapon;

public class TradeCinderAction extends Action {
    CindersOfLord cindersOfLord;

    /**
     * the weapon that would be traded
     */
    private MeleeWeapon weapon;

    public TradeCinderAction(MeleeWeapon weapon, CindersOfLord cindersOfLord) {
        this.weapon = weapon;
        this.cindersOfLord = cindersOfLord;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        //trade logic
        PlayerInterface player = (PlayerInterface) actor;
        player.removeItemFromInventory(cindersOfLord);
        player.removeItemFromInventory((Item) (player.getWeapon()));
        player.addItemToInventory(weapon);
        System.out.println("We(Vendor) have update your inventory successfully");
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor+" trades "+cindersOfLord;
    }
}
