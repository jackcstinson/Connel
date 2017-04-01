

public class DeadCell extends Cell {
	
	public DeadCell(int i, int j) {
		super(i, j);	
		Controller.numDead++;
	}

	
	public void step() {
		if (Controller.rand.nextDouble() < Controller.k4) {
			Controller.numDead--;
			Controller.reviveCell(this);
		}
		
	}
	
	public String toChar() {
		return "[D]";
	}

}
