/* 
 *  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 
 *  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *  @@                                                               @@
 *  @@                                                               @@
 *  @@        #######   #######    ###    #######  ###  ###          @@
 *  @@        ########  ########   ###   #######   ### ###           @@
 *  @@        ###  ##   ###  ###   ###  ####       ######            @@
 *  @@        ######    #######    ###  ###        ####              @@
 *  @@        #######   ### ###    ###  ###        ######            @@
 *  @@        ###  ###  ###  ###   ###  ####       ### ###           @@
 *  @@        #######   ###   ###  ###   #######   ###  ###          @@
 *  @@        ######    ###    ##  ###    #######  ###   ###         @@
 *  @@                                                               @@
 *  @@                                                               @@
 *	@@	        ######       ###       ###   ###   #########         @@
 *  @@         #######      #####      #### ####   ########          @@
 *  @@        ####         ### ###     #########   ####              @@
 *  @@        ###         ###   ###    ### # ###   #########         @@
 *  @@        ###  ###    #########    ###   ###   ########          @@
 *  @@        ###   ###   #########    ###   ###   ####              @@
 *  @@         #######    ###   ###    ###   ###   #########         @@
 *  @@          #####     ###   ###    ###   ###   ########          @@
 *  @@                                                               @@
 *  @@		         Five games in one: TETRIS, SNAKE...             @@
 *  @@                                                               @@
 *  @@        Bonfigli Diego 2002-2003 Tolentino (MC) - ITALY        @@
 *  @@                                                               @@
 *  @@                   d.bonfigli@tiscalinet.it                    @@
 *  @@                                                               @@
 *  @@ 4°year I.T.I.S. E.DIVINI S.Severino (MC) high school student  @@
 *  @@                     Summer school work                        @@
 *  @@                                                               @@
 *  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *	
 *  
 *  *--------------------------------------------*
 *  |                                            |
 *  |               produced with:               |
 *  |                                            | 
 *  |       Java(TM) 2 SDK, Standard Edition     |
 *  |               Version 1.4.0                |
 *  |                                            |
 *  |                    and                     |		
 *  |                                            |
 *  |   JCreator(TM) LE Release V2.50 build 8    |
 *  |   (32 bit) for Win 95/98/NT/ME/2000/XP.    |
 *  |   Copyright(C) 2000-2002 Xinox Software	 |
 *  |                                            |
 *  |          All Rights Reserved.              |
 *  |                                            |
 *  |   Operating System : Windows  98           |
 *  |	Browser          : Explorer 6.0          |	
 *  |                                            |
 *  *--------------------------------------------* 
 *
 *
 * 09.7.03  - start
 * 22.7.03  - rotation accomplished
 * 25.7.03  - first version accomplished 
 * ...........
 * 25.10.03 - version 0.10 accomplished
 * 02.05.04 - snake version accomplished
 *
 * The main problem was the keyboard management: in the beginning I used the 
 * KeyListener interface and its methods but (mainly with Explorer) I noticed 
 * it sometimes caused an error when closing the browser:
 * "java.lang.IllegalStateException: Can't dispose Inputcountext while it's active"
 * So I used keyDown.
 *
 * Another problem belongs the users interface: I made several tests to display 
 * in the way I wanted panels and canvas, specially their dimension, but in the 
 * end I was forced to use that one of the BorderLayout.
 * 
 *
 * Example of HTML page:
 *
 * <HTML>
 *
 * <head>
 *  	<title>	TETRIS </title>
 * </head>
 * <BODY> 
 * 		<applet code="Base.class"
 *			align="baseline" width=310 height=360>
 *				<param name="buttonColorRGB" value="110,250,110">
 *				<param name="fontColorRGB" value="0,100,100">
 *				<param name="panelColorRGB" value="154,209,183">
 *				<param name="piecesName" value="pieces.gif">
 *				<param name="backgroundName" value="3812.jpg">
 * 		</applet>
 * </BODY>
 * </HTML>
 *
 * Parameters are optional (but reccomanded to have a better graphic):
 * they belong colors (you can change button, panel and font colors:to do this
 * you need to put 3 values, red green blue, between 0 and 255 divided by commas 
 * una virgola upl parametro specifico), background image ("backgroundName") and
 * pieces image("piecesName") (further information in "Table" class). If not
 * inserted (or bad inserted), the game will do work but pieces and background
 * will be produced by javalenguage methods.
 *
 * The applet does work with any dimension and the Tabme canvas is put in the 
 * centre but some S.O. (such as WinXP) give less space to the table canvas 
 * and sometimes it is covered by the panel in the right, in this case you must 
 * spread the applet in the HTML page.
 * The best dimension is, however, width=height/2+130 (win98 S.O.).
 *
 * There are 5 games: 
 * 1) classic Tetris; 
 * 2) Tetris-strange-pieces; 
 * 3) Tetris-destroy-last-row: purpose is to destroy last row 
 *    (that one with the yellow brick);
 * 4) Tetris-destroy-last-row & strange-pieces;
 * 5) Snake. 
 *
 * Tetris Commands: 
 * left arrow: move the piece left; 
 * right arrow: move the piece right; 
 * up arrow: rotation;
 * down arrow: fast fall. 
 * 
 * Speed automatically increments every 10000 points.
 * The level point the starting difficult (it will appear some bricks in the
 * bottom of the table in random choice)
 *
 * Snake Commands:
 * keyboard arrow: snake movements.
 *
 */
 
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ BASE : THE MAIN CLASS @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 
 // Base Class: is the applet that build the users interface and give the main 
 // methods of the program structure.
 
import java.awt.*;	        
import java.awt.event.*;
import java.awt.image.*; 
import java.applet.*;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.util.StringTokenizer;
import java.util.Vector;


public class Base extends Applet implements ActionListener, Runnable	{
	
	/*volatile*/ private Thread thread;
	
	//sounds
	private AudioClip endSound;
	private AudioClip explodedLineSound;
	private AudioClip landedPieceSound;
	private AudioClip startSound;
	
	//interface
	private Button buttonStart = new Button("START");
	private Button buttonPause = new Button("PAUSE");
	private Button buttonResume = new Button("RESUME");
	private Button buttonEnd = new Button("END");
	
	private Choice choiceSpeed = new Choice();
	private Choice choiceLevel = new Choice();
	private Choice choiceGame = new Choice();
	
	private Label labelScore = new Label("0", Label.LEFT);
	private Label labelLines = new Label("0", Label.LEFT);
	private Label labelRows = new Label("rows:");
	private Label labelPoints = new Label("score:");
	private Label labelLevel = new Label("level:");
	private Label labelSpeed = new Label("speed:");
	private Label labelGame = new Label("game:");
	
	private JPanel panelW;
	private JPanel panelE;
	
	private Checkbox checkboxSound = new Checkbox("sound", true);
 	
	//matrix of the game table
	private int columns = 10;
	private int rows = 20;
	private int mat[][]= new int [rows][columns];
	
	//score
	private int scoreRows = 0;
	private int scorePoints = 0;
	
	/* currentGame, in wich the specific game rules are loaded (there are 4
	 * games, it means 4 rule class, any rule class has same interface or 
	 * public methods, so you can use Base class to build other rule class 
	 * eaven very different form tetris such as snake or a shot-game).
	 */   
	 
	private Game currentGame = new Game(this);
	
	//canvas preview e Table
	private Table table;
	private Preview preview;
	
	//timer
	private int timer = 400;
	
	//gameIsActive: variance that show if game is active
	private boolean gameIsActive = false;
	
	//colors
	private Color fontColor;
	private Color buttonColor;
	private Color panelColor;
	
	//##### keyboard method ##############################

	public boolean keyDown(Event e, int key)
	{
		if (getGameIsActive())
		{ currentGame.manageKeyboard(key); } 	
		return false;
	}
	
