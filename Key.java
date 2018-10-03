import java.awt.Image;
import javax.swing.ImageIcon;

public class Key
{
     private Image key;
     
     private int[] key1 = {5,6, 19,10, 19,11};
     
     public Key()
     {
          ImageIcon img = new ImageIcon("key.png");
          key = img.getImage();
          
     }
     
     public Image getKey()
     {
          return key;
     }
     
     //Level 1
     public int[] getKeys1()
     {
          return key1;
     }
}