import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.sound.sampled.Line;
import javax.swing.Timer;
import javax.swing.ImageIcon;

public class MainPlayer implements ActionListener { // the class for the user
													// controlled player

	Image playerImage; // the image that is associated with the object and is
						// drawn to the screen
	Image[] annimationImages;
	int currentImageIndex; // the index of the current image in annimationImages
	int x, y; // x and y coordinates for drawing the image on the screen
	int jumpSpeed, gravitySpeed; // the current speeds for jump and gravity
	final int jumpSpeedBound = 10, gravitySpeedBound = 7; // this are the
															// maximum speeds
															// for jump and
															// gravity
	int playerHealth = 100;
	Rectangle playerRectangleDown, playerRectangleLeft, playerRectangleRight,
			playerRectangleJump, CollisionRectangle;
	Line2D.Double lineBottom, lineTop; // this is a Line2D object used to check
										// for collision with the bottom of the
										// screen
	boolean injump = false, directionRight = false; // true if the object faces
													// right and false if it
													// faces left
	ArrayList<StaticObjects> staticobjects; // just pointers to the two
											// arraylists that are created
	ArrayList<ReactiveObjects> reactiveobjects; // in class MainClass
	MainPlayer enemy;
	Timer timer;
	int gravityIncrment = 0;
	int walk = 1;
	int jumpQuantity;
	boolean canDoubleJump = false;
	private int grenadesAvailable = 50;
	private int missilesAvailable = 5;

	public MainPlayer(int xvalue, int yvalue, ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r, MainPlayer enemy) {
		x = xvalue;
		y = yvalue;
		jumpSpeed = 0; // this function sets the variables of this object
		gravitySpeed = 0; // it is called when the object is created
		injump = false;
		loadAnnimationImages();
		playerImage = annimationImages[0];
		playerRectangleLeft = new Rectangle(x - 5, y,
				playerImage.getWidth(null) + 5, playerImage.getHeight(null));
		playerRectangleRight = new Rectangle(x, y,
				playerImage.getWidth(null) + 5, playerImage.getHeight(null));
		playerRectangleDown = new Rectangle(x, y, playerImage.getWidth(null),
				playerImage.getHeight(null) + 2);
		CollisionRectangle = new Rectangle(x, y, playerImage.getWidth(null),
				playerImage.getHeight(null));
		ImageIcon tempImageIcon = new ImageIcon("background.jpg");
		staticobjects = s;
		reactiveobjects = r;
		lineBottom = new Line2D.Double(0.0, 600.0, 800.0, 600.0);
		lineTop = new Line2D.Double(0.0, 0.0, 800.0, 0.0);
		timer = new Timer(50, this);
		timer.addActionListener(this);
		timer.start();
	}

	public void loadAnnimationImages() {
		ImageIcon tempImageIcon;
		annimationImages = new Image[4]; // this function allocates space in the
											// memory for the images of
		tempImageIcon = new ImageIcon("worms-right.png"); // the player and
															// retrieves the
															// images from
															// the file system
		annimationImages[0] = tempImageIcon.getImage(); // images with index 0
														// and 1 are for moving
														// right and 2 and 3 are
		tempImageIcon = new ImageIcon("worms-right1.png"); // for moving left
		annimationImages[1] = tempImageIcon.getImage();
		tempImageIcon = new ImageIcon("worms-left.png");
		annimationImages[2] = tempImageIcon.getImage();
		tempImageIcon = new ImageIcon("worms-left1.png");
		annimationImages[3] = tempImageIcon.getImage();

	}

	// getters and setters
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void addX(int i) {
		this.x += i;
	}

