package model;

public class Tabuleiro {
	private Peca[][] tabuleiro;
	private int dimencao;
	
	public Tabuleiro() {
		
	}
	
	
	public Peca getPeca(int x ,int y) {
		return tabuleiro[x][y];
	}
	public void addPeca(int x ,int y,Peca peca) {
		this.tabuleiro[x][y] = peca;
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
	
}
