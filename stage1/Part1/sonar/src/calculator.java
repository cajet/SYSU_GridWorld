import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;



public class calculator {
	public static void main(String args[]) {
		Runnable tr = new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		};
		javax.swing.SwingUtilities.invokeLater(tr);
	}
	
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Easy Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Pane`s layout
		Container cp = frame.getContentPane();
		cp.setLayout(new FlowLayout());
		
		//add interactive panel to Content Pane
		TextPanel text = new TextPanel();
		cp.add(text);
		cp.add(new ButtonPanel(text));
		
		//show the window
		frame.pack();
		frame.setSize(300, 100);
		frame.setVisible(true);
	}
}

/*Method 1:disabled keyEvent
class KeyAdapter implements KeyListener{
	public void keyTyped(KeyEvent e) {
		int keyChar = e.getKeyChar();
		if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9 || keyChar == KeyEvent.VK_PERIOD || keyChar >= KeyEvent.VK_MINUS) {
			;
		} else {
			e.consume();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
*/

class TextPanel extends JPanel {
	JTextField my_t1;
	JTextField my_t2;
	JTextField my_t3;
	JTextField my_t4;
	
	public TextPanel() {
		my_t1 = new JTextField(5);
		my_t2 = new JTextField(5);
		my_t3 = new JTextField(5);
		my_t4 = new JTextField(5);
		JLabel my_t5 = new JLabel(" = ");
				
		
		this.add(my_t1);
		this.add(my_t2);
		this.add(my_t3);
		this.add(my_t5);
		this.add(my_t4);
		my_t2.setEditable(false);
		
		//KeyAdapter k = new KeyAdapter();
		//my_t1.addKeyListener(k);
		//my_t2.addKeyListener(k);
		//my_t3.addKeyListener(k);
	}
}


class ButtonPanel extends JPanel {
	private TextPanel my_text;
	private int my_op;
	
	public ButtonPanel(TextPanel test) {
		this.my_text = test;
		
		JButton my_Add = new JButton("+");
		JButton my_Sub = new JButton("-");
		JButton my_Mul = new JButton("*");
		JButton my_Div = new JButton("/");
		JButton my_Ok = new JButton("OK");
		
		
		this.add(my_Add);
		this.add(my_Sub);
		this.add(my_Mul);
		this.add(my_Div);
		this.add(my_Ok);
		
		my_Action okAction = new my_Action(0);
		my_Action addAction = new my_Action(1);
		my_Action subAction = new my_Action(2);
		my_Action mulAction = new my_Action(3);
		my_Action divAction = new my_Action(4);
		
		my_Ok.addActionListener(okAction);
		my_Add.addActionListener(addAction);
		my_Sub.addActionListener(subAction);
		my_Mul.addActionListener(mulAction);
		my_Div.addActionListener(divAction);
	}
	
	private class my_Action implements ActionListener
	{
		private int type;
		public my_Action(int n) {
			type = n;
		}
		
		public void actionPerformed(ActionEvent event)
		{
			my_text.my_t2.setEditable(true);
			switch(this.type) {
			case 0:
				{
					double num1, num2, result;
					if (my_text.my_t1.getText().equals("")) {
						;
					} else if (my_text.my_t2.getText().equals("")){
						;
					} else if(my_text.my_t3.getText().equals("")) {
						;
					} else {
						String s1 = "^-?[1-9]*\\.?\\d*$";
						if (!my_text.my_t1.getText().matches(s1) || !my_text.my_t3.getText().matches(s1)) {
							JOptionPane.showMessageDialog(null, "Invalid input!Try again!");
							return;
						}
						num1 = Double.valueOf(my_text.my_t1.getText()).doubleValue();
						num2 = Double.valueOf(my_text.my_t3.getText()).doubleValue();
						switch(my_op) {
							case 1:
								result = num1 + num2;
								break;
							case 2:
								result = num1 - num2;
								break;
							case 3:
								result = num1 * num2;
								break;
							default:
								if (num2 == 0) {
									JOptionPane.showMessageDialog(null, "0 cannot be divisor!");
									return;
								}
								result = num1 / num2;
								break;
						}
						my_text.my_t4.setText(Double.toString(result));
					}
					
					
				}
				break;
			case 1:
				my_op = 1;
				my_text.my_t2.setText("     +");
				break;
			case 2:
				my_op = 2;
				my_text.my_t2.setText("     -");
				break;
			case 3:
				my_op = 3;
				my_text.my_t2.setText("     *");
				break;
			default:
				my_op = 4;
				my_text.my_t2.setText("     /");
				break;
			}
			my_text.my_t2.setEditable(false);
		}
	}
}




