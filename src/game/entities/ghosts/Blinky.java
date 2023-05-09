package game.entities.ghosts;

import game.ghostStrategies.BlinkyStrategy;

//lớp Blinky đại diện cho blinky ghost
//kế thừa lớp cha ghost
public class Blinky extends Ghost {
    public Blinky(int xPos, int yPos) {
        super(xPos, yPos, "blinky.png");
        setStrategy(new BlinkyStrategy());
    }
}
