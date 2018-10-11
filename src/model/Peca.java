package model;

import javax.swing.Icon;

/**
 *
 * @author aparicio da silva
 */
public abstract class Peca {

	private Icon imagem;

	public Peca(Icon imagem) {
		this.imagem = imagem;
	}

	public Icon getImagem() {
		return imagem;
	}

}
