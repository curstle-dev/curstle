package de.wgkassel.curstle.items;

import de.wgkassel.curstle.RoomContent.SilentObjects;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;

import java.util.HashMap;


public abstract class BaseItem extends SilentObjects {

    protected ItemState state = ItemState.ROOM;

    @Override
    public final void act() {
        super.act();
        collect();
        // levelUP();
    }

    /* public void levelUP() {
        if (state == ItemState.INVENTORY && isTouching(Player.class)) {
            state = ItemState.USED;
            Player.lives++;
            getWorld().removeObject(this);
        }
    } */

    public void collect() {

        if (isTouching(Player.class)) {
            state = ItemState.INVENTORY;
            ((BaseWorld) getWorld()).getPlayer().getItemsCollection().add(this);
            HashMap<Class<? extends BaseWorld>, HashMap<Class<? extends BaseItem>, Integer>> itemMap = ((BaseWorld) getWorld()).getPlayer().getItemMap();
            HashMap<Class<? extends BaseItem>, Integer> roomMap = itemMap.get(((BaseWorld) getWorld()).getClass());
            roomMap.put(this.getClass(), roomMap.get(this.getClass()) - 1);
            itemMap.put(((BaseWorld) getWorld()).getClass(), roomMap);
            getWorld().removeObject(this);
            Player.lives++;
        }
    }

    enum ItemState {
        ROOM, INVENTORY, USED;
    }

}
