/*
 * RandomizedQueue - implemented using resizable array
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue <Item> implements Iterable<Item>{
	private int size;
	private Item[] rq;
  
	// construct an empty randomized queue
	public RandomizedQueue () {		
		rq = (Item[]) new Object[1];
		size = 0;
	}
  
	public boolean isEmpty() {
		return size == 0;
	}
  
	public int size() {
		return size;
	}
  
	/* private method resize (),
	 * double the size of array when the array is full, and copy the elements in original array 
	 * into the new one
	 */
	private void resize (int capacity) {
		assert capacity >= size;
		Item [] temp = (Item []) new Object[capacity];
		for(int i = 0; i < size; i++) {
			temp[i] = rq[i];
		}
		rq = temp;
	}
	
	public void enqueue (Item item) {
		if (item == null)
			throw new NullPointerException ("add a null item");
		if(size == rq.length) {
			resize(2*rq.length);
		}
		rq[size++] = item;
	}
  
	public Item dequeue () {
		if (isEmpty())
			throw new NoSuchElementException ("No element");
		int n = StdRandom.uniform(size);
		Item item = rq[n];
		/*if n is the last index, then make the last element null; else fill the removed
		 * index with the last element, then make the last element null, size--; 
		 */
		if(n == (size - 1)) {
			rq[--size] = null;			
		}else {
			rq[n] = rq[--size];
			rq[size] = null;
		}
		/*
		 * check the size of the array, if less than 1/4 of array length, shrink array to halve size
		 */
		if(size <= rq.length/4 && size > 0)
			resize(rq.length/2);
		
		return item;
	}
  
	// return but not remove a random item
	public Item sample () {
		if (isEmpty())
			throw new NoSuchElementException("No element");
		
		int index = StdRandom.uniform(size);
		return rq[index];
	}
  
	public Iterator<Item> iterator() {
		return new RandomizedArrayIterator (rq);
	}
	
	private class RandomizedArrayIterator implements Iterator<Item> {
		private Item[] copyArr;
		private int copySize = size;
		
		public RandomizedArrayIterator (Item [] arr) {
			copyArr = (Item[]) new Object[arr.length];
			for (int i = 0; i < arr.length; i++) {
				copyArr[i] = arr[i];
			}
		}
    
		public boolean hasNext() {
			return copySize != 0;
		}
    
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			int n = StdRandom.uniform(copySize);
			Item item = copyArr[n];			
			if (n == (copySize - 1)) {
				copyArr[--copySize] = null;			
			} else {
				copyArr[n] = copyArr[--copySize];
				copyArr[copySize] = null;
			}			
			return item;			
		}
    
		public void remove() {
			throw new UnsupportedOperationException();
		}		
	}

}
