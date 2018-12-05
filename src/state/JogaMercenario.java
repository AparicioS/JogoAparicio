package state;


public class JogaMercenario extends EstadoJogador{
	

	public JogaMercenario(Jogador jogador) {
		super(jogador,"Mercenario");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void proxJogador() {
		jogador.setJogadorAtual(new JogaDefensor(jogador));
		// TODO Auto-generated method stub
		
	}

}
