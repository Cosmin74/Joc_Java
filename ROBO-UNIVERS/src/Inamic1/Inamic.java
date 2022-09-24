package Inamic1;

import Entity.Entitate;
import Obiecte.Obiect_LaserI;
import main.GameScreen;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class Inamic extends Entitate {
    GameScreen gp;

    public Inamic(GameScreen gp) {
        super(gp);
        this.gp = gp;

        setDefaultValues();

        solida = new Rectangle();
        solida.x = 0;
        solida.y = 95;
        solida.width = 98;
        solida.height = 98;
        solidadefaultx = solida.x;
        solidadefaulty = solida.y;
        tun = new Obiect_LaserI(gp);

        getImage();
    }

    private void setDefaultValues() {
        name = "Inamic";
        type = 2; // e inamic
        direction = "left"; // first move
        speed = 2;
        life = 6;
        maxlife = 6;

        invicible = false;
        exp = 0;
    }

    public void getImage() {
        up1 = setup("/dusman/dusman1", gp.tilesize, gp.tilesize);
        down1 = setup("/dusman/dusman2", gp.tilesize, gp.tilesize);
        left1 = setup("/dusman/dusman1", gp.tilesize, gp.tilesize);
        right1 = setup("/dusman/dusman2", gp.tilesize, gp.tilesize);

    }

    public void setAction() {
        actionLocker++;
        if (actionLocker == 20) {

            Random random = new Random();
            int i = random.nextInt(200) + 1;

            if (i <= 50) {
                direction = "left";
            }
            if (i > 50 && i <= 100) {
                direction = "right";
            }
            if (i > 100 && i <= 150) {
                direction = "up";
            }
            if (i > 150 && i <= 200) {
                direction = "down";
            }

            actionLocker = 0;
        }

        shotAviableCounter++;
        int i = new Random().nextInt(100) + 1;
        if (i < 100 && !tun.alive && shotAviableCounter == 150) {
            tun.set(x, y, "left", true, this);
            gp.laserslist.add(tun);
            shotAviableCounter = 0;
        }
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaledImage(image, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
