import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class StaticObjects { // the class for the static objects on the screen

	Image objectImage; // image for the object
	int x, y; // x and y coordinate for drawing the image on the screen
	int imageWidth, imageHeight; // width and height of the image of the object
	Rectangle rectangle;
	boolean visible;

	public StaticObjects(int xvalue, int yvalue, int type) { // constructor
		this.x = xvalue;
		this.y = yvalue;
		visible = true;
		ImageIcon tempImageIcon;
		switch (type) {
		case 0:
			tempImageIcon = new ImageIcon("images/static/cloud.png");
			this.objectImage = tempImageIcon.getImage();
			break;
		case 1:
			tempImageIcon = new ImageIcon("images/static/crate.png");
			this.objectImage = tempImageIcon.getImage();
			break;
		case 2:
			tempImageIcon = new ImageIcon("images/static/turf.png");
			this.objectImage = tempImageIcon.getImage();
			break;		
		}

		this.rectangle = new Rectangle(xvalue, yvalue,
				this.objectImage.getWidth(null),
				this.objectImage.getHeight(null));
		this.imageHeight = this.objectImage.getHeight(null);
		this.imageWidth = this.objectImage.getWidth(null);
	}

	public void destroy() {
		// call this function when objects will be destroyed
		this.visible = false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void make() {
		this.visible = true;
	}

	public void setImage(int image) {
		ImageIcon tempImageIcon;
		
		switch (image) {
		case 1:
			tempImageIcon = new ImageIcon("images/static/parachuteLeft2.png");
			this.objectImage = tempImageIcon.getImage();
			break;
		case 2:
			tempImageIcon = new ImageIcon("images/static/parachuteLeft1.png");
			this.objectImage = tempImageIcon.getImage();
			break;
		case 3:
			tempImageIcon = new ImageIcon("images/static/parachuteCentre.png");
			this.objectImage = tempImageIcon.getImage();
			break;
		case 4:
			tempImageIcon = new ImageIcon("images/static/parachuteRight1.png");
			this.objectImage = tempImageIcon.getImage();
			break;
		case 5:
			tempImageIcon = new ImageIcon("images/static/parachuteRight2.png");
			this.objectImage = tempImageIcon.getImage();
			break;
		}
	}
	
	public void updateRectangle(){
		this.rectangle = new Rectangle(x, y,
				this.objectImage.getWidth(null),
				this.objectImage.getHeight(null));
	}
}
