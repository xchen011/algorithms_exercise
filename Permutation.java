/**
 * a client program Permutation.java that takes a command-line integer k; reads in a sequence of strings from 
 * standard input using StdIn.readString(); and prints exactly k of them, uniformly at random. Print each item 
 * from the sequence at most once. You may assume that 0 ≤ k ≤ n, where n is the number of string on standard input.
 */
 
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;


public class Permutation {

	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<>();

		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			rq.enqueue(s);
		}
    
		for (int i = 0; i < k; i++) {
			String s = rq.dequeue();
			StdOut.println(s);
		}
    
	}
}
