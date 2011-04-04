package bads.aflevering8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.In;
import stdlib.StdOut;

/**
 * Class Words is used to test if there is a way from one word to another in a collection of words. 
 * 
 * @author Jakob Melnyk & Frederik Roden Lysgaard
 * @version 1
 *
 */
public class Words {

	/**
	 * Runs the test.
	 * 
	 * @param args Location of file to be used in Words.
	 */
	public static void main(String[] args) {
		//In objects from Stdlib.
		In dat = new In(args[0] + ".dat"); //In file used to construct the Word graph.
		In in = new In(args[0] + "-test.in"); //In file containing the words to be checked.


		ArrayList<String> words = new ArrayList<String>(); //List to hold the words, so that we can later refer to it using an index.

		while(!dat.isEmpty()){ // Fills out the List with the Words from the .dat file.
			words.add(dat.readLine());
		}

		Words w = new Words(); //Only used to make the Inner objects Digraph and BreadthFirstDirectedPaths

		Digraph dg = w.new Digraph(words.size()); //Constructs the Directed Graph from the size of the list of Words.

		//Fills out the edges in the Directed Graph. This is done in Quadratic time. :/
		for(String s : words){ 
			for(String a : words){
				if(!a.equals(s)){ //Checks if it's the same word - if they are, we should not addEdges from the word to itself.
					if(compareWords(s, a)){ //Checks if there should be an arc from s to a.
						dg.addEdge(words.indexOf(s), words.indexOf(a));
					}
				}
			}
		}

		while(!in.isEmpty()){	//Runs BFS on the word combinations from the -test.in file and prints the result.
			BreadthFirstDirectedPaths BFS = w.new BreadthFirstDirectedPaths(dg, words.indexOf(in.readString()));
			StdOut.println(BFS.distTo(words.indexOf(in.readString())));
		}
	}

	/**
	 * Checks if there should be an arc from A to B. May work on longer or shorter strings, but has not been tested.
	 * 
	 * @param a First word. Must be five letters to provide a proper result.
	 * @param b Second word. Must be five letters to provide a proper result.
	 * @return If there should be an arc, returns true. Else, returns false.
	 */
	private static boolean compareWords(String a, String b){
		//There are different ways to do this - we could also have removed one letter from the b word, 
		//sorted both Strings and then compared them. This would have meant that we did not need to run compareWords recursively.
		String temp = "";
		String first = a.substring(1);

		if(first.length() == 0) return true; //If a is empty and we have not yet returned false, then there should be an arc from a to b.

		if(b.indexOf(first.charAt(0)) == -1){
			return false; //If an element in the first word does not exist in the second word, then the comparison fails. 
		}

		int i = b.indexOf(first.charAt(0)); //i is the index of the first letter in the a String.
		if(i != 0)temp += b.substring(0, i); //If i is at the start of the String, this should not add anything to the temp String
		if(i != b.length()-1)temp += b.substring(i+1); //If i is at the end of the b-string, it should not add anything to the temp String.

		return compareWords(first, temp); // Recursive.
	}

	//Classes borrowed from http://algs4.cs.princeton.edu/code/
	//Not our own implementation!
	public class Digraph {
		private final int V;
		private int E;
		private Bag<Integer>[] adj;
		/**
		 * Create an empty digraph with V vertices.
		 */
		public Digraph(int V) {
			if (V < 0) throw new RuntimeException("Number of vertices must be nonnegative");
			this.V = V;
			this.E = 0;
			adj = (Bag<Integer>[]) new Bag[V];
			for (int v = 0; v < V; v++) {
				adj[v] = new Bag<Integer>();
			}
		}

		/**
		 * Return the number of vertices in the digraph.
		 */
		public int V() {
			return V;
		}

		/**
		 * Return the number of edges in the digraph.
		 */
		public int E() {
			return E;
		}

		/**
		 * Add the directed edge v-w to the digraph.
		 */
		public void addEdge(int v, int w) {
			adj[v].add(w);
			E++;
		}

		/**
		 * Return the list of neighbors of vertex v as in Iterable.
		 */
		public Iterable<Integer> adj(int v) {
			return adj[v];
		}
	}

	private class Bag<Item> implements Iterable<Item> {
		private int N;         // number of elements in bag
		private Node first;    // beginning of bag

