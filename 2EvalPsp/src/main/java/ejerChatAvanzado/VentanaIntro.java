package ejerChatAvanzado;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaIntro extends JFrame {
	private JTextField textField;

	public VentanaIntro() {
		setSize(276, 170);
		setTitle("Ventana Cliente Entrada");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(22, 36, 229, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Escribe tu nick:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(22, 11, 110, 14);
		getContentPane().add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("ACEPTAR");
		btnNewButton_1.setBounds(83, 87, 89, 23);
		getContentPane().add(btnNewButton_1);

		JLabel lblMensaje = new JLabel("");
		lblMensaje.setForeground(Color.RED);
		lblMensaje.setBounds(22, 61, 229, 14);
		getContentPane().add(lblMensaje);

		btnNewButton_1.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				if (textField.getText().equals("")) {
					lblMensaje.setText("Debe introducir un nick");
				} else {
					setVisible(false);
					dispose();
				}
			}
		});
	}

	public static void main(String[] args) {
		VentanaIntro ventana = new VentanaIntro();
		ventana.setVisible(true);

	}
}
