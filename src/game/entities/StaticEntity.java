package game.entities;

import java.awt.*;

//lớp StaticEntity mô tả các đối tượng tĩnh(không chuyển động trong trò chơi)
//kế thừa từ lớp cha Entity
public abstract class StaticEntity extends Entity {

    protected Rectangle hitbox;

    public StaticEntity(int size, int xPos, int yPos) {
        super(size, xPos, yPos);
        this.hitbox = new Rectangle(xPos, yPos, size, size);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
