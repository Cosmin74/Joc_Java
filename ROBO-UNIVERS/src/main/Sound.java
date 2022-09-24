package main;

import Exceptii.ExceptiiBaza;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/sound/burning.wav");
        soundURL[2] = getClass().getResource("/sound/fanfare.wav");
        soundURL[3] = getClass().getResource("/sound/leveup.wav");
        soundURL[4] = getClass().getResource("/sound/powerup.wav");
        soundURL[5] = getClass().getResource("/sound/receivedamage.wav");
    }

    public void setFile(int i) throws ExceptiiBaza {
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException ex){
                throw new ExceptiiBaza();
        }
        catch (LineUnavailableException ex){
            throw new ExceptiiBaza();
        }
        catch (IOException ex){
            throw new ExceptiiBaza();
        }
        catch (Exception ex){
            throw new ExceptiiBaza();
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
