import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import sun.audio.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class MazeGame
{
     static String username = Menu.username;
     private JLabel lblDeath;
     private JFrame f;
     static Level levi = new Level();
     static boolean isLoad = Menu.isLoad;
     public int death;
     static int level;
     Scanner scanFile;
     PrintWriter outFile;
     /* Constructs frame and calls DrawMaze to do just that
      * */
     public MazeGame()
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
          f.setTitle(username + "'s Maze Game");
          
          
          JPanel canvas = new JPanel();
          canvas.setLayout(new BoxLayout(canvas, BoxLayout.Y_AXIS));
          
          JPanel pnl = new JPanel();
          pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
          pnl.setBackground(Color.BLACK);
          
          JLabel lbl = new JLabel();
          lbl.setForeground(Color.WHITE);
          lbl.setText("MODE: EASY");
          lbl.setFont(new Font("Calibri", Font.BOLD, 25));
          
          JLabel lblLevel = new JLabel();
          lblLevel.setForeground(Color.WHITE);
          
          lblLevel.setText(level + " / 5");
          lblLevel.setFont(new Font("Calibri", Font.BOLD, 25));
          
          lblDeath = new JLabel();
          lblDeath.setForeground(Color.WHITE);
          
          
          lblDeath.setText("DEATHS:  " + death);
          lblDeath.setFont(new Font("Calibri", Font.BOLD, 25));
          
          
          switch(level)
          {
               case 1: 
                    pnl.setPreferredSize(new Dimension(720, 30));
                    lbl.setPreferredSize(new Dimension(120, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(150, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(215, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(175, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(25, 0)));
                    f.setSize(720, 421);
                    break;
               case 2: 
                    pnl.setPreferredSize(new Dimension(497, 30));
                    lbl.setPreferredSize(new Dimension(125, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(150, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(105, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(60, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(22, 0)));
                    f.setSize(497, 550);
                    break;
               case 3:
                    pnl.setPreferredSize(new Dimension(530, 30));
                    lbl.setPreferredSize(new Dimension(125, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(150, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(120, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(75, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(25, 0)));
                    f.setSize(530, 518);
                    break;
               case 4:
                    pnl.setPreferredSize(new Dimension(723, 30));
                    lbl.setPreferredSize(new Dimension(125, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(150, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(217, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(165, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(20, 0)));
                    f.setSize(723, 518);
                    break;
               case 5:
                    pnl.setPreferredSize(new Dimension(723, 30));
                    lbl.setPreferredSize(new Dimension(125, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(150, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(220, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(160, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(30, 0)));
                    f.setSize(723, 518);
                    break;
          }
          canvas.add(pnl);
          canvas.add(new DrawMaze());
          f.add(canvas);
          f.setLocationRelativeTo(null);
          f.setVisible(true);
          f.setResizable(false);
          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }//end MazeGameEasy
     
     class DrawMaze extends JPanel implements ActionListener
     {
          //Create MazeLayout object to retrieve information about positioning
          private Timer timer;
          private MazeLayout maze;
          private Player p;
          private Obstacle blueBalls; 
          private Coin c;
          private int level;
          private int coins, rows;
          private Image[] balls;
          private JLabel lbl;
          
          //Level 1
          private int[] balls_top, balls_bottom;
          private float alpha = 1f, fade = 1f;
          private int yballUp = 72;
          private int yballDown = 264;
          private int move = -3;
          
          //Level 2
          private int[] coins2, coins3, coins4;
          private double angleUp = Math.PI / -2;
          private double angleRight = 0;
          private double angleDown = Math.PI / 2;
          private double angleLeft = Math.PI;
          private int xCircleUp, xCircleDown, xCircleLeft, xCircleRight;
          private int yCircleUp, yCircleDown, yCircleLeft, yCircleRight;
          private int fade1 = 1, fade2 = 1, fade3 = 1, fade4 = 1;
          private boolean fadeAway = false;
          
          
          //Level 3
          private int xUp1, xUp2, xUp3, xUp4, xUp5, xUp6, xUp7, xUp8;
          private int xDown1, xDown2, xDown3, xDown4, xDown5, xDown6, xDown7, xDown8;
          private int xRight1, xRight2, xRight3, xRight4, xRight5, xRight6, xRight7, xRight8; 
          private int xLeft1, xLeft2, xLeft3, xLeft4, xLeft5, xLeft6, xLeft7, xLeft8; 
          
          private int yUp1, yUp2, yUp3, yUp4, yUp5, yUp6, yUp7, yUp8;
          private int yUpA, yDownA, yRightA, yLeftA, yUpB, yDownB, yRightB, yLeftB;
          private int yUpC, yDownC, yRightC, yLeftC, yUpD, yDownD, yRightD, yLeftD;
          private int yDown1, yDown2, yDown3, yDown4, yDown5, yDown6, yDown7, yDown8;
          private int yRight1, yRight2, yRight3, yRight4, yRight5, yRight6, yRight7, yRight8; 
          private int yLeft1, yLeft2, yLeft3, yLeft4, yLeft5, yLeft6, yLeft7, yLeft8;
          private boolean spawn = false;
          
          //Level 4
          private int xBallLeft = 72;
          private int xBallRight = 360;
          private int yBall = 72;
          private int yLong = 104, xLong = 168;
          
          //Level 5
          private int xSquare = 72;
          private int ySquare = 72;
          private int xLTop = 328;
          private int yLTop = 232;
          private int xLSide = 488;
          private int yLSide = 328;
          private boolean putitdownonme = false;
          private boolean crossice = false;
          private boolean cross = false;
          private boolean down = false;
          
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
               
               balls = blueBalls.getBalls();
               
               switch(level)
               {
                    case 1:
                         balls_top = blueBalls.getTilesUp();
                         balls_bottom = blueBalls.getTilesDown();
                         rows = 22;
                         coins = 1;
                         if(Menu.cheat)
                              timer = new Timer(50, this);
                         else
                              timer = new Timer(25, this);
                         timer.start();
                         break;
                    case 2: 
                         rows = 15;
                         coins = 3;
                         coins2 = c.getCoins2();
                         if(Menu.cheat)
                              timer = new Timer(150, this);
                         else 
                              timer = new Timer(75, this);
                         timer.start();
                         break;
                    case 3:
                         rows = 16;
                         coins = 3;
                         coins4 = c.getCoins4();
                         if(Menu.cheat)
                              timer = new Timer(100, this);
                         else 
                              timer = new Timer(50, this);
                         timer.start();
                         break;
                    case 4:
                         rows = 22;
                         coins = 1;
                         if(Menu.cheat)
                              timer = new Timer(100, this);
                         else
                              timer = new Timer(50, this);
                         timer.start();
                         break;
                    case 5:
                         rows = 22;
                         coins = 4;
                         coins3 = c.getCoins3();
                         if(Menu.cheat)
                              timer = new Timer(200, this);
                         else
                              timer = new Timer(100, this);
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
                    // Create an AudioStream object from the input stream.
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
                    case 1:
                         ballsMoving();
                         
                         if(collectCoin(c.getCoinX() * 32 + 32, c.getCoinY() * 32 + 16, fade))
                         {
                              playCash();
                              fade = 0;
                         }
                         if(fadeAway)
                              regenerate();
                         
                         
                         
                         repaint();
                         break;
                    case 2:
                         angleDown += 0.1;
                         angleLeft += 0.1;
                         angleUp += 0.1;
                         angleRight += 0.1;
                         
                         if(collectCoin(coins2[0] * 32 + 32, coins2[1] * 32 + 16, fade1))
                         {
                              playCash();
                              fade1 = 0;
                         }
                         else if(collectCoin(coins2[2] * 32 + 16, coins2[3] * 32 + 32, fade2))
                         {     
                              playCash();
                              fade2 = 0;
                         }
                         else if(collectCoin(coins2[4] * 32 + 32, coins2[5] * 32 + 16, fade3))
                         {   
                              playCash();
                              fade3 = 0;
                         }
                         if(fadeAway)
                              regenerate();
                         
                         repaint();
                         break;
                    case 3:
                         breakOut();
                         loneRanger();
                         if(collectCoin(coins4[0] * 32 + 16, coins4[1] * 32 + 16, fade1))
                         {   
                              playCash();
                              fade1 = 0;
                         }
                         else if(collectCoin(coins4[2] * 32 + 16, coins4[3] * 32 + 16, fade2))
                         {   
                              playCash();
                              fade2 = 0;
                         }
                         else if(collectCoin(coins4[4] * 32 + 16, coins4[5] * 32 + 16, fade3))
                         {   
                              playCash();
                              fade3 = 0;
                         }
                         
                         if(fadeAway)
                              regenerate();
                         repaint();
                         break;
                    case 4:
                         squares();
                         lShape();
                         lSide();
                         
                         if(collectCoin(c.getCoinX() * 32 + 16, c.getCoinY() * 32 + 16, fade))
                         { 
                              playCash();
                              fade = 0;
                         }
                         if(fadeAway)
                              regenerate();
                         
                         repaint();
                         break;
                    case 5:
                         angleDown += 0.1;
                         angleLeft += 0.1;
                         angleUp += 0.1;
                         angleRight += 0.1;
                         
                         if(collectCoin(coins3[0] * 32 + 16, coins3[1] * 32 + 16, fade1))
                         {   
                              playCash();
                              fade1 = 0;
                         }
                         else if(collectCoin(coins3[2] * 32 + 16, coins3[3] * 32 + 16, fade2))
                         {   
                              playCash();
                              fade2 = 0;
                         }
                         else if(collectCoin(coins3[4] * 32 + 16, coins3[5] * 32 + 16, fade3))
                         {   
                              playCash();
                              fade3 = 0;
                         }
                         else if(collectCoin(coins3[6] * 32 + 16, coins3[7] * 32 + 16, fade4))
                         {   
                              playCash();
                              fade4 = 0;
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
               death+=1;
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
                         case 1:
                              p.setTileX(3);
                              p.setTileY(5);
                              c.setCoinX(10);
                              c.setCoinY(5);
                              coins = 1;
                              fade = 1;
                              break;
                         case 2:
                              p.setTileX(8);
                              p.setTileY(2);
                              coins = 3;
                              fade1 = 1;
                              fade2 = 1;
                              fade3 = 1;
                              break;
                         case 3:
                              p.setTileX(3);
                              p.setTileY(3);
                              coins = 3;
                              fade1 = 1;
                              fade2 = 1;
                              fade3 = 1;
                              break;
                         case 4:
                              if(!spawn)
                         { 
                              p.setTileX(2);
                              p.setTileY(2);
                              coins = 1;
                              fade = 1;
                         }
                              else
                              {
                                   p.setTileX(10);
                                   p.setTileY(8);
                                   coins = 1;
                                   fade = 1;
                                   
                              }
                              break;
                         case 5:
                              if(!spawn)
                         {
                              p.setTileX(2);
                              p.setTileY(2);
                              coins = 4;
                              fade1 = 1;
                              fade2 = 1;
                              fade3 = 1;
                              fade4 = 1;
                              
                         }
                              else
                              {
                                   p.setTileX(16);
                                   p.setTileY(6);
                                   fade1 = 1;
                                   fade2 = 1;
                                   fade3 = 1;
                                   fade4 = 1;
                                   coins = 3;
                                   
                              }
                              break;
                    }
               }
          }
          
          public void levelComplete()
          {
               switch(level)
               {
                    case 1:
                         f.setVisible(false);
                         f.dispose();
                         playLevel();
                         levelChange();
                         
                         new MazeGame();
                         break;
                         
                    case 2:
                    {
                         f.setVisible(false);
                         f.dispose();
                         playLevel();
                         levelChange();
                         new MazeGame();
                         break;
                    }
                    case 3:
                         
                    {
                         f.setVisible(false);
                         f.dispose();
                         playLevel(); 
                         levelChange();
                         new MazeGame();
                         break;
                    }
                    case 4:
                    {
                         f.setVisible(false);
                         f.dispose();
                         playLevel();  
                         levelChange();
                         new MazeGame();
                         break;
                    }
                    case 5:
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
          
          //Level 1
          public void ballsMoving()
          {
               if(yballUp == 72 && yballDown == 264)
                    move *= -1;
               
               yballUp += move;
               yballDown -= move;
               
               if(yballUp == 264 && yballDown == 72)
                    move *= -1;
          }
          
          public void breakOut()
          {
               if(xBallLeft < 168 && xBallRight > 264 && yBall == 72)
               {
                    xBallLeft += 4;
                    xBallRight -= 4;
               }
               
               if(xBallLeft == 168 && xBallRight == 264 && yBall < 168)
               {
                    xBallLeft = 168;
                    xBallRight = 264;
                    yBall += 4;
               }
               
               if(yBall == 168 && xBallLeft > 72 && xBallRight < 360)
               {
                    yBall = 168;
                    xBallLeft -= 4;
                    xBallRight += 4;
               }
               
               if(xBallLeft == 72 && xBallRight == 360 && yBall > 72)
                    yBall -= 4;
          }
          
          public void loneRanger()
          {
               if(yLong == 104 && xLong < 264)
                    xLong += 4;
               
               if(xLong == 264 && yLong < 328)
                    yLong += 4;
               
               if(yLong == 328 && xLong > 168)
                    xLong -= 4;
               
               if(xLong == 168 && yLong > 104)
                    yLong -= 4;
          }
          
          public void squares()
          {
               if(ySquare == 72 && xSquare < 104)
                    xSquare += 4;
               
               if(xSquare == 104 && ySquare < 104)
                    ySquare += 4;
               
               if(ySquare == 104 && xSquare > 72)
                    xSquare -= 4;
               
               if(xSquare == 72 && ySquare > 72)
                    ySquare -= 4;
          }
          
          public void lShape()
          {
               if(yLTop == 232 && xLTop < 360 && !crossice)
                    xLTop += 4;
               
               
               if(xLTop == 360 && yLTop > 136 && !putitdownonme)
               { 
                    yLTop -= 4;
                    if(yLTop == 136)
                         putitdownonme = true;
               }
               
               if(putitdownonme)
               { 
                    if(yLTop < 232)
                         yLTop += 4;
                    else if(yLTop == 232)
                    {
                         crossice = true;
                         putitdownonme = false;
                    }
               }
               
               if(xLTop > 328 && crossice)
               {
                    xLTop -= 4;
                    if(xLTop == 328)
                         crossice = false;
               }
          }
          
          public void lSide()
          {
               if(yLSide == 328 && xLSide < 568 && !cross)
                    xLSide += 4;
               
               if(xLSide == 568 && yLSide < 360 && !down)
               { 
                    yLSide += 4;
                    if(yLSide == 360)
                         down = true;
               }
               
               if(down)
               { 
                    if(yLSide > 328)
                         yLSide -= 4;
                    else if(yLSide == 328)
                    {
                         cross = true;
                         down = false;
                    }
               }
               
               if(xLSide > 488 && cross)
               {
                    xLSide -= 4;
                    if(xLSide == 488)
                         cross = false;
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
          
          
          //Level 2
          /**
           * paint method
           * Draws the maze
           * */
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
                    case 1:
                         for(int i=0; i < balls.length; i++)
                    {
                         int xTop = balls_top[i] * 32 + 8;
                         int xBottom = balls_bottom[i] * 32 + 8;
                         g.drawImage(balls[i], xTop, yballUp, null);
                         g.drawImage(balls[i], xBottom, yballDown, null);
                         
                         if(collision(xTop+8, yballUp+8) && alpha == 1)
                              fadeAway = true;
                         
                         else if(collision(xBottom+8, yballDown+8) && alpha == 1)
                              fadeAway = true;
                         
                    }
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
                         g2d.drawImage(c.getCoin(), c.getCoinX() * 32 + 24, c.getCoinY() * 32 + 8, null);
                         break;
                         
                    case 2:
                         for(int i=0; i < balls.length; i++)
                    {
                         xCircleUp = (int)(Math.cos(angleUp) * (280 - (160 + 24 * i)) + 288);
                         yCircleUp = (int)(Math.sin(angleUp) * (280 - (160 + 24 * i)) + 288);
                         
                         xCircleDown = (int)(Math.cos(angleDown) * ((304 + 24 * i) - 280) + 288);
                         yCircleDown = (int)(Math.sin(angleDown) * ((304 + 24 * i) - 280) + 288);
                         
                         xCircleLeft = (int)(Math.cos(angleLeft) * (280 - (160 + 24 * i)) + 288);
                         yCircleLeft = (int)(Math.sin(angleLeft) * (280 - (160 + 24 * i)) + 288);
                         
                         xCircleRight = (int)(Math.cos(angleRight) * ((304 + 24 * i) - 280) + 288);
                         yCircleRight = (int)(Math.sin(angleRight) * ((304 + 24 * i) - 280) + 288);
                         
                         if(collision(xCircleUp, yCircleUp))
                              fadeAway = true;
                         
                         else if(collision(xCircleDown, yCircleDown))
                              fadeAway = true;
                         
                         else if(collision(xCircleRight, yCircleRight))
                              fadeAway = true;
                         
                         else if(collision(xCircleLeft, yCircleLeft))
                              fadeAway = true;
                         
                         g.drawImage(balls[i], xCircleUp - 8, yCircleUp - 8, null);
                         g.drawImage(balls[i], xCircleDown - 8, yCircleDown - 8, null);
                         g.drawImage(balls[i], xCircleLeft - 8, yCircleLeft - 8, null);
                         g.drawImage(balls[i], xCircleRight - 8, yCircleRight - 8, null);
                    }
                         g.drawImage(balls[0], 280, 280, null);
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade1));
                         g2d.drawImage(c.getCoin(), coins2[0] * 32 + 24, coins2[1] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade2));
                         g2d.drawImage(c.getCoin(), coins2[2] * 32 + 8, coins2[3] * 32 + 24, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade3));
                         g2d.drawImage(c.getCoin(), coins2[4] * 32 + 24, coins2[5] * 32 + 8, null);
                         break;
                    case 3:
                         for(int i=0; i < balls.length; i++)
                    {
                         g.drawImage(balls[i], xBallLeft, yBall, null);
                         g.drawImage(balls[i], xBallRight, yBall, null);
                         g.drawImage(balls[i], xBallLeft, yBall + 96, null);
                         g.drawImage(balls[i], xBallLeft, yBall + 192, null);
                         g.drawImage(balls[i], xBallRight, yBall + 96, null);
                         g.drawImage(balls[i], xBallRight, yBall + 192, null);
                         
                         if(collision(xBallLeft+8, yBall+8))
                              fadeAway = true;
                         
                         else if(collision(xBallLeft+8, yBall + 104))
                              fadeAway = true;
                         
                         else if(collision(xBallLeft+8, yBall + 200))
                              fadeAway = true;
                         
                         else if(collision(xBallRight+8, yBall+8))
                              fadeAway = true;
                         
                         else if(collision(xBallRight+8, yBall + 104))
                              fadeAway = true;
                         
                         else if(collision(xBallRight+8, yBall + 200))
                              fadeAway = true;
                         
                         else if(collision(xLong+8, yLong+8))
                              fadeAway = true;
                         
                    }
                         
                         g.drawImage(balls[0], xLong, yLong, null);
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade1));
                         g2d.drawImage(c.getCoin(), coins4[0] * 32 + 8, coins4[1] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade2));
                         g2d.drawImage(c.getCoin(), coins4[2] * 32 + 8, coins4[3] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade3));
                         g2d.drawImage(c.getCoin(), coins4[4] * 32 + 8, coins4[5] * 32 + 8, null);
                         break;
                    case 4:
                         for(int i=0; i < balls.length; i++)
                    {
                         g.drawImage(balls[i], xSquare, ySquare + 64, null);
                         g.drawImage(balls[i], xSquare + 128, ySquare + 64, null);
                         g.drawImage(balls[i], xSquare, ySquare + 256, null);
                         g.drawImage(balls[i], xSquare + 128, ySquare + 256, null);
                         g.drawImage(balls[i], xSquare + 256, ySquare, null);
                         g.drawImage(balls[i], xSquare + 384, ySquare, null);
                         g.drawImage(balls[i], xSquare + 512, ySquare, null);
                         g.drawImage(balls[i], xSquare + 384, ySquare + 192, null);
                         
                         if(collision(xSquare+8, ySquare + 72))
                              fadeAway = true;
                         
                         else if(collision(xSquare + 136, ySquare + 72))
                              fadeAway = true;
                         
                         else if(collision(xSquare+8, ySquare + 264))
                              fadeAway = true;
                         
                         else if(collision(xSquare + 136, ySquare + 264))
                              fadeAway = true;
                         
                         else if(collision(xSquare + 264, ySquare+8))
                              fadeAway = true; 
                         
                         else if(collision(xSquare + 392, ySquare+8))
                              fadeAway = true;
                         
                         else if(collision(xSquare + 520, ySquare+8))
                              fadeAway = true;
                         
                         else if(collision(xSquare + 392, ySquare + 200))
                              fadeAway = true;
                         
                         else if(collision(xLTop+8, yLTop+8))
                              fadeAway = true;
                         
                         else if(collision(xLSide+8, yLSide+8))
                              fadeAway = true;
                         
                    }
                         g.drawImage(balls[0], xLTop, yLTop, null);
                         g.drawImage(balls[0], xLSide, yLSide, null);
                         g.drawImage(balls[0], 104, 216, null);
                         g.drawImage(balls[0], 72, 280, null);
                         g.drawImage(balls[0], 152, 328, null);
                         g.drawImage(balls[0], 200, 280, null);
                         g.drawImage(balls[0], 248, 264, null);
                         g.drawImage(balls[0], 104, 216, null);
                         g.drawImage(balls[0], 296, 296, null);
                         g.drawImage(balls[0], 328, 152, null);
                         g.drawImage(balls[0], 104, 216, null);
                         g.drawImage(balls[0], 152, 136, null);
                         g.drawImage(balls[0], 200, 88, null);
                         g.drawImage(balls[0], 280, 104, null);
                         g.drawImage(balls[0], 536, 104, null);
                         g.drawImage(balls[0], 456, 184, null);
                         g.drawImage(balls[0], 456, 344, null);
                         
                         if(collision(464, 352))
                              fadeAway = true;
                         
                         else if(collision(464, 192))
                              fadeAway = true;
                         
                         else if(collision(112, 224))
                              fadeAway = true;
                         
                         else if(collision(80, 288))
                              fadeAway = true;
                         
                         else if(collision(160, 336))
                              fadeAway = true;
                         
                         else if(collision(208, 288))
                              fadeAway = true;
                         
                         else if(collision(256, 272))
                              fadeAway = true;
                         
                         else if(collision(112, 224))
                              fadeAway = true;
                         
                         else if(collision(304, 304))
                              fadeAway = true;
                         
                         else if(collision(336, 160))
                              fadeAway = true;
                         
                         else if(collision(112, 224))
                              fadeAway = true;
                         
                         else if(collision(160, 144))
                              fadeAway = true;
                         
                         else if(collision(208, 96))
                              fadeAway = true;
                         
                         else if(collision(288, 112))
                              fadeAway = true;
                         
                         else if(collision(544, 112))
                              fadeAway = true;
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
                         g2d.drawImage(c.getCoin(), c.getCoinX() * 32 + 8, c.getCoinY() * 32 + 8, null);
                         break;
                    case 5:
                         for(int i=0; i < balls.length; i++)
                    { 
                         xUp1 = (int)(Math.cos(angleUp) * 48 + 192);
                         yUp1 = (int)(Math.sin(angleUp) * 48 + 128);
                         yUpA = (int)(Math.sin(angleUp) * 48 + 320);
                         
                         xDown1 = (int)(Math.cos(angleDown) * 48 + 192);
                         yDown1 = (int)(Math.sin(angleDown) * 48 + 128);
                         yDownA = (int)(Math.sin(angleDown) * 48 + 320);
                         
                         xRight1 = (int)(Math.cos(angleRight) * 48 + 192);
                         yRight1 = (int)(Math.sin(angleRight) * 48 + 128);
                         yRightA = (int)(Math.sin(angleRight) * 48 + 320);
                         
                         xLeft1 = (int)(Math.cos(angleLeft) * 48 + 192);
                         yLeft1 = (int)(Math.sin(angleLeft) * 48 + 128);
                         yLeftA = (int)(Math.sin(angleLeft) * 48 + 320);
                         
                         xUp2 = (int)(Math.cos(angleUp) * 48 + 320);
                         yUp2 = (int)(Math.sin(angleUp) * 48 + 128);
                         yUpB = (int)(Math.sin(angleUp) * 48 + 320);
                         
                         xDown2 = (int)(Math.cos(angleDown) * 48 + 320);
                         yDown2 = (int)(Math.sin(angleDown) * 48 + 128);
                         yDownB = (int)(Math.sin(angleDown) * 48 + 320);
                         
                         xRight2 = (int)(Math.cos(angleRight) * 48 + 320);
                         yRight2 = (int)(Math.sin(angleRight) * 48 + 128);
                         yRightB = (int)(Math.sin(angleRight) * 48 + 320);
                         
                         xLeft2 = (int)(Math.cos(angleLeft) * 48 + 320);
                         yLeft2 = (int)(Math.sin(angleLeft) * 48 + 128);
                         yLeftB = (int)(Math.sin(angleLeft) * 48 + 320);
                         
                         xUp3 = (int)(Math.cos(angleUp) * 48 + 448);
                         yUp3 = (int)(Math.sin(angleUp) * 48 + 128);
                         yUpC = (int)(Math.sin(angleUp) * 48 + 320);
                         
                         xDown3 = (int)(Math.cos(angleDown) * 48 + 448);
                         yDown3 = (int)(Math.sin(angleDown) * 48 + 128);
                         yDownC = (int)(Math.sin(angleDown) * 48 + 320);
                         
                         xRight3 = (int)(Math.cos(angleRight) * 48 + 448);
                         yRight3 = (int)(Math.sin(angleRight) * 48 + 128);
                         yRightC = (int)(Math.sin(angleRight) * 48 + 320);
                         
                         xLeft3 = (int)(Math.cos(angleLeft) * 48 + 448);
                         yLeft3 = (int)(Math.sin(angleLeft) * 48 + 128);
                         yLeftC = (int)(Math.sin(angleLeft) * 48 + 320);
                         
                         xUp4 = (int)(Math.cos(angleUp) * 48 + 576);
                         yUp4 = (int)(Math.sin(angleUp) * 48 + 128);
                         yUpD = (int)(Math.sin(angleUp) * 48 + 320);
                         
                         xDown4 = (int)(Math.cos(angleDown) * 48 + 576);
                         yDown4 = (int)(Math.sin(angleDown) * 48 + 128);
                         yDownD = (int)(Math.sin(angleDown) * 48 + 320);
                         
                         xRight4 = (int)(Math.cos(angleRight) * 48 + 576);
                         yRight4 = (int)(Math.sin(angleRight) * 48 + 128);
                         yRightD = (int)(Math.sin(angleRight) * 48 + 320);
                         
                         xLeft4 = (int)(Math.cos(angleLeft) * 48 + 576);
                         yLeft4 = (int)(Math.sin(angleLeft) * 48 + 128);
                         yLeftD = (int)(Math.sin(angleLeft) * 48 + 320);
                         
                         if(collision(xUp1, yUp1))
                              fadeAway = true;
                         
                         else if(collision(xDown1, yDown1))
                              fadeAway = true;
                         
                         else if(collision(xRight1, yRight1))
                              fadeAway = true;
                         
                         else if(collision(xLeft1, yLeft1))
                              fadeAway = true;
                         
                         else if(collision(xUp2, yUp2))
                              fadeAway = true;
                         
                         else if(collision(xDown2, yDown2))
                              fadeAway = true;
                         
                         else if(collision(xRight2, yRight2))
                              fadeAway = true;
                         
                         else if(collision(xLeft2, yLeft2))
                              fadeAway = true;
                         
                         else if(collision(xUp3, yUp3))
                              fadeAway = true;
                         
                         else if(collision(xDown3, yDown3))
                              fadeAway = true;
                         
                         else if(collision(xRight3, yRight3))
                              fadeAway = true;
                         
                         else if(collision(xLeft3, yLeft3))
                              fadeAway = true;
                         
                         else if(collision(xUp4, yUp4))
                              fadeAway = true;
                         
                         else if(collision(xDown4, yDown4))
                              fadeAway = true;
                         
                         else if(collision(xRight4, yRight4))
                              fadeAway = true;
                         
                         else if(collision(xLeft4, yLeft4))
                              fadeAway = true;
                         
                         else if(collision(xUp1, yUpA))
                              fadeAway = true; 
                         
                         else if(collision(xDown1, yDownA))
                              fadeAway = true;
                         
                         else if(collision(xLeft1, yLeftA))
                              fadeAway = true;
                         
                         else if(collision(xRight1, yRightA))
                              fadeAway = true;
                         
                         else if(collision(xUp2, yUpB))
                              fadeAway = true;
                         
                         else if(collision(xDown2, yDownB))
                              fadeAway = true;
                         
                         else if(collision(xLeft2, yLeftB))
                              fadeAway = true;
                         
                         else if(collision(xRight2, yRightB))
                              fadeAway = true;
                         
                         else if(collision(xUp3, yUpC))
                              fadeAway = true;
                         
                         else if(collision(xDown3, yDownC))
                              fadeAway = true;
                         
                         else if(collision(xLeft3, yLeftC))
                              fadeAway = true;
                         
                         else if(collision(xRight3, yRightC))
                              fadeAway = true;
                         
                         else if(collision(xUp4, yUpD))
                              fadeAway = true;
                         
                         else if(collision(xDown4, yDownD))
                              fadeAway = true;
                         
                         else if(collision(xRight4, yRightD))
                              fadeAway = true;
                         
                         else if(collision(xLeft4, yLeftD))
                              fadeAway = true;
                         
                         g.drawImage(balls[i], xUp1 - 8, yUp1 - 8, null);
                         g.drawImage(balls[i], xDown1 - 8, yDown1 - 8, null);
                         g.drawImage(balls[i], xRight1 - 8, yRight1 - 8, null);
                         g.drawImage(balls[i], xLeft1 - 8, yLeft1 - 8, null);
                         
                         g.drawImage(balls[i], xUp2 - 8, yUp2 - 8, null);
                         g.drawImage(balls[i], xDown2 - 8, yDown2 - 8, null);
                         g.drawImage(balls[i], xRight2 - 8, yRight2 - 8, null);
                         g.drawImage(balls[i], xLeft2 - 8, yLeft2 - 8, null);
                         
                         g.drawImage(balls[i], xUp3 - 8, yUp3 - 8, null);
                         g.drawImage(balls[i], xDown3 - 8, yDown3 - 8, null);
                         g.drawImage(balls[i], xRight3 - 8, yRight3 - 8, null);
                         g.drawImage(balls[i], xLeft3 - 8, yLeft3 - 8, null);
                         
                         g.drawImage(balls[i], xUp4 - 8, yUp4 - 8, null);
                         g.drawImage(balls[i], xDown4 - 8, yDown4 - 8, null);
                         g.drawImage(balls[i], xRight4 - 8, yRight4 - 8, null);
                         g.drawImage(balls[i], xLeft4 - 8, yLeft4 - 8, null);
                         
                         g.drawImage(balls[i], xUp1 - 8, yUpA - 8, null);
                         g.drawImage(balls[i], xDown1 - 8, yDownA - 8, null);
                         g.drawImage(balls[i], xRight1 - 8, yRightA - 8, null);
                         g.drawImage(balls[i], xLeft1 - 8, yLeftA - 8, null);
                         
                         g.drawImage(balls[i], xUp2 - 8, yUpB - 8, null);
                         g.drawImage(balls[i], xDown2 - 8, yDownB - 8, null);
                         g.drawImage(balls[i], xRight2 - 8, yRightB - 8, null);
                         g.drawImage(balls[i], xLeft2 - 8, yLeftB - 8, null);
                         
                         g.drawImage(balls[i], xUp3 - 8, yUpC - 8, null);
                         g.drawImage(balls[i], xDown3 - 8, yDownC - 8, null);
                         g.drawImage(balls[i], xRight3 - 8, yRightC - 8, null);
                         g.drawImage(balls[i], xLeft3 - 8, yLeftC - 8, null);
                         
                         g.drawImage(balls[i], xUp4 - 8, yUpD - 8, null);
                         g.drawImage(balls[i], xDown4 - 8, yDownD - 8, null);
                         g.drawImage(balls[i], xRight4 - 8, yRightD - 8, null);
                         g.drawImage(balls[i], xLeft4 - 8, yLeftD - 8, null);
                    }
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade1));
                         g2d.drawImage(c.getCoin(), coins3[0] * 32 + 8, coins3[1] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade2));
                         g2d.drawImage(c.getCoin(), coins3[2] * 32 + 8, coins3[3] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade3));
                         g2d.drawImage(c.getCoin(), coins3[4] * 32 + 8, coins3[5] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade4));
                         g2d.drawImage(c.getCoin(), coins3[6] * 32 + 8, coins3[7] * 32 + 8, null);
                         break;
               }
               g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
               g2d.drawImage(p.getPlayer(), p.getTileX() * 32 + 6, p.getTileY() * 32 + 6, null);
               
          }//end paint
          
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
                    
                    
                    switch(level)
                    {
                         case 1: 
                              if((coins == 0) && ((p.getTileX() == 17) && (p.getTileY() >= 4 && p.getTileY() <= 6)))
                              levelComplete();
                              break;
                         case 2: 
                              if((coins == 0) && ((p.getTileX() == 4) && (p.getTileY() == 8 || p.getTileY() == 9)))
                              levelComplete();
                              break;
                         case 3: 
                              if((coins == 0) && (p.getTileX() == 12 && (p.getTileY() == 6 || p.getTileY() == 7)))
                              levelComplete();
                              break;
                         case 4:
                              if((p.getTileX() == 10 || p.getTileX() == 11) && p.getTileY() == 8)
                              spawn = true;
                              if((coins == 0) && ((p.getTileX() == 18 || p.getTileX() == 19) && p.getTileY() == 6))
                                   levelComplete();
                              break;
                         case 5:
                              if((p.getTileX() >= 16 && p.getTileX() <= 19) && p.getTileY() == 6)
                              spawn = true;
                              if((coins == 0) && ((p.getTileX() == 2 || p.getTileX() == 3) && (p.getTileY() == 10 || p.getTileY() == 11)))
                                   levelComplete();
                              break;
                    }
                    
               }
               
               public void keyReleased(KeyEvent e)
               {
                    
               }
               
               public void keyTyped(KeyEvent e)
               {
                    
               }
          }
     }//end DrawMaze
}