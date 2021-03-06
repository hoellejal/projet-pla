package edu.ricm3.game.whaler.Interpretor;

import edu.ricm3.game.whaler.Direction;
import edu.ricm3.game.whaler.Entities.MobileEntity;
import edu.ricm3.game.whaler.Entities.Player;
import edu.ricm3.game.whaler.Game_exception.Game_exception;

public abstract class IAction {

	abstract void step(MobileEntity e) throws Game_exception;

	public static Direction strToDir(String str) { 
		if (str.equals("N")) {
			return Direction.NORTH;
		} else if (str.equals("S")) {
			return Direction.SOUTH;
		} else if (str.equals("E")) {
			return Direction.EAST;
		} else if (str.equals("O")) {
			return Direction.WEST;
		} else if (str.equals("F")) {
			return Direction.FORWARD;
		} else if (str.equals("B")) {
			return Direction.BACKWARD;
		} else if (str.equals("R")) {
			return Direction.RIGHT;
		} else if (str.equals("L")) {
			return Direction.LEFT;
		} else {
			System.out.println("Unknown Direction, will be interpreted as FORWARD");
			return Direction.FORWARD;
		}
	}

	public static class IMove extends IAction {

		Direction m_dir;

		public IMove(String dir) {
			m_dir = strToDir(dir);
		}

		void step(MobileEntity e) throws Game_exception {
			switch (m_dir) {
			case NORTH:
				e.movenorth();
				break;
			case SOUTH:
				e.movesouth();
				break;
			case EAST:
				e.moveeast();
				break;
			case WEST:
				e.movewest();
				break;
			case FORWARD:
				switch (e.m_direction) {
				case EAST:
					e.moveeast();
					break;
				case WEST:
					e.movewest();
					break;
				case NORTH:
					e.movenorth();
					break;
				case SOUTH:
					e.movesouth();
					break;
				default:
					break;
				}
				break;
			case BACKWARD:
				switch (e.m_direction) {
				case EAST:
					e.movewest();
					break;
				case WEST:
					e.moveeast();
					break;
				case NORTH:
					e.movesouth();
					break;
				case SOUTH:
					e.movenorth();
					break;
				default:
					break;
				}
				break;
			case RIGHT:
				switch (e.m_direction) {
				case EAST:
					e.movesouth();
					break;
				case WEST:
					e.movenorth();
					break;
				case NORTH:
					e.moveeast();
					break;
				case SOUTH:
					e.movewest();
					break;
				default:
					break;
				}
				break;
			case LEFT:
				switch (e.m_direction) {
				case EAST:
					e.movenorth();
					break;
				case WEST:
					e.movesouth();
					break;
				case NORTH:
					e.movewest();
					break;
				case SOUTH:
					e.moveeast();
					break;
				default:
					break;
				}
				break;
			}
		}
	}

	public static class IJump extends IAction {

		Direction m_dir;

		public IJump() {
		}

		public IJump(String dir) {
			m_dir = strToDir(dir);
		}

		void step(MobileEntity e) throws Game_exception {
			System.out.println("Impossible to jump, the entity hurt itself and moved back");
			e.m_life--;
			e.movesouth();
		}
	}

	public static class IWizz extends IAction {

		Direction m_dir;

		public IWizz(String dir) {
			m_dir = strToDir(dir);
		}

		void step(MobileEntity e) throws Game_exception {
			e.wizz();
		}

	}

	public static class IPop extends IAction {

		Direction m_dir;

		public IPop(String dir) {
			m_dir = strToDir(dir);
		}

		void step(MobileEntity e) throws Game_exception {
			e.pop();
		}
	}

	public static class ITurn extends IAction {

		Direction m_dir;

		public ITurn(String dir) {
			m_dir = strToDir(dir);
		}

		void step(MobileEntity e) {
			switch (m_dir) {
			case RIGHT:
				e.turnright();
				break;
			case BACKWARD:
				e.turndown();
				break;
			case LEFT:
				e.turnleft();
				break;
			case SOUTH:
				e.m_direction = Direction.SOUTH;
				break;
			case NORTH:
				e.m_direction = Direction.NORTH;
				break;
			case EAST:
				e.m_direction = Direction.EAST;
				break;
			case WEST:
				e.m_direction = Direction.WEST;
				break;
			default:
				break;
			}
		}
	}

	public static class IHit extends IAction {

		Direction m_dir;

		public IHit() {
		}

		public IHit(String dir) {
			m_dir = strToDir(dir);
		}

		void step(MobileEntity e) throws Game_exception {
			e.hit();
		}

	}

	public static class IProtect extends IAction {

		Direction m_dir;

		public IProtect() {

		}

		public IProtect(String dir) {
			m_dir = strToDir(dir);
		}

		void step(MobileEntity e) throws Game_exception {
			System.out.println("Impossible for the entity to protect itself, interpreted as hit");
			e.hit();
		}

	}

	public static class IPick extends IAction {

		Direction m_dir;

		public IPick() {
		}

		public IPick(String dir) {
			m_dir = strToDir(dir);
		}

		void step(MobileEntity e) throws Game_exception {
			if (!(e instanceof Player)) {
				System.out.println("Only the player can pick up oil, interpreted as a pop");
				e.pop();
			} else {
				e.pick();
			}
		}
	}

	public static class IThrow extends IAction {

		Direction m_dir;

		public IThrow() {
		}

		public IThrow(String dir) {
			m_dir = strToDir(dir);
		}

		void step(MobileEntity e) throws Game_exception {
			System.out.println("Nothing to throw, interpreted as a wizz");
			e.wizz();
		}

	}

	public static class IStore extends IAction {
		Direction m_dir;

		public IStore() {
		}

		public IStore(String dir) {
			m_dir = strToDir(dir);
		}

		void step(MobileEntity e) throws Game_exception {
			System.out.println("Nothing to store, the entity will move instead");
			e.movesouth();
		}

	}

	public static class IGet extends IAction {

		public IGet() {
		}

		void step(MobileEntity e) {
		}

	}

	public static class IPower extends IAction {
		Direction m_dir;

		public IPower() {
		}

		public IPower(String dir) {
			m_dir = strToDir(dir);
		}

		void step(MobileEntity e) {

		}

	}

	public static class IKamikaze extends IAction {

		public IKamikaze() {
		}

		void step(MobileEntity e) throws Game_exception {
			e.m_life = 0;
			e.destroy();
		}

	}

	public static class IOr extends IAction {
		IAction m_a;
		IAction m_b;

		public IOr(IAction a, IAction b) {
			m_a = a;
			m_b = b;
		}

		void step(MobileEntity e) throws Game_exception {
			int r = e.m_model.rand.nextInt(2);
			if (r == 0) {
				m_a.step(e);
			} else {
				m_b.step(e);
			}
		}
	}

}
