package model;
/**
*
* @author aparicio da silva
*/
public class Trono extends Peca {

	public Trono( int row, int col) {
		super("Trono", row, col);
		}
	
	public boolean minhaVez(String vez) {
		return vez.equals("Trono");
	}

	@Override
	public boolean possoIr(Peca peca) {
		// TODO Auto-generated method stub
		return false;
	}
}