	//##### ActionListener ################################
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("PAUSE"))
			{ buttonPause.enable(false); 
			  buttonResume.enable(true);
			  buttonResume.requestFocus();
			  setGameIsActive(false);
			  stop(); }
		
		if(e.getActionCommand().equals("RESUME")) 
			{ start();
			  buttonResume.enable(false);
			  buttonPause.enable(true);
			  setGameIsActive(true);
			  table.requestFocus(); 
		    }
		
		if(e.getActionCommand().equals("START"))
		 	{ 
		 	  switch (getGameSelectedIndex()+1)
		 	  {
		 	  	case 1: currentGame = new Tetris(this);         break;
		 	  	case 2: currentGame = new TetrisSp(this);       break;
		 	  	case 3: currentGame = new TetrisRow(this);      break;
		 	  	case 4: currentGame = new TetrisSpRow(this);    break;
		 	  	case 5: currentGame = new Snake(this);			break;
		 	  } 
		 	  currentGame.startGame();
		 	  drawTable();
		 	} 
		
		if(e.getActionCommand().equals("END"))
			{ 
			  currentGame.endGame();
		 	} 
		
	}
	
	//##### init e destroy #######################################
	
	public void init()
	{	
		//audio
		endSound = getAudioClip(getCodeBase(),"die.au");
		explodedLineSound = getAudioClip(getCodeBase(),"driga.au");
		startSound = getAudioClip(getCodeBase(),"begin.au");
		landedPieceSound = getAudioClip(getCodeBase(),"terra.au");
		
		eraseMatrix();	
		
		//produce i colori
		getAllColors();		
		
		//table
		table = new Table(this);
		
		//preview 
		preview = new Preview(this);
		
		//panel West
	 	panelW = new JPanel();
	 	panelW.setBackground(Color.red);
		panelW.setLayout(new GridLayout(0,1, 5, 5));
		panelW.setBorder(BorderFactory.createRaisedBevelBorder());
		
		panelW.add(labelPoints);
		panelW.add(labelScore);
		panelW.add(labelRows);
		panelW.add(labelLines);
		panelW.add(checkboxSound);
		
		panelW.add(labelSpeed);
		panelW.add(choiceSpeed);
		choiceSpeed.addItem("1");
		choiceSpeed.addItem("2");
		choiceSpeed.addItem("3");
		choiceSpeed.addItem("4");
		choiceSpeed.addItem("5");
		choiceSpeed.addItem("6");
		choiceSpeed.addItem("7");
		choiceSpeed.addItem("8");
		choiceSpeed.addItem("9");
		choiceSpeed.addItem("10");
		
		panelW.add(labelLevel);
		panelW.add(choiceLevel);
		choiceLevel.addItem("1");
		choiceLevel.addItem("2");
		choiceLevel.addItem("3");
		choiceLevel.addItem("4");
		choiceLevel.addItem("5");
		choiceLevel.addItem("6");
		choiceLevel.addItem("7");
		choiceLevel.addItem("8");
		choiceLevel.addItem("9");
		choiceLevel.addItem("10");
		choiceLevel.addItem("11");
		choiceLevel.addItem("12");
		choiceLevel.addItem("13");
		
		panelW.add(labelGame);
		panelW.add(choiceGame);
		choiceGame.addItem("1");
		choiceGame.addItem("2");
		choiceGame.addItem("3");
		choiceGame.addItem("4");
		choiceGame.addItem("5");
			
		//actionListeners
		buttonStart.addActionListener(this); 
		buttonPause.addActionListener(this);
		buttonResume.addActionListener(this);
		buttonEnd.addActionListener(this);
		
		// panel Est
		panelE = new JPanel();
		panelE.setBackground(Color.red);
		panelE.setLayout(new GridLayout(0, 1, 5, 5));
		panelE.setBorder(BorderFactory.createRaisedBevelBorder());
		
		panelE.add(buttonStart);
		panelE.add(buttonPause);
		panelE.add(buttonResume);
		panelE.add(buttonEnd);
		panelE.add(preview);
		
		//applet look
		manageColors(); 
		
		setLayout(new BorderLayout());
		add(panelE, "East");
		add(table, "Center");
  		add(panelW, "West");
  		
		buttonEnd.enable(false);
		buttonResume.enable(false);
		buttonPause.enable(false);
	}
		
	//##### Start, Stop, Runnable methods #########################

	public void run()
    {   
        while (thread == Thread.currentThread()) 
        	{  	
        		if (getGameIsActive()) currentGame.fall(); 		
        		try { Thread.currentThread().sleep(timer); } 
        		catch (InterruptedException e) {}
        	}
    }	

	public void stop() 
	{	
	//  if (thread!=null) { thread.stop(); }
	//  thread.stop is deprecated and seems to produce bugs
	thread=null;
	}
			
	public void start() 
	{ 
		if (thread == null) {
			thread = new Thread(this); 
		//	thread.setPriority(Thread.MIN_PRIORITY);
			thread.start(); 
			}
        //thread.start(); 
	}
	
	//##### useful methods #######################
	
	//##### get & set ############################
	
	public void setMat(int[][] m)
	{	for (int i=0; i<rows; i++)
			for (int j=0; j<columns; j++)
					mat[i][j]=m[i][j];
	}
	
	public int[][] getMat()
	{	int[][] z = new int[rows][columns];
		for (int i=0; i<rows; i++)
			for (int j=0; j<columns; j++)
				z[i][j]=mat[i][j];	
		return z; 
	} 
	
	public void setScoreRows(int n) { scoreRows=n; }
	public int getScoreRows() { return scoreRows; }

	public void setScorePoints(int n) { scorePoints=n; }
	public int getScorePoints() { return scorePoints; }

	public int getColumns() { return columns; }
	public int getRows() { return rows; }
	
	public int getTimer() { return timer; }
	public void setTimer(int t) { timer=t; }
	
	public void setGameIsActive(boolean g) { gameIsActive = g;}
	public boolean getGameIsActive() { return (gameIsActive); }
	
	public int getPiecePreview() { return currentGame.getPiecePreview(); }
	public int getPieceColorPreview() 
		{ return currentGame.getPieceColorPreview(); }
	
	public int getSpeedSelectedIndex() {return choiceSpeed.getSelectedIndex();}
	public void setSpeedSelectedIndex(int index)   {choiceSpeed.select(index);}
	
	public int getLevelSelectedIndex() {return choiceLevel.getSelectedIndex();}
	public void setLevelSelectedIndex(int index) { choiceLevel.select(index);}
	
	public int getGameSelectedIndex() {return choiceGame.getSelectedIndex();}
	public void setGameSelectedIndex(int index) {choiceGame.select(index);}
	
	public Color getFontColor() {return new Color(fontColor.getRGB());}
	public Color getButtonColor() {return new Color(buttonColor.getRGB());}
	public Color getPanelColor() {return new Color(panelColor.getRGB());}
	
	public Image[] getPieces() {return table.getPieces();}
	
	//##### matrix operations #############
	
	public void eraseMatrix()
	{	for (int i=0; i<rows; i++)
			for (int j=0; j<columns; j++)
					mat[i][j]=0;	
	}
	
	/*
	//caracter view (used in debug)
	public void viewMat()		
	{ for (int i=0; i<rows; i++)
	   {	System.out.println();
			for (int j=0; j<columns; j++)
				System.out.print (mat[i][j]);
		}
	  System.out.println("-------------");  
	}*/
	
	public void printEND()
	{ 
	  //FINE (End in italian)
	  /* 
	  mat[4][1]=mat[4][2]=mat[4][3]=mat[4][7]=6;
	  mat[5][1]=mat[5][7]=6;  									
	  mat[6][1]=mat[6][2]=mat[6][3]=mat[6][7]=6;
	  mat[7][1]=mat[7][7]=6;  								
	  mat[8][1]=mat[8][7]=6;								
	  
	  mat[11][1]=mat[11][4]=mat[11][6]=mat[11][7]=mat[11][8]=6;		
	  mat[12][1]=mat[12][2]=mat[12][4]=mat[12][6]=6; 			
	  mat[13][1]=mat[13][3]=mat[13][4]=mat[13][6]=mat[13][7]=mat[13][8]=6;
	  mat[14][1]=mat[14][4]=mat[14][6]=6; 						    
	  mat[15][1]=mat[15][4]=mat[15][6]=mat[15][7]=mat[15][8]=6; 		
	  */
	  
	  //END
	  mat[1][1]=mat[1][2]=mat[1][3]=mat[2][1]=mat[3][1]=mat[3][2]=6;
	  mat[3][3]=mat[4][1]=mat[5][1]=mat[5][2]=mat[5][3]=6;
	  mat[7][3]=mat[7][6]=mat[8][3]=mat[8][4]=mat[8][6]=mat[9][3]=mat[9][5]=6;
	  mat[9][6]=mat[10][3]=mat[10][6]=mat[11][3]=mat[11][6]=mat[13][5]=6;
	  mat[13][6]=mat[14][5]=mat[14][7]=mat[15][5]=mat[15][8]=mat[16][5]=6;
	  mat[16][8]=mat[17][5]=mat[17][6]=6;mat[17][7]=mat[17][8]=6;
	  
	  
	  drawTable();
	}
	
	//##### sound methods ####################
	
	public void playEndSound()
	{   if (checkboxSound.getState() == true)
		if (endSound != null) endSound.play(); }
		
	public void playStartSound()
	{   if (checkboxSound.getState() == true)
		if (startSound != null) startSound.play(); }
		
	public void playLandedPieceSound()
	{   if (checkboxSound.getState() == true)
		if (landedPieceSound != null) landedPieceSound.play(); }
		
	public void playExplodedLineSound()
	{   if (checkboxSound.getState() == true)
		if (explodedLineSound != null) explodedLineSound.play(); }

	//###### startGame & endGame ###################
	
	public void startGame()
	{
	  buttonStart.enable(false);
	  buttonEnd.enable(true);
	  buttonPause.enable(true);
	  choiceSpeed.enable(false);
	  choiceLevel.enable(false);
	  choiceGame.enable(false);
	  table.requestFocus();
	  playStartSound();
	  eraseMatrix();	  	  
	  setScorePoints(0);
	  setScoreRows(0);
	  writeScore();
	  setGameIsActive(true);
	  start();
	}
	
	public void endGame()
	{
	  stop();
	  setGameIsActive(false);
	  playEndSound();
	  buttonStart.enable(true);
	  buttonEnd.enable(false);
	  buttonPause.enable(false);
	  buttonResume.enable(false);
	  choiceSpeed.enable(true);
	  choiceLevel.enable(true);
	  choiceGame.enable(true);
	  buttonStart.requestFocus();
	  printEND();
	  drawTable();
	}
	
	//##### drawing methods ############
	
	public void drawPreview()
	{ preview.repaint(); }
	
	public void drawTable()
	{ table.repaint(); }
	
	public void writeScore()
	{
		 labelScore.setText(java.lang.Integer.toString(getScorePoints()));
		 labelLines.setText(java.lang.Integer.toString(getScoreRows()));
	}	
	
	//##### color methods ###########################
		
	//set the colors and give the color to the user-interface
	private void manageColors()
	{		
		buttonStart.setBackground(buttonColor);
		buttonPause.setBackground(buttonColor);
		buttonResume.setBackground(buttonColor);
		buttonEnd.setBackground(buttonColor);
		
		choiceLevel.setBackground(buttonColor);
		choiceSpeed.setBackground(buttonColor);
		choiceGame.setBackground(buttonColor);
		
		panelW.setBackground(panelColor);
		panelE.setBackground(panelColor);
		preview.setBackground(panelColor);
							
		labelScore.setForeground(fontColor);
		labelLines.setForeground(fontColor);
		labelRows.setForeground(fontColor); 
		labelPoints.setForeground(fontColor);
		labelLevel.setForeground(fontColor); 
		labelSpeed.setForeground(fontColor); 
		labelGame.setForeground(fontColor); 
			
		buttonStart.setForeground(fontColor);
		buttonPause.setForeground(fontColor);
		buttonResume.setForeground(fontColor);
		buttonEnd.setForeground(fontColor);
			
		choiceSpeed.setForeground(fontColor);
		choiceLevel.setForeground(fontColor);
		choiceGame.setForeground(fontColor);
			
		checkboxSound.setForeground(fontColor);		
	}	
	
	private void getAllColors()
	{
		buttonColor = getColor("buttonColorRGB");
		panelColor = getColor("panelColorRGB");
		fontColor = getColor("fontColorRGB");
	}
		
	//get the color from the parameter
	private Color getColor(String RGBparameter) 
	{
		StringTokenizer st;
		String token;
		boolean flag=false;
		Color color = Color.black; 
		
		String colorRGB = getParameter(RGBparameter);
		
		if (colorRGB!=null)
		{
		 int red=0, green=0, blue=0;
		 flag=false;
		 st = new StringTokenizer(colorRGB, ",");
		 
		 token = st.nextToken();
		 if (token!=null && token.length()>0)	
		 	{try {red = Integer.parseInt(token); } 
				catch(NumberFormatException e) 
				{ flag = true; }  
			 if (red>255) red=255;
			 if (red<0) red=0;} 
		
		if (st.hasMoreElements())
		{ 
		token = st.nextToken();
		if (token!=null && token.length()>0)	
			{try {green = Integer.parseInt(token); } 
				catch(NumberFormatException e) 
				{ flag = true; } 
			 if (green>255) green=255;
			 if (green<0) green=0;} 
		}
		
		if (st.hasMoreElements())
		{
		token = st.nextToken();
		if (token!=null && token.length()>0)	
			{try {blue = Integer.parseInt(token); } 
				catch(NumberFormatException e) 
				{ flag = true; } 
			 if (blue>255) blue=255;
			 if (blue<0) blue=0;}
		} 
		
		color = new Color(red, green, blue);
		}
		else 
		{	System.out.println ("parametr \""+RGBparameter+"\" for the color doesn't exist, using default...");
			if (RGBparameter.equals("buttonColorRGB")) color = new Color(110,250,110);
			if (RGBparameter.equals("panelColorRGB")) color = new Color(154,209,183);
			if (RGBparameter.equals("fontColorRGB")) color = new Color(0,100,100);
		}
		
		if (flag) 
		{	System.out.println("error in parameter \""+RGBparameter+"\" (isn't it three numbers divised by commas?) using default...");
			if (RGBparameter.equals("buttonColorRGB")) color = new Color(110,250,110);
			if (RGBparameter.equals("panelColorRGB")) color = new Color(154,209,183);
			if (RGBparameter.equals("fontColorRGB")) color = new Color(0,100,100);
		}
		
		return (color);
	}
}  

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ TABLE: THE GAME CANVAS CLASS @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

