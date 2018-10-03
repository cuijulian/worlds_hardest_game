import java.awt.Image;
import javax.swing.ImageIcon;

public class Player
{
     private int tileX, tileY;
     
     private Image player;
     
     private int level;
     
     public Player()
     {
          ImageIcon img = new ImageIcon("player.png");
          player = img.getImage();
          
          if(Menu.easy)
               level = MazeGame.level;
          else if(Menu.medium)
               level = Medium.level;
          else if(Menu.hard)
               level = Hard.level;
          
          
          switch(level)
          {
               case 1: tileX = 3; tileY = 5; break;
               case 2: tileX = 8; tileY = 2; break;
               case 3: tileX = 3; tileY = 3; break;
               case 4: tileX = 2; tileY = 2; break;
               case 5: tileX = 2; tileY = 2; break;
               case 6: tileX = 8; tileY = 7; break;
               case 7: tileX = 2; tileY = 4; break;
               case 8: tileX = 22; tileY = 8; break;
               case 9: tileX = 10; tileY = 9; break;
               case 10: tileX = 8; tileY = 7; break;
               case 11: tileX = 1; tileY = 6; break;
               case 12: tileX = 7; tileY = 7; break;
               case 13: tileX = 1; tileY = 1; break;
               case 14: tileX = 1; tileY = 1; break;
               case 15: tileX = 1; tileY = 4; break;
          }
     }
     
     
     public Image getPlayer()
     {
          return player;
     }
     
     public void setTileX(int x)
     {
          tileX = x;
     }
     
     public void setTileY(int y)
     {
          tileY = y;
     }
     
     public int getTileX()
     {
          return tileX;
     }
     
     public int getTileY()
     {
          return tileY;
     }
     
     public void move(int dx, int dy)
     {
          tileX += dx;
          tileY += dy;
     }
} 
