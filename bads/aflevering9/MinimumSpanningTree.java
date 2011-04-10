package bads.aflevering9;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

import stdlib.StdIn;

public class MinimumSpanningTree {		

	public static void main(String[] args) {
		MinimumSpanningTree mst = new MinimumSpanningTree();
		HashMap<String, Integer> cities = new HashMap<String, Integer>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		String temp = null;
		String city1 = null;
		String city2 = null;
		int weight = 0;
		Integer i = 0;

		while(!StdIn.isEmpty()){
			temp = StdIn.readLine();
			if(!temp.contains("--")){
				cities.put(temp.substring(0, temp.length() - 1), i++);
			}
			else{
				city1 = temp.substring(0, temp.indexOf("--"));
				city2 = temp.substring(temp.indexOf("--") + 2, temp.indexOf("[") - 1);
				weight = Integer.parseInt(temp.substring(temp.indexOf("[") + 1, temp.indexOf("]")));
				
				/*
				System.out.println(cities.get(city1));
				System.out.println(city1);
				System.out.println(cities.get(city2));
				System.out.println(city2);
				*/
				edges.add(mst.new Edge(cities.get(city1), cities.get(city2), weight));
			}
		}

		EdgeWeightedGraph ewg = mst.new EdgeWeightedGraph(cities.size());
		
		for(Edge e : edges){
			ewg.addEdge(e);
		}
		PrimMST prim = mst.new PrimMST(ewg);
		System.out.println(prim.weight());
	}

	public class EdgeWeightedGraph {
		private final int V;
		private int E;
		private Bag<Edge>[] adj;

		/**
		 * Create an empty edge-weighted graph with V vertices.
		 */
		public EdgeWeightedGraph(int V) {
			if (V < 0) throw new RuntimeException("Number of vertices must be nonnegative");
			this.V = V;
			this.E = 0;
			adj = (Bag<Edge>[]) new Bag[V];
			for (int v = 0; v < V; v++) adj[v] = new Bag<Edge>();
		}

		/**
		 * Return the number of vertices in this graph.
		 */
		public int V() {
			return V;
		}

		/**
		 * Return the number of edges in this graph.
		 */
		public int E() {
			return E;
		}

		/**
		 * Add the edge e to this graph.
		 */
		public void addEdge(Edge e) {
			int v = e.either();
			int w = e.other(v);
			adj[v].add(e);
			adj[w].add(e);
			E++;
		}

		/**
		 * Return the edges incident to vertex v as an Iterable.
		 * To iterate over the edges incident to vertex v, use foreach notation:
		 * <tt>for (Edge e : graph.adj(v))</tt>.
		 */
		public Iterable<Edge> adj(int v) {
			return adj[v];
		}

		/**
		 * Return all edges in this graph as an Iterable.
		 * To iterate over the edges, use foreach notation:
		 * <tt>for (Edge e : graph.edges())</tt>.
		 */
		public Iterable<Edge> edges() {
			Bag<Edge> list = new Bag<Edge>();
			for (int v = 0; v < V; v++) {
				int selfLoops = 0;
				for (Edge e : adj(v)) {
					if (e.other(v) > v) {
						list.add(e);
					}
					// only add one copy of each self loop
					else if (e.other(v) == v) {
						if (selfLoops % 2 == 0) list.add(e);
						selfLoops++;
					}
				}
			}
			return list;
		}

		/**
		 * Return a string representation of this graph.
		 */
		public String toString() {
			String NEWLINE = System.getProperty("line.separator");
			StringBuilder s = new StringBuilder();
			s.append(V + " " + E + NEWLINE);
			for (int v = 0; v < V; v++) {
				s.append(v + ": ");
				for (Edge e : adj[v]) {
					s.append(e + "  ");
				}
				s.append(NEWLINE);
			}
			return s.toString();
		}
	}

	public class Edge implements Comparable<Edge> { 

		private final int v;
		private final int w;
		private final double weight;

		/**
		 * Create an edge between v and w with given weight.
		 */
		public Edge(int v, int w, double weight) {
			this.v = v;
			this.w = w;
			this.weight = weight;
		}

		/**
		 * Return the weight of this edge.
		 */
		public double weight() {
			return weight;
		}

		/**
		 * Return either endpoint of this edge.
		 */
		public int either() {
			return v;
		}

		/**
		 * Return the endpoint of this edge that is different from the given vertex
		 * (unless a self-loop).
		 */
		public int other(int vertex) {
			if      (vertex == v) return w;
			else if (vertex == w) return v;
			else throw new RuntimeException("Illegal endpoint");
		}

		/**
		 * Compare edges by weight.
		 */
		public int compareTo(Edge that) {
			if      (this.weight() < that.weight()) return -1;
			else if (this.weight() > that.weight()) return +1;
			else                                    return  0;
		}

		/**
		 * Return a string representation of this edge.
		 */
		public String toString() {
			return String.format("%d-%d %.2f", v, w, weight);
		}
	}

	public class PrimMST {
		private Edge[] edgeTo;        // edgeTo[v] = shortest edge from tree vertex to non-tree vertex
		private double[] distTo;      // distTo[v] = weight of shortest such edge
		private boolean[] marked;     // marked[v] = true if v on tree, false otherwise
		private IndexMinPQ<Double> pq;

