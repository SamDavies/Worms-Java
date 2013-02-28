import javax.swing.JFrame;


public class StartGame { //the class that has to be run to start the game
	
	JFrame frame;
	MainClass mainclass;

	public StartGame(){
		frame=new JFrame();
		frame.setSize(800, 650);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainclass = new MainClass();
	    frame.add(mainclass);
	    frame.setVisible(true);
	}

	
	public static void main(String[] args)   
	{
		StartGame s=new StartGame();
	}
}
