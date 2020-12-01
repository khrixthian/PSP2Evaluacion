package JuegoAdivina;

import java.io.Serializable;

public class ObjetoCompartido implements Serializable {
	int numero;
	boolean acabo;
	int ganador;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isAcabo() {
		return acabo;
	}

	public void setAcabo(boolean acabo) {
		this.acabo = acabo;
	}

	public int getGanador() {
		return ganador;
	}

	public void setGanador(int ganador) {
		this.ganador = ganador;
	}

	public ObjetoCompartido() {
		super();
	}

	public synchronized String nuevaJugada(int jugador, int suNumero) {
		// aki tengo ke recibir el numero secreto del servidorAdivina
		int numSecreto = this.numero;
		jugador = this.ganador;
		if (numSecreto == suNumero) {
			this.acabo = true;
			return "Ha ganado el jugador " + jugador + ", ha adivinado el numero: " + suNumero;
		} else if (numSecreto < suNumero) {
			this.acabo = false;
			return "Has fallado, el numero que buscas es mayor.";
		} else {
			this.acabo = false;
			return "Has fallado, el numero que buscas es menor.";
		}

	}
}
