package model;
/**
*
* @author aparicio da silva
*/
public class Refugio extends Peca{

	public Refugio( int row, int col) {
		super("Refugio", row, col);	
	}
	
	public boolean minhaVez(String vez) {
		return vez.equals(super.getTipo());
	}

	@Override
	public boolean possoIr(Peca peca) {
		// TODO Auto-generated method stub
		return false;
	}
}
