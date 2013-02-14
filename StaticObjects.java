import java.awt.Image;
import java.awt.Rectangle;


public class StaticObjects {     //the class for the static objects on the screen
    
	Image objectImage; //image for the object
	int x,y; //x and y coordinate for drawing the image on the screen
	int imageWidth,imageHeight; //width and height of the image of the object
	Rectangle rectangle;
	public StaticObjects(int xvalue, int yvalue, Image i){		//constructor
		this.x=xvalue;
		this.y=yvalue;
		this.objectImage=i;
		this.rectangle=new Rectangle(xvalue,yvalue,i.getHeight(null),i.getWidth(null));
	}
	
	public void destroy(){
		//call this function when objects will be destroyed	
	}
	
}

