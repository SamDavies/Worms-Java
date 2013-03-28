import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.Timer;


public class StartGame extends JFrame implements ActionListener { //the class that has to be run to start the game
	
	MainClass mainclass;
    StartGameFrame panel1;
    BetweenRoundsPanel panel2;
    GoHomePageFrame frame1;
    JMenu mnFile;
    JMenu mnHelp;
    JMenuItem mntmExitGame, mntmHomePage;
    JDesktopPane desktop;
    Timer timer;
	private int currentMap;
	private String MapWinner[];
	private String player1Name,player2Name;

	public StartGame(){
		MapWinner=new String[3];
		this.setSize(1000, 650);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		desktop = new JDesktopPane();
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmHomePage = new JMenuItem("Home Screen");
		mntmHomePage.addActionListener(this);
		mnFile.add(mntmHomePage);
		mntmHomePage.setVisible(false);
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		mntmExitGame = new JMenuItem("Exit Game");
		mntmExitGame.addActionListener(this);
		mnFile.add(mntmExitGame);
		
		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	    panel1=new StartGameFrame();
	    panel1.btnNewButton.addActionListener(this);
	    this.add(panel1);
	    panel2=new BetweenRoundsPanel();
	    frame1=new GoHomePageFrame();
	    frame1.btnNewButton.addActionListener(this);
	    frame1.btnNewButton_1.addActionListener(this);
	    frame1.setVisible(true);
	    panel2.button.addActionListener(this);
	    panel2.btnStartMap.addActionListener(this);
	    this.setVisible(true);
	    timer=new Timer(1000,this);
	}

	
	public static void main(String[] args)   
	{
		StartGame s=new StartGame();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()==timer.getActionCommand())
		{
			if(mainclass.team1.get(0).playerHealth==0&&mainclass.team2.get(0).playerHealth==0){
				this.setVisible(false);
				this.remove(mainclass);
				MapWinner[currentMap]="The game was a draw";
				timer.stop();
				if(currentMap==0){
					panel2.btnStartMap.setEnabled(true);
					panel2.winner1.setText(MapWinner[currentMap]);
					panel2.status1.setText("Finished");
					}
				if(currentMap==1){
					panel2.button.setEnabled(true);
					panel2.btnStartMap.setEnabled(false);
					panel2.winner2.setText(MapWinner[currentMap]);
					panel2.status2.setText("Finished");
					}
				desktop.remove(frame1);
				desktop.remove(panel1);
				desktop.add(panel2);
				this.add(desktop);
				this.setVisible(true);
				this.currentMap++;
				}
			
			else if(mainclass.team2.get(0).playerHealth==0 && mainclass.team2.get(1).playerHealth==0 && mainclass.team2.get(2).playerHealth==0 && mainclass.team2.get(3).playerHealth==0){
				this.setVisible(false);
				this.remove(mainclass);
				MapWinner[currentMap]=this.getPlayer1Name();
				timer.stop();
				if(currentMap==0){
					panel2.btnStartMap.setEnabled(true);
					panel2.winner1.setText(MapWinner[currentMap]);
					panel2.status1.setText("Finished");
					}
				if(currentMap==1){
					panel2.button.setEnabled(true);
					panel2.btnStartMap.setEnabled(false);
					panel2.winner2.setText(MapWinner[currentMap]);
					panel2.status2.setText("Finished");
					}
				desktop.remove(frame1);
				desktop.remove(panel1);
				desktop.add(panel2);
				this.add(panel2);
				this.setVisible(true);
				this.currentMap++;
				}
			
			if(mainclass.team1.get(0).playerHealth==0 && mainclass.team1.get(1).playerHealth==0 && mainclass.team1.get(2).playerHealth==0 && mainclass.team1.get(3).playerHealth==0){
				this.setVisible(false);
				this.remove(mainclass);
				MapWinner[currentMap]=this.getPlayer2Name();
				timer.stop();
				if(currentMap==0){
					panel2.btnStartMap.setEnabled(true);
					panel2.winner1.setText(MapWinner[currentMap]);
					panel2.status1.setText("Finished");
					}
				if(currentMap==1){
					panel2.button.setEnabled(true);
					panel2.btnStartMap.setEnabled(false);
					panel2.winner2.setText(MapWinner[currentMap]);
					panel2.status2.setText("Finished");
					}
				desktop.remove(frame1);
				desktop.remove(panel1);
				desktop.add(panel2);
				this.add(panel2);
				this.setVisible(true);
				this.currentMap++;
				}
				
					}
			if(e.getActionCommand()==panel1.btnNewButton.getActionCommand())
		{	
			
				SoundEffect.STARTROUND.play();
				
			this.player1Name=panel1.textArea.getText();
			this.player2Name=panel1.textArea_1.getText();
			mainclass=new MainClass(this.getPlayer1Name(), this.getPlayer2Name(),new Maps(1));
			this.setVisible(false);
			this.remove(desktop);
			this.remove(panel1);
			desktop.remove(panel1);
			this.add(mainclass);
			currentMap=0;
			MapWinner=new String[3];
			mntmHomePage.setVisible(true);
			this.setVisible(true);	
			timer.start();
			
		}
	      
