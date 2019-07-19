package de.wgkassel.curstle.enemy;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;

import java.util.List;

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
        handleStateUpdates();
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

    private void handleStateUpdates() {
        List<Player> players = getObjectsInRange(400, Player.class);
        int nPlayers = players.size();
        if (nPlayers >= 1) {
            state = State.ATTACK;
        } else {
            state = State.WALK;
        }
    }

    public void shoot(){
        int x = 0;
        int y;
        if (System.currentTimeMillis() - shotPause > 2000) {
            for (int i = 0; i <= 4; i++) {
                if(i == 2){
                    y = 7;
                }
                else if (i == 1 || i == 3){
                    y = 7;
                }
                else {
                    y = 6;
                }
                doShoot(x,y);
                x = x + 8;

            }
        }
    }

    /**
     * shoot
     *
     * @param rotationOffset
     */
    public void doShoot(int rotationOffset, int speed) {
        ShotgunShot shotgunShot = new ShotgunShot(speed);
        this.getWorld().addObject(shotgunShot, this.getX(), this.getY());
        int rotation = this.getRotation();
        shotgunShot.setRotation(rotation - 16 + rotationOffset);
        shotPause = System.currentTimeMillis();
    }
}
