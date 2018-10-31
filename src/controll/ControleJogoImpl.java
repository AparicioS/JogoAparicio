package controll;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import model.Campo;
import model.Mercenario;
import model.Peca;
import model.Refugio;
import model.Trono;

/**
 *
 * @author aparicio da silva
 */
public class ControleJogoImpl implements ControleJogo {

	private Peca[][] tabuleiro;
	private List<Peca> jogada = new ArrayList<Peca>();
	int dimencao = 7;

	private List<Observador> observadores = new ArrayList<>();

	@Override
	public void addObservador(Observador obs) {
		observadores.add(obs);
	}

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
	}

	@Override
	public void iniciarJogo() {

		for (int j = 0; j < tabuleiro.length; j++) {
			for (int i = 0; i < tabuleiro.length; i++) {

				if ((i == j) & (i == 0) || (i == j) & (i == tabuleiro.length - 1)
						|| (i == j) & (i == tabuleiro.length / 2) || (i == 0) & (j == tabuleiro.length - 1)
						|| (0 == j) & (i == tabuleiro.length - 1)) {
					this.tabuleiro[j][i] = new Mercenario();
				}
			}
		}
		notificarMudancaTabuleiro();
	}
	public void Jogada(Peca peca) {

		for (int j = 0; j < tabuleiro.length; j++) {
			for (int i = 0; i < tabuleiro.length; i++) {

				if (this.tabuleiro[j][i].getClass() == peca.getClass()) {
					this.jogada.add(this.tabuleiro[j][i]);
				}
			}
		}
		notificarMudancaTabuleiro();
	}
	@Override
	public Icon getPeca(int col, int row) {

		return (tabuleiro[col][row] == null ? null : tabuleiro[col][row].getImagem());
	}



	@Override
	public void click(MouseEvent e) {
		System.out.println("teste");
		e.getPoint();
		System.out.println(e.getPoint());
		notificarMudancaTabuleiro();

	}

	@Override
	public void run() {

		Thread t = new Thread() {

			@Override
			public void run() {
				try {

					notificarMudancaTabuleiro();

					Thread.sleep(100); // soh para dar um tempinho

				} catch (Exception e) {

					notificarFimJogo(e.toString());
				}
			}
		};
		t.start();

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

}
