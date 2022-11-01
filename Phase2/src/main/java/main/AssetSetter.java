package main;

import Entities.monster.MON_ScrewDude;
import Entities.object.OBJ_Battery;
import Entities.object.OBJ_Puddle;

/**
 * AssetSetter class that will take care of initiating static/moving objects needed in the game window.
 * @author Hayato
 */
public class AssetSetter {
    GamePanel gp;

    /**
     * Constructor of AssetSetter that takes GamePanel as a parameter
     * @param gp
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
        gp.obj[4].y = 200;

        gp.obj[5] = new OBJ_Battery();
        gp.obj[5].x = 650;
        gp.obj[5].y = 500;

        gp.obj[6] = new OBJ_Puddle();
        gp.obj[6].x = 650;
        gp.obj[6].y = 150;

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

    }
}
