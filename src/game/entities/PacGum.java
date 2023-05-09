package game.entities;

import java.awt.*;

//lớp PacGum đại diện cho thức ăn 
//kế thừa từ lớp cha StaticEntity
public class PacGum extends StaticEntity {
    public PacGum(int xPos, int yPos) {
        super(4, xPos + 8, yPos + 8);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(255, 183, 174));
        g.fillRect(xPos, yPos, size, size);
    }
}
