package controll;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import model.Campo;
import model.DefensorPeao;
import model.Mercenario;
import model.Peca;
import model.Refugio;
import model.Rei;
import model.Tabuleiro;
import model.Trono;
import state.Jogador;
import visitor.CapturaPeca;
import visitor.JogadorDaVez;
import visitor.Visitor;
import visitor.ValidaJogada;;

/**
 *
 * @author aparicio da silva
 */
public class ControleJogoImpl implements ControleJogo {
	private Invoker inv = new Invoker();
	private Tabuleiro tabuleiro;
	private Visitor jogadaVisit;
	private Visitor jogadorVisit;
	private Visitor capturaVisit;
	private List<Observador> observadores;
	private List<Peca> jogadalist;
	private Jogador jogador;
	
	
	public  ControleJogoImpl(){
		this.jogador =new Jogador();
		this.inv = new Invoker();
		this.tabuleiro = new Tabuleiro();
		this.jogadaVisit =  new ValidaJogada();
		this.jogadorVisit =  new JogadorDaVez(jogador.getJogadorAtual().getVez());
		this.capturaVisit =  new CapturaPeca(jogador.getJogadorAtual().getVez());
		this.observadores = new ArrayList<>();
		
		}

	@Override
	public void addObservador(Observador obs) {
		observadores.add(obs);
	}

	// iniciar a matriz tabuleiro com as pecas campo
	@Override
	public void inicializar() {

		//tabuleiro = new Peca[dimencao][dimencao];
		tabuleiro.setTabuleiro(new Peca[tabuleiro.getDimencao()][tabuleiro.getDimencao()]);
		for (int j = 0; j < tabuleiro.getDimencao(); j++) {
			for (int i = 0; i < tabuleiro.getDimencao(); i++) {
				tabuleiro.addPeca(j,i, novoCampo(j,i));

			}
		}
		notificarMudancaTabuleiro();
	}


	public Peca novoCampo(int row, int col) {
		
		if ((row== col) & ((row == 0) ||(row== tabuleiro.getDimencao()- 1))
				|| (row == 0) & (col == tabuleiro.getDimencao()- 1) || (col == 0) & (row== tabuleiro.getDimencao()- 1)) {
			return new Refugio(row,col);
		}else if ((row== col) & (col == tabuleiro.getDimencao()/ 2)) {
			return new Trono(row,col);
		}else {
			return new Campo(row,col);
		}
		
	}

	
	// atualizar a matriz tabuleiro com os jogadores
	@Override
	public void iniciarJogo() {

		for (int j = 0; j < tabuleiro.getDimencao(); j++) {
			for (int i = 0; i < tabuleiro.getDimencao(); i++) {
				if (j == 3 || i == 3) {
					if ((j == 0 || j == 1 || j == 5 || j == 6) || (i == 0 || i == 1 || i == 5 || i == 6)) {
						this.tabuleiro.addPeca(j,i,new Mercenario(j,i));
					} else if ((j == 2 || j == 4) || (i == 2 || i == 4)) {
						this.tabuleiro.addPeca(j,i,new DefensorPeao(j,i));
					}
					if (j == i) {
						this.tabuleiro.addPeca(j,i,new Rei(new DefensorPeao(j,i)));
					} 

				}
			}
		}
		tabuleiro.accept(jogadorVisit);
		jogadalist = jogadorVisit.getJogada();
		notificarMudancaTabuleiro();
	}


	
//	// verificar as pecas do jogador da vez
//	public boolean Jogada(int row, int col) {
//		jogada.clear();
//		int l=1,o=1,n=1,s=1;
//		do {
//			if (col+l < tabuleiro.length && this.tabuleiro[row][col].possoIr(this.tabuleiro[row][col+l])) {
//				this.jogada.add(this.tabuleiro[row][col+l]);
//				l++;
//			}else
//			if (col-o >=0 &&this.tabuleiro[row][col].possoIr(this.tabuleiro[row][col-o])) {
//				this.jogada.add(this.tabuleiro[row][col-o]);
//				o++;
//			}else
//			if (row+n < tabuleiro.length && this.tabuleiro[row][col].possoIr(this.tabuleiro[row+n][col])) {
//				this.jogada.add(this.tabuleiro[row+n][col]);
//				n++;
//			}else
//			if (row-s>=0 && this.tabuleiro[row][col].possoIr(this.tabuleiro[row-s][col])) {
//				this.jogada.add(this.tabuleiro[row-s][col]);
//				s++;
//			}else break;
//		}
//		while(true);
//
//		notificarMudancaTabuleiro();
//		System.out.println("lista" + jogada.isEmpty());
//		return jogada.isEmpty();
//
//	}

	@Override
	public Icon getPeca(int row, int col) {

		return (tabuleiro.getPeca(row, col) == null ? null : tabuleiro.getPeca(row, col) .getImagem());
	}

	@Override
	public void click(MouseEvent e) {
		System.out.println(e.getPoint());

	}

	private void notificarMudancaTabuleiro() {

		for (Observador obs : observadores)
			obs.mudouTabuleiro();

	}
	

//	private void notificarFimJogo(String msgErro) {
//		for (Observador obs : observadores)
//			obs.fimDeJogo(msgErro);
//	}

	@Override
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	@Override
	public Jogador getJogador() {
		return jogador;
	}

	@Override
	public void voltarJogada() {
		if (this.inv.existe()) {
			this.inv.undo();
			jogador.pressionarBotao();
			jogadorVisit.setSelec(jogador.getJogadorAtual().getVez());
			tabuleiro.accept(jogadorVisit);
			notificarMudancaTabuleiro();
		}
	}
	@Override
	public void refazerJogada() {
		if (this.inv.existe()) {
			this.inv.redo();
			jogador.pressionarBotao();
			jogadorVisit.setSelec(jogador.getJogadorAtual().getVez());
			tabuleiro.accept(jogadorVisit);
			notificarMudancaTabuleiro();
		}
	}

	@Override
	public void click(int row, int col) {
	if (this.jogadalist.contains(tabuleiro.getPeca(row, col))) {

			if (tabuleiro.getSelecionada()==null) {
				System.out.println("if 2");
				tabuleiro.setSelecionada(tabuleiro.getPeca(row, col));			
								
					if (tabuleiro.accept(jogadaVisit)) {
						tabuleiro.setSelecionada(null);
						}else {
							this.jogadalist = jogadaVisit.getJogada();
							}			
				} else {								
					inv.execute(new MovimentarPeca(this.tabuleiro,row,col));
					inv.imprimir();
					jogador.pressionarBotao();
					jogadorVisit.setSelec(jogador.getJogadorAtual().getVez());
					
					capturaVisit.setSelec(jogador.getJogadorAtual().getVez());//////
					tabuleiro.setSelecionada(tabuleiro.getPeca(row, col));
					tabuleiro.accept(capturaVisit);
					System.out.println("delete:"+capturaVisit.getJogada().isEmpty());
					jogadalist = capturaVisit.getJogada();	
					/*chamar metodo  de escluir*/
					if(jogadalist.isEmpty())
					for(Peca x:jogadalist) {
					System.out.println("delete row"+x.getRow()+"col"+x.getCol());
					}
					tabuleiro.accept(jogadorVisit);
					jogadalist = jogadorVisit.getJogada();				
					tabuleiro.setSelecionada(null);			
					}
	
			}
		notificarMudancaTabuleiro();
		for(Peca x:jogadalist)
			System.out.println("row"+x.getRow()+"col"+x.getCol());
		}
	
	

}
