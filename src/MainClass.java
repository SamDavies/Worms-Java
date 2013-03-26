import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JPanel;
//in this class the arraylists of different objects as well as the player object are created
//it is also the class that listens for key inputs by the user

public class MainClass extends JPanel implements ActionListener, KeyListener,
		MouseListener {

	Timer timer, changeTurns; // this is the object which is going to call the
								// function actionPerformed
	MainPlayer player1, player2;
	KeyListener listener1;
	Image backgroundImage, boardImage, player1WeaponImage, player2WeaponImage;
	ArrayList<Integer> pressedKeys = new ArrayList<Integer>();
	ArrayList<StaticObjects> staticobjects;
	ArrayList<ReactiveObjects> reactiveobjects;
	ArrayList<Missile> missiles;
	ArrayList<Weapon> grenades;
	ArrayList<Bullet> bullets;
	private double clickVelocity; // stores the velocity for weapon
	boolean player1Turn, player2Turn;
	int[] mouseXY = new int[2]; // stores coordinates of mouse
	int player1Weapon = 1, player2Weapon = 1;
	int timeLeftInTurn = 30, weaponsUsedInTurn = 0, MaxWeaponsPerTurn = 800;
	String board = "";
	
	boolean fired = false;	
	private String player1Name;
	private String player2Name;

	MainPlayer p = player1;
	MainPlayer p2 = player2;
	
	int boxPos= -200;
	int randX = 0;
	boolean drop = false;
	StaticObjects box = new StaticObjects(randX, boxPos, 0);

	public MainClass(String player1Name, String player2Name, Maps map) {		
		this.player1Name = player1Name;
		this.player2Name = player2Name;
		this.setFocusable(true);
		ImageIcon tempImageIcon;
		tempImageIcon = new ImageIcon(
				"background.png");
		backgroundImage = tempImageIcon.getImage();
		tempImageIcon = new ImageIcon("board.png");
		boardImage = tempImageIcon.getImage();
		staticobjects = new ArrayList<StaticObjects>();
		reactiveobjects = new ArrayList<ReactiveObjects>();
		missiles = new ArrayList<Missile>();
		grenades = new ArrayList<Weapon>();
		bullets = new ArrayList<Bullet>();

		int posX = 0, posY = 0, type = 0;
		for (int i = 0; i < map.objectTypeAtIndex.size(); i++) {
			posX = map.objectPositionsXAtIndex.get(i);
			posY = map.objectPositionsYAtIndex.get(i);
			type = map.objectTypeAtIndex.get(i);
			switch (type) {
			case 0:
				staticobjects.add(new StaticObjects(posX, posY, 0));
				break;
			case 1:
				staticobjects.add(new StaticObjects(posX, posY, 1));
				break;
			case 2:
				staticobjects.add(new StaticObjects(posX, posY, 2));
				break;
			case 3:
				reactiveobjects.add(new ReactiveObjects(posX, posY, 1));
				break;
			case 4:
				reactiveobjects.add(new ReactiveObjects(posX, posY, 2));
				break;
			case 5:
				reactiveobjects.add(new ReactiveObjects(posX, posY, 0));
				break;
			case 6:
				reactiveobjects.add(new ReactiveObjects(posX, posY, 3));
				break;
			case 7:
				reactiveobjects.add(new ReactiveObjects(posX, posY, 4));
				break;
			case 8:
				reactiveobjects.add(new ReactiveObjects(posX, posY, 5));
				break;
			case 9:
				reactiveobjects.add(new ReactiveObjects(posX, posY, 6));
				break;
			}
		}
		this.player1WeaponImage = (new ImageIcon("images/weapons/missile0.png")
				.getImage());
		this.player2WeaponImage = (new ImageIcon("images/weapons/missile0.png")
				.getImage());

		player1 = new MainPlayer(50, 50, staticobjects, reactiveobjects,
				player2);
		player2 = new MainPlayer(700, 100, staticobjects, reactiveobjects,
				player1);

		this.addKeyListener(this);
		this.addMouseListener(this);
		timer = new Timer(30, this);
		timer.addActionListener(this);
		changeTurns = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if (timeLeftInTurn == 0) {
					if (player1Turn == true)
						player1Turn = false;
					else
						player1Turn = true;
					if (player2Turn == true)
						player2Turn = false;
					else
						player2Turn = true;
					weaponsUsedInTurn = 0;
					timeLeftInTurn = 30;
				} else
					timeLeftInTurn--;
				if (timeLeftInTurn>0 && timeLeftInTurn<=10){
					SoundEffect.TIMERTICK.play();
				}				
				
				board = createResultBoard();
			}

			
		});

		player1Turn = true;
		player2Turn = false;
		timer.start();
		changeTurns.start();
	}

	private void dropBox(int boxPos, int randX) {		
				
				int frame = 0;
				int condition = (boxPos + 200) %32;
				
				if(condition==0){frame = 1;}
				else if(condition==4){frame = 2;}
				else if(condition==8){frame = 3;}
				else if(condition==12){frame = 4;}
				else if(condition==16){frame = 5;}
				else if(condition==20){frame = 4;}
				else if(condition==24){frame = 3;}
				else if(condition==28){frame = 2;}			
				
				
				box.setImage(frame);
				box.setX(randX);
				box.setY(boxPos);
				box.updateRectangle();
				
			}
	
	public void updateGameVariables() {
		player1.updateSystemVariables(staticobjects, reactiveobjects, player2);
		if (player2 != null)
			player2.updateSystemVariables(staticobjects, reactiveobjects,
					player1);
		if (missiles.isEmpty() == false)
			for (int i = 0; i < missiles.size(); i++)
				missiles.get(i).updateSystemVariables(staticobjects,
						reactiveobjects);
		if (grenades.isEmpty() == false)
			for (int i = 0; i < grenades.size(); i++)
				grenades.get(i).updateSystemVariables(staticobjects,
						reactiveobjects);
		for (int i = 0; i < bullets.size(); i++)
			bullets.get(i)
					.updateSystemVariables(staticobjects, reactiveobjects);
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) { // fires automatically when a key is
		// pressed
		int keycode = e.getKeyCode();
		if (pressedKeys.contains(keycode) == false) {
			pressedKeys.add(keycode);
		}

		if (player1Turn == true) {
			p = player1;
			p2 = player2;
			if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
				changeWeapon(0);
			}
			if (pressedKeys.contains(KeyEvent.VK_UP)) {
				playerJump();
			}
			if (pressedKeys.contains(KeyEvent.VK_RIGHT))
				p.moveRight(1);
			if (pressedKeys.contains(KeyEvent.VK_LEFT))
				p.moveLeft(1);
			if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
				weaponLaunch(0);
			}

		} else {
			p = player2;
			p2 = player1;
			if (pressedKeys.contains(KeyEvent.VK_DOWN)) {
				changeWeapon(1);
			}
			if (pressedKeys.contains(KeyEvent.VK_UP)) {
				playerJump();
			}
			if (pressedKeys.contains(KeyEvent.VK_RIGHT))
				p.moveRight(1);
			if (pressedKeys.contains(KeyEvent.VK_LEFT))
				p.moveLeft(1);
			if (pressedKeys.contains(KeyEvent.VK_SPACE)) {
				weaponLaunch(1);
			}
		}

		// weapon launch

		// weapon launch

	}

	public void changeWeapon(int player) {
		Image temp = (new ImageIcon("images/weapons/missile0.png").getImage());
		int tempW = 0;
		if (player == 0) {
			tempW = player1Weapon;
		} else if (player == 1) {
			tempW = player2Weapon;
		}

		tempW = (tempW + 1) % 3;
		switch (tempW) {
		case 1:
			temp = (new ImageIcon("images/weapons/missile0.png").getImage());
			break;
		case 0:
			temp = (new ImageIcon("images/grenade/grenade0.png").getImage());
			break;
		case 2:
			temp = (new ImageIcon("images/weapons/bullet-right.png").getImage());
			break;
		}
		if (player == 0) {
			this.player1WeaponImage = temp;
			player1Weapon = tempW;
		} else {
			this.player2WeaponImage = temp;
			player2Weapon = tempW;
		}
	}

	public void playerJump() {

		if (p.injump == false
				&& p.checkCollisionDown(staticobjects, reactiveobjects) == true)
			p.startJump(0);
		else if (p.injump == true && p.jumpSpeed > 0
				&& p.jumpSpeed < p.jumpSpeedBound - 5) {
			p.startJump(1);
		}
	}

	public void weaponLaunch(int player) {
		if (player == 0) {
			if (this.weaponsUsedInTurn < this.MaxWeaponsPerTurn)
				if (player1.directionRight == true) {
					if (player1Weapon == 1
							&& player1.getMissilesAvailable() > 0)
						missiles.add(new Missile(player1, player2,
								staticobjects, reactiveobjects, true, 200));
					if (player1Weapon == 2)
						bullets.add(new Bullet(player1, player2, staticobjects,
								reactiveobjects, true));
					this.weaponsUsedInTurn++;
				} else {
					if (player1Weapon == 1
							&& player1.getMissilesAvailable() > 0)
						missiles.add(new Missile(player1, player2,
								staticobjects, reactiveobjects, false, 200));
					if (player1Weapon == 2)
						bullets.add(new Bullet(player1, player2, staticobjects,
								reactiveobjects, false));
					this.weaponsUsedInTurn++;
				}
		} else if (player == 1) {
			if (this.weaponsUsedInTurn < this.MaxWeaponsPerTurn)
				if (player2.directionRight == true) {
					if (player2Weapon == 1
							&& player2.getMissilesAvailable() > 0)
						missiles.add(new Missile(player2, player1,
								staticobjects, reactiveobjects, true, 200));
					if (player2Weapon == 2)
						bullets.add(new Bullet(player2, player1, staticobjects,
								reactiveobjects, true));
					this.weaponsUsedInTurn++;
				} else {
					if (player2Weapon == 1)
						missiles.add(new Missile(player2, player1,
								staticobjects, reactiveobjects, false, 200));
					if (player2Weapon == 0)
						grenades.add(new Weapon(player2, player1,
								staticobjects, reactiveobjects, false, 20, 200));
					if (player2Weapon == 2)
						bullets.add(new Bullet(player2, player1, staticobjects,
								reactiveobjects, false));
					this.weaponsUsedInTurn++;
				}
		}
	}
	
	public void stopTimers() {
		this.timer.stop();
		this.changeTurns.stop();
	}

	public void startTimers() {
		this.timer.start();
		this.changeTurns.start();
	}

	public void keyReleased(KeyEvent e) { // fires automatically when a key is
											// released
		int index;
		index = pressedKeys.indexOf(e.getKeyCode());
		if (index != -1)
			pressedKeys.remove(index);

	}

	public void invisibleObjectCleaner(ArrayList<StaticObjects> s,
			ArrayList<ReactiveObjects> r, ArrayList<Missile> m,
			ArrayList<Weapon> g, ArrayList<Bullet> b) {
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
		if (g.isEmpty() == false)
			for (int i = 0; i < g.size(); i++)
				if (g.get(i).visible == false) {
					g.remove(i);
					updateGameVariables();
				}
		if (b.isEmpty() == false)
			for (int i = 0; i < b.size(); i++)
				if (b.get(i).visible == false) {
					b.remove(i);
					updateGameVariables();
				}
	}

	public void actionPerformed(ActionEvent e) { // fires automatically when an
													// event happens i.e. when
													// timer activates it
		// drop in box at end of turn
		if(timeLeftInTurn==0){
			box.make();
			drop=true;
			}
		if(drop==true){
			if(boxPos==-200){
				Random rand = new Random();
				randX=rand.nextInt(600);
			}
			boxPos++;
			dropBox(boxPos, randX);
		}
		// drop in box at end of turn
		
		invisibleObjectCleaner(staticobjects, reactiveobjects, missiles,
				grenades, bullets);
		updateGameVariables();
		repaint();
		if (fired && (this.player1Weapon == 0 || this.player2Weapon == 0)) {
			clickVelocity += 20; // adds increments to the velocity every event
									// when log timer is active
		} else {
			clickVelocity = 0;
		}
				
	}

	public void paintComponent(Graphics g) {
		renderScreen(g);
	}

	public void renderScreen(Graphics g) {

		g.drawImage(backgroundImage, 0, 0, null);
		// create the result board
		g.setFont(new Font("standart", Font.BOLD, 12));
		g.setColor(Color.GREEN);
		g.drawImage(boardImage, 0, 0, null);
		g.drawString(board, 0, 20);
		g.drawImage(player1WeaponImage, 270, 5, null);
		g.drawImage(player2WeaponImage, 700, 5, null);
		// create the result board
		g.drawImage(player1.playerImage, player1.x, player1.y, null);
		if (player2 != null)
			g.drawImage(player2.playerImage, player2.x, player2.y, null);		
		if (reactiveobjects.isEmpty() == false)
			for (int i = 0; i < reactiveobjects.size(); i++)
				if (reactiveobjects.get(i).visible == true)
					g.drawImage(reactiveobjects.get(i).currentImage,
							reactiveobjects.get(i).x, reactiveobjects.get(i).y,
							null);
		if (staticobjects.isEmpty() == false)
			for (int i = 0; i < staticobjects.size(); i++)
				if (staticobjects.get(i).visible == true)
					g.drawImage(staticobjects.get(i).objectImage,
							staticobjects.get(i).x, staticobjects.get(i).y,
							null);
		if (missiles.isEmpty() == false)
			for (int i = 0; i < missiles.size(); i++)
				if (missiles.get(i).visible == true)
					g.drawImage(missiles.get(i).currentImage,
							missiles.get(i).x, missiles.get(i).y, null);
		if (grenades.isEmpty() == false)
			for (int i = 0; i < grenades.size(); i++)
				if (grenades.get(i).visible == true)
					g.drawImage(grenades.get(i).currentImage,
							grenades.get(i).x, grenades.get(i).y, null);
		if (bullets.isEmpty() == false)
			for (int i = 0; i < bullets.size(); i++)
				if (bullets.get(i).visible == true)
					g.drawImage(bullets.get(i).currentImage, bullets.get(i).x,
							bullets.get(i).y, null);
		if (player1Turn == true)
			g.drawLine(player1.getX(), player1.getY() - 10,
					player1.getX() + 40, player1.getY() - 10);
		else
			g.drawLine(player2.getX(), player2.getY() - 10,
					player2.getX() + 40, player2.getY() - 10);
		
		g.drawImage(box.objectImage,box.x,box.y,null);
	}

	private String createResultBoard() {
		String board = " ";
		for (int i = 0; i < 5; i++)
			board += "          ";
		board += String.valueOf(player1.playerHealth);
		for (int i = 0; i < 5; i++)
			board += "          ";
		if (player1Weapon == 0)
			board += player1.getGrenadesAvailable();
		if (player1Weapon == 1)
			board += player1.getMissilesAvailable();
		for (int i = 0; i < 2; i++)
			board += "          ";
		board += String.valueOf(timeLeftInTurn);
		for (int i = 0; i < 5; i++)
			board += "          ";
		board += "          ";
		board += String.valueOf(player2.playerHealth);
		for (int i = 0; i < 5; i++)
			board += "          ";
		board += "   ";
		if (player2Weapon == 0)
			board += player2.getGrenadesAvailable();
		if (player2Weapon == 1)
			board += player2.getMissilesAvailable();
		return board;
	}

	public void fire(double velocity) {

		SoundEffect.COUGH.play();
		
		double mx = mouseXY[0];
		double my = mouseXY[1] * Math.PI;
		double angleR = Math.atan(my / mx); // calcs angle
		double angleL = Math.atan(my / -mx); // calcs -angle
		if (player1Turn == true) {
			if (player1.directionRight == true)
				grenades.add(new Weapon(player1, player2, staticobjects,
						reactiveobjects, true, clickVelocity, angleR));
			else
				grenades.add(new Weapon(player1, player2, staticobjects,
						reactiveobjects, false, clickVelocity, angleL));
		}

		if (player2Turn == true) {
			if (player2.directionRight == true)
				grenades.add(new Weapon(player2, player1, staticobjects,
						reactiveobjects, true, clickVelocity, angleR));
			else
				grenades.add(new Weapon(player2, player1, staticobjects,
						reactiveobjects, false, clickVelocity, angleL));
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.player2Weapon == 0 || this.player1Weapon == 0)
			fired = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int mousecode = e.getButton();
		if (this.weaponsUsedInTurn < this.MaxWeaponsPerTurn)
			if (player1Turn == true && this.player1Weapon == 0)
				if (player1.getGrenadesAvailable() > 0) {
					mouseXY[0] = e.getX() - player1.getX();// gets x of mouse
															// and takes away
															// player x
					mouseXY[1] = player1.getY() - e.getY();// gets -y of mouse
															// and adds
					// player y

					if (mousecode == MouseEvent.BUTTON1) {
						fire(clickVelocity); // fires weapon
						fired = false;
						; // ends the log
						clickVelocity = 0; // resets click velocity
						this.weaponsUsedInTurn++;
					}
				}
		if (this.weaponsUsedInTurn < this.MaxWeaponsPerTurn)
			if (player2Turn == true && this.player2Weapon == 0)
				if (player1.getGrenadesAvailable() > 0) {
					mouseXY[0] = e.getX() - player2.getX();// gets x of mouse
															// and takes away
															// player x
					mouseXY[1] = player2.getY() - e.getY();// gets -y of mouse
															// and adds
					// player y

					if (mousecode == MouseEvent.BUTTON1) {
						fire(clickVelocity); // fires weapon
						fired = false; // ends the log
						clickVelocity = 0; // resets click velocity
						this.weaponsUsedInTurn++;
					}
				}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
