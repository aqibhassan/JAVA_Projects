
package skyforce_game;

import java.awt.Color;
import java.awt.Graphics;


public class Bullet
{
    private int x,y,Speed;
    
    Bullet(int x,int y)
    {
        this.x=x;
        this.y=y;
        this.Speed=10;
    }
    void InIt()
    {
        
    }
    void Tick()
    {
        y-=20;
    }
    public int Gety()
    {
        return y;
        
    }
    public int Getx()
    {
        return x;
        
    }
    void Render(Graphics g)
    {
        
         if(GameManager.Score<=100&&GameManager.Score>=0)
        {
//            g.setColor(Color.yellow);
//            g.fillRect(x, y, 4, 10);
            g.drawImage(LoadImage.bullet1,x,y,4,10,null);
        }
        else if(GameManager.Score<=200&&GameManager.Score>=100)
        {
           g.drawImage(LoadImage.bullet2,x,y,5,14,null);
        }
        else 
        
            g.drawImage(LoadImage.bullet3,x,y,7,18,null);
        }
         
    }
    