/*
 * The Table class: it is a canvas that use double buffering.
 * Graphics is totally changeable: you can change the background image, the
 * font, button and panel colors, the dimensions and the piece image (someone 
 * could put stars or circles as pieces image, all you need is every brick is
 * 20x20 pixel and every piece must be put in a line without space each other,
 * this dimension is fixed in order to avoid a large number of applet
 * parameters, and functions to control their values and to control it exists
 * both the piecesName parameter and the dimension parameter and so on).
 * Using the offset variables the table will be put in the center of the applet.
 *
 */
 


class Table extends Canvas
{
	private Base base;
	private MediaTracker tracker;
	private Image offscreenImage;
	private Graphics offscreenGraphics;		//double buffering
	private Image back;						//background
	private int dwidth;						//dimension: width
	private int dheight;					//dimension: height
//	private int offsetX;					//offset from the left
//	private int offsetY;					//offset from the top

	private  Image pieces[] = new Image[6]; //pieces images array 
	private int widthBrick;	
	
	
	public Table(Base b)
	{ 
	  base=b;
	  setBackground(base.getPanelColor());
	  
	  //###### dimenion control ##############
	  
	  int pwidth;
	  int pheight;
	  
	  pwidth = base.getWidth()-128; //128 is about the panels width
	  pheight = base.getHeight();
	  
	  //find out the best dimensions
	  if (pwidth<=pheight && pwidth*2<=pheight) 
	  	   {dheight=pwidth*2;
	  	    dwidth=pwidth;}
	  else {dwidth=pheight/2;
	  		dheight=pheight;}
	  
	  widthBrick = dwidth/10;
	  
	  dwidth=widthBrick*10;
	  dheight=widthBrick*20;
	  
//	  offsetX=(pwidth-dwidth)/2;
//	  offsetY=(pheight-dheight)/2;
	  	  
	  //###### produce background #################
	  tracker = new MediaTracker(this);
	  boolean flag=false;
	  String backgroundName = base.getParameter("backgroundName");
	  Image iback = null; //temporany background image storage
	  
	  if (backgroundName!=null)
	  {
	    iback =  base.getImage(base.getCodeBase(), backgroundName);
	    tracker.addImage(iback,0);
	    try {tracker.waitForAll();} 
	    catch (InterruptedException e)
	    	{System.out.println("MediaTraker error in "+backgroundName+" image: "+e.getMessage()); flag=true;}
	  	if (tracker.isErrorAny()) {System.out.println("loading image "+ backgroundName+ 
	  							   " error (image name error or image not found), using default background..."); 
	  							   flag=true;}
	  }
	  //in case of background image error the game produce a default background
	  if (backgroundName==null || flag) 
	  { 
	    if (backgroundName==null) System.out.println ("parameter \"backgroundName\" doesn't exist, using default background...");
	    iback = base.createImage(dwidth, dheight); 
		Graphics g = iback.getGraphics();
		g.setColor(base.getPanelColor());
		g.fillRect(0,0,dwidth, dheight);
	  }
  	
 	   //set the back image 
  	   back = base.createImage(dwidth,dheight);
  	   Graphics offback = back.getGraphics();
  	   offback.setColor(base.getButtonColor());
  	   //background image
  	   offback.drawImage(iback, 0, 0, dwidth, dheight, this); 
  	   //picture-frame
	   offback.drawRect(0,0,dwidth, dheight);
	   offback.drawRect(1,1,dwidth-2, dheight-2);
	   offback.drawRect(4,4,dwidth-8, dheight-8);
	   offback.drawRect(5,5,dwidth-10, dheight-10);
	   offback.drawRect(6,6,dwidth-12, dheight-12);
  		
  	  //##### produce pieces ###################
  	  
  	  tracker = new MediaTracker(this);
  	  flag=false;
  	  Image tmp = null;
  	  String piecesName = base.getParameter("piecesName");
  	  
  	  if (piecesName!=null) 
  	  {
  	  	tmp = base.getImage(base.getCodeBase(), piecesName);
  	  	tracker.addImage(tmp, 0);
      	 	try {tracker.waitForAll();} 
      	  	catch (InterruptedException e) 
      		{System.out.println("MediaTraker error in "+piecesName+" image: "+e.getMessage());}
	  	if (tracker.isErrorAny()) 
	  		{System.out.println("loading image "+piecesName+
	  		"error (image name error or image not found), using default pieces......"); 
	  		flag=true;}
	  }
      
      	  //in case of pieces image error the game produce default pieces
      	  if (piecesName==null || flag) 
    	  {
    	   if (piecesName==null) System.out.println ("parameter \"piecesName\" doesn't exist, using default background......");
		 tmp = base.createImage(120, 20);
		 Graphics g = tmp.getGraphics();
		 g.setColor(Color.green);
		 g.fill3DRect(0,0,20,20,true);
    	   	 g.setColor(Color.red);
		 g.fill3DRect(20,0,20,20,true);
  		 g.setColor(Color.cyan);
		 g.fill3DRect(40,0,20,20,true);
  		 g.setColor(Color.orange);
		 g.fill3DRect(60,0,20,20,true);
  	     	 g.setColor(Color.blue);
		 g.fill3DRect(80,0,20,20,true);
		 g.setColor(Color.yellow);
		 g.fill3DRect(100,0,20,20,true);
		}
	
	  //pieces image divison (to put in an array)	 
  	  ImageFilter filter;
  	  ImageProducer producer;
  	  
  	  filter = new CropImageFilter(0,0,20,20);
	  producer = new FilteredImageSource(tmp.getSource(), filter);
  	  pieces[0] = createImage(producer); 
  	 
  	  filter = new CropImageFilter(20,0,20,20);
	  producer = new FilteredImageSource(tmp.getSource(), filter);
  	  pieces[1] = createImage(producer);
  
  	  filter = new CropImageFilter(40,0,20,20);
	  producer = new FilteredImageSource(tmp.getSource(), filter);
  	  pieces[2] = createImage(producer);
  	
  	  filter = new CropImageFilter(60,0,20,20);
      	  producer = new FilteredImageSource(tmp.getSource(), filter);
  	  pieces[3] = createImage(producer);
  	
  	  filter = new CropImageFilter(80,0,20,20);
	  producer = new FilteredImageSource(tmp.getSource(), filter);
  	  pieces[4] = createImage(producer);
  	
      	  filter = new CropImageFilter(100,0,20,20);
	  producer = new FilteredImageSource(tmp.getSource(), filter);
  	  pieces[5] = createImage(producer);
  	
  	  //##### double buffering ###################
  	  offscreenImage = base.createImage(dwidth, dheight);
	  offscreenGraphics = offscreenImage.getGraphics();
	  offscreenGraphics.setColor(base.getPanelColor());	  	
    	} 
	
