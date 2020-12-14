package ejerChatAvanzado;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaCliente extends JFrame {
	private JTextField textField;
	private String nick;

	public VentanaCliente() {
		setSize(453, 359);
		setTitle("Ventana Cliente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 24, 313, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.setBounds(333, 23, 102, 23);
		getContentPane().add(btnEnviar);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 55, 425, 210);
		getContentPane().add(textArea);

		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBounds(172, 293, 89, 23);
		getContentPane().add(btnSalir);

		btnSalir.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		VentanaCliente ventana = new VentanaCliente();
		ventana.setVisible(true);

	}
}
