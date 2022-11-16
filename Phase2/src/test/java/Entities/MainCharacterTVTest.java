package Entities;

import Entities.object.OBJ_Battery;
import Entities.object.OBJ_Hole;
import main.GamePanel;
import main.KeyHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainCharacterTVTest {

    public static GamePanel gp = new GamePanel();
    public static KeyHandler kH = new KeyHandler(gp);
    public static MainCharacterTV mC = new MainCharacterTV(gp,kH);

    /**
     * this test method tests if tvGuy's life goes 0 when collision with enemy happens
     */
   @Test
    void lifeGoesZeroWhenMonsterIndexIsNot999(){
       for(int i = 0;i<999;i++){
           mC.interactMonster(i);
           assertEquals(0,mC.life);
       }
   }

   @Test
    void lifeIncreasesWhenPowerUpCollected(){
       int score = mC.score;
       for(int i = 0; i < gp.obj.length; i++){
           if(gp.obj[i] != null && gp.obj[i].getClass().equals(OBJ_Battery.class)){
               score+= 100;
               mC.pickUpObject(i);
               assertEquals(score, mC.score);
           }
       }
   }

    /**
     * test if mainCharacter's life goes to zero when touching a trap.
     */
    @Test
    void lifeGoesZeroWhenTvGuyTouchesTrap(){
        int score = mC.score;
        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null && gp.obj[i].getClass().equals(OBJ_Hole.class)){
                mC.pickUpObject(i);
                assertEquals(0, mC.life);
            }
        }
    }

    /**
     * test if gameState = loseState as soon as main Character's life goes zero
     */
    @Test
    void gameStateChangesToLoseStateWhenLifeIsZero(){
        mC.life = 0;
        mC.update();
        assertEquals(gp.gameState, gp.loseState);

    }
}