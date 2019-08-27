package de.wgkassel.curstle.Worlds.StartScreen;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.Worlds.Level1.RoomBoss;
import de.wgkassel.curstle.Worlds.Level1.StairsRoom;
import de.wgkassel.curstle.Worlds.OptionsScreen.OptionsScreen;
import de.wgkassel.curstle.Worlds.TestRoom;
import de.wgkassel.curstle.Worlds.VictoryScreen.VictoryScreen;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class OptionsButton extends Actor {

    /**
     * Just a simple button to option the game.
     */
    public OptionsButton() {
        GreenfootImage optionb = new GreenfootImage(400, 90);
        optionb.drawImage(new GreenfootImage("optionsbutton.png"), -3, 0);
        setImage(optionb);
    }

    @Override
    public void act() {
        super.act();
        generateVictoryScreen();
        generateYEET();
        makeAleynaHappyAgain();
        generateTestRoom();
    }

    public void generateVictoryScreen() {
        if (Greenfoot.isKeyDown("#")) {
            VictoryScreen vs1 = new VictoryScreen(((BaseWorld) getWorld()).getPlayer());
            Greenfoot.setWorld(vs1);
        }
    }

    public void generateYEET() {
        if (Greenfoot.mouseClicked(this)) {
            OptionsScreen optionsScreen = new OptionsScreen();
            Greenfoot.setWorld(optionsScreen);
        }
    }

    public void makeAleynaHappyAgain() {
        if (Greenfoot.isKeyDown("x")) {
            StairsRoom test = new StairsRoom(((BaseWorld) getWorld()).getPlayer());
            Greenfoot.setWorld(test);
        }

        if (Greenfoot.isKeyDown("b")) {
            RoomBoss test2 = new RoomBoss(((BaseWorld) getWorld()).getPlayer());
            Greenfoot.setWorld(test2);
        }
    }

    public void generateTestRoom() {
        if (Greenfoot.isKeyDown("t")) {
            TestRoom testRoom = new TestRoom(((BaseWorld) getWorld()).getPlayer());
            Greenfoot.setWorld(testRoom);
        }
    }
}
