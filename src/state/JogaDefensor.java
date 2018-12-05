package state;

public class JogaDefensor extends EstadoJogador {

	public JogaDefensor(Jogador jogador) {
		super(jogador,"Defensor");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void proxJogador() {
		jogador.setJogadorAtual(new JogaMercenario(jogador));
		// TODO Auto-generated method stub
		
	}

}
