package bads.aflevering10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import stdlib.StdIn;

/**
 * Our algorithm for solving the SuperVectorMario problem from ITU's BADS Spring 2011 course.
 * 
 * @author Jakob Melnyk & Frederik Roden Lysgaard
 * @version 1
 */
public class VectorMario {

	public static void main(String[] args) {

		/* Our code can seem a bit confusing, but hopefully the comments will clear this up. We use the standard implementation
		 * of the directed graph from the algorithms 4th edition book instead of creating our own.
		 * 
		 * In retrospective it might have been a better idea to create our own graph,
		 * but we have decided to not change what we have now.
		 */

		int yLength = Integer.parseInt(StdIn.readLine()); //Height of the coordinate-system.
		int xLength = Integer.parseInt(StdIn.readLine()); //Width of the coordinate-system.
		boolean[][] coords = new boolean[yLength][xLength]; //Coordinate system

		int graphID = 0; //What ID the note should have in the graph.
		HashSet<Coords> finishCoords = new HashSet<Coords>(); // The coordinates of the finishes.

		//Check the inner class "Jump" for comments on how the nodes work.
		HashSet<Jump> startJumps = new HashSet<Jump>(); //The start jumps so that we have a start point for our BreadthFirstSearch. 
		HashSet<Jump> finalJumps = new HashSet<Jump>(); //The different possible final jumps.
		HashMap<Jump, Integer> jumpMap = new HashMap<Jump, Integer>(); //All the possible jumps using the SuperVectorMario rules.
		Queue<Jump> jumpCreation = new Queue<Jump>(); //A queue used to create the jumps.

		int currentRow = 0; //The row currently being looked at by the reader.
		while(!StdIn.isEmpty()){
			String temp = StdIn.readLine();

			for(int i = 0; i < xLength; i++){
				if(temp.substring(i, i + 1).equals(" ")){ //If the checked position is an open space, we open it for travel.
					coords[currentRow][i] = true;
				}
				if(temp.substring(i, i + 1).equals("S")){ //If the checked position is a starting spot, then we add it to our system.
					coords[currentRow][i] = true; //Opens the starting spot for travel.
					Jump startNode = new Jump(i, currentRow, 0, 0); //Creates a jump at the current coordinates with [0, 0] speed.
					jumpCreation.enqueue(startNode); //Enqueues the starting jump, so we can start creating new nodes originating from it.
					startJumps.add(startNode); //Adds the start jump, so that we can use it later in our search.
				}
				if(temp.substring(i, i + 1).equals("F")){ //If the checked position is a finish, we open it for travel and add it to 
														  //our list of finishing coordinates.
					coords[currentRow][i] = true;
					finishCoords.add(new Coords(i, currentRow));
				}
			}
			currentRow++; //Increases the row counter after we have read the line.
		}

		while(!jumpCreation.isEmpty()){
			
			Jump temp = jumpCreation.dequeue(); //Removes the oldest jump from the queue.
			
			if(!jumpMap.containsKey(temp)){ //If a jump does not exist in the map, then we add it and calculate it's possible jumps. 
				
				jumpMap.put(temp, graphID++); //Adds the jump to the map using the graphID then increasing the graph ID.
				
				for(int i = -1; i < 2; i++){
					
					for(int j = -1; j < 2; j++){

						//Calculate the new speed.
						int xNewSpeed = temp.xSpeed + j;
						int yNewSpeed = temp.ySpeed - i;

						//Calculate the new position.
						int xNewPos = temp.position.x + xNewSpeed; 
						int yNewPos = temp.position.y + yNewSpeed;

						//Checks if new position is within bounds of the coordinates.
						if(xNewPos < 0 || xNewPos > xLength - 1 || yNewPos < 0 || yNewPos > yLength - 1){
							continue; //If it is not, then we continue and check with a new speed.
						}

						//If Mario cannot jump to his new position, the jump is discarded.
						if(coords[yNewPos][xNewPos] == false){
							continue;
						}

						//Creates the new jump.
						Jump newJump = new Jump(xNewPos, yNewPos, xNewSpeed, yNewSpeed); 

						//If the coordinates of the new jump is one of the finish spots, then we add it to the set, so we can use it for later.
						if(finishCoords.contains(newJump.position)){
							finalJumps.add(newJump);
						}
						
						jumpCreation.enqueue(newJump); //Adds the new jump to the queue.
					}
				}
			}
		}

		Digraph g = new Digraph(jumpMap.size()); //Directed graph used for our BreadthFirstSearch.

		//Test all possible combinations of jumps.
		for(Jump n : jumpMap.keySet()){
			for(Jump m : jumpMap.keySet()){
				
				if((n.position.x + m.xSpeed == m.position.x) && (n.position.y + m.ySpeed == m.position.y)){
					// Check if n can reach the position of m.
					
					int xSpeed = Math.abs(n.xSpeed - m.xSpeed);
					int ySpeed = Math.abs(n.ySpeed - m.ySpeed);

					if((xSpeed == 1 || xSpeed == 0) && (ySpeed == 1 || ySpeed == 0)){
						//Check if their speed is close enough.
						g.addEdge(jumpMap.get(n), jumpMap.get(m));
					}
				}
			}
		}

		int shortPath = Integer.MAX_VALUE; //Used to find the shortest possible path.
		
		for(Jump s : startJumps){ //Checks all the start jumps.
			BFSDP search = new BFSDP(g, jumpMap.get(s));
			for(Jump f : finalJumps){ //Checks for the shortest way to all the final jumps.
				if(search.distTo(jumpMap.get(f)) < shortPath){
					shortPath = search.distTo(jumpMap.get(f));
				}
			}	
		}
		System.out.println(shortPath + 1); //The amount is incremented by one, because the "start" itself is one step.
	}

