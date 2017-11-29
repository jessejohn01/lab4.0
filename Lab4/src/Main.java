public class Main {
	 public static void main(String[] args) {
		 DivideAndConquer mainInstance = new DivideAndConquer(); // Creates the instance that runs the algorithm.
		 mainInstance.buildNodes();
		 Node[] array = mainInstance.getNodeArray();
		 array = mainInstance.sort(array, 0, array.length-1);
		 mainInstance.findSmallestDistance(array, 0, array.length-1);
	 }
}
