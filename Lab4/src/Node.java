import java.awt.geom.Point2D;

/**
 * Kyle Webster and Jesse Arstein
 * Finished November 28, 2017
 */

public class Node extends Point2D.Double { //Chose Point2D to simplify the sorting and storing of 2D points
	public String name;
	public Node(double x, double y, String name)
	{
		super(x,y); //makes a 2D (x,y) point
		this.name = name; //names the Node for simplifying the
	}
	public Node(double x, double y)
	{
		this(x,y,x+"_"+y);
	}


	public String toString() //prints the value of the point
	{
		return "("+x+","+y+")";
	}

	public boolean equals(Object o) //assumes the Object o is a Node
	{
		Node inNode = (Node)o;
		return this.name.equals(inNode.name);
	}
}
