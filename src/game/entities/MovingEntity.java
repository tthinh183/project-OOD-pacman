package game.entities;

import javax.imageio.ImageIO;

import game.GameplayPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

//lớp MovingEntity đại diện cho sự di chuyển của các thực thể
//kế thừa lớp cha Entity
public abstract class MovingEntity extends Entity {
    protected int spd;//tốc độ của thực thể
    // tốc độ di chuyển theo trục x và trục y
    protected int xSpd = 0;
    protected int ySpd = 0;
    protected BufferedImage sprite;// hình ảnh của thực thể	
    protected float subimage = 0;//chỉ số hình ảnh của sprite được sử dụng để vẽ
    protected int nbSubimagesPerCycle;//số lượng hình ảnh được sử dụng để tạo ra hoạt hình cho sprite
    protected int direction = 0;//hướng di chuyển của thực thể
    protected float imageSpd = 0.2f;

    public MovingEntity(int size, int xPos, int yPos, int spd, String spriteName, int nbSubimagesPerCycle, float imageSpd) {
        super(size, xPos, yPos);
        this.spd = spd;
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResource("resources/img/" + spriteName));
            this.nbSubimagesPerCycle = nbSubimagesPerCycle;
            this.imageSpd = imageSpd;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        updatePosition();
    }

    public void updatePosition() {
        
        if (!(xSpd == 0 && ySpd == 0)) {
            xPos+=xSpd;
            yPos+=ySpd;

        
            if (xSpd > 0) {
                direction = 0;
            } else if (xSpd < 0) {
                direction = 1;
            } else if (ySpd < 0) {
                direction = 2;
            } else if (ySpd > 0) {
                direction = 3;
            }

           
            subimage += imageSpd;
            if (subimage >= nbSubimagesPerCycle) {
                subimage = 0;
            }
        }


        if (xPos > GameplayPanel.width) {
            xPos = 0 - size + spd;
        }

        if (xPos < 0 - size + spd) {
            xPos = GameplayPanel.width;
        }

        if (yPos > GameplayPanel.height) {
            yPos = 0 - size + spd;
        }

        if (yPos < 0 - size + spd) {
            yPos = GameplayPanel.height;
        }
    }

    @Override
    public void render(Graphics2D g) {
        
        g.drawImage(sprite.getSubimage((int)subimage * size + direction * size * nbSubimagesPerCycle, 0, size, size), this.xPos, this.yPos,null);
    }

    //kiểm tra xem thực thể có nằm trên lưới hay không
    public boolean onTheGrid() {
        return (xPos%8 == 0 && yPos%8 == 0);
    }

    // kiểm tra xem thực thể có nằm trong cửa sổ chơi hay không
    public boolean onGameplayWindow() { return !(xPos<=0 || xPos>= GameplayPanel.width || yPos<=0 || yPos>= GameplayPanel.height); }
    
    //lấy hitbox của thực thể để xử lý va chạm
    public Rectangle getHitbox() {
        return new Rectangle(xPos, yPos, size, size);
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public void setSprite(String spriteName) {
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResource("img/" + spriteName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public float getSubimage() {
        return subimage;
    }

    public void setSubimage(float subimage) {
        this.subimage = subimage;
    }

    public int getNbSubimagesPerCycle() {
        return nbSubimagesPerCycle;
    }

    public void setNbSubimagesPerCycle(int nbSubimagesPerCycle) {
        this.nbSubimagesPerCycle = nbSubimagesPerCycle;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getxSpd() {
        return xSpd;
    }

    public void setxSpd(int xSpd) {
        this.xSpd = xSpd;
    }

    public int getySpd() {
        return ySpd;
    }

    public void setySpd(int ySpd) {
        this.ySpd = ySpd;
    }

    public int getSpd() {
        return spd;
    }
}
