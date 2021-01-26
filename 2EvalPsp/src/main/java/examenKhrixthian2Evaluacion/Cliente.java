package examenKhrixthian2Evaluacion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws NoSuchAlgorithmException, ClassNotFoundException {
		String Host = "localhost";
		int Puerto = 5000;// puerto remoto
		Socket Cliente;
		String clave = null;
		Peticion peticionRecibido = new Peticion();
		String cifradoStr = null;

		try {
			Cliente = new Socket(Host, Puerto);

			ObjectInputStream fentrada = new ObjectInputStream(Cliente.getInputStream());
			ObjectOutputStream fsalida = new ObjectOutputStream(Cliente.getOutputStream());

			DataInputStream entrada = new DataInputStream(Cliente.getInputStream());
			DataOutputStream salida = new DataOutputStream(Cliente.getOutputStream());

			Scanner sc = new Scanner(System.in);
			// aki recibe la pregunta1
			System.out.println(entrada.readUTF());
			int num = sc.nextInt();
			// envia la respuesta a la pregunta 1
			salida.writeInt(num);

			// recibe la pregunta de la clave
			String textoC = entrada.readUTF();
			if (!textoC.equals("*")) {
				System.out.println(textoC);
				sc.nextLine();
				// aki escribe en consola la clave
				clave = sc.nextLine();

			} else {
				System.out.println("No se ha encotrado ningun petición con ese ID. Salgo del programa");
			}

			// aki codifica la clave
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte dataBytes[] = clave.getBytes();
			md.update(dataBytes);
			byte resumen[] = md.digest();

			for (int i = 0; i < resumen.length; i++) {
				cifradoStr += resumen[i];
			}

			// aki la envia codificada
			fsalida.writeObject(cifradoStr);

			// recojo el objeto si he puesto la pw correcta
			peticionRecibido = (Peticion) fentrada.readObject();

			System.out.println("¿Kieres cambiar la URL?");
			String resp = sc.nextLine();

			if (!resp.equals("0")) {
				peticionRecibido.setImagen(resp);
			}
			// envio el objeto con la nueva url
			fsalida.writeObject(peticionRecibido);

			// recibo la imagen
			int tamanyo = entrada.readInt();
			byte recibido[] = new byte[tamanyo];
			FileOutputStream f = new FileOutputStream(".//imagen.jpg");

			entrada.read(recibido);
			f.write(recibido);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
