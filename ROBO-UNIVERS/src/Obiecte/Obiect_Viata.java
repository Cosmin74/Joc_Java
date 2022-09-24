package Obiecte;

import Entity.Entitate;
import main.GameScreen ;

public class Obiect_Viata extends Entitate {
    GameScreen gp;
    public Obiect_Viata(GameScreen gp){
        super(gp);
        name="Viata";
        image=setup("D:\\PAOO\\Joculet2\\src\\Viata1\\intreg",gp.tilesize,gp.tilesize);
        image2=setup("D:\\PAOO\\Joculet2\\src\\Viata1\\jumatate",gp.tilesize,gp.tilesize);
        image3=setup("D:\\PAOO\\Joculet2\\src\\Viata1\\gol",gp.tilesize,gp.tilesize);

    }

}
