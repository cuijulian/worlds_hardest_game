import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import sun.audio.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Menu implements ActionListener
{
     //Components for file reader and writer
     static FileWriter fw, fileEasy;
     static PrintWriter outFile, outfile, outFileEasy;
     static String username;
     static int death = 0;
     int level, countEasy, countMedium, countHard;
     static boolean isLoad = false;
     static boolean easy = false;
     static boolean medium = false;
     static boolean hard = false;
     static boolean cheat = false;
     static Scanner scanFile, scanEasy;
     
     //Components for main menu
     JFrame menuFrame = new JFrame("Title");
     
     ImageIcon imgTitle;
     
     JLabel lblTitle = new JLabel();
     
     JPanel masterPanel = new JPanel();
     JPanel imagePanel = new JPanel();
     JPanel btnPanelPlay = new JPanel();
     JPanel btnPanelCheat = new JPanel();
     JPanel btnPanelInstruct = new JPanel();
     JPanel btnPanelBoard = new JPanel();
     
     JButton btnPlay = new JButton("Play");
     JButton btnCheat = new JButton("Cheat Mode");
     JButton btnInstruct = new JButton("Instructions");
     JButton btnBoard = new JButton("Leaderboard");
     
     //Components for new game frame
     JFrame loadFrame = new JFrame("New Game or Load Game?");
     JFrame boardFrame = new JFrame("Select Leaderboard");
     JFrame boardEasy = new JFrame("Leader board: Easy Mode");
     JFrame boardMedium = new JFrame("Leader board: Medium Mode");
     JFrame boardHard = new JFrame("Leader board: Hard Mode");
     
     JPanel pnlBoardEasy = new JPanel();
     JPanel pnlBoardMedium = new JPanel();
     JPanel pnlBoardHard = new JPanel();
     
     JLabel lblBoardEasy = new JLabel();
     JLabel lblBoardEasy1 = new JLabel();
     JLabel lblBoardEasy2 = new JLabel();
     JLabel lblBoardEasy3 = new JLabel();
     JLabel lblBoardEasy4 = new JLabel();
     JLabel lblBoardEasy5 = new JLabel();
     
     JLabel lblBoardMedium = new JLabel();
     JLabel lblBoardMedium1 = new JLabel();
     JLabel lblBoardMedium2 = new JLabel();
     JLabel lblBoardMedium3 = new JLabel();
     JLabel lblBoardMedium4 = new JLabel();
     JLabel lblBoardMedium5 = new JLabel();
     
     JLabel lblBoardHard = new JLabel();
     JLabel lblBoardHard1 = new JLabel();
     JLabel lblBoardHard2 = new JLabel();
     JLabel lblBoardHard3 = new JLabel();
     JLabel lblBoardHard4 = new JLabel();
     JLabel lblBoardHard5 = new JLabel();
     
     JPanel masterPanel2 = new JPanel();
     JPanel lblPanel = new JPanel();
     JPanel btnPanelNew = new JPanel();
     JPanel btnPanelLoad = new JPanel();
     
     JPanel btnBoardEasy = new JPanel();
     JPanel btnBoardMedium = new JPanel();
     JPanel btnBoardHard = new JPanel();
     
     JPanel boardPanel = new JPanel();
     JPanel lblBoard = new JPanel();
     
     JLabel lblNew_Load = new JLabel("Would you like to create a new game or load existing one?");
     JLabel lblBoardSelect = new JLabel("Select leader board mode");
     
     JButton btnNewGame = new JButton("New Game");
     JButton btnLoadGame = new JButton("Load Game");
     
     JButton btnEasyBoard = new JButton("Easy Mode");
     JButton btnMediumBoard = new JButton("Medium Mode");
     JButton btnHardBoard = new JButton("Hard Mode");
     
     //Components for new game option frame
     JFrame userFrame = new JFrame("Username");
     
     JPanel lblPanel2 = new JPanel();
     JPanel txtPanel = new JPanel();
     JPanel btnPanelEasy = new JPanel();
     JPanel btnPanelMed = new JPanel();
     JPanel btnPanelHard = new JPanel();
     JPanel masterPanel3 = new JPanel();
     
     JLabel lblName = new JLabel("Please enter your username:");
     JTextField txtName = new JTextField(12);
     
     JButton btnEnter = new JButton("Enter");
     JButton btnEasy = new JButton("Easy Mode");
     JButton btnMed = new JButton("Intermediate Mode");
     JButton btnHard = new JButton("Hard Mode");
     
     //Components for instructions frame
     JFrame infoFrame = new JFrame("Instructions");
     
     JPanel infoPanel = new JPanel();
     
     JLabel lblInfo1 = new JLabel("You are a red square. Your objective for each level is to collect all");
     JLabel lblInfo2 = new JLabel("the yellow coins and successfully travel to the final green area.");
     JLabel lblInfo3 = new JLabel("But watch out for the blue obstacles that are out to get you!");
     JLabel lblInfo4 = new JLabel("If you are hit by a blue ball, you lose your coins and respawn to");
     JLabel lblInfo5 = new JLabel("the last green area you touched in the level. Try to complete all the");
     JLabel lblInfo6 = new JLabel("levels with the least number of deaths. Good luck!");
     
     JButton btnBack = new JButton("Back to Menu");
     
     //Components for leaderboard frame
     JFrame leaderboardFrame = new JFrame("Leaderboard");
     
     JLabel lblHighScore = new JLabel("HIGH SCORES");
     
     JPanel masterPanel5 = new JPanel();
     
     //Components for load game frame
     JFrame loadGameFrame = new JFrame("Load Game");
     
     JPanel masterPanel6 = new JPanel();
     JPanel lblPanel4 = new JPanel();
     JPanel txtPanel2 = new JPanel();
     
     JLabel lblLoad = new JLabel("Please enter your existing username");
     
     JTextField txtLoad = new JTextField(12);
     
     JButton btnLoad = new JButton("Load");
     
     
     public Menu()
     {
          //Constructing menu frame
          imgTitle = new ImageIcon("Title.png");
          lblTitle.setIcon(imgTitle);
          
          imagePanel.add(lblTitle);
          
          btnPlay.addActionListener(this);
          btnCheat.addActionListener(this);
          btnInstruct.addActionListener(this);
          btnBoard.addActionListener(this);
          
          btnPanelPlay.add(btnPlay);
          btnPanelCheat.add(btnCheat);
          btnPanelInstruct.add(btnInstruct);
          btnPanelBoard.add(btnBoard);
          
          masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
          masterPanel.add(imagePanel);
          masterPanel.add(btnPanelPlay);
          masterPanel.add(btnPanelCheat);
          masterPanel.add(btnPanelInstruct);
          masterPanel.add(btnPanelBoard);
          
          menuFrame.add(masterPanel);
          
          menuFrame.pack();
          menuFrame.setVisible(true);
          menuFrame.setResizable(false);
          menuFrame.setLocationRelativeTo(null);
          
          //Constructing new game frame
          btnNewGame.addActionListener(this);
          btnLoadGame.addActionListener(this);
          
          lblPanel.add(lblNew_Load);
          btnPanelNew.add(btnNewGame);
          btnPanelLoad.add(btnLoadGame);
          
          masterPanel2.setLayout(new BoxLayout(masterPanel2, BoxLayout.Y_AXIS));
          masterPanel2.add(lblPanel);
          masterPanel2.add(btnPanelNew);
          masterPanel2.add(btnPanelLoad);
          
          loadFrame.add(masterPanel2);
          loadFrame.pack();
          loadFrame.setResizable(false);
          loadFrame.setLocationRelativeTo(null);
          
          
          //Constructing board game
          btnEasyBoard.addActionListener(this);
          btnMediumBoard.addActionListener(this);
          btnHardBoard.addActionListener(this);
          
          lblBoard.add(lblBoardSelect);
          btnBoardEasy.add(btnEasyBoard);
          btnBoardMedium.add(btnMediumBoard);
          btnBoardHard.add(btnHardBoard);
          
          boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
          boardPanel.add(lblBoard);
          boardPanel.add(btnBoardEasy);
          boardPanel.add(btnBoardMedium);
          boardPanel.add(btnBoardHard);
          
          boardFrame.add(boardPanel);
          boardFrame.pack();
          boardFrame.setResizable(false);
          boardFrame.setLocationRelativeTo(null);
          
          
          //Constructing new game option frame
          btnEnter.addActionListener(this);
          btnEasy.addActionListener(this);
          btnMed.addActionListener(this);
          btnHard.addActionListener(this);
          
          btnEasy.setEnabled(false);
          btnMed.setEnabled(false);
          btnHard.setEnabled(false);
          
          lblPanel2.add(lblName);
          
          txtPanel.add(txtName);
          txtPanel.add(btnEnter);
          
          btnPanelEasy.add(btnEasy);
          btnPanelMed.add(btnMed);
          btnPanelHard.add(btnHard);
          
          masterPanel3.setLayout(new BoxLayout(masterPanel3, BoxLayout.Y_AXIS));
          masterPanel3.add(lblPanel2);
          masterPanel3.add(txtPanel);
          masterPanel3.add(btnPanelEasy);
          masterPanel3.add(btnPanelMed);
          masterPanel3.add(btnPanelHard);
          
          userFrame.add(masterPanel3);
          userFrame.pack();
          userFrame.setResizable(false);
          userFrame.setLocationRelativeTo(null);
          
          //Constructing instructions frame
          btnBack.addActionListener(this);
          
          infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
          infoPanel.add(lblInfo1);
          infoPanel.add(lblInfo2);
          infoPanel.add(lblInfo3);
          infoPanel.add(lblInfo4);
          infoPanel.add(lblInfo5);
          infoPanel.add(lblInfo6);
          infoPanel.add(btnBack);
          
          infoFrame.add(infoPanel);
          infoFrame.pack();
          infoFrame.setResizable(false);
          infoFrame.setLocationRelativeTo(null);
          
          //Constructing leaderboard frame
          
          //Constructing load game frame  
          btnLoad.addActionListener(this);
          
          lblPanel4.add(lblLoad);
          
          txtPanel2.add(txtLoad);
          txtPanel2.add(btnLoad);
          
          masterPanel6.setLayout(new BoxLayout(masterPanel6, BoxLayout.Y_AXIS));
          masterPanel6.add(lblPanel4);
          masterPanel6.add(txtPanel2);
          
          loadGameFrame.add(masterPanel6);
          loadGameFrame.pack();
          loadGameFrame.setResizable(false);
          loadGameFrame.setLocationRelativeTo(null);
          
          lblBoardEasy.setText("Rank    " + "User    " + "Level    " + "Death ");
          lblBoardEasy1.setText("1         " + sortLeaderBoard(0, "easy") + "       " + readLevel(sortLeaderBoard(0, "easy")) + "       " + readDeath(sortLeaderBoard(0, "easy")));
          lblBoardEasy2.setText("2         " + sortLeaderBoard(1, "easy") + "       " + readLevel(sortLeaderBoard(1, "easy")) + "       " + readDeath(sortLeaderBoard(1, "easy")));
          lblBoardEasy3.setText("3         " + sortLeaderBoard(2, "easy") + "       " + readLevel(sortLeaderBoard(2, "easy")) + "       " + readDeath(sortLeaderBoard(2, "easy")));
          lblBoardEasy4.setText("4         " + sortLeaderBoard(3, "easy") + "       " + readLevel(sortLeaderBoard(3, "easy")) + "       " + readDeath(sortLeaderBoard(3, "easy")));
          lblBoardEasy5.setText("5         " + sortLeaderBoard(4, "easy") + "       " + readLevel(sortLeaderBoard(4, "easy")) + "       " + readDeath(sortLeaderBoard(4, "easy")));
          
          pnlBoardEasy.setLayout(new BoxLayout(pnlBoardEasy, BoxLayout.Y_AXIS));
          pnlBoardEasy.add(lblBoardEasy);
          pnlBoardEasy.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardEasy.add(lblBoardEasy1);
          pnlBoardEasy.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardEasy.add(lblBoardEasy2);
          pnlBoardEasy.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardEasy.add(lblBoardEasy3);
          pnlBoardEasy.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardEasy.add(lblBoardEasy4);
          pnlBoardEasy.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardEasy.add(lblBoardEasy5);
          
          boardEasy.add(pnlBoardEasy);
          boardEasy.pack();
          boardEasy.setResizable(false);
          boardEasy.setLocationRelativeTo(null);
          
          lblBoardMedium.setText("Rank    " + "User    " + "Level    " + "Death ");
          lblBoardMedium1.setText("1         " + sortLeaderBoard(0, "medium") + "       " + readLevel(sortLeaderBoard(0, "medium")) + "       " + readDeath(sortLeaderBoard(0, "medium")));
          lblBoardMedium2.setText("2         " + sortLeaderBoard(1, "medium") + "       " + readLevel(sortLeaderBoard(1, "medium")) + "       " + readDeath(sortLeaderBoard(1, "medium")));
          lblBoardMedium3.setText("3         " + sortLeaderBoard(2, "medium") + "       " + readLevel(sortLeaderBoard(2, "medium")) + "       " + readDeath(sortLeaderBoard(2, "medium")));
          lblBoardMedium4.setText("4         " + sortLeaderBoard(3, "medium") + "       " + readLevel(sortLeaderBoard(3, "medium")) + "       " + readDeath(sortLeaderBoard(3, "medium")));
          lblBoardMedium5.setText("5         " + sortLeaderBoard(4, "medium") + "       " + readLevel(sortLeaderBoard(4, "medium")) + "       " + readDeath(sortLeaderBoard(4, "medium")));
          
          pnlBoardMedium.setLayout(new BoxLayout(pnlBoardMedium, BoxLayout.Y_AXIS));
          pnlBoardMedium.add(lblBoardMedium);
          pnlBoardMedium.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardMedium.add(lblBoardMedium1);
          pnlBoardMedium.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardMedium.add(lblBoardMedium2);
          pnlBoardMedium.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardMedium.add(lblBoardMedium3);
          pnlBoardMedium.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardMedium.add(lblBoardMedium4);
          pnlBoardMedium.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardMedium.add(lblBoardMedium5);
          
          boardMedium.add(pnlBoardMedium);
          boardMedium.pack();
          boardMedium.setResizable(false);
          boardMedium.setLocationRelativeTo(null);
          
          lblBoardHard.setText("Rank    " + "User    " + "Level    " + "Death ");
          lblBoardHard1.setText("1         " + sortLeaderBoard(0, "hard") + "       " + readLevel(sortLeaderBoard(0, "hard")) + "       " + readDeath(sortLeaderBoard(0, "hard")));
          lblBoardHard2.setText("2         " + sortLeaderBoard(1, "hard") + "       " + readLevel(sortLeaderBoard(1, "hard")) + "       " + readDeath(sortLeaderBoard(1, "hard")));
          lblBoardHard3.setText("3         " + sortLeaderBoard(2, "hard") + "       " + readLevel(sortLeaderBoard(2, "hard")) + "       " + readDeath(sortLeaderBoard(2, "hard")));
          lblBoardHard4.setText("4         " + sortLeaderBoard(3, "hard") + "       " + readLevel(sortLeaderBoard(3, "hard")) + "       " + readDeath(sortLeaderBoard(3, "hard")));
          lblBoardHard5.setText("5         " + sortLeaderBoard(4, "hard") + "       " + readLevel(sortLeaderBoard(4, "hard")) + "       " + readDeath(sortLeaderBoard(4, "hard")));
          
          pnlBoardHard.setLayout(new BoxLayout(pnlBoardHard, BoxLayout.Y_AXIS));
          pnlBoardHard.add(lblBoardHard);
          pnlBoardHard.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardHard.add(lblBoardHard1);
          pnlBoardHard.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardHard.add(lblBoardHard2);
          pnlBoardHard.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardHard.add(lblBoardHard3);
          pnlBoardHard.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardHard.add(lblBoardHard4);
          pnlBoardHard.add(Box.createRigidArea(new Dimension(0, 20)));
          pnlBoardHard.add(lblBoardHard5);
          
          boardHard.add(pnlBoardHard);
          boardHard.pack();
          boardHard.setResizable(false);
          boardHard.setLocationRelativeTo(null);
          
     }//end Menu() constructor
     
     public void actionPerformed(ActionEvent e)
     {
          //If play button is pressed
          if (e.getSource() == btnPlay)
          {
               menuFrame.setVisible(false);
               loadFrame.setVisible(true);
          }
          else if(e.getSource() == btnCheat)
          {
               menuFrame.setVisible(false);
               loadFrame.setVisible(true);
               cheat = true;
          }
          //If instructions button is pressed
          else if (e.getSource() == btnInstruct)
          {
               menuFrame.setVisible(false);
               infoFrame.setVisible(true);
               infoFrame.setLocationRelativeTo(null);
          }
          //If leaderboard button is pressed
          else if (e.getSource() == btnBoard)
          {
               menuFrame.setVisible(false);
               boardFrame.setVisible(true);
               boardFrame.setLocationRelativeTo(null);
          }
          
          else if(e.getSource() == btnEasyBoard)
          {
               boardFrame.setVisible(false);
               boardEasy.setVisible(true);  
          }
          else if(e.getSource() == btnMediumBoard)
          {
               boardFrame.setVisible(false);
               boardMedium.setVisible(true);
          }
          else if(e.getSource() == btnHardBoard)
          {
               boardFrame.setVisible(false);
               boardHard.setVisible(true);
          }
          //If back to menu button is pressed
          else if (e.getSource() == btnBack)
          {
               infoFrame.setVisible(false);
               menuFrame.setVisible(true);
          }
          //If enter username button is pressed
          else if (e.getSource() == btnEnter)
          {
               username = txtName.getText();
               
               if (!(username.equals("")))
               {
                    try
                    {
                         if(!cheat)
                              outFile = new PrintWriter(username + "-Level.txt");
                         else
                              outFile = new PrintWriter(username + "-CheatLevel.txt");
                         PrintWriter outFile2 = new PrintWriter(username + "-Death.txt");
                         outFile2.println(death);
                         outFile2.close();
                         if(!cheat)
                         {
                              fw = new FileWriter("Accounts.txt", true);
                              outfile = new PrintWriter(fw);
                              outfile.println(username);
                              outfile.close();
                         }
                    }
                    catch(IOException i)
                    {
                         
                    }
                    
                    txtName.setEnabled(false);
                    btnEnter.setEnabled(false);
                    btnHard.setEnabled(true);
                    btnMed.setEnabled(true);
                    btnEasy.setEnabled(true);
               }
               else 
               {
                    txtName.setText("");
                    lblName.setText("Please try again");
               }
          }
          //If new game button is pressed
          else if (e.getSource() == btnNewGame)
          {
               loadFrame.setVisible(false);
               userFrame.setVisible(true);
          }
          //If load game button is pressed
          else if (e.getSource() == btnLoadGame)
          {
               loadFrame.setVisible(false);
               loadGameFrame.setVisible(true);
          }
          //If load account button is pressed
          else if (e.getSource() == btnLoad)
          {
               //Gets username from text field to load file
               username = txtLoad.getText();
               
               //Tries to load corresponding file; if it doesn't exist try again
               try 
               {
                    if(!cheat)
                         scanFile = new Scanner(new File(username + "-Level.txt"));
                    else
                         scanFile = new Scanner(new File(username + "-CheatLevel.txt"));
                    loadGameFrame.setVisible(false);
                    
                    isLoad = true;
                    
                    level = scanFile.nextInt();
                    System.out.println(level);
                    if(level >= 1 && level <= 5)
                    {
                         
                         easy = true;
                         playSound();
                         new MazeGame();
                    }
                    else if(level >= 6 && level <= 10)
                    { 
                         medium = true;
                         playSound();
                         new Medium();
                    }
                    else if(level >= 11 && level <= 15)
                    { 
                         hard = true;
                         playSound();
                         new Hard();
                    }
               }
               catch (FileNotFoundException i)
               {
                    txtLoad.setText("");
                    lblLoad.setText("Account does not exist. Try again.");
               }
               
               //Makes a MazeGame() object that starts at the given level
               //new MazeGame();
          }
          //If easy mode button is pressed
          else if (e.getSource() == btnEasy)
          {
               outFile.println(1);
               outFile.close();
               try{ 
                    if(!cheat)
                    {
                         scanEasy = new Scanner(new File("EasyAccountNumber.txt"));
                         countEasy = scanEasy.nextInt() + 1;
                         System.out.println(countEasy);
                         outFileEasy = new PrintWriter("EasyAccountNumber.txt");
                         outFileEasy.println(countEasy);
                         outFileEasy.close();
                    }
               }
               catch(IOException ie){
                    System.out.println("NO LEVEL");
               }
               
               easy = true;
               playSound();
               userFrame.setVisible(false);
               new MazeGame();
               
          }
          //If intermediate mode button is pressed
          else if (e.getSource() == btnMed)
          {
               outFile.println(6);
               outFile.close();
               try{ 
                    if(!cheat)
                    {
                         scanEasy = new Scanner(new File("MediumAccountNumber.txt"));
                         countMedium = scanEasy.nextInt() + 1;
                         System.out.println(countMedium);
                         outFileEasy = new PrintWriter("MediumAccountNumber.txt");
                         outFileEasy.println(countMedium);
                         outFileEasy.close();
                    }
               }
               catch(IOException ie){
                    System.out.println("NO LEVEL");
               }
               playSound();
               medium = true;
               userFrame.setVisible(false);
               //Creates intermediate game mode object
               new Medium();
          }
          //If hard mode button is pressed
          else if (e.getSource() == btnHard)
          {
               try{ 
                    if(!cheat)
                    {
                         scanEasy = new Scanner(new File("HardAccountNumber.txt"));
                         countHard = scanEasy.nextInt() + 1;
                         System.out.println(countHard);
                         outFileEasy = new PrintWriter("HardAccountNumber.txt");
                         outFileEasy.println(countHard);
                         outFileEasy.close();
                    }
               }
               catch(IOException ie){
                    System.out.println("NO LEVEL");
               }
               
               outFile.println(11);
               outFile.close();
               hard = true;
               playSound();
               userFrame.setVisible(false);
               //Creates hard game mode object
               new Hard();
          }
     }//end ActionPerformed(ActionEvent)
     
     public void playSound()
     {
          try{
               Clip clip = AudioSystem.getClip();
               AudioInputStream inputStream = AudioSystem.getAudioInputStream(Menu.class.getResource("growing_on_me.wav"));
               clip.open(inputStream);
               clip.loop(Clip.LOOP_CONTINUOUSLY);
               clip.start();
          }catch(IOException e){
               e.printStackTrace();
          }
          catch(UnsupportedAudioFileException ua)
          {
               ua.printStackTrace();
          }
          catch(LineUnavailableException l)
          {
               l.printStackTrace();
          }
     }
     
     public int readLevel(String s)
     {
          try{
               Scanner scan = new Scanner(new File(s + "-Level.txt"));
               int count = scan.nextInt();
               return count;
          }
          catch(FileNotFoundException fn){
               return 0;
          }
     }
     
     public int readDeath(String s)
     {
          try{
               Scanner scan = new Scanner(new File(s + "-Death.txt"));
               int count = scan.nextInt();
               return count;
          }
          catch(FileNotFoundException n){
               return 0;
          }
     }
     
     public String sortLeaderBoard(int x, String c)
     {
          Scanner scan;
          FileWriter file;
          PrintWriter out;
          String user;
          int currentLevel;
          int deaths;
          
          //Counters used to populate account arrays
          int counterEasy = 0, counterMedium = 0, counterHard = 0;
          
          //Loads previous counter values
          try
          {
               scan = new Scanner(new File("EasyAccountNumber.txt"));
               counterEasy = scan.nextInt();
               if(counterEasy == 0 && c.equals("easy"))
                    return "--------";
               
               scan = new Scanner(new File("MediumAccountNumber.txt"));
               counterMedium = scan.nextInt();
               if(counterMedium == 0 && c.equals("medium"))
                    return "--------";
               
               scan = new Scanner(new File("HardAccountNumber.txt"));
               counterHard = scan.nextInt();
               if(counterHard == 0 && c.equals("hard"))
                    return "--------";
          }
          catch (FileNotFoundException it)
          {
               
          }
          
          String [] easyAccounts = new String [counterEasy];
          String [] mediumAccounts = new String [counterMedium];
          String [] hardAccounts = new String [counterHard];
          
          counterEasy = 0; counterMedium = 0; counterHard = 0;
          try{ 
               scan = new Scanner(new File("Accounts.txt"));
               
               //Keeps iterating until all accounts are read in the file
               while (scan.hasNextLine())
               {
                    //Gets next username from account text file
                    user = scan.nextLine();
                    
                    //Reads off of corresponding account level file
                    Scanner scanFile2 = new Scanner(new File(user + "-Level.txt"));
                    int level = scanFile2.nextInt();
                    
                    //If level is in the easy difficulty
                    if (level <= 5)
                    {
                         easyAccounts[counterEasy] = user;
                         counterEasy += 1;
                    }
                    else if (level >= 6 && level <= 10)
                    {
                         mediumAccounts[counterMedium] = user;
                         counterMedium += 1;
                    }
                    else if (level >= 11 && level <= 15)
                    {
                         hardAccounts[counterHard] = user;
                         counterHard += 1;
                    }
               }//end while
          }
          catch (FileNotFoundException in)
          {
               
          }
          
          if(c.equals("easy"))
          { 
               try{
                    insertionSort(easyAccounts);
                    return (easyAccounts[x]);
               }
               catch(Exception en){
                    return "--------";
               }
          }
          else if(c.equals("medium"))
          { 
               try{
                    insertionSort(mediumAccounts);
                    return (mediumAccounts[x]);
               }
               catch(Exception exc){
                    return "--------";
               }
          }
          else if(c.equals("hard"))
          {
               try{
                    insertionSort(hardAccounts);
                    return (hardAccounts[x]);
               }catch(Exception ce){
                    return "--------";
               }
          }
          return c;
     }
     
     public static void insertionSort (String [] list)
     {
          for (int top = 1; top < list.length; top++)
          {
               int accountLevel = 0, account2Level = 0, death = 0, death2 = 0;
               
               String account = list[top];
               int i = top;
               
               String account2 = list[i-1];
               
               //Reads level and death values for each account being compared from text files
               try
               {
                    Scanner outFile = new Scanner(new File(account + "-Level.txt"));
                    accountLevel = outFile.nextInt();
                    
                    Scanner outFile2 = new Scanner(new File(account + "-Death.txt"));
                    death = outFile2.nextInt();
               }
               catch (FileNotFoundException e)
               {
                    
               }
               
               //Reads level and death values for list[i-1]
               try
               {
                    Scanner outFile = new Scanner(new File(account2 + "-Level.txt"));
                    account2Level = outFile.nextInt();
                    
                    Scanner outFile2 = new Scanner(new File(account2 + "-Death.txt"));
                    death2 = outFile2.nextInt();
               }
               catch (FileNotFoundException e)
               {
                    
               }
               
               //If accounts have the same level and one has a greater number of deaths
               if (accountLevel == account2Level && death < death2)
               {
                    list[i] = list [i-1];
                    i--;
               }
               
               //While 
               while (i > 0 && accountLevel > account2Level)
               {
                    list[i] = list [i-1];
                    
                    i--;
               }
               
               list[i] = account;
          }
     }
     public static void main (String [] args)
     {
          new Menu();
     }//end main
     
}//end Menu class