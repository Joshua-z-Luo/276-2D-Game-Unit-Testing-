package main;

import main.GamePanel;
import main.KeyHandler;
import Entities.MainCharacterTV;
import main.UI;
import org.junit.jupiter.api.*;
import org.testng.annotations.AfterClass;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.junit.jupiter.api.Assertions.*;


public class KeyHandlerTest {
    public static boolean upPressed, downPressed, leftPressed, rightPressed; // do we need?
    KeyEvent e;
    public static GamePanel gp = new GamePanel();
    public static KeyHandler kH = new KeyHandler(gp);
    static AssetSetter aSetter = new AssetSetter(gp);
    public static MainCharacterTV tvGuy = new MainCharacterTV(gp,kH);
    public void keyTyped(KeyEvent e) {}

    @BeforeEach
    public void init(){
        gp.setUpGame();
        gp.startGameThread();
        gp.gameState = gp.playState;
    }

    @AfterClass
    public void cleanUp(){
        gp.restart();
        gp.tvGuy.direction = "down";
    }

    /**
     * Tests if the down key was pressed
     */
    @Test
    void MainCharacterMovesDownWhenDownKeyPressed(){
        // simulate key being pressed
        KeyEvent downKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_S, 'S');
        gp.getKeyListeners()[0].keyPressed(downKey);

        kH.keyPressed(downKey);
//        keyH.keyPressed(downKey);
        tvGuy.update();
        // check if direction is down
        assertEquals("down", tvGuy.direction);
    }

    /**
     * Tests if the up key was pressed
     */
    @Test
    void MainCharacterMovesUpWhenUpKeyPressed(){
        // simulate key being pressed
        KeyEvent upKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_W, 'W');
        gp.getKeyListeners()[0].keyPressed(upKey);
        kH.keyPressed(upKey);
        tvGuy.update();
        // check if direction is down
        assertEquals("up",tvGuy.direction);
    }

    /**
     * Tests if the left key was pressed
     */
    @Test
    void MainCharacterMovesLeftWhenLeftKeyPressed(){
        // simulate key being pressed
        KeyEvent leftKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_A, 'A');
        gp.getKeyListeners()[0].keyPressed(leftKey);
        kH.keyPressed(leftKey);
        tvGuy.update();
        // check if direction is down
        assertEquals("left",tvGuy.direction);
    }

    /**
     * Tests if the right key was pressed
     */
    @Test
    void MainCharacterMovesRightWhenRightKeyPressed(){
        // simulate key being pressed
        KeyEvent rightKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_D, 'D');
        gp.getKeyListeners()[0].keyPressed(rightKey);
        kH.keyPressed(rightKey);
        tvGuy.update();
        // check if direction is down
        assertEquals("right",tvGuy.direction);
    }

    @Test
    void GameRestartAfterLosingWhenKeyPressed(){
        // fill in

    }
    @Test
    void GameRestartAfterWinningWhenKeyPressed(){
        // fill in

    }
    @Test
    void GameContinueAfterLosingWhenKeyPressed(){
        // fill in

    }
    @Test
    void GameContinueAfterWinningWhenKeyPressed(){
        // fill in
    }

    /**
     * Tests if the P key was pressed
     */
    @Test
    void GamePausesWhenPIsPressed(){
        KeyEvent key = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_P, 'P');
        gp.getKeyListeners()[0].keyPressed(key);
        assertEquals(gp.pauseState,gp.gameState);
    }

    /**
     * Tests if the I key was pressed
     */
    @Test
    void InstructionsDisplayedWhenIIsPressed(){
        KeyEvent key = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_I, 'I');
        gp.getKeyListeners()[0].keyPressed(key);
        assertEquals(gp.instructionsState,gp.gameState);
    }
}
