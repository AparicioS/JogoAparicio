package controll;

import model.Tabuleiro;

public  class MovimentarPeca implements Command{
	protected Tabuleiro tabuleiro;
	protected int row;
	protected int col;


	
	public MovimentarPeca(Tabuleiro tabuleiro,int row,int col) {
		this.tabuleiro =tabuleiro;
		this.row =row;
		this.col =col;
		
		
	}

	@Override
	public void execute() {
		int row=this.tabuleiro.getSelecionada().getRow();
		int col=this.tabuleiro.getSelecionada().getCol();
		this.tabuleiro.getSelecionada().setRow(this.row);
		this.tabuleiro.getSelecionada().setCol(this.col);					
		this.tabuleiro.addPeca(this.row,this.col,this.tabuleiro.getSelecionada());
		this.tabuleiro.addPeca(row,col,this.tabuleiro.novoCampo(row,col));
				
	}

	@Override
	public void undo() {
	
	}

	@Override
	public void redo() {
		
	}

	
}
