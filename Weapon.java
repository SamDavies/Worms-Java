import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Weapon implements ActionListener {

	Image[] annimationImages;
	Image currentImage;
	boolean launchDirectionRight, visible; // true if it is launched to the
											// right and false to the left
	int x, y; // x and y coordinate for drawing the image on the screen
	private int imageWidth, imageHeight; // width and height of the image of the
											// object
	private Rectangle rectangle;
	private Timer timer;
	private MainPlayer player;
	ArrayList<StaticObjects> staticobjects; // just pointers to the two
											// arraylists that are created
	ArrayList<ReactiveObjects> reactiveobjects; // in class MainClass
	private int[][] trajectoryIncrements; // used to store the trajectory
	private int trajectoryIndex; // this represents the index in
									// trajectoryIncrements

	// that we are currently at

	public Weapon(MainPlayer p, ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r, boolean l, double velocity, double angle) {
		trajectoryIncrements = calculateTrajectory(velocity, angle);
		visible = true;
		staticobjects = s;
		reactiveobjects = r;
		player = p;
		loadAnnimationImages();
		if (l == true) {
			x = p.x + p.playerImage.getWidth(null);
			y = p.y;
		} else {
			x = p.x - p.playerImage.getWidth(null);
			y = p.y;
		}

		launchDirectionRight = l;
		rectangle = new Rectangle(x, y, currentImage.getWidth(null),
				currentImage.getHeight(null));
		imageHeight = currentImage.getHeight(null);
		imageWidth = currentImage.getWidth(null);
		timer = new Timer(5, this);
		timer.start();
	}

	public void loadAnnimationImages() {
		ImageIcon tempImageIcon = new ImageIcon(this.getClass().getResource(
				"grenade-0.png"));
		currentImage = tempImageIcon.getImage();
	}

	public boolean checkCollisionStatic() {
		for (int i = 0; i < staticobjects.size(); i++)
			if (rectangle.intersects(staticobjects.get(i).rectangle))
				return true;
		return false;
	}

	public boolean checkCollisionReactive() {
		for (int i = 0; i < reactiveobjects.size(); i++)
			if (rectangle.intersects(reactiveobjects.get(i).rectangle))
				return true;
		return false;
	}

	public void move() {
		int[][] xyPos = trajectoryIncrements;
		int index = trajectoryIndex;

		if (launchDirectionRight == true) {
			x += xyPos[index][0];
			y -= xyPos[index][1];
		}
		if (launchDirectionRight == false) {
			x -= xyPos[index][0];
			y -= xyPos[index][1];
		}

	}

	public void destroy() {
		timer.stop();
		visible = false;
	}

	public void destroy(ReactiveObjects r) {

	}

	public int[][] calculateTrajectory(double speed, double angle) {
		double vertSpeed = speed * Math.sin(angle); 
		double t = 0.005; //timer updates every 0.005 therefore must be calculated at those intrvals
		double horrSpeed = t * speed * Math.cos(angle);

		int[][] xyPos = new int[1000][2]; //calculates x and y for 1000 intervals ([i][0] = x [i][0] = y

		xyPos[0][1] = 0;
		xyPos[0][0] = (int) Math.round(horrSpeed);

		for (int i = 1; i < 1000; i++) {

			double s = vertSpeed * t + 0.5 * -980 * i * t * i * t;
			xyPos[i][1] = (int) Math.round(s) - xyPos[i - 1][1];
			xyPos[i][0] = (int) Math.round(horrSpeed);
		}

		return xyPos;

	}

	public void updateSystemVariables(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r) {
		staticobjects = s;
		reactiveobjects = r;
	}

	public void updateRectangle() {
		this.rectangle = new Rectangle(this.x, this.y,
				currentImage.getWidth(null), currentImage.getHeight(null));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (checkCollisionStatic() == false) {
			if (checkCollisionReactive() == false) {
				if (trajectoryIndex < trajectoryIncrements.length) {
					move();
					updateRectangle();
					trajectoryIndex++;
				} else
					visible = false;
			} else
				destroy();
		} else
			destroy();

	}

}
