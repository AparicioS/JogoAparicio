package state;



public abstract  class EstadoJogador {
	
	protected Jogador jogador;
	protected String vez;

	public EstadoJogador(Jogador jogador,String vez) {
		this.jogador = jogador;
		this.vez=vez;
	}
	


	public String getVez() {
		return vez;
	}



	public abstract void proxJogador();
}
