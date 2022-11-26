package Entities;

import Entities.object.OBJ_Battery;
import Entities.object.OBJ_Hole;
import Entities.object.OBJ_KeyCard;
import main.GamePanel;
import main.KeyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Main Character that the player will be in control of
 * Includes movement, picking up items and colliding with enemies
 * @author Connor, Joshua, Hayato, Rose
 */
class MainCharacterTVTest {
    public  GamePanel gp;
    public  KeyHandler kH;
    public MainCharacterTV mC;

   @BeforeEach
   void init() {
       gp = new GamePanel();
       kH = new KeyHandler(gp);
       mC = new MainCharacterTV(gp, kH);
       gp.setUpGame();
       gp.aSetter.setObject();
       gp.startGameThread();
       gp.gameState = gp.playState;
   }

    /**
     * Tests if the player's health goes to zero when touching an enemy
     */
   @Test
    void lifeGoesZeroWhenCollidingWithEnemy(){
       for(int i = 0;i < 999;i++){
           mC.interactMonster(i);
           assertEquals(0,mC.life);
           mC.life = 100;
       }
   }

    /**
     * Tests if the score increases by 100 if a battery power up is picked up
     */
   @Test
    void scoreIncreasesWhenPowerUpCollected(){
       int score = mC.score;
       for(int i = 0; i < gp.obj.length; i++){
           if(gp.obj[i] != null && gp.obj[i].getClass().equals(OBJ_Battery.class)){
               score+= 200;
               mC.pickUpObject(i);
               assertEquals(score, mC.score);
           }
       }
       assertEquals(score, mC.score);
   }

    /**
     * Tests if the score increases by 200 if a key card is picked up
     */
   @Test
    void scoreIncreasesWhenKeyCardCollected(){
       int score = mC.score;
       for(int i = 0; i < gp.obj.length; i++){
           if(gp.obj[i] != null && gp.obj[i].getClass().equals(OBJ_KeyCard.class)){
               score += 100;
               mC.pickUpObject(i);
               assertEquals(score, mC.score);
           }
       }
       assertEquals(score, mC.score);
   }

    /**
     * Test if mainCharacter's life goes to zero when touching a trap.
     */
    @Test
    void lifeGoesZeroWhenCollidingWithTrap(){
        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null && gp.obj[i].getClass().equals(OBJ_Hole.class)){
                mC.pickUpObject(i);
                assertEquals(0, mC.life);
                mC.life = 100;
            }
        }
    }


    /**
     * Tests if the life increases after picking up a battery power up
     */
   @Test
    void lifeIncreasesWhenPowerUpCollected() {
       int before = 50;
       mC.life = 50;
       for (int i = 0; i < gp.obj.length; i++) {
           if (gp.obj[i] != null && gp.obj[i].getClass().equals(OBJ_Battery.class)) {
               mC.pickUpObject(i);
               assertTrue(before < mC.life);
           }
       }
   }


    /**
     * Tests if the number of key cards collected goes up after picking up a key card
     */
   @Test
    void numberOfKeyCardsCollectedIncreasesAfterPickingUpKeyCard(){
       gp.tvGuy.pickUpObject(6);
       gp.tvGuy.pickUpObject(8);
       gp.tvGuy.pickUpObject(9);
       assertEquals(3, gp.tvGuy.keyCardCount);
   }

    /**
     * Tests if the player goes up 4 pixels when the up key is pressed
     */
   @Test
    void playerMovesUpFourWhenUpKeyIsPressed(){
       int y = mC.y;
       KeyEvent upKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_W, 'W');
       gp.getKeyListeners()[0].keyPressed(upKey);
       kH.keyPressed(upKey);
       mC.update();
       assertEquals(y - 4, mC.y);
   }

    /**
     * Tests if the player goes down 4 pixels when the down key is pressed
     */
   @Test
    void playerMovesDownFourWhenDownIsPressed(){
       int y = mC.y;
       KeyEvent downKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_S, 'S');
       gp.getKeyListeners()[0].keyPressed(downKey);
       kH.keyPressed(downKey);
       mC.update();
       assertEquals(y + 4, mC.y);
   }

    /**
     * Tests if the player goes left 4 pixels when the left key is pressed
     */
    @Test
    void playerMovesLeftFourWhenLeftIsPressed(){
        int x = mC.x;
        KeyEvent leftKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_A, 'A');
        gp.getKeyListeners()[0].keyPressed(leftKey);
        kH.keyPressed(leftKey);
        mC.update();
        assertEquals(x - 4, mC.x);
    }

    /**
     * Tests if the player goes right 4 pixels when the right key is pressed
     */
    @Test
    void playerMovesRightFourWhenRightIsPressed(){
        int x = mC.x;
        KeyEvent rightKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_D, 'D');
        gp.getKeyListeners()[0].keyPressed(rightKey);
        kH.keyPressed(rightKey);
        mC.update();
        assertEquals(x + 4, mC.x);
    }

}