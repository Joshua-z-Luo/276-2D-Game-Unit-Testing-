package monster;

import MainCharacter.Mobile;
import main.GamePanel;
import org.w3c.dom.Entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MON_ScrewDude extends monsterEntity {

//    public int actionLockCounter ;

    public MON_ScrewDude(GamePanel gp){
        super(gp);

        name = "ScrewDude";
        direction = "down";
        speed = 1;

        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;

        getMonsterImage();

    }
    public void getMonsterImage(){
        try{

            image = ImageIO.read(new File("src/Sprites/screwdude.png"));
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    //set monster's behaviour
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
