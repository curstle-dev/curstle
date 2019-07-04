package de.wgkassel.curstle.Worlds.Screens;

import de.wgkassel.curstle.MysteriousMan;
import de.wgkassel.curstle.Speechbubble;
import de.wgkassel.curstle.Story;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.player.Player;
import greenfoot.Greenfoot;

public class StoryRoom extends BaseWorld {

    int story = 1;
    int i = 100;

    private ChangeCharacter cc;
    private MysteriousMan mM;
    private Speechbubble sb;


    public StoryRoom(Player player) {
        super(player);
        prepare();
    }

    public void prepare() {
        addObject(player, getWidth() / 2, getHeight() / 10 * 8);

        mM = new MysteriousMan();
        addObject(mM, 500, 500);

        sb = new Speechbubble();
        addObject(sb, 670, 400);

        cc = new ChangeCharacter();
        addObject(cc, 840, 400);

        generateWalls();

    }


    public void act() {
        if (Greenfoot.mouseClicked(cc)) {
            story++;
            Story.removeMe = story - 1;
        }


        if (story == 1) {
            Story story = new Story(1);
            addObject(story, 670, 400);
        }
        if (story == 2) {
            Story story = new Story(2);
            addObject(story, 670, 400);
        }
        if (story == 3) {
            Story story = new Story(3);
            addObject(story, 670, 400);
        }
        if (story == 4) {
            Story story = new Story(4);
            addObject(story, 670, 400);
        }
        if (story == 5) {
            Story story = new Story(5);
            addObject(story, 670, 400);
        }
        if (story == 6) {
            Story story = new Story(6);
            addObject(story, 670, 400);
        }
        if (story == 7) {
            Story story = new Story(7);
            addObject(story, 670, 400);
        }
        if (story == 8) {
            Story story = new Story(8);
            addObject(story, 670, 400);
        }

        if (getObjects(Story.class).isEmpty()) {
            if (i >= 0) {
                mM.getImage().setTransparency(i);
                sb.getImage().setTransparency(i);
                cc.getImage().setTransparency(i);
                i--;
            }
            if (i == 0) {
                removeObject(mM);
                removeObject(sb);
                removeObject(cc);
            }
        }

        if (getObjects(MysteriousMan.class).isEmpty()){
            StartScreen2 startScreen2 = new StartScreen2(getPlayer());
            Greenfoot.setWorld(startScreen2);
        }
    }
}

