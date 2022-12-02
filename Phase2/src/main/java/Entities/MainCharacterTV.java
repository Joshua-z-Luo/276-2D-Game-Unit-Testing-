package Entities;

import Entities.object.OBJ_Battery;
import Entities.object.OBJ_KeyCard;
import Entities.object.OBJ_Hole;
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
    private static MainCharacterTV tvGuy = null;
    GamePanel gp;
    KeyHandler keyH;

    public int keyCardCount;
    public double maxLife;
    public double life;
    public int score = 0;

    /**
     * Constructor that will take in the game panel and a key handler as well as set the size of the collision box
     * @param gp The main game panel
     * @param keyH key handler associated with the game panel
     */
//    protected MainCharacterTV(GamePanel gp, KeyHandler keyH){
    public MainCharacterTV(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        keyCardCount = 0;

        //because we start at top left of screen and we are saying hitbox start at bottom left of entity
        solidArea = new Rectangle(8,  gp.tileSize, 32, 32);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    /**
     * Instance method that implements the singleton creational pattern
     * @param gp GamePanel that will contain the game
     * @param keyH key handler associated with the game panel
     * @return the single instance of MainCharacterTV
     */
    public static MainCharacterTV instance(GamePanel gp, KeyHandler keyH){
        if(tvGuy == null){
            tvGuy = new MainCharacterTV(gp, keyH);
        }
        return tvGuy;
    }
    /**
     * Sets the default values for the main character such as position and speed and as well as direction
     */
    public void setDefaultValues(){
        x = 75;
        y = 50;
        speed = 4;
        keyCardCount = 0;
        //how much we will move in the next update
        direction = "down";
        score = 0;
        maxLife = 100.0;
        life = maxLife;

    }

    /**
     * Sets the default location of entities and their direction
     */
    public void setDefaultPosition() {
        x = 75;
        y = 50;
        //how much we will move in the next update
        direction = "down";
    }

    /**
     * Called when a battery power up is picked up
     * Increases score and life
     */
    public void collectReward(){
        life += 30;
        score += 200;
    }

    /**
     * Called when a key card is collected
     * Increases score and increases the number of keycards held
     */
    public void collectKeyCard(){
        score += 100;
        keyCardCount++;
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
     * Also will check if there has been a collision with a solid Entities.tile
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

                    gp.totalLevelTime += ( System.currentTimeMillis() - gp.startTime)/1000;
                    gp.totalLevelScore = score;

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
            collectReward();
            if(life > maxLife){
                life = maxLife;
            }
            //**UPDATE** when TVGuy touches battery his life gets increased by 30 so 10ish seconds
        }
        else if(index != 999 && gp.obj[index].getClass().equals(OBJ_Hole.class)){
//            System.out.println("You walked into a trap!");
            life = 0;
        }
        else if(index != 999 && gp.obj[index].getClass().equals(OBJ_KeyCard.class)){
//            System.out.println("You got a key");
            gp.obj[index] = null;
            collectKeyCard();
        }
    }

    /**
     * Checks if the index of the enemy array is valid and then makes the character's life 0 -> end game
     * @param i index of the enemy
     */
    public void interactMonster(int i){
        if(i != 999){
//            System.out.println("You are hitting a monster");
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
