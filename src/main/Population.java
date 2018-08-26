package main;

public class Population {

	static String running ="";
	
	static int generation = 0;
	static int runningCount = 0;
	//static double avgFitBest = 0;
	static double avgDistBest = 9999999;
	static double totalMovesBest = 999999999;
	static int goalsBest = 0;
	
	static double totalMoves;
	//static double avgFit = 0;
	
	static double totalDist;
	static double avgDist = 0;
	
	static int mutateChance = 4;
	static int goals = 0;
	static int deaths = 0;
	
	static int popSize = 10000;
	
	
	
	//Spawn position
	static int x = 20;
	static int y = 400;
	//
	
	static Dot[] population = new Dot[popSize];
	static int[] inheritedStep = new int[(int) Dot.movesMax];
	
//---------------------------------------------------------------------------	
	static void build(){
		//Builds generation 0 with random directions
		
		for(int i=0; i<popSize;i++) {
			population[i] = new Dot(x,y,true,true);
		}
	}
//---------------------------------------------------------------------------
	static boolean checkMoves() {
		
		//Checks if dot has used every move in array of directions
		for(int i=0; i<popSize;i++) {	
			if(population[i].moves ==Dot.movesMax || goals + deaths == popSize) {
				System.out.println("Deaths: " +deaths);
				
				return true;
			}
		}
		return false;
	}
//---------------------------------------------------------------------------
	static void reproduce(){
		
		//Top % of population reproduce by passing on their step directions to the next generation,
		//bottom % die off without passing directions
		
		averageDist();
		
		for(int i=0; i<popSize;i++) {
			int mutateRate = 0 + (int) (Math.random() * ((1000 - 0) + 1));
			if (population[i].dist(i)<=avgDist) {
				for(int j=0; j<Dot.movesMax;j++) {
					inheritedStep[j] = population[i].step[j];
				}
				population[i]=new Dot(x,y, true,false);
				for(int j=0; j<Dot.movesMax;j++) {
					population[i].step[j]= inheritedStep[j];
					if(mutateRate<mutateChance) {
						int p = 0 + (int)(Math.random() * ((7 - 0) + 1));
						population[i].step[j]= p;
					}
				}
			}else {

				population[i]=new Dot(x,y, true,false);
				for(int j=0; j<Dot.movesMax;j++) {
					population[i].step[j]= inheritedStep[j];
					if(mutateRate<mutateChance) {
						int p = 0 + (int)(Math.random() * ((7 - 0) + 1));
						population[i].step[j]= p;
					}
				}
			}
				
		}
		
	}	
//---------------------------------------------------------------------------
	static double averageFit() {
		
		//Average fit decides well... average fitness to compare to other dots for reproduction
		
		//Move based
		
		for(int i=0; i<popSize;i++) {
			totalMoves+= population[i].fitMoves;
			
			
		}
		double avgFit = (popSize*Dot.movesMax)/(totalMoves);
		
		//analytics();
		
		return avgFit;

	}
//---------------------------------------------------------------------------	
	static double averageDist() {
		
		//Used for analytics
			totalMoves =0;
			for(int i=0; i<popSize;i++) {
				totalMoves+= population[i].fitMoves;
			}
		//End analytics section	

		totalDist = 0;
		for(int i=0; i<popSize;i++) {
			totalDist+= population[i].dist(i);
		}
		
		avgDist = totalDist/popSize;

		System.out.println("Total dist: "+totalDist + "\nAvg Dist: "
		+avgDist);
		
		analytics();
		
		return avgDist;
	}
//---------------------------------------------------------------------------	
	static void analytics() {
		
		//Outputs analytics in bottom of frame 
		
		System.out.println("Total moves:     "+totalMoves);
		System.out.println("Average distance: "+avgDist);
		System.out.println("Goals:           "+Population.goals);
		System.out.println("------------------------------------");
		
		if(goalsBest < Population.goals) {
			goalsBest = Population.goals;
		}
		if(avgDistBest> avgDist) {
			avgDistBest= avgDist;
		}
		if(totalMovesBest> totalMoves) {
			totalMovesBest = totalMoves;
		}
		
		//Running refers to the dots in the bottom left at the top of anylitics that show that the process is running
		
		switch(runningCount) {
			case 0:{
				running = "";
				runningCount++;
				break;
			}
			case 1:{
				running = ".";
				runningCount++;
				break;
			}
			case 2:{
				running = "..";
				runningCount++;
				break;
			}
			case 3:{
				running = "...";
				runningCount = 0;;
				break;
			}
		}
		generation++;
		MyJFrame.label.setText("<html>"+ running+ " <br/> "+"Generation: "+generation+"<br/> Best | Goals: "+goalsBest+"/"+popSize+" | Total moves: "+totalMovesBest+" | Average moves: "+totalMovesBest/popSize+" | <br/> Average Distance: "+avgDistBest+"<br/>"+"Last | Goals: "+Population.goals+"/"+popSize+"| Total moves: "+totalMoves+ " | Average moves: "+totalMoves/popSize+" | <br/> Average Distance: "+avgDist+"<html>");
		//reset goals for next generation here instead of the board reset in paint
		Population.goals = 0;
		
	}
}