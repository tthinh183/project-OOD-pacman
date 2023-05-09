package game.entities.ghosts;

import game.Game;
import game.ghostStrategies.InkyStrategy;

//lớp Inky đại diện cho inky ghost
//kế thừa lớp cha ghost
public class Inky extends Ghost {
    public Inky(int xPos, int yPos) {
        super(xPos, yPos, "inky.png");
        setStrategy(new InkyStrategy(Game.getBlinky()));
    }
}
