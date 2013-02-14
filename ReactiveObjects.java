import java.awt.Image;
import java.awt.Rectangle;


public class ReactiveObjects {

	Image[] annimationImages;
	Image currentImage;
	int x,y; //x and y coordinate for drawing the image on the screen
	int imageWidth,imageHeight; //width and height of the image of the object
	Rectangle rectangle;
	public ReactiveObjects(int xvalue, int yvalue, Image[] i){		//constructor
		this.x=xvalue;
		this.y=yvalue;
		this.annimationImages=i;
		this.currentImage=i[0];
		this.rectangle=new Rectangle(xvalue,yvalue,i[0].getWidth(null),i[0].getHeight(null));
	}
	
	public void destroy(){
	//call this function when objects will be destroyed	
	}

}
	
	

