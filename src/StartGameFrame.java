import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;


public class StartGameFrame extends JPanel {

	JButton btnNewButton;
	JTextArea textArea_1;
	JTextArea textArea;
	public StartGameFrame() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Player 1 Name");
		lblNewLabel.setBounds(180, 305, 108, 14);
		add(lblNewLabel);
		
		JLabel lblPlayerName = new JLabel("Player 2 Name");
		lblPlayerName.setBounds(438, 305, 108, 14);
		add(lblPlayerName);
		
	    textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textArea.setBounds(180, 343, 108, 28);
		add(textArea);
		
	    textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textArea_1.setBounds(438, 343, 108, 28);
		add(textArea_1);
		
	    btnNewButton = new JButton("Start Game");
		btnNewButton.setBounds(319, 208, 120, 23);
		add(btnNewButton);

	}
}
