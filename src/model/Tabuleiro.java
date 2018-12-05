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
	
	public void campo() {
		this.tabuleiro=(new Peca[this.dimencao][this.dimencao]);
		for (int j = 0; j < this.dimencao; j++) {
			for (int i = 0; i < this.dimencao; i++) {
				addPeca(j,i, novoCampo(j,i));

			}
		}
	}
	
	public void iniciarJogo() {
		

		for (int j = 0; j < this.dimencao; j++) {
			for (int i = 0; i < this.dimencao; i++) {
				if (j == 3 || i == 3) {
					if ((j == 0 || j == 1 || j == 5 || j == 6) || (i == 0 || i == 1 || i == 5 || i == 6)) {
					addPeca(j,i,new Mercenario(j,i));
					} else if ((j == 2 || j == 4) || (i == 2 || i == 4)) {
					addPeca(j,i,new DefensorPeao(j,i));
					}
					if (j == i) {
					addPeca(j,i,new Rei(new DefensorPeao(j,i)));
					} 

				}
			}
		}

	}
	
	public Peca novoCampo(int i, int j) {		
		if ((i == j) & (i == 0) || (i == j) & (i == this.dimencao- 1)
				|| (i == 0) & (j == this.dimencao- 1) || (0 == j) & (i == this.dimencao- 1)) {
			return new Refugio(i, j);
		}else if ((i == j) & (i ==this.dimencao/ 2)) {
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
