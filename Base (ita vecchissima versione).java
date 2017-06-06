/* 
 * 
 *  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *  @@	                                                             @@
 *  @@                                                               @@
 *  @@	#########  #########  #########  #######    ###    #######   @@   
 *  @@	#########  #########  #########  ########   ###   #######    @@
 *  @@	   ###     ####          ###     ###  ###   ###  #####       @@
 *  @@	   ###     #########     ###     #######    ###   #####      @@
 *  @@	   ###     #########     ###     ### ###    ###    ######    @@
 *  @@	   ###     ####          ###     ###  ###   ###      #####   @@
 *  @@	   ###     #########     ###     ###   ###  ###    ######    @@
 *  @@	   ###     #########     ###     ###    ##  ###  ######      @@
 *  @@                                                               @@
 *  @@ Bonfigli Diego 4°G 2002-2003 "ITIS E. Divini" S.Severino (MC) @@
 *  @@                                                               @@
 *  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 *  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * 
 *
 *
 * 09.7.03 - inizio
 * 22.7.03 - rotazione completata
 * 25.7.03 - versione "beta" completata (grafica povera e nessun gioco multiplo)
 * ...........
 * 12.9.03 - versione 0.7 completata
 *
 * Il problema principale è stato la gestione della tastiera: all'inizio 
 * ho usato l'interfaccia KeyListener e i suoi metodi ma poi (soprattutto con 
 * Explorer) mi sono accorto che provocava un errore alla chiusura:
 * "java.lang.IllegalStateException: Can't dispose InputContext while it's active"
 * Così ho usato keyDown.
 *
 * Un altro problema riuguarda l'interfaccia: ho fatto diverse prove per 
 * disporre nel modo che volevo gli elementi e soprattutto la loro dimensione 
 * e alla fine mi sono dovuto piegare a quella che mi forniva il BorderLayout.
 * 
 *
 * Esempio di pagina HTLML per l'applet:
 *
 * <HTML>
 *
 * <head>
 *  	<title>	TETRIS </title>
 * </head>
 * <BODY> 
 * 		<applet code="Base.class"
 *      	 align="baseline" width=310 height=360>
 *       	    <param name="coloreBottoniRGB" value="110,250,110">
 *           	<param name="coloreFontRGB" value="0,100,100">
 *           	<param name="colorePannelliRGB" value="154,209,183">
 *           	<param name="nomeBlocchi" value="blocchi.gif">
 *           	<param name="nomeSfondo" value="3812.jpg">
 * 		</applet>
 * </BODY>
 * </HTML>
 *
 * i parametri sono facoltativi (ma consigliati, pena una grafica peggiore):
 * essi riguardano il colore (si può personalizzare il colore dei bottoni, dei
 * pannelli e dei font: per fare ciò bisogna inserire 3 valori red green blue 
 * da 0 a 255 separati da una virgola sul parametro specifico), l'immagine di 
 * sfondo ("nomeSfondo") e l'immagine dei blocchi ("nomeBlocchi") (per maggiori 
 * informazioni vedi la classe Disegno). Se non inseriti (o inseriti errati), 
 * il gioco funzionerà correttamente ma i blocchi e lo sfondo saranno generati 
 * automaticamente con i metodi messi a disposizione dal linguaggio.
 *
 * L'applet funziona con qualsiasi dimensione data ma esteticamente il miglior 
 * risultato si ottiene con la formula width=height/2+140 (140 è la larghezza 
 * dei due pannelli e la matrice di gioco è 20*10, cioè la larghezza la metà 
 * dell'altezza, in questo modo il gioco sfrutterà tutto lo spazio a 
 * disposizione).
 *
 * Ci sono 4 giochi: 
 * 1) Tetris classico; 
 * 2) Tetris con pezzi strani; 
 * 3) TetrisAbbattiL'UltimaRiga: lo scopo è abbattere l'ultima riga 
 *    (quella con il quadratino giallo); 
 * 4) TetrisAbbattiL'UltimaRiga & pezzi strani 
 *
 * comandi: 
 * Freccia a sinistra: muovi a sinistra 
 * Freccia a destra: muovi a destra 
 * Freccia su: rotazione
 * Freccia giu: discesa rapida 
 * 
 * La velocità si incrementa automaticamente ogni 10000 punti.
 * Il livello indica la difficoltà iniziale (appariranno alcuni pezzi sul fondo 
 * in modo casuale)
 */
 
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ BASE : LA CLASSE PRINCIPALE @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 
 // la classe Base: è l'applet che costituisce l'interfaccia e dona i metodi 
 // principali per quanto riguarda la struttura del programma
 
import java.awt.*;	        
import java.awt.event.*;
import java.awt.image.*; 
import java.applet.*;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.util.StringTokenizer;


public class Base extends Applet implements ActionListener, Runnable	{
	
	/*volatile*/ private Thread thread;
	
	//suoni
	private AudioClip suonoFine;
	private AudioClip suonoRiga;
	private AudioClip suonoTerra;
	private AudioClip suonoInizio;
	
	//interfaccia
	private Button inizia = new Button("START");
	private Button pausa = new Button("PAUSE");
	private Button riprendi = new Button("RESUME");
	private Button fine = new Button("END");
	
	private Choice velocita = new Choice();
	private Choice livello = new Choice();
	private Choice gioco = new Choice();
	
	private Label punti = new Label("0", Label.LEFT);
	private Label linee = new Label("0", Label.LEFT);
	private Label labelLinee = new Label("lines:");
	private Label labelPunti = new Label("points:");
	private Label labelLivello = new Label("level:");
	private Label labelVelocita = new Label("speed:");
	private Label labelGioco = new Label("game:");
	
	private JPanel panelW;
	private JPanel panelE;
	
	private Checkbox checkSuono = new Checkbox("sound", true);
 	
	//matrice dell'area di gioco
	private int colonne = 10;
	private int righe = 20;
	private int mat[][]= new int [righe][colonne];
	
	//punteggio
	private int nLinee=0;
	private int nPunti=0;
	
	/* giocoCorrente, gli altri giochi derivano da questo (ereditarietà) e
	 * quindi hanno la stessa interfaccia (i metodi di interfaccia sono stati
	 * sovraccaricati), così può essere usato da Base anche come un altro 
	 * gioco (non-Tetris). (wow! polimorfismo in azione!)
	 */   
	 
	private Tetris giocoCorrente = new Tetris(this);
	
	//canvas anteprima e disegno
	private Disegno disegno;
	private Anteprima anteprima;
	
	//timer
	private int timer = 400;
	
	//giocoAttivo: la variabile che dice se il gioco è in stato attivo
	private boolean giocoAttivo = false;
	
	//colori
	private Color coloreFont;
	private Color coloreBottoni;
	private Color colorePannelli;
	
	//##### metodo per la tastiera ##############################

	public boolean keyDown(Event e, int key)
	{
		if (getGiocoAttivo())
		{ int tasto = key;
		  giocoCorrente.gestisciTastiera(tasto);
		} 
			
		return false;
	}
	