	public void paint(Graphics g) 
	{		
		offPaint();	
	    g.drawImage(offscreenImage,0,0,/*offsetX, offsetY,*/ this);
	}
				
	public void update(Graphics g)
	{ paint(g); }
	
	private void offPaint()
	{
		int[][] mat = base.getMat();
		
		//background
		offscreenGraphics.drawImage(back,0,0, dwidth, dheight, this);
		
		//pieces	 									
		for (int i=0; i<base.getRows(); i++)
			for (int j=0; j<base.getColumns(); j++)
				{	switch (mat[i][j]) 
						{	
							case 101: 	
							case 1: 	offscreenGraphics.drawImage(pieces[0],
										j*widthBrick, i*widthBrick,
										widthBrick,
										widthBrick,
										this);
							break;	
							case 102:	
							case 2: 	offscreenGraphics.drawImage(pieces[1],
										j*widthBrick,
										i*widthBrick,
										widthBrick,widthBrick, 
										this);
							break;	
							case 103:	
							case 3:	    offscreenGraphics.drawImage(pieces[2],
										j*widthBrick, 
										i*widthBrick,
										widthBrick,
										widthBrick, 
										this);
							break;	
							case 104: 	
							case 4: 	offscreenGraphics.drawImage(pieces[3],
										j*widthBrick,
										i*widthBrick,
										widthBrick,
										widthBrick, 
										this);
							break;	
							case 105:
							case 5:  	offscreenGraphics.drawImage(pieces[4],
										j*widthBrick, 
										i*widthBrick, 
										widthBrick,
										widthBrick, 
										this);
	 						break;
	 						case 106:
	 						case 6: 	offscreenGraphics.drawImage(pieces[5],
	 									j*widthBrick,
	 									i*widthBrick, 
	 									widthBrick,
	 									widthBrick, 
	 									this);
	 						break;
						}				
				} 
	} 
	
	public Image[] getPieces()
	{ return pieces; }	
}

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ PREVIEW: THE PIECE PREVIEW CANVAS CLASS @@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 
 // The preview class: it is a canvas that draw next piece.
 // It can draw just pieces and it i made up of a 4x4 matrix.

class Preview extends Canvas
{
	private Base base;
	private Image offscreenImage;
	private Graphics offscreenGraphics;		//double buffering
	private Image[] pieces = new Image [6];
	
	private int widthBrickpreview = 0;
	private int offsetX=0;
	private int offsetY=0;
	private boolean dimensioned = false;
	
	
	public Preview(Base b)
	{ 
	 base=b;
	 setBackground(base.getPanelColor()); 
	 
	 offscreenImage = base.createImage(100,100);
	 //anyway the image will be dimensioned like the canvas width
	 offscreenGraphics = offscreenImage.getGraphics();
	 
	 pieces = base.getPieces();	 
	}
	
	private void offPaint() 
	{	
	 int [][] mat = new int [4][4];	
	 int n= base.getPiecePreview();
	 int c= base.getPieceColorPreview();	 									
		
	 switch (n) 
		{	
			case 0: mat[0][0]=mat[0][1]=mat[0][2]=mat[0][3]=0;
					mat[1][0]=mat[1][1]=mat[1][2]=mat[1][3]=0;
					mat[2][0]=mat[2][1]=mat[2][2]=mat[2][3]=0;
					mat[3][0]=mat[3][1]=mat[3][2]=mat[3][3]=0; //nothing
			break;					
			case 1: mat[1][1]=mat[1][2]=mat[2][1]=mat[2][2]=c; //S
			break;
			case 2: mat[1][1]=mat[2][0]=mat[2][1]=mat[2][2]=c; //T
			break;
			case 3: mat[0][1]=mat[1][1]=mat[2][1]=mat[2][2]=c; //Lr
			break;
			case 4: mat[1][1]=mat[1][0]=mat[1][2]=mat[1][3]=c; //S
			break;
			case 5: mat[1][0]=mat[1][1]=mat[2][1]=mat[2][2]=c; //Zr
			break;
			case 6: mat[0][2]=mat[1][2]=mat[2][2]=mat[2][1]=c; //Ll
			break;
			case 7: mat[1][2]=mat[1][1]=mat[2][1]=mat[2][0]=c; //Zl
			break;
			case 8: mat[0][1]=mat[0][0]=mat[1][1]=mat[2][1]=mat[2][2]=c; //2
			break;				
			case 9: mat[0][1]=mat[0][2]=mat[1][1]=mat[2][1]=mat[2][0]=c; //S2
			break;				
			case 10: mat[1][1]=mat[2][1]=mat[2][2]=c; //Llr
			break;				
			case 11: mat[1][2]=mat[2][1]=mat[2][2]=c; //Lls
			break;				
			case 12: mat[1][1]=mat[2][1]=c; //Ss
			break;				
			case 13: mat[1][1]=c; //Ls
			break;				
			case 14: mat[0][1]=mat[1][1]=mat[2][1]=mat[2][0]=mat[2][1]=mat[2][2]=c; //Tb
			break;
			case 15: mat[1][2]=mat[0][1]=mat[1][1]=mat[1][0]=mat[2][1]=c; //X
			break;										
		}				
	 
	 if (!dimensioned)
	 {
	 if ( (int) (this.size().height/3) < (int) (this.size().width/4) )
	 		{ widthBrickpreview = (int) (this.size().height/3);
	 		  offsetX = (this.size().width-widthBrickpreview*4)/2; }
	 	else
	 		{widthBrickpreview = (int) (this.size().width/4);
	 		 offsetY = (this.size().height-widthBrickpreview*3)/2; }
	 
	 dimensioned = true;
	 }
	 
	 offscreenGraphics.setColor(base.getPanelColor());	
	 offscreenGraphics.fill3DRect(0,0,this.size().width,this.size().height,true);
	 	 	
	 for (int i=0; i<4; i++)
		for (int j=0; j<4; j++)		  
			  switch (mat[i][j]) 
			  	{
			  	 case 1:	offscreenGraphics.drawImage(pieces[0],
			  								j*widthBrickpreview+offsetX,
			  								i*widthBrickpreview+offsetY,
			  								widthBrickpreview,
			  								widthBrickpreview,
			  								this);
			  	 break;
			  	 case 2:	offscreenGraphics.drawImage(pieces[1],
			  								j*widthBrickpreview+offsetX,
			  								i*widthBrickpreview+offsetY,
			  								widthBrickpreview,
			  								widthBrickpreview,
			  								this);
			  	 break;
			  	 case 3:	offscreenGraphics.drawImage(pieces[2],
			  								j*widthBrickpreview+offsetX,
			  								i*widthBrickpreview+offsetY,
			  								widthBrickpreview,
			  								widthBrickpreview,
			  								this);
			  	 break;
			  	 case 4:	offscreenGraphics.drawImage(pieces[3],
			  								j*widthBrickpreview+offsetX,
			  								i*widthBrickpreview+offsetY,
			  								widthBrickpreview,
			  								widthBrickpreview,
			  								this);
			  	 break;
			  	 case 5:	offscreenGraphics.drawImage(pieces[4],
			  								j*widthBrickpreview+offsetX,
			  								i*widthBrickpreview+offsetY,
			  								widthBrickpreview,
			  								widthBrickpreview,
			  								this);
			  	 break;
			  	 case 6:	offscreenGraphics.drawImage(pieces[5],
			  								j*widthBrickpreview+offsetX,
			  								i*widthBrickpreview+offsetY,
			  								widthBrickpreview,
			  								widthBrickpreview,
			  								this);
			  	 break;
			  	}						 
	}
		
	public void paint (Graphics g)
	{
		offPaint();	
		g.drawImage(offscreenImage,0,0,this);
	}			
	
	public void update(Graphics g)
	{ paint(g); }	
	
}

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ TETRIS: THE GAME RULES @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

/* The Tetris class: here there are every method belong the tetris game rules.
 * 
 * I made it just in a class but probably it will have be better to build in 
 * more different classes, for istance one for the pieces and one for the pieces 
 * movements and rotation.
 *
 * The class work in this way: a matrix 20x10 represent the game area and the
 * pieces values represent the color and the pieces state (active pieces, that
 * are moveing, have a value of 100 plus the color value).
 *
 */

