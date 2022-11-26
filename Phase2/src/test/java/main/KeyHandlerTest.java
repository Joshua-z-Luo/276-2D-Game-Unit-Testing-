package main;

import Entities.MainCharacterTV;
import org.junit.jupiter.api.*;
import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the KayHandler
 * Tests include testing certain keys if they are pressed and that they are registered by the game
 * @author Rose, Connor, Hayato, Joshua
 */
public class KeyHandlerTest {
    public GamePanel gp = new GamePanel();
    public KeyHandler kH = new KeyHandler(gp);
    public MainCharacterTV tvGuy = new MainCharacterTV(gp,kH);

    @BeforeEach
    public void init(){
        gp.setUpGame();
        gp.gameState = gp.playState;
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


    /**
     * Tests if the P key was pressed
     */
    @Test
    void GamePausesWhenPIsPressed(){
        assertEquals(gp.gameState, gp.playState);
        KeyEvent key = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_P, 'P');
        gp.getKeyListeners()[0].keyPressed(key);
        kH.keyPressed(key);
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
        kH.keyPressed(key);
        assertEquals(gp.instructionsState, gp.gameState);
    }
}
