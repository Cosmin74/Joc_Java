package Obiecte;

import Entity.Tun;
import main.GameScreen;

public class Obicet_Laser extends Tun {

    GameScreen gp;
    public Obicet_Laser(GameScreen gp){
        super(gp);
        this.gp=gp;

        name="Laser";
        speed=200;
        maxlife=6;
        life=maxlife;
        attack=2;
        alive=false;
        getImage();
    }

    public void getImage(){
        up1=setup("/laser/Tun4",gp.tilesize,gp.tilesize);
        down1=setup("/laser/Tun4",gp.tilesize,gp.tilesize);
        right1=setup("/laser/Tun4",gp.tilesize,gp.tilesize);
        left1=setup("/laser/Tun4",gp.tilesize,gp.tilesize);
    }
}
