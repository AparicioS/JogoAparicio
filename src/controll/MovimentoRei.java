package controll;


import model.Campo;
import model.Defensor;
import model.Peca;

/**
*
* @author aparicio da silva
*/
public class MovimentoRei extends MoviPecaJogador{

	@Override
	protected void criarPeca() {
		this.peca =new Defensor();
		
	}

	@Override
	protected boolean validarOutrosTiposCasas(Peca peca) {
		
		return peca.getClass()== Campo.class;
	}

}
