/**
 * Evaluates layout stored in text file, processes it, and stores it
 * Creates images as well
 * @author Hikmat Sahak
 * @date December 18, 2015
 * @course ICS4U
 * */

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class MazeLayout
{
     //Initialize variables
     private Scanner in;
     private Image floor, wall, zone, barrier;
     private String[] MazeLayout;
     
     private int level;
     private int y;
     
     public static void main(String[] args)
     {
          new MazeLayout();
     }
     
     /**
      * Default constructor
      * Initializes images and calls file methods
      * */
     public MazeLayout()
     {
          //Maze Floor image
          ImageIcon img = new ImageIcon("floor.png");
          floor = img.getImage();
          
          //Maze Wall Image
          img = new ImageIcon("wall.png");
          wall = img.getImage();
          
          //Maze Start-End Zone Image
          img = new ImageIcon("zone.png");
          zone = img.getImage();
          
          //Maze Barrier Image
          img = new ImageIcon("barrier.png");
          barrier = img.getImage();
          
          if(Menu.easy)
          {
               level = MazeGame.level;
          }
          else if(Menu.medium)
          {
               level = Medium.level;
          }
          else if(Menu.hard)
               level = Hard.level;
          
          switch(level)
          {
               case 1: y = 11; break;
               case 2: y = 15; break;
               case 3: y = 14; break;
               case 4: y = 14; break; 
               case 5: y = 14; break;   
               case 6: y = 16; break;
               case 7: y = 10; break;
               case 8: y = 10; break;
               case 9: y = 12; break;
               case 10: y = 16; break;
               case 11: y = 14; break;
               case 12: y = 14; break;
               case 13: y = 12; break;
               case 14: y = 16; break;
               case 15: y = 10; break;
          }
          
          MazeLayout = new String[y];
          //Call files to read, process, and store information from text files
          openFile();
          readFile();
          closeFile();
     }//end MazeLayout()
     
     /**
      * getFloor method
      * Returns image of maze floor tile
      * @return Image floor
      * */
     public Image getFloor()
     {
          return floor;
     }//end getFloor
     
     /**
      * getWall method
      * Returns image of maze wall tile
      * @return Image wall
      * */
     public Image getWall()
     {
          return wall;
     }//end getWall
     
     /**
      * getZone method
      * Returns image of maze zone tile
      * @return Image zone
      * */
     public Image getZone()
     {
          return zone;
     }//end getZone
     
     /**
      * getBarrier method
      * Returns image of maze barrier
      * @return Image barrier
      * */
     public Image getBarrier()
     {
          return barrier;
     }//end getZone
     
     public int getColumn()
     {
          return y;
     }
     
     /**
      * getMazeTile method
      * Returns the letter in a particular row given the position of the value  
      * @param int x - value in a row
      * @param int y - row number
      * @return String index - letter that determines identity of tile - floor, wall, zone
      * */
     public String getMazeTile(int x, int y)
     {
          //Since row is a string of letters, look at that row and get the letter at given position
          String index = MazeLayout[y].substring(x, x+1);
          return index;
     }//end getMazeTile
     
     public void setMazeTile(int x, int y, String c)
     {
          //Since row is a string of letters, look at that row and get the letter at given position
          String index = MazeLayout[y].substring(0,x) + c + MazeLayout[y].substring(x+1);
          MazeLayout[y] = index;
     }
     
     /**
      * openFile method
      * Check to see if file exists
      * */
     public void openFile()
     {
          try{   
               in = new Scanner(new File("level" + level + ".txt"));
          }catch(Exception e){
               System.out.println(level);
          }
     }//end openFile
     
     /**
      * readFile method
      * Go through text file and store each line in the array
      * */
     public void readFile()
     {
          while(in.hasNext())
          {
               for(int i=0; i < y; i++)
                    MazeLayout[i] = in.next();
          }//end while
     }//end readFile
     
     /**
      * closeFile method
      * Close the scanner
      * */
     public void closeFile()
     {
          in.close();
     }//end closeFile
}//end class MazeLayout

