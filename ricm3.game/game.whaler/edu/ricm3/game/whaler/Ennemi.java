package edu.ricm3.game.whaler;

import java.awt.image.BufferedImage;

public abstract class Ennemi extends Mobile_entity {

	protected Ennemi(Model model, Location position, BufferedImage im_sprites, int nrows, int ncols) {
		super(model, position, true, false, im_sprites, nrows, ncols);
	}

}
