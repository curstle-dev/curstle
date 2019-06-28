package de.wgkassel.curstle.gates;

public class RoomOne2Gate extends BaseGate {

    @Override
    public void handleGateLogic() {
        generateRoomOne2();
        setImage("images/door_open.png");
    }

}
