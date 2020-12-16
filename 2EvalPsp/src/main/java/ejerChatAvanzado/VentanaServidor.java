package ejerChatAvanzado;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaServidor extends JFrame {
	private JTextField textField;
	private Servidor servidor;

	public VentanaServidor() {
		setSize(450, 320);
		setTitle("Ventana Servidor");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 11, 414, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 42, 414, 174);
		textArea.setText("Esperando conexiones...");
		getContentPane().add(textArea);

		JButton btnNewButton = new JButton("SALIR");
		btnNewButton.setBounds(165, 227, 89, 23);
		getContentPane().add(btnNewButton);

		servidor = new Servidor(textArea, textField);
		servidor.start();

		btnNewButton.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// para que deje lanzar varias ventanaIntro
				// pero de momento la 2 no se conecta creo que por que intenta ir al mismo
				// puerto
				try {
					servidor.desconectar();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
	}

	public static void main(String[] args) {
		VentanaServidor serv = new VentanaServidor();
		serv.setVisible(true);

	}
}
