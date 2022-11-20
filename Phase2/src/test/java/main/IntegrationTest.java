package main;

import Entities.MainCharacterTV;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    public GamePanel gp;// = new GamePanel();
    public KeyHandler kH;// = new KeyHandler(gp);
    public  MainCharacterTV mC;// = new MainCharacterTV(gp, kH);

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
//        gp.getKeyListeners()[0].keyPressed(enterKey);
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

}

