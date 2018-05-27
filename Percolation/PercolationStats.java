import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
	private double[] percThresholds;
	private int t;
  
	public PercolationStats(int n, int trials) {
		if(n <= 0 || trials <= 0)
			throw new IllegalArgumentException("n and trials should not be less than 0");
      
		this.t = trials;
		percThresholds = new double[t];
		
		for(int i=0; i<t; i++){
			int opensites = 0;
			Percolation p = new Percolation(n);
			while(!p.percolates()){
				int row = StdRandom.uniform(n)+1;
				int col = StdRandom.uniform(n)+1;
				if(!p.isOpen(row, col)){
					p.open(row, col);
					opensites++;
				}
			}
			percThresholds[i] = opensites / (double) (n*n);
		}
		
	}
  
	public double mean() {
		return StdStats.mean(percThresholds);
	}
	
	public double stddev() {
		return StdStats.stddev(percThresholds);		
	}
  
	public double confidenceLo() {
		return mean() - (1.96*(stddev()/Math.sqrt(t)));
	}
  
	public double confidenceHi() {
		return mean() + (1.96*(stddev() / Math.sqrt(t)));
	}

	public static void main(String[] args) {
  
		int size;  // Grid size
        int trials;  // Number of trials

        if (args.length != 2) {
            StdOut.println("Usage: java PercolationStats <size>, <trials>");
            return;
        }

        size = Integer.parseInt(args[0]);
        trials = Integer.parseInt(args[1]);

        // Perform T experiments for N-by-N grid
        PercolationStats percStats = new PercolationStats(size, trials);

        // print statistics and confidence interval
        StdOut.println("mean                    = " + percStats.mean());
        StdOut.println("stddev                  = " + percStats.stddev());
        StdOut.println("95% confidence interval = " + percStats.confidenceLo()
                + ", " + percStats.confidenceHi());
        //double time1 = timer1.elapsedTime();
        //StdOut.print(time1);
	}

}
