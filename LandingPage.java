import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
public class LandingPage implements ActionListener {

	public JFrame landingPage;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	 int height = (int)screenSize.getHeight();
	 int width = (int)screenSize.getWidth();
	JButton button;
	
	JLabel label;
	LandingPage()
	{
		
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		landingPage = new JFrame();
		landingPage.setSize(width, height);
		landingPage.setVisible(true);
		landingPage.setTitle("Hangman Game");
		landingPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		landingPage.setResizable(true);
		ImageIcon image = new ImageIcon("dtb.png");
		landingPage.setIconImage(image.getImage());
		landingPage.getContentPane().setBackground(new Color(255, 99, 71));//196, 164, 132
		
		
		//Label
		label = new JLabel();
		label.setText("Welcome to Hangman World" );
		label.setIcon(image);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.TOP);
		label.setForeground(Color.white);
		label.setFont(new Font("Sanserif",Font.BOLD,25));
		label.setBounds(560,160,400,400);
		label.setIconTextGap(50);
		Border border = BorderFactory.createLineBorder(Color.black,8);
		label.setBorder(border);
		landingPage.add(label);
		
		
		
		//Button
		button = new JButton();
		button.setText("Click here to Start");
		button.setBounds(660,600,200,50);
		button.setFocusable(false);
		button.setBackground(Color.BLACK);
		button.setForeground(Color.white);
		button.setFont(new Font("Sanserif",Font.BOLD,18));
		button.addActionListener(this);
		button.setCursor(cursor);
		landingPage.add(button);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == button)
		{	
			
			//landingPage.dispose();
			try 
			{
				GamePage gp = new GamePage();
			} 
			catch (Exception e1)
			{
				
				e1.printStackTrace();
			}
			
		}
		
	}
}
