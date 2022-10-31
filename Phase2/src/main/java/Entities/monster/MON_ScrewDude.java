package Entities.monster;

//import MainCharacter.Mobile;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Hayato add stuff here
 * @author Hayato
 */
public class MON_ScrewDude extends monsterEntity {

//    public int actionLockCounter ;

    /**
     * Hayato add stuff here
     * @param gp
     */
    public MON_ScrewDude(GamePanel gp){
        super(gp);

        name = "ScrewDude";
        direction = "down";
        speed = 1;

        maxLife = 4;
        life = maxLife;

        //hitbox microadjustments
        solidArea = new Rectangle(2*gp.scale, 0, gp.tileSize-4*gp.scale, gp.tileSize);
//        solidArea.x = 3;
//        solidArea.y = 18;
//        solidArea.width = 42;
//        solidArea.height = 30;

        solidAreaDefaultX = 0;
        solidAreaDefaultY = gp.tileSize;
        getMonsterImage();

    }

    /**
     * Hayato add stuff here
     */
    public void getMonsterImage(){
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
     * Hayato add stuff here
     */
    public void setAction(){
        actionLockCounter ++;


        if(actionLockCounter==120){
            Random random = new Random();
            int i  = random.nextInt(100)+1;//pick a number from 1 to 100

            if(i <=25){
                direction = "up";

            }
            if(i>25 && i<=50){
                direction = "down";

            }
            if(i > 50 && i <=75){
                direction = "left";

            }
            if(i > 75 && i <=100){

                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
