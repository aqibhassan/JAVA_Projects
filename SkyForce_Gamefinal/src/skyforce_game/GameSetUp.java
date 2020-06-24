
package skyforce_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.text.AttributedCharacterIterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import static skyforce_game.GameOver.Frame3;



public class GameSetUp implements Runnable
{
    String title;
    int height;
    int width;
    Thread thread;
    Graphics G;
    GameManager GM;
    JButton B;
    public static final int GameWidth=400;
    public static final int GameHeight=500;
    
    private int y;
    
    public BufferStrategy buffer;
    boolean running;
    private Display D;
    GameSetUp(String title,int width,int height)
    {
        this.title=title;
        this.width=width;
        this.height=height;
        
    }
    
    public void init()
    {
       D=new Display(title,width,height); 
    LoadImage.InIT();
       GM= new GameManager();
       GM.InIt();
       
    }
    public void tick()
    {
        
        GM.Tick();
    }
    public void render()
    {
        buffer = D.GetCanvas().getBufferStrategy();
        if(buffer ==null)
        {
            D.GetCanvas().createBufferStrategy(3);
            return;
        }
        G=buffer.getDrawGraphics();
        
             G.clearRect(0, 0, width, height);
               G.drawImage(LoadImage.MainBack,0, 0, width, height,null); 
       
        //Draw
          //  LoadImage IM=new LoadImage();
              //   IM.InIT();
        
//        if(GameManager.Score<=100)
//        {
//            G.setColor(Color.YELLOW);
//        }
      // G.fillRect(50,50,this.GameWidth,this.GameHeight);
                   if(GameManager.Score<=100&&GameManager.Score>=0)
        {
           G.drawImage(LoadImage.Level1,50,50,GameSetUp.GameWidth,GameSetUp.GameHeight,null);
        }
        else if(GameManager.Score<=200&&GameManager.Score>=100)
        {
           G.drawImage(LoadImage.Level2,50,50,GameSetUp.GameWidth,GameSetUp.GameHeight,null);
        }
        else 
        {
            
            G.drawImage(LoadImage.Level3,50,50,GameSetUp.GameWidth,GameSetUp.GameHeight,null);
         }
         
      
       
             GM.Render(G);
               G.drawImage(LoadImage.Score,0,0,140,35,null);  
               G.drawImage(LoadImage.Health,220,0,163,40,null);  
               
//       G.drawString("Health:"+(Player.Health), 110, 25);
               G.setColor(Color.red);
              G.setFont(new Font("arial",Font.BOLD,35));
            G.drawString(":"+GameManager.Score, 145, 30);
          
        //End Of Draw
        buffer.show();
        G.dispose();
    }
    public synchronized void start()
    {
        
        if(running)
         return;
        running= true;
        if(thread==null)
        {
            thread =new Thread(this);
        this.thread.start();
        }
        
    }
    public synchronized void stop()
    {
        if(!(running))
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
           ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        init();
        double FPS=0.00000015;
        
        long  l=1000000000;
        double TimePerTick =l/FPS;
        long Current =System.nanoTime();
        double delta=0.1;
        
        while(running)
        {
//            if(GameManager.Score==200)
//            {
//                Current=System.nanoTime();
//            }
            delta+= (System.nanoTime()-Current)/TimePerTick;
            if(delta>=1)
            {
                this.tick();
                this.render();
                delta--;
            }
            
        }
       
    }

   
}
