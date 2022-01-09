package tinkeredmst;

import inputprocessers.Point;
import inputprocessers.Terminal;

public class TinkeredInfo {
	private Terminal tinkeredTerminal;
	private Point portal;
	
	public Terminal getTinkeredTerminal() {
		return tinkeredTerminal;
	}
	public void setTinkeredTerminal(Terminal tinkeredTerminal) {
		this.tinkeredTerminal = tinkeredTerminal;
	}
	public Point getPortal() {
		return portal;
	}
	public void setPortal(Point portal) {
		this.portal = portal;
	}
	
	@Override
	public String toString() {
		return "tinkered Terminal="
				+ tinkeredTerminal
				+", used Portal:"
				+portal;
	}

}
