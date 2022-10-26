package main;

import monster.MON_ScrewDude;
import object.OBJ_Battery;
import object.OBJ_ScrewDude;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }

    public void setObject(){

        //screwDude is subClass of SuperObject, so we can instantiate them like this
        gp.obj[3] = new OBJ_Battery();
        gp.obj[3].x = 200;
        gp.obj[3].y = 400;


        gp.obj[4] = new OBJ_Battery();
        gp.obj[4].x = 600;
        gp.obj[4].y = 200;
    }
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