	//##### metodi ActionListener ################################
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("PAUSE"))
			{ pausa.enable(false); 
			  riprendi.enable(true);
			  riprendi.requestFocus();
			  setGiocoAttivo(false);
			  stop(); }
		
		if(e.getActionCommand().equals("RESUME")) 
			{ start();
			  riprendi.enable(false);
			  pausa.enable(true);
			  setGiocoAttivo(true);
			  disegno.requestFocus(); 
		    }
		
		if(e.getActionCommand().equals("START"))
		 	{ 
		 	  switch (getIndexGioco()+1)
		 	  {
		 	  	case 1: giocoCorrente = new Tetris(this); 		break;
		 	  	case 2: giocoCorrente = new Tetris2(this); 		break;
		 	  	case 3: giocoCorrente = new TetrisRiga(this);	break;
		 	  	case 4: giocoCorrente = new Tetris2Riga(this);	break;
		 	  } 
		 	  giocoCorrente.startGame();
		 	  disegnaDisegno();
		 	} 
		
		if(e.getActionCommand().equals("END"))
			{ 
			  giocoCorrente.endGame();
		 	} 
		
	}
	
	//##### init e destroy #######################################
	
	public void init()
	{	
		//audio
		suonoFine = getAudioClip(getCodeBase(),"die.au");
		suonoRiga = getAudioClip(getCodeBase(),"driga.au");
		suonoInizio = getAudioClip(getCodeBase(),"begin.au");
		suonoTerra = getAudioClip(getCodeBase(),"terra.au");
		
		cancella();	
		
		//genera i colori
		prendiTuttiColori();		
		
		//disegno
		disegno = new Disegno(this);
		
		//anteprima 
		anteprima = new Anteprima(this);
		
		//pannello W
	 	panelW = new JPanel();
	 	panelW.setBackground(Color.red);
		panelW.setLayout(new GridLayout(0,1, 5, 5));
		panelW.setBorder(BorderFactory.createRaisedBevelBorder());
		
		panelW.add(labelPunti);
		panelW.add(punti);
		panelW.add(labelLinee);
		panelW.add(linee);
		panelW.add(checkSuono);
		
		panelW.add(labelVelocita);
		panelW.add(velocita);
		velocita.addItem("1");
		velocita.addItem("2");
		velocita.addItem("3");
		velocita.addItem("4");
		velocita.addItem("5");
		velocita.addItem("6");
		velocita.addItem("7");
		velocita.addItem("8");
		velocita.addItem("9");
		velocita.addItem("10");
		
		panelW.add(labelLivello);
		panelW.add(livello);
		livello.addItem("1");
		livello.addItem("2");
		livello.addItem("3");
		livello.addItem("4");
		livello.addItem("5");
		livello.addItem("6");
		livello.addItem("7");
		livello.addItem("8");
		livello.addItem("9");
		livello.addItem("10");
		livello.addItem("11");
		livello.addItem("12");
		livello.addItem("13");
		
		panelW.add(labelGioco);
		panelW.add(gioco);
		gioco.addItem("1");
		gioco.addItem("2");
		gioco.addItem("3");
		gioco.addItem("4");
			
		//actionListeners
		inizia.addActionListener(this); 
		pausa.addActionListener(this);
		riprendi.addActionListener(this);
		fine.addActionListener(this);
		
		// pannello E
		panelE = new JPanel();
		panelE.setBackground(Color.red);
		panelE.setLayout(new GridLayout(0, 1, 5, 5));
		panelE.setBorder(BorderFactory.createRaisedBevelBorder());
		
		panelE.add(inizia);
		panelE.add(pausa);
		panelE.add(riprendi);
		panelE.add(fine);
		panelE.add(anteprima);
		
		//aspetto applet
		setBackground(Color.lightGray);
		gestisciColori(); 
		
		setLayout(new BorderLayout());
		add(panelE, "East");
		add(disegno, "Center");
  		add(panelW, "West");
  		
     	fine.enable(false);
     	riprendi.enable(false);
     	pausa.enable(false);
	}
	
	//non so se serve veramente...
	public void destroy()
	{
		giocoCorrente=null;
	}
	
	//##### metodi Start, Stop, Runnable #########################

    public void run()
    {   
        while ( thread == Thread.currentThread()) 
        	{  	
        		if (getGiocoAttivo())	
        			giocoCorrente.scendi();
        		
        		try { Thread.currentThread().sleep(timer); } 
        		catch (InterruptedException e) {}
        	}
    }	

	public void stop() 
	{	
//	if (thread!=null) { thread.stop(); }
// non sò perchè, se faccio thread.stop() allora thread=null non viene eseguita
// adesso lo so perchè! l'ho letto su una guida: è deprecato e dà problemi
		thread=null;
		}
			
	public void start() 
	{ 
		if (thread == null) {
			thread = new Thread(this); 
		//	thread.setPriority(Thread.MIN_PRIORITY);
			thread.start(); 
			}
	//	thread.start(); 
	}
	
	//##### metodi utili e di uso generale #######################
	
	//##### get & set ##########################
	
	public void setMat(int[][] m)
	{	for (int i=0; i<righe; i++)
			for (int j=0; j<colonne; j++)
					mat[i][j]=m[i][j];
	}
	
	public int[][] getMat()
	{	int[][] z = new int[righe][colonne];
		for (int i=0; i<righe; i++)
			for (int j=0; j<colonne; j++)
				z[i][j]=mat[i][j];	
		return z; 
	} 
	
	public void setnLinee(int n) { nLinee=n; }
	public int getnLinee() { return nLinee; }

    public void setnPunti(int n) { nPunti=n; }
	public int getnPunti() { return nPunti; }

	public int getColonne() { return colonne; }
	public int getRighe() { return righe; }
	
	public int getTimer() { return timer; }
	public void setTimer(int t) { timer=t; }
	
	public void setGiocoAttivo(boolean g) { giocoAttivo = g;}
	public boolean getGiocoAttivo() { return (giocoAttivo); }
	
	public int getAnteprimaPezzo() { return giocoCorrente.getAnteprimaPezzo(); }
	public int getAnteprimaColorePezzo() 
	{ return giocoCorrente.getAnteprimaColorePezzo(); }
	
	public int getIndexVelocita() { return velocita.getSelectedIndex(); }
	public void setIndexVelocita(int index) { velocita.select(index); }
	
	public int getIndexLivello() { return livello.getSelectedIndex(); }
	public void setIndexLivello(int index) { livello.select(index); }
	
	public int getIndexGioco() { return gioco.getSelectedIndex(); }
	public void setIndexGioco(int index) { gioco.select(index); }
	
	public Color getColoreFont() { return new Color(coloreFont.getRGB()); }
	public Color getColoreBottoni() { return new Color(coloreBottoni.getRGB()); }
	public Color getColorePannelli() { return new Color(colorePannelli.getRGB()); }
	
	public Image[] getBlocchi() { return disegno.getBlocchi(); }
	
	//##### operazioni sulla matrice #############
	
	// cancella la matrice del gioco
	public void cancella()
	{	for (int i=0; i<righe; i++)
			for (int j=0; j<colonne; j++)
					mat[i][j]=0;	
	}
	/*
	//visualizzazione a caratteri (per il debug)
	public void visualizzaMat()		
	{ for (int i=0; i<righe; i++)
	   {	System.out.println();
			for (int j=0; j<colonne; j++)
				System.out.print (mat[i][j]);
		}
	  System.out.println("-------------");
	   
	}
	*/
	public void visualizzaFINE()
	{ 
	  //FINE
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
	  
	  
	  disegnaDisegno();
	}
	
	//##### metodi per i suoni ####################
	
	public void playFine()
	{   if (checkSuono.getState() == true)
		if (suonoFine != null) suonoFine.play(); }
		
	public void playInizio()
	{   if (checkSuono.getState() == true)
		if (suonoInizio != null) suonoInizio.play(); }
		
	public void playTerra()
	{   if (checkSuono.getState() == true)
		if (suonoTerra != null) suonoTerra.play(); }
		
	public void playRiga()
	{   if (checkSuono.getState() == true)
		if (suonoRiga != null) suonoRiga.play(); }

	//###### startGame & endGame ###################
	
	public void startGame()
	{
	  inizia.enable(false);
	  fine.enable(true);
	  pausa.enable(true);
	  velocita.enable(false);
	  livello.enable(false);
	  gioco.enable(false);
	  disegno.requestFocus();
	  playInizio();
	  cancella();	  	  
	  setnPunti(0);
	  setnLinee(0);
	  scriviPunteggio();
	  setGiocoAttivo(true);
	  start();
	}
	
	public void endGame()
	{
	  stop();
	  setGiocoAttivo(false);
	  playFine();
	  inizia.enable(true);
	  fine.enable(false);
	  pausa.enable(false);
	  riprendi.enable(false);
	  velocita.enable(true);
	  livello.enable(true);
	  gioco.enable(true);
	  inizia.requestFocus();
	  visualizzaFINE();
	  disegnaDisegno();
	}
	
	//##### metodi per repaint e le Choice ############
	
	public void disegnaAnteprima()
	{ anteprima.repaint(); }
	
	public void disegnaDisegno()
	{ disegno.repaint(); }
	
	public void scriviPunteggio()
	{
		 punti.setText(java.lang.Integer.toString(getnPunti()));
		 linee.setText(java.lang.Integer.toString(getnLinee()));
	}	
	
	//##### metodi il colore ###########################
		
	//imposta i colori e colora gli oggetti
	private void gestisciColori()
	{		
		inizia.setBackground(coloreBottoni);
		pausa.setBackground(coloreBottoni);
		riprendi.setBackground(coloreBottoni);
		fine.setBackground(coloreBottoni);
		
		livello.setBackground(coloreBottoni);
		velocita.setBackground(coloreBottoni);
		gioco.setBackground(coloreBottoni);
		
		panelW.setBackground(colorePannelli);
		panelE.setBackground(colorePannelli);
		anteprima.setBackground(colorePannelli);
							
		punti.setForeground(coloreFont);
		linee.setForeground(coloreFont);
		labelLinee.setForeground(coloreFont); 
		labelPunti.setForeground(coloreFont);
		labelLivello.setForeground(coloreFont); 
		labelVelocita.setForeground(coloreFont); 
		labelGioco.setForeground(coloreFont); 
			
		inizia.setForeground(coloreFont);
		pausa.setForeground(coloreFont);
		riprendi.setForeground(coloreFont);
		fine.setForeground(coloreFont);
			
		velocita.setForeground(coloreFont);
		livello.setForeground(coloreFont);
		gioco.setForeground(coloreFont);
			
		checkSuono.setForeground(coloreFont);		
	}	
	
	private void prendiTuttiColori()
	{
		coloreBottoni = prendiColori("coloreBottoniRGB");
		colorePannelli = prendiColori("colorePannelliRGB");
		coloreFont = prendiColori("coloreFontRGB");
	}
		
	//prende il colore dai parametri
	private Color prendiColori(String parametroRGB) 
	{
		StringTokenizer st;
		String token;
		boolean flag=false;
		Color colore = Color.black; 
		
		String coloreRGB = getParameter(parametroRGB);
		
		if (coloreRGB!=null)
		{
		 int red=0, green=0, blue=0;
		 flag=false;
		 st = new StringTokenizer(coloreRGB, ",");
		 
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
		
		colore = new Color(red, green, blue);
		}
		else 
		{	System.out.println ("parametro \""+parametroRGB+"\" per il colore non esistente, uso colori di default...");
			if (parametroRGB.equals("coloreBottoniRGB")) colore = new Color(110,250,110);
			if (parametroRGB.equals("colorePannelliRGB")) colore = new Color(154,209,183);
			if (parametroRGB.equals("coloreFontRGB")) colore = new Color(0,100,100);
		}
		
		if (flag) 
		{	System.out.println("errore nel formato del parametro \""+parametroRGB+"\" (non sono tre numeri separati da una virgola?), uso colori di default...");
			if (parametroRGB.equals("coloreBottoniRGB")) colore = new Color(110,250,110);
			if (parametroRGB.equals("colorePannelliRGB")) colore = new Color(154,209,183);
			if (parametroRGB.equals("coloreFontRGB")) colore = new Color(0,100,100);
		}
		
		return (colore);
	}
}  

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ DISEGNO: LA CLASSE PER LA GRAFICA @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

