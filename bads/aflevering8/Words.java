package bads.aflevering8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.In;
import stdlib.StdOut;

public class Words {

	public static void main(String[] args) {
		In dat = new In(args[0] + ".dat");
		In in = new In(args[0] + "-test.in");
		ArrayList<String> words = new ArrayList<String>();
		while(!dat.isEmpty()){
			words.add(dat.readLine());
		}
		
		Words w = new Words(); //Only used to make the Inner objects Digraph and BreadthFirstDirectedPaths
		
		Digraph dg = w.new Digraph(words.size());
		for(String s : words){
			for(String a : words){
				if(!a.equals(s)){
					//System.out.println(s + " " + a);
					//System.out.println(compareWords(s, a));
					if(compareWords(s.substring(1), a)){
						//System.out.println(s + " " + a);
						//System.out.println(words.indexOf(s) + " " + words.indexOf(a));
						dg.addEdge(words.indexOf(s), words.indexOf(a));
					}
				}
			}
		}
		while(!in.isEmpty()){
			BreadthFirstDirectedPaths BFS = w.new BreadthFirstDirectedPaths(dg, words.indexOf(in.readString()));
			StdOut.println(BFS.distTo(words.indexOf(in.readString())));
		}
	}

	/**
	 * Checks if there should be an arc from A to B.
	 * 
	 * @param a First word.
	 * @param b Second word.
	 * @return If there should be a graph, returns true. Else, false.
	 */
	private static boolean compareWords(String a, String b){
		String temp = "";
		if(a.length() == 0) return true;
		if(b.indexOf(a.charAt(0)) == -1){
			return false;
		}
		for(int i = 0; i < b.length(); i++){
			if(b.charAt(i) == (a.charAt(0))){
				if(i != 0)temp += b.substring(0, i);
				if(i != b.length()-1)temp += b.substring(i+1);
				return compareWords(a.substring(1), temp);
			}
		}
		return true;
	}
	
	
	//Classes borrowed from http://algs4.cs.princeton.edu/code/
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