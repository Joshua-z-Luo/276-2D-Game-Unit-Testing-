package Entities;

import Entities.object.OBJ_Battery;
import Entities.object.OBJ_KeyCard;
import Entities.object.OBJ_Puddle;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The main character class that will take care of drawing and updating.
 * @author Connor
 */
public class MainCharacterTV extends MovingObject {
    GamePanel gp;
    KeyHandler keyH;


    Boolean hasKeyCard;

    /**
     * Constructor that will take in the game panel and a key handler as well as set the size of the collision box
     * @param gp The main game panel
     * @param keyH key handler associated with the game panel
     */
    public MainCharacterTV(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        hasKeyCard = false;

        //because we start at top left of screen and we are saying hitbox start at bottom left of entity
        solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);

        solidAreaDefaultX = 0;
        solidAreaDefaultY = gp.tileSize;

        setDefaultValues();
        getPlayerImage();
    }

    /**
     * Sets the default values for the main character such as position and speed and as well as direction
     */
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;

        //how much we will move in the next update
        direction = "down";
        directionX = 0;
        directionY = 0;

        maxLife = 100.0;
        life = maxLife;
    }

    public void setDefaultPosition() {
        x = 100;
        y = 100;
        //how much we will move in the next update
        direction = "down";
    }

    public void restoreLife() {
        life = maxLife;
    }

    /**
     * Grabs the image for the character
     */
    public void getPlayerImage(){
        try{

            walk = ImageIO.read(new File("src/Sprites/tvGuy.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Checks if a key was pressed and updates accordingly of which direction the character should move on screen
     * Also will check if there has been a collision with a solid tile
     */
    public void update() {
        if(life <= 0){
            gp.gameState = gp.loseState;
        }
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            } else if (keyH.downPressed) {
                direction = "down";
            }
            collisionOn = false;

            //

            //Check Tile Collision
            gp.cChecker.checkTile(this);

            //check Entities.object collision
            int objIndex = gp.cChecker.checkObject(this, true);
           //if objIndex is the index of door, then
           //show win screen
            if(objIndex ==7){
                if(hasKeyCard){
                    gp.gameState = gp.winState;
                }
                //jump to the main page
            }
            else{
                pickUpObject(objIndex);
            }
            //Check Entities.monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            interactMonster(monsterIndex);

            if (!collisionOn) {
                switch (direction) {
                    case "up" -> y -= speed;
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
                    case "down" -> y += speed;
                }
            }

        }
    }

    public void pickUpObject(int index){
        if(index != 999 && gp.obj[index].getClass().equals(OBJ_Battery.class)){
            gp.obj[index] = null;
            life += 30;
            //**UPDATE** when TVGuy touches battery his life gets increased by 30 so 10ish seconds
        }
        else if(index != 999 && gp.obj[index].getClass().equals(OBJ_Puddle.class)){
            System.out.println("You walked into a trap!");
            life = 0;
        }
        else if(index != 999 && gp.obj[index].getClass().equals(OBJ_KeyCard.class)){
            System.out.println("You got a key");
            gp.obj[index] = null;
            this.hasKeyCard = true;
        }
    }

    public void walkInTrap(int index){
        if(index != 999){
            System.out.println("You walked into a trap!");
            life = 0;
        }
    }

    public void interactMonster(int i ){
        if(i != 999){
            System.out.println("You are hitting a monster");
            life = 0;
        }
    }


    /**
     * Draws the character
     * @param g2 Graphics2D Entities.object associated with the game panel
     */
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;
        switch(direction){
            case "up":
                image = walk;
                break;
            case "down":
                image = walk;
                break;
            case "right":
                image = walk;
                break;
            case "left":
                image = walk;
                break;

        }
        g2.drawImage(image, x, y, gp.imageEntityWidth, gp.imageEntityHeight, null);
    }
}
