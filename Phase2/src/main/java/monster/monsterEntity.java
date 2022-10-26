package monster;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class monsterEntity {
    GamePanel gp;
    public int x, y;
    public BufferedImage image;
    public int speed;
    public int maxLife;
    public int life;

    public String name;

    public String direction;
    public Rectangle solidArea= new Rectangle(0,0,48,48);

    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn = false;

    public int actionLockCounter = 0;

    public monsterEntity(GamePanel gp){
        this.gp = gp;
    }

    public void setAction(){

    }
    public void update(){
        setAction();//if subclass has setAction() too, then that is priority

        collisionOn = false;
//        gp.cChecker.checkTile(this);
        if(!collisionOn){
            switch(direction){
                case "up": y -= speed; break;
                case "down": y += speed; break;
                case "left": x -= speed; break;
                case "right": x += speed; break;
            }

        }

    }
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
