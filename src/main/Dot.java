package main;

public class Dot {
	
	static int goals;
	boolean isAlive;
	boolean reachedGoal= false;
	double moves= 0;
	double fitVal;
	double fitMoves= 0;
	static double movesMax = 3000;
	int[] step = new int[(int) movesMax];
	double movesReached;
	int x;
    int y;
//---------------------------------------------------------------------------	
   public Dot(int x, int y, boolean isAlive) {
	   this.x= x;
	   this.y= y;
	   this.isAlive = isAlive;
	   
	   for(int i = 0; i<movesMax; i++) {
			int r = 0 + (int)(Math.random() * ((7 - 0) + 1));
			step[i] = r;
		}
   }
//---------------------------------------------------------------------------    
    public boolean isAlive() {
    	return isAlive;
    }
//---------------------------------------------------------------------------	    
    public boolean reachedGoal() {
    	return reachedGoal;
    }
//---------------------------------------------------------------------------	
	public void movePro() {
		
    	if(moves<movesMax) {
	    	move(step[(int)moves]);
	    	moves++;
	    	fitMoves++;
    	}
    	
    }
//---------------------------------------------------------------------------	    
	public void move(int dir) {
		
		try {Thread.sleep(0,0);} catch (InterruptedException e) {e.printStackTrace();}
	
		
		if(x>=250 && x<=255 && y>=20 && y<=25) {
			reachedGoal = true;
			movesReached = moves;
			moves= movesMax;
			goals++;
			//System.out.println("Goals: "+ Population.goals);
			
		}else {
		switch (dir) {
	        case 0:
	            x+=5;
	            break;
	        case 1:
	            x-=5;
	            break;
	        case 2:
	            y+=5;
	            break;
	        case 3:
	            y-=5;
	            break;
	        case 4:
	        	x-=5;
	            y-=5;
	            break;
	        case 5:
	        	x+=5;
	            y-=5;
	            break; 
	        case 6:
	        	x-=5;
	            y+=5;
	            break; 
	        case 7:
	        	x+=5;
	            y+=5;
	            break;     
		} 
		}
		
		if(x>495) {
			x=495;
			//isAlive = false;
			//Population.deaths++;
		}
		if(x<0) {
			x=0;
			//isAlive = false;
			//Population.deaths++;
		}
		if(y>465) {
			y=465;
			//isAlive = false;
			//Population.deaths++;
		}
		if(y<0) {
			y=0;
			//isAlive = false;
			//Population.deaths++;
		}
		
	}
//---------------------------------------------------------------------------			
	double isFit(int i) {
		
		if(Population.population[i].reachedGoal) {
			fitVal = movesMax/fitMoves;
			//System.out.println("Fitval: "+fitVal);
			return fitVal;
			
		}
		return 0;
	}
	
}