import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;


public class MainPlayer {     //the class for the user controlled player
  
	Image playerImage; //the image that is associated with the object and is drawn to the screen
	int x,y;          //x and y coordinates for drawing the image on the screen
	int imageWidth,imageHeight; //width and height of the playerImage image
	Rectangle rectangle;
	public MainPlayer(int xvalue, int yvalue, Image i){
		this.x=xvalue;
		this.y=yvalue;
		this.playerImage=i;
		this.rectangle=new Rectangle(xvalue-1,yvalue-1,i.getHeight(null)+2,i.getWidth(null)+2);
	
	}
	
	public void renderScreen(MainPlayer p,ArrayList<StaticObjects> s,ArrayList<ReactiveObjects> r,Graphics g){
	 //draw everything on the screen
	}
	
	public boolean checkCollisionRight(MainPlayer p,ArrayList<StaticObjects> s, ArrayList<ReactiveObjects> r){
		for(int i=0;i<s.size();i++)
		{
			if(p.x<s.get(i).x)
			  if(p.rectangle.intersects(s.get(i).rectangle))
				  return true;
	    }
		for(int i=0;i<s.size();i++)
		{
			if(p.x<r.get(i).x)
			  if(p.rectangle.intersects(r.get(i).rectangle))
				  return true;
	    }
		return false;
	}
	
	public boolean checkCollisionLeft(MainPlayer p,ArrayList<StaticObjects> s, ArrayList<ReactiveObjects> r){
		for(int i=0;i<s.size();i++)
		{
			if(p.x>s.get(i).x)
			  if(p.rectangle.intersects(s.get(i).rectangle))
				  return true;
	    }
		for(int i=0;i<s.size();i++)
		{
			if(p.x>r.get(i).x)
			  if(p.rectangle.intersects(r.get(i).rectangle))
				  return true;
	    }
		return false;
	}
	
	public boolean checkCollisionJump(MainPlayer p,ArrayList<StaticObjects> s, ArrayList<ReactiveObjects> r){
		for(int i=0;i<s.size();i++)
		{
			if(p.y>s.get(i).y)
			  if(p.rectangle.intersects(s.get(i).rectangle))
				  return true;
	    }
		for(int i=0;i<s.size();i++)
		{
			if(p.y>r.get(i).y)
			  if(p.rectangle.intersects(r.get(i).rectangle))
				  return true;
	    }
		return false;
	}
	
	
	public void moveRight(ArrayList<StaticObjects> s, ArrayList<ReactiveObjects> r){
	if(checkCollisionRight(this,s,r)==false)
		{
			//there is no collision 1 pixel on the right
			//move to the right
		}
		
	}

	public void moveLeft(ArrayList<StaticObjects> s, ArrayList<ReactiveObjects> r){
		if(checkCollisionLeft(this,s,r)==false)
			{
				//there is no collision 1 pixel on the left
				//move to the left
			}
			
		}
	
	public void jump(ArrayList<StaticObjects> s, ArrayList<ReactiveObjects> r){
		if(checkCollisionJump(this,s,r)==false)
			{
				//there is no collision 1 pixel up
				//jump hint-to control the speed of the jump use thread.sleep(miliseconds);
			}
			
		}
	
}
	

