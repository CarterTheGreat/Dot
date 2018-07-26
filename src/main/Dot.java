package main;

public class Dot {

	int x;
    int y;
	
   public Dot(int x, int y) {
	   this.x= x;
	   this.y= y;
	   
   }
    
    public void movePro() {
    	int r = 0 + (int)(Math.random() * ((7 - 0) + 1));
    	move(r);
    }
    
	public void move(int dir) {
		
		try {Thread.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
		
		if(x==250 && y==20) {
			System.out.println("Success");
		}
		else {
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
		if(x>500) {
			x=500;
		}
		if(x<0) {
			x=0;
		}
		if(y>500) {
			y=500;
		}
		if(y<0) {
			y=0;
		}
	}
		
		

}