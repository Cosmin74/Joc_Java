package main;

import java.awt.*;

public class EventHandler {
    GameScreen gp;
    EventRect eventRect[][];
    int eventDefaultX, eventDefaultY;

    public EventHandler(GameScreen gp) {
        this.gp = gp;

        eventRect=new EventRect[gp.maxcol][gp.maxrow];

        int col=0;
        int row=0;

        while(col<gp.maxcol && row<gp.maxrow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 98;
            eventRect[col][row].y = 98;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventDefaultX = eventRect[col][row].x;
            eventDefaultY = eventRect[col][row].y;

            col++;
            if(col == gp.maxcol){
                col=0;
                row++;
            }
        }
    }

    public void checkEvent() {
        if (hit(8, 1, "any") == true && gp.currentMap==1) {
            lowerLive(8,1);
            checkgmaeover();
        }
        if (hit(8, 1, "any") == true && gp.currentMap==2) {
            lowerLive(9,1);
            checkgmaeover();
        }
        if (hit(9, 1, "any") == true && gp.currentMap==2) {
            lowerLive(9,1);
            checkgmaeover();
        }
        if (hit(5, 2, "any") == true && gp.currentMap==2) {
            lowerLive(5,2);
            checkgmaeover();
        }
        if (hit(6, 2, "any") == true && gp.currentMap==2) {
            lowerLive(6,2);
            checkgmaeover();
        }
        if (hit(7, 2, "any") == true && gp.currentMap==2) {
            lowerLive(7,2);
            checkgmaeover();
        }
        if (hit(8, 2, "any") == true && gp.currentMap==2) {
            lowerLive(8,2);
            checkgmaeover();
        }
        if (hit(4, 5, "any") == true && gp.currentMap==2) {
            lowerLive(4,5);
            checkgmaeover();
        }
        if (hit(3, 6, "any") == true && gp.currentMap==2) {
            lowerLive(3,6);
            checkgmaeover();
        }
        if (hit(9, 1, "any") == true && gp.currentMap==1) {
            lowerLive(9,1);
            checkgmaeover();
        }
        if (hit(8, 2, "any") == true && gp.currentMap==1) {
            lowerLive(8,2);
            checkgmaeover();
        }
        if (hit(7, 2, "any") == true && gp.currentMap==1) {
            lowerLive(7,2);
            checkgmaeover();
        }
        if (hit(7, 3, "any") == true && gp.currentMap==1) {
            lowerLive(7,3);
            checkgmaeover();
        }

        if (hit(6, 3, "any") == true && gp.currentMap==1) {
            lowerLive(6,3);
            checkgmaeover();
        }

        if (hit(11, 3, "any") == true && gp.currentMap==1) {
            addLive(11,3);

        }

        if (hit(10, 5, "any") == true && gp.currentMap==1) {
            addLive(10,5);
        }

        if (hit(5, 1, "any") == true && gp.currentMap==1) {
            addLive(5,1);
        }

        if (hit(1, 6, "any") == true && gp.currentMap==1) {
            addLive(1,6);
        }

        if (hit(2, 2, "any") == true && gp.currentMap==2) {
            addLive(2,2);
        }

        if (hit(9, 4, "any") == true && gp.currentMap==2) {
            addLive(9,4);
        }

        if (hit(2, 5, "any") == true && gp.currentMap==2) {
            addLive(2,5);
        }

        if (hit(1, 6, "any") == true && gp.currentMap==2) {
            addLive(1,6);
        }

        if (hit(11, 1, "any") == true && gp.currentMap==2) {
            addLive(11,1);
        }

        if (hit(4, 4, "any") == true && gp.currentMap==2) {
            addLive(4,4);
        }

        if (hit(8, 1, "any") == true && gp.currentMap==3) {
            lowerLive(8,1);
            checkgmaeover();
        }

        if (hit(9, 1, "any") == true && gp.currentMap==3) {
            lowerLive(9,1);
            checkgmaeover();
        }
        

        if (hit(7, 2, "any") == true && gp.currentMap==3) {
            lowerLive(7,2);
            checkgmaeover();
        }

        if (hit(8, 2, "any") == true && gp.currentMap==3) {
            lowerLive(8,2);
            checkgmaeover();
        }

        if (hit(9, 2, "any") == true && gp.currentMap==3) {
            lowerLive(9,2);
            checkgmaeover();
        }

        if (hit(6, 3, "any") == true && gp.currentMap==3) {
            lowerLive(6,3);
            checkgmaeover();
        }

        if (hit(7, 3, "any") == true && gp.currentMap==3) {
            lowerLive(7,3);
            checkgmaeover();
        }

        if (hit(8, 3, "any") == true && gp.currentMap==3) {
            lowerLive(8,3);
            checkgmaeover();
        }

        if (hit(9, 3, "any") == true && gp.currentMap==3) {
            lowerLive(9,3);
            checkgmaeover();
        }

        if (hit(11, 3, "any") == true && gp.currentMap==3) {
            addLive(11,3);
        }

        if (hit(10, 5, "any") == true && gp.currentMap==3) {
            addLive(10,5);
        }

        if (hit(5, 1, "any") == true && gp.currentMap==3) {
            addLive(5,1);
        }

        if (hit(1, 6, "any") == true && gp.currentMap==3) {
            addLive(1,6);
        }

        if (hit(8, 2, "any") == true && gp.currentMap==3) {
            addLive(8,2);
        }

    }

    public boolean hit(int col, int row, String regDirection) {
        boolean hit = false;
        gp.player.solida.x = gp.player.x + gp.player.solida.x;
        gp.player.solida.y = gp.player.y + gp.player.solida.y;
        eventRect[col][row].x = col * gp.tilesize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tilesize + eventRect[col][row].y;

        if (gp.player.solida.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
            if (regDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solida.x = gp.player.solidadefaultx;
        gp.player.solida.y = gp.player.solidadefaulty;
        eventRect[col][row].x = eventDefaultX;
        eventRect[col][row].y = eventDefaultY;

        return hit;
    }

    public void lowerLive(int col,int row) {
        gp.player.life -= 1;
        eventRect[col][row].eventDone=true;
        gp.playSE(5);
    }

    public void addLive(int col,int row) {
        if(gp.player.life<=gp.player.maxlife){
            gp.player.life += 1;
        }
        eventRect[col][row].eventDone=true;

        gp.playSE(4);
    }

    void checkgmaeover(){
        if(gp.player.life==0){
            gp.playSE(3);
            gp.gamerOver=1;
            gp.gamestate=gp.pausestate;
            gp.menu=2;
            gp.player.life=gp.player.maxlife;
        }
    }
}