class Tetris extends Game
{	
	//piece shape
	protected int piece = 0;
	
	//central co-ordinates of the piece in the game matrix
	protected int xP=-1;
	protected int yP=-1;
	
	//next piece
	protected int piecePreview;
	protected int pieceColorPreview;
	
	public Tetris(Base b)
	{ super(b); }

	//##### speed and level management ##################
	
	// these methods aren't implemented in the Base class so different 
	// game rules can have different management (for istance a snake game is 
	// far more fast than a tetris game).
	
	protected void manageSpeed()
	{	
		switch (base.getSpeedSelectedIndex())
			{
				case 0: base.setTimer(400); break;
				case 1: base.setTimer(370); break;
				case 2: base.setTimer(340); break;
				case 3: base.setTimer(290); break;
				case 4: base.setTimer(240); break;
				case 5: base.setTimer(190); break;
				case 6: base.setTimer(150); break;
				case 7: base.setTimer(130); break;
				case 8: base.setTimer(110); break;
				case 9: base.setTimer(80);  break;
			}
	}
	
	protected void manageLevel()
	{	
	  int n= (base.getRows()-(base.getLevelSelectedIndex()+1))+1;
	  int[][] x = new int[base.getRows()][base.getColumns()]; 
	  
	  if (n>0)
	  { for (int i=base.getRows()-1; i>=n; i--)
	  		for (int j=0; j<base.getColumns(); j++)
	  			{	if (Math.random()>0.5)
	  				x[i][j]=(int)(Math.random()*5+1);
	  			}
		
		base.setMat(x);
		checkRow();
	  }
	
	}

	//##### startGame / endGame ##################################
	
	public void startGame()
	{ 
	  base.startGame();
	  
	  //control starting speed and level
	  manageSpeed();
	  manageLevel();
	  
	  //produce the first piece
	  piecePreview();
	  producePiece();
    }

	public void endGame() 
	{  
	  base.endGame();
	}

	//##### fall: the iterator ##################################
	
	//this procedure does work just if there is an active piece (of 100) in 
	//the game matrix
	
	public void fall()	
	{	
	
		boolean flag = true;
		int[][] x = base.getMat();
		
		for (int i=base.getRows()-1; i>=0; i--)
			for (int j=base.getColumns()-1; j>=0; j--)
					 if (x[i][j]>100) 
					 	 if  ((i+1)<base.getRows())
					 	 	if (x[i+1][j]==0) 
								 { x[i+1][j]=x[i][j];
						  	  	   x[i][j]=0; }
					 	    else { flag=false; i=-1; j=-1;}
					 	 else { flag=false; i=-1; j=-1;}
					 						 
		if (!flag) //if the piece touch the bottom or other pieces...
			 {	int[][] n = base.getMat();
			 	//take the active state difference of the piece off 			 				
				for (int i=0; i<base.getRows(); i++) 			 
					for (int j=0; j<base.getColumns(); j++) 
							if (n[i][j]>100) n[i][j]-=100;

				base.setMat(n);
				checkRow();
				base.playLandedPieceSound();
				producePiece();
			 }
		else { yP++; base.setMat(x); } 	//else continue the fall
		
		base.drawTable();
						 
	}

	//##### check the full rows ##########################
	
	protected void checkRow()
	{
		int [][] m = base.getMat();
		int count=0;
		
		for (int i=base.getRows()-1; i>=0; i--)
			for (int j=0; j<base.getColumns(); j++)
				{ if (m[i][j]==0 || m[i][j]>100) break;
				  if (j==base.getColumns()-1) 
				  	{
				  	 destroyRow(i); 
				  	 
				  	 base.setScorePoints(base.getScorePoints()+100);
				  	 base.setScoreRows(base.getScoreRows()+1);
				  	 base.writeScore();
				  	 
				  	 //check if increment speed
				  	 if (base.getSpeedSelectedIndex()<10)
				  	 	if (base.getScorePoints()/10000/(base.getSpeedSelectedIndex()+1)>=1)
				  	 		{ base.setSpeedSelectedIndex(base.getSpeedSelectedIndex()+1);
				  	 	  	  manageSpeed();
				  	 		}  
				  	 
				  	 //prepare next check... 
				 	 m = base.getMat();
				  	 i++;
				  	 count++;
				  	 }
				}
		
		if (count>1)
		{
			base.setScorePoints(base.getScorePoints()+100*count);
			base.writeScore();
		}
		
		if (count>0) base.playExplodedLineSound();
	}
	
	protected void destroyRow(int row)
	{
		int [][] m = base.getMat();
		
		for (int i=row-1; i>=0; i--)
			for (int j=0; j<base.getColumns(); j++)
				m[i+1][j]=m[i][j];
		
		base.setMat(m);
	}
						
	//##### produce pieces #########################################
	
	protected void piecePreview()
	{ piecePreview=(int)(Math.random()*7+1);
	  base.drawPreview();
	  pieceColorPreview=(int)(Math.random()*5+1); }
	
	protected void producePiece()
	{
		piece = piecePreview;
		int p = piece;
		if (p==1) produceQ();
		if (p==2) produceT();
		if (p==3) produceLr();
		if (p==4) produceS();
		if (p==5) produceZr();
		if (p==6) produceLl();
		if (p==7) produceZl();
		
		/*
		//random rotation at the beginning
		int x=(int)(Math.random()*3);
		for (int i=0; i<x; i++) rotation();
		*/
		
		base.drawPreview();	
		
		//prepare next piece
		piecePreview();
	}
	
	protected boolean produceQ() //square [][]
	                             //       [][]
	{	
	    boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[0][5]!=0 || m[1][4]!=0 || m[1][5]!=0) flag = false;
		m[0][4]=m[0][5]=m[1][4]=m[1][5]=pieceColorPreview+100;
		base.setMat(m);  xP=4; yP=0; piece=1;	
		if (!flag) endGame();
		return (flag);
	}
		
	protected boolean produceT() //T shape  []
	                             //       [][][]
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][3]!=0 || m[1][4]!=0 || m[1][5]!=0) flag = false;	
		m[0][4]=m[1][3]=m[1][4]=m[1][5]=pieceColorPreview+100;
		base.setMat(m); xP=4; yP=1; piece=2;	
		if (!flag) endGame();
		return (flag);
	}	
	
	protected boolean produceLr() //L shape with right brick []
	                              //                         []
	                              //                         [][]
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[2][4]!=0 || m[2][5]!=0) flag = false;
		m[0][4]=m[1][4]=m[2][4]=m[2][5]=pieceColorPreview+100;
		base.setMat(m); xP=4; yP=1; piece=3;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean produceLl() //L shape with left brick []
	                              //                        []
	                              //                      [][]
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[2][4]!=0 || m[2][3]!=0) flag = false;
	    m[0][4]=m[1][4]=m[2][4]=m[2][3]=pieceColorPreview+100;
	    base.setMat(m); xP=4; yP=1; piece=6;	
		if (!flag) endGame();
		return (flag);
	}
		
	protected boolean produceS() //segment (long thin piece) [][][][]
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[2][4]!=0 || m[3][4]!=0) flag = false;
		m[0][4]=m[1][4]=m[2][4]=m[3][4]=pieceColorPreview+100;
		base.setMat(m); xP=4; yP=2; piece=4;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean produceZr() //Z shape with right bottom brick [][]
	                              //                                  [][]
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][3]!=0 || m[0][4]!=0 || m[1][4]!=0 || m[1][5]!=0) flag = false;	
		m[0][3]=m[0][4]=m[1][4]=m[1][5]=pieceColorPreview+100;
		base.setMat(m); xP=4; yP=1; piece=5;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean produceZl() //Z shape with left bottom brick [][]
	                              //                             [][]
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[1][3]!=0 || m[0][4]!=0 || m[1][4]!=0 || m[0][5]!=0) flag = false;		
		m[1][3]=m[0][4]=m[1][4]=m[0][5]=pieceColorPreview+100;
		base.setMat(m); xP=4; yP=1; piece=7;	
		if (!flag) endGame();
		return (flag);
	}
	
	//##### piece movement ######################################
	
	public void manageKeyboard(int t)
	{         	
		switch (t) 
		{	
		case 1006:	left();		//left arrow
		break;		
		case 1004:	rotation();	//up arrow
		break;
		case 1007:	right();	//right arrow
		break;	
		case 1005:	fall();		//down arrow
		}
	}
	
	/* movement methods return a boolean value that point if there was a real
	 * movement (there may be few space and the piece may not move) 
	 */
	 
	protected boolean left()
	{
		boolean flag = true;
		boolean flag2 = false;
		int[][] x = base.getMat();
		
		for (int i=0; i<base.getRows(); i++)
			for (int j=1; j<base.getColumns(); j++)
					 if (x[i][j]>100)
					 	{ if  (x[i][j-1]==0) 
							   { flag2=true;
							     x[i][j-1]=x[i][j];
						  	     x[i][j]=0; }
					 	  else
					 	  { flag=false; i=base.getRows(); j=base.getColumns();}
					 	}		
		
		if (flag && flag2) 
		{ base.setMat(x); if(xP>0)xP--; base.drawTable(); }
		
		return (flag && flag2);					 
	}
	
	protected boolean right()
	{
		boolean flag = true;
		boolean flag2 = false;
		
		int[][] x = base.getMat();
		
		for (int i=0; i<base.getRows(); i++)
			for (int j=base.getColumns()-2; j>=0; j--)
					 if (x[i][j]>100)
					 	{ if (x[i][j+1]==0) 
							 { flag2=true;
							   x[i][j+1]=x[i][j];
						  	   x[i][j]=0; }
					 	 else{ flag=false; i=base.getRows(); j=-1;}
					 	}		
		if (flag && flag2) { base.setMat(x); 
							 if( xP<base.getColumns() ) xP++;
							 base.drawTable(); }	
		
		return (flag && flag2);				 
	}
	
	/*
	//used in rotation correction (now not used)
	protected boolean up()
	{
		int[][] m = base.getMat();
	
		boolean flag = true;
		boolean flag2 = false;
		
		for(int i=0; i<base.getRows(); i++)
			for (int j=0; j<base.getColumns(); j++)
					if (m[i][j]>100)
						if (i-1>=0)
							if (m[i-1][j]==0)
						  	{	flag2 = true;
						  		m[i-1][j]=m[i][j];
						  	    m[i][j]=0; }
						    else flag = false;
						else flag = false;			
		
		if (flag && flag2) {base.setMat(m); yP-=1; }
		
		return (flag && flag2);
						  
	}
	*/
	
	//##### rotation ############################################
	
	protected void rotation() //specific rotation for every piece
	{
		int[][] m = base.getMat();
		
		if (piece!=1 && piece!=15) if (piece==4) rotate(4); else rotate(3);	
		base.drawTable();	
	}	
	
	/* rotate method can rotate any piece with any dimension or shape, the parametr
	 * "side" is the width of the square circumscribeing the piece, it returns
	 * a boolean value that point if there was a real rotation (there may be 
	 * few space and the piece may not rotate)
	 */
	protected boolean rotate(int side)
	{
		int l=(int)(side/2);
		int[][] m = base.getMat();
		int[][] x = base.getMat();
		
		boolean ok=true;
	
		//here it erases the piece from the next matrix, after the rotation starts
		for (int i=-l; i<=l; i++) 
			for (int j=-l; j<=l; j++)
			{	if (yP+i>=base.getRows() || yP+i<0 || xP+j>=base.getColumns() || xP+j<0) continue;
				if (x[yP+i][xP+j]>100) x[yP+i][xP+j]=0;
			}
				
		 for (int i=-l; i<=l; i++) //rotate the piece
			for (int j=-l; j<=l; j++)
			{	if (yP+i>=base.getRows() || yP+i<0 || xP+j>=base.getColumns() || xP+j<0) continue;
				
				if (m[yP+i][xP+j]>100)	
				{	if (yP-j<0  || yP-j>=base.getRows() || xP+i>=base.getColumns() || xP+i<0) {ok=false; break;}
					if (m[yP-j][xP+i]>100 || m[yP-j][xP+i]==0)
					   {x[yP-j][xP+i] = m[yP+i][xP+j]; }
					else ok=false;
				}
			}					
		if (ok) {base.setMat(x); }

		return (ok);
	}
	
	//##### get e set methods #####################################
	
	public int getPiecePreview()
	{ return piecePreview; }
	
	public int getPieceColorPreview()
	{ return pieceColorPreview; }

