package de.wgkassel.curstle.Worlds.Level1;

import de.wgkassel.curstle.RoomContent.Torch;
import de.wgkassel.curstle.Worlds.StartScreen.ChangeCharacter;
import de.wgkassel.curstle.Worlds.StartScreen.StartScreen2;
import de.wgkassel.curstle.player.Player;
import greenfoot.Greenfoot;

public class StoryRoom extends BaseWorld {

    static int story = 1;
    int i = 100;
    private boolean allowStory = true;

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
        addObject(sb, mM.getX() + 170, mM.getY() - 100);

        cc = new ChangeCharacter();
        addObject(cc, mM.getX() + 340, mM.getY() - 100);

        generateWalls();

        Torch torch = new Torch();
        addObject(torch, getWidth() / 8, getHeight() / 8);

        Torch torch1 = new Torch();
        addObject(torch1, getWidth() / 8 + 1300, getHeight() / 8);

        Torch torch2 = new Torch();
        addObject(torch2, getWidth() / 8 + 1300, getHeight() / 8 + 700);

        Torch torch3 = new Torch();
        addObject(torch3, getWidth() / 8, getHeight() / 8 + 700);

    }

    public MysteriousMan getMysteriousMan() {
        return mM;
    }

    public void act() {
        if (Greenfoot.mouseClicked(cc)) {
            story++;
            allowStory = true;
            MysteriousMan.animation = true;
            Story.removeMe = story - 1;
        }

        sb.setLocation(mM.getX() + 170, mM.getY() - 100);
        cc.setLocation(mM.getX() + 340, mM.getY() - 100);
        int storyX = mM.getX() + 170;
        int storyY = mM.getY() - 100;



        if (story == 1 && allowStory) {
            Story story = new Story(1);
            addObject(story, storyX, storyY);

            allowStory = false;
        }
        if (story == 2 && allowStory) {
            Story story = new Story(2);
            addObject(story, storyX, storyY);
            allowStory = false;
        }
        if (story == 3 && allowStory) {
            Story story = new Story(3);
            addObject(story, storyX, storyY);
            allowStory = false;
        }
        if (story == 4 && allowStory) {
            Story story = new Story(4);
            addObject(story, storyX, storyY);
            allowStory = false;
        }
        if (story == 5 && allowStory) {
            Story story = new Story(5);
            addObject(story, storyX, storyY);
            allowStory = false;
        }
        if (story == 6 && allowStory) {
            Story story = new Story(6);
            addObject(story, storyX, storyY);
            allowStory = false;
        }
        if (story == 7 && allowStory) {
            Story story = new Story(7);
            addObject(story, storyX, storyY);
            allowStory = false;
        }
        if (story == 8 && allowStory) {
            Story story = new Story(8);
            addObject(story, storyX, storyY);
            allowStory = false;
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

        if (getObjects(MysteriousMan.class).isEmpty()) {
            StartScreen2 startScreen2 = new StartScreen2(getPlayer());
            Greenfoot.setWorld(startScreen2);
        }
    }
}

