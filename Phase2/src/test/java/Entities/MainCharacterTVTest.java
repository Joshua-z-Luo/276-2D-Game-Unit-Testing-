package Entities;

import Entities.object.OBJ_Battery;
import Entities.object.OBJ_Hole;
import Entities.object.OBJ_KeyCard;
import main.AssetSetter;
import main.GamePanel;
import main.KeyHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class MainCharacterTVTest {

    public static GamePanel gp = new GamePanel();
    public static KeyHandler kH = new KeyHandler(gp);
    public static MainCharacterTV mC = new MainCharacterTV(gp,kH);
    private static AssetSetter aSetter = new AssetSetter(gp);

   @BeforeAll
   static void init() {
       gp.setUpGame();
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
               score+= 100;
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
               score += 200;
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
     * Tests if gameState = loseState as soon as main Character's life goes zero
     */
    @Test
    void gameStateChangesToLoseStateWhenLifeIsZero(){
        mC.life = 0;
        mC.update();
        assertEquals(gp.gameState, gp.loseState);
    }

    /**
     * Tests if the life increases after picking up a battery power up
     */
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

    /**
     * Tests if the player has all 3 keycards and then collides with the exit door, the game state will change to the win state
     */
   @Test
    void gameStateChangesToWinStateWhenMainCharacterReachesDoorWith3Keycards(){
        mC.keyCardCount = 3;
        mC.x = 1050;
        mC.y = 700;
        KeyEvent rightKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_D, 'D');
        gp.getKeyListeners()[0].keyPressed(rightKey);
        kH.keyPressed(rightKey);
        mC.update();
        assertEquals(gp.winState, gp.gameState);

   }

    /**
     * Tests if the number of key cards collected goes up after picking up a key card
     */
   @Test
    void numberOfKeyCardsCollectedIncreasesAfterPickingUpKeyCard(){
       int count = 0;
       for(int i = 0; i < gp.obj.length; i++){
           if(gp.obj[i] != null && gp.obj[i].getClass().equals(OBJ_KeyCard.class)){
               mC.pickUpObject(i);
               count++;
               assertEquals(count, mC.keyCardCount);
           }
       }
       assertEquals(3, mC.keyCardCount);
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

    /**
     * Tests if the level gets incremented by 1 so the next level can load
     */
    @Test
    void levelGetsIncrementedWhenWinningLevel0(){
        gp.gameState = gp.winState;
        KeyEvent enterKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_ENTER, 'e');
        gp.getKeyListeners()[0].keyPressed(enterKey);
        kH.keyPressed(enterKey);
        mC.update();
        assertEquals(1, gp.level);
    }

    /**
     * Tests if the level is decremented after there are no lives and the player dies
     */
    @Test
    void levelGetsDecrementedWhenZeroRetriesLeft(){
        gp.level = 1;
        gp.retries = 0;
        gp.gameState = gp.loseState;
        KeyEvent enterKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),0, KeyEvent.VK_ENTER, 'e');
        gp.getKeyListeners()[0].keyPressed(enterKey);
        kH.keyPressed(enterKey);
        gp.update();
        assertEquals(0, gp.level);
    }
}