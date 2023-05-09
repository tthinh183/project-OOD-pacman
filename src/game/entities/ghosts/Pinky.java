package game.entities.ghosts;

import game.ghostStrategies.PinkyStrategy;

//lớp Pinky đại diện cho Pinky ghost
//kế thừa lớp cha ghost
public class Pinky extends Ghost {
    public Pinky(int xPos, int yPos) {
        super(xPos, yPos, "pinky.png");
        setStrategy(new PinkyStrategy());
    }
}
