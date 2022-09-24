package main;

import BD.Bazadedate;
import Exceptii.ExceptiiBazadeDate;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean up,dw,lft,rg,shot;
    GameScreen gp;
    Bazadedate bd;
    public KeyHandler(GameScreen gp){
        this.gp=gp;
        bd=new Bazadedate(gp);
    }

    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if ((gp.flag1==3 || gp.gamerOver == 1) && gp.gamestate == gp.pausestate && gp.menu == 2) {
            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gamestate = gp.playstate;
                    gp.flag1 = 0;
                    gp.currentMap = 1;
                    gp.setupGame();
                    gp.menu=0;
                }
                if (gp.ui.commandNum == 1) {
                    System.exit(0);
                }
            }
        }
            if (gp.titlestate == 0 || (gp.gamestate == gp.pausestate && gp.menu == 1)) {
                if (code == KeyEvent.VK_UP) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum < 0) {
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_DOWN) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum > 2) {
                        gp.ui.commandNum = 3;
                    }
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0) {
                        gp.titlestate = 1;
                        gp.gamestate = gp.playstate;
                        gp.flag1 = 0;
                        gp.currentMap = 1;
                        gp.setupGame();
                    }
                    if (gp.ui.commandNum == 1) {

                        try {
                            bd.tabela1();
                        } catch (ExceptiiBazadeDate ex) {
                            //ex.printStackTrace();
                        }
                        gp.titlestate = 1;
                        gp.gamestate = gp.playstate;
                        gp.setupGame();
                    }
                    if (gp.ui.commandNum == 2) {
                        try {
                            bd.tabela();
                        } catch (ExceptiiBazadeDate ex) {
                            //ex.printStackTrace();
                        }
                    }
                    if (gp.ui.commandNum == 3) {
                        System.exit(0);
                    }
                }
            }

            if (code == KeyEvent.VK_W) {
                up = true;
            }

            if (code == KeyEvent.VK_S) {
                dw = true;
            }

            if (code == KeyEvent.VK_A) {
                lft = true;
            }

            if (code == KeyEvent.VK_D) {
                rg = true;
            }

            if (code == KeyEvent.VK_SPACE) {
                shot = true;
            }

            if (code == KeyEvent.VK_ESCAPE) {
                if (gp.gamestate == gp.playstate) {
                    gp.gamestate = gp.pausestate;
                    gp.menu = 1;
                } else if (gp.gamestate == gp.pausestate) {
                    gp.gamestate = gp.playstate;
                    gp.menu = 0;
                }
            }

        }
    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W)
        {
            up=false;
        }

        if(code == KeyEvent.VK_S)
        {
            dw=false;
        }

        if(code == KeyEvent.VK_A)
        {
            lft=false;
        }

        if(code == KeyEvent.VK_D)
        {
            rg=false;
        }

        if(code == KeyEvent.VK_SPACE)
        {
            shot=false;
        }
    }
}
