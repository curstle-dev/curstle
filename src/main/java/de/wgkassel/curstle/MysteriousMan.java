package de.wgkassel.curstle;

import de.wgkassel.curstle.player.Player;
import greenfoot.Actor;

public class MysteriousMan extends Actor {
    public MysteriousMan() {
        setImage("MysteriousMan.png");
        this.getImage().scale(132, 174);
    }
        @Override
        public void act() {
            if (isTouching(Player.class)){
                //StoryRoom storyRoom = new StoryRoom(((BaseWorld) getWorld()).getPlayer());
                //Greenfoot.setWorld(storyRoom);
            }
        }

}
