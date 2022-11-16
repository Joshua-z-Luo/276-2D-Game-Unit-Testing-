package main;

import Entities.MainCharacterTV;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionCheckerTest {
    static GamePanel gp = new GamePanel();
    static KeyHandler kH = new KeyHandler(gp);
    static MainCharacterTV tvGuy =   new MainCharacterTV(gp,kH);
    static AssetSetter aSetter = new AssetSetter(gp);

    static CollisionChecker cChecker = new CollisionChecker(gp);

    @BeforeAll
    static void init(){
        //initiate monsters
        gp.setUpGame();
        gp.startGameThread();
        gp.gameState = gp.playState;

    }

    @Test
    void testCheckIfMainCharacterCollidesWithMonsterAt350350(){
        tvGuy.x = 350;
        tvGuy.y = 360;
        tvGuy.direction = "up";
        int result =  cChecker.checkEntity(tvGuy, gp.monster);
        //if result is 999, no collision
        //else, collision
        //So this assertion assume collision between tvGuy and a monster at 350, 350
    assertNotEquals(999,result);
    }

    @Test
    void testCheckIfMainCharacterCollidesWithMonsterAt150150(){
        tvGuy.x = 150;
        tvGuy.y = 160;
        tvGuy.direction = "up";
        int result =  cChecker.checkEntity(tvGuy, gp.monster);
        //if result is 999, no collision
        //else, collision
        //So this assertion assume collision between tvGuy and a monster at 350, 350
        assertNotEquals(999,result);

    }

    @Test
    void testCheckIfMainCharacterCollidesWithMonsterAt350100(){
        tvGuy.x = 350;
        tvGuy.y = 110;
        tvGuy.direction = "up";

        int result =  cChecker.checkEntity(tvGuy, gp.monster);

        assertNotEquals(999,result);

    }

    @Test
    void testCheckIfMainCharacterCollidesWithMonsterAt1000350(){
        tvGuy.x = 1000;
        tvGuy.y = 350;
        tvGuy.direction = "up";

        int result =  cChecker.checkEntity(tvGuy, gp.monster);

        assertNotEquals(999,result);

    }

    @Test
    void mainCharacterCanReachDoor(){
        tvGuy.keyCardCount = 3;
        tvGuy.x = 1050;
        tvGuy.y = 700;
        int result =  cChecker.checkObject(tvGuy, true);
        assertEquals(7, result);
    }
}