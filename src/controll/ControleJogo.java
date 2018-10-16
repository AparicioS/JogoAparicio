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

	void pressTecla(int keyCode) throws Exception;

	void run() throws Exception;

	void setTipoHeroi(String tipoHeroi) throws Exception;

	void criarHeroi() throws Exception;

	void addObservador(Observador obs);

	void clic(MouseEvent e);

	void criarHeroi(int l, int c) throws Exception;

}
