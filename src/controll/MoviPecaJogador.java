package controll;

import model.Campo;
import model.Peca;
import model.PecaJogador;
/**
*
* @author aparicio da silva
*/
public abstract class MoviPecaJogador {

	protected PecaJogador peca;
	private int x;
	private int y;
	
	public MoviPecaJogador() {

		criarPeca();
		
	}
	
	protected abstract void criarPeca();

	public PecaJogador getPeca() {
		return peca;
	}

	protected abstract boolean validarOutrosTiposCasas(Peca peca);

	public void zerarDeslocamento() {
		this.x = 0;
		this.y = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
