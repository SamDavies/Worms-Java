import java.awt.Image;


public class ReactiveObjects {

	Image[] annimationImages;
	Image currentImage;
	int x,y; //x and y coordinate for drawing the image on the screen
	int imageWidth,imageHeight; //width and height of the image of the object
	
	public ReactiveObjects(){
	
	}
	
	public static int[] missileMovement(int x, int y, int time){ //function which calculates the new position after a time
		
		int t = time;	//starts a clock to update the missile
		double u = 10; //Initial speed of missile
		double a = -10; //acceleration due to gravity
		
				
		x = x + 10; // creates a constant horizontal speed 
					
		double s = u*t + 0.5 * a * t * t; //equation for vertical position at a given time
				
		y = y + (int)Math.round(s); //old position plus change in position
		
		int[] xy = {x, y}; //out put coordinates
		
		return xy;				
		
	 }
	
	
}
