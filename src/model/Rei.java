package model;
/**
*
* @author aparicio da silva
*/
public class Rei extends DefensorDecorator  {
	
	public Rei(Defensor defensor) {
		super(defensor,"Rei",defensor.getRow(),defensor.getCol());
	}
	@Override
	public boolean possoIr(Peca possicao) {		
		return (super.possoIr(possicao)||possicao.minhaVez("Refugio")||possicao.minhaVez("Trono"));
	}

}
