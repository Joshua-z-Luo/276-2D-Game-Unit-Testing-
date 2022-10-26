package main;

import MainCharacter.Mobile;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Mobile character){
        int characterLeftX = character.x + character.solidArea.x;
        int characterRightX = character.x + character.solidArea.x + character.solidArea.width;
        int characterTopY = character.y + character.solidArea.y;
        int characterBottomY = character.y + character.solidArea.y + character.solidArea.height;

        int characterLeftCol = characterLeftX/gp.tileSize;
        int characterRightCol = characterRightX/gp.tileSize;
        int characterTopRow = characterTopY/gp.tileSize;
        int characterBottomRow = characterBottomY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(character.direction){
            case "up":
                characterTopRow = (characterTopY - character.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[characterLeftCol][characterTopRow];
                tileNum2 = gp.tileM.mapTileNum[characterRightCol][characterTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    character.collisionOn = true;
                }
                break;
            case "down":
                characterBottomRow = (characterBottomY + character.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[characterLeftCol][characterBottomRow];
                tileNum2 = gp.tileM.mapTileNum[characterRightCol][characterBottomRow];
                if(gp.tileM.tile[tileNum1].collision|| gp.tileM.tile[tileNum2].collision){
                    character.collisionOn = true;
                }
            case "left":
                characterLeftCol = (characterLeftX - character.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[characterLeftCol][characterTopRow];
                tileNum2 = gp.tileM.mapTileNum[characterLeftCol][characterBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    character.collisionOn = true;
                }
                break;
            case "right":
                characterRightCol = (characterRightX + character.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[characterRightCol][characterTopRow];
                tileNum2 = gp.tileM.mapTileNum[characterRightCol][characterBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                    character.collisionOn = true;
                }
                break;
        }

    }
}
