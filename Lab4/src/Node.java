
public class Node { //Node Environment to setup
	private double x = 0;
	private double y = 0;
	
	public Node(double xIn,double yIN) { //Constructor that creates the Nodes
		x = xIn;
		y = yIN;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double[] getArrayOfXandY(){ // An array that will be return containing the points for easy access. 
		double[] tempArray = new double[2];
		tempArray[0] = x;
		tempArray[1] = y;
		return tempArray;
		
	}
}
