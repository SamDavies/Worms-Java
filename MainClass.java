import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JPanel;
//in this class the arraylists of different objects as well as the player object are created
//it is also the class that listens for key inputs by the user

public class MainClass extends JPanel implements ActionListener, KeyListener,
		MouseListener {

	Timer timer; // this is the object which is going to call the function
					// actionPerformed
	MainPlayer player;
	Image backgroundImage;
	ArrayList<StaticObjects> staticobjects;
	ArrayList<ReactiveObjects> reactiveobjects;
	ArrayList<Weapon> missiles;
	int[][] staticPositions = { { 280, 370 }, { 150, 550 }, { 200, 400 } };
	int[][] reactivePositions = { { 180, 300 }, { 350, 480 } };

	private double clickVelocity; // stores the velocity for weapon
	int[] mouseXY = new int[2]; //stores coordinates  of mouse

	public static void main(String[] args) {

	}

	public MainClass() {
		this.setFocusable(true);
		ImageIcon tempImageIcon;
		tempImageIcon = new ImageIcon(this.getClass().getResource(
				"background.jpg"));
		backgroundImage = tempImageIcon.getImage();

		staticobjects = new ArrayList<StaticObjects>();
		reactiveobjects = new ArrayList<ReactiveObjects>();
		missiles = new ArrayList<Weapon>();

		for (int i = 0; i < staticPositions.length; i++) {
			StaticObjects temp = new StaticObjects(staticPositions[i][0],
					staticPositions[i][1]);
			staticobjects.add(temp);
		}

		for (int i = 0; i < reactivePositions.length; i++) {
			reactiveobjects.add(new ReactiveObjects(reactivePositions[i][0],
					reactivePositions[i][1]));
		}

		player = new MainPlayer(50, 300, staticobjects, reactiveobjects);
		this.addKeyListener(this);
		this.addMouseListener(this);
		timer = new Timer(20, this);
		timer.addActionListener(this);
		timer.start();
		
		SoundEffect.init(); //loads the sound files so that there is no delay where playing.
	}

	public void updateGameVariables() {
		player.updateSystemVariables(staticobjects, reactiveobjects);
		if (missiles.isEmpty() == false)
			for (int i = 0; i < missiles.size(); i++)
				missiles.get(i).updateSystemVariables(staticobjects,
						reactiveobjects);
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) { // fires automatically when a key is
											// pressed

		int keycode = e.getKeyCode();
		if (keycode == KeyEvent.VK_RIGHT)
			player.moveRight();
		if (keycode == KeyEvent.VK_LEFT)
			player.moveLeft();
		if (keycode == KeyEvent.VK_UP)
			if (player.injump == false
					&& player
							.checkCollisionDown(staticobjects, reactiveobjects) == true)
				player.startJump();

	}

	public void fire(double velocity) {
		
		double mx = mouseXY[0];
		double my = mouseXY[1] * Math.PI;
		double angleR = Math.atan(my/mx); //calcs angle
		double angleL = Math.atan(my/-mx); //calcs -angle
		
		if (player.directionRight == true)
			missiles.add(new Weapon(player, staticobjects, reactiveobjects,
					true, clickVelocity, angleR));
		else
			missiles.add(new Weapon(player, staticobjects, reactiveobjects,
					false, clickVelocity, angleL));
	}

	public void keyReleased(KeyEvent e) { // fires automatically when a key is
											// released

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		Timer.setLogTimers(true);
	}

	public void mouseReleased(MouseEvent e) {
		int mousecode = e.getButton();
		mouseXY[0]= e.getX() - player.getX();//gets x of mouse and takes away player x
		mouseXY[1]= player.getY()-e.getY();//gets -y of mouse and adds player y

		if (mousecode == MouseEvent.BUTTON1) {
			fire(clickVelocity); //fires weapon
			Timer.setLogTimers(false); //ends the log
			clickVelocity = 0; // resets click velocity
		}

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void invisibleObjectCleaner(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r, ArrayList<Weapon> m) {
		if (s.isEmpty() == false)
			for (int i = 0; i < s.size(); i++)
				if (s.get(i).visible == false) {
					s.remove(i);
					updateGameVariables();
				}
		if (r.isEmpty() == false)
			for (int i = 0; i < r.size(); i++)
				if (r.get(i).visible == false) {
					r.remove(i);
					updateGameVariables();
				}
		if (m.isEmpty() == false)
			for (int i = 0; i < m.size(); i++)
				if (m.get(i).visible == false) {
					m.remove(i);
					updateGameVariables();
				}
	}

	public void actionPerformed(ActionEvent e) { // fires automatically when an
													// event happens i.e. when
													// timer activates it
		invisibleObjectCleaner(staticobjects, reactiveobjects, missiles);
		updateGameVariables();
		repaint();

		if (Timer.getLogTimers() == true) {
			clickVelocity += 20; // adds increments to the velocity every event
									// when log timer is active
		}

	}

	public void paintComponent(Graphics g) {
		renderScreen(player, staticobjects, reactiveobjects, missiles, g);
	}

	public void renderScreen(MainPlayer p, ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r, ArrayList<Weapon> m, Graphics g) {

		g.drawImage(backgroundImage, 0, 0, null);
		g.drawImage(p.playerImage, p.x, p.y, null);
		if (s.isEmpty() == false)
			for (int i = 0; i < s.size(); i++)
				if (s.get(i).visible == true)
					g.drawImage(s.get(i).objectImage, s.get(i).x, s.get(i).y,
							null);
		if (r.isEmpty() == false)
			for (int i = 0; i < r.size(); i++)
				if (r.get(i).visible == true)
					g.drawImage(r.get(i).currentImage, r.get(i).x, r.get(i).y,
							null);
		if (m.isEmpty() == false)
			for (int i = 0; i < m.size(); i++)
				if (m.get(i).visible == true)
					g.drawImage(m.get(i).currentImage, m.get(i).x, m.get(i).y,
							null);
	}

}
