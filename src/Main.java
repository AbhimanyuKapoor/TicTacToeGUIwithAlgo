import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main implements ActionListener
{
	JFrame frame=new JFrame();
	JPanel title=new JPanel();
	JPanel buttons=new JPanel();
	JButton cpu=new JButton();
	JButton player=new JButton();
	JLabel textfield=new JLabel();
	
	public Main()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TicTacToe Choice");
		frame.setSize(300,130);
		frame.getContentPane().setBackground(new Color(100,100,100));
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setResizable(false); //Can't be resized
		
		textfield.setBackground(new Color(50,50,50));
		textfield.setForeground(new Color(25,255,0));
		textfield.setFont(new Font("MV Boli",Font.ITALIC,25));
		textfield.setHorizontalAlignment(JLabel.CENTER);
		textfield.setText("Choose Game Mode:");
		textfield.setOpaque(true);

		title.setLayout(new BorderLayout());
		
		buttons.setLayout(new GridLayout(1,2));
		buttons.setBackground(new Color(100,100,100));
		
		player=new JButton("2 Player");
		cpu=new JButton("CPU");
		player.setFocusable(false);
		cpu.setFocusable(false);
		player.addActionListener(this);
		cpu.addActionListener(this);
		buttons.add(player);
		buttons.add(cpu);
		player.setBackground(new Color(75,75,75));
		cpu.setBackground(new Color(75,75,75));
		player.setFont(new Font("MV Boli",Font.BOLD,25));
		player.setForeground(new Color(255,255,255));
		cpu.setFont(new Font("MV Boli",Font.BOLD,25));
		cpu.setForeground(new Color(255,255,255));
		
		title.add(textfield);
		frame.add(title, BorderLayout.NORTH);
		frame.add(buttons);
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{
		int count=0;
		Main obj=new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==player)
		{
			new TicTacToe2player();
			frame.dispose();
		}
		else
		{
			new TicTacToeCPUEasy();
			frame.dispose();
		}			
	}
}
