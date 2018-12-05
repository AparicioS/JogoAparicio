package controll;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JOptionPane;

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
import visitor.CapturaRei;
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
	private Visitor capturaReiVisit;
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
		this.capturaReiVisit =  new CapturaRei(""+ tabuleiro.getDimencao());
		this.observadores = new ArrayList<>();
		
		}

	@Override
	public void addObservador(Observador obs) {
		observadores.add(obs);
	}

	// iniciar a matriz tabuleiro com as pecas campo
	@Override
	public void inicializar() {
		tabuleiro.campo();
		notificarMudancaTabuleiro();
	}

	
	// atualizar a matriz tabuleiro com os jogadores
	@Override
	public void iniciarJogo() {
		tabuleiro.iniciarJogo();
		tabuleiro.accept(jogadorVisit);
		jogadalist = jogadorVisit.getJogada();
		notificarMudancaTabuleiro();
	}


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
					/*Movimenta peca*/
					vitoria1(row,col);
					inv.execute(new MovimentarPeca(this.tabuleiro,row,col));
					inv.imprimir();
					jogador.pressionarBotao();					
					tabuleiro.setSelecionada(tabuleiro.getPeca(row, col));					
					/*checa possivel fim do jogo*/					
					tabuleiro.accept(capturaReiVisit);
					//capturaVisit.getJogada();					
					/*consulta captura*/					
					capturaVisit.setSelec(jogador.getJogadorAtual().getVez());		
					tabuleiro.accept(capturaVisit);
					jogadalist = capturaVisit.getJogada();	
					/*executa captura*/
					if(!jogadalist.isEmpty()) {
					for(Peca x:jogadalist) {
						tabuleiro.setSelecionada(x);
						inv.execute(new MovimentarPeca(this.tabuleiro,x.getRow(),x.getCol()));
						}
					}	
					/*proximo jogador*/
					jogadorVisit.setSelec(jogador.getJogadorAtual().getVez());
					tabuleiro.accept(jogadorVisit);
					jogadalist = jogadorVisit.getJogada();				
					tabuleiro.setSelecionada(null);			
					}
	
			}
		notificarMudancaTabuleiro();
		for(Peca x:jogadalist)
			System.out.println("row"+x.getRow()+"col"+x.getCol());
		}
	public void vitoria1(int row, int col) {
		if(tabuleiro.getSelecionada().getTipo().equals("Rei")&
			tabuleiro.getPeca(row, col).minhaVez("Refugio")) {
			JOptionPane.showMessageDialog(null,"Fim do Jogo o Rei esta no Refugio Vitoria dos Defensores");
			
		}
			
		
	}
	
	

}