/*
  rotation (with correction) of old versions. 
  (sometimes, seldom, it produce some bugs).
  (cmq meglio prevenire che curare).
  (anche se ho sprecato un bel po' di tempo per scriverla).
  (perchè è stata la cosa più difficile di tutto quanto).
  (e adesso l'ho tolta).
  (che peccato).
  (uffa).
 
 protected void rotation() 
	{
		int[][] m=base.getMat();
		switch (piece)
		{	
		case 5:	if(rotate(3))	//Zr
				{if(yP-1>=0 && xP-1>=0)
				 if(m[yP-1][xP-1]==0) { yP+=1;  up();}
				}
				else {left(); if(rotate(3))	
								 {if(yP-1>=0 && xP-1>=0)
							      if(m[yP-1][xP-1]==0) { yP+=1;  up();}
								 }
					  right();
					 }
				base.drawTable();
		break;	
		case 7: if(rotate(3))	//Zl
				{if(yP-1>=0 && xP-1>=0)
				if(m[yP-1][xP-1]>100) { yP+=1;  up();}
				}
				else {left(); if(rotate(3))	
								 {if(yP-1>=0 && xP-1>=0)
							      if(m[yP-1][xP-1]>100) { yP+=1;  up();}
								 }
					  right();
					 }
				base.drawTable();
		break;			
		case 2:	if (!rotate(3))	//T
				{ if (right()) {rotate(3); left();}
				  else {left(); rotate(3); right();}
				} 
				base.drawTable();
		break;	
		case 3:	 if(!rotate(3)) //Lr
				    	{if(left()) {rotate(3); right();}
				     		else {right(); rotate(3); left();}
						}
				 if (yP+1<base.getRows() && yP-1>=0 && xP-1>=0 && xP+1<base.getColumns())   
				 if (m[yP][xP-1]<100 && m[yP-1][xP+1]<100 && m[yP-1][xP-1]<100)
				  	 left();
				 
				 if (yP+1<base.getRows() && yP-1>=0 && xP-1>=0)   
				 if (m[yP+1][xP-1]<100 && m[yP+1][xP]<100 && m[yP-1][xP-1]<100)
				  	 right(); 
				 base.drawTable();
		break;
		case 4: if (rotate(4))	//S  
		        {
		        	if(yP-2>=0)
				 	if(m[yP-2][xP]==0) { yP+=1; up(); }
				} 
				else
				{
					up();
					if (rotate(4))	//S  
		        	{ if(yP-2>=0)
					  if(m[yP-2][xP]==0) { yP+=1; up(); } }
					fall();
				} 
				base.drawTable();
		break;
		case 6: if(!rotate(3)) //Ll
					{if(left()) {rotate(3); right();}
				     	else {right(); rotate(3); left();}
					}	
				if (xP+1<base.getColumns() && yP-1>=0 && xP-1>=0)   
				if (m[yP-1][xP-1]<100 && m[yP-1][xP+1]<100 && m[yP][xP+1]<100)
				   left();  
				  
				if (yP-1>=0 && xP-1>=0 && yP+1<base.getRows())   
				if (m[yP][xP-1]<100 && m[yP+1][xP-1]<100 && m[yP-1][xP]<100 && m[yP][xP+1]>100)
				  	right(); 	
				base.drawTable();
		break;
		
		case 8:  rotate(3); base.drawTable(); break; //2
		case 9:  rotate(3); base.drawTable(); break; //S2
		case 10:  //lpd
		case 11:  //Lps
		case 12: if (!rotate(2)) //Sp
					{ if (left()) 
						{rotate(2); right();}
					  else {right(); rotate(2); left();}
					}
				 base.drawTable();
	    break; //Sp
		case 14: rotate(3); base.drawTable(); break; //Tg
		}	
	}
*/
}

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ TetrisSp: STRANGE-PIECES-TETRIS @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

 // TetrisSp class: like Tetris but with some strange... pieces!


class TetrisSp extends Tetris

{
	public TetrisSp(Base b)
	{	super(b);	}

	protected void piecePreview()
	{  
		piecePreview=(int)(Math.random()*15+1);
		base.drawPreview(); 
		pieceColorPreview=(int)(Math.random()*5+1);
	}
	
	protected void producePiece()
	{
		piece = piecePreview;
		int p = piece;
		if (p==1)  produceQ();
		if (p==2)  produceT();
		if (p==3)  produceLr();
		if (p==4)  produceS();
		if (p==5)  produceZr();
		if (p==6)  produceLl();
		if (p==7)  produceZl();
		if (p==8)  produce2();
		if (p==9)  produceS2();
		if (p==10) produceSll();
		if (p==11) produceSlr();
		if (p==12) produceSs();
		if (p==13) produceLs();
		if (p==14) produceTb();
		if (p==15) produceX();				
		
		base.drawTable();	
		
		//prepare next pieces
		piecePreview();
	}
	
