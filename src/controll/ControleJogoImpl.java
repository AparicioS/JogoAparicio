package controll;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;

import model.Agua;
import model.Campo;
import model.Montanha;
import model.Peca;

/**
*
* @author aparicio da silva
*/
public class ControleJogoImpl implements ControleJogo {

	private Peca[][] tabuleiro;
	private int ultTecla;
	private String tipoHeroi;
	private MovimentoHeroi movimentoHeroi;

	private List<Observador> observadores = new ArrayList<>();

	@Override
	public void addObservador(Observador obs) {
		observadores.add(obs);
	}

	@Override
	public void inicializar() {
		// for(Peca x:tabuleiro[0])

		tabuleiro = new Peca[7][7];
		for (int j = 0; j < tabuleiro.length; j++) {
			for (int i = 0; i < tabuleiro.length; i++) {
				tabuleiro[j][i] = new Campo();
				if ((i == j) & (i == 0) || (i == j) & (i == tabuleiro.length - 1)
						|| (i == j) & (i == tabuleiro.length / 2) || (i == 0) & (j == tabuleiro.length - 1)
						|| (0 == j) & (i == tabuleiro.length - 1)) {
					tabuleiro[j][i] = new Agua();
					;
				}
			}
		}
	}

	/*
	 * tabuleiro[0][0] = new Campo(); tabuleiro[0][1] = null;// coloca uma heroi
	 * mais abaixo tabuleiro[0][2] = new Montanha();
	 * 
	 * tabuleiro[1][0] = new Montanha(); tabuleiro[1][1] = new Agua();
	 * tabuleiro[1][2] = new Agua();
	 * 
	 * tabuleiro[2][0] = new Campo(); tabuleiro[2][1] = new Campo(); tabuleiro[2][2]
	 * = new Campo();
	 */

	@Override
	public Icon getPeca(int col, int row) {

		return (tabuleiro[col][row] == null ? null : tabuleiro[col][row].getImagem());
	}

	@Override
	public void pressTecla(int keyCode) {
		this.ultTecla = keyCode;
	}
	@Override
	public void clic(MouseEvent e) {
		System.out.println("teste");
		
	}
	@Override
	public void run() {

		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					// posicoes do heroi
					int x = 0;
					int y = 1;

					Peca pecaAnterior = null;

					while (true) {
						// lerInputs
						movimentoHeroi.zerarDeslocamento();

						// como nao interessa nesse exercicio, nao estou consistindo se chegou no limite
						// do mundo
						switch (ultTecla) {
						case 37:
							movimentoHeroi.vaiParaEsquerda(tabuleiro[x - 1][y]);
							break;
						case 38:
							movimentoHeroi.vaiParaCima(tabuleiro[x][y - 1]);
							break;
						case 39:
							movimentoHeroi.vaiParaDireita(tabuleiro[x + 1][y]);
							break;
						case 40:
							movimentoHeroi.vaiParaBaixo(tabuleiro[x][y + 1]);
							break;
						}
						ultTecla = 0;

						// mudar a posicao do heroi
						if (movimentoHeroi.getX() != 0) {
							Peca p = tabuleiro[x + movimentoHeroi.getX()][y];
							tabuleiro[x + movimentoHeroi.getX()][y] = movimentoHeroi.getPeca();
							tabuleiro[x][y] = pecaAnterior;
							pecaAnterior = p;
							x = x + movimentoHeroi.getX();
						} else {
							if (movimentoHeroi.getY() != 0) {
								Peca p = tabuleiro[x][y + movimentoHeroi.getY()];
								tabuleiro[x][y + movimentoHeroi.getY()] = movimentoHeroi.getPeca();
								tabuleiro[x][y] = pecaAnterior;
								pecaAnterior = p;
								y = y + movimentoHeroi.getY();
							}
						}

						notificarMudancaTabuleiro();

						Thread.sleep(100); // soh para dar um tempinho
					}
				} catch (Exception e) {

					notificarFimJogo(e.toString());
				}
			}
		};
		t.start();

	}

	@Override
	public void setTipoHeroi(String tipoHeroi) {
		this.tipoHeroi = tipoHeroi;
	}

	@Override
	public void criarHeroi() throws Exception {

		this.movimentoHeroi = (MovimentoHeroi) Class.forName(tipoHeroi).newInstance();
		tabuleiro[0][1] = this.movimentoHeroi.getPeca();

		notificarMudancaTabuleiro();
	}
	@Override
	public void criarHeroi(int l, int c) throws Exception {

		this.movimentoHeroi = (MovimentoHeroi) Class.forName(tipoHeroi).newInstance();
		if(tabuleiro[l][c] != this.movimentoHeroi.getPeca()) {
		tabuleiro[l][c] = this.movimentoHeroi.getPeca();
}
		notificarMudancaTabuleiro();
	}
	private void notificarMudancaTabuleiro() {

		for (Observador obs : observadores)
			obs.mudouTabuleiro();

	}

	private void notificarFimJogo(String msgErro) {
		for (Observador obs : observadores)
			obs.fimDeJogo(msgErro);
	}

}
