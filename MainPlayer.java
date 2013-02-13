import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;


public class MainPlayer {     //the class for the user controlled player
  
	Image playerImage; //the image that is associated with the object and is drawn to the screen
	int x,y;          //x and y coordinates for drawing the image on the screen
	int imageWidth,imageHeight; //width and height of the playerImage image
	public MainPlayer(){
	
			
	}
	
	public int moveLeft(){
		
		x = x-1;	
		return x;
	}
	
	public int moveRight(){
		
		x= x+1;
		return y;
	}
	
	
	public void renderScreen(ArrayList<StaticObjects> s,ArrayList<ReactiveObjects> r){
	
	}
	

}
	

