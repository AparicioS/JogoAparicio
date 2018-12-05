package model;

import javax.swing.ImageIcon;

import visitor.Visitor;

/**
 *
 * @author aparicio da silva
 */

public class Campo extends Peca {

	public Campo( int row, int col) {
		super("Campo", row, col);
	}

	@Override
	public boolean minhaVez(String vez) {
		return vez.equals("Campo");
	}

	@Override
	public boolean possoIr(Peca peca) {
		// TODO Auto-generated method stub
		return false;
	}




}
