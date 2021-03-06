package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

public final class Iceberg extends StaticEntity {

	/**
	 * @param pos
	 * @param sprite
	 * @param underSprite
	 * @param model
	 * @throws Game_exception
	 */
	public Iceberg(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model) throws Game_exception {
		super(pos, true, sprite, underSprite, model);
	}
	
	

	@Override
	public void step(long now) {
	}

	@Override
	public void paint(Graphics g, Location map_ref) {
		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {
	}
	
	public boolean isSolid() {
		return true;
	}
	
	public boolean isSolidUnder() {
		return false;
	}

	@Override
	public EntityType getType() {
		return EntityType.ICEBERG;
	}

}
