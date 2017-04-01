

public class HealthyCell extends Cell {
	
	public HealthyCell(int i, int j) {
		super(i, j);	
		Controller.numHealthy++;
	}
	
	public void step() {
	
	}
	
	public String toChar() {
		return "[H]";
	}
	

}
