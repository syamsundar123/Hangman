import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

import javax.swing.*;
import javax.swing.border.Border;
public class GamePage implements ActionListener
{
	
	
	Scanner sc = new Scanner(System.in);
	Database db = new Database();
	Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
	
	static String imagePaths[] = {"hang.jpg","img4.png","img3.png","img2.png","img1.jpg"};
	
	static String word;  static int wordLength;  static int chances = 4;
	
	
	static JFrame gamePage;  static JTextField text[];   JLabel msglabel,hint;
	static JLabel imageLabel;   static JLabel guess;    static ImageIcon icon;    static JButton button[];
	
	
	JButton skipButton,backButton,exitButton;
	
	
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int)screenSize.getHeight();
	int width = (int)screenSize.getWidth();
	
	
	GamePage() throws Exception
	{
		
		
		//Window Creation
		gamePage = new JFrame();
		gamePage.setSize(width,height);
		gamePage.setVisible(true);
		gamePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePage.getContentPane().setBackground(new Color(255, 99, 71));
		
		
		
		
		//Label
		msglabel = new JLabel();
		msglabel.setText("Enter your Answer:");
		msglabel.setBounds(220,255,500,50);
		msglabel.setFont(new Font("TimesnewRoman",Font.BOLD,30));
		gamePage.add(msglabel);
		
		
		
		//Hint Label
		hint = new JLabel();
		hint.setBounds(10,10,1500,100);
		Border border = BorderFactory.createLineBorder(Color.black,8);
		hint.setBorder(border);
		String Hint = "Hint: "+db.rs.getString(3);
		hint.setText("<html>"+ Hint +  "</html>");//Objects.nonNull(db.rs.getString(3))
		hint.setFont(new Font("TimesnewRoman",Font.BOLD,30));
		gamePage.add(hint);
		
		//Guess Message Label
		guess = new JLabel();
		guess.setBounds(40,655,700,50);
		guess.setFont(new Font("TimesnewRoman",Font.BOLD,30));
		guess.setForeground(Color.red);
		gamePage.add(guess);
		guess.setText("Chances remaining:" + chances);
		guess.setVisible(true);
		gamePage.add(guess);
		
		//skipButton
		skipButton = new JButton();
		skipButton.setText("SKIP");
		skipButton.setBounds(1000,655,100,50);
		skipButton.setBackground(Color.black);
		skipButton.setForeground(Color.white);
		skipButton.setFont(new Font("TimesnewRoman",Font.BOLD,25));
		skipButton.setVisible(true);
		skipButton.addActionListener(this);
		skipButton.setCursor(cursor);
		gamePage.add(skipButton);
		
		
		
		//BackButton
		
		backButton = new JButton();
		backButton.setText("BACK");
		backButton.setBounds(1150,655,140,50);
		backButton.setBackground(Color.black);
		backButton.setForeground(Color.white);
		backButton.setFont(new Font("TimesnewRoman",Font.BOLD,25));
		backButton.setVisible(true);
		backButton.addActionListener(this);
		backButton.setCursor(cursor);
		gamePage.add(backButton);
		
		
		
		

		exitButton = new JButton();
		exitButton.setText("EXIT");
		exitButton.setBounds(1350,655,140,50);
		exitButton.setBackground(Color.black);
		exitButton.setForeground(Color.white);
		exitButton.setFont(new Font("TimesnewRoman",Font.BOLD,25));
		exitButton.setVisible(true);
		exitButton.addActionListener(this);
		exitButton.setCursor(cursor);
		gamePage.add(exitButton);
		
		
		
		
		//Keyboard Buttons
		button = new JButton[26];
		for(int i= 65;i<91;i++)
		{
			
			button[i-65] = new JButton();
			button[i-65].setText(Character.toString((char)i));//(Character.toString((char)i));
			button[i-65].setFont(new Font("TimesnewRoman",Font.BOLD,25));
			
			if(i>79)
			{
				button[i-65].setBounds(220 + (i-79)*70,500,60,60);
			}
			else
			{
				button[i-65].setBounds(150+ (i-65)*70,400,60,60);
			}
			
			button[i-65].setForeground(Color.white);
			button[i-65].setFocusable(false);
			button[i-65].setHorizontalTextPosition(JButton.CENTER);
			button[i-65].setBackground(Color.green);
			button[i-65].addActionListener(this);
			Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
			button[i-65].setCursor(cursor);
			gamePage.add(button[i-65]);
		}
		
		
		
		
		//Entry Boxes
		word = db.rs.getString(2);
		wordLength = word.length();
		text = new JTextField[wordLength];
		for(int i = 0;i< wordLength;i++)
		{
			text[i] = new JTextField();
			text[i].setFont(new Font("TimesnewRoman",Font.BOLD,30));
			text[i].setText("_");
			text[i].setBounds(500 + i*50,260,35,40);
			text[i].setHorizontalAlignment(JTextField.CENTER);
			text[i].setEditable(false);
			gamePage.add(text[i]);
		}
		
		
		
		//ImageSetup
		imageLabel = new JLabel();
		imageLabel.setBounds(1250,140,350,350);
		icon = new ImageIcon(imagePaths[chances]);
		Image img  = icon.getImage();
		Image newImage =  img.getScaledInstance(250, 350, Image.SCALE_DEFAULT);
		icon = new ImageIcon(newImage);
		imageLabel.setIcon(icon);
		imageLabel.setFont(new Font("TimesnewRoman",Font.BOLD,30));
		imageLabel.setVisible(true);
		gamePage.add(imageLabel);
		
		
		gamePage.setLayout(null);

		
		
		
 	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == exitButton)
		{
			System.exit(0);
		}
		
		else if (e.getSource() == backButton)
		{
			
			gamePage.dispose();
			
			return;
		}
		
		else if(e.getSource() == skipButton)
		{
			
			try 
			{
				gamePage.dispose();
				new GamePage();
				chances = 4;
				icon = new ImageIcon(imagePaths[chances]);
				Image img  = icon.getImage();
				Image newImage =  img.getScaledInstance(250, 350, Image.SCALE_DEFAULT);
				icon = new ImageIcon(newImage);
				imageLabel.setIcon(icon);
				
				
				for(int i1 = 0;i1<26;i1++)
				{
					button[i1].setVisible(true);
				}
				guess.setText("Chances remaining:" + chances);
			} 
			catch (Exception e1) 
			{
				
				e1.printStackTrace();
			}
			
			return;
		}
		
		
		validate();
		for(int k = 0;k<26;k++)
		{
			
			
			
			
			if(e.getSource() == button[k])
			{
				if(word.contains(button[k].getText()))
				{
					
					ArrayList<Integer> indexlist = findIndex(word,(char)(k + 65));
					button[k].setVisible(false);
					for(int i:indexlist)
					{
						text[i].setText(button[k].getText());
					}
					
					
				}
				else
				{
					
					
					chances -= 1;
					icon = new ImageIcon(imagePaths[chances]);
					Image img  = icon.getImage();
					Image newImage =  img.getScaledInstance(250, 350, Image.SCALE_DEFAULT);
					icon = new ImageIcon(newImage);
					imageLabel.setIcon(icon);
					
					if(chances == 0)
					{
						int a = JOptionPane.showConfirmDialog(gamePage, "You Lose the Game,Do you want to contnue..?", "Lose", JOptionPane.YES_NO_OPTION);
						
						
						if(a == 1)
						{
							System.exit(0);
							
						}
						for(int i = 0;i<wordLength;i++)
						{
							text[i].setText("_");
							
						}
						
						
						
						chances = 4;
						icon = new ImageIcon(imagePaths[chances]);
						img  = icon.getImage();
						newImage =  img.getScaledInstance(250, 350, Image.SCALE_DEFAULT);
						icon = new ImageIcon(newImage);
						imageLabel.setIcon(icon);
						guess.setText("Chances remaining:" + chances);
						
						
						for(int i = 0;i<26;i++)
						{
							button[i].setVisible(true);
						}
						
						
						return;
					}
					else
					{
						
						guess.setText("You loss the guess,Chances remaining:" + chances);
						guess.setVisible(true);
						
					}
					
					
				}
				
			}
		}
		
		
		validate();
		
	}
	
	
	//Finding Indices of Alphabets in Word.
	static ArrayList<Integer> findIndex(String word,char c)
	{
		
		
		ArrayList<Integer> indexlist = new ArrayList<Integer>();
		for(int i =0;i<wordLength;i++)
		{
			
			
			if(word.charAt(i) == c)
			{
				indexlist.add(i);
			}
			
			
		}
		
		return indexlist;
	}
	
	
	
	//Checking whether the word is correct or not.
	static void validate()
	{
		
		
		String check = "";
		
		for(int i = 0;i<wordLength;i++)
		{
			
			
			
			check += text[i].getText();
			
			if(check.equals(word))
			{
				
				
				
				int a = JOptionPane.showConfirmDialog(gamePage, "You won the Game,Do you want to contnue..?", "Won", JOptionPane.YES_NO_OPTION);
				if(a == 0)
				{
					
					
					try 
					{
					
						gamePage.dispose();
						new GamePage();
						chances = 4;
						icon = new ImageIcon(imagePaths[chances]);
						Image img  = icon.getImage();
						Image newImage =  img.getScaledInstance(250, 350, Image.SCALE_DEFAULT);
						icon = new ImageIcon(newImage);
						imageLabel.setIcon(icon);
						
						
						for(int i1 = 0;i1<26;i1++)
						{
							button[i1].setVisible(true);
						}
						
						for(int i1 = 0;i1<wordLength;i1++)
						{
							text[i1].setText("_");
							
						}
						
						guess.setText("Chances remaining:" + chances);
					} 
					catch (Exception e1)
					{
						
						e1.printStackTrace();
					}
					
				}
				
				else
				{
					System.exit(0);
				}
			}
		}
		
		System.out.println();
		return;
		
	}
	
	
}
