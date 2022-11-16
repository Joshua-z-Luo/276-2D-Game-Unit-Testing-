package Entities;

import Entities.object.OBJ_Battery;
import Entities.object.OBJ_Hole;
import Entities.object.OBJ_KeyCard;
import main.GamePanel;
import main.KeyHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainCharacterTVTest {

    public static GamePanel gp = new GamePanel();
    public static KeyHandler kH = new KeyHandler(gp);
    public static MainCharacterTV mC = new MainCharacterTV(gp,kH);

   @BeforeAll
   static void init() {
       gp.setUpGame();
       gp.startGameThread();
       gp.gameState = gp.playState;
   }

   @Test
    void lifeGoesZeroWhenMonsterIndexIsNot999(){
       for(int i = 0;i < 999;i++){
           mC.interactMonster(i);
           assertEquals(0,mC.life);
       }
   }

   @Test
    void scoreIncreasesWhenPowerUpCollected(){
       int score = mC.score;
       for(int i = 0; i < gp.obj.length; i++){
           if(gp.obj[i] != null && gp.obj[i].getClass().equals(OBJ_Battery.class)){
               score+= 100;
               mC.pickUpObject(i);
           }
           assertEquals(score, mC.score);
       }
   }

   @Test
    void scoreIncreasesWhenKeyCardCollected(){
       int score = mC.score;
       for(int i = 0; i < gp.obj.length; i++){
           if(gp.obj[i] != null && gp.obj[i].getClass().equals(OBJ_KeyCard.class)){
               score += 200;
               mC.pickUpObject(i);
           }
           assertEquals(score, mC.score);
       }
   }

   @Test
    void lifeIncreasesWhenPowerUpCollected() {
       mC.life = 50;
       for (int i = 0; i < gp.obj.length; i++) {
           if (gp.obj[i] != null && gp.obj[i].getClass().equals(OBJ_Battery.class)) {
               mC.pickUpObject(i);
           }
           assertTrue(50 < mC.life);
       }
   }

   @Test
    void gameStateIsLoseStateWhenHealthIsZero(){
       mC.life = 0;
       mC.update();
       assertEquals(gp.loseState, gp.gameState);
   }
}