			if(e.getActionCommand()==panel2.btnStartMap.getActionCommand())
			{   
				mainclass.setEnabled(false);
				mainclass.stopTimers();
				this.remove(mainclass);
				mainclass=new MainClass(this.getPlayer1Name(), this.getPlayer2Name(),new Maps(2));
				this.setVisible(false);
				this.remove(desktop);
				this.remove(panel2);
				this.add(mainclass);
				this.setVisible(true);	
				timer.start();
			}
	     
	       if(e.getActionCommand()==panel2.button.getActionCommand())
			{
	    	    mainclass.setEnabled(false);
	    	    mainclass.stopTimers();
	    		this.remove(mainclass);
			 	mainclass=new MainClass(this.getPlayer1Name(), this.getPlayer2Name(),new Maps(3));
				this.setVisible(false);
				this.remove(desktop);
				this.remove(panel2);
				this.add(mainclass);
				this.setVisible(true);
				timer.start();
			}
	       
	       if(e.getActionCommand()==frame1.btnNewButton.getActionCommand())
			{
	    	   	mainclass.setEnabled(false);
	    	   	mainclass.stopTimers();
				this.setVisible(false);
				this.remove(mainclass);
				desktop.remove(frame1);
				desktop.add(panel1);
				//this.getContentPane().add(panel1);
				this.setVisible(true);	
				timer.stop();
			}
	      
	       if(e.getActionCommand()==frame1.btnNewButton_1.getActionCommand())
				{
					this.setVisible(false);
					this.remove(desktop);
					this.add(mainclass);
					desktop.remove(frame1);
					mainclass.setEnabled(true);
					mainclass.startTimers();
					this.setVisible(true);	
				}
	     
	       if(e.getActionCommand()==this.mntmHomePage.getActionCommand())
				{
					this.setVisible(false);
					mainclass.setEnabled(false);
					mainclass.stopTimers();
				    this.remove(mainclass);
				    desktop.remove(panel1);
				    desktop.add(frame1);
					this.add(desktop);
					this.setVisible(true);
				}     
	      
	       if(e.getActionCommand()==this.mntmExitGame.getActionCommand())
			{
				this.dispose();
				this.setVisible(false);
			}   

	       if(e.getActionCommand()==frame1.btnNewButton.getActionCommand())
				{
					this.setVisible(false);
					this.remove(desktop);
					desktop.remove(panel1);
					desktop.remove(frame1);
					desktop.add(panel1);
					this.add(desktop);
					this.setVisible(true);	
				}
	       
	      
	}


	private String getPlayer1Name() {
		return this.player1Name;

	}
	private String getPlayer2Name() {
		return this.player2Name;
	}
}
