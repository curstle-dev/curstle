package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.RoomContent.Wall;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import greenfoot.Actor;
import greenfoot.Greenfoot;

import java.util.HashMap;

public abstract class BaseEnemy extends Actor {

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

    public void walkRandomly() {
        move(3);

        if (Greenfoot.getRandomNumber(100) > 96) {
            int random = Greenfoot.getRandomNumber(360);
            turn(random);
        }
        if (isTouching(Wall.class)) {
            turn(180);
            move(10);
        }
        if (isTouching(Shelf.class)) {
            turn(180);
            move(10);
        }
    }
public void lowerHealth(){}

}
