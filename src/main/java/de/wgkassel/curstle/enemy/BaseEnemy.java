package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import greenfoot.Actor;

import java.util.HashMap;

public class BaseEnemy extends Actor {

    public void die() {
        HashMap<Class<? extends BaseWorld>, HashMap<Class<? extends BaseEnemy>, Integer>> levelmap = ((BaseWorld) getWorld()).getPlayer().getLevelmap();
        Class<? extends BaseWorld> worldClass = ((BaseWorld) getWorld()).getClass();
        HashMap<Class<? extends BaseEnemy>, Integer> enemyTypeMap = levelmap.get(worldClass);

        Integer integer = enemyTypeMap.get(getClass());
        if (integer == null) {
            integer = 1;
        }
        enemyTypeMap.put(getClass(), integer - 1);
        levelmap.put(worldClass, enemyTypeMap);
        getWorld().removeObject(this);

    }
}
