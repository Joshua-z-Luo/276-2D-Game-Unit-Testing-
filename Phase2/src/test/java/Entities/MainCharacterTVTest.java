package Entities;

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
}