import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;


public class BetweenRoundsPanel extends JPanel {

	JButton button, btnStartMap;
	JLabel lblNewLabel_2, lblNewLabel_3, status1, winner1, status2, winner2, status3, winner3;

	public BetweenRoundsPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Current Game Status");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(284, 25, 265, 21);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Map 1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(100, 165, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(260, 132, 46, 14);
		add(lblStatus);
		
		status1 = new JLabel("In Progress");
		status1.setBounds(245, 166, 97, 14);
		add(status1);
		
		JLabel lblWinner = new JLabel("Winner");
		lblWinner.setBounds(457, 132, 46, 14);
		add(lblWinner);
		
	    winner1 = new JLabel("Still Playing");
		winner1.setBounds(446, 166, 118, 14);
		add(winner1);
		
		JLabel lblMap = new JLabel("Map 2");
		lblMap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMap.setBounds(100, 259, 46, 14);
		add(lblMap);
		
		status2 = new JLabel("Not Started");
		status2.setBounds(245, 260, 97, 14);
		add(status2);
		
		winner2 = new JLabel("Not Started");
		winner2.setBounds(446, 260, 103, 14);
		add(winner2);
		
		JLabel lblMap_1 = new JLabel("Map 3");
		lblMap_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMap_1.setBounds(100, 346, 46, 14);
		add(lblMap_1);
		
		status3 = new JLabel("Not Started");
		status3.setBounds(245, 347, 97, 14);
		add(status3);
		
		winner3 = new JLabel("Not Started");
		winner3.setBounds(446, 347, 103, 14);
		add(winner3);
		
		btnStartMap = new JButton("Start Map");
		btnStartMap.setBounds(559, 256, 89, 23);
		btnStartMap.setEnabled(false);
		add(btnStartMap);
		
		
		button = new JButton("Start Map");
		button.setBounds(556, 343, 89, 23);
		add(button);
		button.setEnabled(false);
	}
}
