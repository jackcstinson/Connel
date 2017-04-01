import java.util.Random;

public class Controller {
	
	public static Cell[][] cells;
	public static int size = 100;
	public static Random rand;
	//k1 is for cancer conversion rate
	public static double k1 = 0.4;
	//k2 is for cancer->attached rate
	public static double k2 = 0.1;
	//k3 kills cancer cells
	public static double k3 = 0.35;
	//k4 revives dead cells
	public static double k4 = 0.4;
	
	public static int numHealthy;
	public static int numCancer;
	public static int numAttached;
	public static int numDead;
	

	public static void main(String[] args) {
		
		rand = new Random(System.currentTimeMillis());
		
		initialise();
		printInfo();
		
		for (int i=0; i<1000; i++) {
			
			for (int j=0; j<10; j++) {
				runLoop();
				printInfo();
			}
			
			printInfo();
			
		}
		
		
	}
	
	private static void runLoop() {
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				cells[i][j].step();
			}
		}
	}
	
	private static void initialise() {
		
		cells = new Cell[size][size];
		
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				
				if (rand.nextInt(100) < 5) {
					cells[i][j] = new CancerCell(i,j);
				}
				else 
				cells[i][j] = new HealthyCell(i,j);
				
			}
		}
	}
	
	private static void printCells() {
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				System.out.print(cells[i][j].toChar());
			}
			System.out.println();
		}
		
		System.out.println();
	}
	
	public static void killCell(Cell c) {
		cells[c.x][c.y] = new DeadCell(c.x,c.y);
	}
	
	public static void reviveCell(Cell c) {
		cells[c.x][c.y] = new HealthyCell(c.x,c.y);
	}
	
	private static void printInfo() {
		System.out.println("Num Healthy: " + Controller.numHealthy);
		System.out.println("Num Cancer: " + Controller.numCancer);
		System.out.println("Num Attached: " + Controller.numAttached);
		System.out.println("Num Dead: " + Controller.numDead);
		System.out.println();
		
	}

}
