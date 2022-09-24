package Obiecte;

import Entity.Tun;
import main.GameScreen;

public class Obiect_LaserB extends Tun {
    GameScreen gp;
    public Obiect_LaserB(GameScreen gp){
        super(gp);
        this.gp=gp;

        name="LaserBoss";
        speed=200;
        maxlife=30;
        life=maxlife;
        attack=2;
        alive=false;
        getImage();
        getImage1();
    }

    public void getImage(){
        up1=setup("/laser/Tun3",gp.tilesize,gp.tilesize);
        down1=setup("/laser/Tun3",gp.tilesize,gp.tilesize);
        right1=setup("/laser/Tun3",gp.tilesize,gp.tilesize);
        left1=setup("/laser/Tun3",gp.tilesize,gp.tilesize);
    }

    public void getImage1(){
        up2=setup("/laser/Tun1",gp.tilesize,gp.tilesize);
        down2=setup("/laser/Tun1",gp.tilesize,gp.tilesize);
        right2=setup("/laser/Tun1",gp.tilesize,gp.tilesize);
        left2=setup("/laser/Tun1",gp.tilesize,gp.tilesize);
    }
}
