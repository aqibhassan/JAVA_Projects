
package skyforce_game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;


public class GameManager implements KeyListener
{
   
    Player p;
    public static int Score;
    public static ArrayList<Bullet> bullet;
    public static ArrayList<Enemy> enemy;
    private long current;
   
    private long delay;
    boolean Start;
    public GameManager()
    {
        
    }
    void InIt()
    {
       Display.Frame.addKeyListener(this);
       p=new Player((GameSetUp.GameWidth/2)+50,GameSetUp.GameHeight+19); 
       p.InIt();
      bullet =new ArrayList<Bullet>();
      enemy =new ArrayList<Enemy>();
      current=System.nanoTime();
      delay =2000;
     // Start=true;
    }
    void Tick()
    {
        if(Start)
        {
            p.Tick();
        for(int i=0;i<bullet.size();i++)
        {
            bullet.get(i).Tick();
        }
          long breaks=(System.nanoTime()-current)/1000000;
          if(breaks>delay)
          {
               for(int i=0;i<2;i++)
             {
           Random Rand =new Random();
           int RX= Rand.nextInt(425);
           int RY= Rand.nextInt(70);
                   if(Player.Health>0)
                   {
                      
                         enemy.add(new Enemy(RX,RY));
                   }
         
                }
                 current=System.nanoTime();
          }
            
        //enemies.
      
        
        for(int i=0;i<enemy.size();i++)
        {
            enemy.get(i).Tick();
        }
        }
        
    }
    void Render(Graphics G)
    {
        if(Start)
        {
       p.Render(G);
       for(int i=0;i<bullet.size();i++)
        {
            bullet.get(i).Render(G);
        }
       for(int i=0;i<bullet.size();i++)
       {
           if(bullet.get(i).Gety()<=70)
           {
               bullet.remove(i);
               i--;
           }
        }
     
       
       
       //Enemies.
       for(int i=0;i<enemy.size();i++)
        {   
            
            
            if(enemy.get(i).Getx()>50&&enemy.get(i).Gety()>50&&enemy.get(i).Gety()<=495 )
            {
            enemy.get(i).Render(G);
            }
        }
       
       for(int i=0;i<enemy.size();i++)
       {    int Ex=enemy.get(i).Getx();
            int Ey=enemy.get(i).Gety();
            int Px=p.Getx();
            int Py=p.Gety();
             if(Ex<Px+60&&Ex+40>Px&&Ey<Py+60&&Ey+40>Py)
               {
                   enemy.remove(i);
                   i--;
                   Player.Health--;
                   if(Player.Health<=0)
                   {
                       enemy.removeAll(enemy);
                       Score=0;
                       Start=false;
//                       String x;
                    //   G.setColor(Color.red);
                      //  G.drawString("Game over",150, 10);
                      
                     
                   }
        
               }
             
           for(int j=0;j<bullet.size();j++)
           {
               int Bx=bullet.get(j).Getx();
               int By=bullet.get(j).Gety();
               if(Ex<Bx+4&&Ex+40>Bx&&Ey<By+4&&Ey+40>By)
               {
                   enemy.remove(i);
                   i--;
                   bullet.remove(j);
                   j--;
                   Score+=10;
               }
               
           }
       }
       
    }
        else
        {
            if(Player.Health<=0)
            {
                G.drawImage(LoadImage.Blast,p.Getx(),p.Gety()-32,64,64,null);
                 G.drawImage(LoadImage.GameOver,100,50,300,200,null);  
                 
            }
             G.drawImage(LoadImage.Start,50,250,300,200,null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
            }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
       if(e.getKeyCode()==KeyEvent.VK_ENTER)
       {
           Start=true;
           InIt();
       }
    }
    
        
    }

