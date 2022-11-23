package Entities.monster;

import Entities.MovingObject;
import main.GamePanel;
import java.awt.*;

/**
 * monsterEntity class that takes care of any monster's drawing and updating.
 * @author Hayato
 */
public class monsterEntity extends MovingObject {
    GamePanel gp;
    public int maxLife;
    public int life;
    public String name;
    public int actionLockCounter = 0;

    /**
     * Constructor that takes GamePanel as a parameter and sets the solidArea of a monster
     * @param gp GamePanel object that holds the game
     */
    public monsterEntity(GamePanel gp){
        this.gp = gp;
        solidArea = new Rectangle(0,0,48,48);
    }

    /**
     * defines how a monster moves
     */
    public void setAction(){

    }

    /**
     * updates the state of a monster. it takes care of collision check if an enemy collides with other entities.
     */
    public void update(){
        setAction();

        collisionOn = false;
        gp.cChecker.checkWall(this);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkPlayer(this);
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
     * draws an image of a monster on the game window.
     * @param g2 Graphics2D object that draws the game
     * @param gp GamePanel object that holds the game
     */
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(walk, x, y, gp.imageEntityWidth, gp.imageEntityHeight, null);
    }
}
