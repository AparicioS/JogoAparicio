package visitor;

import java.util.ArrayList;


import model.Peca;

public class CapturaPeca implements Visitor {
	
	private ArrayList<Peca> jogada;	
	private String selec;
	private boolean L,O,N,S;
	private Peca temp;
		
	public CapturaPeca(String selec) {
		this.jogada = new ArrayList<Peca>();
		this.selec= selec;
		this.L=false;
		this.O=false;
		this.N=false;
		this.S=false;
	}
	
	

	@Override
	public ArrayList<Peca> getJogada() {
			return jogada;
	}

	@Override
	public void visit(Peca peca, Peca selecionada) {
		
		boolean cerca = (peca.minhaVez(selecionada.getTipo())||peca.minhaVez("Trono")||peca.minhaVez("Refugio"));
		if(peca.getRow()==selecionada.getRow()) {
			if(peca.getCol()==(selecionada.getCol()-2)& cerca) {
				L=true;		}		
			if(peca.getCol()==(selecionada.getCol()-1) & peca.minhaVez(selec)& L &(!peca.getTipo().equals("Rei"))) {
				this.jogada.add(peca);}
			if(peca.getCol()==selecionada.getCol()+1 & peca.minhaVez(selec)&(!peca.getTipo().equals("Rei"))) {
				temp=peca;
				O=true;		}
			if(peca.getCol()==selecionada.getCol()+2 & cerca & O) {
				this.jogada.add(temp);		}
		
			
			}
		if(peca.getCol()==selecionada.getCol()) {
			if(peca.getRow()==(selecionada.getRow()-2)& cerca ) {
				N=true;		}		
			if(peca.getRow()==(selecionada.getRow()-1)&peca.minhaVez(selec)& N &(!peca.getTipo().equals("Rei"))) {
				this.jogada.add(peca);}
			if(peca.getRow()==selecionada.getRow()+1 & peca.minhaVez(selec)&(!peca.getTipo().equals("Rei"))) {
				temp=peca;
				S=true;		}
			if(peca.getRow()==selecionada.getRow()+2& cerca &S) {
				this.jogada.add(temp);		}
		
			
			}	
		
	}			
	
	@Override
	public void setSelec(String selec) {
		this.selec = selec;
	}

	@Override
	public void limpar() {
		this.jogada.clear();
		this.L=false;
		this.O=false;
		this.N=false;
		this.S=false;
		// TODO Auto-generated method stub
		
	}

}
