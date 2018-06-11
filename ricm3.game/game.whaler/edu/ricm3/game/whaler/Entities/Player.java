package edu.ricm3.game.whaler.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Location;
import edu.ricm3.game.whaler.Model;
import edu.ricm3.game.whaler.Options;
import edu.ricm3.game.whaler.Game_exception.Automata_Exception;
import edu.ricm3.game.whaler.Game_exception.Map_exception;
import edu.ricm3.game.whaler.Game_exception.Tile_exception;
import edu.ricm3.game.whaler.Interpretor.IAutomata;

public final class Player extends Mobile_Entity {

	BufferedImage m_playerNorth;
	BufferedImage m_playerSouth;
	BufferedImage m_playerEast;
	BufferedImage m_playerWest;

	
	BufferedImage m_playerNorthUnder;
	BufferedImage m_playerSouthUnder;
	BufferedImage m_playerEastUnder;
	BufferedImage m_playerWestUnder;
	
	
	


	int m_life; // Live gauge



	/**
	 * 
	 * @param pos
	 * @param sprite
	 * @param underSprite
	 * @param model
	 * @param dir
	 * @param auto
	 * @throws Map_exception
	 */
	public Player(Location pos, BufferedImage sprite, BufferedImage underSprite, Model model, Direction dir, IAutomata auto)
			throws Map_exception {
		super(pos, true, sprite, underSprite, model, dir);
		m_life = Options.PLAYER_LIFE;
		m_automata = auto;
		loadSprites();
		switch (dir) {
		case EAST:
			m_underSprite = m_playerEastUnder;
			m_sprite = m_playerEast;
			break;
		case WEST:
			m_underSprite = m_playerWestUnder;
			m_sprite = m_playerWest;
			break;
		case NORTH:
			m_underSprite = m_playerNorthUnder;
			m_sprite = m_playerNorth;
			
			break;
		default:
			m_underSprite = m_playerSouthUnder;
			m_sprite = m_playerSouth;
			break;
		}
	}

	/*
	 * 
	 */
	public void loadSprites() {
		m_playerSouth = m_sprite.getSubimage(0, 0, 32, 32);
		m_playerWest = m_sprite.getSubimage(0, 32, 32, 32);
		m_playerEast = m_sprite.getSubimage(0, 64, 32, 32);
		m_playerNorth = m_sprite.getSubimage(0, 96, 32, 32);
		
		m_playerNorthUnder = m_underSprite.getSubimage(0, 0, 32, 32);
		m_playerSouthUnder = m_underSprite.getSubimage(0, 32, 32, 32);
		m_playerEastUnder = m_underSprite.getSubimage(0, 64, 32, 32);
		m_playerWestUnder = m_underSprite.getSubimage(0, 96, 32, 32);
	}

	@Override
	public void step(long now) throws Exception {
		long elapsed = now - m_lastStep;
		if (elapsed > 200L) {
			m_lastStep = now;
			m_automata.step(this, m_model);
			//Changement du sprite si changement de direction
			switch (m_direction) {
			case EAST:
				m_sprite = m_playerEast;
				break;
			case WEST:
				m_sprite = m_playerWest;
				break;
			case NORTH:
				m_sprite = m_playerNorth;
				break;
			default:
				m_sprite = m_playerSouth;
				break;
			}
		}
	}

	@Override
	public void paint(Graphics g, Location map_ref) {
		g.drawImage(m_sprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}

	@Override
	public void paint_under(Graphics g, Location map_ref) {
		g.drawImage(m_underSprite, (m_pos.x - map_ref.x) * 32, (m_pos.y - map_ref.y) * 32, 32, 32, null);
	}

	@Override
	public void pop() {
		// TODO
	}

	@Override
	public void wizz() {
		// TODO
	}

	@Override
	public void hit() {
		// TODO
	}

}
