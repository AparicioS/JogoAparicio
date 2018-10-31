package controll;

import java.awt.event.MouseEvent;

import javax.swing.Icon;

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

	int getDimencao();

	void setDimencao(int dimencao);

	void click(int rowAtPoint, int columnAtPoint);

	void voltarJogada();

	String getJogador();

}
