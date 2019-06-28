package de.wgkassel.curstle.Worlds.Screens;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Knight;
import de.wgkassel.curstle.player.Mage;
import de.wgkassel.curstle.player.Player;

public class Endscreen extends BaseWorld {

    private boolean playerAlive = true;

    public Endscreen(Player player) {
        super((player instanceof Knight) ? new Knight() {
            @Override
            public void attack() {
                //noop
            }
        } : new Mage() {
            @Override
            public void attack() {
                //noop
            }
        });
        prepare();

    }

    public void prepare() {
        RestartButton restartButton = new RestartButton();
        addObject(restartButton, getWidth() / 2, getHeight() / 2);

        addObject(player, getWidth() / 2, 700);

        BloodRain bloodRain = new BloodRain();
        addObject(bloodRain, 0, 0);
    }

    @Override
    public void act() {
        super.act();
        if (playerAlive) {
            movePlayer();
        }
    }

    public void movePlayer() {
        if (player.isAtEdge()) {
            removeObject(player);
            playerAlive = false;
        } else {
            player.setLocation(player.getX(), player.getY() + 2);
        }

    }
}
