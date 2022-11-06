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

//    public int actionLockCounter ;

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
//        solidArea.x = 3;
//        solidArea.y = 18;
//        solidArea.width = 42;
//        solidArea.height = 30;

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
//    public void update(){
//        setAction();//if subclass has setAction() too, then that is priority
//
//        collisionOn = false;
//        gp.cChecker.checkTile(this);
//        if(!collisionOn){
//            switch(direction){
//                case "up": y -= speed; break;
//                case "down": y += speed; break;
//                case "left": x -= speed; break;
//                case "right": x += speed; break;
//            }
//
//        }
//
//    }

    //set Entities.monster's behaviour

    /**
     * Implements how the enemy moves
     */
    public void setAction(){
        actionLockCounter ++;

        if(actionLockCounter == 25){
            Random random = new Random();
            int i  = random.nextInt(100)+1;//pick a number from 1 to 100

            if(collisionDetect!="up" && collisionDetect!="down" && this.collisionOn){
                if(i <=25 && (this.y>gp.tvGuy.y)){
                    direction = "up";

                }
                if(i>25 && i<=50 && (this.y<gp.tvGuy.y)){
                    direction = "down";

                }
            }

            if(collisionDetect!="left" && collisionDetect!="right" && this.collisionOn){
                if(i > 50 && i <=75 && (this.x>gp.tvGuy.x)){
                    direction = "left";

                }
                if(i > 75 && i <=100 && (this.x<gp.tvGuy.x)){
                    direction = "right";
                }
            }

            actionLockCounter = 0;
        }
    }
}
