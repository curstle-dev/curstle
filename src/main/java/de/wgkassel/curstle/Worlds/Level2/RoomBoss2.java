package de.wgkassel.curstle.Worlds.Level2;


import de.wgkassel.curstle.MiniMap;
import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.enemy.Endboss;
import de.wgkassel.curstle.enemy.EndbossBar;
import de.wgkassel.curstle.gates.Boss2ToSixGate;
import de.wgkassel.curstle.player.Hearts;
import de.wgkassel.curstle.player.Player;

public class RoomBoss2 extends BaseWorld {
    public RoomBoss2(Player player) {
        super(player);
        prepare();
        generatePotions();
    }

    public void prepare() {
        generateWoodWalls();

        addObject(player, 850, 800);
        Endboss endboss = new Endboss();
        addObject(endboss, getWidth() / 2, getHeight() / 2);

        addObject(endboss.getImageActor(), getWidth() / 2, getHeight() / 2);

        EndbossBar endbossBar = new EndbossBar();
        addObject(endbossBar, 850, 150);


        Boss2ToSixGate gate1 = new Boss2ToSixGate();
        gate1.setRotation(180);
        addObject(gate1, 850, 1000);

        Hearts hearts = new Hearts();
        addObject(hearts, 1300, 35);

        MiniMap miniMap = new MiniMap(14);
        addObject(miniMap, 1500, 800);

    }
}
