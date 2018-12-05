package visitor;

import java.util.ArrayList;
import java.util.List;

import model.Peca;

public class VitoriaDefensor implements Visitor {
	
	private List<Peca> jogada;	
	private String selec;
		
	public VitoriaDefensor() {
		this.jogada = new ArrayList<Peca>();
		this.selec= "Refugio";		
	}
	@Override
	public void limpar() {
		this.jogada.clear();
	}
	@Override
	public void setSelec(String selec) {
		this.selec = selec;
	}


	public List<Peca> getJogada() {
		return jogada;
	}


	@Override
	public void visit(Peca peca, Peca selecionada) {
		if(peca.minhaVez(this.selec))	
		this.jogada.add(peca);
		
	}

}