	public void addY(int i) {
		this.y += i;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setInjump(boolean x) {
		this.injump = x;
	}

	public void setJumpSpeed(int x) {
		this.jumpSpeed = x;
	}

	public void setGravitySpeed(int x) {
		this.gravitySpeed = x;
	}

	public void setCurrentImageIndex(int x) {
		this.currentImageIndex = x;
	}

	public void setDirectionRight(boolean x) {
		this.directionRight = x;
	}

	public int getGrenadesAvailable() {
		return grenadesAvailable;
	}

	public void setGrenadesAvailable(int grenadesAvailable) {
		if (grenadesAvailable >= 0)
			this.grenadesAvailable = grenadesAvailable;
		else
			this.grenadesAvailable = 0;
	}

	public int getMissilesAvailable() {
		return missilesAvailable;
	}

	public void setMissilesAvailable(int missilesAvailable) {
		if (missilesAvailable >= 0)
			this.missilesAvailable = missilesAvailable;
		else
			this.missilesAvailable = 0;
	}

	// getters and setters

	public void hasHit(String type) {
		switch (type) {
		case "gold":
			this.grenadesAvailable += 2;
			this.missilesAvailable += 5;
			break;
		case "diamond":
			this.grenadesAvailable += 5;
			this.missilesAvailable += 10;
			break;
		case "heart":
			if (this.playerHealth <= 80)
				this.playerHealth += 20;
			else
				this.playerHealth = 100;
			break;
		}

	}

	public void getsHit(int damage) {
		this.playerHealth -= damage;
		if (this.playerHealth < 0)
			this.playerHealth = 0;
	}

	public boolean checkCollisionRight(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r) {
		if (enemy != null)
			if (playerRectangleRight.intersects(enemy.CollisionRectangle))
				return true;
		for (int i = 0; i < s.size(); i++) {
			if (playerRectangleRight.intersects(s.get(i).rectangle)) {

				// this function checks for collision
				return true; // to the right of the player
			} // it uses playerRectangleRight rectangle
		} // which extends to the right of the player
		for (int i = 0; i < r.size(); i++) // we have a separate loop for every
											// type of
		{ // arraylist
			if (playerRectangleRight.intersects(r.get(i).rectangle)) {

				// new
				Rectangle temp = new Rectangle(x, y,
						playerImage.getWidth(null) + 5,
						playerImage.getHeight(null) - 8);
				boolean sum = true;
				for (int j = 0; j < r.size(); j++) {

					if (temp.intersects(r.get(j).rectangle)) {
						sum = sum & false;
						return true;
					}
				}
				if (sum) {
					this.addX(1);
					this.addY(-8);
					this.updateRectangle();
				}
				// new

				return true;
			}
		}
		return false;
	}

	public boolean checkCollisionLeft(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r) {
		if (enemy != null)
			if (playerRectangleLeft.intersects(enemy.CollisionRectangle))
				return true;
		for (int i = 0; i < s.size(); i++) { // same as checkCollisonRight
			if (playerRectangleLeft.intersects(s.get(i).rectangle)) {

				return true;
			}
		}
		for (int i = 0; i < r.size(); i++) {
			if (playerRectangleLeft.intersects(r.get(i).rectangle)) {
				// new
				Rectangle temp = new Rectangle(x - 5, y,
						playerImage.getWidth(null) + 5,
						playerImage.getHeight(null)-8);
				boolean sum = true;
				for (int j = 0; j < r.size(); j++) {

					if (temp.intersects(r.get(j).rectangle)) {
						sum = sum & false;
						return true;
					}
				}
				if (sum) {
					this.addX(-1);
					this.addY(-8);
					this.updateRectangle();
				}
				// new
				return true;
			}
		}
		return false;
	}

	public boolean checkCollisionJump(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r) {
		if (this.playerRectangleJump.intersectsLine(this.lineTop))
			return true;

		if (enemy != null) {
			if (playerRectangleJump.intersects(enemy.CollisionRectangle)) {
				this.setY(enemy.y + enemy.playerImage.getHeight(null) + 1);
				return true;
			}
		}

		for (int i = 0; i < s.size(); i++) {
			if (playerRectangleJump.intersects(s.get(i).rectangle)) // this
																	// function
																	// is
																	// similar
																	// to the
																	// functions
																	// used to
																	// check for
																	// right and
																	// left
			{ // collision
				this.setY(s.get(i).y + s.get(i).imageHeight + 1);
				return true;
			}
		}

		for (int i = 0; i < r.size(); i++) {
			if (playerRectangleJump.intersects(r.get(i).rectangle)) {
				this.setY(r.get(i).y + r.get(i).imageHeight + 1);
				return true;
			}
		}
		return false;
	}

	public boolean checkCollisionDown(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r) {
		if (this.playerRectangleDown.intersectsLine(this.lineBottom))
			return true;
		// same as for checkCollisionDown
		if (enemy != null)
			if (playerRectangleDown.intersects(enemy.CollisionRectangle)) {
				this.setY(enemy.y - playerImage.getHeight(null) - 1);
				return true;
			}

		for (int i = 0; i < s.size(); i++) {
			if (playerRectangleDown.intersects(s.get(i).rectangle)) {
				this.setY(s.get(i).y - playerImage.getHeight(null) - 1);
				return true;
			}
		}

		for (int i = 0; i < r.size(); i++) {
			if (playerRectangleDown.intersects(r.get(i).rectangle)) {
				this.setY(r.get(i).y - playerImage.getHeight(null) - 1);
				return true;
			}
		}
		return false;
	}

	public void moveRight(int amount) {
		this.setDirectionRight(true);

		if (!playerImage.equals(annimationImages[0])) {
			playerImage = annimationImages[0];
			walk++;
		} else
			playerImage = annimationImages[walk % 2];
		walk++;

		if (this.x < 750) // this functions moves the player to the right
			if (checkCollisionRight(staticobjects, reactiveobjects) == false) {
				this.addX(amount);
				this.updateRectangle();

			}

	}

	public void moveLeft(int amount) {
		directionRight = false;
		if (!playerImage.equals(annimationImages[2]))
			playerImage = annimationImages[2];
		else
			playerImage = annimationImages[3]; // this function moves the player
												// to the left
		if (this.x > 0)
			if (checkCollisionLeft(staticobjects, reactiveobjects) == false) {
				this.addX(-amount);
				this.updateRectangle();

			}

	}

	public void startJump(int a) {
		this.setInjump(true); // this starts a jump of the player
		if (a == 0) {
			this.setJumpSpeed(jumpSpeedBound);
			jumpQuantity = 1;
			canDoubleJump = true;
		} else if (a == 1 && canDoubleJump == true) {
			jumpQuantity = -2;
			jumpSpeed += jumpSpeedBound;
			canDoubleJump = false;
		}
	}

	public void jump() {
		if (jumpQuantity == 1 | jumpQuantity == -2) {
			if (jumpSpeed != 0)
				if (checkCollisionJump(staticobjects, reactiveobjects) == false) // this
																					// is
																					// actually
																					// the
																					// function
																					// that
																					// implements
																					// the
																					// logic
																					// behind
				{ // the jump
					this.addY(-jumpSpeed);
					if (directionRight == true) {
						moveRight(jumpQuantity);
					} else {
						moveLeft(jumpQuantity);
					}
					this.setJumpSpeed(jumpSpeed - 1);
					this.updateRectangle();
				} else
					this.setJumpSpeed(0);

			else
				this.setInjump(false);
		}
	}

	public void applyGravity() {
		if (checkCollisionDown(staticobjects, reactiveobjects) == false) // this
																			// applies
																			// gravity
																			// to
																			// the
																			// player
		{
			gravityIncrment = 0;
			this.addY(gravitySpeed);
			if (gravitySpeed < gravitySpeedBound)
				this.setGravitySpeed(gravitySpeed + 1);
			this.updateRectangle();
		} else
			this.setGravitySpeed(0);

	}

	public void updateRectangle() {
		playerRectangleLeft = new Rectangle(x - 5, y,
				playerImage.getWidth(null) + 5, playerImage.getHeight(null));
		playerRectangleRight = new Rectangle(x, y,
				playerImage.getWidth(null) + 5, playerImage.getHeight(null));
		playerRectangleDown = new Rectangle(x + 4, y,
				playerImage.getWidth(null) - 8, playerImage.getHeight(null) + 5); // collision
		playerRectangleJump = new Rectangle(this.x + 4, this.y - jumpSpeed,
				playerImage.getWidth(null) - 8, 10);
		CollisionRectangle = new Rectangle(this.x, this.y,
				this.playerImage.getWidth(null),
				this.playerImage.getHeight(null));
	}

	public void updateSystemVariables(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r, MainPlayer enemy) {
		this.staticobjects = s;
		this.reactiveobjects = r;
		this.enemy = enemy;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		updateRectangle();
		if (injump == false) {
			gravityIncrment++;
			applyGravity();
			jumpQuantity = 0;
		} else
			jump();
	}

}
