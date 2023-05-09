package game.entities.ghosts;

import game.ghostStrategies.ClydeStrategy;

//lớp Clyde đại diện cho Clyde ghost
//kế thừa lớp cha ghost
public class Clyde extends Ghost {
    public Clyde(int xPos, int yPos) {
        super(xPos, yPos, "clyde.png");
        setStrategy(new ClydeStrategy(this));
    }
}
