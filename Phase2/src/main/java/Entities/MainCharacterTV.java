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
 * @author Connor, Hayato, Rose, Joshua
 */
public class MainCharacterTV extends MovingObject {
    GamePanel gp;
    KeyHandler keyH;
    Boolean hasKeyCard;

    public int keyCardCount;
    public double maxLife;
    public double life;

    /**
     * Constructor that will take in the game panel and a key handler as well as set the size of the collision box
     * @param gp The main game panel
     * @param keyH key handler associated with the game panel
     */
    public MainCharacterTV(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        hasKeyCard = false;
        keyCardCount = 0;

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
        keyCardCount = 0;
        //how much we will move in the next update
        direction = "down";
        directionX = 0;
        directionY = 0;

        maxLife = 100.0;
        life = maxLife;
    }

    /**
     * Sets the default location of entities and their direction
     */
    public void setDefaultPosition() {
        x = 100;
        y = 100;
        //how much we will move in the next update
        direction = "down";
    }

    /**
     * Restores the life of the main character to full
     */
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

            //Check Tile Collision
            gp.cChecker.checkWall(this);

            //check object collision
            int objIndex = gp.cChecker.checkObject(this, true);

           //if objIndex is the index of door and has collected the keycard, then
           //show win screen & reset keycard count to 0 to replay
            if(objIndex == 7){
                if(keyCardCount == 3){
                    gp.gameState = gp.winState;
                }
                if(keyCardCount == 3 && gp.gameState == gp.winState){
                    keyCardCount = 0;
                }
            }
            //check for the other objects
            else{
                pickUpObject(objIndex);
            }
            //Check monster collision
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

    /**
     * Checks what the object that was picked up
     * If it was a battery power up, the main character's life will increase and the battery will disappear
     * If it was a puddle (trap), the main character's life will become 0 and the game will end
     * If it was a keycard, then the main character will be able to leave the level
     * @param index The index of the object collided with such as puddles, key cards or power ups
     */
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
            this.keyCardCount++;
//            this.hasKeyCard = true;
        }
    }

    /**
     * Checks if the index of the enemy array is valid and then makes the character's life 0 -> end game
     * @param i index of the enemy
     */
    public void interactMonster(int i){
        if(i != 999){
            System.out.println("You are hitting a monster");
            life = 0;
        }
    }


    /**
     * Draws the character
     * @param g2 Graphics2D object associated with the game panel
     */
    public void draw(Graphics2D g2){
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
