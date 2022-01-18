import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TicTacToe2player implements ActionListener
//ActionListener is an Interface which our class will implement
{
	ArrayList<Integer> Xplays=new ArrayList<Integer>();
	ArrayList<Integer> Oplays=new ArrayList<Integer>();
	
	Random rand=new Random();
	JFrame frame=new JFrame(); //Makes the frame
	JPanel title_panel=new JPanel(); //Add things to the frame this is for title
	JPanel button_panel=new JPanel(); //Hold all buttons
	JPanel lower_buttons=new JPanel();
	JLabel textfield=new JLabel(); //Adds text to Panel
	JButton[] buttons=new JButton[9]; //This is an array of buttons 
	JButton replayThis;
	JButton replayAnother;
	boolean player1_turn; //If player1 false its player2's turn
	
	public TicTacToe2player()
	{
		frame.setTitle("TicTacToe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Exits when cross is pressed
		frame.setSize(500,500);
		//Size in pixels
		frame.getContentPane().setBackground(new Color(50,50,50));
		//In Color we put the rgb values
		frame.setLayout(new BorderLayout());
		//A border layout lays out a container, arranging and resizing its components to fit in five regions: 
		//north, south, east, west, and center.
		frame.setVisible(true);
		
		textfield.setBackground(new Color(25,25,25)); //Background color of text
		textfield.setForeground(new Color(25,255,0));//Actual color of text
		textfield.setFont(new Font("MV Boli",Font.ITALIC,50)); //the last option is font size
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("TicTacToe");
		textfield.setOpaque(true);
		//Now we need to add textfield to title_panel and title_panel to frame
		
		title_panel.setLayout(new BorderLayout()); 
		//If I don't put this the title panel will not stretch across the entire screen
		title_panel.setBounds(0,0,500,60); 
		//Coordinated where we want it to start which is x,y first two and the size width and height next two
		//In the above case it starts from 0,0 within the frame and it is 500 pixels wide and 60 pixels tall
		
		button_panel.setLayout(new GridLayout(3,3));
		//3,3 grid
		button_panel.setBackground(new Color(150,150,150));
		
		//for loop to handle the array of buttons
		for(int i=0; i<9; i++)
		{
			buttons[i]=new JButton(); 
			button_panel.add(buttons[i]); //Adding every button to button panel
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setFocusable(false);
			//When its not false it creates a dotted line around the text
			buttons[i].addActionListener(this);
		}	
		replayThis=new JButton("Replay");
		replayAnother=new JButton("Play With CPU");
		replayThis.setBackground(new Color(75,75,75));
		replayAnother.setBackground(new Color(75,75,75));
		replayThis.setForeground(new Color(255,255,255));
		replayAnother.setForeground(new Color(255,255,255));
		
		replayThis.setFocusable(false);
		replayThis.setFont(new Font("MV Boli", Font.BOLD, 25));
		replayThis.addActionListener(this);
		replayAnother.setFocusable(false);
		replayAnother.setFont(new Font("MV Boli", Font.BOLD, 25));
		replayAnother.addActionListener(this);
		
		lower_buttons.setLayout(new GridLayout(1,2));
		
		lower_buttons.add(replayThis);
		lower_buttons.add(replayAnother);
		
		title_panel.add(textfield);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(lower_buttons, BorderLayout.SOUTH);
		frame.add(button_panel);
		//So the text doesn't cross the north part
		
		firstTurn();
	}
	
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
			for(int i=0; i<9; i++)
			{
				if(e.getSource()==buttons[i])
				//getSource is to tell which button was clicked
				{
					if(player1_turn)
					{
						if(buttons[i].getText()=="")
							//If there is no text on the button already
						{
							buttons[i].setForeground(new Color(0,0,255));
							//rgb and 255 is max value so player X is blue
							buttons[i].setText("X");
							//When that button is clicked it shows a giant X
							player1_turn=false;
							textfield.setText("O's Turn");
							Xplays.add(i);
							check();
						}
						//If the buttons have text it wont do anything
					}
					else
					{
						if(buttons[i].getText()=="")
						{
							buttons[i].setForeground(new Color(255,0,0));
							buttons[i].setText("O");
							player1_turn=true;
							textfield.setText("X's turn");
							Oplays.add(i);
							check();
						}
					}
				}
			}
			if(e.getSource()==replayThis)
			{
				frame.dispose();
				TicTacToe2player obj=new TicTacToe2player();
			}
			else if(e.getSource()==replayAnother)
			{
				frame.dispose();
				TicTacToeCPUEasy obj1=new TicTacToeCPUEasy();
			}
	}
	public void firstTurn()
	{
		try {
			Thread.sleep(1500);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(rand.nextInt(2)==0)
		//Between 0(inclusive) and <2
		{
			player1_turn=true;
			textfield.setText("X's turn");
		}		
		else
		{
			player1_turn=false;
			textfield.setText("O's turn");
		}
	}
	@SuppressWarnings("rawtypes")
	public void check()
	{
		List<Integer> topRow= Arrays.asList(0,1,2);
		List<Integer> midRow= Arrays.asList(3,4,5);
		List<Integer> botRow= Arrays.asList(6,7,8);
		List<Integer> leftCol=Arrays.asList(0,3,6);
		List<Integer> midCol= Arrays.asList(1,4,7);
		List<Integer> rightCol=Arrays.asList(2,5,8);
		List<Integer> cross1=Arrays.asList(0,4,8);
		List<Integer> cross2=Arrays.asList(2,4,6);
		
		List<List> winnings=new ArrayList<List>();
		
		winnings.add(topRow);
		winnings.add(midRow);
		winnings.add(botRow);
		winnings.add(leftCol);
		winnings.add(rightCol);
		winnings.add(midCol);
		winnings.add(cross2);
		winnings.add(cross1);
		
		for(List l:winnings)
		{
			if(Xplays.containsAll(l))
			{
				int a=(int)l.get(0);
				int b=(int)l.get(1);
				int c=(int)l.get(2);
				xWins(a,b,c);
			}
			else if(Oplays.containsAll(l))
			{
				int a=(int)l.get(0);
				int b=(int)l.get(1);
				int c=(int)l.get(2);
				oWins(a,b,c);
			}
		}
		if(Oplays.size()+Xplays.size()==9)
		{
			if(textfield.getText().equals("X Wins!")||textfield.getText().equals("O Wins!"))
			{
				textfield.setBackground(new Color(25,25,25));
			}
			else
			{
				textfield.setText("It is a Tie!");
				for(int i=0; i<9; i++)
				{
					buttons[i].setEnabled(false);
				}
			}
		}
	}
	public void xWins(int a, int b, int c)
	//Parameters takes in the buttons on which x wins to make it green
	{
		buttons[a].setBackground(new Color(0,255,0));
		buttons[b].setBackground(new Color(0,255,0));
		buttons[c].setBackground(new Color(0,255,0));
		textfield.setText("X Wins!");
		
		for(int i=0; i<9; i++)
		{
			buttons[i].setEnabled(false);
			//disabling all the buttons
		}
	}
	public void oWins(int a, int b, int c)
	{
		buttons[a].setBackground(new Color(0,255,0));
		buttons[b].setBackground(new Color(0,255,0));
		buttons[c].setBackground(new Color(0,255,0));
		textfield.setText("O Wins!");
		
		for(int i=0; i<9; i++)
		{
			buttons[i].setEnabled(false);
		}
	}
}
