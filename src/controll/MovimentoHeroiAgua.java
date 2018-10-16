package controll;

import model.Agua;
import model.HeroiAgua;
import model.HeroiMonatanha;
import model.Montanha;
import model.Peca;

/**
*
* @author aparicio da silva
*/
public class MovimentoHeroiAgua extends MovimentoHeroi{

	@Override
	protected void criarPeca() {
		this.peca =new HeroiAgua();
		
	}

	@Override
	protected boolean validarOutrosTiposCasas(Peca peca) {
		
		return peca.getClass()== Agua.class;
	}

}
