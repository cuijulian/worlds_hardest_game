import java.awt.Image;
import javax.swing.ImageIcon;

public class Coin
{
     private int coinX, coinY;
     private Image coin;
     
     private int[] coin2 = {8,5, 12,8, 8,12};
     private int[] coin3 = {4,8, 8,8, 12,8, 16,8};
     private int[] coin4 = {2,11, 11,2, 11,11};
     private int[] coinM1 = {3,3, 14,3, 14,12, 3,12};
     private int[] coinM2 = {7,2, 10,2, 13,7, 16,7};
     private int[] coinM3 = {21,1, 20,4, 16,2, 16,5, 13,4, 9,2, 9,5}; 
     private int[] coinM4 = {3,1, 18,1, 1,10, 20,10};
     private int[] coinM5 = {5,2, 12,2, 5,13, 12,13};
     private int[] coinH1 = {18,2, 19,2, 20,2, 18,3, 19,3, 20,3};
     private int[] coinH2 = {7,2, 8,2, 7,3, 8,3};
     private int[] coinH3 = {1,9, 2,9, 1,10, 2,10};
     private int[] coinH4 = {3,4, 8,4, 17,4, 22,4, 8,1, 17,1, 3,11, 8,11, 17,11, 22,11, 8,14, 17,14};
     private int[] coinH5 = {5,4, 6,3, 7,2, 8,3, 9,4, 6,5, 7,6, 8,5, 15,4, 16,3, 17,2, 18,3, 19,4, 16,5, 17,6, 18,5};
     
     
     private int level;
     
     public Coin()
     {
          ImageIcon img = new ImageIcon("coin.png");
          coin = img.getImage();
          
          if(Menu.easy)
          {
               level = MazeGame.level;
               switch(level)
               {
                    case 1:
                         coinX = 10;
                         coinY = 5;
                         break;
                    case 4:
                         coinX = 19;
                         coinY = 10;
                         break;
               }
          }
     }
     
     public Image getCoin()
     {
          return coin;
     }
     
     //Level 1
     public void setCoinX(int x)
     {
          coinX = x;
     }
     
     public void setCoinY(int y)
     {
          coinY = y;
     }
     
     public int getCoinX()
     {
          return coinX;
     }
     
     public int getCoinY()
     {
          return coinY;
     }
     
     //Level 2
     public int[] getCoins2()
     {
          return coin2;
     }
     
     public int[] getCoins3()
     {
          return coin3;
     }
     
     public int[] getCoins4()
     {
          return coin4;
     }
     
     public int[] getCoinsM1()
     {
          return coinM1;
     }
     
     public int[] getCoinsM2()
     {
          return coinM2;
     }
     
     public int[] getCoinsM3()
     {
          return coinM3;
     }
     
     public int[] getCoinsM4()
     {
          return coinM4;
     }
     
     public int[] getCoinsM5()
     {
          return coinM5;
     }
     
     public int[] getCoinsH1()
     {
          return coinH1;
     }
     
     public int[] getCoinsH2()
     {
          return coinH2;
     }
     
     public int[] getCoinsH3()
     {
          return coinH3;
     }
     
     public int[] getCoinsH4()
     {
          return coinH4;
     }
     
     public int[] getCoinsH5()
     {
          return coinH5;
     }
}