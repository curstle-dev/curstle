package de.wgkassel.curstle.Worlds.EndScreen;

import de.wgkassel.curstle.Worlds.StartScreen.StartScreen;
import de.wgkassel.curstle.enemy.Boss1.Boss;
import de.wgkassel.curstle.enemy.Boss2.Boss2;
import de.wgkassel.curstle.enemy.Endboss.Endboss;
import de.wgkassel.curstle.gates.BaseGate;
import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class RestartButton extends Actor {
    public RestartButton() {
        GreenfootImage restart = new GreenfootImage(400, 90);
        restart.drawImage(new GreenfootImage("restartbutton.png"), 0, 0);
        setImage(restart);
    }


    @Override
    public void act() {
        super.act();
        if (Greenfoot.mouseClicked(this)) {
            StartScreen startScreen = new StartScreen();
            Greenfoot.setWorld(startScreen);
            Player.lives = 10;
            Boss.lives = 30;
            Endboss.live = 50;
            Boss2.lives = 40;
            StartScreen.shouldStart = false;
            BaseGate.twoToOne = false;
            BaseGate.bossToOne = false;
            BaseGate.RoomOne2ToStairs2_1 = false;
            BaseGate.stair1ToBoss = false;
            BaseGate.threeToOne = false;
            BaseGate.RoomThree2ToTwo2 = false;
            BaseGate.roomTwoCleared = false;
            BaseGate.roomThreeCleared = false;


        }
    }
}
