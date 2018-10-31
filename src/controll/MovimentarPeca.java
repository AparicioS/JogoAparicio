package controll;

import model.Peca;

public  class MovimentarPeca implements Command{
	protected Peca[][] tabuleiro;
	protected int[] atual;
	protected int[] anterior;
	
	public MovimentarPeca(Peca[][] tabuleiro,int[] atual,int[] anterior) {
		this.tabuleiro =tabuleiro;
		this.atual = atual;
		this.anterior = anterior;
		
	}

	@Override
	public void execute() {
		Peca aux = tabuleiro[atual[0]][atual[1]];
		this.tabuleiro[atual[0]][atual[1]]=tabuleiro[anterior[0]][anterior[1]];
		this.tabuleiro[anterior[0]][anterior[1]]= aux;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undo() {
		Peca aux = tabuleiro[atual[0]][atual[1]];
		this.tabuleiro[atual[0]][atual[1]]=tabuleiro[anterior[0]][anterior[1]];
		this.tabuleiro[anterior[0]][anterior[1]]= aux;	
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo() {
		Peca aux = tabuleiro[atual[0]][atual[1]];
		this.tabuleiro[atual[0]][atual[1]]=tabuleiro[anterior[0]][anterior[1]];
		this.tabuleiro[anterior[0]][anterior[1]]= aux;	
		// TODO Auto-generated method stub
		
	}

	
}
