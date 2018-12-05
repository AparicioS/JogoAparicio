package model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
*
* @author aparicio da silva
*/
public abstract class DefensorDecorator extends Defensor {

	private Defensor defensor;
	
	public DefensorDecorator(Defensor defensor,String nomeImagem, int row, int col) {
		super(nomeImagem,row,col);
		this.defensor=defensor;
	}
	
	@Override
	public boolean possoIr(Peca possicao) {		
		return this.defensor.possoIr(possicao);
	}

	
}
