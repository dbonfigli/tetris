 import java.util.Vector;
 
 public class Snake extends Tetris
 {
 	
 	private Base base;
 	private Vector corpo;
	public int direzione; 		
 	
 		
 	public Snake(Base b)
	{
		base = b;
		
		corpo = new Vector();
			
		corpo.add( new Osso(1,1) );
		corpo.add( new Osso(1,2) );
		corpo.add( new Osso(1,3) );
			
		direzione = 6;
		
		base.setTimer(400);
		
	}
				
		protected class Osso
		{
			public int x;
			public int y;
			public int colore;
		
			public Osso(int x, int y, int colore)
				{
		   			this.x= x;
		   			this.y= y;
		   			this.colore = colore; 
				}
		
			public Osso(int x, int y)
				{
		   			this.x= x;
		   			this.y= y;
		   			this.colore = 102; 
				}	
		}
		
		
	public void avanti()
	{
		switch (direzione)
			{
				case 12: su();			break;
				case  3: destra();		break;
				case  6: giu();			break;
				case  9: sinistra();	break;
			}
		disegna();	
	}


	public void disegna()
	{
			
		int[][] mat = base.getMat();
		
		Osso o;
		
		for(int i=0; i<corpo.size(); i++)
			{ o = (Osso) corpo.get(i);
			  mat[o.x][o.y] = 102;
			}	  
		
		base.setMat(mat);	
	}		
			
	public void su()
	{
		int[][] mat = base.getMat();
			
		Osso o = (Osso) corpo.firstElement();
			
		if (o.y+1<20)
			{
				switch (mat[o.x][o.y+1])
					{ 
						case 102: endGame();	
						break;
						case   0: corpo.add( new Osso(o.x, o.y+1) );
								  corpo.remove( corpo.lastElement() );
						break;
						case 106: corpo.add( new Osso(o.x, o.y+1) );
						break;
					} 
			}			
		else endGame(); 
	}
	
	public void giu()
	{
		int[][] mat = base.getMat();
			
		Osso o = (Osso) corpo.firstElement();
			
		if (o.y-1>=0)
			{
				switch (mat[o.x][o.y-1])
					{ 
						case 102: endGame();	
						break;
						case   0: corpo.add( new Osso(o.x, o.y-1) );
								  corpo.remove( corpo.lastElement() );
						break;
						case 106: corpo.add( new Osso(o.x, o.y-1) );
						break;
					} 
			}			
		else endGame(); 
	}

	public void destra()
	{
		int[][] mat = base.getMat();
			
		Osso o = (Osso) corpo.firstElement();
			
		if (o.x+1<10)
			{
				switch (mat[o.x+1][o.y])
					{ 
						case 102: endGame();	
						break;
						case   0: corpo.add( new Osso(o.x+1, o.y) );
								  corpo.remove( corpo.lastElement() );
						break;
						case 106: corpo.add( new Osso(o.x+1, o.y) );
						break;
					} 
			}			
		else endGame(); 
	}

	public void sinistra()
	{
		int[][] mat = base.getMat();
			
		Osso o = (Osso) corpo.firstElement();
			
		if (o.x-1>=0)
			{
				switch (mat[o.x-1][o.y])
					{ 
						case 102: endGame();	
						break;
						case   0: corpo.add( new Osso(o.x-1, o.y) );
								  corpo.remove( corpo.lastElement() );
						break;
						case 106: corpo.add( new Osso(o.x-1, o.y) );
						break;
					} 
			}			
		else endGame(); 
	}

	public void endGame() 
	{  
	  base.endGame();
	}
	
	public void manageKeyboard(int t)
	{         	
		switch (t) 
		{	
		case 1006:	direzione = 9;		//left arrow
		break;		
		case 1004:	direzione = 12;		//up arrow
		break;
		case 1007:	direzione = 3;		//right arrow
		break;	
		case 1005:	direzione = 6;		//down arrow
		}
	}

	public void startGame()
	{ 
	  base.startGame();
	  
	  //control starting speed and level
	  //manageSpeed();
	  //manageLevel();
	  
	  //produce the first piece
	  //piecePreview();
	  //producePiece();
    }

	public void endGame() 
	{  
	  base.endGame();
	}


}	