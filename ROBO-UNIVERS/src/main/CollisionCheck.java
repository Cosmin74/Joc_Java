package main;

import Entity.Entitate;

import java.lang.reflect.GenericArrayType;

public class CollisionCheck {

    GameScreen gp;

    public CollisionCheck(GameScreen gp) {
        this.gp = gp;
    }

    public void chektile(Entitate entity) {
        int entityleftx = entity.x + entity.solida.x;
        int entityrightx = entity.x + entity.solida.x + entity.solida.width;
        int entitytopy = entity.y + entity.solida.y;
        int entitybottomy = entity.y + entity.solida.y - entity.solida.height;

        int entityleftcol = entityleftx / gp.tilesize;
        int entityrightcol = entityrightx / gp.tilesize;
        int entitytoprow = entitytopy / gp.tilesize;
        int entitybottomrow = entitybottomy / gp.tilesize;

        int tilenum1, tilenum2;

        switch (entity.direction) {
            case "up" -> {
                entitytoprow = (entitybottomy - entity.speed) / gp.tilesize;
                tilenum1 = gp.tileM.mapTileNum[gp.currentMap][entityleftcol][entitytoprow];
                tilenum2 = gp.tileM.mapTileNum[gp.currentMap][entityrightcol][entitytoprow];
                if (gp.tileM.tile[tilenum1].collision || gp.tileM.tile[tilenum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entitybottomrow = (entitytopy + entity.speed) / gp.tilesize;
                tilenum1 = gp.tileM.mapTileNum[gp.currentMap][entityleftcol][entitybottomrow];
                tilenum2 = gp.tileM.mapTileNum[gp.currentMap][entityrightcol][entitybottomrow];
                if (gp.tileM.tile[tilenum1].collision || gp.tileM.tile[tilenum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entityleftcol = (entityleftx - entity.speed) / gp.tilesize;
                tilenum1 = gp.tileM.mapTileNum[gp.currentMap][entityleftcol][entitytoprow];
                tilenum2 = gp.tileM.mapTileNum[gp.currentMap][entityleftcol][entitybottomrow];
                if (gp.tileM.tile[tilenum1].collision || gp.tileM.tile[tilenum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityrightcol = (entityrightx - entity.speed) / gp.tilesize;
                tilenum1 = gp.tileM.mapTileNum[gp.currentMap][entityrightcol][entitytoprow];
                tilenum2 = gp.tileM.mapTileNum[gp.currentMap][entityrightcol][entitybottomrow];
                if (gp.tileM.tile[tilenum1].collision || gp.tileM.tile[tilenum2].collision) {
                    entity.collisionOn = true;
                }
            }
        }
    }
    /*
    param = 3
    for(i - 0 i < 10l; ii {
        i != param
    }
     */


    public int checkEntity(Entitate entity, Entitate[][] target) {
        int index = 999;
        for (int i = 0; i < target[0].length; i++) {
            if (target[gp.currentMap][i] != null && target[gp.currentMap][i]!=entity) {

                entity.solida.x = entity.x + entity.solida.x;
                entity.solida.y = entity.y + entity.solida.y;
                target[gp.currentMap][i].solida.x = target[gp.currentMap][i].x + target[gp.currentMap][i].solida.x;
                target[gp.currentMap][i].solida.y = target[gp.currentMap][i].y + target[gp.currentMap][i].solida.y;

                switch (entity.direction) {
                    case "up" -> entity.solida.y -= entity.speed;
                    case "down" -> entity.solida.y += entity.speed;
                    case "left" -> entity.solida.x -= entity.speed;
                    case "right" -> entity.solida.x += entity.speed;
                }

                if (entity.solida.intersects(target[gp.currentMap][i].solida)) {
//                    if (entity.solida.intersects(target[gp.currentMap][i].solida)) {
                    entity.collisionOn = true;
                    index = i;
//                    }
                }
                entity.solida.x = entity.solidadefaultx;
                entity.solida.y = entity.solidadefaulty;
                target[gp.currentMap][i].solida.x = target[gp.currentMap][i].solidadefaultx;
                target[gp.currentMap][i].solida.y = target[gp.currentMap][i].solidadefaulty;
            }
        }
        return index;
    }


    public boolean checkInteresctWith(Entitate entity) {

        boolean contactBoss = false;

        entity.solida.x = entity.x + entity.solida.x;
        entity.solida.y = entity.y + entity.solida.y;
        gp.boss.solida.x = gp.boss.x + gp.boss.solida.x;
        gp.boss.solida.y = gp.boss.y + gp.boss.solida.y;

        switch (entity.direction) {
            case "up" -> entity.solida.y -= entity.speed;
            case "down" -> entity.solida.y += entity.speed;
            case "left" -> entity.solida.x -= entity.speed;
            case "right" -> entity.solida.x += entity.speed;
        }
        if (entity.solida.intersects(gp.boss.solida)) {
            contactBoss = true;
        }

        entity.solida.x = entity.solidadefaultx;
        entity.solida.y = entity.solidadefaulty;
        gp.boss.solida.x = gp.boss.solidadefaultx;
        gp.boss.solida.y = gp.boss.solidadefaulty;

        return contactBoss;
    }

    public boolean checkInteresctWith1(Entitate entity) {

        boolean contactBoss = false;

        entity.solida.x = entity.x + entity.solida.x;
        entity.solida.y = entity.y + entity.solida.y;
        gp.player.solida.x = gp.player.x + gp.player.solida.x;
        gp.player.solida.y = gp.player.y + gp.player.solida.y;

        switch (entity.direction) {
            case "up" -> entity.solida.y -= entity.speed;
            case "down" -> entity.solida.y += entity.speed;
            case "left" -> entity.solida.x -= entity.speed;
            case "right" -> entity.solida.x += entity.speed;
        }
        if (entity.solida.intersects(gp.player.solida)) {
            contactBoss = true;
        }

        entity.solida.x = entity.solidadefaultx;
        entity.solida.y = entity.solidadefaulty;
        gp.player.solida.x = gp.player.solidadefaultx;
        gp.player.solida.y = gp.player.solidadefaulty;

        return contactBoss;
    }
}
