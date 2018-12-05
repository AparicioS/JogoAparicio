package controll;

import java.awt.event.MouseEvent;

import javax.swing.Icon;

import model.Tabuleiro;
import state.Jogador;

/**
*
* @author aparicio da silva
*/

public interface ControleJogo {

	void inicializar() throws Exception;

	Icon getPeca(int col, int row) throws Exception;

	void addObservador(Observador obs);

	void iniciarJogo();

	void click(MouseEvent e);

	void click(int rowAtPoint, int columnAtPoint);

	void voltarJogada();

	void refazerJogada();
	Tabuleiro getTabuleiro();

	Jogador getJogador();

}
