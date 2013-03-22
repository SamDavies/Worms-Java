import java.awt.Point;
import java.awt.Rectangle;


public class RoundCollision {


	
		int x;
		int y;
		double radius;

		public RoundCollision(int x, int y, double radius){
			this.x=x;
			this.y=y;
			this.radius=radius;
		}	
			
	
	
	public boolean intersects(Rectangle r) {
		int a = r.x;
		int b = r.y;
		int c = r.height;
		int d = r.width;

		Point centre = new Point(x, y);
		Point top = new Point(x, b);
		Point bottom = new Point(x, b + c);
		Point left = new Point(a, y);
		Point right = new Point(a + d, y);

		double distT = top.distance(centre);
		double distB = bottom.distance(centre);
		double distL = left.distance(centre);
		double distR = right.distance(centre);

		if ((distT <= radius) & (distL <= radius)) {
			return true;
		} else if ((distT <= radius) & (distR <= radius)) {
			return true;
		} else if ((distB <= radius) & (distL <= radius)) {
			return true;
		} else if ((distB <= radius) & (distR <= radius)) {
			return true;
		}else{return false;}

		
	}
}
