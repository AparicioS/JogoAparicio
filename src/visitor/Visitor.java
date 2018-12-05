package visitor;


import java.util.List;

import model.Peca;

public interface Visitor {
	
	List<Peca> getJogada();
	void visit(Peca peca, Peca selecionada);
	void setSelec(String selec);
	void limpar();

}
