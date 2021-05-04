package test;

public class TransportationWalk {
	
	private Walk w;
	private String start, end;
	public TransportationWalk(Walk w) {
		this.w = w;
	}
	public void move() {
		w.print();
	}
	public void setStart(String start) {
		this.start = start;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getStart() {
		return start;
	}
	public String getEnd() {
		return end;
	}
}
