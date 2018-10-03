import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;
import sun.audio.*;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Hard
{
     static String username = Menu.username;
     private JLabel lblDeath;
     private JFrame f;
     int death;
     static Level levi = new Level();
     static boolean isLoad = Menu.isLoad;
     static int level;
     Scanner scanFile;
     PrintWriter outFile;
     
     /**
      * Default constructor:
      * Constructs frame and calls DrawMaze to do just that
      * */
     public Hard()
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
          f.setTitle(username + " 's Maze Game");
          
          JPanel canvas = new JPanel();
          canvas.setLayout(new BoxLayout(canvas, BoxLayout.Y_AXIS));
          
          JPanel pnl = new JPanel();
          pnl.setLayout(new BoxLayout(pnl, BoxLayout.X_AXIS));
          pnl.setBackground(Color.BLACK);
          
          JLabel lbl = new JLabel();
          lbl.setForeground(Color.WHITE);
          lbl.setText("MODE: HARD");
          lbl.setFont(new Font("Calibri", Font.BOLD, 25));
          
          JLabel lblLevel = new JLabel();
          lblLevel.setForeground(Color.WHITE);
          
          lblLevel.setText((level - 10) + " / 5");
          lblLevel.setFont(new Font("Calibri", Font.BOLD, 25));
          
          lblDeath = new JLabel();
          lblDeath.setForeground(Color.WHITE);
          
          lblDeath.setText("DEATHS: " + death); 
          lblDeath.setFont(new Font("Calibri", Font.BOLD, 25));
          
          switch(level)
          {
               case 11: 
                    pnl.setPreferredSize(new Dimension(723, 30));
                    lbl.setPreferredSize(new Dimension(200, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(200, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(100, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(100, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(15, 0)));
                    f.setSize(723, 520);
                    break;
               case 12: 
                    pnl.setPreferredSize(new Dimension(525, 30));
                    lbl.setPreferredSize(new Dimension(200, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(200, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(100, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(100, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(15, 0)));
                    f.setSize(525, 520);
                    break;
               case 13: 
                    pnl.setPreferredSize(new Dimension(540, 30));
                    lbl.setPreferredSize(new Dimension(200, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(200, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(100, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(100, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(15, 0)));
                    f.setSize(540, 450);
                    break;
               case 14: 
                    pnl.setPreferredSize(new Dimension(895, 30));
                    lbl.setPreferredSize(new Dimension(200, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(200, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(100, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(100, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(15, 0)));
                    f.setSize(850, 585);
                    break;
               case 15: 
                    pnl.setPreferredSize(new Dimension(895, 30));
                    lbl.setPreferredSize(new Dimension(200, 30));
                    lblLevel.setPreferredSize(new Dimension(35, 30));
                    lblDeath.setPreferredSize(new Dimension(200, 30));
                    pnl.add(lbl);
                    pnl.add(Box.createRigidArea(new Dimension(100, 0)));
                    pnl.add(lblLevel);
                    pnl.add(Box.createRigidArea(new Dimension(100, 0)));
                    pnl.add(lblDeath);
                    pnl.add(Box.createRigidArea(new Dimension(15, 0)));
                    f.setSize(850, 400);
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
          private boolean key = false;
          private boolean key2 = false;
          private boolean key3 = false;
          private MazeLayout maze;
          private Player p;
          private Obstacle blueBalls; 
          private Key k;
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
          private float fade7 = 1f;
          private float fade8 = 1f;
          private float fade9 = 1f;
          private float fade10 = 1f;
          private float fade11 = 1f;
          private float fade12 = 1f;
          private float fade13 = 1f;
          private float fade14 = 1f;
          private float fade15 = 1f;
          
          private float keyFade = 1f;
          private float keyFade1 = 1f;
          private float keyFade2 = 1f;
          
          private int moveVertical = -4;
          private int moveHorizontal = -4;
          private int move = -2;
          private int moveSide = -2;
          private boolean spawn1 = false;
          private boolean spawn2 = false;
          
          private int[] coinsH1;
          private int[] coinsH2;
          private int[] coinsH3; 
          private int[] coinsH4; 
          private int[] coinsH5; 
          private int[] key1;
          private int yDoor1 = 96;
          private int yDoor2 = 128;
          private int yDoor3 = 96;
          private int yDoor4 = 64;
          private int yDoor5 = 64;
          private int yDoor = 32;
          
          private int xBounceRight = 200;
          private int xBounceLeft = 488;
          private int yBounceDown = 328;
          private int yBounceUp = 392;
          
          //Level 2
          private int xSquareRight = 40;
          private int ySquareRight = 296;
          private int xSquareLeft = 136;
          private int ySquareLeft = 392;
          
          //Level 3
          private int xSquareDown = 40;
          private int ySquareDown = 136;
          private int xSquareUp = 136;
          private int ySquareUp = 264;
          private int xSquare = 168;
          private int ySquare = 296;
          
          //Level 4
          private int yDown = 40;
          private int xSide = 104;
          
          //Level 5
          private double angle = Math.PI / -2;
          private double angleDown = Math.PI / 2;
          private int x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, x7, y7, x8, y8;
          private double increment = 0.1;
          
          public DrawMaze()
          {
               maze = new MazeLayout();
               p = new Player();
               blueBalls = new Obstacle();
               k = new Key();
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
                    level = levi.getLevel();
               
               
               
               switch(level)
               {
                    case 11:
                         rows = 22;
                         coins = 6;
                         coinsH1 = c.getCoinsH1();
                         key1 = k.getKeys1();
                         if(!Menu.cheat)
                              timer = new Timer(25, this);
                         else
                              timer = new Timer(50, this);
                         timer.start();
                         break;
                    case 12:
                         rows = 16;
                         coins = 4;
                         coinsH2 = c.getCoinsH2();
                         if(!Menu.cheat)
                              timer = new Timer(25, this);
                         else
                              timer = new Timer(50, this);
                         timer.start();
                         break;
                    case 13:
                         rows = 16;
                         coins = 4;
                         coinsH3 = c.getCoinsH3();
                         if(!Menu.cheat)
                              timer = new Timer(25, this);
                         else
                              timer = new Timer(50, this);
                         timer.start();
                         break;
                    case 14:
                         rows = 26;
                         coins = 12;
                         coinsH4 = c.getCoinsH4();
                         if(!Menu.cheat)
                              timer = new Timer(25, this);
                         else
                              timer = new Timer(50, this);
                         timer.start();
                         break;
                    case 15:
                         rows = 26;
                         coins = 16;
                         coinsH5 = c.getCoinsH5();
                         if(!Menu.cheat)
                              timer = new Timer(50, this);
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
          
          public void playKey()
          {
               try{
                    
                    InputStream in = new FileInputStream("unlock.wav");
                    AudioStream as = new AudioStream(in);         
                    AudioPlayer.player.start(as);
               }catch(IOException e){}
          }
          
          public void actionPerformed(ActionEvent e)
          {
               switch(level)
               {
                    case 11:
                         move1();
                         if(collectCoin(coinsH1[0] * 32 + 8, coinsH1[1] * 32 + 8, fade))
                         {
                              playCash();
                              fade = 0;
                         }
                         else if(collectCoin(coinsH1[2] * 32 + 8, coinsH1[3] * 32 + 8, fade1))
                         { 
                              playCash();
                              fade1 = 0;
                         }
                         else if(collectCoin(coinsH1[4] * 32 + 8, coinsH1[5] * 32 + 8, fade2))
                         {
                              playCash();
                              fade2 = 0;
                         }
                         else if(collectCoin(coinsH1[6] * 32 + 8, coinsH1[7] * 32 + 8, fade3))
                         { 
                              playCash();
                              fade3 = 0;
                         }
                         else if(collectCoin(coinsH1[8] * 32 + 8, coinsH1[9] * 32 + 8, fade4))
                         { 
                              playCash();
                              fade4 = 0;
                         }
                         else if(collectCoin(coinsH1[10] * 32 + 8, coinsH1[11] * 32 + 8, fade5))
                         { 
                              playCash();
                              fade5 = 0;
                         }
                         
                         if(key)
                         { 
                              yDoor3 -= 4;
                              if(yDoor3 <= 0)
                                   yDoor3 = 0;
                              
                         }
                         if(key2)
                         { 
                              yDoor1 -= 4;
                              if(yDoor1 <= 0)
                                   yDoor1 = 0;
                         }
                         if(key3)
                         { 
                              yDoor2 -= 4;
                              if(yDoor2 <= 0)
                                   yDoor2 = 0;
                         }
                         
                         if(fadeAway)
                              regenerate();
                         repaint();
                         break;
                    case 12:
                         squaresRight();
                         squaresLeft();
                         
                         if(collectCoin(coinsH2[0] * 32 + 8, coinsH2[1] * 32 + 8, fade))
                         { 
                              playCash();
                              fade = 0;
                         }
                         else if(collectCoin(coinsH2[2] * 32 + 8, coinsH2[3] * 32 + 8, fade1))
                         { 
                              playCash(); 
                              fade1 = 0;
                         }
                         else if(collectCoin(coinsH2[4] * 32 + 8, coinsH2[5] * 32 + 8, fade2))
                         { 
                              playCash();
                              fade2 = 0;
                         }
                         else if(collectCoin(coinsH2[6] * 32 + 8, coinsH2[7] * 32 + 8, fade3))
                         { 
                              playCash();
                              fade3 = 0;
                         }
                         
                         if(key)
                         {
                              yDoor2 -= 4;
                              if(yDoor2 <= 0)
                                   yDoor2 = 0;
                         }
                         
                         if(fadeAway)
                              regenerate();
                         repaint();
                         break;
                    case 13:
                         squares();
                         squareDown();
                         
                         if(collectCoin(coinsH3[0] * 32 + 8, coinsH3[1] * 32 + 8, fade))
                         { 
                              playCash();
                              fade = 0;
                         }
                         else if(collectCoin(coinsH3[2] * 32 + 8, coinsH3[3] * 32 + 8, fade1))
                         { 
                              playCash(); 
                              fade1 = 0;
                         }
                         else if(collectCoin(coinsH3[4] * 32 + 8, coinsH3[5] * 32 + 8, fade2))
                         { 
                              playCash();
                              fade2 = 0;
                         }
                         else if(collectCoin(coinsH3[6] * 32 + 8, coinsH3[7] * 32 + 8, fade3))
                         { 
                              playCash();
                              fade3 = 0;
                         }
                         
                         if(key)
                         {
                              yDoor -=4;
                              if(yDoor <= 0)
                                   yDoor = 0;
                         }
                         if(fadeAway)
                              regenerate();
                         repaint();
                         break;
                    case 14:
                         move5();
                         if(collectCoin(coinsH4[0] * 32 + 8, coinsH4[1] * 32 + 8, fade))
                         { 
                              playCash();
                              fade = 0;
                         }
                         else if(collectCoin(coinsH4[2] * 32 + 8, coinsH4[3] * 32 + 8, fade1))
                         { 
                              playCash();
                              fade1 = 0;
                         }
                         else if(collectCoin(coinsH4[4] * 32 + 8, coinsH4[5] * 32 + 8, fade2))
                         { 
                              playCash();
                              fade2 = 0;
                         }
                         else if(collectCoin(coinsH4[6] * 32 + 8, coinsH4[7] * 32 + 8, fade3))
                         { 
                              playCash();
                              fade3 = 0;
                         }
                         else if(collectCoin(coinsH4[8] * 32 + 8, coinsH4[9] * 32 + 8, fade4))
                         { 
                              playCash();
                              fade4 = 0;
                         }
                         else if(collectCoin(coinsH4[10] * 32 + 8, coinsH4[11] * 32 + 8, fade5))
                         { 
                              playCash();
                              fade5 = 0;
                         }
                         else if(collectCoin(coinsH4[12] * 32 + 8, coinsH4[13] * 32 + 8, fade6))
                         { 
                              playCash();
                              fade6 = 0;
                         }
                         else if(collectCoin(coinsH4[14] * 32 + 8, coinsH4[15] * 32 + 8, fade7))
                         { 
                              playCash(); 
                              fade7 = 0;
                         }
                         else if(collectCoin(coinsH4[16] * 32 + 8, coinsH4[17] * 32 + 8, fade8))
                         { 
                              playCash();
                              fade8 = 0;
                         }
                         else if(collectCoin(coinsH4[18] * 32 + 8, coinsH4[19] * 32 + 8, fade9))
                         { 
                              playCash();
                              fade9 = 0;
                         }
                         else if(collectCoin(coinsH4[20] * 32 + 8, coinsH4[21] * 32 + 8, fade10))
                         { 
                              playCash();
                              fade10 = 0;
                         }
                         else if(collectCoin(coinsH4[22] * 32 + 8, coinsH4[23] * 32 + 8, fade11))
                         {
                              playCash();
                              fade11 = 0;
                         }
                         if(fadeAway)
                              regenerate();
                         repaint();
                         break;
                    case 15:
                         angle += 0.1;
                         angleDown += 0.1;
                         
                         if(collectCoin(coinsH5[0] * 32 + 8, coinsH5[1] * 32 + 8, fade))
                         { 
                              playCash();
                              fade = 0;
                         }
                         else if(collectCoin(coinsH5[2] * 32 + 8, coinsH5[3] * 32 + 8, fade1))
                         {
                              playCash();
                              fade1 = 0;
                         }
                         else if(collectCoin(coinsH5[4] * 32 + 8, coinsH5[5] * 32 + 8, fade2))
                         { 
                              playCash();
                              fade2 = 0;
                         }
                         else if(collectCoin(coinsH5[6] * 32 + 8, coinsH5[7] * 32 + 8, fade3))
                         { 
                              playCash();
                              fade3 = 0;
                         }
                         else if(collectCoin(coinsH5[8] * 32 + 8, coinsH5[9] * 32 + 8, fade4))
                         { 
                              playCash(); 
                              fade4 = 0;
                         }
                         else if(collectCoin(coinsH5[10] * 32 + 8, coinsH5[11] * 32 + 8, fade5))
                         { 
                              playCash();
                              fade5 = 0;
                         }
                         else if(collectCoin(coinsH5[12] * 32 + 8, coinsH5[13] * 32 + 8, fade6))
                         { 
                              playCash();
                              fade6 = 0;
                         }
                         else if(collectCoin(coinsH5[14] * 32 + 8, coinsH5[15] * 32 + 8, fade7))
                         { 
                              playCash();
                              fade7 = 0;
                         }
                         else if(collectCoin(coinsH5[16] * 32 + 8, coinsH5[17] * 32 + 8, fade8))
                         {
                              playCash();
                              fade8 = 0;
                         }
                         else if(collectCoin(coinsH5[18] * 32 + 8, coinsH5[19] * 32 + 8, fade9))
                         { 
                              playCash();
                              fade9 = 0;
                         }
                         else if(collectCoin(coinsH5[20] * 32 + 8, coinsH5[21] * 32 + 8, fade10))
                         { 
                              playCash();
                              fade10 = 0;
                         }
                         else if(collectCoin(coinsH5[22] * 32 + 8, coinsH5[23] * 32 + 8, fade11))
                         {
                              playCash();
                              fade11 = 0;
                         }
                         else if(collectCoin(coinsH5[24] * 32 + 8, coinsH5[25] * 32 + 8, fade12))
                         { 
                              playCash();
                              fade12 = 0;
                         }
                         else if(collectCoin(coinsH5[26] * 32 + 8, coinsH5[27] * 32 + 8, fade13))
                         {
                              playCash();
                              fade13 = 0;
                         }
                         else if(collectCoin(coinsH5[28] * 32 + 8, coinsH5[29] * 32 + 8, fade14))
                         { 
                              playCash();
                              fade14 = 0;
                         }
                         else if(collectCoin(coinsH5[30] * 32 + 8, coinsH5[31] * 32 + 8, fade15))
                         { 
                              playCash();
                              fade15 = 0;
                         }
                         if(key)
                         {
                              yDoor4 -= 4;
                              if(yDoor4 <= 0)
                                   yDoor4 = 0;
                         }
                         if(key2)
                         {
                              yDoor5 -= 4;
                              if(yDoor5 <= 0)
                                   yDoor5 = 0;
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
               lblDeath.setText("DEATHS: " + death);
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
                         case 11:
                              if(spawn1)
                         {
                              p.setTileX(18);
                              p.setTileY(10);
                              coins = 6;
                              fade = 1;
                              fade1 = 1;
                              fade2= 1;
                              fade3 = 1;
                              fade4= 1;
                              fade5 = 1;
                              
                         }
                              else if(spawn2)
                              {
                                   p.setTileX(18);
                                   p.setTileY(2);
                                   coins = 0;
                                   
                              }
                              else
                              {   
                                   p.setTileX(1);
                                   p.setTileY(6);
                                   coins = 6;
                                   fade = 1;
                                   fade1 = 1;
                                   fade2= 1;
                                   fade3 = 1;
                                   fade4= 1;
                                   fade5 = 1;
                                   maze.setMazeTile(16, 10, "b");
                                   maze.setMazeTile(16, 11, "b");
                                   maze.setMazeTile(16, 12, "b");
                                   maze.setMazeTile(17, 10, "b");
                                   maze.setMazeTile(17, 11, "b");
                                   maze.setMazeTile(17, 12, "b");
                                   keyFade = 1;
                                   keyFade1 = 1;
                                   keyFade2 = 1;
                                   key = false;
                                   key2 = false;
                                   key3 = false;
                                   yDoor1 = 96;
                                   yDoor2 = 128;
                                   yDoor3 = 96;
                              }
                              break;
                         case 12:
                              p.setTileX(7);
                              p.setTileY(7);
                              coins = 4;
                              fade = 1;
                              fade1 = 1;
                              fade2= 1;
                              fade3 = 1;
                              keyFade = 1;
                              maze.setMazeTile(5, 1, "b");
                              maze.setMazeTile(5, 2, "b");
                              maze.setMazeTile(5, 3, "b");
                              maze.setMazeTile(5, 4, "b");
                              maze.setMazeTile(6, 1, "b");
                              maze.setMazeTile(6, 2, "b");
                              maze.setMazeTile(6, 3, "b");
                              maze.setMazeTile(6, 4, "b");
                              yDoor2 = 128;
                              key = false;
                              break;
                         case 13:
                              p.setTileX(1);
                              p.setTileY(1);
                              coins = 4;
                              fade = 1;
                              fade1 = 1;
                              fade2= 1;
                              fade3 = 1;
                              keyFade = 1;
                              maze.setMazeTile(2, 8, "b");
                              yDoor = 32;
                              key = false;
                              break;
                         case 14:
                              p.setTileX(1);
                              p.setTileY(1);
                              coins = 12;
                              fade = 1;
                              fade1 = 1;
                              fade2= 1;
                              fade3 = 1;
                              fade4 = 1;
                              fade5 = 1;
                              fade6= 1;
                              fade7 = 1;
                              fade8 = 1;
                              fade9 = 1;
                              fade10= 1;
                              fade11 = 1;
                              break;
                         case 15:
                              p.setTileX(1);
                              p.setTileY(4);
                              coins = 16;
                              fade = 1;
                              fade1 = 1;
                              fade2= 1;
                              fade3 = 1;
                              fade4 = 1;
                              fade5 = 1;
                              fade6= 1;
                              fade7 = 1;
                              fade8 = 1;
                              fade9 = 1;
                              fade10= 1;
                              fade11 = 1;
                              fade12 = 1;
                              fade13 = 1;
                              fade14 = 1;
                              keyFade = 1;
                              keyFade1 = 1;
                              yDoor4 = 64;
                              yDoor5 = 64;
                              maze.setMazeTile(12, 4, "b");
                              maze.setMazeTile(12, 5, "b");
                              maze.setMazeTile(13, 4, "b");
                              maze.setMazeTile(13, 5, "b");
                              maze.setMazeTile(22, 4, "b");
                              maze.setMazeTile(22, 5, "b");
                              key = false;
                              key2 = false;
                              fade15= 1;
                              break;
                    }
               }
          }
          
          public void levelComplete()
          {
               switch(level)
               {
                    case 11:
                         f.setVisible(false);
                         f.dispose();
                         playLevel();
                         levelChange();
                         new Hard();
                         break;
                    case 12:
                         f.setVisible(false);
                         f.dispose();
                         playLevel(); 
                         levelChange();
                         new Hard();
                         break;
                    case 13:
                         f.setVisible(false);
                         f.dispose();
                         playLevel();
                         levelChange();
                         new Hard();
                         break;
                    case 14:
                         f.setVisible(false);
                         f.dispose();
                         playLevel();
                         levelChange();
                         new Hard();
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
          
          public void move1()
          {
               if(xBounceRight == 200 && xBounceLeft == 488)
               {
                    moveSide *= -1;
                    move *= -1;
               }
               
               if(xBounceRight == 328 && xBounceLeft == 360)
               {
                    moveSide *= -1;
                    move *= -1;
               }
               
               if(xBounceRight == 264 && xBounceLeft == 424)
                    move *= -1;
               
               xBounceRight += moveSide;
               xBounceLeft -= moveSide;
               yBounceDown += move;
               yBounceUp -= move;
               
               if((xBounceRight == 232 || xBounceRight == 296) && (xBounceLeft == 456 || xBounceLeft == 392))
                    move *= -1;
          }
          
          public void squaresRight()
          {
               if(ySquareRight == 296 && xSquareRight < 136)
                    xSquareRight += 4;
               if(xSquareRight == 136 && ySquareRight < 392)
                    ySquareRight += 4;
               if(ySquareRight == 392 && xSquareRight > 40)
                    xSquareRight -= 4;
               if(xSquareRight == 40 && ySquareRight > 296)
                    ySquareRight -= 4;
          }
          
          public void squaresLeft()
          {
               if(ySquareLeft == 392 && xSquareLeft > 40)
                    xSquareLeft -= 4;
               if(xSquareLeft == 40 && ySquareLeft > 296)
                    ySquareLeft -= 4;
               if(ySquareLeft == 296 && xSquareLeft < 136)
                    xSquareLeft += 4;
               if(xSquareLeft == 136 && ySquareLeft < 392)
                    ySquareLeft += 4;
          }
          
          public void squares()
          {
               if(ySquareDown == 136 && xSquareDown < 72)
                    xSquareDown += 2;
               if(xSquareDown == 72 && ySquareDown < 168)
                    ySquareDown += 2;
               if(ySquareDown == 168 && xSquareDown > 40)
                    xSquareDown -= 2;
               if(xSquareDown == 40 && ySquareDown > 72)
                    ySquareDown -= 2;
          }
          
          public void squareDown()
          {
               if(ySquare == 296 && xSquare > 136)
                    xSquare -= 2;
               if(xSquare == 136 && ySquare > 264)
                    ySquare -= 2;
               if(ySquare == 264 && xSquare < 168)
                    xSquare += 2;
               if(xSquare == 168 && ySquare < 296)
                    ySquare += 2;
          }
          
          int movingX = -8;
          int movingY = -8;
          
          public void move5()
          {
               if(yDown == 40)
                    movingY *= -1;
               
               if(xSide == 104)
                    movingX *= -1;
               
               yDown += movingY;
               xSide += movingX;
               
               if(yDown == 456)
                    movingY *= -1;
               
               if(xSide == 712)
                    movingX *= -1;
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
                         else if(maze.getMazeTile(x,y).equals("b"))
                         {
                              if(level == 11)
                              {
                                   if((x == 16 || x == 17) && (y == 10 || y == 11 || y == 12)) 
                                   {
                                        if(key)
                                        {
                                             maze.setMazeTile(x, y, "f");
                                             g.drawImage(maze.getFloor(), x * 32, y * 32, null);
                                        }
                                        
                                   }
                                   else if((x == 16 || x == 17) && (y == 1 || y == 2 || y == 3))
                                   { 
                                        if(key2)
                                        {
                                             maze.setMazeTile(x, y, "f");
                                             g.drawImage(maze.getFloor(), x * 32, y * 32, null);
                                        }
                                   }
                                   else if((x == 16 || x == 17) && (y == 5 || y == 6 || y == 7 || y == 8))
                                   {
                                        if(key3)
                                        {
                                             maze.setMazeTile(x, y, "f");
                                             g.drawImage(maze.getFloor(), x * 32, y * 32, null);
                                        }
                                        
                                   }
                              }
                              else if(level == 12)
                              {
                                   if((x == 5 || x == 6) && (y == 1 || y == 2 || y == 3 || y == 4)) 
                                   {
                                        if(key)
                                        {
                                             maze.setMazeTile(x, y, "f");
                                             g.drawImage(maze.getFloor(), x * 32, y * 32, null);
                                        }
                                   }
                              }
                              else if(level == 13)
                              {
                                   if(x == 2 && y == 8)
                                   { 
                                        if(key)
                                        {
                                             maze.setMazeTile(x, y, "f");
                                             g.drawImage(maze.getFloor(), x * 32, y * 32, null);
                                        }
                                   }
                              }
                              else if(level == 15)
                              {
                                   if((x == 12 || x == 13) && (y == 4 || y == 5))
                                   { 
                                        if(key)
                                        {
                                             maze.setMazeTile(x, y, "f");
                                             g.drawImage(maze.getFloor(), x * 32, y * 32, null);
                                        }
                                   }
                                   else if(x== 22 && (y == 4 || y == 5))
                                   { 
                                        if(key2)
                                        {
                                             maze.setMazeTile(x, y, "f");
                                             g.drawImage(maze.getFloor(), x * 32, y * 32, null);
                                        }
                                   }
                              }
                         }
                    }//end for
               }//end for
               
               switch(level)
               {
                    case 11:
                         g.drawImage(blueBalls.getBall(), xBounceRight, yBounceDown, null);
                         if(collision(xBounceRight, yBounceDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBounceLeft, yBounceDown, null);
                         if(collision(xBounceLeft, yBounceDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBounceRight, yBounceUp, null);
                         if(collision(xBounceRight, yBounceUp))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBounceLeft, yBounceUp, null);
                         if(collision(xBounceLeft, yBounceUp))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBounceRight, yBounceDown - 288, null);
                         if(collision(xBounceRight, yBounceDown - 288))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBounceLeft, yBounceDown - 288, null);
                         if(collision(xBounceLeft, yBounceDown - 288))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBounceRight, yBounceUp - 288, null);
                         if(collision(xBounceRight, yBounceUp - 288))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xBounceLeft, yBounceUp - 288, null);
                         if(collision(xBounceLeft, yBounceUp - 288))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 200, 168, null);
                         if(collision(200, 168))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 200, 200, null);
                         if(collision(200, 200))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 200, 232, null);
                         if(collision(200, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 264, 200, null);
                         if(collision(264, 200))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 264, 232, null);
                         if(collision(264, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 264, 264, null);
                         if(collision(264, 264))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 328, 168, null);
                         if(collision(328, 168))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 328, 200, null);
                         if(collision(328, 200))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 328, 232, null);
                         if(collision(328, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 392, 200, null);
                         if(collision(392, 200))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 392, 232, null);
                         if(collision(392, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 392, 264, null);
                         if(collision(392, 264))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 456, 168, null);
                         if(collision(456, 168))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 456, 200, null);
                         if(collision(456, 200))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 456, 232, null);
                         if(collision(456, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 520, 200, null);
                         if(collision(520, 200) && key3)
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 520, 232, null);
                         if(collision(520, 232) && key3)
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 520, 264, null);
                         if(collision(520, 264) && key3)
                              fadeAway = true;
                         
                         Color color = new Color(211, 211, 211);
                         g.setColor(color);
                         g.fillRect(512, 320, 64, yDoor3);
                         g.fillRect(512, 160, 64, yDoor2);
                         g.fillRect(512, 32, 64, yDoor1);
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
                         g2d.drawImage(c.getCoin(), coinsH1[0] * 32 + 8, coinsH1[1] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade1));
                         g2d.drawImage(c.getCoin(), coinsH1[2] * 32 + 8, coinsH1[3] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade2));
                         g2d.drawImage(c.getCoin(), coinsH1[4] * 32 + 8, coinsH1[5] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade3));
                         g2d.drawImage(c.getCoin(), coinsH1[6] * 32 + 8, coinsH1[7] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade4));
                         g2d.drawImage(c.getCoin(), coinsH1[8] * 32 + 8, coinsH1[9] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade5));
                         g2d.drawImage(c.getCoin(), coinsH1[10] * 32 + 8, coinsH1[11] * 32 + 8, null);
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, keyFade));
                         g2d.drawImage(k.getKey(), key1[0] * 32 + 2, key1[1] * 32 + 2, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, keyFade1));
                         g2d.drawImage(k.getKey(), key1[2] * 32 + 2, key1[3] * 32 + 2, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, keyFade2));
                         g2d.drawImage(k.getKey(), key1[4] * 32 + 2, key1[5] * 32 + 2, null);
                         
                         break;
                    case 12:
                         g.drawImage(blueBalls.getBall(), xSquareRight, ySquareRight, null);
                         if(collision(xSquareRight, ySquareRight))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareLeft, ySquareLeft, null);
                         if(collision(xSquareLeft, ySquareLeft))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareRight, ySquareRight - 128, null);
                         if(collision(xSquareRight, ySquareRight - 128))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareLeft, ySquareLeft - 128, null);
                         if(collision(xSquareLeft, ySquareLeft - 128))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareLeft, ySquareRight - 256, null);
                         if(collision(xSquareLeft, ySquareRight - 256))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareRight, ySquareLeft - 256, null);
                         if(collision(xSquareRight, ySquareLeft - 256))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareRight + 128, ySquareRight, null);
                         if(collision(xSquareRight + 128, ySquareRight))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareLeft + 128, ySquareLeft, null);
                         if(collision(xSquareLeft + 128, ySquareLeft))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareRight + 256, ySquareRight, null);
                         if(collision(xSquareRight + 256, ySquareRight))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareLeft + 256, ySquareLeft, null);
                         if(collision(xSquareLeft + 256, ySquareLeft))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareRight + 320, ySquareRight - 128, null);
                         if(collision(xSquareRight + 320, ySquareRight - 128))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareLeft + 320, ySquareLeft - 128, null);
                         if(collision(xSquareLeft + 320, ySquareLeft - 128))
                              fadeAway = true;
                         
                         
                         
                         g.drawImage(blueBalls.getBall(), 72, 328, null);
                         if(collision(72, 328))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 72, 360, null);
                         if(collision(72, 360))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 104, 328, null);
                         if(collision(104, 328))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 104, 360, null);
                         if(collision(104, 360))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 200, 328, null);
                         if(collision(200, 328))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 200, 360, null);
                         if(collision(200, 360))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 232, 328, null);
                         if(collision(232, 328))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 232, 360, null);
                         if(collision(232, 360))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 328, 328, null);
                         if(collision(328, 328))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 328, 360, null);
                         if(collision(328, 360))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 360, 328, null);
                         if(collision(360, 328))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 360, 360, null);
                         if(collision(360, 360))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 72, 200, null);
                         if(collision(72, 200))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 72, 232, null);
                         if(collision(72, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 104, 200, null);
                         if(collision(104, 200))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 104, 232, null);
                         if(collision(104, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 392, 200, null);
                         if(collision(392, 200))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 392, 232, null);
                         if(collision(392, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 424, 200, null);
                         if(collision(424, 200))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 424, 232, null);
                         if(collision(424, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 72, 72, null);
                         if(collision(72, 72))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 72, 104, null);
                         if(collision(72, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 104, 72, null);
                         if(collision(104, 72))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 104, 104, null);
                         if(collision(104, 104))
                              fadeAway = true;
                         
                         Color grey = new Color(211, 211, 211);
                         g.setColor(grey);
                         g.fillRect(160, 32, 64, yDoor2);   
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
                         g2d.drawImage(c.getCoin(), coinsH2[0] * 32 + 8, coinsH2[1] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade1));
                         g2d.drawImage(c.getCoin(), coinsH2[2] * 32 + 8, coinsH2[3] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade2));
                         g2d.drawImage(c.getCoin(), coinsH2[4] * 32 + 8, coinsH2[5] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade3));
                         g2d.drawImage(c.getCoin(), coinsH2[6] * 32 + 8, coinsH2[7] * 32 + 8, null);
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, keyFade));
                         g2d.drawImage(k.getKey(), 13 * 32 + 2, 2 * 32 + 2, null);
                         break;
                    case 13:
                         g.drawImage(blueBalls.getBall(), xSquareDown, ySquareDown, null);
                         if(collision(xSquareDown, ySquareDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareDown + 64, ySquareDown, null);
                         if(collision(xSquareDown + 64, ySquareDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareDown + 128, ySquareDown, null);
                         if(collision(xSquareDown + 128, ySquareDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareDown, ySquareDown + 64, null);
                         if(collision(xSquareDown, ySquareDown + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareDown + 64, ySquareDown + 64, null);
                         if(collision(xSquareDown + 64, ySquareDown + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareDown + 128, ySquareDown + 64, null);
                         if(collision(xSquareDown + 128, ySquareDown + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareDown + 256, ySquareDown, null);
                         if(collision(xSquareDown + 256, ySquareDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareDown + 256, ySquareDown + 64, null);
                         if(collision(xSquareDown + 256, ySquareDown + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareDown + 320, ySquareDown, null);
                         if(collision(xSquareDown + 320, ySquareDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareDown + 320, ySquareDown + 64, null);
                         if(collision(xSquareDown + 320, ySquareDown + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareDown + 384, ySquareDown, null);
                         if(collision(xSquareDown + 384, ySquareDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquareDown + 384, ySquareDown + 64, null);
                         if(collision(xSquareDown + 384, ySquareDown + 64))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare, ySquare, null);
                         if(collision(xSquare, ySquare))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 64, ySquare, null);
                         if(collision(xSquare + 64, ySquare))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 128, ySquare, null);
                         if(collision(xSquare + 128, ySquare))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSquare + 192, ySquare, null);
                         if(collision(xSquare + 192, ySquare))
                              fadeAway = true;
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
                         g2d.drawImage(c.getCoin(), coinsH3[0] * 32 + 8, coinsH3[1] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade1));
                         g2d.drawImage(c.getCoin(), coinsH3[2] * 32 + 8, coinsH3[3] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade2));
                         g2d.drawImage(c.getCoin(), coinsH3[4] * 32 + 8, coinsH3[5] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade3));
                         g2d.drawImage(c.getCoin(), coinsH3[6] * 32 + 8, coinsH3[7] * 32 + 8, null);
                         
                         Color gr = new Color(211, 211, 211);
                         g.setColor(gr);
                         g.fillRect(64, 256, 32, yDoor);
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, keyFade));
                         g2d.drawImage(k.getKey(), 450, 34, null);
                         break;
                    case 14:
                         g.drawImage(blueBalls.getBall(), 104, yDown, null);
                         if(collision(104, yDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 264, yDown, null);
                         if(collision(264, yDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 552, yDown, null);
                         if(collision(552, yDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 712, yDown, null);
                         if(collision(712, yDown))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSide, 40, null);
                         if(collision(xSide, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSide, 136, null);
                         if(collision(xSide, 136))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSide, 360, null);
                         if(collision(xSide, 360))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), xSide, 456, null);
                         if(collision(xSide, 456))
                              fadeAway = true;
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
                         g2d.drawImage(c.getCoin(), coinsH4[0] * 32 + 8, coinsH4[1] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade1));
                         g2d.drawImage(c.getCoin(), coinsH4[2] * 32 + 8, coinsH4[3] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade2));
                         g2d.drawImage(c.getCoin(), coinsH4[4] * 32 + 8, coinsH4[5] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade3));
                         g2d.drawImage(c.getCoin(), coinsH4[6] * 32 + 8, coinsH4[7] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade4));
                         g2d.drawImage(c.getCoin(), coinsH4[8] * 32 + 8, coinsH4[9] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade5));
                         g2d.drawImage(c.getCoin(), coinsH4[10] * 32 + 8, coinsH4[11] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade6));
                         g2d.drawImage(c.getCoin(), coinsH4[12] * 32 + 8, coinsH4[13] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade7));
                         g2d.drawImage(c.getCoin(), coinsH4[14] * 32 + 8, coinsH4[15] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade8));
                         g2d.drawImage(c.getCoin(), coinsH4[16] * 32 + 8, coinsH4[17] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade9));
                         g2d.drawImage(c.getCoin(), coinsH4[18] * 32 + 8, coinsH4[19] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade10));
                         g2d.drawImage(c.getCoin(), coinsH4[20] * 32 + 8, coinsH4[21] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade11));
                         g2d.drawImage(c.getCoin(), coinsH4[22] * 32 + 8, coinsH4[23] * 32 + 8, null);
                         break;
                    case 15:
                         x1 = (int)(Math.cos(angle) * 96 + 248);
                         y1 = (int)(Math.sin(angle) * 96 + 160);
                         
                         x2 = (int)(Math.cos(angle) * 64 + 248);
                         y2 = (int)(Math.sin(angle) * 64 + 160);
                         
                         x3 = (int)(Math.cos(angle) * 32 + 248);
                         y3 = (int)(Math.sin(angle) * 32 + 160);
                         
                         x4 = (int)(Math.cos(angleDown) * 32 + 248);
                         y4 = (int)(Math.sin(angleDown) * 32 + 160);
                         
                         x5 = (int)(Math.cos(angleDown) * 64 + 248);
                         y5 = (int)(Math.sin(angleDown) * 64 + 160);
                         
                         x6 = (int)(Math.cos(angleDown) * 96 + 248);
                         y6 = (int)(Math.sin(angleDown) * 96 + 160); 
                         
                         g.drawImage(blueBalls.getBall(), x1, y1 - 8, null);
                         if(collision(x1, y1))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), x2, y2 - 8, null); 
                         if(collision(x2, y2))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), x3, y3 - 8, null); 
                         if(collision(x3, y3))
                              fadeAway = true;    
                         
                         g.drawImage(blueBalls.getBall(), 248, 152, null);
                         if(collision(248, 152))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), x4, y4 - 8, null);  
                         if(collision(x4, y4))
                              fadeAway = true;    
                         
                         g.drawImage(blueBalls.getBall(), x5, y5 - 8, null);
                         if(collision(x5, y5))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), x6, y6 - 8, null); 
                         if(collision(x6, y6))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), x1 + 320, y1 - 8, null);
                         if(collision(x1 + 320, y1))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), x2 + 320, y2 - 8, null); 
                         if(collision(x2 + 320, y2))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), x3 + 320, y3 - 8, null); 
                         if(collision(x3 + 320, y3))
                              fadeAway = true;    
                         
                         g.drawImage(blueBalls.getBall(), 568, 152, null);
                         if(collision(568, 152))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), x4 + 320, y4 - 8, null);  
                         if(collision(x4 + 320, y4))
                              fadeAway = true;    
                         
                         g.drawImage(blueBalls.getBall(), x5 + 320, y5 - 8, null);
                         if(collision(x5 + 320, y5))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), x6 + 320, y6 - 8, null); 
                         if(collision(x6 + 320, y6))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 136, 40, null); 
                         if(collision(136, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 168, 40, null); 
                         if(collision(168, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 200, 40, null); 
                         if(collision(200, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 136, 72, null); 
                         if(collision(136, 72))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 168, 72, null); 
                         if(collision(168, 72))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 136, 104, null); 
                         if(collision(136, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 360, 40, null); 
                         if(collision(360, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 328, 40, null); 
                         if(collision(328, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 296, 40, null); 
                         if(collision(296, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 328, 72, null); 
                         if(collision(328, 72))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 360, 72, null); 
                         if(collision(360, 72))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 360, 104, null); 
                         if(collision(360, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 136, 200, null); 
                         if(collision(136, 200))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 136, 232, null); 
                         if(collision(136, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 168, 232, null); 
                         if(collision(168, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 136, 264, null); 
                         if(collision(136, 264))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 168, 264, null); 
                         if(collision(168, 264))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 200, 264, null); 
                         if(collision(200, 264))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 360, 264, null); 
                         if(collision(360, 264))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 328, 264, null); 
                         if(collision(328, 264))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 296, 264, null); 
                         if(collision(296, 264))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 360, 232, null); 
                         if(collision(360, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 328, 232, null); 
                         if(collision(328, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 360, 200, null); 
                         if(collision(360, 200))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 456, 40, null); 
                         if(collision(456, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 488, 40, null); 
                         if(collision(488, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 520, 40, null); 
                         if(collision(520, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 456, 72, null); 
                         if(collision(456, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 488, 72, null); 
                         if(collision(488, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 456, 104, null); 
                         if(collision(456, 264))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 456, 200, null); 
                         if(collision(456, 200))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 456, 232, null); 
                         if(collision(456, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 488, 232, null); 
                         if(collision(488, 232))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 456, 264, null); 
                         if(collision(456, 264))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 488, 264, null); 
                         if(collision(488, 264))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 520, 264, null); 
                         if(collision(520, 264))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 680, 40, null); 
                         if(collision(680, 40))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 648, 40, null); 
                         if(collision(648, 40))
                              fadeAway = true;
                         g.drawImage(blueBalls.getBall(), 616, 40, null); 
                         if(collision(616, 40))
                              fadeAway = true;
                         g.drawImage(blueBalls.getBall(), 680, 72, null); 
                         if(collision(680, 72))
                              fadeAway = true;
                         g.drawImage(blueBalls.getBall(), 648, 72, null); 
                         if(collision(648, 72))
                              fadeAway = true;
                         g.drawImage(blueBalls.getBall(), 680, 104, null); 
                         if(collision(680, 104))
                              fadeAway = true;
                         
                         g.drawImage(blueBalls.getBall(), 680, 200, null); 
                         if(collision(680, 200))
                              fadeAway = true;
                         g.drawImage(blueBalls.getBall(), 680, 232, null); 
                         if(collision(680, 232))
                              fadeAway = true;
                         g.drawImage(blueBalls.getBall(), 648, 232, null); 
                         if(collision(648, 232))
                              fadeAway = true;
                         g.drawImage(blueBalls.getBall(), 680, 264, null); 
                         if(collision(680, 264))
                              fadeAway = true;
                         g.drawImage(blueBalls.getBall(), 648, 264, null); 
                         if(collision(648, 264))
                              fadeAway = true;
                         g.drawImage(blueBalls.getBall(), 616, 264, null); 
                         if(collision(616, 264))
                              fadeAway = true;
                         
                         Color lightG = new Color(211, 211, 211);
                         g.setColor(lightG);
                         g.fillRect(384, 128, 64, yDoor4);
                         g.fillRect(704, 128, 32, yDoor5);
                         
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade));
                         g2d.drawImage(c.getCoin(), coinsH5[0] * 32 + 8, coinsH5[1] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade1));
                         g2d.drawImage(c.getCoin(), coinsH5[2] * 32 + 8, coinsH5[3] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade2));
                         g2d.drawImage(c.getCoin(), coinsH5[4] * 32 + 8, coinsH5[5] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade3));
                         g2d.drawImage(c.getCoin(), coinsH5[6] * 32 + 8, coinsH5[7] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade4));
                         g2d.drawImage(c.getCoin(), coinsH5[8] * 32 + 8, coinsH5[9] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade5));
                         g2d.drawImage(c.getCoin(), coinsH5[10] * 32 + 8, coinsH5[11] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade6));
                         g2d.drawImage(c.getCoin(), coinsH5[12] * 32 + 8, coinsH5[13] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade7));
                         g2d.drawImage(c.getCoin(), coinsH5[14] * 32 + 8, coinsH5[15] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade8));
                         g2d.drawImage(c.getCoin(), coinsH5[16] * 32 + 8, coinsH5[17] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade9));
                         g2d.drawImage(c.getCoin(), coinsH5[18] * 32 + 8, coinsH5[19] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade10));
                         g2d.drawImage(c.getCoin(), coinsH5[20] * 32 + 8, coinsH5[21] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade11));
                         g2d.drawImage(c.getCoin(), coinsH5[22] * 32 + 8, coinsH5[23] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade12));
                         g2d.drawImage(c.getCoin(), coinsH5[24] * 32 + 8, coinsH5[25] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade13));
                         g2d.drawImage(c.getCoin(), coinsH5[26] * 32 + 8, coinsH5[27] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade14));
                         g2d.drawImage(c.getCoin(), coinsH5[28] * 32 + 8, coinsH5[29] * 32 + 8, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade15));
                         g2d.drawImage(c.getCoin(), coinsH5[30] * 32 + 8, coinsH5[31] * 32 + 8, null);
                         
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, keyFade));
                         g2d.drawImage(k.getKey(), 194, 194, null);
                         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, keyFade1));
                         g2d.drawImage(k.getKey(), 514, 66, null);
                         
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
                              if(!maze.getMazeTile(p.getTileX(), p.getTileY() - 1).equals("w") && !maze.getMazeTile(p.getTileX(), p.getTileY() - 1).equals("b"))
                                   p.move(0, -1);
                              
                         }
                         else if(keycode == KeyEvent.VK_DOWN && alpha == 1f)
                         {
                              if(!maze.getMazeTile(p.getTileX(), p.getTileY() + 1).equals("w") && !maze.getMazeTile(p.getTileX(), p.getTileY() + 1).equals("b"))
                                   p.move(0, 1);
                              
                         }
                         else if(keycode == KeyEvent.VK_LEFT && alpha == 1f)
                         {
                              if(!maze.getMazeTile(p.getTileX() - 1, p.getTileY()).equals("w") && !maze.getMazeTile(p.getTileX() - 1, p.getTileY()).equals("b"))
                                   p.move(-1, 0);
                              
                         }
                         else if(keycode == KeyEvent.VK_RIGHT && alpha == 1f)
                         {
                              if(!maze.getMazeTile(p.getTileX() + 1, p.getTileY()).equals("w") && !maze.getMazeTile(p.getTileX() + 1, p.getTileY()).equals("b"))
                                   p.move(1, 0);
                              
                         }
                    }
                    else
                    {
                         if(keycode == KeyEvent.VK_UP)
                         {
                              if(!maze.getMazeTile(p.getTileX(), p.getTileY() - 1).equals("w") && !maze.getMazeTile(p.getTileX(), p.getTileY() - 1).equals("b"))
                                   p.move(0, -1);
                              
                         }
                         else if(keycode == KeyEvent.VK_DOWN)
                         {
                              if(!maze.getMazeTile(p.getTileX(), p.getTileY() + 1).equals("w") && !maze.getMazeTile(p.getTileX(), p.getTileY() + 1).equals("b"))
                                   p.move(0, 1);
                              
                         }
                         else if(keycode == KeyEvent.VK_LEFT)
                         {
                              if(!maze.getMazeTile(p.getTileX() - 1, p.getTileY()).equals("w") && !maze.getMazeTile(p.getTileX() - 1, p.getTileY()).equals("b"))
                                   p.move(-1, 0);
                              
                         }
                         else if(keycode == KeyEvent.VK_RIGHT)
                         {
                              if(!maze.getMazeTile(p.getTileX() + 1, p.getTileY()).equals("w") && !maze.getMazeTile(p.getTileX() + 1, p.getTileY()).equals("b"))
                                   p.move(1, 0);
                              
                         }
                    }
                    
                    
                    switch(level)
                    {
                         case 11: 
                              if(p.getTileX() == 5 && p.getTileY() == 6 && !key)
                         { 
                              key = true;
                              keyFade = 0;
                              playKey();
                              repaint();
                         } 
                              else if(p.getTileX() == 19 && p.getTileY() == 10 && !key2)
                              { 
                                   key2 = true;
                                   keyFade1 = 0;
                                   playKey();
                                   repaint();
                              }
                              else if(p.getTileX() == 19 && p.getTileY() == 11 && !key3)
                              { 
                                   key3 = true;
                                   keyFade2 = 0;
                                   playKey();
                                   repaint();
                              }
                              if(p.getTileX() == 18 && (p.getTileY() == 10 || p.getTileY() == 11))
                                   spawn1 = true;
                              else if(p.getTileX() == 18 && (p.getTileY() == 2 || p.getTileY() == 3))
                              { 
                                   spawn2 = true;
                                   spawn1 = false;
                              }
                              if((coins == 0) && (p.getTileX() == 18 && (p.getTileY() == 6 || p.getTileY() == 7)))
                                   levelComplete();
                              break;
                         case 12:
                              if(p.getTileX() == 13 && p.getTileY() == 2 && !key)
                         { 
                              key = true;
                              keyFade = 0;
                              playKey();
                              repaint();
                         }
                              if((coins == 0) && ((p.getTileX() == 7 || p.getTileX() == 8) && (p.getTileY() == 7 || p.getTileY() == 8)))
                                   levelComplete();
                              break;
                         case 13:
                              if(p.getTileX() == 14 && p.getTileY() == 1 && !key)
                         { 
                              key = true;
                              keyFade = 0;
                              playKey();
                              repaint();
                         }
                              if((coins == 0) && ((p.getTileX() == 13 || p.getTileX() == 14) && (p.getTileY() == 9 || p.getTileY() == 10)))
                                   levelComplete();
                              break;
                         case 14:
                              if((coins == 0) && ((p.getTileX() == 23 || p.getTileX() == 24) && (p.getTileY() == 13 || p.getTileY() == 14)))
                              levelComplete();
                         case 15:
                              if(p.getTileX() == 6 && p.getTileY() == 6 && !key)
                         { 
                              key = true;
                              keyFade = 0;
                              playKey();
                              repaint();
                         }
                              else if(p.getTileX() == 16 && p.getTileY() == 2 && !key2)
                              { 
                                   key2 = true;
                                   keyFade1 = 0;
                                   playKey();
                                   repaint();
                              }
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
     }//end DrawMaze*/
}