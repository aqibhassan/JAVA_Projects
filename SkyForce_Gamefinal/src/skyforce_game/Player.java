
package skyforce_game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class Player   implements KeyListener 
{
    public int x,y;
    boolean left,right,fire,Enter=false;
    private long current,delay;
    static int Health=3;
    
    GameManager GM=new GameManager();
    Player()
    {
        
    }
    Player(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    
    void InIt()
    {
        Display.Frame.addKeyListener(this);
        LoadImage.InIT();
        current =System.nanoTime();
        delay=100;
        Health=3;
    }
    void Tick()
    {
       
        //x=test.x;
        if(Health>0)
        {
          
             if(x==50)
        {
            x=GameSetUp.GameWidth+20;
        }
        
        if(left)
        {
            x-=2;
        }
        if(x==GameSetUp.GameWidth+20)
        {
            x=50;
        }
        if(right)
        {
            x+=2;
        }
        if(fire)
        {
            
            long breaks=(System.nanoTime()-current)/1000000;
            if(breaks>delay)
            GameManager.bullet.add(new Bullet(x+30,y-12));
             current =System.nanoTime();
        }
       
       
      
        }
       
    }
    public int Getx()
    {
        return x;
        
    }
    public int Gety()
    {
        return y;
    }
    void Render(Graphics G)
    {
        if(Health>0)
        {
     //  G.setColor(Color.red);
          if(GameManager.Score<=100&&GameManager.Score>=0)
        {
           G.drawImage(LoadImage.player,x,y-32,64,64,null);
        }
        else if(GameManager.Score<=200&&GameManager.Score>=100)
        {
           G.drawImage(LoadImage.player2,x,y-32,64,64,null);
        }
        else 
        {
            G.drawImage(LoadImage.player3,x,y-32,64,64,null);
        }
        

     //  G.fillRect(x, y, 30,30);
        }
        if(Health==3)
        {
            G.drawImage(LoadImage.player,380,2,30,30,null);
            G.drawImage(LoadImage.player,420,2,30,30,null);
            G.drawImage(LoadImage.player,460,2,30,30,null);
            
        }
       if(Health==2)
        {
            G.drawImage(LoadImage.player,380,2,30,30,null);
            G.drawImage(LoadImage.player,420,2,30,30,null);
           
            
        }
       if(Health==1)
        {
            G.drawImage(LoadImage.player,380,2,30,30,null);
               
        }
       
      
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        int source =e.getKeyCode();
        if(source==KeyEvent.VK_LEFT)
        {
            this.left=true;
        }
        if(source==KeyEvent.VK_RIGHT)
        {
            this.right=true;
        }
        if(source==KeyEvent.VK_SPACE)
        {
            this.fire=true;
        }
       
    }
    @Override
    public void keyReleased(KeyEvent e)
    {
         int source =e.getKeyCode();
        if(source==KeyEvent.VK_LEFT)
        {
            this.left=false;
        }
        if(source==KeyEvent.VK_RIGHT)
        {
            this.right=false;
        }
        if(source==KeyEvent.VK_SPACE)
        {
            this.fire=false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e)
    {
        
    }

   
}
