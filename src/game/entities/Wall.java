package game.entities;

//lớp Wall đại diện cho tường của game
//kế thừa từ lớp cha StaticEntity
public class Wall extends StaticEntity {
    public Wall(int xPos, int yPos) {
        super(8, xPos, yPos);
    }
}
