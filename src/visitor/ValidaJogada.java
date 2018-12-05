package visitor;
/**
*
* @author aparicio da silva
*/

import java.util.ArrayList;

import model.Peca;


public class ValidaJogada implements Visitor {
	
	private ArrayList<Peca> jogada;
	private ArrayList<Peca> row ;
	private ArrayList<Peca> col;
	private boolean linha;
	private boolean coluna ;


	public ValidaJogada() {
		this.jogada = new ArrayList<Peca>();
		this.row = new ArrayList<Peca>();
		this.col = new ArrayList<Peca>();
		this.linha = true;
		this.coluna = true;	
	}

	@Override
	public void visit(Peca peca,Peca selecionada) {
		
		if(peca.getRow()==selecionada.getRow()& linha) {
				if (selecionada.possoIr(peca)) {
					this.jogada.add(peca);
					this.row.add(peca);			
				}else if((!peca.minhaVez("Trono"))) {
					if(peca.getCol()<selecionada.getCol()){
					   for(Peca x:this.row) {
					   this.jogada.remove(x);				
					   		}
					   this.row.clear();
				   	}else if(peca.getCol()!=selecionada.getCol()) 
					   this.linha = false;			
				}		
		}else if(peca.getCol()==selecionada.getCol()& coluna) {	
			if (selecionada.possoIr(peca)) {
				this.jogada.add(peca);
				this.col.add(peca);
			}else if((!peca.minhaVez("Trono"))) {
				if(peca.getRow()<selecionada.getRow()){
				   for(Peca x:this.col) {
					   this.jogada.remove(x);				
				   		}
				   this.col.clear();
		   	}else if(peca.getRow()!=selecionada.getRow()) 
			   this.coluna = false;
			}
		}
		
		
	}
	
	@Override
	public void limpar() {
		this.jogada.clear();
		this.row.clear();
		this.col.clear();
		this.linha = true;
		this.coluna = true;	
	}
	
	@Override
	public ArrayList<Peca> getJogada() {
		return jogada;
	}

	@Override
	public void setSelec(String selec) {
	// TODO Auto-generated method stub		
	}



}
