import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TicTacToeCPUMedium implements ActionListener
{
	ArrayList<Integer> Playerplays=new ArrayList<Integer>();
	ArrayList<Integer> Computerplays=new ArrayList<Integer>();
	
	Random rand=new Random();
	JFrame frame=new JFrame(); 
	JPanel title_panel=new JPanel();
	JPanel button_panel=new JPanel(); 
	JPanel lower_buttons=new JPanel();
	JLabel textfield=new JLabel(); 
	JButton[] buttons=new JButton[9]; 
	JButton replayThis;
	JButton replayAnother;
	
	@SuppressWarnings("rawtypes")
	JComboBox choice; //Drop Down List
	
	boolean player1_turn; 
	boolean player_is_x;
	String[] options= {"Easy","Medium","Hard"}; //Drop Down List options
	
	//For check winner and check missing position
	List<Integer> topRow= Arrays.asList(0,1,2);
	List<Integer> midRow= Arrays.asList(3,4,5);
	List<Integer> botRow= Arrays.asList(6,7,8);
	List<Integer> leftCol=Arrays.asList(0,3,6);
	List<Integer> midCol= Arrays.asList(1,4,7);
	List<Integer> rightCol=Arrays.asList(2,5,8);
	List<Integer> cross1=Arrays.asList(0,4,8);
	List<Integer> cross2=Arrays.asList(2,4,6);
	
	@SuppressWarnings("rawtypes")
	List<List> winnings=new ArrayList<List>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TicTacToeCPUMedium()
	{
		//For check winner
		winnings.add(topRow);
		winnings.add(midRow);
		winnings.add(botRow);
		winnings.add(leftCol);
		winnings.add(rightCol);
		winnings.add(midCol);
		winnings.add(cross2);
		winnings.add(cross1);
		
		frame.setTitle("TicTacToe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.getContentPane().setBackground(new Color(50,50,50));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		textfield.setBackground(new Color(25,25,25)); 
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("MV Boli",Font.ITALIC,50)); 
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("TicTacToe");
		textfield.setOpaque(true);

		title_panel.setLayout(new BorderLayout());
		title_panel.setBounds(0,0,500,60); 
		
		button_panel.setLayout(new GridLayout(3,3));
		button_panel.setBackground(new Color(150,150,150));
		
		choice=new JComboBox(options);
		choice.addActionListener(this);
		choice.setSelectedItem("Medium");
		choice.setFont(new Font("MV Boli",Font.BOLD,15)); 
		choice.setBackground(new Color(165, 183, 201));
		choice.setForeground(new Color(255,255,255));
		choice.setBorder(null);
		choice.setEditable(false);
		
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
		replayAnother=new JButton("Play 2 Player");
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
		frame.add(lower_buttons, BorderLayout.SOUTH);
		frame.add(title_panel, BorderLayout.NORTH);
		frame.add(button_panel);
		frame.add(choice, BorderLayout.WEST);
		
		firstTurn();
	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) 
	{
			for(int i=0; i<9; i++)
			{
				if(e.getSource()==buttons[i])
				{
					if(player1_turn)
					{
						if(buttons[i].getText()=="")
						{
							if(player_is_x)
							{
								buttons[i].setForeground(new Color(0,0,255));
								buttons[i].setText("X");
								int positionInformed=10;
								player1_turn=false;
								Playerplays.add(i);								
								String what="";
								what=check(); //Only needed after players chance as computer still places its chance after winning
								//Not needed after computers as all buttons get disabled
								Random rand=new Random();
								while(true)
								{
									if(what.length()>1)
										break;
									else
									{
										positionInformed=returnAIPosition();
										int num=rand.nextInt(9);
										if(buttons[num].getText()=="")
										{
											if(positionInformed<=9) //If the computer found a position to play
											{
												buttons[positionInformed].setForeground(new Color(255,0,0));
												buttons[positionInformed].setText("O");
												player1_turn=true;
												textfield.setText("Player's Turn");
												Computerplays.add(positionInformed);												
												check();
												break;
											}
											else //If the computer did not find anything and is playing random move
											{
												buttons[num].setForeground(new Color(255,0,0));
												buttons[num].setText("O");
												player1_turn=true;
												textfield.setText("Player's Turn");
												Computerplays.add(num);
												check();
												break;
											}
										}
										else
											continue;
									}
								}
							}
							else
							{
								buttons[i].setForeground(new Color(0,0,255));
								//rgb and 255 is max value so player X is blue
								buttons[i].setText("O");
								//When that button is clicked it shows a giant X
								player1_turn=false;
								int positionInformed=10;
								Playerplays.add(i);								
								String what="";
								what=check();
								Random rand=new Random();
								while(true)
								{
									if(what.length()>1)
										break;
									else
									{
										positionInformed=returnAIPosition();
										int num=rand.nextInt(9);
										if(buttons[num].getText()=="")
										{
											if(positionInformed<=9)
											{
												buttons[positionInformed].setForeground(new Color(255,0,0));
												buttons[positionInformed].setText("X");
												player1_turn=true;
												textfield.setText("Player's Turn");
												Computerplays.add(positionInformed);												
												check();
												break;
											}
											else
											{
												buttons[num].setForeground(new Color(255,0,0));
												buttons[num].setText("X");
												player1_turn=true;
												textfield.setText("Player's Turn");
												Computerplays.add(num);												
												check();
												break;
											}
										}
										else
											continue;
									}
								}
							}		
						}
						//If the buttons have text it wont do anything
					}
				}
			}
			if(e.getSource()==replayThis)
			{
				frame.dispose();
				TicTacToeCPUMedium obj=new TicTacToeCPUMedium();
			}
			else if(e.getSource()==replayAnother)
			{
				frame.dispose();
				TicTacToe2player obj1=new TicTacToe2player();
			}
			else if(e.getSource()==choice)
			{
				if(choice.getSelectedItem()=="Hard")
				{
					frame.dispose();
					TicTacToeCPUHard obj2=new TicTacToeCPUHard();
				}
				else if(choice.getSelectedItem()=="Easy")
				{
					frame.dispose();
					TicTacToeCPUEasy obj3=new TicTacToeCPUEasy();
				}
			}
	}
	public void firstTurn()
	{
		if(rand.nextInt(2)==0)
		//Between 0(inclusive) and <2
		{
			player1_turn=true;
			player_is_x=true;
			textfield.setText("Player is X");
			try {
				Thread.sleep(1000);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			textfield.setText("X's Chance");
		}		
		else
		{
			player1_turn=true;
			player_is_x=false;
			textfield.setText("Player is O");
			try {
				Thread.sleep(1000);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			textfield.setText("O's Chance");
		}
	}
	@SuppressWarnings("rawtypes")
	public String check()
	{
		for(List l:winnings)
		{
			if(Playerplays.containsAll(l))
			{
				int a=(int)l.get(0);
				int b=(int)l.get(1);
				int c=(int)l.get(2);
				String message="";
				message=playerWins(a,b,c);
				if(message.length()>1)
				{
					return message;
				}
			}
			else if(Computerplays.containsAll(l))
			{
				int a=(int)l.get(0);
				int b=(int)l.get(1);
				int c=(int)l.get(2);
				cpuWins(a,b,c);
			}
		}
		if(Playerplays.size()+Computerplays.size()==9)
		{
			if(textfield.getText().equals("You Win!")||textfield.getText().equals("You Loose :("))
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
				return "It is a tie";
			}
		}
		return "";
	}
	@SuppressWarnings("rawtypes")
	public int returnAIPosition()
	{
		/*
		How the Algorithm works-
		First it checks if it can win by seeing if the 2 positions of computer's chance is occupied and places the third.
		
		If it is not winning it goes on to check if it is loosing (If two positions of winning are occupied by the player in its
		ArrayList and places the third to block.
		
		{
			If the player is not winning nor is the computer but the center of the board is empty it plays that so that it can prevent double traps.
			If the center is occupied and neither the computer nor the player is winning the computer goes the the second most important place- Corner and checks each of them
			If corner is occupied it just return 10 which can't be played on board so computer plays random out of 4 places between the corners
			The move above is very important to prevent double traps as the center position controls all point- THIS MAKES THE ALGORITHM UNBEATABLE
		}

		I haven't used MiniMax algorithm (decision tree) just used the logic of seeing if 2 positions of each winning list are occupied or not		
		*/
		int position = 10; //Position can get changed even if their is text at that place so I am taking a variable changed instead
		int changed=0;
		
		for(List l:winnings)
		{
			if((Computerplays.contains(l.get(0))&&Computerplays.contains(l.get(1))))
			{
				position=(int)l.get(2);
				if((buttons[position].getText().length())>=1) //Since X/O are only 1 character therefore >=
					continue;
				else
				{
					changed++;
					return position;
				}
			}
			else if(Computerplays.contains(l.get(1))&&Computerplays.contains(l.get(2)))
			{
				position=(int)l.get(0);
				if((buttons[position].getText().length())>=1)
					continue;
				else
				{
					changed++;
					return position;
				}
			}
			else if(Computerplays.contains(l.get(0))&&Computerplays.contains(l.get(2)))
			{
				position=(int)l.get(1);
				if((buttons[position].getText().length())>=1)
					continue; //If position is occupied it will check for the next list
				else
				{
					changed++;
					return position;
				}
			}
		}
		if(changed==0)
		{
			for(List l:winnings)
			{
				if((Playerplays.contains(l.get(0))&&Playerplays.contains(l.get(1))))
				{
					position=(int)l.get(2);
					if((buttons[position].getText().length())>=1)
						continue;
					else
					{
						changed++;
						return position;
					}
				}
				else if(Playerplays.contains(l.get(1))&&Playerplays.contains(l.get(2)))
				{
					position=(int)l.get(0);
					if((buttons[position].getText().length())>=1)
						continue;
					else
					{
						changed++;
						return position;
					}
				}
				else if(Playerplays.contains(l.get(0))&&Playerplays.contains(l.get(2)))
				{
					position=(int)l.get(1);
					if((buttons[position].getText().length())>=1)
						continue;
					else
					{
						changed++;
						return position;
					}
				}
			}
		}
		//The above algorithm is to win and block in-case none of that happens the one below gives weight to each place
		//The center is first priority then comes the corners, if the corner is occupied then random the places between the corner
		
		if(changed==0)
		{
			Random random=new Random();
			if((random.nextInt(10)%3==0)) //In medium 1 in 3 chaces it will play smartly
			{
				if((buttons[4].getText())=="") //If the center is occupied check for the corners
					return 4;
				//For double trap which does not comply with the below rules
				else if(Playerplays.contains(1)&&Playerplays.contains(3))
				{
					if((buttons[0].getText())=="")
						return 0;
				}
				else if(Playerplays.contains(1)&&Playerplays.contains(5))
				{
					if((buttons[2].getText())=="")
						return 2;
				}
				else if(Playerplays.contains(7)&&Playerplays.contains(3))
				{
					if((buttons[6].getText())=="")
						return 6;
				}
				else if(Playerplays.contains(7)&&Playerplays.contains(5))
				{
					if((buttons[8].getText())=="")
						return 8;
				}
				else if(Playerplays.contains(0)&&Playerplays.contains(7))
				{
					if((buttons[3].getText())=="")
						return 3;
					if((buttons[6].getText()==""))
						return 6;
				}
				else if(Playerplays.contains(2)&&Playerplays.contains(7))
				{
					if((buttons[5].getText())=="")
						return 5;
					if((buttons[8].getText())=="")
						return 8;
				}
				//For double trap which does not comply with the below rules
				
				else if(Playerplays.contains(4))
				{
					if((buttons[0].getText())=="") //If corner 1 is occupied check for 2nd corner
						return 0;
					else if((buttons[2].getText())=="") //If corner 2 is occupied check for 3rd corner
						return 2;
					else if((buttons[6].getText())=="") //If corner 3 is occupied check for 4th corner
						return 6;
					else if((buttons[8].getText())=="") //If corner 4 is occupied just play any random for the positions between the corner
						return 8;
					else 
						changed=0;
				}
				else if((buttons[1].getText())=="") //If corner 1 is occupied check for 2nd corner
					return 1;
				else if((buttons[3].getText())=="") //If corner 2 is occupied check for 3rd corner
					return 3;
				else if((buttons[5].getText())=="") //If corner 3 is occupied check for 4th corner
					return 5;
				else if((buttons[7].getText())=="") //If corner 4 is occupied just play any random for the positions between the corner
					return 7;
				else 
					changed=0;
			}
		}
		else
			return 10; //If the computer neither finds a place to block nor to win it will play random place
		return 10;
	}
	public String playerWins(int a, int b, int c)
	{
		buttons[a].setBackground(new Color(0,255,0));
		buttons[b].setBackground(new Color(0,255,0));
		buttons[c].setBackground(new Color(0,255,0));
		textfield.setText("You Win!");
		
		for(int i=0; i<9; i++)
		{
			buttons[i].setEnabled(false);
			//disabling all the buttons
		}
		return "Player has won";
	}
	public void cpuWins(int a, int b, int c)
	{
		buttons[a].setBackground(new Color(255,0,0));
		buttons[b].setBackground(new Color(255,0,0));
		buttons[c].setBackground(new Color(255,0,0));
		textfield.setText("You Loose :(");
		
		for(int i=0; i<9; i++)
		{
			buttons[i].setEnabled(false);
		} 
	}
} 
