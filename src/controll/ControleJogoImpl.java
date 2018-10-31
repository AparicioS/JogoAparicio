package controll;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import model.Campo;
import model.Defensor;
import model.Mercenario;
import model.Peca;
import model.Refugio;
import model.Rei;
import model.Trono;

/**
 *
 * @author aparicio da silva
 */
public class ControleJogoImpl implements ControleJogo {
	private Invoker inv = new Invoker();
	private Peca[][] tabuleiro;
	private List<Peca> jogada = new ArrayList<Peca>();
	private int dimencao = 7;
	private String jogador ="Defensor";
	private int lin, coluna;

	private List<Observador> observadores = new ArrayList<>();

	@Override
	public void addObservador(Observador obs) {
		observadores.add(obs);
	}

	// iniciar a matriz tabuleiro com as pecas campo
	@Override
	public void inicializar() {

		tabuleiro = new Peca[dimencao][dimencao];
		for (int j = 0; j < tabuleiro.length; j++) {
			for (int i = 0; i < tabuleiro.length; i++) {
				tabuleiro[j][i] = new Campo();
				if ((i == j) & (i == 0) || (i == j) & (i == tabuleiro.length - 1)
						|| (i == 0) & (j == tabuleiro.length - 1) || (0 == j) & (i == tabuleiro.length - 1)) {
					tabuleiro[j][i] = new Refugio();
				}
				if ((i == j) & (i == tabuleiro.length / 2)) {
					tabuleiro[j][i] = new Trono();
				}
			}
		}
		notificarMudancaTabuleiro();
	}

	// atualizar a matriz tabuleiro com os jogadores
	@Override
	public void iniciarJogo() {

		for (int j = 0; j < tabuleiro.length; j++) {
			for (int i = 0; i < tabuleiro.length; i++) {
				if (i == 3 || j == 3) {
					if ((i == 0 || i == 1 || i == 5 || i == 6) || (j == 0 || j == 1 || j == 5 || j == 6)) {
						this.tabuleiro[j][i] = new Mercenario();
					} else if ((i == 2 || i == 4) || (j == 2 || j == 4)) {
						this.tabuleiro[j][i] = new Defensor();
					}
					if (i == j) {
						this.tabuleiro[j][i] = new Rei();
					}

				}
			}
		}
		Jogada(new Defensor());
		notificarMudancaTabuleiro();
	}

	// salvar as possiveis jogadas da vez
	public void Jogada(Peca peca) {
		jogada.clear();
		for (int j = 0; j < tabuleiro.length; j++) {
			for (int i = 0; i < tabuleiro.length; i++) {

				if (this.tabuleiro[j][i].getClass() == peca.getClass()
						|| (this.tabuleiro[i][j].getClass().equals(Rei.class) && jogador.equals("Defensor"))) {
					this.jogada.add(this.tabuleiro[j][i]);
				}

			}
		}
		//notificarMudancaTabuleiro();
	}

	// verificar as pecas do jogador da vez
	public boolean Jogada(int row, int col) {
		jogada.clear();
		for (int i = col + 1; i < tabuleiro.length; i++) {
			if (!(this.tabuleiro[row][i].getClass().equals(Campo.class))) {
				break;
			}
			this.jogada.add(this.tabuleiro[row][i]);

		}
		for (int i = row + 1; i < tabuleiro.length; i++) {
			if (!this.tabuleiro[i][col].getClass().equals(Campo.class)) {
				break;
			}
			this.jogada.add(this.tabuleiro[i][col]);

		}
		for (int i = col - 1; i >= 0; i--) {
			if (!(this.tabuleiro[row][i].getClass().equals(Campo.class))) {
				break;
			}
			this.jogada.add(this.tabuleiro[row][i]);

		}
		for (int i = row - 1; i >= 0; i--) {
			if (!this.tabuleiro[i][col].getClass().equals(Campo.class)) {
				break;
			}
			this.jogada.add(this.tabuleiro[i][col]);

		}

		notificarMudancaTabuleiro();
		System.out.println("lista" + jogada.isEmpty());
		return jogada.isEmpty();

	}

	@Override
	public Icon getPeca(int col, int row) {

		return (tabuleiro[col][row] == null ? null : tabuleiro[col][row].getImagem());
	}

	@Override
	public void click(MouseEvent e) {
		System.out.println(e.getPoint());
	}



	private void notificarMudancaTabuleiro() {

		for (Observador obs : observadores)
			obs.mudouTabuleiro();

	}

	private void notificarFimJogo(String msgErro) {
		for (Observador obs : observadores)
			obs.fimDeJogo(msgErro);
	}

	@Override
	public int getDimencao() {
		return dimencao;
	}

	@Override
	public void setDimencao(int dimencao) {
		this.dimencao = dimencao;
		
	}
	@Override
	public String getJogador() {
		return jogador;
	}

	@Override
	public void voltarJogada() {
		this.inv.redo();
		if (jogador.equals("Defensor")) {
			jogador = "Mercenario";
			Jogada(new Mercenario());
		} else {
			jogador = "Defensor";
			Jogada(new Defensor());

		}
		notificarMudancaTabuleiro();
		
	}

	@Override
	public void click(int row, int col) {
		System.out.println(jogada.contains(tabuleiro[row][col]));

		if (jogada.contains(tabuleiro[row][col])) {
			if (!tabuleiro[row][col].getClass().equals(Campo.class)) {
				lin = row;
				coluna = col;
				Peca aux = tabuleiro[row][col];
				if (Jogada(row, col)) {
					Jogada(aux);
				}

			} else {			
				int a[] ={row,col};
				int[] b= {lin,coluna};
				inv.execute(new MovimentarPeca(tabuleiro,a,b));
				inv.imprimir();
					if (jogador.equals("Defensor")) {
					this.jogador = "Mercenario";
					Jogada(new Mercenario());
				} else {
					this.jogador = "Defensor";
					Jogada(new Defensor());

				}
			}
		}
		notificarMudancaTabuleiro();
		// TODO Auto-generated method stub

	}

}
