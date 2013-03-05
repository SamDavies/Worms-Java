import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.JPanel;
//in this class the arraylists of different objects as well as the player object are created
//it is also the class that listens for key inputs by the user

public class MainClass extends JPanel implements ActionListener, KeyListener{

    Timer  timer;   //this is the object which is going to call the function actionPerformed
	MainPlayer player;
	Image backgroundImage;
	ArrayList<StaticObjects> staticobjects;
	ArrayList<ReactiveObjects> reactiveobjects;
	ArrayList<Weapon> missiles;
	int [][] staticPositions={
	 {280,370},{150,550},{200,400}		
	 };
	int [][] reactivePositions={
			 {180,300},{350,480}		
			 };
	
    public static void main(String[] args)
	{
		
	}
	
	public MainClass()
	{   
		this.setFocusable(true);
		ImageIcon tempImageIcon;
		tempImageIcon=new ImageIcon(this.getClass().getResource("background.jpg"));
	    backgroundImage= tempImageIcon.getImage();
	    
		staticobjects = new ArrayList<StaticObjects>();
		reactiveobjects = new ArrayList<ReactiveObjects>();
		missiles = new ArrayList<Weapon>();
		
		for(int i=0;i<staticPositions.length;i++)
		{   
			StaticObjects temp=new StaticObjects(staticPositions[i][0],staticPositions[i][1]);
			staticobjects.add(temp);
		}
		
		for(int i=0;i<reactivePositions.length;i++)
		{   
			reactiveobjects.add(new ReactiveObjects(reactivePositions[i][0],reactivePositions[i][1]));
		}
		
		player = new MainPlayer(50, 300,staticobjects,reactiveobjects);
		this.addKeyListener(this);
		timer = new Timer(20,this);
		timer.addActionListener(this);
		timer.start();
	}


	public void updateGameVariables(){
	player.updateSystemVariables(staticobjects, reactiveobjects);
	 if(missiles.isEmpty()==false)
		 for(int i=0;i<missiles.size();i++)
			 missiles.get(i).updateSystemVariables(staticobjects, reactiveobjects);
	}
	
	public void keyTyped(KeyEvent e) {     
		
		
	}


	public void keyPressed(KeyEvent e) {    //fires automatically when a key is pressed
		int keycode=e.getKeyCode();
		  if(keycode==KeyEvent.VK_RIGHT)
			  player.moveRight();
		  if(keycode==KeyEvent.VK_LEFT)		
			  player.moveLeft();
		  if(keycode==KeyEvent.VK_UP)
			  if(player.injump==false&&player.checkCollisionDown(staticobjects, reactiveobjects)==true)
				  player.startJump();
		  if(keycode==KeyEvent.VK_SPACE)
			  if(player.directionRight==true)
				  missiles.add(new Weapon(player,staticobjects,reactiveobjects,true,4000, Math.PI*0.4));
			  else missiles.add(new Weapon(player,staticobjects,reactiveobjects,false,4000, Math.PI*0.4));
		
	}

	
	public void keyReleased(KeyEvent e) {   //fires automatically when a key is released
		
		
	}

	public void invisibleObjectCleaner(ArrayList<StaticObjects> s,ArrayList<ReactiveObjects> r,ArrayList<Weapon> m){
		if(s.isEmpty()==false)
			for(int i=0;i<s.size();i++)
				if(s.get(i).visible==false)
				{
					s.remove(i);
					updateGameVariables();
				}
		if(r.isEmpty()==false)
			for(int i=0;i<r.size();i++)
				if(r.get(i).visible==false)
				{
					r.remove(i);
					updateGameVariables();
				}
		if(m.isEmpty()==false)
			for(int i=0;i<m.size();i++)
				if(m.get(i).visible==false)
				{
					m.remove(i);
					updateGameVariables();
				}
	}

	public void actionPerformed(ActionEvent e) {  //fires automatically when an event happens i.e. when timer activates it
		invisibleObjectCleaner(staticobjects, reactiveobjects, missiles);
		updateGameVariables();
		repaint();
	}

	public void paintComponent(Graphics g){
		renderScreen(player, staticobjects, reactiveobjects, missiles, g);
	}
	
	public void renderScreen(MainPlayer p,ArrayList<StaticObjects> s,ArrayList<ReactiveObjects> r,ArrayList<Weapon> m,Graphics g){
		
		g.drawImage(backgroundImage, 0, 0, null);
		g.drawImage(p.playerImage, p.x, p.y, null);
		if(s.isEmpty()==false)
			for(int i=0;i<s.size();i++)
				if(s.get(i).visible==true)
				g.drawImage(s.get(i).objectImage, s.get(i).x, s.get(i).y, null);
		if(r.isEmpty()==false)
			for(int i=0;i<r.size();i++)
				if(r.get(i).visible==true)
				g.drawImage(r.get(i).currentImage, r.get(i).x, r.get(i).y, null);
		if(m.isEmpty()==false)
			for(int i=0;i<m.size();i++)
				if(m.get(i).visible==true)
				g.drawImage(m.get(i).currentImage, m.get(i).x, m.get(i).y, null);
	}
	
}
