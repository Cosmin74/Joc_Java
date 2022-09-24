package main;

import Entity.FinalBoss;
import Inamic1.Inamic;


public class AssetSetter {
    GameScreen gp;

    public AssetSetter(GameScreen gp) {
        this.gp = gp;
    }

    public void setInamicMap1() {
        gp.inamic[gp.currentMap][0] = new Inamic(gp);
        gp.inamic[gp.currentMap][0].x = 13 * gp.tilesize;
        gp.inamic[gp.currentMap][0].y = 4 * gp.tilesize;

        gp.inamic[gp.currentMap][1] = new Inamic(gp);
        gp.inamic[gp.currentMap][1].x = 13 * gp.tilesize;
        gp.inamic[gp.currentMap][1].y = 3 * gp.tilesize;
    }

    public void setInamicMap2() {
        gp.inamic[gp.currentMap][0] = new Inamic(gp);
        gp.inamic[gp.currentMap][0].x = 13 * gp.tilesize;
        gp.inamic[gp.currentMap][0].y = 4 * gp.tilesize;

        gp.inamic[gp.currentMap][1] = new Inamic(gp);
        gp.inamic[gp.currentMap][1].x = 13 * gp.tilesize;
        gp.inamic[gp.currentMap][1].y = 3 * gp.tilesize;

        gp.inamic[gp.currentMap][2] = new Inamic(gp);
        gp.inamic[gp.currentMap][2].x = 12 * gp.tilesize;
        gp.inamic[gp.currentMap][2].y = 3 * gp.tilesize;

        gp.inamic[gp.currentMap][3] = new Inamic(gp);
        gp.inamic[gp.currentMap][3].x = 12 * gp.tilesize;
        gp.inamic[gp.currentMap][3].y = 2 * gp.tilesize;

    }

    public void setInamicMap3() {
        gp.boss = FinalBoss.getInstance();
        gp.boss.x = 13 * gp.tilesize;
        gp.boss.y = 4 * gp.tilesize;

    }
}
