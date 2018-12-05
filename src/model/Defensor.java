package model;


public abstract class  Defensor extends PecaJogador   {


	public Defensor(String nomeImagem, int row, int col) {
		super(nomeImagem,row,col);
	}

	public abstract boolean possoIr(Peca possicao);
	
	public boolean minhaVez(String vez) {
		return vez.equals("Defensor");
	}

}
