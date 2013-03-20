import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class StartGame implements ActionListener { //the class that has to be run to start the game
	
	JFrame frame;
	MainClass mainclass;
	JPanel p1;
	JButton j1;
	JButton j2;
	
	public StartGame(){
		frame=new JFrame();
		frame.setSize(800, 650);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    p1=new JPanel();
	    j1=new JButton("Single Player");
	    j1.addActionListener(this);
	    j1.setLocation(100, 100);
	    j1.setSize(100, 20);
	    p1.add(j1);
	    j2=new JButton("Multiplayer");
	    j2.addActionListener(this);
	   // j2.setLocation(100,300);
	    j2.setSize(100, 20);
	    j2.setLocation(0, 350);
	    p1.add(j2);
	    //p1.setFocusable(true);
	    frame.add(p1);
	   // frame.add(mainclass);
	    frame.setVisible(true);
	}

	
	public static void main(String[] args)   
	{
		StartGame s=new StartGame();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()==j1.getActionCommand()){
			mainclass=new MainClass(true,new Maps(1));
			frame.setVisible(false);
			frame.remove(p1);
			frame.add(mainclass);
			frame.setVisible(true);	
		}
		if(e.getActionCommand()==j2.getActionCommand())
		{
			mainclass=new MainClass(false,new Maps(1));
			frame.setVisible(false);
			frame.remove(p1);
			frame.add(mainclass);
			frame.setVisible(true);	
		}
	}
}
