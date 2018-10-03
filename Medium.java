import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;
import sun.audio.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Medium
{
     static String username = Menu.username;
     private JLabel lblDeath;
     private JFrame f;
     int death;
     static Level levi = new Level();
     static int level;
     static boolean isLoad = Menu.isLoad;
     Scanner scanFile;
     PrintWriter outFile;
     
     /**
      * Default constructor:
      * Constructs frame and calls DrawMaze to do just that
      * */
     public Medium()
     {
          if (isLoad)
          {
               try
               {
                    if(!Menu.cheat)
                         scanFile = new Scanner(new File(username + "-Level.txt"));
                    else
                         scanFile = new Scanner(new File(username + "-CheatLevel.txt"));
                    level = scanFile.nextInt();
                    
               }
               catch (FileNotFoundException i)
               {
                    
               }
               
               try
               {
                    Scanner scanFile = new Scanner(new File(username + "-Death.txt"));
                    death = scanFile.nextInt();
               }
               catch (FileNotFoundException i)
               {
                    
               }
          }
          else
          {
               level = levi.getLevel();
               
          }
          
          try
          {
               Scanner scanFile = new Scanner(new File(username + "-Death.txt"));
               death = scanFile.nextInt();
          }
          catch(FileNotFoundException i){}
          
          f = new JFrame();
          f.setTitle("Maze Game");
          
          JPanel canvas = new JPanel();
          canvas.setLayout(new BoxLayout(canvas, BoxLayout.Y_AXIS));
          
          JPanel pnl = new JPanel();
          pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
          pnl.setBackground(Color.BLACK);
          
          JLabel lbl = new JLabel();
          lbl.setForeground(Color.WHITE);
          lbl.setText("MODE: MEDIUM");
          lbl.setFont(new Font("Calibri", Font.BOLD, 25));
          
          JLabel lblLevel = new JLabel();
          lblLevel.setForeground(Color.WHITE);
          
          lblLevel.setText((level - 5) + " / 5");
          lblLevel.setFont(new Font("Calibri", Font.BOLD, 25));
          
          lblDeath = new JLabel();
          lblDeath.setForeground(Color.WHITE);
          
          lblDeath.setText("DEATHS:  " + death); 
          lblDeath.setFont(new Font("Calibri", Font.BOLD, 25));
          
          switch(level)
          {
               case 6: 
                    pnl.setPreferredSize(new Dimension(593, 30));
                    lbl.setPreferredSize(new Dimension(125, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(150, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(107, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(107, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(10, 0)));
                    f.setSize(593, 583);
                    break;
               case 7: 
                    pnl.setPreferredSize(new Dimension(785, 30));
                    lbl.setPreferredSize(new Dimension(125, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(150, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(215, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(185, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(15, 0)));
                    f.setSize(785, 390);
                    break;
               case 8:
                    pnl.setPreferredSize(new Dimension(785, 30));
                    lbl.setPreferredSize(new Dimension(125, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(150, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(215, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(185, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(15, 0)));
                    f.setSize(785, 390);
                    break;
               case 9:
                    pnl.setPreferredSize(new Dimension(723, 30));
                    lbl.setPreferredSize(new Dimension(125, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(150, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(172, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(162, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(15, 0)));
                    f.setSize(723, 455);
                    break;
               case 10:
                    pnl.setPreferredSize(new Dimension(595, 30));
                    lbl.setPreferredSize(new Dimension(125, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(150, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(95, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(115, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(15, 0)));
                    f.setSize(595, 583);
                    break;
          }
          canvas.add(pnl);
          canvas.add(new DrawMaze());
          f.add(canvas);
          f.setLocationRelativeTo(null);
          f.setVisible(true);
          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }//end MazeGame
     
     class DrawMaze extends JPanel implements ActionListener
     {
          //Create MazeLayout object to retrieve information about positioning
          private Timer timer, t;
          private MazeLayout maze;
          private Player p;
          private Obstacle blueBalls; 
          private Coin c;
          private int level;
          private int coins, rows;
          private Image[] balls;
          private JLabel lbl;
          private boolean fadeAway = false;
          private float alpha = 1f;
          
          //Level 1
          private float fade = 1f;
          private float fade1 = 1f;
          private float fade2 = 1f;
          private float fade3 = 1f;
          private float fade4 = 1f;
          private float fade5 = 1f;
          private float fade6 = 1f;
          
          private int xBallDown = 72;
          private int yBallDown = 136;
          private int yBallUp = 360;
          
          private int xBallTop = 136;
          private int xBallBottom = 424;
          private int yBallTop = 72;
          
          private int xBallSmall = 200;
          private int yBallSmallD = 72;
          private int yBallSmallU = 200;
          private int moveVertical = -4;
          private int moveHorizontal = -4;
          private int move = -2;
          private int moveSide = -2;
          
          private int[] coins1;
          private int[] coins2;
          private int[] coins3;
          private int[] coins4;
          private int[] coins5;
          
          //Level 2
          private int xSquare = 136;
          private int ySquare = 72;
          
          //Level 3
          private int xUp = 680;
          private int yUp = 200;
          private int xSide = 552;
          private int ySide = 200;
          
          //Level 4
          private int xVUp = 168;
          private int yVUp = 72;
          private int yVDown = 168;
          private int xTop = 40;
          private int yTop = 264;
          private int yBottom = 328;
          
          //Level 50
          private int xLeft= 520;
          private int xRight = 40;
          
          
          public DrawMaze()
          {
               maze = new MazeLayout();
               p = new Player();
               blueBalls = new Obstacle();
               c = new Coin();
               
               if (isLoad)
               {
                    try
                    {
                         if(!Menu.cheat)
                              scanFile = new Scanner(new File(username + "-Level.txt"));
                         else
                              scanFile = new Scanner(new File(username + "-CheatLevel.txt"));
                         level = scanFile.nextInt();
                    }
                    catch (FileNotFoundException i)
                    {
                         
                    }
                    
                    isLoad = false;
               }
               else
               {
                    level = levi.getLevel();
               }
               
               switch(level)
               {
                    case 6:
                         rows = 18;
                         coins = 4;
                         coins1 = c.getCoinsM1();
                         if(Menu.cheat)
                              timer = new Timer(50, this);
                         else
                              timer = new Timer(25, this);
                         timer.start();
                         break;
                    case 7:
                         rows = 24;
                         coins = 4;
                         coins2 = c.getCoinsM2();
                         if(Menu.cheat)
                              timer = new Timer(50, this);
                         else
                              timer = new Timer(25, this);
                         timer.start();
                         break;
                    case 8:
                         rows = 24;
                         coins = 7;
                         coins3 = c.getCoinsM3();
                         if(Menu.cheat)
                              timer = new Timer(50, this);
                         else
                              timer = new Timer(25, this);
                         timer.start();
                         break;
                    case 9:
                         rows = 22;
                         coins = 4;
                         coins4 = c.getCoinsM4();
                         if(Menu.cheat)
                              timer = new Timer(50, this);
                         else
                              timer = new Timer(25, this);
                         timer.start();
                         break;
                    case 10:
                         rows = 18;
                         coins = 4;
                         coins5 = c.getCoinsM5();
                         if(Menu.cheat)
                              timer = new Timer(50, this);
                         else
                              timer = new Timer(25, this);
                         timer.start();
                         break;
               }
               addKeyListener(new Move());
               setFocusable(true);
          }//end DrawMaze
          
          public void playCollide()
          {
               try{
                    InputStream in = new FileInputStream("collide.wav");
                    AudioStream as = new AudioStream(in);         
                    AudioPlayer.player.start(as);
               }catch(IOException e){}
          }
          
          public void playCash()
          {
               try{
                    InputStream in = new FileInputStream("cash.wav");
                    AudioStream as = new AudioStream(in);         
                    AudioPlayer.player.start(as);
               }catch(IOException e){}
          }
          
          public void playLevel()
          {
               try{
                    InputStream in = new FileInputStream("levelDone.wav");
                    AudioStream as = new AudioStream(in);         
                    AudioPlayer.player.start(as);
               }catch(IOException e){}
          }
          
          public void actionPerformed(ActionEvent e)
          {
               switch(level)
               {
                    case 6:
                         ballsMoving();
                         
                         if(collectCoin(coins1[0] * 32 + 8, coins1[1] * 32 + 8, fade))
                         { 
                              playCash();
                              fade = 0;
                         }
                         else if(collectCoin(coins1[2] * 32 + 8, coins1[3] * 32 + 8, fade1))
                         { 
                              playCash();
                              fade1 = 0;
                         }
                         else if(collectCoin(coins1[4] * 32 + 8, coins1[5] * 32 + 8, fade2))
                         { 
                              playCash();
                              fade2 = 0;
                         }
                         else if(collectCoin(coins1[6] * 32 + 8, coins1[7] * 32 + 8, fade3))
                         { 
                              playCash();
                              fade3 = 0;
                         }
                         if(fadeAway)
                              regenerate();
                         repaint();
                         break;
                    case 7:
                         squares();
                         if(collectCoin(coins2[0] * 32 + 8, coins2[1] * 32 + 8, fade))
                         { 
                              playCash();
                              fade = 0;
                         }
                         else if(collectCoin(coins2[2] * 32 + 8, coins2[3] * 32 + 8, fade1))
                         { 
                              playCash();
                              fade1 = 0;
                         }
                         else if(collectCoin(coins2[4] * 32 + 8, coins2[5] * 32 + 8, fade2))
                         { 
                              playCash();
                              fade2 = 0;
                         }
                         else if(collectCoin(coins2[6] * 32 + 8, coins2[7] * 32 + 8, fade3))
                         { 
                              playCash(); 
                              fade3 = 0;
                         }          
                         if(fadeAway)
                              regenerate();
                         repaint();
                         break;
                    case 8:
                         move3();
                         if(collectCoin(coins3[0] * 32 + 8, coins3[1] * 32 + 8, fade))
                         { 
                              playCash();
                              fade = 0;
                         }
                         else if(collectCoin(coins3[2] * 32 + 8, coins3[3] * 32 + 8, fade1))
                         { 
                              playCash();
                              fade1 = 0;
                         }
                         else if(collectCoin(coins3[4] * 32 + 8, coins3[5] * 32 + 8, fade2))
                         { 
                              playCash();
                              fade2 = 0;
                         }
                         else if(collectCoin(coins3[6] * 32 + 8, coins3[7] * 32 + 8, fade3))
                         { 
                              playCash();
                              fade3 = 0;
                         }
                         else if(collectCoin(coins3[8] * 32 + 8, coins3[9] * 32 + 8, fade4))
                         { 
                              playCash();
                              fade4 = 0;
                         }
                         else if(collectCoin(coins3[10] * 32 + 8, coins3[11] * 32 + 8, fade5))
                         { 
                              playCash();
                              fade5 = 0;
                         }
                         else if(collectCoin(coins3[12] * 32 + 8, coins3[13] * 32 + 8, fade6))
                         { 
                              playCash();
                              fade6 = 0;
                         }
                         if(fadeAway)
                              regenerate();
                         repaint();
                         break;
                    case 9:
                         move4();
                         if(collectCoin(coins4[0] * 32 + 8, coins4[1] * 32 + 8, fade))
                         { 
                              playCash();
                              fade = 0;
                         }
                         else if(collectCoin(coins4[2] * 32 + 8, coins4[3] * 32 + 8, fade1))
                         { 
                              playCash();
                              fade1 = 0;
                         }
                         else if(collectCoin(coins4[4] * 32 + 8, coins4[5] * 32 + 8, fade2))
                         { 
                              playCash();
                              fade2 = 0;
                         }
                         else if(collectCoin(coins4[6] * 32 + 8, coins4[7] * 32 + 8, fade3))
                         { 
                              playCash();
                              fade3 = 0;
                         }
                         if(fadeAway)
                              regenerate();
                         repaint();
                         break;
                    case 10:
                         move5();
                         if(collectCoin(coins5[0] * 32 + 8, coins5[1] * 32 + 8, fade))
                         { 
                              playCash();
                              fade = 0;
                         }
                         else if(collectCoin(coins5[2] * 32 + 8, coins5[3] * 32 + 8, fade1))
                         { 
                              playCash();
                              fade1 = 0;
                         }
                         else if(collectCoin(coins5[4] * 32 + 8, coins5[5] * 32 + 8, fade2))
                         { 
                              playCash();
                              fade2 = 0;
                         }
                         else if(collectCoin(coins5[6] * 32 + 8, coins5[7] * 32 + 8, fade3))
                         { 
                              playCash();
                              fade3 = 0;
                         }
                         if(fadeAway)
                              regenerate();
                         repaint();
                         break;
               }
          }
          
          public void regenerate()
          {
               alpha += -0.1f;
               if (alpha <= 0)
               {
                    alpha = 1;
                    reset();
               }
          }
          
          public void reset()
          {
               playCollide();
               death += 1;
               lblDeath.setText("DEATHS:  " + death);
               fadeAway = false;
               //Writes death number into account
               try
               {
                    PrintWriter outFile = new PrintWriter(username + "-Death.txt");
                    outFile.println(death);
                    outFile.close();
               }
               catch(IOException e){}
               
               if(!Menu.cheat)
               {
                    switch(level)
                    {
                         case 6:
                              p.setTileX(8);
                              p.setTileY(7);
                              coins = 4;
                              fade = 1;
                              fade1 = 1;
                              fade2= 1;
                              fade3 = 1;
                              break;
                         case 7:
                              p.setTileX(2);
                              p.setTileY(4);
                              coins = 4;
                              fade = 1;
                              fade1 = 1;
                              fade2= 1;
                              fade3 = 1;
                              break;
                         case 8:
                              p.setTileX(22);
                              p.setTileY(8);
                              coins = 7;
                              fade = 1;
                              fade1 = 1;
                              fade2= 1;
                              fade3 = 1;
                              fade4 = 1;
                              fade5 = 1;
                              fade6 = 1;
                              break;
                         case 9:
                              p.setTileX(10);
                              p.setTileY(9);
                              coins = 4;
                              fade = 1;
                              fade1 = 1;
                              fade2 = 1;
                              fade3 = 1;
                              break;
                         case 10: 
                              p.setTileX(8);
                              p.setTileY(7);
                              coins = 4;
                              fade = 1;
                              fade1 = 1;
                              fade2 = 1;
                              fade3 = 1;
                              break; 
                    }
               }
          }
          
          public void levelComplete()
          {
               switch(level)
               {
                    case 6:
                         if((coins == 0) && ((p.getTileX() == 7 || p.getTileX() == 8 || p.getTileX() == 9 || p.getTileX() == 10) && (p.getTileY() == 7 || p.getTileY() == 8)))
                    {
                         f.setVisible(false);
                         f.dispose();
                         levi.setLevel(level+1);
                         playLevel();
                         try
                         {
                              PrintWriter writer = new PrintWriter("death.txt");
                              writer.println(death);
                              writer.close();
                         }catch(IOException e){}
                         levelChange();
                         new Medium();
                         break;
                    }
                    case 7:
                         if((coins == 0) && ((p.getTileX() == 20 || p.getTileX() == 21) && (p.getTileY() == 4 || p.getTileY() == 5)))
                    {
                         f.setVisible(false);
                         f.dispose();
                         playLevel();
                         levi.setLevel(level+1);
                         try
                         {
                              PrintWriter writer = new PrintWriter("death.txt");
                              writer.println(death);
                              writer.close();
                         }catch(IOException e){}
                         levelChange();
                         new Medium();
                         break;
                    }
                    case 8:
                         if((coins == 0) && (p.getTileX() == 3 && (p.getTileY() == 1 || p.getTileY() == 2)))
                    {
                         f.setVisible(false);
                         f.dispose();
                         playLevel();
                         levi.setLevel(level+1);
                         try
                         {
                              PrintWriter writer = new PrintWriter("death.txt");
                              writer.println(death);
                              writer.close();
                         }catch(IOException e){}
                         levelChange();
                         new Medium();
                         break;
                    }
                    case 9:
                         if((coins == 0) && ((p.getTileX() == 9 || p.getTileX() == 10 || p.getTileX() == 11 || p.getTileX() == 12) && (p.getTileY() == 7 || p.getTileY() == 8 || p.getTileY() == 9 || p.getTileY() == 10)))
                    {
                         f.setVisible(false);
                         f.dispose();
                         playLevel();
                         levi.setLevel(level+1);
                         try
                         {
                              PrintWriter writer = new PrintWriter("death.txt");
                              writer.println(death);
                              writer.close();
                         }catch(IOException e){}
                         levelChange();
                         new Medium();
                         break;
                    }
                    case 10:
                         if((coins == 0) && ((p.getTileX() == 8 || p.getTileX() == 9) && (p.getTileY() == 7 || p.getTileY() == 8)))
                         playLevel();
                         break;
               } 
          }   
          
          public void levelChange()
          {
               levi.setLevel(level + 1);
               
               try
               {
                    if(!Menu.cheat)
                         outFile = new PrintWriter(username + "-Level.txt");
                    else
                         outFile = new PrintWriter(username + "-CheatLevel.txt");
                    outFile.println(level + 1);
                    outFile.close();
               }
               catch (IOException i)
               {
                    
               }
          }
          
          public boolean collectCoin(int x, int y, float f)
          {
               if(f == 1)
               {
                    int xDistance = Math.abs(x - (p.getTileX() * 32 + 16));
                    int yDistance = Math.abs(y - (p.getTileY() * 32 + 16));
                    
                    if (xDistance > 18)
                         return false; 
                    if (yDistance > 18)
                         return false;
                    if (xDistance <= 10)
                    {
                         if(coins > 0)
                              coins -= 1;
                         return true; 
                    }
                    if (yDistance <= 10)
                    {
                         if(coins > 0)
                              coins -= 1;
                         return true; 
                    }
                    double cornerDistance = Math.pow(xDistance - 10, 2) + Math.pow(yDistance - 10, 2);
                    
                    if(cornerDistance <= 64)
                    {
                         if(coins > 0)
                              coins -= 1;
                         return true;
                    }
               }
               return false;
          }
          
          public boolean collision(int x, int y)
          {
               int xDistance = Math.abs(x - (p.getTileX() * 32 + 16));
               int yDistance = Math.abs(y - (p.getTileY() * 32 + 16));
               
               if (xDistance > 18)
                    return false; 
               if (yDistance > 18)
                    return false;
               if (xDistance <= 10)
                    return true; 
               if (yDistance <= 10)
                    return true; 
               
               double cornerDistance = Math.pow(xDistance - 10, 2) + Math.pow(yDistance - 10, 2);
               
               return (cornerDistance <= 64);
          }
          
          public void ballsMoving()
          {
               if(yBallDown == 136 && yBallUp == 360)
                    moveVertical *= -1;
               
               if(xBallTop == 136 && xBallBottom == 424)
                    moveHorizontal *= -1;
               
               if(yBallSmallU == 200 && yBallSmallD == 72)
                    move *= -1;
               
               yBallDown += moveVertical;
               yBallUp -= moveVertical;
               xBallTop += moveHorizontal;
               xBallBottom -= moveHorizontal;
               yBallSmallU -= move;
               yBallSmallD += move;
               
               
               if(yBallDown == 360 && yBallUp == 136) 
                    moveVertical *= -1;
               
               if(xBallTop == 424 && xBallBottom == 136)
                    moveHorizontal *= -1;
               
               if(yBallSmallU == 72 && yBallSmallD == 200)
                    move *= -1;
          }
          
          public void squares()
          {
               if(ySquare == 72 && xSquare < 168)
                    xSquare += 2;
               if(xSquare == 168 && ySquare < 104)
                    ySquare += 2;
               if(ySquare == 104 && xSquare > 136)
                    xSquare -= 2;
               if(xSquare == 136 && ySquare > 72)
                    ySquare -= 2;
          }
          
          int moveSlowlyUp = -1;
          int moveSlowlyDown = -1;
          
          public void move3()
          {
               if(yUp == 200)
                    move *= -1;
               if(xSide == 552)
                    moveSide *= -1;
               
               yUp -= move;
               xSide += moveSide;
               
               if(yUp == 40)
                    move *= -1;
               if(xSide == 680)
                    moveSide *= -1;
               
          }
          
          public void move4()
          {
               if(yVUp == 72)
                    moveSlowlyDown *= -1;
               if(yVDown == 168)
                    moveSlowlyUp *= -1;
               if(yTop == 264)
                    move *= -1;
               if(yBottom == 328)
                    moveSide *= -1;
               
               yVUp += moveSlowlyDown;
               yVDown -= moveSlowlyUp;
               yTop += move;
               yBottom -= moveSide;
               
               if(yVUp == 136)
                    moveSlowlyDown *= -1;
               if(yVDown == 104)
                    moveSlowlyUp *= -1;
               if(yTop == 328)
                    move *= -1;
               if(yBottom == 264)
                    moveSide *= -1;
          }
          
          int moveFastRight = -6;
          int moveFastLeft = -6;
          
          public void move5()
          {
               if(xRight == 40)
                    moveFastRight *= -1;
               if(xLeft == 520)
                    moveFastLeft *= -1;
               
               xRight += moveFastRight;
               xLeft -= moveFastLeft; 
               
               if(xRight == 520)
                    moveFastRight *= -1;
               if(xLeft == 40)
                    moveFastLeft *= -1;
          }
          
          public void paint(Graphics g)
          {
               ((Graphics2D) g).setRenderingHint(
                                                 RenderingHints.KEY_ANTIALIASING,
                                                 RenderingHints.VALUE_ANTIALIAS_ON);
               
               super.paint(g);
               Graphics2D g2d = (Graphics2D) g;
               
               //Loop through number of rows in text file
               for(int y=0; y < maze.getColumn(); y++)
               {
                    //Loop through each letter in a row
                    for(int x=0; x < rows; x++)
                    {
                         if(maze.getMazeTile(x,y).equals("f"))
                         {
                              //Draw tile and position it so that it occupies desired space
                              g.drawImage(maze.getFloor(), x * 32, y * 32, null);
                         }
                         //If tile at current position is wall
                         else if(maze.getMazeTile(x,y).equals("w"))
                         {
                              g.drawImage(maze.getWall(), x * 32, y * 32, null);
                         }
                         //If tile at current position is green zone - start or end
                         else if(maze.getMazeTile(x,y).equals("g"))
                         {
                              g.drawImage(maze.getZone(), x * 32, y * 32, null);
                         }
                         //If tile at current position is barrier
                         else if(maze.getMazeTile(x,y).equals("b"))
                         {
                              g.drawImage(maze.getBarrier(), x * 32, y * 32, null);
                         }//end if
                    }//end for
               }//end for
               
               switch(level)
               {
                    case 6:
                         //Up and down left side   
                         g.drawImage(blueBalls.getBall(), xBallDown, yBallDown, null);
                         if(collision(xBallDown, yBallDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBallDown + 32, yBallUp, null);
                         if(collision(xBallDown + 32, yBallUp))
                              fadeAway = true;
                         
                         //Up and down right side
                         g.drawImage(blueBalls.getBall(), xBallDown + 384, yBallUp, null);
                         if(collision(xBallDown + 384, yBallUp))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBallDown + 414, yBallDown, null);
                         if(collision(xBallDown + 414, yBallDown))
                              fadeAway = true;
                         
                         //Top side to side
                         g.drawImage(blueBalls.getBall(), xBallTop, yBallTop, null);
                         if(collision(xBallTop, yBallTop))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBallBottom, yBallTop + 32, null);
                         if(collision(xBallBottom, yBallTop + 32))
                              fadeAway = true;
                         
                         //Bottom side to side
                         g.drawImage(blueBalls.getBall(), xBallBottom, yBallTop + 320, null);
                         if(collision(xBallBottom, yBallTop + 320))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBallTop, yBallTop + 352, null);
                         if(collision(xBallTop, yBallTop + 352))
                              fadeAway = true;
                         
                         //Mini down left side
                         g.drawImage(blueBalls.getBall(), xBallSmall, yBallSmallD, null);
                         if(collision(xBallSmall, yBallSmallD))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBallSmall + 32, yBallSmallU, null);
                         if(collision(xBallSmall + 32, yBallSmallU))
                              fadeAway = true;
                         
                         //Mini down right side
                         g.drawImage(blueBalls.getBall(), xBallSmall + 128, yBallSmallU, null);
                         if(collision(xBallSmall + 128, yBallSmallU))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBallSmall + 160, yBallSmallD, null);
                         if(collision(xBallSmall + 160, yBallSmallD))
                              fadeAway = true;
                         
                         //Mini down bottom left
                         g.drawImage(blueBalls.getBall(), xBallSmall, yBallSmallD + 224, null);
                         if(collision(xBallSmall, yBallSmallD + 224))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBallSmall + 32, yBallSmallU + 224, null);
                         if(collision(xBallSmall + 32, yBallSmallU + 224))
                              fadeAway = true;
                         
                         //Mini down bottom right            
                         g.drawImage(blueBalls.getBall(), xBallSmall + 128, yBallSmallD + 224, null);
                         if(collision(xBallSmall + 128, yBallSmallD + 224))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBallSmall + 160, yBallSmallU + 224, null);
                         if(collision(xBallSmall + 160, yBallSmallU + 224))
                              fadeAway = true;
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
                         g2d.drawImage(c.getCoin(), coins1[0] * 32 + 8, coins1[1] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade1));
                         g2d.drawImage(c.getCoin(), coins1[2] * 32 + 8, coins1[3] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade2));
                         g2d.drawImage(c.getCoin(), coins1[4] * 32 + 8, coins1[5] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade3));
                         g2d.drawImage(c.getCoin(), coins1[6] * 32 + 8, coins1[7] * 32 + 8, null);
                         break;
                    case 7:
                         g.drawImage(blueBalls.getBall(), xSquare, ySquare, null);
                         if(collision(xSquare, ySquare))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 64, ySquare, null);
                         if(collision(xSquare + 64, ySquare))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 192, ySquare, null);
                         if(collision(xSquare + 192, ySquare))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 256, ySquare, null);
                         if(collision(xSquare + 256, ySquare))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 320, ySquare, null);
                         if(collision(xSquare + 320, ySquare))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 384, ySquare, null);
                         if(collision(xSquare + 384, ySquare))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 448, ySquare, null);
                         if(collision(xSquare + 448, ySquare))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare, ySquare + 64, null);
                         if(collision(xSquare, ySquare + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 64, ySquare + 64, null);
                         if(collision(xSquare + 64, ySquare + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 192, ySquare + 64, null);
                         if(collision(xSquare + 192, ySquare + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 256, ySquare + 64, null);
                         if(collision(xSquare + 256, ySquare + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 384, ySquare + 64, null);
                         if(collision(xSquare + 384, ySquare + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 448, ySquare + 64, null);
                         if(collision(xSquare + 448, ySquare + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare, ySquare + 128, null);
                         if(collision(xSquare, ySquare + 128))
                              fadeAway = true;
                         
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 64, ySquare + 128, null);
                         if(collision(xSquare + 64, ySquare + 128))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 128, ySquare + 128, null);
                         if(collision(xSquare + 128, ySquare + 128))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 192, ySquare + 128, null);
                         if(collision(xSquare + 192, ySquare + 128))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 256, ySquare + 128, null);
                         if(collision(xSquare + 256, ySquare + 128))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 384, ySquare + 128, null);
                         if(collision(xSquare + 384, ySquare + 128))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 448, ySquare + 128, null);
                         if(collision(xSquare + 448, ySquare + 128))
                              fadeAway = true;
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
                         g2d.drawImage(c.getCoin(), coins2[0] * 32 + 8, coins2[1] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade1));
                         g2d.drawImage(c.getCoin(), coins2[2] * 32 + 8, coins2[3] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade2));
                         g2d.drawImage(c.getCoin(), coins2[4] * 32 + 8, coins2[5] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade3));
                         g2d.drawImage(c.getCoin(), coins2[6] * 32 + 8, coins2[7] * 32 + 8, null);
                         break;
                    case 8:
                         g.drawImage(blueBalls.getBall(), xUp, yUp, null);   
                         if(collision(xUp, yUp))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xUp - 128, yUp, null);   
                         if(collision(xUp - 128, yUp))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xUp - 224, yUp, null);   
                         if(collision(xUp - 224, yUp))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xUp - 352, yUp, null);   
                         if(collision(xUp - 352, yUp))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xUp - 448, yUp, null);
                         if(collision(xUp - 448, yUp))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSide, ySide, null);   
                         if(collision(xSide, ySide))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSide, ySide - 96, null);   
                         if(collision(xSide, ySide - 96))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSide - 96, ySide - 64, null);   
                         if(collision(xSide - 96, ySide - 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSide - 96, ySide - 160, null);   
                         if(collision(xSide - 96, ySide - 160))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSide - 224, ySide, null);   
                         if(collision(xSide - 224, ySide))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSide - 224, ySide - 96, null);   
                         if(collision(xSide - 224, ySide - 96))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSide - 320, ySide - 64, null);   
                         if(collision(xSide - 320, ySide - 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSide - 320, ySide - 160, null);   
                         if(collision(xSide - 320, ySide - 160))
                              fadeAway = true;  
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
                         g2d.drawImage(c.getCoin(), coins3[0] * 32 + 8, coins3[1] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade1));
                         g2d.drawImage(c.getCoin(), coins3[2] * 32 + 8, coins3[3] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade2));
                         g2d.drawImage(c.getCoin(), coins3[4] * 32 + 8, coins3[5] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade3));
                         g2d.drawImage(c.getCoin(), coins3[6] * 32 + 8, coins3[7] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade4));
                         g2d.drawImage(c.getCoin(), coins3[8] * 32 + 8, coins3[9] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade5));
                         g2d.drawImage(c.getCoin(), coins3[10] * 32 + 8, coins3[11] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade6));
                         g2d.drawImage(c.getCoin(), coins3[12] * 32 + 8, coins3[13] * 32 + 8, null);    
                         break;
                         
                    case 9:
                         g.drawImage(blueBalls.getBall(), xVUp, yVUp, null);
                         if(collision(xVUp, yVUp))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xVUp + 32, yVDown, null);
                         if(collision(xVUp + 32, yVDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xVUp + 64, yVUp + 64, null);
                         if(collision(xVUp + 64, yVUp + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xVUp + 96, yVDown + 64, null);
                         if(collision(xVUp + 96, yVDown + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xVUp + 128, yVUp + 128, null);
                         if(collision(xVUp + 128, yVUp + 128))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xVUp + 224, yVUp + 128, null);
                         if(collision(xVUp + 224, yVUp + 128))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xVUp + 256, yVDown + 64, null);
                         if(collision(xVUp + 256, yVDown + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xVUp + 288, yVUp + 64, null);
                         if(collision(xVUp + 288, yVUp + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xVUp + 320, yVDown, null);
                         if(collision(xVUp + 320, yVDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xVUp + 352, yVUp, null);
                         if(collision(xVUp + 352, yVUp))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop, yTop, null);
                         if(collision(xTop, yTop))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 32, yBottom, null);
                         if(collision(xTop + 32, yBottom))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 64, yTop, null);
                         if(collision(xTop + 64, yTop))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 96, yBottom, null);
                         if(collision(xTop + 96, yBottom))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 128, yTop, null);
                         if(collision(xTop + 128, yTop))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 160, yBottom, null);
                         if(collision(xTop + 160, yBottom))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 192, yTop, null);
                         if(collision(xTop + 192, yTop))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 224, yBottom, null);
                         if(collision(xTop + 224, yBottom))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 384, yBottom, null);
                         if(collision(xTop + 384, yVUp))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 416, yTop, null);
                         if(collision(xTop + 416, yTop))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 448, yBottom, null);
                         if(collision(xTop + 448, yBottom))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 480, yTop, null);
                         if(collision(xTop + 480, yTop))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 512, yBottom, null);
                         if(collision(xTop + 512, yBottom))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 544, yTop, null);
                         if(collision(xTop + 544, yTop))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 576, yBottom, null);
                         if(collision(xTop + 576, yBottom))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xTop + 608, yTop, null);
                         if(collision(xTop + 608, yTop))
                              fadeAway = true;
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
                         g2d.drawImage(c.getCoin(), coins4[0] * 32 + 8, coins4[1] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade1));
                         g2d.drawImage(c.getCoin(), coins4[2] * 32 + 8, coins4[3] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade2));
                         g2d.drawImage(c.getCoin(), coins4[4] * 32 + 8, coins4[5] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade3));
                         g2d.drawImage(c.getCoin(), coins4[6] * 32 + 8, coins4[7] * 32 + 8, null);
                         break;
                    case 10:
                         g.drawImage(blueBalls.getBall(), 40, 136, null);
                         if(collision(40, 136))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 72, 104, null);
                         if(collision(72, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 104, 72, null);
                         if(collision(104, 72))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 136, 40, null);
                         if(collision(136, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 424, 40, null);
                         if(collision(424, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 456, 72, null);
                         if(collision(456, 72))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 488, 104, null);
                         if(collision(488, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 520, 136, null);
                         if(collision(520, 136))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 40, 360, null);
                         if(collision(40, 360))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 72, 392, null);
                         if(collision(72, 392))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 104, 424, null);
                         if(collision(104, 424))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 136, 456, null);
                         if(collision(136, 456))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 520, 360, null);
                         if(collision(520, 360))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 488, 392, null);
                         if(collision(488, 392))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 456, 424, null);
                         if(collision(456, 424))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 424, 456, null);
                         if(collision(424, 456))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 104, 168, null);
                         if(collision(104, 168))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 136, 136, null);
                         if(collision(136, 136))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 168, 104, null);
                         if(collision(168, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 200, 104, null);
                         if(collision(200, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 232, 104, null);
                         if(collision(232, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 264, 104, null);
                         if(collision(264, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 296, 104, null);
                         if(collision(296, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 328, 104, null);
                         if(collision(328, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 360, 104, null);
                         if(collision(360, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 392, 104, null);
                         if(collision(392, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 424, 136, null);
                         if(collision(424, 136))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 456, 168, null);
                         if(collision(456, 168))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 104, 328, null);
                         if(collision(104, 328))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 136, 360, null);
                         if(collision(136, 360))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 168, 392, null);
                         if(collision(168, 392))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 200, 392, null);
                         if(collision(200, 392))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 232, 392, null);
                         if(collision(232, 392))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 264, 392, null);
                         if(collision(264, 392))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 296, 392, null);
                         if(collision(296, 392))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 328, 392, null);
                         if(collision(328, 392))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 360, 392, null);
                         if(collision(360, 392))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 392, 392, null);
                         if(collision(392, 392))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 424, 360, null);
                         if(collision(424, 360))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 456, 328, null);
                         if(collision(456, 328))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xRight, 40, null);
                         if(collision(xRight, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xRight, 72, null);
                         if(collision(xRight, 72))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xRight, 104, null);
                         if(collision(xRight, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xRight, 136, null);
                         if(collision(xRight, 136))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xRight, 168, null);
                         if(collision(xRight, 168))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xLeft, 328, null);
                         if(collision(xLeft, 328))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xLeft, 360, null);
                         if(collision(xLeft, 360))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xLeft, 392, null);
                         if(collision(xLeft, 392))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xLeft, 424, null);
                         if(collision(xLeft, 424))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xLeft, 456, null);
                         if(collision(xLeft, 456))
                              fadeAway = true;
                         
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
                         g2d.drawImage(c.getCoin(), coins5[0] * 32 + 8, coins5[1] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade1));
                         g2d.drawImage(c.getCoin(), coins5[2] * 32 + 8, coins5[3] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade2));
                         g2d.drawImage(c.getCoin(), coins5[4] * 32 + 8, coins5[5] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade3));
                         g2d.drawImage(c.getCoin(), coins5[6] * 32 + 8, coins5[7] * 32 + 8, null);
                         break;
                         
               }
               
               g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
               g2d.drawImage(p.getPlayer(), p.getTileX() * 32 + 6, p.getTileY() * 32 + 6, null);
          }
          
          public class Move extends KeyAdapter
          {
               public void keyPressed(KeyEvent e)
               {
                    int keycode = e.getKeyCode();
                    
                    if(!Menu.cheat)
                    {
                         if(keycode == KeyEvent.VK_UP && alpha == 1f)
                         {
                              if(!maze.getMazeTile(p.getTileX(), p.getTileY() - 1).equals("w"))
                                   p.move(0, -1);
                         }
                         else if(keycode == KeyEvent.VK_DOWN && alpha == 1f)
                         {
                              if(!maze.getMazeTile(p.getTileX(), p.getTileY() + 1).equals("w"))
                                   p.move(0, 1);
                         }
                         else if(keycode == KeyEvent.VK_LEFT && alpha == 1f)
                         {
                              if(!maze.getMazeTile(p.getTileX() - 1, p.getTileY()).equals("w"))
                                   p.move(-1, 0);
                         }
                         else if(keycode == KeyEvent.VK_RIGHT && alpha == 1f)
                         {
                              if(!maze.getMazeTile(p.getTileX() + 1, p.getTileY()).equals("w"))
                                   p.move(1, 0);
                         }
                    }
                    else
                    {
                         if(keycode == KeyEvent.VK_UP)
                         {
                              if(!maze.getMazeTile(p.getTileX(), p.getTileY() - 1).equals("w"))
                                   p.move(0, -1);
                         }
                         else if(keycode == KeyEvent.VK_DOWN)
                         {
                              if(!maze.getMazeTile(p.getTileX(), p.getTileY() + 1).equals("w"))
                                   p.move(0, 1);
                         }
                         else if(keycode == KeyEvent.VK_LEFT)
                         {
                              if(!maze.getMazeTile(p.getTileX() - 1, p.getTileY()).equals("w"))
                                   p.move(-1, 0);
                         }
                         else if(keycode == KeyEvent.VK_RIGHT)
                         {
                              if(!maze.getMazeTile(p.getTileX() + 1, p.getTileY()).equals("w"))
                                   p.move(1, 0);
                         }
                    }
                    
                    levelComplete();
               }
               
               public void keyReleased(KeyEvent e)
               {
                    
               }
               
               public void keyTyped(KeyEvent e)
               {
                    
               }
          }
     }//end DrawMaze*/
}