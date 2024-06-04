import javax.swing.*;  

public class buttonframe extends JFrame{//inheriting JFrame  
    JFrame f;  
    buttonframe(){  
    JButton b=new JButton("click");//create button  
    b.setBounds(1,100,100, 40);
    JButton c=new JButton("click");//create button  
    c.setBounds(101,100,100, 40);

            
    add(b);//adding button on frame  
    add(c);
    setSize(330,200);  
    setLayout(null);  
    setVisible(true);  
    
}  
public static void main(String[] args) {  
    new buttonframe();  
    }
}  
