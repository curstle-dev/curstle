package de.wgkassel.curstle.enemy.Endboss;

import de.wgkassel.curstle.RoomContent.WoodWall;
import de.wgkassel.curstle.Worlds.VictoryScreen.VictoryScreen;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;
import greenfoot.Greenfoot;

public class Endboss extends Actor {

    private long shotPause = System.currentTimeMillis();
    private long howLongCanIDoNothing = System.currentTimeMillis();
    private int attackCount = 0;
    private long lastInvisibleStart = 0L;
    boolean megaWeaponActive = true;
    public static boolean invisible = false;
    private EndbossImage image = new EndbossImage();

    private State state = State.DONOTHING;

    public static int live = 50;
    int megaShot = 0;

    public Endboss() {

        getImage().setTransparency(0);

    }

    private enum State {
        FIRE, INVISIBLE, TELEPORT, MEGAWEAPON, DONOTHING;
    }

    /**
     * switches between the states of the endboss
     */
    @Override
    public void act() {
        super.act();

        handleStateUpdates();

        switch (state) {
            case TELEPORT:
                teleport();
                break;
            case INVISIBLE:
                image.getImage().setTransparency(0);
                invisible = true;
                break;
            case FIRE:
            default:
                attack();
                break;
            case DONOTHING:
                doingNothing();
                break;
            case MEGAWEAPON:
                megaWeapon();
                break;
        }

        image.setLocation(this.getX(), this.getY());
        checkDie();


    }

    /**
     * gives the boss an image
     *
     * @return
     */
    public EndbossImage getImageActor() {
        return image;
    }

    /**
     * declare when the boss goes into another state
     */
    private void handleStateUpdates() {
        if (attackCount >= 4) {
            state = State.INVISIBLE;
            lastInvisibleStart = System.currentTimeMillis();
            attackCount = 0;
        }
        if (state == State.INVISIBLE && System.currentTimeMillis() - lastInvisibleStart > 3000) {
            state = State.TELEPORT;
        } else if (state == State.TELEPORT) {
            state = State.FIRE;
        } else if (state == State.FIRE && megaShot >= 5) {
            state = State.MEGAWEAPON;
        } else if (state == State.DONOTHING && System.currentTimeMillis() - howLongCanIDoNothing > 6000) {
            state = State.FIRE;
            megaShot = 0;
        }
        if (!megaWeaponActive) {
            state = State.DONOTHING;
            howLongCanIDoNothing = System.currentTimeMillis();
            megaWeaponActive = true;
        }
    }

    /**
     * teleports the Endboss to a random place
     */
    public void teleport() {
        image.getImage().setTransparency(255);
        invisible = false;
        setLocation(getRandomX(), getRandomY());

        megaShot++;
    }

    /**
     * creates a random X coordinate thats not in the player or near him
     *
     * @return
     */
    private int getRandomX() {
        Player player = ((BaseWorld) getWorld()).getPlayer();
        int playerX = player.getX();
        int x;
        do {
            x = Greenfoot.getRandomNumber(getWorld().getWidth() - (170 * 2)) + 170;
        } while (x > playerX - 100 && x < playerX + 100);
        return x;
    }

    /**
     * creates a random Y coordinate thats not on the player or near him
     *
     * @return
     */
    private int getRandomY() {
        Player player = ((BaseWorld) getWorld()).getPlayer();
        int playerY = player.getY();
        int y;
        do {
            y = Greenfoot.getRandomNumber(getWorld().getHeight() - (170 * 2)) + 170;
        } while (y > playerY - 100 && y < playerY + 100);
        return y;
    }


    /**
     * generate weapons in a circle around the endboss
     */
    public void attack() {
        int x = 0;
        if (System.currentTimeMillis() - shotPause > 500) {
            for (int i = 0; i <= 15; i++) {
                doShoot(x);
                x = x + 24;
            }
            attackCount++;
        }
    }

    /**
     * shoot
     *
     * @param rotationOffset
     */
    public void doShoot(int rotationOffset) {
        EndbossWeapon endbossWeapon = new EndbossWeapon();
        this.getWorld().addObject(endbossWeapon, this.getX() + 90, this.getY() + 10);
        int rotation = endbossWeapon.getRotation();
        endbossWeapon.setRotation(rotation + rotationOffset);
        shotPause = System.currentTimeMillis();
    }


    /**
     * lets the endboss die if his life is below 0
     */
    public void checkDie() {

        if (live <= 0) {
            VictoryScreen victoryScreen = new VictoryScreen(((BaseWorld) getWorld()).getPlayer());
            Greenfoot.setWorld(victoryScreen);
            getWorld().removeObject(image);
            getWorld().removeObject(this);
        }
    }

    /**
     * shoot a mega weapon in the direction of the player
     */
    public void megaWeapon() {
        if (megaWeaponActive) {
            MegaWeapon megaWeapon = new MegaWeapon();
            this.getWorld().addObject(megaWeapon, this.getX(), this.getY());
            megaWeaponActive = false;
        }
    }

    /**
     * walks randomly around
     */
    public void doingNothing() {
        move(3);
        if (Greenfoot.getRandomNumber(100) > 96) {
            int random = Greenfoot.getRandomNumber(360);
            turn(random);
        }
        if (isTouching(WoodWall.class)) {
            turn(180);
        }
    }
}
