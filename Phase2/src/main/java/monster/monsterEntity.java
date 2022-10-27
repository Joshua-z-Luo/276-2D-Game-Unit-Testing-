package monster;

import MainCharacter.Mobile;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Hayato add stuff here
 * @author Hayato
 */
public class monsterEntity extends Mobile {
    GamePanel gp;

//    solidArea = new Rectangle();
//    public int x, y;
//    public BufferedImage image;
//    public int speed;
    public int maxLife;
    public int life;

    public String name;

//    public String direction;

//    public int solidAreaDefaultX,solidAreaDefaultY;
//    public boolean collisionOn = false;

    public int actionLockCounter = 0;

    /**
     * Hayato add stuff here
     * @param gp
     */
    public monsterEntity(GamePanel gp){
        this.gp = gp;
        solidArea = new Rectangle(0,0,48,48);
    }

    /**
     * Hayato add stuff here
     */
    public void setAction(){

    }

    /**
     * Hayato add stuff here
     */
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

    /**
     * Hayato add stuff here
     * @param g2
     * @param gp
     */
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(walk, x, y, gp.tileSize, gp.tileSize, null);
    }
}