/*
 * la classe disegno: la parte grafica (con Double Buffering!!!)
 * La grafica è totalmente personalizzabile: si può cambiare lo sfondo, i colori
 * di font, bottoni e pannelli, le dimensioni e l'immagine dei blocchi (chi 
 * vuole magari può fare tutte stelline o tondini al posto dei quadrati, basta 
 * che ogni blocco sia 20x20 pixel e che tutti i blocchi siano allineati 
 * orizzontalmente senza nessuno spazio fra loro).  
 *
 * i blocchi nella loro immagine sorgente devono essere 20x20 pixel, non è 
 * possibile modificare queste dimensioni per semplicità: in questo modo non 
 * c'è bisogno di aumentare il numero dei parametri dell'applet ( e quindi di 
 * istruzioni per controllare il suo valore, per controllare che esistano 
 * sia il parametro nomeBlocchi che quello della dimensione e così via). 
 *
 */
 


class Disegno extends Canvas
{
	private Base base;
	private MediaTracker tracker;
	private Image offscreenImage;
	private Graphics offscreenGraphics;		//double buffering
	private Image back;						//sfondo
	private int dwidth;						//dimensioni: larghezza
	private int dheight;					//dimensioni: altezza
	
	private  Image blocchi[] = new Image[6]; //array delle immagini dei blocchi
	private int latoBlocco;	
	
	
	public Disegno(Base b)
	{ 
	  base=b;
	  setBackground(base.getColorePannelli());
	  
	  //###### verifica dimensioni ##############
	  dwidth = base.getWidth()-130; //130 è la larghezza dei pannelli
	  dheight = base.getHeight();
	  
	  //ricerca delle dimensioni ottimali
	  if (dwidth<=dheight && dwidth*2<=dheight) dheight=dwidth*2;
	  else dwidth=dheight/2;
	  
	  latoBlocco = dwidth/10;
	  
	  dwidth=latoBlocco*10;
	  dheight=latoBlocco*20;
	  
	  resize(dwidth,dheight);
	  
	  //###### creazione sfondo #################
	  tracker = new MediaTracker(this);
	  boolean flag=false;
	  String nomeSfondo = base.getParameter("nomeSfondo");
	  Image iback = null; //dove mettere temporaneamente l'immagine di sfondo
	  
	  if (nomeSfondo!=null)
	  {
	    iback =  base.getImage(base.getCodeBase(), nomeSfondo);
	    tracker.addImage(iback,0);
	    try {tracker.waitForAll();} 
	    catch (InterruptedException e)
	    	{System.out.println("errore MediaTraker per l'immagine "+nomeSfondo+": "+e.getMessage()); flag=true;}
	  	if (tracker.isErrorAny()) {System.out.println("errore nel caricamento immagine "+ nomeSfondo+
	  							   " (nome immagine errato o immagine non trovata), avvio creazione sfondo automatico..."); 
	  							   flag=true;}
	  }
	  //in caso di errore del' immagine di sfondo il gioco se la autocrea con sue funzioni
	  if (nomeSfondo==null || flag) 
	  { 
	    if (nomeSfondo==null) System.out.println ("parametro \"nomeSfondo\" per lo sfondo non esistente, uso sfondo di default...");
	    iback = base.createImage(dwidth, dheight); 
		Graphics g = iback.getGraphics();
		g.setColor(base.getColoreBottoni());
		g.fillRect(0,0,dwidth, dheight);
	  }
  	
 	   //setta l'immagine back (lo sfondo)
  	   back = base.createImage(dwidth,dheight);
  	   Graphics offback = back.getGraphics();
  	   offback.setColor(base.getColorePannelli());
  	   //immagine di sfondo
  	   offback.drawImage(iback, 0, 0, dwidth, dheight, this); 
  	   //cornice
	   offback.drawRect(0,0,dwidth, dheight);
	   offback.drawRect(1,1,dwidth-2, dheight-2);
	   offback.drawRect(4,4,dwidth-8, dheight-8);
	   offback.drawRect(5,5,dwidth-10, dheight-10);
	   offback.drawRect(6,6,dwidth-12, dheight-12);
  		
  	  //##### creazione blocchi ###################
  	  
  	  tracker = new MediaTracker(this);
  	  flag=false;
  	  Image tmp = null;
  	  String nomeBlocchi = base.getParameter("nomeBlocchi");
  	  
  	  if (nomeBlocchi!=null) 
  	  {
  	  	tmp = base.getImage(base.getCodeBase(), nomeBlocchi);
  	  	tracker.addImage(tmp, 0);
      	try {tracker.waitForAll();} 
      	catch (InterruptedException e) 
      		{System.out.println("errore MediaTraker per l'immagine "+nomeBlocchi+": "+e.getMessage());}
	  	if (tracker.isErrorAny()) 
	  		{System.out.println("errore nel caricamento immagine "+nomeBlocchi+
	  		"(nome immagine errato o immagine non trovata), uso blocchi di default..."); 
	  		flag=true;}
	  }
      
      //in caso di errore del' immagine dei blocchi il gioco se la autocrea con sue funzioni 
      if (nomeBlocchi==null || flag) 
    	{
    	 if (nomeBlocchi==null) System.out.println ("parametro \"nomeBlocchi\" per le immagini dei blocchi non esistente, uso blocchi di default...");
		 tmp = base.createImage(120, 20);
		 Graphics g = tmp.getGraphics();
		 g.setColor(Color.green);
		 g.fill3DRect(0,0,20,20,true);
    	 g.setColor(Color.red);
		 g.fill3DRect(20,0,20,20,true);
  		 g.setColor(Color.cyan);
		 g.fill3DRect(40,0,20,20,true);
  		 g.setColor(Color.magenta);
		 g.fill3DRect(60,0,20,20,true);
  	     g.setColor(Color.blue);
		 g.fill3DRect(80,0,20,20,true);
		 g.setColor(Color.yellow);
		 g.fill3DRect(100,0,20,20,true);
		}
	
	  //divisione dell'immagine dei blocchi...	 
  	  ImageFilter filter;
  	  ImageProducer producer;
  	  
  	  filter = new CropImageFilter(0,0,20,20);
	  producer = new FilteredImageSource(tmp.getSource(), filter);
  	  blocchi[0] = createImage(producer); 
  	 
  	  filter = new CropImageFilter(20,0,20,20);
	  producer = new FilteredImageSource(tmp.getSource(), filter);
  	  blocchi[1] = createImage(producer);
  
  	  filter = new CropImageFilter(40,0,20,20);
	  producer = new FilteredImageSource(tmp.getSource(), filter);
  	  blocchi[2] = createImage(producer);
  	
  	  filter = new CropImageFilter(60,0,20,20);
      producer = new FilteredImageSource(tmp.getSource(), filter);
  	  blocchi[3] = createImage(producer);
  	
  	  filter = new CropImageFilter(80,0,20,20);
	  producer = new FilteredImageSource(tmp.getSource(), filter);
  	  blocchi[4] = createImage(producer);
  	
      filter = new CropImageFilter(100,0,20,20);
	  producer = new FilteredImageSource(tmp.getSource(), filter);
  	  blocchi[5] = createImage(producer);
  	
  	  //##### double buffering ###################
  	  offscreenImage = base.createImage(dwidth, dheight);
	  offscreenGraphics = offscreenImage.getGraphics();
	  offscreenGraphics.setColor(base.getColorePannelli());	  	
    }
	
