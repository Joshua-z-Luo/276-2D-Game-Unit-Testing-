import Entities.object.OBJ_Battery;
import main.AssetSetter;
import main.GamePanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AssetSetterTest Class to test some aspects of AssetSetter class
 * @author hayatokoyama
 */
class AssetSetterTest {
    static GamePanel gp = new GamePanel();
    private static AssetSetter aSetter = new AssetSetter(gp);
    @BeforeAll
    static void init(){
        aSetter.setPowerUps();
    }

    @Test
    void powerUpsInitiatedCorrectly(){
        assertEquals(new OBJ_Battery().getClass(), gp.obj[0].getClass());
    }

}