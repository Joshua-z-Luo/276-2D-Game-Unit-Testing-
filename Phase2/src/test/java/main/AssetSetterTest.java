package main;

import Entities.object.OBJ_Battery;
import Entities.object.OBJ_Hole;
import main.AssetSetter;
import main.GamePanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * main.AssetSetterTest Class to test some aspects of AssetSetter class
 * @author hayatokoyama
 */
class AssetSetterTest {
    static GamePanel gp = new GamePanel();
    private static AssetSetter aSetter = new AssetSetter(gp);
    @BeforeAll
    static void init(){
        aSetter.setPowerUps();
        aSetter.setObject();
    }

    @Test
    void powerUpsInitiatedCorrectly(){
        for(int i =0;i<5;i++){
            assertEquals(new OBJ_Battery().getClass(), gp.obj[i].getClass());
        }
    }

    @Test
    void HolesInitiatedCorrectly(){
        assertEquals(new OBJ_Hole().getClass(), gp.obj[5].getClass());
        assertEquals(new OBJ_Hole().getClass(), gp.obj[10].getClass());
        assertEquals(new OBJ_Hole().getClass(), gp.obj[11].getClass());
        assertEquals(new OBJ_Hole().getClass(), gp.obj[12].getClass());
        assertEquals(new OBJ_Hole().getClass(), gp.obj[13].getClass());
        assertEquals(new OBJ_Hole().getClass(), gp.obj[14].getClass());
        assertEquals(new OBJ_Hole().getClass(), gp.obj[15].getClass());
        assertEquals(new OBJ_Hole().getClass(), gp.obj[17].getClass());
    }
}