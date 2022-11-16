package Entities;

import Entities.object.OBJ_Battery;
import main.GamePanel;
import main.KeyHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainCharacterTVTest {

    public static GamePanel gp = new GamePanel();
    public static KeyHandler kH = new KeyHandler(gp);
    public static MainCharacterTV mC = new MainCharacterTV(gp,kH);

//   @BeforeAll
//   static void init() {
//   }

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
}