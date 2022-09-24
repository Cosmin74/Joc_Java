package Entity;

import Exceptii.ExceptiiCitire;
import Obiecte.Obicet_Laser;
import Obiecte.Obiect_Viata;
import main.GameScreen;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Entitate {

    public int x, y;
    public int speed;

    GameScreen gp;
    public BufferedImage image, image2, image3;
    public BufferedImage up1, down1, left1, right1, st;
    public BufferedImage up2, down2, left2, right2;

    public String direction = "up";
    public int spritecounter = 0;
    public int spriteNum = 1;
    public Rectangle solida = new Rectangle(0, 0, 0, 0);
    public Rectangle attackArea = new Rectangle(0, 95, 97, 97);
    public boolean collisionOn = false;
    public int solidadefaultx, solidadefaulty;
    public String name;
    public int actionLocker = 0;
    public int shotAviableCounter = 0;
    public int attack;
    public boolean alive;
    public int maxlife;
    public boolean dying = false;
    public int life;
    public int type;
    public int exp;
    public boolean invicible = false;
    public Tun tun;
    public Tun tun1;
    public Tun tun2;

    public Entitate(GameScreen gp) {
        this.gp = gp;

        solida = new Rectangle();
        solida.x = 125;
        solida.y = 125;
        solida.width = 125;
        solida.height = 125;
        solidadefaultx = solida.x;
        solidadefaulty = solida.y;
    }

    public void damagePlayer( ) {
            gp.player.life -= 1;
            if (gp.player.life == 0) {
                gameovercheck();
            }
    }

    public void gameovercheck() {
        gp.gamerOver=1;
        gp.gamestate=gp.pausestate;
        gp.playSE(3);
        gp.winGameMillis = System.currentTimeMillis();
        gp.stopMusic();
        gp.menu=2;
    }

        public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = right1;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = left1;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = down1;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = up1;
                }
            }
        }

        if (type == 2) {

            double onescale = (double) gp.tilesize / maxlife;
            double hpbarcvalue = onescale * life;

            g2.setColor(new Color(35, 35, 35));
            g2.fillRect(x + 10, y - 16, 82, 10);

            g2.setColor(new Color(255, 0, 0));
            g2.fillRect(x + 10, y - 15, (int) hpbarcvalue, 10);
        }

        g2.drawImage(image, x, y, gp.tilesize, gp.tilesize, null);
    }

    public void setAction() {
    }

    public void update() {
        setAction();

        collisionOn = false;
        gp.checker.chektile(this);
        gp.checker.checkEntity(this, gp.inamic);
        gp.checker.checkEntity(gp.player, gp.inamic);

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
