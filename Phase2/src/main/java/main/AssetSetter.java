package main;

import object.OBJ_ScrewDude;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }

    public void setObject(){

        //screwDude is subClass of SuperObject, so we can instantiate them like this
        gp.obj[0] = new OBJ_ScrewDude();
        gp.obj[0].x = 200;
        gp.obj[0].y = 200;

        gp.obj[1] = new OBJ_ScrewDude();
        gp.obj[1].x = 300;
        gp.obj[1].y = 300;

        gp.obj[2] = new OBJ_ScrewDude();
        gp.obj[2].x = 600;
        gp.obj[2].y = 300;
    }
}