		public PrimMST(EdgeWeightedGraph G) {
			edgeTo = new Edge[G.V()];
			distTo = new double[G.V()];
			marked = new boolean[G.V()];
			pq = new IndexMinPQ<Double>(G.V());
			for (int v = 0; v < G.V(); v++) distTo[v] = Double.POSITIVE_INFINITY;

			for (int v = 0; v < G.V(); v++)      // run from each vertex to find
				if (!marked[v]) prim(G, v);      // minimum spanning forest
		}

		// run Prim's algorithm in graph G, starting from vertex s
		private void prim(EdgeWeightedGraph G, int s) {
			distTo[s] = 0.0;
			pq.insert(s, distTo[s]);
			while (!pq.isEmpty()) {
				int v = pq.delMin();
				scan(G, v);
			}
		}

		// scan vertex v
		private void scan(EdgeWeightedGraph G, int v) {
			marked[v] = true;
			for (Edge e : G.adj(v)) {
				int w = e.other(v);
				if (marked[w]) continue;         // v-w is obsolete edge
				if (e.weight() < distTo[w]) {
					distTo[w] = e.weight();
					edgeTo[w] = e;
					if (pq.contains(w)) pq.change(w, distTo[w]);
					else                pq.insert(w, distTo[w]);
				}
			}
		}

		// return iterator of edges in MST
		public Iterable<Edge> edges() {
			Bag<Edge> mst = new Bag<Edge>();
			for (int v = 0; v < edgeTo.length; v++) {
				Edge e = edgeTo[v];
				if (e != null) {
					mst.add(e);
				}
			}
			return mst;
		}

		// return weight of MST
		public double weight() {
			double weight = 0.0;
			for (Edge e : edges())
				weight += e.weight();
			return weight;
		}
	}

	public class Bag<Item> implements Iterable<Item> {
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

	public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
		private int N;           // number of elements on PQ
		private int[] pq;        // binary heap using 1-based indexing
		private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
		private Key[] keys;      // keys[i] = priority of i

		public IndexMinPQ(int NMAX) {
			keys = (Key[]) new Comparable[NMAX + 1];    // make this of length NMAX??
			pq   = new int[NMAX + 1];
			qp   = new int[NMAX + 1];                   // make this of length NMAX??
			for (int i = 0; i <= NMAX; i++) qp[i] = -1;
		}

		// is the priority queue empty?
		public boolean isEmpty() { return N == 0; }

		// is k an index on the priority queue?
		public boolean contains(int k) {
			return qp[k] != -1;
		}

		// number of keys in the priority queue
		public int size() {
			return N;
		}

		// associate key with index k
		public void insert(int k, Key key) {
			if (contains(k)) throw new RuntimeException("item is already in pq");
			N++;
			qp[k] = N;
			pq[N] = k;
			keys[k] = key;
			swim(N);
		}

		// return the index associated with a minimal key
		public int min() { 
			if (N == 0) throw new RuntimeException("Priority queue underflow");
			return pq[1];        
		}

		// return a minimal key
		public Key minKey() { 
			if (N == 0) throw new RuntimeException("Priority queue underflow");
			return keys[pq[1]];        
		}

		// delete a minimal key and returns its associated index
		public int delMin() { 
			if (N == 0) throw new RuntimeException("Priority queue underflow");
			int min = pq[1];        
			exch(1, N--); 
			sink(1);
			qp[min] = -1;            // delete
			keys[pq[N+1]] = null;    // to help with garbage collection
			pq[N+1] = -1;            // not needed
			return min; 
		}

		// change the key associated with index k
		public void change(int k, Key key) {
			if (!contains(k)) throw new RuntimeException("item is not in pq");
			keys[k] = key;
			swim(qp[k]);
			sink(qp[k]);
		}

		// decrease the key associated with index k
		public void decrease(int k, Key key) {
			if (!contains(k)) throw new RuntimeException("item is not in pq");
			if (keys[k].compareTo(key) <= 0) throw new RuntimeException("illegal decrease");
			keys[k] = key;
			swim(qp[k]);
		}

		// increase the key associated with index k
		public void increase(int k, Key key) {
			if (!contains(k)) throw new RuntimeException("item is not in pq");
			if (keys[k].compareTo(key) >= 0) throw new RuntimeException("illegal decrease");
			keys[k] = key;
			sink(qp[k]);
		}


		/**************************************************************
		 * General helper functions
		 **************************************************************/
		private boolean greater(int i, int j) {
			return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
		}

		private void exch(int i, int j) {
			int swap = pq[i]; pq[i] = pq[j]; pq[j] = swap;
			qp[pq[i]] = i; qp[pq[j]] = j;
		}

		private void swim(int k)  {
			while (k > 1 && greater(k/2, k)) {
				exch(k, k/2);
				k = k/2;
			}
		}

		private void sink(int k) {
			while (2*k <= N) {
				int j = 2*k;
				if (j < N && greater(j, j+1)) j++;
				if (!greater(k, j)) break;
				exch(k, j);
				k = j;
			}
		}

		public Iterator<Integer> iterator() { return new HeapIterator(); }
		private class HeapIterator implements Iterator<Integer> {
			// create a new pq
			private IndexMinPQ<Key> copy;

			// add all elements to copy of heap
			// takes linear time since already in heap order so no keys move
			public HeapIterator() {
				copy = new IndexMinPQ<Key>(pq.length - 1);
				for (int i = 1; i <= N; i++)
					copy.insert(pq[i], keys[pq[i]]);
			}

			public boolean hasNext()  { return !copy.isEmpty();                     }
			public void remove()      { throw new UnsupportedOperationException();  }

			public Integer next() {
				if (!hasNext()) throw new NoSuchElementException();
				return copy.delMin();
			}
		}
	}
}