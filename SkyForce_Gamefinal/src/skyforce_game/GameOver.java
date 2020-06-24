
package skyforce_game;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static skyforce_game.Display.Frame;

public class GameOver {
   static JFrame Frame3;
    GameOver(String title,int width,int height)
    {
        Frame3 =new JFrame(title);
       Frame3.setSize(width, height);
       
      // Frame.setLayeredPane(null);
       Frame3.setLocationRelativeTo(null);
       Frame3.setResizable(false);
      // Frame.setLayout(null);
       Frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
      
   ImageIcon IC=new ImageIcon("C:\\Users\\aqib hassan\\Documents\\NetBeansProjects\\SkyForce_Game\\src\\skyforce_game\\GameOver.gif");
     JLabel BG=new JLabel("",IC,JLabel.CENTER);
      BG.setBounds(50, 50, GameSetUp.GameWidth, GameSetUp.GameWidth);
       Frame3.add(BG);
    }
}
