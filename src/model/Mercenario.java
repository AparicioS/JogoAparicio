package model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
*
* @author aparicio da silva
*/
public class Mercenario extends PecaJogador{

	public Mercenario( int row, int col) {
		super("Mercenario", row, col);

	}

	@Override
	public boolean minhaVez(String vez) {
		return vez.equals("Mercenario");
	}

	@Override
	public boolean possoIr(Peca possicao) {	
		return possicao.minhaVez("Campo");
	}

}
