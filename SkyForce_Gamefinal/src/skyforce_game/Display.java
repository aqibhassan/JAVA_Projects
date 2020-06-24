
package skyforce_game;

import javax.swing.JFrame;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
public class Display 
{
    public static  JFrame Frame;
    private  Canvas canvas;
    String title;
    int height;
    int width;
    Canvas  GetCanvas()
    {
        return canvas;
    }
    
    void CreateDisplay()
    {
      Frame =new JFrame(title);
       Frame.setSize(width, height);
       Frame.setVisible(true);
      // Frame.setLayeredPane(null);
       Frame.setLocationRelativeTo(null);
       Frame.setResizable(false);
      // Frame.setLayout(null);
       Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       canvas =new Canvas();
       canvas.setPreferredSize(new Dimension(width,height));
      
//      ImageIcon IC=new ImageIcon("/SKY.png");
//       JLabel BG=new JLabel("",IC,JLabel.CENTER);
//       BG.setBounds(50, 50, GameSetUp.GameWidth, GameSetUp.GameWidth);
//       Frame.add(BG);
       Frame.add(canvas);
       
    }
    Display(String title,int width,int height)
    {
        this.title=title;
        this.width=width;
        this.height=height;
        this.CreateDisplay();
        
    }
    
}
