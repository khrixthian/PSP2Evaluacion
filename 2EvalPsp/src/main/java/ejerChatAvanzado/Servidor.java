package ejerChatAvanzado;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Servidor extends JFrame {
	private JTextField textField;

	public Servidor() {
		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 11, 414, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 42, 414, 174);
		getContentPane().add(textArea);

		JButton btnNewButton = new JButton("SALIR");
		btnNewButton.setBounds(165, 227, 89, 23);
		getContentPane().add(btnNewButton);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
