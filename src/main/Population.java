package main;

public class Population {

	static String running ="";
	
	static int generation = 0;
	static int runningCount = 0;
	static double avgFitBest = 0;
	static double avgDistBest = 9999999;
	static double totalMovesBest = 999999999;
	static int goalsBest = 0;
	
	static double totalMoves;
	static double avgFit = 0;
	static double avgMoves = Dot.movesMax;
	static double bestFit = 999999999;
	static double bestFitEver = 999999999;
	static double bestDist = 99999999;
	
	static double totalDist;
	static double avgDist = 0;
	
	static int mutateChance = 20;
	
	static int goals = 0;
	static int deaths = 0;
	
	static int popSize = 10000;
	
	
	
	//Spawn position
	static int x = 20;
	static int y = 500;
	//
	
	static Dot[] population = new Dot[popSize];
	static int[] inheritedStep = new int[(int) Dot.movesMax];
	
//---------------------------------------------------------------------------	
	static void build(){
		//Builds generation 0 with random directions
		
		for(int i=0; i<popSize;i++) {
			population[i] = new Dot(x,y,true,true);
		}
		
		System.out.println("Population built");
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
		
		
		
		bestFit();
		bestDist();
		analytics();
		
		double fitVal = bestFit;
		boolean fitChoice;
		
		
		
		//fitChoice true when a dot has reached goal
		if(fitVal < 1200 && goals != popSize) {
			fitChoice = true;
		} else fitChoice = false;
		
				
		if(fitChoice) {	
			for(int i=0; i<popSize;i++) {
				if (population[i].fit() == bestFit) {
					for(int j=0; j<Dot.movesMax;j++) {
						inheritedStep[j] = population[i].step[j];
					}
				}	
			}
		}else {
			for(int i=0; i<popSize;i++) {
				if (population[i].dist() == bestDist) {
					for(int j=0; j<Dot.movesMax;j++) {
						inheritedStep[j] = population[i].step[j];
					}
				}
			}
		}	
		
		
		
		for(int i=0;i<popSize;i++) {
			//Builds new dot
			population[i]=new Dot(x,y, true,false);
							
			for(int j=0; j<Dot.movesMax;j++) {
				//Sets dot steps to inherited steps
				population[i].step[j]= inheritedStep[j];
				
				int mutateRate = 0 + (int) (Math.random() * ((1000 - 0) + 1));//     1000
				
				//System.out.println("Steps assigned from inheritance");
				if(mutateRate<mutateChance) {
					//Mutates random steps to random direction
					int p = 0 + (int)(Math.random() * ((7 - 0) + 1));
					population[i].step[j]= p;
					
					//System.out.println("Mutation processed");
				}
			}
		}
		
		
		
	}	
//---------------------------------------------------------------------------
	static double bestFit() {
		
		//Average fit decides well... average fitness to compare to other dots for reproduction
		
		
		for(int i=0; i<popSize;i++) {
			if(bestFit > population[i].fit()) {
				bestFit = population[i].fit();
				System.out.println("best fit for loop if");
			}
			//System.out.println("Best fit for loop");
		}
		
		//analytics();
		
		System.out.println("Best fit: "+bestFit);
		return bestFit;

	}
//---------------------------------------------------------------------------	
	static double bestDist() {
		
		bestDist = 9999999;
		
		for(int i=0; i<popSize;i++) {
			if(bestDist > population[i].dist()) {
				bestDist = population[i].dist();
				//System.out.println("best dist for loop");
			}
			totalDist += population[i].dist();
			totalMoves += population[i].moves;
			//Dot.movesMax = totalMoves/popSize;
		}
		
		avgDist = totalDist/popSize;
		avgMoves = totalMoves/popSize;
		//analytics();
		
		System.out.println("Best dist: "+bestDist);
		return bestDist;
	}
//---------------------------------------------------------------------------	
	static void analytics() {
		
		//Outputs analytics in console && frame south
		
		//double bestFitPossible = (Math.sqrt(Math.pow((MyJPanel.goalX-x), 2) + Math.pow((MyJPanel.goalY-y), 2)))/5;
		
		
		System.out.println("Best Fit:     "+bestFit);
		//System.out.println("Best Fit Possible: "+bestFitPossible);
		System.out.println("Average distance: "+avgDist);
		System.out.println("Goals:           "+Population.goals);
		System.out.println("------------------------------------");
		
		
		if(goalsBest < Population.goals) {
			goalsBest = Population.goals;
		}
		if(avgDistBest > avgDist) {
			avgDistBest= avgDist;
		}
		if(totalMovesBest > totalMoves) {
			totalMovesBest = totalMoves;
		}
		if(bestFitEver > bestFit) {
			bestFitEver = bestFit;
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
		Frame.label.setText("<html>"+ running+ " <br/> "+"Generation: "+generation+"<br/> Best | Goals: "+goalsBest+"/"+popSize+" | Least moves: "+bestFitEver+" | Average moves: "+totalMovesBest/popSize+" | <br/> Average Distance: "+avgDistBest+"<br/>"+"Last | Goals: "+Population.goals+"/"+popSize+"| Least moves: "+bestFit+ " | Average moves: "+totalMoves/popSize+" | <br/> Average Distance: "+avgDist+"<html>");
		//reset goals for next generation here instead of the board reset in paint
		Population.goals = 0;
		
	}
}