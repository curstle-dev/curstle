package de.wgkassel.curstle.gates;

import greenfoot.GreenfootImage;


public class MainGate extends BaseGate {

    @Override
    public void handleGateLogic() {
        generateRoomOne();
    }

    public MainGate() {
        GreenfootImage door1 = new GreenfootImage(100, 10);
        this.setImage(door1);
    }

}
