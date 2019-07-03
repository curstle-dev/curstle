package de.wgkassel.curstle.Worlds.Screens;

import de.wgkassel.curstle.MysteriousMan;
import de.wgkassel.curstle.Speechbubble;
import de.wgkassel.curstle.Story;
import de.wgkassel.curstle.Torch;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;
import greenfoot.Greenfoot;

public class StoryRoom extends BaseWorld {

    int story = 1;
    private ChangeCharacter cc;

    boolean existStory1 = false;
    boolean existStory2 = false;
    boolean existStory3 = false;
    boolean existStory4 = false;
    boolean existStory5 = false;
    boolean existStory6 = false;
    boolean existStory7 = false;
    boolean existStory8 = false;

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

        Torch torch = new Torch();
        addObject(torch, 400, 400);

    }


    public void act() {
        if (Greenfoot.mouseClicked(cc)) {
            story++;
        }
        Story story1 = new Story(1);
        Story story2 = new Story(2);
        Story story3 = new Story(3);
        Story story4 = new Story(4);
        Story story5 = new Story(5);
        Story story6 = new Story(6);
        Story story7 = new Story(7);
        Story story8 = new Story(8);

        if (existStory2) {
            removeObject(story1);
        }

        if (existStory3) {
            removeObject(story2);
        }

        if (existStory4) {
            removeObject(story3);
        }
        if (existStory5) {
            removeObject(story4);
        }
        if (existStory6) {
            removeObject(story5);
        }
        if (existStory7) {
            removeObject(story6);
        }
        if (existStory8) {
            removeObject(story7);
        }
        if (story == 1) {
            addObject(story1, 800, 500);
            existStory1 = true;
        }

        if (story == 2) {
            existStory2 = true;
            addObject(story2, 800, 500);
        }
        if (story == 3) {
            existStory3 = true;
            addObject(story3, 800, 500);
        }
        if (story == 4) {
            existStory4 = true;
            addObject(story4, 800, 500);
        }
        if (story == 5) {
            existStory5 = true;
            addObject(story5, 800, 500);
        }
        if (story == 6) {
            existStory6 = true;
            addObject(story6, 800, 500);
        }
        if (story == 7) {
            existStory7 = true;
            addObject(story7, 800, 500);
        }
        if (story == 8) {
            existStory8 = true;
            addObject(story8, 800, 500);
        }
    }
}

