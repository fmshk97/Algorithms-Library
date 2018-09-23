class SegmentTree {
	private final int MAX_TREE = 0;		// To indicate a Segment Tree is for max values in a range
	private final int MIN_TREE = 1;		// To indicate a Segment Tree is for min values in a range
	private final int SUM_TREE = 2;		// To indicate a Segment Tree is for sum of values in a range
	
	private long[] input, tree;
	private int input_size, choiceCode;
	
	public SegmentTree(long[] input) {		// Constructor
		this.input = input;
		input_size = input.length;
		tree = new long[(2 * nextPowerOf2(input_size)) - 1];
	}
	
	public void createMaxSegmentTree() {		// Create Segment Tree to find range max value
		choiceCode = MAX_TREE;		
		createSegmentTree(0, input_size - 1, 0);
	}
	
	public void createMinSegmentTree() {		// Create Segment Tree to find range min value
		choiceCode = MIN_TREE;
		createSegmentTree(0, input_size - 1, 0);
	}
	
	public void createSumSegmentTree() {		// Create Segment Tree to find range sum value
		choiceCode = SUM_TREE;
		createSegmentTree(0, input_size - 1, 0);
	}
	
	public long getRangeResult(int left, int right) {		// To get the desired value in specified range (left and right inclusive)
		return getRangeResult(tree, 0, input_size - 1, left, right, 0);
	}
	
	public void update(long value, int index) {
		long difference = value - input[index];
		input[index] = value;
		update(0, input_size - 1, 0, index, difference);
	}
	
	private void update(int low, int high, int root, int index, long difference) {
		if (high == low) {
			tree[root] = input[low];
			return;
		}
		int mid = (low + high) / 2;
		if (index <= mid) {
			update(low, mid, 2 * root + 1, index, difference);
		}
		else {
			update(mid + 1, high, 2 * root + 2, index, difference);
		}
	
		if (choiceCode == MAX_TREE) {
			tree[root] = Math.max(tree[2 * root + 1], tree[2 * root + 2]);
		}
		else if (choiceCode == MIN_TREE) {
			tree[root] = Math.min(tree[2 * root + 1], tree[2 * root + 2]);
		}
		else {
			tree[root] += difference;
		}
	}
	
	private void createSegmentTree(int low, int high, int root) {		// Create Segment Tree
		if (low == high) {
			tree[root] = input[low];
			return;
		}
		int mid = (high + low) / 2; 
		createSegmentTree(low, mid, 2 * root + 1);
		createSegmentTree(mid + 1, high, 2 * root + 2);
		
		if (choiceCode == MAX_TREE) {
			tree[root] = Math.max(tree[2 * root + 1], tree[2 * root + 2]);
		}
		else if (choiceCode == MIN_TREE) {
			tree[root] = Math.min(tree[2 * root + 1], tree[2 * root + 2]);
		}
		else {
			tree[root] = tree[2 * root + 1] + tree[2 * root + 2];
		}
	}
	
	private long getRangeResult(long[] tree, int low, int high, int l, int r, int root) {		// Helper method for getRangeResult()
		if (low >= l && high <= r) {		// Total Overlap
			return tree[root];
		}
		if (high < l || low > r) {			// No Overlap
			if (choiceCode == MIN_TREE) {
				return Long.MAX_VALUE;
			}
			return 0;
		}
		int mid = (high + low) / 2;
		
		// Partial Overlap
		if (choiceCode == MAX_TREE) {
			return Math.max(getRangeResult(tree, low, mid, l, r, 2 * root + 1), getRangeResult(tree, mid + 1, high, l, r, 2 * root + 2));
		}
		else if (choiceCode == MIN_TREE) {
			return Math.min(getRangeResult(tree, low, mid, l, r, 2 * root + 1), getRangeResult(tree, mid + 1, high, l, r, 2 * root + 2));
		}
		else {
			return getRangeResult(tree, low, mid, l, r, 2 * root + 1) + getRangeResult(tree, mid + 1, high, l, r, 2 * root + 2);
		}
	}
	
	private int nextPowerOf2(int n) {		// For calculating value greater than or equal to n which is power of 2
		if ((n & (n - 1)) == 0)		// If n already power of 2
			return n;
		int count = 0;
		while ((n & ~1) != 0) {
			n >>= 1;
			count++;
		}
		return (1 << (count + 1));
	}
}
