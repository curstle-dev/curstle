package de.wgkassel.curstle.Worlds.StartScreen;

import de.wgkassel.curstle.Worlds.Level1.BaseWorld;
import de.wgkassel.curstle.gates.MainGate;
import de.wgkassel.curstle.player.Player;

public class StartScreen2 extends BaseWorld {

    public StartScreen2(Player player) {
        super(player);
        prepare();
    }


    public void prepare() {
        MainGate gate = new MainGate();
        addObject(gate, getWidth() / 2, getHeight() / 2 + 80);

        addObject(player, getWidth() / 2, getHeight() / 10 * 9);
    }
}
