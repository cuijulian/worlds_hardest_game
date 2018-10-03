public class Level
{
     private int level;
     
     public Level()
     {
          
          if(Menu.easy)
               level = 1;
          else if(Menu.medium)
               level = 6;
          else if(Menu.hard)
               level = 11;
          
          
          
     }
     
     public int getLevel()
     {
          return level;
     }
     
     public void setLevel(int l)
     {
          level = l;
     }
}