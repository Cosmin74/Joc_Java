package Entity;

import Exceptii.ExceptiiBaza;
import Exceptii.ExceptiiCitire;
import Obiecte.Obiect_LaserB;
import Obiecte.Obiect_LaserI;
import main.GameScreen;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class FinalBoss extends Entitate {
     GameScreen gp;

    private static FinalBoss  Boss = null;

    private FinalBoss(GameScreen gp) {
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
        tun1 = new Obiect_LaserB(gp);
        tun2= new Obiect_LaserB(gp);

        getImage();
        getImage1();
    }

    public static FinalBoss getInstance() {
        if(Boss==null){
            try{
                Boss=new FinalBoss(GameScreen.getInstance());
            } catch (Exception e){

            }
        }
        return Boss;
    }

    public void reset(){
        Boss=null;
    }


    private void setDefaultValues() {
        name = "Inamic";
        type = 3; // e inamic
        direction = "left"; // first move
        speed = 2;
        life = 30;
        maxlife = 30;

        invicible = false;
        exp = 0;
    }

    public void getImage() {
        up1 = setup("/BossFinal/Boss1", gp.tilesize, gp.tilesize);
        down1 = setup("/BossFinal/Boss2", gp.tilesize, gp.tilesize);
        left1 = setup("/BossFinal/Boss1", gp.tilesize, gp.tilesize);
        right1 = setup("/BossFinal/Boss2", gp.tilesize, gp.tilesize);

    }

    public void getImage1() {
        up2 = setup("/BossFinal/Boss1", gp.tilesize, gp.tilesize);
        down2 = setup("/BossFinal/Boss2", gp.tilesize, gp.tilesize);
        left2 = setup("/BossFinal/Boss1", gp.tilesize, gp.tilesize);
        right2 = setup("/BossFinal/Boss2", gp.tilesize, gp.tilesize);

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
        if (i < 100 && !tun1.alive && !tun2.alive && shotAviableCounter == 300) {
            tun1.set(x, y, "left", true, this);
            tun2.set(x+20, y+20, "left", true, this);
            gp.laserslist.add(tun1);
            gp.laserslist.add(tun2);
            shotAviableCounter = 0;
        }
    }

    public void update() {
        setAction();

        collisionOn = false;
        gp.checker.chektile(this);
        Boolean contact=gp.checker.checkInteresctWith1(this);
        if(contact){

        }
        if (!collisionOn) {
            switch (direction) {
                case "up" -> y -= speed;
                case "down" -> y += speed;
                case "left" -> x -= speed;
                case "right" -> x += speed;
            }
        } else {
            direction = Arrays.asList("up", "down", "left", "right").get(new Random().nextInt(4));
        }

        spritecounter++;
        if (spritecounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spritecounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        //g2.setColor(Color.white);
        //g2.fillRect(x,y,gp.tilesize,gp.tilesize);

        BufferedImage image = left1;
        BufferedImage image1 = left1;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                    image1=up2;
                }
                if (spriteNum == 2) {
                    image = right1;
                    image1=right2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                    image1=down2;
                }
                if (spriteNum == 2) {
                    image = left1;
                    image1=left2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                    image1=left2;
                }
                if (spriteNum == 2) {
                    image = down1;
                    image1=down2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                    image1=right2;
                }
                if (spriteNum == 2) {
                    image = up1;
                    image1=up2;
                }
            }
        }

        if (type == 3) {

            double onescale = (double) gp.tilesize / maxlife;
            double hpbarcvalue = onescale * life;

            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(x + 9, y - 16, 82, 10);

            g2.setColor(new Color(255, 0, 0));
            g2.fillRect(x + 10, y - 15, (int) hpbarcvalue, 10);
        }

        g2.drawImage(image, x, y, gp.tilesize, gp.tilesize, null);
        g2.drawImage(image1, x, y, gp.tilesize, gp.tilesize, null);
    }

    public void damagePlayer( ) {
        gp.player.life -= 2;
        if (gp.player.life <= 0) {
            gameovercheck();
        }
    }

    public void gameovercheck() {
        gp.gamerOver=1;
        gp.gamestate=gp.pausestate;
        gp.playSE(3);
        gp.winGameMillis = System.currentTimeMillis();
        gp.menu=2;
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaledImage(image, width, height);

        }catch(IllegalArgumentException ex){
            throw new ExceptiiCitire();
        }
        catch (IOException e) {
            throw new ExceptiiCitire();
        }
            return image;
    }

}
