package tile;

import main.GameScreen;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.imageio.*;
import java.io.InputStream;

public class TileManager {
    GameScreen gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GameScreen gp) {

        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[4][gp.maxcol][gp.maxrow];


        getTileImage();
        loadMap("/Maps/Mapa1.txt", 1);
        loadMap("/Maps/Mapa2.txt", 2);
        loadMap("/Maps/Mapa3.txt", 3);
    }

    public void getTileImage() {
        setup(0, "crate", false);
        setup(1, "lava", false);
        setup(4, "rocaZid", true);
        setup(2, "rocaAsteroid", true);
        setup(3, "rocaMetalica", false);

    }

    public void setup(int index, String image, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + image + ".png"));
            tile[index].image = uTool.scaledImage(tile[index].image, gp.tilesize, gp.tilesize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String path, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxcol && row < gp.maxrow) {
                String line = br.readLine();

                while (col < gp.maxcol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gp.maxcol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }
    }

    public void drawMap1(Graphics2D g2) {

        for (int i = 0; i < mapTileNum[1].length; i++){
            for(int j=0;j<mapTileNum[1][0].length;j++){
                int type = mapTileNum[1][i][j];
                BufferedImage image = tile[type].image;
                g2.drawImage(image, i*100, j * 100, gp.tilesize, gp.tilesize, null);
            }
        }

        g2.drawImage(tile[0].image, 1100, 300, gp.tilesize, gp.tilesize, null);
        g2.drawImage(tile[0].image, 1000, 500, gp.tilesize, gp.tilesize, null);
        g2.drawImage(tile[0].image, 500, 100, gp.tilesize, gp.tilesize, null);
        g2.drawImage(tile[0].image, 100, 600, gp.tilesize, gp.tilesize, null);
    }

    public void drawMap2(Graphics2D g2) {

        for (int i = 0; i < mapTileNum[2].length; i++){
            for(int j=0;j<mapTileNum[2][0].length;j++){
                int type = mapTileNum[2][i][j];
                BufferedImage image = tile[type].image;
                g2.drawImage(image, i*100, j * 100, gp.tilesize, gp.tilesize, null);
            }
        }

        g2.drawImage(tile[0].image, 200, 200, gp.tilesize, gp.tilesize, null);
        g2.drawImage(tile[0].image, 900, 400, gp.tilesize, gp.tilesize, null);
        g2.drawImage(tile[0].image, 200, 500, gp.tilesize, gp.tilesize, null);
        g2.drawImage(tile[0].image, 100, 600, gp.tilesize, gp.tilesize, null);
        g2.drawImage(tile[0].image, 1100, 100, gp.tilesize, gp.tilesize, null);
        g2.drawImage(tile[0].image, 400, 400, gp.tilesize, gp.tilesize, null);


    }

    public void drawMap3(Graphics2D g2) {

        for (int i = 0; i < mapTileNum[3].length; i++){
            for(int j=0;j<mapTileNum[3][0].length;j++){
                int type = mapTileNum[3][i][j];
                BufferedImage image = tile[type].image;
                g2.drawImage(image, i*100, j * 100, gp.tilesize, gp.tilesize, null);
            }
        }

        g2.drawImage(tile[0].image, 1100, 300, gp.tilesize, gp.tilesize, null);
        g2.drawImage(tile[0].image, 1000, 500, gp.tilesize, gp.tilesize, null);
        g2.drawImage(tile[0].image, 500, 100, gp.tilesize, gp.tilesize, null);
        g2.drawImage(tile[0].image, 100, 600, gp.tilesize, gp.tilesize, null);
        g2.drawImage(tile[0].image, 300, 600, gp.tilesize, gp.tilesize, null);

    }
}

