package state;


public class Jogador {
	
	private EstadoJogador jogadorAtual;
	
	public Jogador() {
		this.jogadorAtual = new JogaDefensor(this);
	}
	
	public void setJogadorAtual(EstadoJogador jogadorAtual) {
		this.jogadorAtual = jogadorAtual;
	}
	
	public void pressionarBotao() {
		this.jogadorAtual.proxJogador();
	}

	public EstadoJogador getJogadorAtual() {
		return jogadorAtual;
	}

}
