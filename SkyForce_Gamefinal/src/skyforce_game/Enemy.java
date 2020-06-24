
package skyforce_game;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy
{
    
    private int x,y;
    
    Enemy(int x,int y)
    {
        this.x=x;
        this.y=y;
        
    }
   void Tick()
   {
       y+=1;
   }
   void Render(Graphics G)
   {
      // G.setColor(Color.black);
//       G.fillOval(x, y, 25, 25);
        if(GameManager.Score<=100&&GameManager.Score>=0)
        {
           G.drawImage(LoadImage.Enemy,x,y,40,40,null);
        }
        else if(GameManager.Score<=200&&GameManager.Score>=100)
        {
          G.drawImage(LoadImage.Enemy2,x,y,45,45,null);
        }
        else 
        {
            G.drawImage(LoadImage.Enemy3,x,y,55,55,null);
        }
          

   }
   public int Getx()
   {
       return x;
   }
   public int  Gety()
   {
       return y;
   }
}