	public void paint(Graphics g) 
	{					
		offPaint();	
	    g.drawImage(offscreenImage,0,0,this);
	}
				
	public void update(Graphics g)
	{ paint(g); }
	
	private void offPaint()
	{
		int[][] mat = base.getMat();
		
		//sfondo
		offscreenGraphics.drawImage(back,0,0, dwidth, dheight, this);
		
		//blocchi	 									
		for (int i=0; i<base.getRighe(); i++)
			for (int j=0; j<base.getColonne(); j++)
				{	switch (mat[i][j]) 
						{	
							case 101: 	
							case 1: 	offscreenGraphics.drawImage(blocchi[0],
										j*latoBlocco, i*latoBlocco,
										latoBlocco,
										latoBlocco,
										this);
							break;	
							case 102:	
							case 2: 	offscreenGraphics.drawImage(blocchi[1],
										j*latoBlocco,
										i*latoBlocco,
										latoBlocco,latoBlocco, 
										this);
							break;	
							case 103:	
							case 3:	    offscreenGraphics.drawImage(blocchi[2],
										j*latoBlocco, 
										i*latoBlocco,
										latoBlocco,
										latoBlocco, 
										this);
							break;	
							case 104: 	
							case 4: 	offscreenGraphics.drawImage(blocchi[3],
										j*latoBlocco,
										i*latoBlocco,
										latoBlocco,
										latoBlocco, 
										this);
							break;	
							case 105:
							case 5:  	offscreenGraphics.drawImage(blocchi[4],
										j*latoBlocco, 
										i*latoBlocco, 
										latoBlocco,
										latoBlocco, 
										this);
	 						break;
	 						case 106:
	 						case 6: 	offscreenGraphics.drawImage(blocchi[5],
	 									j*latoBlocco,
	 									i*latoBlocco, 
	 									latoBlocco,
	 									latoBlocco, 
	 									this);
	 						break;
						}				
				} 
	} 
	
	public Image[] getBlocchi()
	{ 
	 Image[] im = new Image[6];
	 for (int i=0; i<6; i++)
	  	im[i]=blocchi[i];
	 
	 return im;
	}	
}

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ ANTEPRIMA : PER LA CANVAS DELL'ANTEPRIMA DEI PEZZI @@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 
// la classe Antperima: la canvas che disegna i prossimi pezzi
// può visualizzare solo pezzi ed è del formato di una matrice 4x4
// (no ho usato il double buffering perchè viene aggiornata ogni tanto)


class Anteprima extends Canvas
{
	private Base base;
	private Image offscreenImage;
	private Graphics offscreenGraphics;		//double buffering
	private Image[] blocchi = new Image [6];
	
	private int latoBloccoAnteprima = 0;
	private int scostamentoX=0;
	private int scostamentoY=0;
	private boolean dimensionato = false;
	
	
	public Anteprima(Base b)
	{ 
	 base=b;
	 setBackground(base.getColorePannelli()); 
	 
	 offscreenImage = base.createImage(100,100);
	 //comunque l'immagine sarà ridimensionata alla larghezza della canvas
	 offscreenGraphics = offscreenImage.getGraphics();
	 
	 blocchi = base.getBlocchi();	 
	}
	
