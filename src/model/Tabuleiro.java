package model;
/**
*
* @author aparicio da silva
*/

import visitor.Visitor;

public class Tabuleiro {
	private Peca[][] tabuleiro;
	private int dimencao;
	private int row, col;
	private Peca selecionada;
	
	public Tabuleiro() {
		this.dimencao = 7;
		this.selecionada = null;
		}
	
	public Peca novoCampo(int i, int j) {		
		if ((i == j) & (i == 0) || (i == j) & (i == dimencao- 1)
				|| (i == 0) & (j == dimencao- 1) || (0 == j) & (i == dimencao- 1)) {
			return new Refugio(i, j);
		}else if ((i == j) & (i == dimencao/ 2)) {
			return new Trono(i,j);
		}else {
			return new Campo(i,j);
		}
		
	}
	
	
	public boolean accept(Visitor v) {
	v.limpar();
	for (int j = 0; j < tabuleiro.length; j++) {
		for (int i = 0; i < tabuleiro.length; i++) {
			v.visit(this.tabuleiro[j][i],selecionada);
			}
	}
	System.out.println("lista:"+v.getJogada().isEmpty());
	return v.getJogada().isEmpty();
	}	

	public void addPeca(int row,int col,Peca peca) {
		this.tabuleiro[row][col] = peca;
	}	
	public Peca getPeca(int row,int col) {
		return tabuleiro[row][col];
	}
	public Peca[][] getTabuleiro() {
		return tabuleiro;
	}
	public void setTabuleiro(Peca[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public int getDimencao() {
		return dimencao;
	}
	public void setDimencao(int dimencao) {
		this.dimencao = dimencao;
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
	public Peca getSelecionada() {
		return selecionada;
	}
	public void setSelecionada(Peca selecionada) {
		this.selecionada = selecionada;
	}

	
}
