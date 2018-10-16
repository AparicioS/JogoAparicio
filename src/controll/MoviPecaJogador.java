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

	public Heroi getPeca() {
		return peca;
	}

	public void vaiParaCima(Peca peca) {
		// essa comparacao entre tipos de classes nao eh o mais recomendado...
		// vamos resolver isso no futuro com outros padroes
		if (peca == null || peca.getClass() == Campo.class || validarOutrosTiposCasas(peca)) {
			y--;
		}
	}

	public void vaiParaEsquerda(Peca peca) {
		// essa comparacao entre tipos de classes nao eh o mais recomendado...
		// vamos resolver isso no futuro com outros padroes
		if (peca == null || peca.getClass() == Campo.class || validarOutrosTiposCasas(peca)) {
			x--;
		}
	}

	public void vaiParaBaixo(Peca peca) {
		// essa comparacao entre tipos de classes nao eh o mais recomendado...
		// vamos resolver isso no futuro com outros padroes
		if (peca == null || peca.getClass() == Campo.class || validarOutrosTiposCasas(peca)) {
			y++;
		}
	}

	public void vaiParaDireita(Peca peca) {
		// essa comparacao entre tipos de classes nao eh o mais recomendado...
		// vamos resolver isso no futuro com outros padroes
		if (peca == null || peca.getClass() == Campo.class || validarOutrosTiposCasas(peca)) {
			x++;
		}
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
