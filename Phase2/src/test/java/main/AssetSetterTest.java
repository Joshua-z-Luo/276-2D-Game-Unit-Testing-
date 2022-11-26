package main;

import Entities.object.OBJ_Battery;
import Entities.object.OBJ_Door;
import Entities.object.OBJ_Hole;
import Entities.object.OBJ_KeyCard;
import main.AssetSetter;
import main.GamePanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the AssetSetter
 * Tests include checking that objects such as enemies, power ups, key cards, traps and enemies are place correctly on the map
 * @author Hayato, Connor, Rose, Joshua
 */
class AssetSetterTest {
    static GamePanel gp = new GamePanel();
    static AssetSetter aSetter = new AssetSetter(gp);
    @BeforeAll
    static void init(){
        aSetter.setPowerUps();
        aSetter.setObject();
    }

    /**
     * Tests if the power ups are initialized properly
     */
    @Test
    void powerUpsInitiatedCorrectly(){
        for(int i =0;i<5;i++){
            assertEquals(new OBJ_Battery().getClass(), gp.obj[i].getClass());
        }
    }


    /**
     * Test if the Holes are initiated correctly
     */
    @Test
    void HolesInitiatedCorrectly(){
        OBJ_Hole testHole = new OBJ_Hole();
        assertEquals(testHole.getClass(), gp.obj[5].getClass());
        assertEquals(testHole.getClass(), gp.obj[10].getClass());
        assertEquals(testHole.getClass(), gp.obj[11].getClass());
        assertEquals(testHole.getClass(), gp.obj[12].getClass());
        assertEquals(testHole.getClass(), gp.obj[13].getClass());
        assertEquals(testHole.getClass(), gp.obj[14].getClass());
        assertEquals(testHole.getClass(), gp.obj[15].getClass());
        assertEquals(testHole.getClass(), gp.obj[17].getClass());
    }

    /**
     *
     * Test if the KeyCards are initiated correctly
     */
    @Test
    void keyCardsInitiatedCorrectly(){
        OBJ_KeyCard testKey = new OBJ_KeyCard();
        assertEquals(testKey.getClass(), gp.obj[6].getClass());
        assertEquals(testKey.getClass(), gp.obj[8].getClass());
        assertEquals(testKey.getClass(), gp.obj[9].getClass());
    }
    /**
     * test if the door is successfully initiated in the first level
     */
    @Test
    void doorInitiatedCorrectlyFirstLevel(){
        OBJ_Door testDoor = new OBJ_Door();
        assertEquals(testDoor.getClass(), gp.obj[7].getClass());
    }

}