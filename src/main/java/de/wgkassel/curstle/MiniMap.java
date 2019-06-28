package de.wgkassel.curstle;

import greenfoot.Actor;

public class MiniMap extends Actor {

    public MiniMap(int x) {
        switch (x) {
            case 1:
            default:
                setImage("MiniMapRoom1.png");
                this.getImage().scale(172, 136);
                break;
            case 2:
                setImage("MiniMapRoom2.png");
                this.getImage().scale(172, 136);
                break;
            case 3:
                setImage("MiniMapRoom3.png");
                this.getImage().scale(172, 136);
                break;
            case 4:
                setImage("MiniMapBossRoom.png");
                this.getImage().scale(172, 136);
                break;
            case 5:
                setImage("MiniMapStairsRoom.png");
                this.getImage().scale(172, 136);
                break;
            case 6:
                setImage("MiniMapLevel2Room1.png");
                this.getImage().scale(228, 228);
                break;
            case 7:
                setImage("MiniMapLevel2Room2.png");
                this.getImage().scale(228, 228);
                break;
            case 8:
                setImage("MiniMapLevel2Room3.png");
                this.getImage().scale(228, 228);
                break;
            case 9:
                setImage("MiniMapLevel2Room4.png");
                this.getImage().scale(228, 228);
                break;
            case 10:
                setImage("MiniMapLevel2Room5.png");
                this.getImage().scale(228, 228);
                break;
            case 11:
                setImage("MiniMapLevel2Room6.png");
                this.getImage().scale(228, 228);
                break;
            case 12:
                setImage("MiniMapLevel2StairsRoomDown.png");
                this.getImage().scale(228, 228);
                break;
            case 13:
                setImage("MiniMapLevel2StairsRoomUp.png");
                this.getImage().scale(228, 228);
                break;
            case 14:
                setImage("MiniMapLevel2BossRoom.png");
                this.getImage().scale(228, 228);
        }
    }
}
