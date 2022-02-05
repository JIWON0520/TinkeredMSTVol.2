package tinkeredmst;

import inputprocessers.Point;
import inputprocessers.Terminal;

public class TinkeredInfo {
	private Terminal tinkeredTerminal;
	private Point portal;
	private Terminal pairTerminal;
	
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
	
	public Terminal getPairTerminal() {
		return pairTerminal;
	}
	public void setPairTerminal(Terminal pairTerminal) {
		this.pairTerminal = pairTerminal;
	}
	@Override
	public String toString() {
		return "tinkered Terminal="
				+ tinkeredTerminal
				+", used Portal:"
				+portal
				+"PairTerminal="
				+pairTerminal;
	}

}
