import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;

public class DivideAndConquer {
	String inputFilePath = "input.txt"; // Easy filename access.
	int nodeAmount = this.readInCount(inputFilePath); // Amount of nodes created
	Node[] nodeArray = new Node[nodeAmount]; // Array to index all nodes

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
		System.out.println("Nodes have been indexed.");
	}

	public Node[] getNodeArray() {
		return nodeArray;
	}

	public void merge(Node[] arr, int l, int m, int r)
	{
		int n1 = m - l + 1;
		int n2 = r - m;
		Node L[] = new Node [n1];
		Node R[] = new Node [n2];
		for (int i=0; i<n1; i++) {
			L[i] = arr[l + i];
		}
		for (int j=0; j<n2; j++) {
			R[j] = arr[m + 1 + j];
		}
		int i = 0, j = 0;
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i].getX() <= R[j].getX()) {
				arr[k] = L[i];
				i++;
			}
			else if(L[i].getX()> R[j].getX()) {
				arr[k] = R[j];
				j++;
			}
			else if(L[i].getY()> R[j].getY()) {
				arr[k] = R[j];
				j++;
			}
			else {
				arr[k] = L[i];
				i++;
			}
			k++;
		}
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}

	}

	public Node[] sort(Node[] inNode, int l, int r)
	{
		if (l < r)
		{
			int m = (l+r)/2;
			sort(inNode, l, m);
			sort(inNode , m+1, r);
			merge(inNode, l, m, r);
		}
		return inNode;
	}

	public void findSmallestDistance(Node[] inNode, int l, int r)
	{
		int m = (l + r) / 2;
		if(l < r-1) {
			System.out.println("Solving Problem at Point[" + l + "]... Point[" + r + "]");
			System.out.println("Dividing at Point[" + m + "]");
			findSmallestDistance(inNode, l, m);
			System.out.println("Solving Problem at Point[" + l + "]... Point[" + m + "]");
			double left = distanceFormula(inNode, l, m);
			findSmallestDistance(inNode, m + 1, r);
			System.out.println("Solving Problem at Point[" +(m+1) + "]... Point[" + r + "]");
			double right = distanceFormula(inNode, m + 1, r);
		}
	}

	public double distanceFormula(Node[] inNode, int l, int r)
	{
		double length = 0;
		if(l == r)
		{
			length = 999999999;
			return length;
		}
		else{
			double x1 = inNode[l].getX();
			double x2 = inNode[r].getX();
			double y1 = inNode[l].getY();
			double y2 = inNode[r].getY();
			double xLength = Math.pow(x2 - x1, 2);
			double yLength = Math.pow(y2 - y1, 2);
			length = Math.pow(xLength+yLength, .5);
			System.out.println(length);
			return length;
		}
	}
}
