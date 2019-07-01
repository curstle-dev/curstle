package de.wgkassel.curstle;

import de.wgkassel.curstle.Worlds.Screens.StoryRoom;
import greenfoot.Actor;
import greenfoot.Greenfoot;

public class MysteriousMan extends Actor {
    public MysteriousMan() {
    }
        @Override
        public void act() {
            if (Greenfoot.mouseClicked(this)){
                StoryRoom storyRoom = new StoryRoom(1700,100,1);
                Greenfoot.setWorld(storyRoom);
            }
        }
}
