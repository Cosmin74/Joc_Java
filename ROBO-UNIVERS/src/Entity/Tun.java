package Entity;

import main.GameScreen;

public class Tun extends Entitate {

    Entitate user;

    public Tun(GameScreen gp) {
        super(gp);
    }

    public void set(int x, int y, String direction, boolean alive, Entitate user) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxlife;
    }

    public void update(){
        if(user==gp.player){
            if(gp.currentMap==1 || gp.currentMap==2) {
                int inamicIndex = gp.checker.checkEntity(this, gp.inamic);
                if (inamicIndex != 999) {
                    gp.player.damageInamic(inamicIndex);
                    alive = false;
                }
            }
            else if(gp.currentMap==3){
                boolean contact =gp.checker.checkInteresctWith(gp.boss);
                if(contact){
                    gp.player.damageInamic1();
                    alive=false;
                }
            }

            if(user!=gp.player){
                boolean contactPlayer = gp.checker.checkInteresctWith(this);
                if(contactPlayer){
                    damagePlayer();
                    alive=false;
                }
            }
        }

        switch (direction) {
            case "up":
                if(user==gp.player)
                    x += speed;
                if(user!=gp.player)
                    x-=speed;
                break;
            case "down":
                if(user==gp.player)
                    x += speed;
                if(user!=gp.player)
                    x-=speed;
                break;
            case "left":
                if(user==gp.player)
                    x += speed;
                if(user!=gp.player)
                    x-=speed;

                break;
            case "right":
                if(user==gp.player)
                    x += speed;
                if(user!=gp.player)
                    x-=speed;
                break;
        }

        life--;
        if(life<=0){
            alive=false;
        }

        spritecounter++;
        if(spritecounter>10)
        {
            if(spriteNum == 1){
                spriteNum=2;
            }
            else if(spriteNum == 2){
                spriteNum=1;
            }
            spritecounter=0;
        }

    }
}
