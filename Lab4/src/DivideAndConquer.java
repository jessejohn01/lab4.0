import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;
import java.util.Arrays;
import java.util.Comparator;
/**
 * Kyle Webster and Jesse Arstein
 * Finished November 28, 2017
 */
public class DivideAndConquer {
	private String inputFilePath = "input.txt"; // Easy filename access.
	private int nodeAmount = this.readInCount(inputFilePath); // Amount of nodes created
	private Node[] nodeArray = new Node[nodeAmount]; // Array to index all nodes
	private double minimum = 999999999; //maximum double, anything else has to be smaller than this starting point
	private Node[] minimumPoint = new Node[2]; //the shortest distance will be between 2 nodes, saved here

	private int readInCount(String inputFilePath) { // Method to read in node count
		int lineCount = 0;
		try {
			BufferedReader input1 = new BufferedReader(new FileReader(inputFilePath)); // Reads through file
			while (input1.readLine() != null) {
				lineCount++; // Counts
			}
			input1.close();

		} catch (IOException e) {
			System.out.println("File Not Found.");
		}
		return lineCount;
	}

	private String[] readInStrings() { // Method to return a string array of the nodes.
		String[] inputStringArray = new String[nodeAmount];
		try {

			BufferedReader input2 = new BufferedReader(new FileReader(inputFilePath)); //Read through file
			for (int i = 0; i < inputStringArray.length; i++) {
				inputStringArray[i] = input2.readLine();
			}
			input2.close();

		} catch (IOException e) {
			System.out.println("File Not Found");
		}
		return inputStringArray;
	}

	public void buildNodes() { // Method to build the nodes and index them
		String[] inputStringArray = this.readInStrings(); // Line to take in the Node Lines.
		for(int i = 0; i<nodeAmount; i++) {
			String[] bufferStringArray = inputStringArray[i].split(","); //Splits the line by comma.
			double x = Double.parseDouble(bufferStringArray[0]); // parses the double from the array
			double y = Double.parseDouble(bufferStringArray[1]); //parses the double from the array.
			nodeArray[i] = new Node(x,y); // Creates Node and indexes
		}
	}

	public void shortestDistance()
	{
		Arrays.sort(nodeArray, xComparator);//uses the Arrays java util to sort the nodeArray by x values then y values
		System.out.println(Arrays.toString(nodeArray)); //prints node for easy reference
		findClosestPoints(nodeArray); //recursive algorithm that will find the shortest on the left

		int left = nodeArray.length / 2;//this is a recall of the recursive algorithm for the right points
		int right = nodeArray.length / 2 + nodeArray.length%2;

		Node[] nRight = new Node[right];
		for (int i = 0; i < right; i++)
			nRight[i] = nodeArray[i + left];
		findClosestPoints(nRight);
		System.out.println("Final Result: Closest pair: " + Arrays.toString(minimumPoint) + ", Distance = " + minimum); //prints the final results
	}

	public Node[] findClosestPoints(Node[] inNode) //recursive algorithm seperates the array until size 1 or 2
	{
			Node[] leftMin, rightMin, closest;
			closest = inNode;
			int n = inNode.length-1;
			if (n > 2) {
				System.out.println("Solving Problem at Point " + inNode[0].toString() + " ... Point[" + inNode[n].toString() + "]");
				System.out.println("Dividing at Point[" + inNode[n/2].toString() + "]");
				int left = n / 2;
				int right = n / 2 + n%2;

				// the set datas
				Node[] nLeft = new Node[left]; //creates new array size n/2
				Node[] nRight = new Node[right]; //creates new array of the rest of the original array


				for (int i = 0; i < left; i++)
					nLeft[i] = inNode[i]; // fills the left array
				for (int i = 0; i < right; i++)
					nRight[i] = inNode[i + left]; //fills the right array
				leftMin = findClosestPoints(nLeft); //recursively finds points at most 1 apart from each other using recursion
				rightMin = findClosestPoints(nRight);//recursively finds points at most 1 apart from each other using recursion
				closest = merge(leftMin, rightMin);//merges the found points and finds the minimum between the two
				return closest;
			}
			return closest;
	}

	private Node[] merge(Node[] nLeft, Node[] nRight) //finds the smallest points between a set of 2 points
	{
		double d1 = 999999999;
		double d2 = 999999999;
		if(nLeft.length != 1) { //ensures that the algorithm does not get an error
			d1 = distance(nLeft[0], nLeft[1]);
		}
		else{ //for if one of the input points is a size 1
			System.out.println("Solving Problem at Point " + nLeft[0].toString() + " ... Point[" + nLeft[0].toString() + "]");
			System.out.println("Found Solution: INF");
		}
		if(nRight.length != 1) { //ensures that the algorithm does not get an error
			d2 = distance(nRight[0], nRight[1]);
		}
		else{ //for if one of the input points is a size 1
			System.out.println("Solving Problem at Point " + nRight[0].toString() + " ... Point[" + nRight[0].toString() + "]");
			System.out.println("Found Solution: INF");
		}

		double D = d1 < d2 ? d1 : d2; //uses the ? conditional operator to prevent excessive if - else statements in determining the smallest value
		Node[] min = d1 < d2 ? nLeft : nRight; //uses the ? conditional operator to prevent excessive if - else statements in determining the smallest point

		if(D < minimum) //if the smallest value is smaller than the instance variable, then making the point the smallest instance variable
		{
			minimum = D;
			minimumPoint = min;
		}
		System.out.println("Combining Problems: " + Arrays.toString(nLeft) + " and " + Arrays.toString(nRight));
		System.out.println("Found Solution: " + min[0].toString() + ", " + min[1].toString() + ", Distance: " + D);
		return min;
	}

	private double distance(Node x1, Node x2) //calculates the distance between 2 points
	{
		double length = 0;
		System.out.println("Solving Problem at Point " + x1.toString() + " ... Point[" + x2.toString() + "]");

			double xa = x1.getX();
			double xb = x2.getX();
			double ya = x1.getY();
			double yb = x2.getY();
			double xLength = Math.pow(xb - xa, 2); //(x2+x1)^2
			double yLength = Math.pow(yb - ya, 2); //(y2 + y1)^2
			length = Math.pow(xLength+yLength, .5); //sqrt(xLength + yLength)
			System.out.println("Found Result with length: " + length);
			return length;
	}

	public static final Comparator<Node> xComparator = new Comparator<Node>() { //java util comparator extension used to sort x values then y values for Arrays.sort()
		public int compare(Node a, Node b) {
			if (a.x < b.x) {
				return -1;
			}
			if (a.x > b.x) {
				return 1;
			}
			//if equal, sort by y values
			if (a.y < b.y) {
				return -1;
			}
			if (a.y > b.y) {
				return 1;
			}
			return 0;
		}
	};
}
