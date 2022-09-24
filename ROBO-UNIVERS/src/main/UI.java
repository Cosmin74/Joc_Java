package main;

import Entity.Entitate;
import Obiecte.Obiect_Viata;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UI {
    GameScreen gp;
    Font arial_40;
    Graphics g2;
    BufferedImage full_bar, half_bar, empty_bar;
    public int commandNum=0;

    public UI(GameScreen gp) throws IOException {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 20);
        arial_40 = new Font("Arial", Font.BOLD, 20);
        //Entitate viata = new Entitate(gp);
        full_bar = ImageIO.read(new File("D:\\PAOO\\Joculet2\\src\\Viata1\\intreg.png"));
        half_bar = ImageIO.read(new File("D:\\PAOO\\Joculet2\\src\\Viata1\\jumate.png"));
        empty_bar = ImageIO.read(new File("D:\\PAOO\\Joculet2\\src\\Viata1\\gol.png"));
    }

    public void draw(Graphics g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

            drawPlayerlife();

        if(gp.titlestate==0){
            drawTittleScreen();
        }
        else if (gp.gamestate == gp.pausestate && gp.menu==1) {
            drawTittleScreen();
        }

        else if(gp.gamerOver==1 && gp.gamestate==gp.pausestate){
            drawGameOveScreen();
        }

        else if (gp.gamestate == gp.pausestate && gp.flag1==1 && gp.gamerOver==0) {
           drawWin1Screen();
        }

        else if (gp.flag1 == 2 && gp.gamestate == gp.pausestate && gp.gamerOver==0) {
            drawWin2Screen();
        }

        else if (gp.flag1 == 3 && gp.gamestate == gp.pausestate && gp.gamerOver==0) {
            drawGameOveScreen();
        }

    }

    public void drawPlayerlife() {
        //gp.player.life=6;
        int x = 0;
        int y = 0;
        int i = 0;
        while (i < gp.player.maxlife / 2) {
            g2.drawImage(empty_bar, x, y, 100, 100, null);
            i++;
            x += gp.tilesize;
        }

        x = 0;
        y = 0;
        i = 0;
        while (i < gp.player.life) {
            g2.drawImage(half_bar, x, y, 100, 100, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(full_bar, x, y, 100, 100, null);
            }
            i++;
            x += gp.tilesize;
        }
    }

    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 90));
        String text = "PAUSED";
        int x = getXforCenteredText(text);

        int y = gp.screenheigth / 2;

        g2.drawString(text, x, y);
    }

    public void drawLevel2Screen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 90));
        String text = "Level II";
        int x = getXforCenteredText(text);
        int y = gp.screenheigth / 2;
        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenwidth / 2 - lenght / 2;
        return x;
    }

    public int getXforCenteredText1(String text) {
        int lenght = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = (gp.screenwidth + 2 * gp.tilesize) / 2 - lenght / 2;
        return x;
    }

    public void drawWin1Screen() {
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 90));
        String text = "YOU WON LEVEL I";
        String text1 = "LEVEL II NOW";
        int x = getXforCenteredText(text);
        int x1 = getXforCenteredText1(text1);
        int y = gp.screenheigth / 2;
        int y1 = (gp.screenheigth + 2 * gp.tilesize) / 2;
        g2.drawString(text, x, y);
        g2.drawString(text1, x1, y1);

    }

    public void drawWin2Screen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 90));
        String text = "YOU WON LEVEL II";
        String text1 = "FINAL LEVEL NOW";
        int x = getXforCenteredText(text);
        int x1 = getXforCenteredText(text1);
        int y = gp.screenheigth / 2;
        int y1 = (gp.screenheigth + 2 * gp.tilesize) / 2;

        g2.drawString(text, x, y);
        g2.drawString(text1, x1, y1);
    }

    public void drawWin3Screen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 90));
        String text = "YOU WON THE GAME";
        String text1 = "YOU ARE THE BEST";
        int x = getXforCenteredText(text);
        int x1 = getXforCenteredText(text1);
        int y = gp.screenheigth / 2;
        int y1 = (gp.screenheigth + 2 * gp.tilesize) / 2;

        g2.drawString(text, x, y);
        g2.drawString(text1, x1, y1);
    }

    public void drawGameOveScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenwidth,gp.screenheigth);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,110f));

        text="GAME OVER";
        x=getXforCenteredText(text);
        y=gp.tilesize*2;
        g2.drawString(text,x,y);

        g2.setColor(Color.gray);
        g2.drawString(text,x+7,y+7);

        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        g2.setFont(g2.getFont().deriveFont(50f));
        text="RETRY";
        x=getXforCenteredText(text);
        y+=gp.tilesize*2;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.drawString(">",x-gp.tilesize,y);
        }

        g2.setFont(g2.getFont().deriveFont(50f));
        text="QUIT";
        x=getXforCenteredText(text);
        y+=gp.tilesize;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.drawString(">",x-gp.tilesize,y);
        }
    }


    public void drawTittleScreen(){

        g2.setColor(new Color(20,20,120));
        g2.fillRect(0,0,gp.screenwidth,gp.screenheigth);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text="ROBO-UNIVERSE";
        int x = getXforCenteredText(text);
        int y=gp.tilesize*2;

        g2.setColor(Color.gray);
        g2.drawString(text,x+7,y+7);

        g2.setColor(Color.white);
        g2.drawString(text,x,y);


        text="NEW GAME";
        x=getXforCenteredText(text);
        y+=gp.tilesize*2;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.drawString(">",x- gp.tilesize,y);
        }


        text="LOAD GAME";
        x=getXforCenteredText(text);
        y+=gp.tilesize;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.drawString(">",x- gp.tilesize,y);
        }

        text="SAVE";
        x=getXforCenteredText(text);
        y+=gp.tilesize;
        g2.drawString(text,x,y);
        if(commandNum==2){
            g2.drawString(">",x- gp.tilesize,y);
        }

        text="QUIT";
        x=getXforCenteredText(text);
        y+=gp.tilesize;
        g2.drawString(text,x,y);
        if(commandNum==3){
            g2.drawString(">",x- gp.tilesize,y);
        }
    }
}