	private void offPaint() 
	{	
	 int [][] mat = new int [4][4];	
	 int n= base.getAnteprimaPezzo();
	 int c= base.getAnteprimaColorePezzo();	 									
		
	 switch (n) 
		{	
			case 1: mat[1][1]=mat[1][2]=mat[2][1]=mat[2][2]=c; //Q
			break;
			case 2: mat[1][1]=mat[2][0]=mat[2][1]=mat[2][2]=c; //T
			break;
			case 3: mat[0][1]=mat[1][1]=mat[2][1]=mat[2][2]=c; //Ld
			break;
			case 4: mat[1][1]=mat[1][0]=mat[1][2]=mat[1][3]=c; //S
			break;
			case 5: mat[1][0]=mat[1][1]=mat[2][1]=mat[2][2]=c; //Zd
			break;
			case 6: mat[0][2]=mat[1][2]=mat[2][2]=mat[2][1]=c; //Ls
			break;
			case 7: mat[1][2]=mat[1][1]=mat[2][1]=mat[2][0]=c; //Zs
			break;
			case 8: mat[0][1]=mat[0][0]=mat[1][1]=mat[2][1]=mat[2][2]=c; //Lgd
			break;				
			case 9: mat[0][1]=mat[0][2]=mat[1][1]=mat[2][1]=mat[2][0]=c; //Lgs
			break;				
			case 10: mat[1][1]=mat[2][1]=mat[1][2]=c; //Lpd
			break;				
			case 11: mat[1][1]=mat[2][1]=mat[2][2]=c; //Lpd
			break;				
			case 12: mat[1][1]=mat[2][1]=c; //Sp
			break;				
			case 13: mat[1][1]=c; //Spp
			break;				
			case 14: mat[0][1]=mat[1][1]=mat[2][1]=mat[2][0]=mat[2][1]=mat[2][2]=c; //Tg
			break;
			case 15: mat[1][2]=mat[0][1]=mat[1][1]=mat[1][0]=mat[2][1]=c; //X
			break;										
		}				
	 
	 if (!dimensionato)
	 {
	 if ( (int) (this.size().height/3) < (int) (this.size().width/4) )
	 		{ latoBloccoAnteprima = (int) (this.size().height/3);
	 		  scostamentoX = (this.size().width-latoBloccoAnteprima*4)/2; }
	 	else
	 		{latoBloccoAnteprima = (int) (this.size().width/4);
	 		 scostamentoY = (this.size().height-latoBloccoAnteprima*3)/2; }
	 
	 dimensionato = true;
	 }
	 
	 offscreenGraphics.setColor(base.getColorePannelli());	
	 offscreenGraphics.fill3DRect(0,0,this.size().width,this.size().height,true);
	 	 	
	 for (int i=0; i<4; i++)
		for (int j=0; j<4; j++)		  
			  switch (mat[i][j]) 
			  	{
			  	 case 1:	offscreenGraphics.drawImage(blocchi[0],
			  								j*latoBloccoAnteprima+scostamentoX,
			  								i*latoBloccoAnteprima+scostamentoY,
			  								latoBloccoAnteprima,
			  								latoBloccoAnteprima,
			  								this);
			  	 break;
			  	 case 2:	offscreenGraphics.drawImage(blocchi[1],
			  								j*latoBloccoAnteprima+scostamentoX,
			  								i*latoBloccoAnteprima+scostamentoY,
			  								latoBloccoAnteprima,
			  								latoBloccoAnteprima,
			  								this);
			  	 break;
			  	 case 3:	offscreenGraphics.drawImage(blocchi[2],
			  								j*latoBloccoAnteprima+scostamentoX,
			  								i*latoBloccoAnteprima+scostamentoY,
			  								latoBloccoAnteprima,
			  								latoBloccoAnteprima,
			  								this);
			  	 break;
			  	 case 4:	offscreenGraphics.drawImage(blocchi[3],
			  								j*latoBloccoAnteprima+scostamentoX,
			  								i*latoBloccoAnteprima+scostamentoY,
			  								latoBloccoAnteprima,
			  								latoBloccoAnteprima,
			  								this);
			  	 break;
			  	 case 5:	offscreenGraphics.drawImage(blocchi[4],
			  								j*latoBloccoAnteprima+scostamentoX,
			  								i*latoBloccoAnteprima+scostamentoY,
			  								latoBloccoAnteprima,
			  								latoBloccoAnteprima,
			  								this);
			  	 break;
			  	 case 6:	offscreenGraphics.drawImage(blocchi[5],
			  								j*latoBloccoAnteprima+scostamentoX,
			  								i*latoBloccoAnteprima+scostamentoY,
			  								latoBloccoAnteprima,
			  								latoBloccoAnteprima,
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
 //@@@@@@@@@@ TETRIS: LE REGOLE DEL GIOCO @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

/* La classe Tetris: la principale per quanto riguarda il gioco: 
 * qui ci sono tutti i metodi che servono strettamente ad esso.
 *
 * L'ho fatto tutto in una classe, ma sarebbe sato molto meglio 
 * (in quanto a leggibilità del codice) strutturarlo in più classi, una per
 * i pezzi, una per i movimenti dei pezzi e la rotazione e una per il gioco
 * stesso.
 *
 * La classe funziona in questo modo: una matrice descrive l'area di gioco e, 
 * in base al valore che ha ogni suo elemento, disegna o no un colore, i pezzi 
 * attivi (che possono muoversi e che stanno scendendo) hanno valore
 * incrementato di di 100.
 *
 */

class Tetris

{
	protected Base base; 
	
	//tipo di pezzo
	protected int pezzo = 0;
	
	//coordinate centrali del pezzo sulla matrice
	protected int xP=-1;
	protected int yP=-1;
	
	// prossimo pezzo
	protected int anteprimaPezzo;
	protected int anteprimaColorePezzo;
	
	//##### costruttore ##########################################
	
	public Tetris(Base b)
	{ base = b; }

	//##### gestione delle Choice velocita e livello della classe Base #######
	
	// questi metodi non sono stati implementati nella classe Base in modo che
	// futuri giochi diversi possano avere gestioni diverse
		
	protected void gestisciVelocita()
	{	
		switch (base.getIndexVelocita())
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
	
	protected void gestisciLivello()
	{	
	  int n= (base.getRighe()-(base.getIndexLivello()+1))+1;
	  int[][] x = new int[base.getRighe()][base.getColonne()]; 
	  
	  if (n>0)
	  { for (int i=base.getRighe()-1; i>=n; i--)
	  		for (int j=0; j<base.getColonne(); j++)
	  			{	if (Math.random()>0.5)
	  				x[i][j]=(int)(Math.random()*5+1);
	  			}
		
		base.setMat(x);
		controlloRiga();
	  }
	
	}

	//##### startGame / endGame ##################################
	
	public void startGame()
	{ 
	  base.startGame();
	  
	  //controlla la velocità e il livello iniziale
	  gestisciVelocita();
	  gestisciLivello();
	  
	  //genera il primo pezzo
	  anteprimaPezzo();
	  generaPezzo();
    }

	public void endGame() 
	{  
	  base.endGame();
	}

	//##### scendi: l'iteratore ##################################
	
	//la procedura funziona solo e soltanto se c'è un pezzo attivo (da 100) in giro
	public void scendi()	
	{	
	
		boolean flag = true;
		int[][] x = base.getMat();
		
		for (int i=base.getRighe()-1; i>=0; i--)
			for (int j=base.getColonne()-1; j>=0; j--)
					 if (x[i][j]>100) 
					 	 if  ((i+1)<base.getRighe())
					 	 	if (x[i+1][j]==0) 
								 { x[i+1][j]=x[i][j];
						  	  	   x[i][j]=0; }
					 	    else { flag=false; i=-1; j=-1;}
					 	 else { flag=false; i=-1; j=-1;}
					 						 
		if (!flag) //se il pezzo ha toccato il fondo o altri pezzi...
			 {	int[][] n = base.getMat();
			 	//azzera il dislivello del pezzo attivo			 				
				for (int i=0; i<base.getRighe(); i++) 			 
					for (int j=0; j<base.getColonne(); j++) 
							if (n[i][j]>100) n[i][j]-=100;

				base.setMat(n);
				controlloRiga();
				base.playTerra();
				generaPezzo();
			 }
		else { yP++; base.setMat(x); } 	//altrimenti continua la discesa
		
		base.disegnaDisegno();
						 
	}

	//##### controllo delle righe piene ##########################
	
	protected void controlloRiga()
	{
		int [][] m = base.getMat();
		int cont=0;
		
		for (int i=base.getRighe()-1; i>=0; i--)
			for (int j=0; j<base.getColonne(); j++)
				{ if (m[i][j]==0 || m[i][j]>100) break;
				  if (j==base.getColonne()-1) 
				  	{
				  	 distruggiRiga(i); 
				  	 
				  	 base.setnPunti(base.getnPunti()+100);
				  	 base.setnLinee(base.getnLinee()+1);
				  	 base.scriviPunteggio();
				  	 
				  	 //controlla se alzare la velocità
				  	 if (base.getIndexVelocita()<10)
				  	 	if (base.getnPunti()/10000/(base.getIndexVelocita()+1)>=1)
				  	 		{ base.setIndexVelocita(base.getIndexVelocita()+1);
				  	 	  	  gestisciVelocita();
				  	 		}  
				  	 
				  	 //prepara prossimo controllo... 
				 	 m = base.getMat();
				  	 i++;
				  	 cont++;
				  	 }
				}
		
		if (cont>1)
		{
			base.setnPunti(base.getnPunti()+100*cont);
			base.scriviPunteggio();
		}
		
		if (cont>0) base.playRiga();
	}
	
	protected void distruggiRiga(int riga)
	{
		int [][] m = base.getMat();
		
		for (int i=riga-1; i>=0; i--)
			for (int j=0; j<base.getColonne(); j++)
				m[i+1][j]=m[i][j];
		
		base.setMat(m);
	}
						
	//##### genera pezzi #########################################
	
	protected void anteprimaPezzo()
	{ anteprimaPezzo=(int)(Math.random()*7+1);
	  base.disegnaAnteprima();
	  anteprimaColorePezzo=(int)(Math.random()*5+1); }
	
	protected void generaPezzo()
	{
		pezzo = anteprimaPezzo;
		int p = pezzo;
		if (p==1) generaQ();
		if (p==2) generaT();
		if (p==3) generaLd();
		if (p==4) generaS();
		if (p==5) generaZd();
		if (p==6) generaLs();
		if (p==7) generaZs();
		
		/*
		//rotazione casuale iniziale
		int x=(int)(Math.random()*4);
		for (int i=0; i<x; i++) rotazione();
		*/
		
		base.disegnaAnteprima();	
		
		//prepara il prossimo pezzo
		anteprimaPezzo();
	}
	
	protected boolean generaQ() //quadrato
	{	
	    boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[0][5]!=0 || m[1][4]!=0 || m[1][5]!=0) flag = false;
		m[0][4]=m[0][5]=m[1][4]=m[1][5]=anteprimaColorePezzo+100;
		base.setMat(m);  xP=4; yP=0; pezzo=1;	
		if (!flag) endGame();
		return (flag);
	}
		
	protected boolean generaT() //a forma di T
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][3]!=0 || m[1][4]!=0 || m[1][5]!=0) flag = false;	
		m[0][4]=m[1][3]=m[1][4]=m[1][5]=anteprimaColorePezzo+100;
		base.setMat(m); xP=4; yP=1; pezzo=2;	
		if (!flag) endGame();
		return (flag);
	}	
	
	protected boolean generaLd() //genera a forma di L con quadratino a destra
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[2][4]!=0 || m[2][5]!=0) flag = false;
		m[0][4]=m[1][4]=m[2][4]=m[2][5]=anteprimaColorePezzo+100;
		base.setMat(m); xP=4; yP=1; pezzo=3;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean generaLs() //genera a forma di L con quadratino a sinistra
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[2][4]!=0 || m[2][3]!=0) flag = false;
	    m[0][4]=m[1][4]=m[2][4]=m[2][3]=anteprimaColorePezzo+100;
	    base.setMat(m); xP=4; yP=1; pezzo=6;	
		if (!flag) endGame();
		return (flag);
	}
		
	protected boolean generaS() //genera segmento (pezzo lungo)
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[2][4]!=0 || m[3][4]!=0) flag = false;
		m[0][4]=m[1][4]=m[2][4]=m[3][4]=anteprimaColorePezzo+100;
		base.setMat(m); xP=4; yP=2; pezzo=4;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean generaZd() //pezzo a forma di Z con quadratino in basso a destra
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][3]!=0 || m[0][4]!=0 || m[1][4]!=0 || m[1][5]!=0) flag = false;	
		m[0][3]=m[0][4]=m[1][4]=m[1][5]=anteprimaColorePezzo+100;
		base.setMat(m); xP=4; yP=1; pezzo=5;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean generaZs() //pezzo a forma di Z con quadratino in basso a sinstra
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[1][3]!=0 || m[0][4]!=0 || m[1][4]!=0 || m[0][5]!=0) flag = false;		
		m[1][3]=m[0][4]=m[1][4]=m[0][5]=anteprimaColorePezzo+100;
		base.setMat(m); xP=4; yP=1; pezzo=7;	
		if (!flag) endGame();
		return (flag);
	}
	
	//##### movimento pezzi ######################################
	
	public void gestisciTastiera(int t)
	{         
		int tasto=t;
	
		switch (tasto) 
		{	
		case 1006:	sinistra();		//freccia sinistra
		break;		
		case 1004:	rotazione();	//freccia su
		break;
		case 1007:	destra();		//freccia destra
		break;	
		case 1005:	scendi();		//freccia giu'
		}
	}
	
	/* i metodi per i movimenti ritornano un valore booleano 
	 * che inica se lo spostamento è andato a buon fine
	 */
	 
	protected boolean sinistra()
	{
		boolean flag = true;
		boolean flag2 = false;
		int[][] x = base.getMat();
		
		for (int i=0; i<base.getRighe(); i++)
			for (int j=1; j<base.getColonne(); j++)
					 if (x[i][j]>100)
					 	{ if  (x[i][j-1]==0) 
							   { flag2=true;
							     x[i][j-1]=x[i][j];
						  	     x[i][j]=0; }
					 	  else
					 	  { flag=false; i=base.getRighe(); j=base.getColonne();}
					 	}		
		
		if (flag && flag2) 
		{ base.setMat(x); if(xP>0)xP--; base.disegnaDisegno(); }
		
		return (flag && flag2);					 
	}
	
	protected boolean destra()
	{
		boolean flag = true;
		boolean flag2 = false;
		
		int[][] x = base.getMat();
		
		for (int i=0; i<base.getRighe(); i++)
			for (int j=base.getColonne()-2; j>=0; j--)
					 if (x[i][j]>100)
					 	{ if (x[i][j+1]==0) 
							 { flag2=true;
							   x[i][j+1]=x[i][j];
						  	   x[i][j]=0; }
					 	 else{ flag=false; i=base.getRighe(); j=-1;}
					 	}		
		if (flag && flag2) { base.setMat(x); 
							 if( xP<base.getColonne() ) xP++;
							 base.disegnaDisegno(); }	
		
		return (flag && flag2);				 
	}
	
	/*
	//utilizzato solo nella correzione della rotazione(adesso è stata eliminata)
	protected boolean su()
	{
		int[][] m = base.getMat();
	
		boolean flag = true;
		boolean flag2 = false;
		
		for(int i=0; i<base.getRighe(); i++)
			for (int j=0; j<base.getColonne(); j++)
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
	
	//##### rotazione ############################################
	
	protected void rotazione() //rotazione specifica per ogno pezzo
	{
		int[][] m = base.getMat();
		
		if (pezzo!=1 && pezzo!=15) if (pezzo==4) ruota(4); else ruota(3);	
		base.disegnaDisegno();	
	}	
	
	/* il metodo ruota può far ruotare qualsiasi pezzo di qualsiasi dimensione
	 * e forma, il parametro "lato" è la lunghezza del lato del quadrato 
	 * circoscritto nel pezzo, ritorna un valore booleano che dice se la 
	 * rotazione è andata a buon fine
	 */
	protected boolean ruota(int lato)
	{
		int l=(int)(lato/2);
		int[][] m = base.getMat();
		int[][] x = base.getMat();
		
		boolean bene=true;
	
		for (int i=-l; i<=l; i++) //azzera il pezzo
			for (int j=-l; j<=l; j++)
			{	if (yP+i>=base.getRighe() || yP+i<0 || xP+j>=base.getColonne() || xP+j<0) continue;
				if (x[yP+i][xP+j]>100) x[yP+i][xP+j]=0;
			}
				
		 for (int i=-l; i<=l; i++) //ruota il pezzo
			for (int j=-l; j<=l; j++)
			{	if (yP+i>=base.getRighe() || yP+i<0 || xP+j>=base.getColonne() || xP+j<0) continue;
				
				if (m[yP+i][xP+j]>100)	
				{	if (yP-j<0  || yP-j>=base.getRighe() || xP+i>=base.getColonne() || xP+i<0) {bene=false; break;}
					if (m[yP-j][xP+i]>100 || m[yP-j][xP+i]==0)
					   {x[yP-j][xP+i] = m[yP+i][xP+j]; }
					else bene=false;
				}
			}					
		if (bene) {base.setMat(x); }

		return (bene);
	}
	
	//##### motodi get e set #####################################
	
	public int getAnteprimaPezzo()
	{ return anteprimaPezzo; }
	
	public int getAnteprimaColorePezzo()
	{ return anteprimaColorePezzo; }

}

