import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JPanel;
import java.util.ArrayList;
//in this class the arraylists of different objects as well as the player object are created
//it is also the class that listens for key inputs by the user

public class MainClass extends JPanel implements ActionListener, KeyListener{

    Timer  timer;    //this is the object which is going to call the function actionPerformed
	MainPlayer player;
	ArrayList<StaticObjects> staticobjects;
	ArrayList<ReactiveObjects> reactiveobjects;
	
    public static void main(String[] args)
	{
		
	}
	
	public MainClass()
	{
		//initialize the timer and the other variable of MainClass
	}


	public void keyTyped(KeyEvent e) {     
		
		
	}


	public void keyPressed(KeyEvent e) {    //fires automatically when a key is pressed
		int keycode=e.getKeyCode();
		switch(keycode){
		case 39:  player.moveRight(staticobjects, reactiveobjects);
				  break;
		case 37:  player.moveLeft(staticobjects, reactiveobjects);
		  	      break;
		  	      
		default:  break;  	      
		}
		
	}

	
	public void keyReleased(KeyEvent e) {   //fires automatically when a key is released
		
		
	}


	public void actionPerformed(ActionEvent e) {  //fires automatically when an event happens
		
		
	}
	
}
