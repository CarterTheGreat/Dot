package main;

public class Population {

	static int runningCount = 0;
	static String running ="";
	static double avgFitBest = 0;
	static double totalMovesBest = 6000000;
	static int goalsBest = 0;
	
	static int mutateChance = 10;
	static double totalMoves;
	static double avgFit = 0;
	static int goals = 0;
	static int deaths = 0;
	static int popSize = 1000;
	
	static Dot[] population = new Dot[popSize];
	static int[] inheritedStep = new int[(int) Dot.movesMax];
	
//---------------------------------------------------------------------------	
	static void build(){
		for(int i=0; i<popSize;i++) {
			population[i] = new Dot(250,250, true);
		}
	}
//---------------------------------------------------------------------------
	static boolean checkMoves() {
		for(int i=0; i<popSize;i++) {	
			if(population[i].moves ==Dot.movesMax|| goals == popSize) {
				return true;
			}
		}
		return false;
	}
//---------------------------------------------------------------------------
	static void reproduce(){
		averageFit();
		
		
		
		
		for(int i=0; i<popSize;i++) {
			int mutateRate = 0 + (int)(Math.random() * ((1000 - 0) + 1));
			if (population[i].isFit(i)>=avgFit) {
				for(int j=0; j<Dot.movesMax;j++) {
					inheritedStep[j] = population[i].step[j];
				}
				population[i]=new Dot(250,250, true);
				for(int j=0; j<Dot.movesMax;j++) {
					population[i].step[j]= inheritedStep[j];
					if(mutateRate<5) {
						int p = 0 + (int)(Math.random() * ((7 - 0) + 1));
						population[i].step[j]= p;
					}
				}
			}else {

				population[i]=new Dot(250,250, true);
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
		totalMoves =0;
		for(int i=0; i<popSize;i++) {
			totalMoves+= population[i].fitMoves;
			
		}
		avgFit = (popSize*Dot.movesMax)/totalMoves;
		System.out.println("Total moves: "+totalMoves);
		System.out.println("Average fitness: "+avgFit);
		System.out.println("Goals: "+Dot.goals);
		
		if(goalsBest < Dot.goals) {
			goalsBest = Dot.goals;
		}
		if(avgFitBest< avgFit) {
			avgFitBest= avgFit;
		}
		if(totalMovesBest> totalMoves) {
			totalMovesBest = totalMoves;
		}
		
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
		
		MyJFrame.label.setText("<html>"+ running+ " <br/> "+" Best | Goals: "+goalsBest+" |Total moves: "+totalMovesBest+" | Average fitness: "+avgFitBest+"<br/>"+"This | Goals: "+Dot.goals+" | Total moves: "+totalMoves+ " | Average fitness: "+avgFit+"<html>");
		Dot.goals = 0;
		
		return avgFit;
	}
	
}