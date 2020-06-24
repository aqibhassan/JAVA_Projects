
package skyforce_game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class LoadImage 
{
    
    public static BufferedImage Level1;
    public static BufferedImage Level2;
    public static BufferedImage Level3;
    public static BufferedImage player;
    public static BufferedImage player2;
    public static BufferedImage player3;
    public static BufferedImage GameOver;
    public static BufferedImage Enemy;
    public static BufferedImage Enemy2;
    public static BufferedImage Enemy3;
    public static BufferedImage Score;
    public static BufferedImage Blast;
    public static BufferedImage bullet3;
    public static BufferedImage bullet2;
    public static BufferedImage bullet1;
    public static BufferedImage MainBack;
    public static BufferedImage Health;
    public static BufferedImage Start;
  //  publiac  BufferedImage image1;
    
    public static void InIT() 
    {
       Level1 = imageloader("/skyforce_game/Level1.jpg");
       Level2 = imageloader("/skyforce_game/Level2.jpg");
       Level3 = imageloader("/skyforce_game/Level3.jpg");
       Health = imageloader("/skyforce_game/health.png");
       bullet1 = imageloader("/skyforce_game/bullet1.png");
       bullet2 = imageloader("/skyforce_game/bullet3.png");
       bullet3 = imageloader("/skyforce_game/fire.png");
       MainBack = imageloader("/skyforce_game/backback.png");
       Score = imageloader("/skyforce_game/Score.png");
       Blast = imageloader("/skyforce_game/blast.png");
       player = imageloader("/skyforce_game/Player_1.png");
       player2 = imageloader("/skyforce_game/PLAYER2_1.png");
       player3 = imageloader("/skyforce_game/Player3.png");
       GameOver = imageloader("/skyforce_game/Game over.png");
       Enemy = imageloader("/skyforce_game/ENEMY.PNG");
       Enemy2 = imageloader("/skyforce_game/Enemy2.PNG");
       Enemy3 = imageloader("/skyforce_game/Enemy3.PNG");
       Start = imageloader("/skyforce_game/StartG.jpg");
    }
     public  static BufferedImage  imageloader(String path) 
     {
        try {
            
//            image1 = ImageIO.read(LoadImage.class.getResource(path));
           return  ImageIO.read(LoadImage.class.getResource(path));
             
        } catch (IOException ex) {
            ex.printStackTrace();
           // System.out.println("Not Working");
            System.exit(1);  
        }
         return null;                                                                               
        
        
     }
}
