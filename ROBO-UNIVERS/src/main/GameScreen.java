package main;

import Entity.Entitate;
import Entity.FinalBoss;
import Entity.Player;
import Exceptii.ExceptiiBaza;
import Inamic1.Inamic;
import tile.TileManager;

import java.awt.*;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;

import static java.lang.System.exit;

public class GameScreen extends JPanel implements Runnable {
    final int originaltile = 100;
    final int scale = 1;

    public final int tilesize = originaltile * scale;
    public final int maxcol = 15;
    public final int maxrow = 8;
    final int screenwidth = tilesize * maxcol;
    final int screenheigth = tilesize * maxrow;

    public final int maxworldcol = 50;
    public final int maxworldrow = 50;
    public final int worldwidth = tilesize * maxworldcol;
    public final int worldheight = tilesize * maxworldrow;
    public final int maxMap = 3;
    public int currentMap = 1;

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public Thread gameThread;
    public CollisionCheck checker = new CollisionCheck(this);
    public Player player = new Player(this, keyH);
    public Sound sound = new Sound();
    public Sound se = new Sound();
    public FinalBoss boss;
    public Entitate obj[][]=new Entitate[maxMap][20];
    public Entitate inamic[][] = new Entitate[maxMap][20];
    public AssetSetter aSetter = new AssetSetter(this);
    public EventHandler eHandler = new EventHandler(this);
    ArrayList<Entitate> entitylist = new ArrayList<>();
    public ArrayList<Entitate> laserslist = new ArrayList<>();
    public UI ui = new UI(this);


    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    public int gamestate;
    public final int playstate = 1;
    public final int pausestate = 2;
    public int flag1 = 0;
    public int titlestate=0;
    public int menu=0;
    public long winGameMillis;
    public boolean isWaiting;
    public int gamerOver=0;

    public void setupGame() {
        boss = FinalBoss.getInstance();

        switch (currentMap) {
            case 1 -> aSetter.setInamicMap1();
            case 2 -> aSetter.setInamicMap2();
            case 3 -> aSetter.setInamicMap3();
        }
    }

    private static GameScreen gamepanel=null;

    public static GameScreen getInstance() throws IOException{
        if(gamepanel==null){
            gamepanel=new GameScreen();
        }
        return gamepanel;
    }

    private GameScreen() throws IOException {
        this.setPreferredSize(new Dimension(screenwidth, screenheigth));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000 / FPS;
        double delta = 0;
        long lasttime = System.nanoTime();
        long currenttime;
        long timer = 0;
        int draw = 0;

        while (gameThread != null) {
            currenttime = System.nanoTime();
            delta += (currenttime - lasttime) / interval;
            timer += (currenttime - lasttime);
            lasttime = currenttime;

            if (delta >= 1) {

                update();
                repaint();
                delta--;
                draw++;
            }

            if (timer >= 1000000000) {
                draw = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        if (gamestate == playstate) {
            player.update();
            if(currentMap==1 || currentMap==2){
            for (int i = 0; i < inamic[currentMap].length; i++) {
                if (inamic[currentMap][i] != null) {
                    inamic[currentMap][i].update();
                }
            }

            }
            if(currentMap==3){
                boss.update();
            }

            for (int i = 0; i < laserslist.size(); i++) {
                if (laserslist.get(i) != null) {
                    if (laserslist.get(i).alive) {
                        laserslist.get(i).update();
                    }
                    if (!laserslist.get(i).alive)
                        laserslist.remove(i);
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if ( gamestate == pausestate && (System.currentTimeMillis() - winGameMillis < 5000 || menu == 1 || menu == 2)) {
            ui.draw(g2);
        } else {
            // because it starts with 0
            gamestate = playstate;
            player.draw(g2);
            switch (currentMap) {
                case 1 -> tileM.drawMap1(g2);
                case 2 -> tileM.drawMap2(g2);
                case 3 -> tileM.drawMap3(g2);
            }
            if (currentMap == 3) {
                boss.draw(g2);
            }

            entitylist.add(player);
            if (currentMap == 3) {
                 entitylist.add(boss);
            }
            if(currentMap!=3) {
            for (int i = 0; i < inamic[1].length; i++) {
                   if (inamic[currentMap][i] != null) {
                       entitylist.add(inamic[currentMap][i]);
                   }
               }
            }


            for (int i = 0; i < laserslist.size(); i++) {
                if (laserslist.get(i) != null) {
                    entitylist.add(laserslist.get(i));
                }
            }

            entitylist.sort((o1, o2) -> Integer.compare(o1.y, o2.x));

            for (Entitate entitate : entitylist) {
                entitate.draw(g2);
            }

            for (int i = 0; i < entitylist.size(); i++) {
                entitylist.remove(i);

            }
            ui.draw(g2);
            //player.draw(g2);
            g2.dispose();
        }
    }

    public void playMusic(int i) {
        try {
            sound.setFile(i);
        } catch (ExceptiiBaza e) {
            //e.printStackTrace();
        }
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        try {
            se.setFile(i);
        } catch (ExceptiiBaza e) {
            //e.printStackTrace();
        }
        se.play();
    }

}
