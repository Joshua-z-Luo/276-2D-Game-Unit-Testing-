package main;

import Entities.monster.MON_ScrewDude;
import Entities.object.OBJ_Battery;
import Entities.object.OBJ_Door;
import Entities.object.OBJ_KeyCard;
import Entities.object.OBJ_Hole;

/**
 * AssetSetter class that will take care of initiating static/moving objects needed in the game window.
 * @author Hayato, Connor, Rose, Joshua
 */
public class AssetSetter {
    private static AssetSetter assetSetter = null;
    GamePanel gp;

    /**
     * Constructor of AssetSetter that takes GamePanel as a parameter
     * @param gp the GamePanel object that is the main game window
     */
    protected AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public static AssetSetter instance(GamePanel gp){
        if(assetSetter == null){
            assetSetter = new AssetSetter(gp);
        }
        return assetSetter;
    }

    /**
     * Takes away the power ups every "240 ticks" and after "500 ticks" the power ups reappear
     */
    public void objectUpdate(){
        if(gp.powerUpTimer > 240) {

            gp.obj[0] = null;
            gp.obj[1] = null;
            gp.obj[2] = null;
            gp.obj[3] = null;
            gp.obj[4] = null;
            gp.obj[16] = null;
        }
        if(gp.powerUpTimer > 500){
            gp.powerUpTimer = 0;
            setPowerUps();
        }
    }

    /**
     * sets up the power ups on the map
     */
    public void setPowerUps(){
        gp.obj[0] = new OBJ_Battery();
        gp.obj[0].x = 200;
        gp.obj[0].y = 450;

        gp.obj[1] = new OBJ_Battery();
        gp.obj[1].x = 600;
        gp.obj[1].y = 150;

        gp.obj[2] = new OBJ_Battery();
        gp.obj[2].x = 650;
        gp.obj[2].y = 500;

        gp.obj[3] = new OBJ_Battery();
        gp.obj[3].x = 50;
        gp.obj[3].y = 700;

        gp.obj[4] = new OBJ_Battery();
        gp.obj[4].x = 50;
        gp.obj[4].y = 700;

        gp.obj[16] = new OBJ_Battery();
        gp.obj[16].x = 575;
        gp.obj[16].y = 700;
    }

    /**
     * gets all the static object items(battery, puddle) ready when setting up the game so that they appear when the game is opened
     */
    public void setObject(){

        //screwDude is subClass of SuperObject, so we can instantiate them like this
        setPowerUps();

        //Door
        gp.obj[7] = new OBJ_Door();
        gp.obj[7].x = 1050;
        gp.obj[7].y = 700;

        //key card
        gp.obj[6] = new OBJ_KeyCard();
        gp.obj[8] = new OBJ_KeyCard();
        gp.obj[9] = new OBJ_KeyCard();

        //Traps
        gp.obj[5] = new OBJ_Hole();
        gp.obj[10] = new OBJ_Hole();
        gp.obj[11] = new OBJ_Hole();
        gp.obj[12] = new OBJ_Hole();
        gp.obj[13] = new OBJ_Hole();
        gp.obj[14] = new OBJ_Hole();
        gp.obj[15] = new OBJ_Hole();
        gp.obj[17] = new OBJ_Hole();

        if(gp.level == 0){
            gp.obj[5].x = 675;
            gp.obj[5].y = 150;

            gp.obj[6].x = 450;
            gp.obj[6].y = 650;

            gp.obj[8].x = 800;
            gp.obj[8].y = 600;

            gp.obj[9].x = 675;
            gp.obj[9].y = 50;

            gp.obj[10].x = 1050;
            gp.obj[10].y = 510;

            gp.obj[11].x = 955;
            gp.obj[11].y = 250;

            gp.obj[12].x = 150;
            gp.obj[12].y = 10;

            gp.obj[13].x = 400;
            gp.obj[13].y = -10;

            gp.obj[14].x = 50;
            gp.obj[14].y = 635;

            gp.obj[15].x = 600;
            gp.obj[15].y = 330;

            gp.obj[17].x = 870;
            gp.obj[17].y = 425;
        }
        else{
            gp.obj[5].x = 725;
            gp.obj[5].y = 150;

            gp.obj[6].x = 450;
            gp.obj[6].y = 500;

            gp.obj[8].x = 575;
            gp.obj[8].y = 625;

            gp.obj[9].x = 150;
            gp.obj[9].y = 450;

            gp.obj[10].x = 775;
            gp.obj[10].y = 525;

            gp.obj[11].x = 955;
            gp.obj[11].y = 250;

            gp.obj[12].x = 200;
            gp.obj[12].y = 50;

            gp.obj[13].x = 150;
            gp.obj[13].y = 400;

            gp.obj[14].x = 50;
            gp.obj[14].y = 635;

            gp.obj[15].x = 775;
            gp.obj[15].y = 700;

            gp.obj[17].x = 870;
            gp.obj[17].y = 425;

            gp.obj[18] = new OBJ_Hole();
            gp.obj[18].x = 385;
            gp.obj[18].y = 535;
        }



    }

    /**
     * gets all the enemies(ScrewDude) ready when setting up the game so that they appear when the game is opened
     */
    public void setMonster(){
        gp.monster[0] = new MON_ScrewDude(gp);
        gp.monster[1] = new MON_ScrewDude(gp);
        gp.monster[2] = new MON_ScrewDude(gp);
        gp.monster[3] = new MON_ScrewDude(gp);
        gp.monster[4] = new MON_ScrewDude(gp);
        if(gp.level == 0){
            gp.monster[0].x = 150;
            gp.monster[0].y = 150;

            gp.monster[1].x = 350;
            gp.monster[1].y = 350;

            gp.monster[2].x = 350;
            gp.monster[2].y = 100;

            gp.monster[3].x = 1000;
            gp.monster[3].y = 350;

            gp.monster[4].x = 450;
            gp.monster[4].y = 650;
        }

        else{
            gp.monster[0].x = 75;
            gp.monster[0].y = 400;

            gp.monster[1].x = 500;
            gp.monster[1].y = 350;

            gp.monster[2].x = 1000;
            gp.monster[2].y = 250;

            gp.monster[3].x = 850;
            gp.monster[3].y = 600;

            gp.monster[4].x = 450;
            gp.monster[4].y = 500;

            gp.monster[5] = new MON_ScrewDude(gp);
            gp.monster[5].x = 75;
            gp.monster[5].y = 200;
        }

    }
}
