package Entity;


import Exceptii.ExceptiiBaza;
import Exceptii.ExceptiiCitire;
import Obiecte.Obicet_Laser;
import Obiecte.Obiect_Viata;
import main.GameScreen;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entitate {

    GameScreen gp;
    KeyHandler keyH;
    public int nr=0;

    public Player(GameScreen gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        solida = new Rectangle();
        solida.x = 0;
        solida.y = 95;
        solida.width = 98;
        solida.height = 98;
        solidadefaultx = solida.x;
        solidadefaulty = solida.y;
        attackArea.width = 97;
        attackArea.height = 97;
        setDefaultValues();
        getPlyerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 300;
        speed = 4;
        direction = "left";

        maxlife = 6;
        life = maxlife;
        invicible = false;

        tun = new Obicet_Laser(gp);
    }

    public void getPlyerImage() {
        try {
            up1 = setup("/res/miscare111", gp.tilesize, gp.tilesize);
            down1 = setup("/res/miscare112", gp.tilesize, gp.tilesize);
            left1 = setup("/res/miscare111", gp.tilesize, gp.tilesize);
            right1 = setup("/res/miscare112", gp.tilesize, gp.tilesize);

        } catch (ExceptiiCitire e) {
            e.printStackTrace();
        }

    }

    public BufferedImage setup(String imagePath, int width, int height) throws ExceptiiCitire {
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

    public void update() {


        if (keyH.up == true) {

            direction = "up";
            //y-=speed;
        } else if (keyH.dw == true) {
            //y+=speed;
            direction = "down";
        } else if (keyH.lft == true) {
            //x-=speed;
            direction = "left";
        } else if (keyH.rg == true) {
            ///x+=speed;
            direction = "right";
        }
        collisionOn = false;
        gp.checker.chektile(this);

        if(gp.currentMap==1 && gp.currentMap==2) {
            gp.checker.checkEntity(this, gp.inamic);
        }
        Boolean contact1=true;
        if(contact1==true){
            Boolean contact=gp.checker.checkInteresctWith(this);
        }


        gp.eHandler.checkEvent();

        if (keyH.up == true || keyH.dw == true || keyH.lft == true || keyH.rg == true) {

            if (!collisionOn) {
                switch (direction) {
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
                }
            }
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

        if (gp.keyH.shot == true && tun.alive == false) {
            tun.set(x, y, direction, true, this);
            gp.laserslist.add(tun);
            gp.playSE(5);
            nr++;
            if(gp.currentMap==1) {
                if (nr == 7) {
                    life -= 1;
                    nr = 0;
                    checkgmaeover();
                }
            }

           else if(gp.currentMap==2) {
                if (nr == 10) {
                    life -= 1;
                    nr = 0;
                    checkgmaeover();
                }
            }
           else if(gp.currentMap==3) {
                if (nr == 12) {
                    life -= 2;
                    nr = 0;
                    checkgmaeover();
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        //g2.setColor(Color.white);
        //g2.fillRect(x,y,gp.tilesize,gp.tilesize);

        BufferedImage image = left1;

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

        if (invicible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, x, y, gp.tilesize, gp.tilesize, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void damageInamic(int i) {
        if (i != 999) {
            gp.inamic[gp.currentMap][i].life -= 1;

            if (gp.inamic[gp.currentMap][i].life <= 0) {
                gp.inamic[gp.currentMap][i] = null;
                exp++;
                checklevelup();
            }
        }
    }

    public void damageInamic1() {
        gp.boss.life -= 1;
        if (gp.boss.life <= 0) {
            gp.boss.reset();
            exp++;
            checklevelup();
        }
    }


    public void checklevelup() {
        if (exp == 2 && gp.currentMap == 1) {
            gp.playSE(2);
            gp.flag1 = 1;
            gp.currentMap++;
            gp.gamestate = gp.pausestate;
            //gp.stopMusic();
            gp.player.life = gp.player.maxlife;
            gp.winGameMillis = System.currentTimeMillis();
            gp.setupGame();
            exp = 0;
        } else if (exp == 4 && gp.currentMap == 2) {
            gp.playSE(2);
            gp.flag1 = 2;
            gp.currentMap++;
            gp.gamestate = gp.pausestate;
            gp.player.life = gp.player.maxlife;
           // gp.stopMusic();
            gp.winGameMillis = System.currentTimeMillis();
            gp.setupGame();
            exp = 0;
        } else if (exp == 1 && gp.currentMap == 3) {
            gp.playSE(2);
            gp.flag1 = 3;
            //gp.currentMap++;
            gp.gamestate = gp.pausestate;
            gp.player.life = gp.player.maxlife;
           // gp.stopMusic();
            gp.winGameMillis = System.currentTimeMillis();
            //gp.setupGame();
            exp = 0;
            gp.menu=2;
        }
    }

    void checkgmaeover(){
        if(life==0){
            gp.playSE(3);
            gp.gamerOver=1;
            gp.gamestate=gp.pausestate;
            gp.menu=2;
            gp.player.life=gp.player.maxlife;
        }
    }
}
