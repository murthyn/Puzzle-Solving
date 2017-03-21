interface Fringe{
	
        // removes all elements from the Fringe
        public void clear();
      
        // returns true if the Fringe has no elements
        public boolean isEmpty();
      
        // adds a new node to the Fringe
        public void insert(SearchNode node);
      
        // removes and returns the first node in the Fringe
        public SearchNode removeFirst();
}