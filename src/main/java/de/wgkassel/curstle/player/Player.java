package de.wgkassel.curstle.player;

import de.wgkassel.curstle.Worlds.Screens.Endscreen;
import de.wgkassel.curstle.RoomContent.DecoKnight;
import de.wgkassel.curstle.RoomContent.Shelf;
import de.wgkassel.curstle.RoomContent.Wall;
import de.wgkassel.curstle.Worlds.Screens.VictoryScreen;
import de.wgkassel.curstle.Worlds.Level1.*;
import de.wgkassel.curstle.Worlds.Level2.*;
import de.wgkassel.curstle.enemy.*;
import de.wgkassel.curstle.items.*;
import greenfoot.Actor;
import greenfoot.Greenfoot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Player extends Actor {

    public static final double DEFAULT_MULTIPLIER = 0.001;

    //prevent the enemies from getting more than one dmg per hit
    private boolean allowHit = true;
    private boolean allowHitBoss = true;
    private boolean allowHitBossShots = true;
    private boolean allowHitEndBoss = true;
    private boolean allowHitEndBossShots = true;
    private boolean allowHitMegaWeapon = true;
    private boolean allowHitBeeBullet;
    private boolean allowHitBee;

    public static int lives = 10;
    private double multiplier = DEFAULT_MULTIPLIER;
    private int mac /*multiplier acceleration count*/ = 0;
    private int mbc /*multiplier braking count*/ = 0;
    private int d = 7;//diagonal walking speed
    private int s = 7;//straight walking speed
    private Direction direction = null;
    private boolean moving;

    private boolean upPressed;
    private boolean leftPressed;
    private boolean downPressed;
    private boolean rightPressed;
    private int wallSize = 145;
    private int lastX;
    private int lastY;

    private HashMap<Class<? extends BaseWorld>, HashMap<Class<? extends BaseEnemy>, Integer>> levelmap = new HashMap<>();
    private HashMap<Class<? extends BaseWorld>, HashMap<Class<? extends BaseItem>, Integer>> itemsMap = new HashMap<>();


    public Player() {
        initEnemyMap(RoomOne.class, Bug.class, 4);
        initEnemyMap(RoomOne.class, Enemy2.class, 4);


        initEnemyMap(RoomTwo.class, Bug.class, 4);
        initEnemyMap(RoomTwo.class, Enemy2.class, 4);

        initEnemyMap(RoomThree.class, Bug.class, 4);
        initEnemyMap(RoomThree.class, Enemy2.class, 4);

        initItemMap(RoomOne.class, PotionRed.class, 1);
        initEnemyMap(RoomOne2.class, Bug.class, 2);
        initEnemyMap(RoomOne2.class, Enemy2.class, 2);

        initEnemyMap(RoomTwo2.class, Bug.class, 1);
        initEnemyMap(RoomTwo2.class, Enemy2.class, 3);

        initEnemyMap(RoomThree2.class, Bug.class, 0);
        initEnemyMap(RoomThree2.class, Enemy2.class, 4);

        initEnemyMap(RoomFour2.class, Bug.class, 2);
        initEnemyMap(RoomFour2.class, Enemy2.class, 2);

        initEnemyMap(RoomFive2.class, Bug.class, 3);
        initEnemyMap(RoomFive2.class, Enemy2.class, 1);

        initEnemyMap(RoomSix2.class, Bug.class, 3);
        initEnemyMap(RoomSix2.class, Enemy2.class, 3);

        initItemMap(RoomOne.class, PotionRed.class, 1);
        initItemMap(RoomBoss.class, PotionGreen.class, 1);
        initItemMap(RoomBoss.class, PotionBlue.class, 1);
        initItemMap(RoomThree.class, PotionPurple.class, 1);

        initItemMap(RoomOne2.class, PotionRed.class, 1);
        initItemMap(RoomTwo2.class, PotionBlue.class, 1);
        initItemMap(RoomTwo2.class, PotionGreen.class, 1);
        initItemMap(RoomThree2.class, PotionBlue.class, 1);
        initItemMap(RoomFour2.class, PotionPurple.class, 1);
        initItemMap(RoomFive2.class, PotionBlue.class, 1);
        initItemMap(RoomFive2.class, PotionRed.class, 1);
        initItemMap(RoomSix2.class, PotionGreen.class, 1);
        initItemMap(RoomBoss2.class, PotionPurple.class, 1);
        initItemMap(RoomBoss2.class, PotionBlue.class, 1);
        initItemMap(RoomBoss2.class, PotionGreen.class, 1);

    }

    private void initItemMap(Class<? extends BaseWorld> worldClass, Class<? extends BaseItem> itemClass, int i) {

        HashMap<Class<? extends BaseItem>, Integer> roomMap = itemsMap.get(worldClass);
        if (roomMap == null) {
            roomMap = new HashMap<>();
        }
        roomMap.put(itemClass, i);
        itemsMap.put(worldClass, roomMap);
    }

    void initEnemyMap(Class<? extends BaseWorld> worldClass, Class<? extends BaseEnemy> enemyClass, Integer mainEnemyAmount) {
        HashMap<Class<? extends BaseEnemy>, Integer> roomMap = levelmap.get(worldClass);
        if (roomMap == null) {
            roomMap = new HashMap<>();
        }
        roomMap.put(enemyClass, mainEnemyAmount);
        levelmap.put(worldClass, roomMap);
    }

    public HashMap<Class<? extends BaseWorld>, HashMap<Class<? extends BaseEnemy>, Integer>> getLevelmap() {
        return levelmap;
    }

    private ArrayList<BaseItem> itemsCollection = new ArrayList<>();

    public ArrayList<BaseItem> getItemsCollection() {
        return itemsCollection;
    }

    public HashMap<Class<? extends BaseWorld>, HashMap<Class<? extends BaseItem>, Integer>> getItemMap() {
        return itemsMap;
    }

    @Override
    public final void act() {
        super.act();
        upPressed = Greenfoot.isKeyDown("w");
        leftPressed = Greenfoot.isKeyDown("a");
        downPressed = Greenfoot.isKeyDown("s");
        rightPressed = Greenfoot.isKeyDown("d");

        checkHits();

        if (canMove()) {
            if (!isTouching(Shelf.class) && !isTouching(DecoKnight.class)) {
                lastX = getX();
                lastY = getY();
            }
            knights();
            shelves();
            accelMove();
            brakeMove();
            walk();
        }
        doAct();
        cheat();
    }

    /**
     * uses combines all checkHit Methods
     */
    public void checkHits() {
        checkHitBossShots();
        checkHitEnemy();
        checkHitBoss();
        checkHitEndBossShots();
        checkHitMegaWeapon();
        checkHitBee();
        checkHitBeeBullet();
        //checkHitEndBoss();
    }

    private void accelMove() {
        mac++;
        if (mac >= 7 && multiplier <= 1 && (upPressed || downPressed || leftPressed || rightPressed)) {
            multiplier = multiplier + 0.33;
            mac = 0;
        }
        multiplier = 1;
    }

    public abstract void doAct();

    /**
     * decreases lives
     */
    public void decreaseHealth() {
        lives = lives - 1;
        if (lives <= 0) {
            die();
        }
    }

    public void cheat() {
        if (Greenfoot.isKeyDown("q")) {
            lives = 10;
        }
    }


    /**
     * PLayer dies
     */
    public void die() {
        Endscreen endscreen = new Endscreen(((BaseWorld) getWorld()).getPlayer());
        Greenfoot.setWorld(endscreen);
    }


    /**
     * check Hits for any kind of damage
     */
    public void checkHitEnemy() {
        if (!getIntersectingObjects(MainEnemy.class).isEmpty() && allowHit) {
            decreaseHealth();
            allowHit = false;
        } else if (getIntersectingObjects(MainEnemy.class).isEmpty()) {
            allowHit = true;
        }
    }

    public void checkHitBoss() {
        if (!getIntersectingObjects(Boss.class).isEmpty() && allowHitBoss) {
            decreaseHealth();
            allowHitBoss = false;
        } else if (getIntersectingObjects(Boss.class).isEmpty()) {
            allowHitBoss = true;
        }
    }

    public void checkHitBossShots() {
        List<WeaponOfTheBoss> weaponList = getIntersectingObjects(WeaponOfTheBoss.class);
        if (!weaponList.isEmpty() && allowHitBossShots) {
            decreaseHealth();
            weaponList.forEach(w -> getWorld().removeObject(w));
            allowHitBossShots = false;

        } else if (weaponList.isEmpty()) {
            allowHitBossShots = true;
        }
    }

    public void checkHitEndBoss() {
        if (!getIntersectingObjects(Endboss.class).isEmpty() && allowHitEndBoss) {
            decreaseHealth();
            allowHitEndBoss = false;
        } else if (getIntersectingObjects(Endboss.class).isEmpty()) {
            allowHitEndBoss = true;
        }
    }

    public void checkHitEndBossShots() {
        List<EndbossWeapon> weaponList = getIntersectingObjects(EndbossWeapon.class);
        if (!weaponList.isEmpty() && allowHitEndBossShots) {
            decreaseHealth();
            weaponList.forEach(w -> getWorld().removeObject(w));
            allowHitEndBossShots = false;

        } else if (weaponList.isEmpty()) {
            allowHitEndBossShots = true;
        }
    }

    public void checkHitMegaWeapon() {
        List<MegaWeapon> weaponList = getIntersectingObjects(MegaWeapon.class);
        if (!weaponList.isEmpty() && allowHitMegaWeapon) {
            decreaseHealth();
            decreaseHealth();
            weaponList.forEach(w -> getWorld().removeObject(w));
            allowHitMegaWeapon = false;

        } else if (weaponList.isEmpty()) {
            allowHitMegaWeapon = true;
        }
    }

    public void checkHitBee() {
        if (!getIntersectingObjects(Enemy2.class).isEmpty() && allowHitBee) {
            decreaseHealth();
            allowHitBee = false;
        } else if (getIntersectingObjects(Enemy2.class).isEmpty()) {
            allowHitBee = true;
        }
    }

    public void checkHitBeeBullet() {
        List<BeeBullet> weaponList = getIntersectingObjects(BeeBullet.class);
        if (!weaponList.isEmpty() && allowHitBeeBullet) {
            decreaseHealth();
            weaponList.forEach(w -> getWorld().removeObject(w));
            allowHitBeeBullet = false;

        } else if (weaponList.isEmpty()) {
            allowHitBeeBullet = true;
        }
    }

    /**
     * thats all
     */


    public int getWalkingSpeed(int actual) {
        //Berechnet die Geschwindigkeit aus der jeweiligen Maximalgeschwindigkeit. Dies sorgt für eine gewisse Trägheit beim Bewegen des Charakters.
        return (int) (actual * multiplier);
    }

    private void walk() {
        int x = getX();
        int y = getY();

        if (upPressed && !downPressed) {
            if (leftPressed) {
                setLocation(x - getWalkingSpeed(d), y - getWalkingSpeed(d)); //Moving left and up
                moving = true;
                direction = Direction.getCurrent();
                return;
            } else if (rightPressed) {
                setLocation(x + getWalkingSpeed(d), y - getWalkingSpeed(d)); //Moving right and up
                moving = true;
                direction = Direction.getCurrent();
                return;
            } else {
                setLocation(x, y - getWalkingSpeed(s)); //Moving up
                moving = true;
                direction = Direction.getCurrent();
                return;
            }
        } else if (downPressed && !upPressed) {
            if (leftPressed) {
                setLocation(x - getWalkingSpeed(d), y + getWalkingSpeed(d)); //Moving left and down
                moving = true;
                direction = Direction.getCurrent();
                return;
            } else if (rightPressed) {
                setLocation(x + getWalkingSpeed(d), y + getWalkingSpeed(d)); //Moving right and down
                moving = true;
                direction = Direction.getCurrent();
                return;
            } else {
                setLocation(x, y + getWalkingSpeed(s)); //Moving down
                moving = true;
                direction = Direction.getCurrent();
                return;
            }
        }


        if (rightPressed && !leftPressed) {
            setLocation(x + getWalkingSpeed(s), y); //Moving right
            moving = true;
            direction = Direction.getCurrent();
        } else if (leftPressed && !rightPressed) {
            setLocation(x - getWalkingSpeed(s), y); //Moving left
            moving = true;
            direction = Direction.getCurrent();
        }

    }

    public void brakeMove() {
        if (!(upPressed || downPressed || leftPressed || rightPressed)) {

            if (moving) {

                if (mbc >= 5) {
                    moving = false;
                    mbc = 0;
                }
                mbc++;
                if (multiplier < DEFAULT_MULTIPLIER) {
                    multiplier = DEFAULT_MULTIPLIER;
                }
                multiplier = multiplier - DEFAULT_MULTIPLIER;
                if (direction == Direction.UP && getY() > wallSize) {
                    setLocation(getX(), getY() - getWalkingSpeed(s));
                    return;
                }
                if (direction == Direction.DOWN && getY() < BaseWorld.HEIGHT - wallSize) {
                    setLocation(getX(), getY() + getWalkingSpeed(s));
                    return;
                }
                if (direction == Direction.LEFT && getX() > wallSize) {
                    setLocation(getX() - getWalkingSpeed(s), getY());
                    return;
                }
                if (direction == Direction.RIGHT && getX() < BaseWorld.WIDTH - wallSize) {
                    setLocation(getX() + getWalkingSpeed(s), getY());
                    return;
                }

                if (direction == Direction.LEFT_UP && getY() > wallSize && getX() > wallSize) {
                    setLocation(getX() - getWalkingSpeed(d), getY() - getWalkingSpeed(d));
                    return;

                }

                if (direction == Direction.LEFT_DOWN && getY() < wallSize && getX() > wallSize) {
                    setLocation(getX() - getWalkingSpeed(d), getY() + getWalkingSpeed(d));
                    return;

                }

                if (direction == Direction.RIGHT_UP && getY() > wallSize && getX() < wallSize) {
                    setLocation(getX() + getWalkingSpeed(d), getY() - getWalkingSpeed(d));
                    return;

                }

                if (direction == Direction.RIGHT_DOWN && getY() < wallSize && getX() < wallSize) {
                    setLocation(getX() + getWalkingSpeed(d), getY() + getWalkingSpeed(d));
                }
            }

        }
    }

    public boolean canMove() {
        if (getWorld() instanceof VictoryScreen || getWorld() instanceof Endscreen) {
            return false;
        }
        int walkingSpeed = getWalkingSpeed(4);
        int radius = (int) Math.ceil(wallSize + walkingSpeed);
        List<Wall> walls = getObjectsInRange(radius, Wall.class);
        for (Wall wall : walls) {
            int px = getX();
            int py = getY();
            int wx = wall.getX();
            int wy = wall.getY();

            double dx = px - wx;
            double dy = py - wy;

            // System.out.println("px = " + px + ", py = " + py);

            if ((py - walkingSpeed < wallSize && upPressed) && !(Math.abs(dx) > wallSize)) {
                // wall top
                // System.out.println("HOCH");
                return false;
            } else if ((py + walkingSpeed > BaseWorld.HEIGHT - (wallSize) && downPressed) && !(Math.abs(dx) > wallSize)) {
                // wall bottom
                // System.out.println("RUNTER");
                return false;
            } else if ((px - walkingSpeed < wallSize && leftPressed) && !(Math.abs(dy) > wallSize)) {
                // wall left
                // System.out.println("LINKS");
                return false;
            } else if ((px + walkingSpeed > BaseWorld.WIDTH - wallSize && rightPressed) && !(Math.abs(dy) > wallSize)) {
                // wall right
                // System.out.println("RECHTS");
                return false;
            }

        }
        return true;
    }

    public void shelves() {
        if (isTouching(Shelf.class)) {
            this.setLocation(lastX, lastY);
            return;
        }
    }

    public void knights() {
        if (isTouching(DecoKnight.class)) {
            this.setLocation(lastX, lastY);
            return;
        }
    }


}