	protected boolean produceX() //+ shape []
	                             //      [][][]
	                             //        []
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[1][3]!=0 || m[2][4]!=0 || m[1][5]!=0) flag = false;		
		m[0][4]=m[1][4]=m[1][3]=m[2][4]=m[1][5]=pieceColorPreview+100; 
		base.setMat(m); xP=4; yP=1; piece=15;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean produceTb() //big T shape []
	                              //            []
	                              //          [][][]
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[2][3]!=0 || m[2][4]!=0 || m[2][5]!=0) flag = false;		
		m[0][4]=m[1][4]=m[2][3]=m[2][4]=m[2][5]=pieceColorPreview+100; 
		base.setMat(m); xP=4; yP=1; piece=14;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean produceLs() //little square shape []
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0) flag = false;		
		m[0][4]=pieceColorPreview+100; base.setMat(m); xP=4; yP=0; piece=13;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean produceSs() //small segment shape [][]
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0) flag = false;		
		m[0][4]=m[1][4]=pieceColorPreview+100;
		base.setMat(m); xP=4; yP=0; piece=12;	
		if (!flag) endGame();
		return (flag);
	}
		
	protected boolean produceSlr() //small L shape with right bottom brick []
	                               //                                      [][]
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[1][3]!=0) flag = false;		
		m[0][4]=m[1][4]=m[1][3]=pieceColorPreview+100;
		base.setMat(m); xP=4; yP=1; piece=11;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean produceSll() //small L shape with left bottom brick []
	                               //                                   [][]
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[1][5]!=0) flag = false;		
		m[0][4]=m[1][4]=m[1][5]=pieceColorPreview+100;
		base.setMat(m); xP=4; yP=1; piece=10;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean produceS2()  //specular 2 shape [][]
	                               //                 []
	                               //               [][] 
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[0][5]!=0 || m[2][4]!=0 || m[2][3]!=0) flag = false;		
		m[0][4]=m[1][4]=m[0][5]=m[2][4]=m[2][3]=pieceColorPreview+100;
		 base.setMat(m); xP=4; yP=1; piece=9;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean produce2()	//2 shape  [][]
	                                //           []
	                                //           [][]
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[0][3]!=0 || m[2][4]!=0 || m[2][5]!=0) flag = false;		
		m[0][4]=m[1][4]=m[0][3]=m[2][4]=m[2][5]=pieceColorPreview+100;
		base.setMat(m); xP=4; yP=1; piece=8;	
		if (!flag) endGame();
		return (flag);
	}
}

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ TetrisRow: TETRIS-DESTROY-LAST-ROW @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 
 // rules: same of tetris but the purpose is to destroy the last row
 // (then the level incremets)

class TetrisRow extends Tetris

{
	public TetrisRow(Base b)
	{	super(b);	}

	protected void destroyRow(int row)
	{
		int [][] m = base.getMat();
		
		for (int i=row-1; i>=0; i--)
			for (int j=0; j<base.getColumns(); j++)
				m[i+1][j]=m[i][j];
		
		base.setMat(m);
		base.drawTable();
		base.playExplodedLineSound();
		
		if (row==base.getRows()-1) 
			{
			 base.setScorePoints(base.getScorePoints()+400);
			 base.writeScore();	
			 
			 if (base.getLevelSelectedIndex()<12)
				{
			 	 base.setLevelSelectedIndex(base.getLevelSelectedIndex()+1);
				}
				
			 manageLevel();
			}
	}
	
	protected void manageLevel()
	{	
	  int n= (base.getRows()-(base.getLevelSelectedIndex()+1))+1;
	  int[][] x = new int[base.getRows()][base.getColumns()]; 
	  
	  if (n>0)
	  { for (int i=base.getRows()-1; i>=n; i--)
	  		for (int j=0; j<base.getColumns(); j++)
	  			{	if (Math.random()>0.5)
	  				x[i][j]=(int)(Math.random()*5+1);
	  			}
	  }
	
	  x[base.getRows()-1][(int)(base.getColumns()/2-1)]=6;
	  base.setMat(x);
	  checkRow();	
	}	
}

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ TetrisSpRow: TETRIS-DESTROY-LAST-ROW & STRANGE-PECS @@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

 // rules: same of tetris-strange-piaces but the purpose is to destroy the last
 // row (then the level incremets).
 // Unfortunately java has not multiple hineritance! This class hinerits
 // TestrisSp but has the same body of TetrisRow... 

class TetrisSpRow extends TetrisSp

{
	public TetrisSpRow(Base b)
	{	super(b);	}

	protected void destroyRow(int row)
	{
		int [][] m = base.getMat();
		
		for (int i=row-1; i>=0; i--)
			for (int j=0; j<base.getColumns(); j++)
				m[i+1][j]=m[i][j];
		
		base.setMat(m);
		base.drawTable();	
		base.playExplodedLineSound();
		
		if (row==base.getRows()-1) 
			{
			 base.setScorePoints(base.getScorePoints()+400);
			 base.writeScore();	
			 
			 if (base.getLevelSelectedIndex()<12)
				{
			 	 base.setLevelSelectedIndex(base.getLevelSelectedIndex()+1);
				}
				
			 manageLevel();
			}
	}
	
	protected void manageLevel()
	{	
	  int n= (base.getRows()-(base.getLevelSelectedIndex()+1))+1;
	  int[][] x = new int[base.getRows()][base.getColumns()]; 
	  
	  if (n>0)
	  { for (int i=base.getRows()-1; i>=n; i--)
	  		for (int j=0; j<base.getColumns(); j++)
	  			{	if (Math.random()>0.5)
	  				x[i][j]=(int)(Math.random()*5+1);
	  			}
	  }
	
	  x[base.getRows()-1][(int)(base.getColumns()/2-1)]=6;
	  base.setMat(x);
	  checkRow();	
	}	
}

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ SANKE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

 // There is a hungry snake and some yellow apples, you must feed it. But be
 // careful: the snake may grows up a lot and may not to stay longer in his lair!
 
