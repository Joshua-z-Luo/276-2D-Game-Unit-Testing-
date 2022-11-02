package main;

import Entities.Entity;
import Entities.MovingObject;
import Entities.StaticObject;

/**
 * This class checks the characters' position on the map and sees if it is touching any tiles that you should not walk through
 * @author Connor
 */
public class CollisionChecker {
    GamePanel gp;

    /**
     * Constructor that takes in the main GamePanel
     * @param gp GamePanel that will contain the game
     */
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    /**
     * This will check the tiles the character is on and make sure the character will not walk through walls or blocked areas
     * @param character The character that will be checked. Could be enemy or main character
     */



    public void checkWall(MovingObject character){
        int characterLeftX = character.x + character.solidArea.x;
        int characterRightX = character.x + character.solidArea.x + character.solidArea.width;
        int characterTopY = character.y + character.solidArea.y;
        int characterBottomY = character.y + character.solidArea.y + character.solidArea.height;

        int characterLeftCol = characterLeftX/gp.tileSize;
        int characterRightCol = characterRightX/gp.tileSize;
        int characterTopRow = characterTopY/gp.tileSize;
        int characterBottomRow = characterBottomY/gp.tileSize;

        int wallNum1, wallNum2;

        switch(character.direction){
            case "up":
                characterTopRow = (characterTopY - character.speed)/gp.tileSize;
                wallNum1 = gp.wallM.mapWallNum[characterLeftCol][characterTopRow];
                wallNum2 = gp.wallM.mapWallNum[characterRightCol][characterTopRow];
                if(gp.wallM.wall[wallNum1].collision || gp.wallM.wall[wallNum2].collision){
                    character.collisionOn = true;
                }
                break;
            case "down":
                characterBottomRow = (characterBottomY + character.speed)/gp.tileSize;
                wallNum1 = gp.wallM.mapWallNum[characterLeftCol][characterBottomRow];
                wallNum2 = gp.wallM.mapWallNum[characterRightCol][characterBottomRow];
                if(gp.wallM.wall[wallNum1].collision|| gp.wallM.wall[wallNum2].collision){
                    character.collisionOn = true;
                }
            case "left":
                characterLeftCol = (characterLeftX - character.speed)/gp.tileSize;
                wallNum1 = gp.wallM.mapWallNum[characterLeftCol][characterTopRow];
                wallNum2 = gp.wallM.mapWallNum[characterLeftCol][characterBottomRow];
                if(gp.wallM.wall[wallNum1].collision || gp.wallM.wall[wallNum2].collision){
                    character.collisionOn = true;
                }
                break;
            case "right":
                characterRightCol = (characterRightX + character.speed)/gp.tileSize;
                wallNum1 = gp.wallM.mapWallNum[characterRightCol][characterTopRow];
                wallNum2 = gp.wallM.mapWallNum[characterRightCol][characterBottomRow];
                if(gp.wallM.wall[wallNum1].collision || gp.wallM.wall[wallNum2].collision){
                    character.collisionOn = true;
                }
                break;
        }


    }