/*
  rotazione (con correzione) delle vecchie versioni. 
  (certe volte genera errori, ma molto raramente).
  (cmq meglio prevenire che curare).
  (anche se ho sprecato un bel po' di tempo per scriverla).
  (perchè è stata la cosa più difficile di tutto quanto).
  (e adesso l'ho tolta).
  (che peccato).
  (uffa).
 
 protected void rotazione() 
	{
		int[][] m=base.getMat();
		switch (pezzo)
		{	
		case 5:	if(ruota(3))	//Zd
				{if(yP-1>=0 && xP-1>=0)
				 if(m[yP-1][xP-1]==0) { yP+=1;  su();}
				}
				else {sinistra(); if(ruota(3))	
								 {if(yP-1>=0 && xP-1>=0)
							      if(m[yP-1][xP-1]==0) { yP+=1;  su();}
								 }
					  destra();
					 }
				base.disegnaDisegno();
		break;	
		case 7: if(ruota(3))	//Zs
				{if(yP-1>=0 && xP-1>=0)
				if(m[yP-1][xP-1]>100) { yP+=1;  su();}
				}
				else {sinistra(); if(ruota(3))	
								 {if(yP-1>=0 && xP-1>=0)
							      if(m[yP-1][xP-1]>100) { yP+=1;  su();}
								 }
					  destra();
					 }
				base.disegnaDisegno();
		break;			
		case 2:	if (!ruota(3))	//T
				{ if (destra()) {ruota(3); sinistra();}
				  else {sinistra(); ruota(3); destra();}
				} 
				base.disegnaDisegno();
		break;	
		case 3:	 if(!ruota(3)) //Ld
				    	{if(sinistra()) {ruota(3); destra();}
				     		else {destra(); ruota(3); sinistra();}
						}
				 if (yP+1<base.getRighe() && yP-1>=0 && xP-1>=0 && xP+1<base.getColonne())   
				 if (m[yP][xP-1]<100 && m[yP-1][xP+1]<100 && m[yP-1][xP-1]<100)
				  	 sinistra();
				 
				 if (yP+1<base.getRighe() && yP-1>=0 && xP-1>=0)   
				 if (m[yP+1][xP-1]<100 && m[yP+1][xP]<100 && m[yP-1][xP-1]<100)
				  	 destra(); 
				 base.disegnaDisegno();
		break;
		case 4: if (ruota(4))	//S  
		        {
		        	if(yP-2>=0)
				 	if(m[yP-2][xP]==0) { yP+=1; su(); }
				} 
				else
				{
					su();
					if (ruota(4))	//S  
		        	{ if(yP-2>=0)
					  if(m[yP-2][xP]==0) { yP+=1; su(); } }
					scendi();
				} 
				base.disegnaDisegno();
		break;
		case 6: if(!ruota(3)) //Ls
					{if(sinistra()) {ruota(3); destra();}
				     	else {destra(); ruota(3); sinistra();}
					}	
				if (xP+1<base.getColonne() && yP-1>=0 && xP-1>=0)   
				if (m[yP-1][xP-1]<100 && m[yP-1][xP+1]<100 && m[yP][xP+1]<100)
				   sinistra();  
				  
				if (yP-1>=0 && xP-1>=0 && yP+1<base.getRighe())   
				if (m[yP][xP-1]<100 && m[yP+1][xP-1]<100 && m[yP-1][xP]<100 && m[yP][xP+1]>100)
				  	destra(); 	
				base.disegnaDisegno();
		break;
		
		case 8:  ruota(3); base.disegnaDisegno(); break; //Lgd
		case 9:  ruota(3); base.disegnaDisegno(); break; //Lgs
		case 10:  //lpd
		case 11:  //Lps
		case 12: if (!ruota(2)) //Sp
					{ if (sinistra()) 
						{ruota(2); destra();}
					  else {destra(); ruota(2); sinistra();}
					}
				 base.disegnaDisegno();
	    break; //Sp
		case 14: ruota(3); base.disegnaDisegno(); break; //Tg
		}	
	}
*/


 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ TETRIS2: TETRIS & PEZZI STRANI @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

 // la classe tetris2: uguale al tetris con in più alcuni pezzi... strani!


