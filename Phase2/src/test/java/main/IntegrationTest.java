package main;

import Entities.MainCharacterTV;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration tests for various interactions in our game
 * Test include interactions between the UI, KeyHandler and the GamePanel
 * @author Connor, Joshua, Rose, Hayato
 */
public class IntegrationTest {
    public GamePanel gp;
    public KeyHandler kH;
    public  MainCharacterTV mC;

    @BeforeEach
    public void init() {
        gp = new GamePanel();
        kH = new KeyHandler(gp);
        mC = new MainCharacterTV(gp, kH);
        gp.setUpGame();
        gp.startGameThread();
        gp.ui.commandNum = 0;
    }

    @AfterEach
    public void cleanup(){
        gp = null;
        kH = null;
        mC = null;
    }

    /**
     * Tests the KeyHandler and the UI is interacting as expected in the main menu
     * Tests if the player can go through the options in the main menu
     */
    @Test
    void KeyHandlerandUIInteractionsWithOptionsInMainMenu() {
        assertEquals(gp.titleState, gp.gameState);

        assertEquals(0, gp.ui.commandNum);
        KeyEvent downKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_S, 'S');
        kH.keyPressed(downKey);
        assertEquals(1, gp.ui.commandNum);

        KeyEvent upKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_W, 'W');
        kH.keyPressed(upKey);
        assertEquals(0, gp.ui.commandNum);
    }

    /**
     * Tests the interaction between the KeyHandler and the UI in the main menu
     * Tests if the player can press enter when the Play option is selected and then checks if the game state is changed to play state
     */
    @Test
    void KeyHandlerUIandGamePanelInteractionsWhenPlayEntered(){
        assertEquals(gp.titleState, gp.gameState);
        assertEquals(0, gp.ui.commandNum);
        KeyEvent enterKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_ENTER, 'e');
        kH.keyPressed(enterKey);
        assertEquals(gp.playState, gp.gameState);
    }

    /**
     * Tests the interaction between the KeyHandler and the UI in the lose screen
     * Tests if the player can go through the lose options
     */
    @Test
    void KeyHandlerandUIInteractionsWhenLoseState(){
        gp.gameState = gp.playState;
        mC.life = 0;
        mC.update();
        assertEquals(gp.loseState, gp.gameState);
        KeyEvent downKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_S, 'S');
        kH.keyPressed(downKey);
        assertEquals(1, gp.ui.commandNum);

        KeyEvent upKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_W, 'W');
        kH.keyPressed(upKey);
        assertEquals(0, gp.ui.commandNum);
    }

    /**
     * Tests when the game is in lose state that the player can press enter on the retry option and the game will go back to play state (running game screen)
     */
    @Test
    void KeyHandlerUIandGamePanelInteractionsWhenLoseStateandRetryEntered(){
        gp.gameState = gp.playState;
        mC.life = 0;
        mC.update();
        assertEquals(gp.loseState, gp.gameState);
        assertEquals(0, gp.ui.commandNum);
        KeyEvent enterKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_ENTER, 'e');
        kH.keyPressed(enterKey);
        assertEquals(gp.playState, gp.gameState);
    }

    /**
     * Tests when the game is in lose state that the player can go to the exit option and press enter and the game will go to the title state (title screen)
     */
    @Test
    void KeyHandlerUIandGamePanelInteractionsWhenLoseStateandExitEntered(){
        gp.gameState = gp.playState;
        mC.life = 0;
        mC.update();
        assertEquals(gp.loseState, gp.gameState);
        KeyEvent downKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_S, 'S');
        kH.keyPressed(downKey);
        assertEquals(1, gp.ui.commandNum);
        KeyEvent enterKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_ENTER, 'e');
        kH.keyPressed(enterKey);
        assertEquals(gp.titleState, gp.gameState);
    }

    /**
     * Tests the interaction between the KeyHandler and the UI in the win screen
     * Tests if the player can go through the win options
     */
    @Test
    void KeyHandlerandUIInteractionsWhenWinState(){
        gp.gameState = gp.winState;
        assertEquals(gp.winState, gp.gameState);
        KeyEvent downKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_S, 'S');
        kH.keyPressed(downKey);
        assertEquals(1, gp.ui.commandNum);
        KeyEvent enterKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_ENTER, 'e');
        kH.keyPressed(enterKey);
        assertEquals(gp.titleState, gp.gameState);
    }

    /**
     * Tests when the game is in win state that the player can press enter on continue game option and the game will continue to the next level(running game screen/play state)
     */
    @Test
    void KeyHandlerUIandGamePanelInteractionsWhenWinStateContinueOption(){
        gp.gameState = gp.winState;
        assertEquals(gp.winState, gp.gameState);
        assertEquals(0, gp.ui.commandNum);
        KeyEvent enterKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_ENTER, 'e');
        kH.keyPressed(enterKey);
        assertEquals(1,gp.level);
        assertEquals(gp.playState, gp.gameState);
    }

    /**
     * Tests when the game is in win state that the player can press enter on the return to main menu option and the game will go back to the main menu (title state)
     */
    @Test
    void KeyHandlerUIandGamePanelInterationsWhenWinStateExitOption(){
        gp.gameState = gp.winState;
        mC.update();
        assertEquals(gp.winState, gp.gameState);
        KeyEvent downKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_S, 'S');
        kH.keyPressed(downKey);
        assertEquals(1, gp.ui.commandNum);
        KeyEvent enterKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_ENTER, 'e');
        kH.keyPressed(enterKey);
        assertEquals(gp.titleState, gp.gameState);
    }

    /**
     * Tests if the player can start the game, then move the character around and also pause the game and resume the game.
     */
    @Test
    void KeyHandlerUIandGamePanelInteractionsStartGameandPause(){
        int x = mC.x;
        int y = mC.y;
        assertEquals(gp.titleState, gp.gameState);
        assertEquals(0, gp.ui.commandNum);
        KeyEvent enterKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_ENTER, 'e');
        kH.keyPressed(enterKey);
        assertEquals(gp.playState, gp.gameState);

        KeyEvent upKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_W, 'W');
        KeyEvent rightKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_D, 'D');
        KeyEvent downKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_S, 'S');
        KeyEvent leftKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_A, 'A');
        KeyEvent pKey = new KeyEvent(gp, KeyEvent.KEY_PRESSED, System.currentTimeMillis(),
                0, KeyEvent.VK_P, 'P');

        kH.keyPressed(upKey);
        mC.update();
        assertEquals(y - 4, mC.y);
        kH.keyReleased(upKey);

        kH.keyPressed(downKey);
        mC.update();
        assertEquals(y, mC.y);
        kH.keyReleased(downKey);

        kH.keyPressed(rightKey);
        mC.update();
        assertEquals(x + 4, mC.x);
        kH.keyReleased(rightKey);

        kH.keyPressed(leftKey);
        mC.update();
        assertEquals(x, mC.x);
        kH.keyReleased(leftKey);

        kH.keyPressed(pKey);
        assertEquals(gp.pauseState, gp.gameState);

        kH.keyPressed(pKey);
        assertEquals(gp.playState, gp.gameState);
    }

}

