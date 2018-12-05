package model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import visitor.Visitor;

/**
 *
 * @author aparicio da silva
 */
public abstract class Peca {
	private String tipo;
	private int row;
	private int col;
	
	
	public abstract boolean minhaVez(String string);
	public abstract boolean possoIr(Peca peca);
//	{
//		return false;
//	}
	
	
	public Peca(String tipo ,int row, int col) {
	this.tipo=tipo;
	this.row =row;
	this.col=col;
	}

	public Icon getImagem() {
		return new ImageIcon("src/pecas/"+tipo+".png");
	}
	
	public String getTipo() {
		return tipo;
	}


	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}







}
