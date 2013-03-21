import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JEditorPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;


public class GoHomePageFrame extends JInternalFrame {

	JButton btnNewButton, btnNewButton_1;
	
		public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoHomePageFrame frame = new GoHomePageFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GoHomePageFrame() {
		setTitle("WARNING");
		setBounds(100, 100, 449, 205);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("If you continue to the Home Page");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(92, 29, 267, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("all progress so far will be lost! Do you");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(92, 45, 229, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("want to continue?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(92, 60, 180, 14);
		getContentPane().add(lblNewLabel_2);
		
	    btnNewButton = new JButton("YES");
		btnNewButton.setBounds(67, 119, 89, 23);
		getContentPane().add(btnNewButton);
		
	    btnNewButton_1 = new JButton("NO");
		btnNewButton_1.setBounds(241, 119, 89, 23);
		getContentPane().add(btnNewButton_1);

	}
}
