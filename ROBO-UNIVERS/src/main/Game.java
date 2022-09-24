package main;
import javax.swing.JFrame;
import java.io.IOException;


public class Game  {

    public static void main(String[] args) throws IOException {
        JFrame window=new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Robo-Universe");

        GameScreen gameScreen=GameScreen.getInstance();
        window.add(gameScreen);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gameScreen.setupGame();

        gameScreen.playMusic(0);
        gameScreen.startGameThread();
    }
}