    public int checkObject(MovingObject mobile, boolean player){
        int index = 999;

        for(int i = 0;i<gp.obj.length;i++){
            if(gp.obj[i]!=null){
                //Get MainCharacter.Entity's solid area position
                mobile.solidArea.x = mobile.x + mobile.solidArea.x;
                mobile.solidArea.y = mobile.y + mobile.solidArea.y;
                //Get the Entities.object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].x + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].y + gp.obj[i].solidArea.y;

                switch(mobile.direction){
                    case "up":
                        mobile.solidArea.y -= mobile.speed;
                        if(mobile.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collisionOn==true){
                                mobile.collisionOn=true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        mobile.solidArea.y += mobile.speed;
                        if(mobile.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collisionOn==true){
                                mobile.collisionOn=true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        mobile.solidArea.x -= mobile.speed;
                        if(mobile.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collisionOn==true){
                                mobile.collisionOn=true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        mobile.solidArea.x += mobile.speed;
                        if(mobile.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collisionOn==true){
                                mobile.collisionOn=true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                }
                mobile.solidArea.x = mobile.solidAreaDefaultX;
                mobile.solidArea.y = mobile.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }
        }
        return index;
    }

    public int checkEntity(MovingObject mobile, MovingObject[] target){
        int index = 999;

        for(int i = 0;i<target.length;i++){
            if(target[i]!=null){
                //Get MainCharacter.Entity's solid area position
                mobile.solidArea.x = mobile.x + mobile.solidArea.x;
                mobile.solidArea.y = mobile.y + mobile.solidArea.y;
                //Get the Entities.object's solid area position
                target[i].solidArea.x = target[i].x + target[i].solidArea.x;
                target[i].solidArea.y = target[i].y + target[i].solidArea.y;

                switch(mobile.direction){
                    case "up":
                        mobile.solidArea.y -= mobile.speed;
                        if(mobile.solidArea.intersects(target[i].solidArea)){
//                            System.out.println("up collision!!!");
                            if(target[i]!=mobile){
                                mobile.collisionOn=true;
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        mobile.solidArea.y += mobile.speed;
                        if(mobile.solidArea.intersects(target[i].solidArea)){
//                            System.out.println("down collision!!!");
                            if(target[i]!=mobile){
                                mobile.collisionOn=true;
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        mobile.solidArea.x -= mobile.speed;
                        if(mobile.solidArea.intersects(target[i].solidArea)){
//                            System.out.println("left collision!!!");
                            if(target[i]!=mobile){
                                mobile.collisionOn=true;
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        mobile.solidArea.x += mobile.speed;
                        if(mobile.solidArea.intersects(target[i].solidArea)){
//                            System.out.println("right collision!!!");
                            if(target[i]!=mobile){
                                mobile.collisionOn=true;
                                index = i;
                            }
                        }
                        break;
                }
                mobile.solidArea.x = mobile.solidAreaDefaultX;
                mobile.solidArea.y = mobile.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }
        }
        return index;
    }
    public void checkPlayer(MovingObject mobile){

        //Get MainCharacter.Entity's solid area position
        mobile.solidArea.x = mobile.x + mobile.solidArea.x;
        mobile.solidArea.y = mobile.y + mobile.solidArea.y;
        //Get the Entities.object's solid area position
        gp.tvGuy.solidArea.x = gp.tvGuy.x + gp.tvGuy.solidArea.x;
        gp.tvGuy.solidArea.y = gp.tvGuy.y + gp.tvGuy.solidArea.y;

        switch(mobile.direction){
            case "up":
                mobile.solidArea.y -= mobile.speed;
                if(mobile.solidArea.intersects(gp.tvGuy.solidArea)){
                    System.out.println("up collision???");
                    gp.tvGuy.life = 0;
//                    if(gp.tvGuy!=mobile){
                        mobile.collisionOn=true;
//                    }
                }
                break;
            case "down":
                mobile.solidArea.y += mobile.speed;
                if(mobile.solidArea.intersects(gp.tvGuy.solidArea)){
                    System.out.println("down collision???");
                    gp.tvGuy.life = 0;
//                    if(gp.tvGuy!=mobile){
                        mobile.collisionOn=true;
//                    }
                }
                break;
            case "left":
                mobile.solidArea.x -= mobile.speed;
                if(mobile.solidArea.intersects(gp.tvGuy.solidArea)){
                    System.out.println("left collision???");
                    gp.tvGuy.life = 0;
//                    if(gp.tvGuy!=mobile){
                        mobile.collisionOn=true;
//                    }
                }
                break;
            case "right":
                mobile.solidArea.x += mobile.speed;
                if(mobile.solidArea.intersects(gp.tvGuy.solidArea)){
                    System.out.println("right collision???");
                    gp.tvGuy.life = 0;
//                    if(gp.tvGuy!=mobile){
                        mobile.collisionOn=true;
//                    }
                }
                break;
        }
        mobile.solidArea.x = mobile.solidAreaDefaultX;
        mobile.solidArea.y = mobile.solidAreaDefaultY;
        gp.tvGuy.solidArea.x = gp.tvGuy.solidAreaDefaultX;
        gp.tvGuy.solidArea.y = gp.tvGuy.solidAreaDefaultY;

    }
}

