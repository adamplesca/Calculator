// Simple calculator I made in Java, watched & followed a youtube tutorial but commented almost all the code to actually learn from this video
// Adam Plesca
// 10/5/23

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{

	JFrame frame; //creating calculator frame
	JTextField textField; //cal textField
	JButton[] numberButtons = new JButton[10]; //array of cal num buttons 0-9
	JButton[] functionButtons = new JButton[9]; //array of cal function buttons (+,-,*,/)
	JButton addButton,subButton,mulButton,divButton,decButton,equButton,delButton,clrButton,negButton; //all func buttons
	JPanel panel; //cal panel

	Font myFont = new Font("Century Gothic",Font.BOLD,20); //calcultor font

	double num1=0,num2=0,result=0;
	char operator;

	//calc constructor
	Calculator(){
		frame = new JFrame("Calculator"); //cal name for frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //cal closes when you exit out of app
		frame.setSize(420,550); //dimensions of cal
		frame.setLayout(null); //no layout for cal

		textField = new JTextField(); //creates cal textField
		textField.setBounds(50,25,300,50); //textfield dimensions
		textField.setFont(myFont); //universal font for calc
		textField.setEditable(false); //you can't type into the textbox yet but can use buttons to imput nums in calc

		//creating the func buttons for the calc here
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("Delete");
		clrButton = new JButton("Clear");
		negButton = new JButton("(-)");

		//assigns func buttons to cal array
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;

		//loops through array to listen for button clicks
		for(int i=0;i<9;i++){
			functionButtons[i].addActionListener(this); //listens for func button clicks
			functionButtons[i].setFont(myFont); //adds font to buttons
			functionButtons[i].setFocusable(false); // removes annoying outline when clicked
		}

		//anonymous for the buttons
		for(int i=0;i<10;i++){
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this); //listens for func button clicks
			numberButtons[i].setFont(myFont); //adds font to buttons
			numberButtons[i].setFocusable(false); // removes annoying outline when clicked
		}

		//adding the del and clr button out of calc button grid
		negButton.setBounds(50,430,100,50);
		delButton.setBounds(150,430,100,50);//location for del btn
		clrButton.setBounds(250,430,100,50);//location for clr btn

		//making jpanel for buttons
		panel = new JPanel();
		panel.setBounds(50,100,300,300); //panel dimensions
		panel.setLayout(new GridLayout(4,4,10,10)); //
		panel.setBackground(new Color(0xADD8E6)); //makes panel light blue, matches calc buttons

		//adding all the buttons to the panels
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		panel.add(decButton);
		panel.add(numberButtons[0]);
		panel.add(equButton);
		panel.add(divButton);

		//adding everything in construtor here
		frame.add(panel);//adds panel for btns
		frame.add(negButton);//adds negetive number btn
		frame.add(delButton);//adds del btn
		frame.add(clrButton); // adds clr btn
		frame.add(textField); //adds textfield
		frame.setVisible(true); //makes it visible once everything is done
	}

	//main method
	public static void main(String args[]){
		//creating calc object
		Calculator calc = new Calculator();
	}

	//actionListener method (this is where all the functionality of the buttons go
	@Override
	public void actionPerformed(ActionEvent e){

		//for loop for button to register when clicked
		for(int i =0; i<10;i++){
			if(e.getSource() == numberButtons[i]) {
				textField.setText(textField.getText().concat(String.valueOf(i))); //concatonates a string
			}
		}
		if(e.getSource()==decButton){ //registers when you click the deicmal btn on calc
			textField.setText(textField.getText().concat("."));
		}
		if(e.getSource()==addButton){ //registers when you click the add btn on calc and clears the num to adda new num to it
			num1 = Double.parseDouble(textField.getText());
			operator = '+'; //adds op
			textField.setText(""); //clears screen after pressing the add btn
		}
		if(e.getSource()==subButton){ //registers when you click the sub btn on calc and clears the num to sub a new num to it
			num1 = Double.parseDouble(textField.getText());
			operator = '-'; //minus op
			textField.setText(""); //clears screen after pressing the sub btn
		}
		if(e.getSource()==mulButton){ //registers when you click the mul btn on calc and clears the num to mul a new num to it
			num1 = Double.parseDouble(textField.getText());
			operator = '*'; //muls op
			textField.setText(""); //clears screen after pressing the mul btn
		}
		if(e.getSource()==divButton){ //registers when you click the div btn on calc and clears the num to div a new num to it
			num1 = Double.parseDouble(textField.getText());
			operator = '/'; //div op
			textField.setText(""); //clears screen after pressing the div btn
		}
		if(e.getSource()==equButton){ //registers when you click the equ btn on calc it give the output
			num2 = Double.parseDouble(textField.getText());
			//switch case for all the operator btns when used they preform the calculations needed
			switch(operator){
				case'+':
					result = num1 + num2;
					break;
				case'-':
					result = num1 - num2;
					break;
				case'*':
				    result = num1 * num2;
					break;
				case'/':
				    result = num1 / num2;
					break;
			}
			//gets result from switch case
			textField.setText(String.valueOf(result));
			num1=result;
		}
		//clears away text
		if(e.getSource()==clrButton){
			textField.setText("");
		}
		//delete button, will loop through if btn is pressed to delete the previously input value until user is happy
		if(e.getSource()==delButton){
			String string = textField.getText();
			textField.setText("");
			for(int i =0;i<string.length()-1;i++){
				textField.setText(textField.getText()+string.charAt(i));
			}
		}
		//adds negetive number button so it can minus negitive nums
		if(e.getSource()==negButton) {
			double temp = Double.parseDouble(textField.getText());
			temp*=-1;
			textField.setText(String.valueOf(temp));
		}
	}
}
