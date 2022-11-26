package main;

import Entities.MainCharacterTV;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Game Panel
 * Tests include level values and game state changes
 * @author Connor, Hayato, Rose, Joshua
 */
public class GamePanelTest {
    public GamePanel gp;
    public KeyHandler kH;
    public MainCharacterTV mC;

    @BeforeEach
    public void init(){
        gp = new GamePanel();
        kH = new KeyHandler(gp);
        mC = new MainCharacterTV(gp, kH);
        gp.setUpGame();
        gp.startGameThread();
        gp.gameState = gp.playState;
    }

    @AfterEach
    public void cleanup(){
        gp = null;
        kH = null;
        mC = null;
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
     * Tests if gameState = loseState as soon as main Character's life goes zero
     */
    @Test
    void gameStateChangesToLoseStateWhenLifeIsZero(){
        mC.life = 0;
        mC.update();
        assertEquals(gp.gameState, gp.loseState);
    }
}
