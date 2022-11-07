package Entities.monster;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * The ScrewDude class that draw the screwDude and defines how it moves
 * @author Hayato
 */
public class MON_ScrewDude extends monsterEntity {

    /**
     * Constructor for initiating the screwDude.
     * @param gp Game Panel that holds the game
     */
    public MON_ScrewDude(GamePanel gp){
        super(gp);

        name = "ScrewDude";
        direction = "down";
        speed = 1;

        maxLife = 4;
        life = maxLife;

        //hitbox microadjustments
        solidArea = new Rectangle(2*gp.scale, gp.tileSize, gp.tileSize-4*gp.scale, gp.tileSize);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setMonsterImage();

    }

    /**
     * Draws the moving enemy
     */
    public void setMonsterImage(){
        try{
            walk = ImageIO.read(new File("src/Sprites/screwdude.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

    }
    //set Entities.monster's behaviour

    /**
     * Implements how the enemy moves
     */
    public void setAction(){
        actionLockCounter ++;

        if(actionLockCounter == 25){
            Random random = new Random();
            int i  = random.nextInt(100)+1;//pick a number from 1 to 100

            if(i <=25 && (this.y>gp.tvGuy.y || this.collisionOn)){
                direction = "up";

            }
            if(i>25 && i<=50 && (this.y<gp.tvGuy.y || this.collisionOn)){
                direction = "down";

            }
            if(i > 50 && i <=75 && (this.x>gp.tvGuy.x || this.collisionOn)){
                direction = "left";

            }
            if(i > 75 && i <=100 && (this.x<gp.tvGuy.x || this.collisionOn)){
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
