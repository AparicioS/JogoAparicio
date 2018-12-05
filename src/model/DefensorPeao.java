package model;
/**
*
* @author aparicio da silva
*/
public class DefensorPeao  extends  Defensor{
	


	public DefensorPeao( int row, int col) {
		super("Defensor", row, col);
	
	}

	public boolean possoIr(Peca possicao) {	
		return possicao.minhaVez("Campo");
	}



	

}
