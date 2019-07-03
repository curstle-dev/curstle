package de.wgkassel.curstle.Worlds.Screens;

import de.wgkassel.curstle.MysteriousMan;
import de.wgkassel.curstle.Speechbubble;
import de.wgkassel.curstle.Story;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;
import greenfoot.Greenfoot;

public class StoryRoom extends BaseWorld {

    int story = 1;
    private ChangeCharacter cc;

    public StoryRoom(Player player) {
        super(player);
        prepare();
    }

    public void prepare() {
        addObject(player, getWidth() / 2, getHeight() / 10 * 9);

        MysteriousMan mysteriousMan = new MysteriousMan();
        addObject(mysteriousMan, 500, 500);

        Speechbubble speechbubble = new Speechbubble();
        addObject(speechbubble, 800, 500);

        cc = new ChangeCharacter();
        addObject(cc, 1000, 500);

    }


    public void act() {
        if (Greenfoot.mouseClicked(cc)) {
            story++;
            Story.removeMe = story - 1;
        }


        if (story == 1) {
            Story story = new Story(1);
            addObject(story, 800, 500);
        }


        if (story == 2) {
            Story story = new Story(2);
            addObject(story, 800, 500);
        }
        if (story == 3) {
            Story story = new Story(3);
            addObject(story, 800, 500);
        }
        if (story == 4) {
            Story story = new Story(4);
            addObject(story, 800, 500);
        }
        if (story == 5) {
            Story story = new Story(5);
            addObject(story, 800, 500);
        }
        if (story == 6) {
            Story story = new Story(6);
            addObject(story, 800, 500);
        }
        if (story == 7) {
            Story story = new Story(7);
            addObject(story, 800, 500);
        }
        if (story == 8) {
            Story story = new Story(8);
            addObject(story, 800, 500);
        }
    }
}