class Tetris2 extends Tetris

{
	public Tetris2(Base b)
	{	super(b);	}

	protected void anteprimaPezzo()
	{  
	  anteprimaPezzo=(int)(Math.random()*15+1);
	  base.disegnaAnteprima(); 
	  anteprimaColorePezzo=(int)(Math.random()*5+1);
	}
	
	protected void generaPezzo()
	{
		pezzo = anteprimaPezzo;
		int p = pezzo;
		if (p==1)  generaQ();
		if (p==2)  generaT();
		if (p==3)  generaLd();
		if (p==4)  generaS();
		if (p==5)  generaZd();
		if (p==6)  generaLs();
		if (p==7)  generaZs();
		if (p==8)  generaLgd();
		if (p==9)  generaLgs();
		if (p==10) generaLpd();
		if (p==11) generaLps();
		if (p==12) generaSp();
		if (p==13) generaSpp();
		if (p==14) generaTg();
		if (p==15) generaX();				
		
		base.disegnaDisegno();	
		
		//prepara il prossimo pezzo
		anteprimaPezzo();
	}
	
	protected boolean generaX() //pezzo a forma di croce
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[1][3]!=0 || m[2][4]!=0 || m[1][5]!=0) flag = false;		
		m[0][4]=m[1][4]=m[1][3]=m[2][4]=m[1][5]=anteprimaColorePezzo+100; 
		base.setMat(m); xP=4; yP=1; pezzo=15;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean generaTg() //pezzo a forma di T più grande
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[2][3]!=0 || m[2][4]!=0 || m[2][5]!=0) flag = false;		
		m[0][4]=m[1][4]=m[2][3]=m[2][4]=m[2][5]=anteprimaColorePezzo+100; 
		base.setMat(m); xP=4; yP=1; pezzo=14;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean generaSpp() //pezzo a forma quadratino piccolo
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0) flag = false;		
		m[0][4]=anteprimaColorePezzo+100; base.setMat(m); xP=4; yP=0; pezzo=13;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean generaSp() //pezzo lungo 2 quadratini
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[0][5]!=0) flag = false;		
		m[0][4]=m[0][5]=anteprimaColorePezzo+100;
		base.setMat(m); xP=4; yP=0; pezzo=12;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean generaLbs() //pezzo a forma di L più piccolo con sporgenza in basso a sinistra
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[1][3]!=0) flag = false;		
		m[0][4]=m[1][4]=m[1][3]=anteprimaColorePezzo+100;
		base.setMat(m); xP=4; yP=1; pezzo=11;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean generaLps() //pezzo a forma di L più piccolo con sporgenza in basso a destra
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[1][5]!=0) flag = false;		
		m[0][4]=m[1][4]=m[1][5]=anteprimaColorePezzo+100;
		base.setMat(m); xP=4; yP=1; pezzo=10;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean generaLpd() //pezzo a forma di L più piccolo con sporgenza in basso a destra
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[1][5]!=0) flag = false;		
		m[0][4]=m[1][4]=m[1][5]=anteprimaColorePezzo+100;
		base.setMat(m); xP=4; yP=1; pezzo=10;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean generaLgs() //pezzo a forma di L con uno sbecco in basso a sinistra
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[0][5]!=0 || m[2][4]!=0 || m[2][3]!=0) flag = false;		
		m[0][4]=m[1][4]=m[0][5]=m[2][4]=m[2][3]=anteprimaColorePezzo+100;
		 base.setMat(m); xP=4; yP=1; pezzo=9;	
		if (!flag) endGame();
		return (flag);
	}
	
	protected boolean generaLgd() //pezzo a forma di L con uno sbecco in basso a sinistra
	{
		boolean flag = true;
		int m[][]= base.getMat();
		if (m[0][4]!=0 || m[1][4]!=0 || m[0][3]!=0 || m[2][4]!=0 || m[2][5]!=0) flag = false;		
		m[0][4]=m[1][4]=m[0][3]=m[2][4]=m[2][5]=anteprimaColorePezzo+100;
		base.setMat(m); xP=4; yP=1; pezzo=8;	
		if (!flag) endGame();
		return (flag);
	}
}

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ TETRISRIGA: ABBATTI L'ULTIMA RIGA @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 
 //regole: uguale a tetris ma lo scopo è abbattere l'ultima riga (e poi si alza il livello)

