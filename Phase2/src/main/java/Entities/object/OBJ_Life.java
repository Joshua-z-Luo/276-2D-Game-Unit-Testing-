package Entities.object;

import Entities.StaticObject;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Health bar class that will change the image accordingly based on the player's health
 * @author Connor
 */
public class OBJ_Life extends StaticObject {
    GamePanel gp;

    /**
     * Constructor for the health bar that takes in the game panel
     * @param gp main game panel where the game is
     */
    public OBJ_Life(GamePanel gp){
        this.gp = gp;
        name = "Health";

        try{
            image = ImageIO.read(new File("src/Sprites/battery1hp.png"));
            image2 = ImageIO.read(new File("src/Sprites/battery2hp.png"));
            image3 = ImageIO.read(new File("src/Sprites/battery3hp.png"));
            image4 = ImageIO.read(new File("src/Sprites/battery4hp.png"));
            image5 = ImageIO.read(new File("src/Sprites/battery5hp.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
