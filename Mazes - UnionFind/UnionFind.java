/*
 * Assignment number : 8
 * File name : UnionFind.java
 * Name : Guy Itzhaki
 * Email: guyitzhaki@outlook.com
 */

/**
 * Implementation of the Union-Find ADT. 
 */ 
public class UnionFind { 
 
   int up[];
   int weight[];
   int numSets;
   int height[];
 
 
   /** 
    * Constructor - initializes up and weight arrays. 
    * 
    * @param numElements initial number of singleton sets. 
    */ 
   public UnionFind (int numElements) 
   { 
		//your code comes here
   		this.up=new int[numElements+1];
   		this.weight=new int[numElements+1];
	   	this.height=new int[numElements+1];
   		numSets=numElements;
   		for (int i=1; i<=numElements; i++)
   		{
   			this.up[i]=-1;
   			this.weight[i]=1;
   			this.height[i]=0;
   		}
   } 
 
   /** 
    * Returns the size of the set which contains i.
    * 
    * @param i
    */ 
   public int getSize(int i)
   {
		//your code comes here
   		return this.weight[find(i)];
   }
   
   /** 
    * Returns the height of the tree containing i. 
	* Disregards any changes which might have been caused by path compression.
    * 
    * @param i
    */ 
   public int getHeight(int i)
   {
		//your code comes here
   		return this.height[find(i)];
   }
		
   /** 
    * Unites two sets using weighted union. 
    * 
    * @param i is an element of the first set.
    * @param j is an element of the second set. 
    */ 
	public void union (int i, int j) 
	{ 
		//your code comes here
		int heighti=getHeight(i);
		int heightj=getHeight(j);
		int findi=find(i);
		int findj=find(j);
		if (findi==findj)
 			return ;
 		if (weight[findi]<weight[findj])
 		{
 			up[findi]=findj;
 			weight[findj]+=weight[findi];
 			height[findj]=Math.max(heighti+1, heightj);
 		}
 		else
 		{
 			up[findj]=findi;
 			weight[findi]+=weight[findj];
 			height[findi]=Math.max(heighti, heightj+1);
 		}
 		numSets--;
   	}

	/** 
    * Finds the set representative, and applies path compression. 
    * 
    * @param i the element to search. 
    * @return the representative of the set which contains i. 
    */ 
   	public int find (int i) 
   	{
		//your code comes here
   		if (up[i]==-1)
   			return i;
   		up[i]=find(up[i]);
   		return up[i];
   	} 
 
   	/** 
    * Find the current number of sets. 
    * 
    * @return the number of set. 
    */ 
   	public int getNumSets() 
   	{ 
		//your code comes here
   		return this.numSets;
   	}
 
   	/** 
    * Prints the contents of the up array. 
    */ 
   	private void debugPrintUp() 
    {
      	System.out.print ("up:     "); 
      	for (int i = 1; i < up.length; i++) 
        	System.out.print (up[i] + " "); 
      	System.out.println (""); 
   	} 
 
   	/** 
    * Prints the results of running find on all elements. 
    */ 
   	private void debugPrintFind() 
   	{ 
      	System.out.print ("find:   "); 
      	for (int i = 1; i < up.length; i++) 
        	System.out.print (find (i) + " "); 
      	System.out.println (""); 
   	} 
 
   	/** 
    * Prints the contents of the weight array. 
    */ 
   	private void debugPrintWeight() 
   	{ 
      	System.out.print ("weight: "); 
      	for (int i = 1; i < weight.length; i++) 
      	   System.out.print (weight[i] + " "); 
      	System.out.println (""); 
   	} 
 
   	/** 
    * Various tests for the Union-Find functionality. 
    * 
    * @param args command line arguments - not used. 
    */ 
   	public static void main (String[] args) 
   	{ 
    	UnionFind uf = new UnionFind (10); 
 
    	uf.debugPrintUp(); 
      	uf.debugPrintFind(); 
      	uf.debugPrintWeight(); 
      	System.out.println ("Number of sets: " + uf.getNumSets()); 
      	System.out.println (""); 
 
	    uf.union (2, 1); 
    	uf.union (3, 2); 
      	uf.union (4, 2); 
      	uf.union (5, 2); 
 
    	uf.debugPrintUp(); 
      	uf.debugPrintFind(); 
      	uf.debugPrintWeight(); 
      	System.out.println ("Number of sets: " + uf.getNumSets()); 
      	System.out.println(); 
 
      	uf.union (6, 7); 
      	uf.union (8, 9); 
      	uf.union (6, 8); 
 
      	uf.debugPrintUp(); 
      	uf.debugPrintFind(); 
      	uf.debugPrintWeight(); 
      	System.out.println ("Number of sets: " + uf.getNumSets()); 
      	System.out.println(); 
 
      	uf.find (8); 
 
      	uf.debugPrintUp(); 
      	uf.debugPrintFind(); 
      	uf.debugPrintWeight(); 
      	System.out.println ("Number of sets: " + uf.getNumSets()); 
      	System.out.println(); 
   	} 
} 
