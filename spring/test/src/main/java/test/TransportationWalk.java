package test;

public class TransportationWalk {
	
	private Walk w;
	public TransportationWalk(Walk w) {
		this.w = w;
	}
	public void move() {
		w.print();
	}
}