		// helper linked list class
		private class Node {
			private Item item;
			private Node next;
		}

		/**
		 * Create an empty stack.
		 */
		public Bag() {
			first = null;
			N = 0;
		}

		/**
		 * Is the BAG empty?
		 */
		public boolean isEmpty() {
			return first == null;
		}

		/**
		 * Return the number of items in the bag.
		 */
		public int size() {
			return N;
		}

		/**
		 * Add the item to the bag.
		 */
		public void add(Item item) {
			Node oldfirst = first;
			first = new Node();
			first.item = item;
			first.next = oldfirst;
			N++;
		}


		/**
		 * Return an iterator that iterates over the items in the bag.
		 */
		public Iterator<Item> iterator()  {
			return new LIFOIterator();  
		}

		// an iterator, doesn't implement remove() since it's optional
		private class LIFOIterator implements Iterator<Item> {
			private Node current = first;

			public boolean hasNext()  { return current != null;                     }
			public void remove()      { throw new UnsupportedOperationException();  }

			public Item next() {
				if (!hasNext()) throw new NoSuchElementException();
				Item item = current.item;
				current = current.next; 
				return item;
			}
		}

	}

	public class Queue<Item> implements Iterable<Item> {
		private int N;         // number of elements on queue
		private Node first;    // beginning of queue
		private Node last;     // end of queue

		// helper linked list class
		private class Node {
			private Item item;
			private Node next;
		}

		/**
		 * Create an empty queue.
		 */
		public Queue() {
			first = null;
			last  = null;
		}

		/**
		 * Is the queue empty?
		 */
		public boolean isEmpty() {
			return first == null;
		}


		/**
		 * Add the item to the queue.
		 */
		public void enqueue(Item item) {
			Node x = new Node();
			x.item = item;
			if (isEmpty()) { first = x;     last = x; }
			else           { last.next = x; last = x; }
			N++;
		}

		/**
		 * Remove and return the item on the queue least recently added.
		 * Throw an exception if the queue is empty.
		 */
		public Item dequeue() {
			if (isEmpty()) throw new RuntimeException("Queue underflow");
			Item item = first.item;
			first = first.next;
			N--;
			if (isEmpty()) last = null;   // to avoid loitering
			return item;
		}

		/**
		 * Return an iterator that iterates over the items on the queue in FIFO order.
		 */
		public Iterator<Item> iterator()  {
			return new FIFOIterator();  
		}

		// an iterator, doesn't implement remove() since it's optional
		private class FIFOIterator implements Iterator<Item> {
			private Node current = first;

			public boolean hasNext()  { return current != null;                     }
			public void remove()      { throw new UnsupportedOperationException();  }

			public Item next() {
				if (!hasNext()) throw new NoSuchElementException();
				Item item = current.item;
				current = current.next; 
				return item;
			}
		}
	}

	public class BreadthFirstDirectedPaths {
		private static final int INFINITY = -1;
		private boolean[] marked;  // marked[v] = is there an s->v path?
		private int[] edgeTo;      // edgeTo[v] = last edge on shortest s->v path
		private int[] distTo;      // distTo[v] = length of shortest s->v path
		private final int s;       // the source

		public BreadthFirstDirectedPaths(Digraph G, int s) {
			marked = new boolean[G.V()];
			distTo = new int[G.V()];
			edgeTo = new int[G.V()];
			for (int v = 0; v < G.V(); v++) distTo[v] = INFINITY;
			this.s = s;
			bfs(G, s);
		}

		private void bfs(Digraph G, int s) {
			Queue<Integer> q = new Queue<Integer>();
			marked[s] = true;
			distTo[s] = 0;
			q.enqueue(s);
			while (!q.isEmpty()) {
				int v = q.dequeue();
				for (int w : G.adj(v)) {
					if (!marked[w]) {
						edgeTo[w] = v;
						distTo[w] = distTo[v] + 1;
						marked[w] = true;
						q.enqueue(w);
					}
				}
			}
		}

		// length of shortest path from s to v
		public int distTo(int v) {
			return distTo[v];
		}

		// is there a directed path from s to v?
		public boolean hasPathTo(int v) {
			return marked[v];
		}
	}
}