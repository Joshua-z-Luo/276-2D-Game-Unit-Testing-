package main;

import Entities.monster.MON_ScrewDude;
import Entities.object.OBJ_Battery;
import Entities.object.OBJ_Door;
import Entities.object.OBJ_KeyCard;
import Entities.object.OBJ_Puddle;

/**
 * AssetSetter class that will take care of initiating static/moving objects needed in the game window.
 * @author Hayato, Connor, Rose, Joshua
 */
public class AssetSetter {
    GamePanel gp;

    /**
     * Constructor of AssetSetter that takes GamePanel as a parameter
     * @param gp the GamePanel object that is the main game window
     */
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    /**
     * get all the static object items(battery, puddle) ready when setting up the game so that they appear when the game is opened
     */
    public void setObject(){

        //screwDude is subClass of SuperObject, so we can instantiate them like this
        gp.obj[3] = new OBJ_Battery();
        gp.obj[3].x = 200;
        gp.obj[3].y = 450;


        gp.obj[4] = new OBJ_Battery();
        gp.obj[4].x = 600;
        gp.obj[4].y = 150;

        gp.obj[5] = new OBJ_Battery();
        gp.obj[5].x = 650;
        gp.obj[5].y = 500;

        gp.obj[6] = new OBJ_Puddle();
        gp.obj[6].x = 650;
        gp.obj[6].y = 150;

        gp.obj[7] = new OBJ_Door();
        gp.obj[7].x = 1050;
        gp.obj[7].y = 750;

        //key card
        gp.obj[8] = new OBJ_KeyCard();
        gp.obj[8].x = 450;
        gp.obj[8].y = 650;

<<<<<<< HEAD

=======
>>>>>>> f6bd120d7be1e6b652b8871673d6b1b29c3fcebb
        gp.obj[9] = new OBJ_KeyCard();
        gp.obj[9].x = 750;
        gp.obj[9].y = 350;

        gp.obj[10] = new OBJ_KeyCard();
        gp.obj[10].x = 750;
        gp.obj[10].y = 150;
<<<<<<< HEAD
=======
        gp.obj[9] = new OBJ_Battery();
        gp.obj[9].x = 50;
        gp.obj[9].y = 700;
>>>>>>> f6bd120d7be1e6b652b8871673d6b1b29c3fcebb

        gp.obj[11] = new OBJ_Battery();
        gp.obj[11].x = 50;
        gp.obj[11].y = 700;

<<<<<<< HEAD
        gp.obj[12] = new OBJ_Puddle();
        gp.obj[12].x = 1050;
        gp.obj[12].y = 550;

        gp.obj[13] = new OBJ_Puddle();
        gp.obj[13].x = 955;
        gp.obj[13].y = 250;
=======
        gp.obj[11] = new OBJ_Puddle();
        gp.obj[11].x = 955;
        gp.obj[11].y = 250;
>>>>>>> f6bd120d7be1e6b652b8871673d6b1b29c3fcebb
    }

    /**
     * get all the enemies(ScrewDude) ready when setting up the game so that they appear when the game is opened
     */
    public void setMonster(){
        gp.monster[0] = new MON_ScrewDude(gp);
        gp.monster[0].x = 150;
        gp.monster[0].y = 150;

        gp.monster[1] = new MON_ScrewDude(gp);
        gp.monster[1].x = 350;
        gp.monster[1].y = 350;

        gp.monster[2] = new MON_ScrewDude(gp);
        gp.monster[2].x = 350;
        gp.monster[2].y = 100;

        gp.monster[3] = new MON_ScrewDude(gp);
        gp.monster[3].x = 1000;
        gp.monster[3].y = 350;

    }
}