class Snake extends Game
 {
 	
 	public int level;
 	private Vector body;
	public int direction;
	public int xApple;
	public int yApple; 
	
	private Vector keyboard;		
 	
 		
 	public Snake(Base b)
	{
		super(b);
		
		base = b;
		
		body = new Vector();
		keyboard = new Vector();
		
		body.add( new Bone(9,7) );
		body.add( new Bone(8,7) );	
		body.add( new Bone(7,7) );	
		
		base.drawPreview();
			
		direction = 6;		
	}
				
		protected class Bone
		{
			public int x;
			public int y;
			public int color;
				
			public Bone(int y, int x)
				{
		   			this.x = x;
		   			this.y = y;
		   			this.color = 102; 
				}	
		}
		
		
	public void fall()
	{
		if (keyboard.isEmpty()!=true)
		{	
			direction = ((Integer) keyboard.firstElement()).intValue();
			keyboard.remove(0);
		}
		
		switch (direction)
			{
				case 12: up();			break;
				case  3: right();		break;
				case  6: down();		break;
				case  9: left();		break;
			}
			
		if (base.getGameIsActive()) draw();
		
			
	}

	protected void draw()
	{  
		drawLevel();
		drawSnake();
		drawApple();
		
		base.drawTable();
	}

	protected void drawSnake()
	{
			
		int[][] mat = base.getMat();
		
		Bone b;
		
		for(int i=0; i<body.size(); i++)
			{ b = (Bone) body.get(i);
			  mat[b.y][b.x] = 102;
			} 
		
		base.setMat(mat);			
	}		
			
	protected void down()
	{
		int[][] mat = base.getMat();
			
		Bone b = (Bone) body.firstElement();
			
		if (b.y+1<20)
			{
				switch (mat[b.y+1][b.x])
					{ 
						case 102: endGame();	
						break;
						case   0: body.add(0, new Bone(b.y+1, b.x ) );
								  body.remove( body.lastElement() );
						break;
						case 106: body.add(0, new Bone(b.y+1, b.x ) );
								  base.setScorePoints(base.getScorePoints()+100);
				  	 			  base.setScoreRows(base.getScoreRows()+1);
				  	 		      base.writeScore();
				  	 		      base.playLandedPieceSound();
								  createApple();
						break;
						default:  endGame();
						break;
					} 
			}			
		else endGame(); 
		
	}
	
	protected void up()
	{
		int[][] mat = base.getMat();
			
		Bone b = (Bone) body.firstElement();
			
		if (b.y-1>=0)
			{
				switch (mat[b.y-1][b.x])
					{ 
						case 102: endGame();	
						break;
						case   0: body.add(0, new Bone(b.y-1, b.x ) );
								  body.remove( body.lastElement() );
						break;
						case 106: body.add(0, new Bone(b.y-1, b.x ) );
								  base.setScorePoints(base.getScorePoints()+100);
				  	 			  base.setScoreRows(base.getScoreRows()+1);
				  	 		      base.writeScore();
				  	 		      base.playLandedPieceSound();
								  createApple();
						break;
						default:  endGame();
						break;
					} 
			}			
		else endGame(); 
		
	}

	protected void right()
	{
		int[][] mat = base.getMat();
			
		Bone b = (Bone) body.firstElement();
			
		if (b.x+1<10)
			{
				switch (mat[b.y][b.x+1])
					{ 
						case 102: endGame();	
						break;
						case   0: body.add(0, new Bone(b.y, b.x+1 ) );
								  body.remove( body.lastElement() );
						break;
						case 106: body.add(0, new Bone(b.y, b.x+1 ) );
								  base.setScorePoints(base.getScorePoints()+100);
				  	 			  base.setScoreRows(base.getScoreRows()+1);
				  	 		      base.writeScore();
				  	 		      base.playLandedPieceSound();
								  createApple();
						break;
						default:  endGame();
						break;
					} 
			}			
		else endGame(); 
		
	}

	protected void left()
	{
		int[][] mat = base.getMat();
			
		Bone b = (Bone) body.firstElement();
			
		if (b.x-1>=0)
			{
				switch (mat[b.y][b.x-1])
					{ 
						case 102: endGame();	
						break;
						case   0: body.add(0, new Bone(b.y, b.x-1 ) );
								  body.remove( body.lastElement() );
						break;
						case 106: body.add(0, new Bone(b.y, b.x-1 ) );
								  base.setScorePoints(base.getScorePoints()+100);
				  	 			  base.setScoreRows(base.getScoreRows()+1);
				  	 		      base.writeScore();
				  	 		      base.playLandedPieceSound();
								  createApple();
						break;
						default:  endGame();
						break;
					} 
			}			
		else endGame(); 
		
	}
	
	public void manageKeyboard(int t)
	{         	
		switch (t) 
		{	
		case 1006:	if (keyboard.isEmpty()!=true)
						{ 
							if (((Integer) keyboard.firstElement()).intValue()!=9 && 
		                        ((Integer) keyboard.firstElement()).intValue()!=3) 
							{ keyboard.add(new Integer(9)); }
						}
					else 	if (direction!=9 && direction!=3) 
								{ keyboard.add(new Integer(9)); }		//left arrow
		break;		
		case 1004:	if (keyboard.isEmpty()!=true)
						{ 
							if (((Integer) keyboard.firstElement()).intValue()!=12 && 
		                        ((Integer) keyboard.firstElement()).intValue()!=6) 
							{ keyboard.add(new Integer(12)); }
						}
					else 	if (direction!=12 && direction!=6) 
								{ keyboard.add(new Integer(12)); }		//up arrow
		break;
		case 1007:	if (keyboard.isEmpty()!=true)
						{ 
							if (((Integer) keyboard.firstElement()).intValue()!=3 && 
		                        ((Integer) keyboard.firstElement()).intValue()!=9) 
							{ keyboard.add(new Integer(3)); }
						}
					else 	if (direction!=3 && direction!=9) 
								{ keyboard.add(new Integer(3)); }		//right arrow
		break;	
		case 1005:	if (keyboard.isEmpty()!=true)
						{ 
							if (((Integer) keyboard.firstElement()).intValue()!=6 && 
		                        ((Integer) keyboard.firstElement()).intValue()!=12) 
							{ keyboard.add(new Integer(6)); }
						}
					else 	if (direction!=6 && direction!=12) 
								{ keyboard.add(new Integer(6)); }		//down arrow
		}
	}

	public void startGame()
	{ 
	  base.startGame();
	  
	  //control starting speed and level
	  manageSpeed();
	  manageLevel();
	  
	  drawLevel();
	  drawSnake();
	  createApple();
	  drawApple();
    }

	public void endGame() 
	{  
	  base.endGame();
	}


	protected void drawLevel()
	{	
	  switch (level)
		{
		default:
		case 1: 
				int[][] m = new int[20][10];
				base.setMat(m);
				break;
				
		case 2:
				int[][] m1 = {
					 
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 }
					
					   };
	
				base.setMat(m1);
		break;
		case 3:
				int[][] m2 = {
					 
					 { 0,  0,  0,  3,  3,  3,  3,  0,  0,  0 },
					 { 0,  0,  0,  3,  3,  3,  3,  0,  0,  0 },
					 { 0,  0,  0,  0,  3,  3,  0,  0,  0,  0 },
					 { 0,  0,  0,  0,  3,  3,  0,  0,  0,  0 },
					 { 3,  3,  0,  0,  3,  3,  0,  0,  3,  3 },
					 { 3,  3,  0,  0,  3,  3,  0,  0,  3,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  3,  0,  0,  3,  3,  0,  0,  3,  3 },
					 { 3,  3,  0,  0,  3,  3,  0,  0,  3,  3 },
					 { 0,  0,  0,  0,  3,  3,  0,  0,  0,  0 },
					 { 0,  0,  0,  0,  3,  3,  0,  0,  0,  0 },
					 { 0,  0,  0,  3,  3,  3,  3,  0,  0,  0 },
					 { 0,  0,  0,  3,  3,  3,  3,  0,  0,  0 }
					
					   };
	
				base.setMat(m2);
		break;
		case 4:
				int[][] m3 = {
					 
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  0,  0,  0,  0,  0,  0,  3,  3 },
					 { 3,  3,  0,  0,  0,  0,  0,  0,  3,  3 },
					 { 3,  0,  0,  0,  3,  3,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  3,  3,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  3,  3,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  3,  3,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  3,  3,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  3,  3,  0,  0,  0,  3 },
					 { 3,  3,  0,  0,  0,  0,  0,  0,  3,  3 },
					 { 3,  3,  0,  0,  0,  0,  0,  0,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 }
					
					   };
	
				base.setMat(m3);
		break;
		case 5:
				int[][] m4 = {
					 
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 0,  0,  0,  3,  3,  3,  3,  0,  0,  0 },
					 { 0,  3,  0,  3,  3,  3,  3,  0,  3,  0 },
					 { 0,  3,  0,  3,  3,  3,  3,  0,  3,  0 },
					 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
					 { 0,  3,  0,  3,  3,  3,  3,  0,  3,  0 },
					 { 0,  3,  0,  3,  3,  3,  3,  0,  3,  0 },
					 { 0,  3,  0,  0,  0,  0,  0,  0,  3,  0 },
					 { 0,  3,  0,  0,  0,  0,  0,  0,  3,  0 },
					 { 0,  3,  0,  0,  0,  0,  0,  0,  3,  0 },
					 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
					 { 0,  3,  0,  0,  0,  0,  0,  0,  3,  0 },
					 { 0,  3,  0,  0,  0,  0,  0,  0,  3,  0 },
					 { 0,  3,  0,  3,  3,  3,  3,  0,  3,  0 },
					 { 0,  3,  0,  3,  3,  3,  3,  0,  3,  0 },
					 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
					 { 0,  3,  0,  3,  3,  3,  3,  0,  3,  0 },
					 { 0,  3,  0,  3,  3,  3,  3,  0,  3,  0 },
					 { 0,  0,  0,  3,  3,  3,  3,  0,  0,  0 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 }
					
					   };
	
				base.setMat(m4);
		break; 
		case 6:
				int[][] m5 = {
					 
					 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
					 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
					 { 0,  0,  3,  3,  3,  3,  3,  3,  0,  0 },
					 { 0,  0,  3,  3,  3,  3,  3,  3,  0,  0 },
					 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
					 { 0,  0,  3,  3,  0,  0,  3,  3,  0,  0 },
					 { 0,  0,  3,  3,  0,  0,  3,  3,  0,  0 },
					 { 0,  0,  3,  3,  0,  0,  3,  3,  0,  0 },
					 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
					 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
					 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
					 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
					 { 0,  0,  3,  3,  0,  0,  3,  3,  0,  0 },
					 { 0,  0,  3,  3,  0,  0,  3,  3,  0,  0 },
					 { 0,  0,  3,  3,  0,  0,  3,  3,  0,  0 },
					 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
					 { 0,  0,  3,  3,  3,  3,  3,  3,  0,  0 },
					 { 0,  0,  3,  3,  3,  3,  3,  3,  0,  0 },
					 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 },
					 { 0,  0,  0,  0,  0,  0,  0,  0,  0,  0 }
					
					   };
	
				base.setMat(m5);
		break; /*
		//so many level!!!! I've not fency! 
		case 7:
				int[][] m6 = {
					 
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 }
					
					   };
	
				base.setMat(m6);
		break;
		case 8:
				int[][] m7 = {
					 
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 }
					
					   };
	
				base.setMat(m7);
		break;
		case 9:
				int[][] m8 = {
					 
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 }
					
					   };
	
				base.setMat(m8);
		break;
		case 10:
				int[][] m9 = {
					 
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 }
					
					   };
	
				base.setMat(m9);
		break;
		case 11:
				int[][] m10 = {
					 
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 }
					
					   };
	
				base.setMat(m10);
		break;
		case 12:
				int[][] m11 = {
					 
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 }
					
					   };
	
				base.setMat(m11);
		break;
		case 13:
				int[][] m12 = {
					 
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  0,  0,  0,  0,  0,  0,  0,  0,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 },
					 { 3,  3,  3,  3,  3,  3,  3,  3,  3,  3 }
					
					   };
	
				base.setMat(m12);
		break;*/
		}

	}
	
	protected void manageSpeed()
	{
		switch (base.getSpeedSelectedIndex())
			{
				case 0: base.setTimer(400); break;
				case 1: base.setTimer(370); break;
				case 2: base.setTimer(340); break;
				case 3: base.setTimer(290); break;
				case 4: base.setTimer(240); break;
				case 5: base.setTimer(190); break;
				case 6: base.setTimer(150); break;
				case 7: base.setTimer(130); break;
				case 8: base.setTimer(110); break;
				case 9: base.setTimer(80);  break;
			}
	}
	
	protected void manageLevel()
	{
		level = base.getLevelSelectedIndex()+1;
	}
	
	protected void drawApple()
	{
		int[][] mat = base.getMat();
		mat[yApple][xApple]=106;
		base.setMat(mat);
	}
	
	protected void createApple()
	{	
		int[][] mat = base.getMat();
		int x, y;
		
		boolean flag = true;
		do
		{  x = (int) (Math.random()*10);
		   y = (int) (Math.random()*20);
		  if (mat[y][x]==0) flag = false;
		}
		while (flag);
	
		xApple = x;
		yApple = y;	
		
		mat[y][x]=106;
		base.setMat(mat);
	}
}	

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ GAME: GENERAL GAME RULE INTEFACE@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

 // It acts as a general game rule class where other classes must hinerit and
 // overrides its methods 

class Game
{
	protected Base base;
	public Game (Base b) {base = b; }
	public void fall() {}
	public void endGame() {}
	public void startGame() {}
	public void manageKeyboard(int t) {}
	public int getPieceColorPreview() { return 0; } 
	public int getPiecePreview() { return 0; }
}
	







