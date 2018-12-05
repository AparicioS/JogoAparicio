package visitor;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Peca;

public class CapturaRei implements Visitor {
	private ArrayList<Peca> jogada;	
	private String selec;
	private int L,O,N,S;

		
	public CapturaRei(String selec) {

		this.jogada = new ArrayList<Peca>();
		this.selec= selec;
		this.L=0;
		this.O=0;
		this.N=0;
		this.S=0;
	}
	


	@Override
	public ArrayList<Peca> getJogada() {
		
		if((this.L==0||this.O==0||this.N==0||this.S==0)) {
			JOptionPane.showMessageDialog(null,"Fim do Jogo o Rei foi capturado Vitoria dos Mercenarios");
		}
		if((this.L==1||this.O==1||this.N==1||this.S==1)) {
			JOptionPane.showMessageDialog(null,"Olha o Rei");
		}
			return jogada;
	}

	@Override
	public void visit(Peca peca, Peca selecionada) {
		boolean cerca = (peca.minhaVez("Mercenario")||peca.minhaVez("Trono")||peca.minhaVez("Refugio"));
		if(selecionada.getRow()==0) {
			L=2;
		}
		if(selecionada.getRow()==Integer.parseInt(selec)-1) {
			O=2;
		}
		if(selecionada.getCol()==0) {
			N=2;
		}
		if(selecionada.getCol()==Integer.parseInt(selec)-1) {
			S=2;
		}		
		
		if(peca.getRow()==selecionada.getRow()-2) {
			if(peca.getCol()==(selecionada.getCol())& (!cerca)) {
				L++;
			}
		}
		if(peca.getRow()==selecionada.getRow()-1) {
			if(peca.getCol()==(selecionada.getCol()-1)& (!cerca)) {
				L++;N++; 	}
			if(peca.getCol()==(selecionada.getCol()) &(!(peca.getTipo().equals("Rei")))) {
				L+=2; 	}
			if(peca.getCol()==(selecionada.getCol()+1)&(! cerca)) {
				L++;S++; 	}			
		}
		if(peca.getRow()==selecionada.getRow()) {
			if(peca.getCol()==(selecionada.getCol()-2)&(! cerca)) {
				N++;		}				
			if(peca.getCol()==(selecionada.getCol()-1)&(! (peca.getTipo().equals("Rei")))){
				N=2;}
			if(peca.getCol()==(selecionada.getCol()+1)&(! (peca.getTipo().equals("Rei")))){
				S=2;}
			if(peca.getCol()==selecionada.getCol()+2 &(! cerca)){
				S++; }				
		}
		if(peca.getRow()==selecionada.getRow()+1) {
			if(peca.getCol()==(selecionada.getCol()-1)&(! cerca)) {
				O++;N++; 	}
			if(peca.getCol()==(selecionada.getCol()) &(!(peca.getTipo().equals("Rei")))) {
				O=2; 	}
			if(peca.getCol()==(selecionada.getCol()+1)&(! cerca)) {
				O++;S++; 	}			
		}
		if(peca.getRow()==selecionada.getRow()+2) {
			if(peca.getCol()==(selecionada.getCol())& (!cerca)) {
				O++;
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
		this.L=0;
		this.O=0;
		this.N=0;
		this.S=0;
		// TODO Auto-generated method stub
		
	}

}
