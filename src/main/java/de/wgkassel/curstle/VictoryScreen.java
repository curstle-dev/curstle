package de.wgkassel.curstle;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;
import greenfoot.GreenfootSound;


public class VictoryScreen extends BaseWorld {

    private int jumpCounter = 0;
    private boolean uped = false;

    public VictoryScreen(Player player) {
        super(player);
        prepare();
    }

    @Override
    public void act() {
        super.act();
        playerJump();
        jumpCounter++;
    }

    public void prepare() {
        RestartButton restartButton = new RestartButton();
        addObject(restartButton, getWidth() / 2, getHeight() / 2);

        VictoryText victoryText = new VictoryText();
        addObject(victoryText, getWidth() / 2, getHeight() / 4);

        addObject(player, getWidth() / 2, getHeight() / 7 * 6);

        Sylvester sylvester = new Sylvester();
        addObject(sylvester, 0, getHeight() / 4);


        Music.getMoi().stopped();
    }

    public void playerJump() {
        if (!uped) {
            if (jumpCounter <= 1) {
                player.setLocation(player.getX(), player.getY() - 1);
            }
            if (jumpCounter <= 2) {
                player.setLocation(player.getX(), player.getY() - 1);
            }
            if (jumpCounter <= 4) {
                player.setLocation(player.getX(), player.getY() - 1);
            }
            if (jumpCounter <= 8) {
                player.setLocation(player.getX(), player.getY() - 1);
            }
            if (jumpCounter <= 16) {
                player.setLocation(player.getX(), player.getY() - 1);
            }
            if (jumpCounter <= 32) {
                player.setLocation(player.getX(), player.getY() - 1);
            }
            if (jumpCounter <= 64) {
                player.setLocation(player.getX(), player.getY() - 1);
            }
            if (jumpCounter <= 80) {
                player.setLocation(player.getX(), player.getY() - 1);
            }
            if (jumpCounter >= 81) {
                uped = true;
                jumpCounter = 0;
            }
        }

        if (uped) {
            if (jumpCounter >= 1) {
                player.setLocation(player.getX(), player.getY() + 1);
            }
            if (jumpCounter >= 2) {
                player.setLocation(player.getX(), player.getY() + 1);
            }
            if (jumpCounter >= 4) {
                player.setLocation(player.getX(), player.getY() + 1);
            }
            if (jumpCounter >= 8) {
                player.setLocation(player.getX(), player.getY() + 1);
            }
            if (jumpCounter >= 16) {
                player.setLocation(player.getX(), player.getY() + 1);
            }
            if (jumpCounter >= 45) {
                player.setLocation(player.getX(), player.getY() + 1);
            }
            if (jumpCounter >= 46) {
                uped = false;
                jumpCounter = 0;
                player.setLocation(getWidth() / 2, getHeight() / 7 * 6);
            }
        }
    }
}