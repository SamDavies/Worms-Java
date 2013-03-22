import java.awt.Rectangle;


public class Circle {
	
	int x;
	int y;
	double radius;

	public Circle(int x, int y, double radius){
		this.x=x;
		this.y=y;
		this.radius=radius;
	}
	
	
	public boolean intersects(Rectangle r){
		double a = r.x;
		double b = r.y;
		double c = r.height;
		double d = r.width;	
		
		double distTL = Math.sqrt((x-a) * (x-a) + (y-b) * (y-b));
		double distTR = Math.sqrt(((x+c)-a) * ((x+c)-a) + (y-b) * (y-b));
		double distBL = Math.sqrt((x-a) * (x-a) + ((y+d)-b) * ((y+d)-b));
		double distBR = Math.sqrt(((x+c)-a) * ((x+c)-a) + ((y+d)-b) * ((y+d)-b));
		
		if(distTL<=radius & distTR<=radius & distBL<=radius & distBR<=radius){
			return true;
		}else{
			return false;
		}
	}
}
