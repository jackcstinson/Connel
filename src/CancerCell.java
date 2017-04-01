
public class CancerCell extends Cell {
	
	private boolean hasAttach = false;
	private int q;
	
	public CancerCell(int i, int j) {
		super(i, j);	
		Controller.numCancer++;
	}

	public void step() {
		
		if (!hasAttach) {
			q = 0;
			try {
				 if (Controller.cells[x+1][y] instanceof CancerCell) {
					 q++;
				 }
			} catch (Exception e) {};
			try {
				if (Controller.cells[x-1][y] instanceof CancerCell){
					q++;
				}
			} catch (Exception e) {};
			try {
				if (Controller.cells[x][y+1] instanceof CancerCell){
					q++;
				}
			} catch (Exception e) {};
			try {
				if (Controller.cells[x][y-1] instanceof CancerCell){
					q++;
				}
			} catch (Exception e) {};
			
			//System.out.println(q);
			
			double k1dash = Controller.k1 * (1 - q / 4.0);
			convertCells(k1dash);
			
			//This code block converts C -> E
			if (Controller.rand.nextDouble() < Controller.k2) {
				hasAttach = true;
				Controller.numCancer--;
				Controller.numAttached++;
			}
		}
		
		//This code block converts E -> D
		else {
			if (Controller.rand.nextDouble() < Controller.k3) {
				Controller.numAttached--;
				Controller.killCell(this);
				
			}
		}
		
		
		
	}
	
	private void convertCells(double k1dash) {
		
		try {
			if (Controller.rand.nextDouble() < k1dash && healthCheck(x+1, y)) {
				Controller.cells[x+1][y] = new CancerCell(x+1, y);
				Controller.numHealthy--;
				return;
			}
			} catch (Exception e) {};
		try {
			if (Controller.rand.nextDouble() < k1dash && healthCheck(x-1, y)) {
				Controller.cells[x-1][y] = new CancerCell(x-1, y);
				Controller.numHealthy--;
			}
				return;
			}catch (Exception e) {};
		try {
			if (Controller.rand.nextDouble() < k1dash && healthCheck(x, y+1)) {
				Controller.cells[x][y+1] = new CancerCell(x, y+1);
				Controller.numHealthy--;
			}
				return;
			} catch (Exception e) {};
		try {
			if (Controller.rand.nextDouble() < k1dash && healthCheck(x, y-1)) {
				Controller.cells[x][y-1] = new CancerCell(x, y-1);
				Controller.numHealthy--;
				return;
			}
		} catch (Exception e) {};
		
	}
	
	public String toChar() {
		if (hasAttach) return "[e]";
		else return "[C]";
	}
	
	private boolean healthCheck(int x, int y) {
		return Controller.cells[x][y] instanceof HealthyCell;
	}

}
