package main;

public class Dot {

	static double movesMax = 1200;
	
	boolean isAlive;
	boolean isFirstGen;
	boolean reachedGoal= false;
	
	double moves= 0;
	double movesReached;
	double fitVal;
	double fitMoves= 0;
	
	int[] step = new int[(int) movesMax];
	
	int x;
    int y;
    
//---------------------------------------------------------------------------	
   public Dot(int x, int y, boolean isAlive, boolean isFirstGen) {
	   this.x= x;
	   this.y= y;
	   this.isAlive = isAlive;
	   this.isFirstGen = isFirstGen;
	   
	   if(isFirstGen) {
		   //Builds random array of first moves if first gen
		   for(int i = 0; i<movesMax; i++) {
				int r = 0 + (int)(Math.random() * ((7 - 0) + 1));
				step[i] = r;
		   }
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
		
	//move processing makes sure directions are followed in order
		
    	if(moves<movesMax) {
	    	move(step[(int)moves]);
	    	moves++;
	    	fitMoves++;
    	}
    	
    }
//---------------------------------------------------------------------------	    
	public void move(int dir) {
		
		//Dots act out next move in direction here 
		
		try {Thread.sleep(0,0);} catch (InterruptedException e) {e.printStackTrace();}
	
		//Dot reached goal
		if(x>=EvolutionPanel.goalX && x<=EvolutionPanel.goalX+5 && y>=EvolutionPanel.goalY && y<=EvolutionPanel.goalY+5) {
			reachedGoal = true;
			movesReached = moves;
			//moves= movesMax;
			Population.goals++;
			
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
		
		//For Death by Wall
		//To change - comment out isAlive and deaths++ in lines below and "For Death by Wall" section in EvolutionPanel
		
		if(x>EvolutionPanel.panelX) {
			x=EvolutionPanel.panelX-5;
			//isAlive = false;
			//Population.deaths++;
		}
		if(x<0) {
			x=0;
			//isAlive = false;
			//Population.deaths++;
		}
		if(y>EvolutionPanel.panelY) {
			y=EvolutionPanel.panelY-5;
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
	double fit() {
		//Fitness function based on moves to reach goal
		//Failure to reach goal results in 0 (lowest possible) fitness
		
		if(reachedGoal) {
			
			return fitMoves;
			
		} 
		else {
		return movesMax;
		}
		
		
	}
//---------------------------------------------------------------------------	
	double dist() {
	//Distance to goal based fitness
			if(reachedGoal) {
				return 0;
			}
			else {
				double distance = Math.sqrt(Math.pow((EvolutionPanel.goalX-x), 2) + Math.pow((EvolutionPanel.goalY-y), 2));
				
				return distance/5;
				
			}
	}		
	
}