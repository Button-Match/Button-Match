import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//btn1.setBackground(colors[index]
public class GameM implements ActionListener {
	//Name of popup
	JFrame frame = new JFrame("Button Match");
	JPanel field = new JPanel();
	JPanel menu = new JPanel();
	JPanel menu2 = new JPanel();
	JPanel menu3 = new JPanel();
	JPanel mini = new JPanel();
	JPanel start_screen = new JPanel();
	JPanel end_screen = new JPanel();
	JButton btn[] = new JButton[20];
    JButton easy = new JButton("color");
    JButton hard = new JButton("Shapes");
    
    Random randomGenerator = new Random();
    private boolean purgatory = false;
	Boolean game_over = false;
	
	String[] board;
	int[] boardQ=new int[20];
	Boolean shown = true;
	int temp=30;
	int temp2=30;
	String a[]=new String[10];
	boolean eh=true;
	
	private JTextField text = new JTextField(10);
	
	public GameM(){//Main menu
		frame.setSize(300,100);
		frame.setLocation(100,100);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		start_screen.setLayout(new BorderLayout());
		menu2.setLayout(new FlowLayout(FlowLayout.CENTER));
		menu3.setLayout(new FlowLayout(FlowLayout.CENTER));
		mini.setLayout(new FlowLayout(FlowLayout.CENTER));

		start_screen.add(menu3, BorderLayout.CENTER);
		start_screen.add(menu2, BorderLayout.SOUTH);
		menu3.add(mini, BorderLayout.CENTER);
		menu.add(text);
		mini.add(easy, BorderLayout.NORTH);
		mini.add(hard, BorderLayout.NORTH);
		
		easy.addActionListener(this);
		easy.setEnabled(true);
		hard.addActionListener(this);
		hard.setEnabled(true);
		
		frame.add(start_screen, BorderLayout.CENTER);
		frame.setVisible(true);
	}	
	public void setUpGame(int x,Boolean what){//Game setup
		frame.setSize(800,800);
		frame.setLocation(100,100);
		x = 8;
		clearMain();
		
		board = new String[2*x];
		for(int i=0;i<(x*2);i++){
			btn[i] = new JButton("");
			btn[i].setBackground(new Color(220, 220, 220));
			btn[i].addActionListener(this);
			btn[i].setEnabled(true);
			field.add(btn[i]);
		
		}
		
		String[] b = {"✈︎","̸̇̎/̸̄̿̅̎̎̅͆ ͆͆͆͆̔̿͞ ͆̅̿̄͞ ̿ ̄̇̿̚ ̎ ̎͆","▬▬ι═══════ﺤ ","⚔","⌐╦╦═─","▬ι═ﺤ","\"▄︻̷̿┻̿═━一","︻╦̵̵͇══╤─"};;//shapes version
		String[] c = {"RED","GREEN","BLUE","YELLOW","ORANGE","PINK","GREY","MAGENTA"};//color version
		if(what) a=c;//if what is true, make the game easy and use c
		else a=b;//otherwise make it hard and use b
			
		for(int i=0;i<x;i++){
				for(int z=0;z<2;z++){
					while(true){	
						int y = randomGenerator.nextInt(x*2);
						if(board[y]==null){
							btn[y].setText(a[i]);
							if (a[i] == "RED"){
								btn[y].setBackground(Color.RED);
							}else if (a[i] == "GREEN"){
								btn[y].setBackground(Color.GREEN);
							}else if (a[i] == "BLUE"){
								btn[y].setBackground(Color.BLUE);
							}else if (a[i] == "YELLOW"){
								btn[y].setBackground(Color.YELLOW);
							}else if (a[i] == "ORANGE"){
								btn[y].setBackground(Color.ORANGE);
							}else if (a[i] == "RPINKD"){
								btn[y].setBackground(Color.PINK);
							}else if (a[i] == "GREY"){
								btn[y].setBackground(Color.GRAY);
							}else if (a[i] == "MAGENTA"){
								btn[y].setBackground(Color.MAGENTA);
							}
							board[y]=a[i];
							break;
						}
					}
				}
				
			
		}
		createBoard();
		
	}
	public void hideField(int x){//start game blank
		for(int i=0;i<(x*2);i++){
			/*if(boardQ[i]==0)*/ btn[i].setText("");	
			btn[i].setBackground(null);		
		}
		shown=false;
	}
	public void switchSpot(int i){//this will switch the current spot to either blank or what it should have
		if(board[i]!="done"){//when a match is correctly chosen, it will no longer switch when pressed
		if(btn[i].getText()==""){
			btn[i].setText(board[i]);

		} else {
			btn[i].setText("");
			shown=false;
		}
		}
	}
	public void showSpot(int i){
		btn[i].setText(board[i]);
	}
	public void showField(int x, String a[]){//this shows all the symbols on the field
		for(int i=0;i<(x*2);i++){
			btn[i].setText(a[i]);
		}
		shown=true;
	}
	public boolean checkWin(){//checks if every spot is labeled as done
		for(int i=0;i<(8*2);i++){
			if (board[i]!="done")
			return false;
		}
		winner();
		return true;
	}
	public void createBoard(){//Grid for buttons
		field.setLayout(new BorderLayout());
		start_screen.add(field, BorderLayout.CENTER);
		
		field.setLayout(new GridLayout(4,4,2,2));
		field.setBackground(Color.black);
		field.requestFocus();
	}
	public void clearMain(){//Clears all menu for fresh menu without old ones
		start_screen.remove(menu2);
		start_screen.remove(menu3);

    start_screen.revalidate();
    start_screen.repaint();
	}
	public void actionPerformed(ActionEvent click){//Give buttons things it listens to
		Object source = click.getSource(); //on click actions
		
		
		if(purgatory){
			switchSpot(temp2);
			switchSpot(temp);
			temp=(8*2);
			temp2=30;
			purgatory=false;
		}
		
		if(source==easy){//Chooses which gamemode to play color or the other one
			eh=true;
			setUpGame(8, eh);
		} 
		else if(source==hard){
			eh=false;
			setUpGame(8, eh);
		}
		
		for(int i =0;i<(8*2);i++){//gameplay when a button is pressed
			if(source==btn[i]){
				if(shown){
					hideField(8);//if first time, hides field
				}else{//otherwise play
					switchSpot(i);
					if(temp>=(8*2)){
						temp=i;
					} else {
						if((board[temp]!=board[i])||(temp==i)){
							temp2=i;
							purgatory=true;
							
							
						} else {
							board[i]="done";
							board[temp]="done";
							checkWin();
							temp=(8*2);
							
						}
						
					}
				}
				
			}
			
			
		}
		

	}
	public void winner(){//on win repeat game
		frame.dispose();
		new GameM();
	}
	public static void main(String[] args) {//starts the program
		new GameM();// Calling the class construtor.
	}
	
}