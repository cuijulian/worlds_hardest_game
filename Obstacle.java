import java.awt.Image;
import javax.swing.ImageIcon;

public class Obstacle
{
     private Image[] balls;
     private Image ball;
     
     //Level 1
     private int[] tilesUp = {5,7,9,11,13,15};
     private int[] tilesDown = {6,8,10,12,14,16};
     
     private int level;
     
     public Obstacle()
     {
          ImageIcon img = new ImageIcon("ball.png");
          ball = img.getImage();
          
          if(Menu.easy)
               level = MazeGame.level;
          
          
          switch(level)
          {
               case 1:
                    balls = new Image[6];
                    for(int i=0; i<6; i++)
                         balls[i] = ball;
                    break;
               case 2:
                    balls = new Image[5];
                    for(int i=0; i < 5; i++)
                         balls[i] = ball;
                    break;
               case 3:
                    balls = new Image[3];
                    for(int i=0; i < 3; i++)
                         balls[i] = ball;
                    break;
               case 4:
                    balls = new Image[8];
                    for(int i=0; i<8; i++)
                         balls[i] = ball;
               case 5:
                    balls = new Image[1];
                    for(int i=0; i < 1; i++)
                         balls[i] = ball;
                    break;
          }
     }
     
     
     
     public Image[] getBalls()
     {
          return balls;
     }
     
     //Level 1
     public int[] getTilesUp()
     {
          return tilesUp;
     }
     
     public int[] getTilesDown()
     {
          return tilesDown;
     }
     
     public Image getBall()
     {
          return ball;
     }
     
}