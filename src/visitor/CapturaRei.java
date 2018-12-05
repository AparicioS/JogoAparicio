package visitor;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Peca;

public class CapturaRei implements Visitor {
	private ArrayList<Peca> jogada;	
	private String selec;
	private boolean L,O,N,S;

		
	public CapturaRei(String selec) {

		this.jogada = new ArrayList<Peca>();
		this.selec= selec;
		this.L=true;
		this.O=true;
		this.N=true;
		this.S=true;
	}
	


	@Override
	public ArrayList<Peca> getJogada() {
		
		if((this.L||this.O||this.N||this.S)) {
			JOptionPane.showMessageDialog(null,"Fim do Jogo o Rei foi capturado Vitoria dos Mercenarios");
		}
			return jogada;
	}

	@Override
	public void visit(Peca peca, Peca selecionada) {
		if(selecionada.getRow()==0) {
			L=false;
		}
		if(selecionada.getRow()==Integer.parseInt(selec)-1) {
			O=false;
		}
		if(selecionada.getCol()==0) {
			N=false;
		}
		if(selecionada.getCol()==Integer.parseInt(selec)-1) {
			S=false;
		}
		
		boolean cerca = (peca.minhaVez("Mercenario")||peca.minhaVez("Trono")||peca.minhaVez("Refugio"));
		/////////////////////////////////////////////////////////////////
		if(peca.getRow()==selecionada.getRow()-2) {
			if(peca.getCol()==(selecionada.getCol())& (!cerca)) {
				L=false;
			}
		}
		////////////////////////////////////////////////////////////////
		if(peca.getRow()==selecionada.getRow()-1) {
			if(peca.getCol()==(selecionada.getCol()-1)& (!cerca)) {
				L=false;N=false; 	}
			if(peca.getCol()==(selecionada.getCol()) &(!(peca.getTipo().equals("Rei")))) {
				L=false; 	}
			if(peca.getCol()==(selecionada.getCol()+1)&(! cerca)) {
				L=false;S=false; 	}
			
		}
		/////////////////////////////////////////////////////////////////
		if(peca.getRow()==selecionada.getRow()&(N||S)) {
			if(peca.getCol()==(selecionada.getCol()-2)&(! cerca)) {
				N=false;		}				
			if(peca.getCol()==(selecionada.getCol()-1)&(! (peca.getTipo().equals("Rei")))){
				N=false;}
			if(peca.getCol()==(selecionada.getCol()+1)&(! (peca.getTipo().equals("Rei")))){
				S=false;}
			if(peca.getCol()==selecionada.getCol()+2 &(! cerca)){
				S=false; }				
			}
		/////////////////////////////////////////////////////////////////////
		if(peca.getRow()==selecionada.getRow()+1) {
			if(peca.getCol()==(selecionada.getCol()-1)&(! cerca)) {
				O=false;N=false; 	}
			if(peca.getCol()==(selecionada.getCol()) &(!(peca.getTipo().equals("Rei")))) {
				O=false; 	}
			if(peca.getCol()==(selecionada.getCol()+1)&(! cerca)) {
				O=false;S=false; 	}			
		}
		/////////////////////////////////////////////////////////////////////////
		if(peca.getRow()==selecionada.getRow()+2) {
			if(peca.getCol()==(selecionada.getCol())& (!cerca)) {
				O=false;
			}
		}
	}			
	
	
	@Override
	public void setSelec(String selec) {
		//this.selec = selec;
	}

	@Override
	public void limpar() {
		///this.jogada.clear();
		this.L=true;
		this.O=true;
		this.N=true;
		this.S=true;
		// TODO Auto-generated method stub
		
	}

}