	/**
	 * A jump that contains coordinates and speed on the two axis.
	 * @author Jakob Melnyk & Frederik Roden Lysgaard
	 *
	 */
	private static class Jump{
		public int xSpeed;
		public int ySpeed;
		public Coords position;	

		/**
		 * A node that contains coordinates and speed on the two axis.
		 * @param x x-position
		 * @param y y-position
		 * @param xS speed in x-direction
		 * @param yS speed in y-direction
		 */
		public Jump(int x, int y, int xS, int yS){
			xSpeed = xS;
			ySpeed = yS;
			position = new Coords(x, y);
		}

		/*'
		 * (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object o){
			if(this.getClass() != o.getClass())return false;
			Jump temp = (Jump) o;
			return this.xSpeed == temp.xSpeed && this.ySpeed == temp.ySpeed && this.position.equals(temp.position);	
		}

		/*'
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString(){
			return "xS: " + xSpeed + ", yS: " + ySpeed + ", " + position;
		}

		/*'
		 * (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		public int hashCode(){
			return xSpeed * 13 + ySpeed * 13 + position.x * 13 + position.y * 13;
		}
	}

	/**
	 * A simple coordinate object.
	 * @author Jakob Melnyk & Frederik Roden Lysgaard
	 *
	 */
	private static class Coords{
		public final int x;
		public final int y;

		/**
		 * A simple coordinate object.
		 * @param x X-coordinate.
		 * @param y Y-coordinate.
		 */
		public Coords(int x, int y){
			this.x = x;
			this.y = y;
		}

		/*'
		 * (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object o){
			if(this.getClass() != o.getClass())return false;
			Coords temp = (Coords) o;
			return this.x == temp.x && this.y == temp.y;

		}

		/*'
		 * (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		public int hashCode(){
			return x * 13 + y * 13;
		}

		/*'
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString(){
			return "Coords: " + x + ", " + y;
		}
	}

	//Classes below are borrowed from http://algs4.cs.princeton.edu/code/

	private static class Bag<Item> implements Iterable<Item> {
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

	private static class Queue<Item> implements Iterable<Item> {
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
		 * Return the number of items in the queue.
		 */
		public int size() {
			return N;     
		}

		/**
		 * Return the item least recently added to the queue.
		 * Throw an exception if the queue is empty.
		 */
		public Item peek() {
			if (isEmpty()) throw new RuntimeException("Queue underflow");
			return first.item;
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
		 * Return string representation.
		 */
		public String toString() {
			StringBuilder s = new StringBuilder();
			for (Item item : this)
				s.append(item + " ");
			return s.toString();
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

	private static class Digraph {
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

		/**
		 * Return the reverse of the digraph.
		 */
		public Digraph reverse() {
			Digraph R = new Digraph(V);
			for (int v = 0; v < V; v++) {
				for (int w : adj(v)) {
					R.addEdge(w, v);
				}
			}
			return R;
		}

		/**
		 * Return a string representation of the digraph.
		 */
		public String toString() {
			StringBuilder s = new StringBuilder();
			String NEWLINE = System.getProperty("line.separator");
			s.append(V + " " + E + NEWLINE);
			for (int v = 0; v < V; v++) {
				s.append(v + ": ");
				for (int w : adj[v]) {
					s.append(w + " ");
				}
				s.append(NEWLINE);
			}
			return s.toString();
		}
	}

	private static class Stack<Item> implements Iterable<Item> {
		private int N;          // size of the stack
		private Node first;     // top of stack

		// helper linked list class
		private class Node {
			private Item item;
			private Node next;
		}

		/**
		 * Create an empty stack.
		 */
		public Stack() {
			first = null;
			N = 0;
		}

		/**
		 * Is the stack empty?
		 */
		public boolean isEmpty() {
			return first == null;
		}

		/**
		 * Return the number of items in the stack.
		 */
		public int size() {
			return N;
		}

		/**
		 * Add the item to the stack.
		 */
		public void push(Item item) {
			Node oldfirst = first;
			first = new Node();
			first.item = item;
			first.next = oldfirst;
			N++;
		}

		/**
		 * Delete and return the item most recently added to the stack.
		 * Throw an exception if no such item exists because the stack is empty.
		 */
		public Item pop() {
			if (isEmpty()) throw new RuntimeException("Stack underflow");
			Item item = first.item;        // save item to return
			first = first.next;            // delete first node
			N--;
			return item;                   // return the saved item
		}


		/**
		 * Return the item most recently added to the stack.
		 * Throw an exception if no such item exists because the stack is empty.
		 */
		public Item peek() {
			if (isEmpty()) throw new RuntimeException("Stack underflow");
			return first.item;
		}

		/**
		 * Return string representation.
		 */
		public String toString() {
			StringBuilder s = new StringBuilder();
			for (Item item : this)
				s.append(item + " ");
			return s.toString();
		}


		/**
		 * Return an iterator to the stack that iterates through the items in LIFO order.
		 */
		public Iterator<Item> iterator()  { return new LIFOIterator();  }

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

	private static class BFSDP {
		private static final int INFINITY = Integer.MAX_VALUE;
		private boolean[] marked;  // marked[v] = is there an s->v path?
		private int[] edgeTo;      // edgeTo[v] = last edge on shortest s->v path
		private int[] distTo;      // distTo[v] = length of shortest s->v path
		private final int s;       // the source

		public BFSDP(Digraph G, int s) {
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

		// return shortest path from s to v; null if no such path
		public Iterable<Integer> pathTo(int v) {
			if (!hasPathTo(v)) return null;
			Stack<Integer> path = new Stack<Integer>();
			for (int x = v; x != s; x = edgeTo[x])
				path.push(x);
			path.push(s);
			return path;
		}
	}

}