package de.wgkassel.curstle.Worlds.StartScreen;

import de.wgkassel.curstle.Music;
import de.wgkassel.curstle.Worlds.Level1.MysteriousMan;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.gates.MainGate;
import de.wgkassel.curstle.player.Knight;
import de.wgkassel.curstle.player.Mage;
import de.wgkassel.curstle.player.SwordHit;
import greenfoot.Greenfoot;

public class StartScreen extends BaseWorld {

    private OptionsButton optionb;
    private PlayButton playb;
    private CurstleTitle ct;
    private MainGate gate;
    private ChangeCharacter cc;
    private ChangeCharacter cc2;
    public static int ccc = 0; //change character counter: used to define which character is selected.
    int i = 100;
    public static boolean shouldStart = false;
    private boolean doorgenerated = false;
    private int prevCcc = 0;


    /**
     * Constructor for StartScreen.
     */
    public StartScreen() {
        super(new Knight());
        prepare();
        Greenfoot.start();
    }

    public void startGame() {
        shouldStart = true;
    }

    @Override
    public void act() {
        removeObject(cc);
        removeObject(cc2);
        if (shouldStart && i >= 0) {
            optionb.getImage().setTransparency(i);
            playb.getImage().setTransparency(i);
            ct.getImage().setTransparency(i);
            i--;

            if (i == 0) {
                removeObject(playb);
                removeObject(optionb);
                removeObject(ct);
            }
            if (!doorgenerated) {
                gate = new MainGate();
                addObject(gate, getWidth() / 2, getHeight() / 2 + 80);
            }
        }
        started();
        if (!shouldStart) {
            changeCharacter(2);
            addObject(cc, player.getX() + 150, player.getY() + 10);
            addObject(cc2, player.getX() - 150, player.getY() + 10);
        }
        keepOnGround();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     * Z-Index is based on order of addition (last to be added is on top)
     */

    private void prepare() {

        playb = new PlayButton();
        addObject(playb, getWidth() / 2, getHeight() / 2);

        optionb = new OptionsButton();
        addObject(optionb, 200, getHeight() / 20 * 19);

        ct = new CurstleTitle();
        addObject(ct, getWidth() / 2, getHeight() / 4);

        addObject(player, getWidth() / 2, getHeight() / 10 * 9);

        MysteriousMan mysteriousMan = new MysteriousMan();
        addObject(mysteriousMan, getWidth()/2, getHeight()/2 + 160);

        cc = new ChangeCharacter(); //Used to change the character

        cc2 = new ChangeCharacter(); //The same as the line above
        cc2.setRotation(180);
        shouldStart = false;
    }

    public void started() {
        if (!Music.getMoi().isPlaying()) {
            Music.getMoi().started();
        }
    }

    public void stopped() {
        if (Music.getMoi().isPlaying()) {
            Music.getMoi().stopped();
        }
    }

    public void changeCharacter(int maxChars) {
        if (Greenfoot.mouseClicked(cc)) {
            ccc++;
        }
        if (Greenfoot.mouseClicked(cc2)) {
            ccc--;
        }
        if (ccc >= maxChars) {
            ccc = 0;
        }
        if (ccc < 0) {
            ccc = maxChars - 1;
        }

        if (ccc != prevCcc) {
            int x = player.getX();
            int y = player.getY();
            removeObject(player);
            switch (ccc) {
                case 0:
                    player = new Knight();
                    break;
                case 1:
                    player = new Mage();
                    break;
            }
            addObject(player, x, y);
            prevCcc = ccc;
        }
    }

    public void keepOnGround() {
        int wh = 650;
        if (player.getY() < wh) {
            player.setLocation(player.getX(), wh);
        }
    }

}
