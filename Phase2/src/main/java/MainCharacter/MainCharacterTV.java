package MainCharacter;

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
public class MainCharacterTV extends Mobile {
    GamePanel gp;
    KeyHandler keyH;

    /**
     * Constructor that will take in the game panel and a key handler as well as set the size of the collision box
     * @param gp The main game panel
     * @param keyH key handler associated with the game panel
     */
    public MainCharacterTV(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle(8,16, 32, 32);

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
        direction = "down";
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
            gp.cChecker.checkTile(this);

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
     * Draws the character
     * @param g2 Graphics2D object associated with the game panel
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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
