package main;

public class Obstacle {

	int x;
	int y;
	int width;
	int height;
	int xMin;
	int xMax;
	int yMin;
	int yMax;
	
	public Obstacle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.xMin = x;
		this.xMax = x+width;
		
		this.yMin = y;
		this.yMax = x-height;
	}
	
	
	
	
}
