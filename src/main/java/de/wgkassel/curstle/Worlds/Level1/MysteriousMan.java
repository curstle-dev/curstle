package de.wgkassel.curstle.Worlds.Level1;

import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;
import greenfoot.Greenfoot;

public class MysteriousMan extends Actor {
    static boolean firstTime = true;
    private int speakCounter;
    public static boolean animation = false;
    private int countAnimation;
    long whenToTurn = System.currentTimeMillis();

    public MysteriousMan() {
        setImage("MysteriousMan.png");
        this.getImage().scale(132, 174);
    }

    @Override
    public void act() {
        animation();
        if (firstTime) {
            if (isTouching(Player.class)) {
                firstTime = false;
                StoryRoom storyRoom = new StoryRoom(((BaseWorld) getWorld()).getPlayer());
                Greenfoot.setWorld(storyRoom);
                getWorld().removeObject(this);
            }
        }
        if(StoryRoom.story >= 2) {
            walk();
        }
    }

    public void walk() {
        this.setImage("MysteriousManRight.png");
        this.getImage().scale(132,174);
        move(3);
    }

    public void animation() {
        if (animation) {
            speakCounter++;
            if (speakCounter <= 10) {
                setImage("MysteriousMan.png");
                this.getImage().scale(132, 174);
            } else {
                if (speakCounter > 20) {
                    speakCounter = 0;
                }
                setImage("MysteriousMan_open.png");
                this.getImage().scale(132, 174);
            }
            countAnimation++;
        if(countAnimation > 100)
        {
            animation = false;
            countAnimation = 0;
            speakCounter = 0;
            setImage("MysteriousMan.png");
            this.getImage().scale(132, 174);
        }
        }
    }
}
