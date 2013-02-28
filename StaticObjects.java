import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class StaticObjects {     //the class for the static objects on the screen
    
	Image objectImage; //image for the object
	int x,y; //x and y coordinate for drawing the image on the screen
	int imageWidth,imageHeight; //width and height of the image of the object
	Rectangle rectangle;
	boolean visible;
	public StaticObjects(int xvalue, int yvalue){		//constructor
		this.x=xvalue;
		this.y=yvalue;
		visible=true;
		ImageIcon tempImageIcon=new ImageIcon(this.getClass().getResource("brick.jpg"));
		this.objectImage=tempImageIcon.getImage();
		this.rectangle=new Rectangle(xvalue,yvalue,this.objectImage.getWidth(null),this.objectImage.getHeight(null));
		this.imageHeight=this.objectImage.getHeight(null);
		this.imageWidth=this.objectImage.getWidth(null);
	}
	
	public void destroy(){
		//call this function when objects will be destroyed	
		this.visible=false;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
}