class TetrisRiga extends Tetris

{
	public TetrisRiga(Base b)
	{	super(b);	}

	protected void distruggiRiga(int riga)
	{
		int [][] m = base.getMat();
		
		for (int i=riga-1; i>=0; i--)
			for (int j=0; j<base.getColonne(); j++)
				m[i+1][j]=m[i][j];
		
		base.setMat(m);
		base.disegnaDisegno();
		base.playRiga();
		
		if (riga==base.getRighe()-1) 
			{
			 base.setnPunti(base.getnPunti()+400);
			 base.scriviPunteggio();	
			 
			 if (base.getIndexLivello()<12)
				{
			 	 base.setIndexLivello(base.getIndexLivello()+1);
				}
				
			 gestisciLivello();
			}
	}
	
	protected void gestisciLivello()
	{	
	  int n= (base.getRighe()-(base.getIndexLivello()+1))+1;
	  int[][] x = new int[base.getRighe()][base.getColonne()]; 
	  
	  if (n>0)
	  { for (int i=base.getRighe()-1; i>=n; i--)
	  		for (int j=0; j<base.getColonne(); j++)
	  			{	if (Math.random()>0.5)
	  				x[i][j]=(int)(Math.random()*5+1);
	  			}
	  }
	
	  x[base.getRighe()-1][(int)(base.getColonne()/2-1)]=6;
	  base.setMat(x);
	  controlloRiga();	
	}	
}

 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 //@@@@@@@@@@ TETRIS2RIGA: ABBATTI L'ULTIMA RIGA & PEZZI STRANI @@@@@@@@@@@@@@@
 //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

 //regole: uguale a tetris con pezzi strani ma lo scopo è abbattere l'ultima riga (e poi si alza il livello)
 //purtroppo non c'è l'eredità multipla in java! la classe è stata estesa da Tetris2 
 //ma ha esattamente lo stesso corpo di TetrisRiga... 

class Tetris2Riga extends Tetris2

{
	public Tetris2Riga(Base b)
	{	super(b);	}

	protected void distruggiRiga(int riga)
	{
		int [][] m = base.getMat();
		
		for (int i=riga-1; i>=0; i--)
			for (int j=0; j<base.getColonne(); j++)
				m[i+1][j]=m[i][j];
		
		base.setMat(m);
		base.disegnaDisegno();	
		base.playRiga();
		
		if (riga==base.getRighe()-1) 
			{
			 base.setnPunti(base.getnPunti()+400);
			 base.scriviPunteggio();	
			 
			 if (base.getIndexLivello()<12)
				{
			 	 base.setIndexLivello(base.getIndexLivello()+1);
				}
				
			 gestisciLivello();
			}
	}
	
	protected void gestisciLivello()
	{	
	  int n= (base.getRighe()-(base.getIndexLivello()+1))+1;
	  int[][] x = new int[base.getRighe()][base.getColonne()]; 
	  
	  if (n>0)
	  { for (int i=base.getRighe()-1; i>=n; i--)
	  		for (int j=0; j<base.getColonne(); j++)
	  			{	if (Math.random()>0.5)
	  				x[i][j]=(int)(Math.random()*5+1);
	  			}
	  }
	
	  x[base.getRighe()-1][(int)(base.getColonne()/2-1)]=6;
	  base.setMat(x);
	  controlloRiga();	
	}	
}