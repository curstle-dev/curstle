package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.enemy.Endboss.EndbossWeapon;
import de.wgkassel.curstle.player.Player;

public class ShotgunMan extends BaseEnemy {
    int lives = 5;
    private long shotPause = System.currentTimeMillis();
    private State state = State.ATTACK;
    private enum State {
        WALK, ATTACK;
    }

    public ShotgunMan(){
        setImage("Knight.png");
        this.getImage().scale(91, 131);
    }

    @Override
    public void act() {
        super.act();
        switch (state) {
            case WALK:
                walkRandomly();
                break;
            case ATTACK:
                Player player = ((BaseWorld) getWorld()).getPlayer();
                int playerX = player.getX();
                int playerY = player.getY();
                turnTowards(playerX, playerY);
                shoot();
                break;
        }
    }

    public void shoot(){
        int x = 0;
        if (System.currentTimeMillis() - shotPause > 2000) {
            for (int i = 0; i <= 4; i++) {
                doShoot(x);
                x = x + 8;
            }
        }
    }

    /**
     * shoot
     *
     * @param rotationOffset
     */
    public void doShoot(int rotationOffset) {
        ShotgunShot shotgunShot = new ShotgunShot();
        this.getWorld().addObject(shotgunShot, this.getX(), this.getY());
        int rotation = this.getRotation() - 20;
        shotgunShot.setRotation(rotation + rotationOffset);
        shotPause = System.currentTimeMillis();
    }
}
