import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* Disjoint set using rank and path compression
*	Consists of 3 operations
*	makeSet which will create a node and set the parent as self and rank as zero
*	union which will join 2 different sets if possible
*	findSet which will return the value of the set to which a particular element belongs to. Also, performs path compression
*	Author : vi123
*/

class Node
{
	int data;
	Node parent;
	int rank;
}

public class DisjointSet {
	
	Map<Integer,Node> map = new HashMap<Integer,Node>();
	
	public void makeSet(int data)
	{
		Node n = new Node();
		n.data = data;
		n.rank = 0;
		n.parent = n;
		map.put(data,n);
	}
	
	public void union(int data1,int data2)
	{
		Node n1 = map.get(data1);
		Node n2 = map.get(data2);
		
		Node parent1 = findSet(n1);
		Node parent2 = findSet(n2);
		
		if(parent1.rank >= parent2.rank)
		{
			parent1.rank = parent1.rank==parent2.rank?parent1.rank+1: parent1.rank;
			parent2.parent = parent1;
		}
		else
		{
			parent1.parent=parent2;
		}
		
	}
	
	public Node findSet(Node n)
	{
		if(n.parent == n)
			return n;
		else
		{
			n.parent = findSet(n.parent);
		}
		
		return n.parent;
		
	}
	
	public int findSet(int data)
	{
		return findSet(map.get(data)).data;
	}
	
	
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int q = in.nextInt();
		
		DisjointSet set = new DisjointSet();

		for(int i =0;i<n;i++)
			set.makeSet(i);
		
		for(int i =0;i<q;i++)
			set.union(in.nextInt(),in.nextInt());
		
		for(int i =0;i<n;i++)
			System.out.println(set.findSet(i));
		
		in.close();
	}

}


