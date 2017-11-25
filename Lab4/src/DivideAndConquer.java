import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

}
