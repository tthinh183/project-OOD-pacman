package game.entities;

import java.awt.*;


public abstract class Entity {
    protected int size;
    protected int xPos;
    protected int yPos;
    
    //thực thể đã bị phá hủy hay chưa
    protected boolean destroyed = false;

    public Entity(int size, int xPos, int yPos) {
        this.size = size;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void update() {}

    public void render(Graphics2D g) {}

    public void destroy() {
        this.xPos = -32;
        this.yPos = -32;
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getSize() {
        return size;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
    //trả về hình chữ nhật bao quanh thực thể, được sử dụng để xác định va chạm giữa các thực thể khác.
    public abstract Rectangle getHitbox();
}
