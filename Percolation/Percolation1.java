import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private boolean [][] grid;  //2D  indicates block, true indicates open
  private WeightedQuickUnionUF uf;
	private int numOfOpenSites;
	private int size;
	private int top;
	private int bottom;
		
//create N*N boolean array, each item was initialized as false, indicating block   
public Percolation(int n) {
		if (n <= 0) throw new IllegalArgumentException(n + "is illegal argument");
		this.size = n;
		grid = new boolean[size][size];		
		uf = new WeightedQuickUnionUF(size*size+2);
		top = 0;
		bottom = size*size+1;
	}
	
	public void open(int row, int col) {
		validate(row, col);
		int index = toIndexUF(row, col);
		grid[row-1][col-1] = true;
		
		if (row == 1) uf.union(index,top);
		if (row == size) uf.union(index,bottom);
		if (row > 1 && isOpen(row-1, col)) uf.union(index, toIndexUF(row-1, col));
		if (row < size && isOpen(row+1, col)) uf.union(index, toIndexUF(row+1, col));
		if (col > 1 && isOpen(row, col-1)) uf.union(index, toIndexUF(row, col-1));
		if (col < size && isOpen(row, col+1)) uf.union(index, toIndexUF(row, col+1));
		
		numOfOpenSites++;
	}
	public boolean isOpen(int row, int col) {
		validate(row, col);
		return grid[row-1][col-1];
	}
	
	public boolean isFull(int row, int col) {
		validate(row, col);
		
		return uf.connected(top, toIndexUF(row, col));
	}
	
	public int numberOfOpenSites() {
		return numOfOpenSites;
	}
	public boolean percolates() {
		return uf.connected(top, bottom);
	}
	
	private void validate(int row,int col) {
		if(row <= 0 || row > size || col <= 0 || col > size) {
			throw new IndexOutOfBoundsException(row + " " + col + " out of bounds");
		}
	}
	
	private int toIndexUF(int row, int col) {
		return size * (row-1) + col;
	}
	
}
