/**
 * Dequeue. A double-ended queue or deque (pronounced "deck") is a generalization of a stack 
 * and a queue that supports adding and removing items from either the front or the back of 
 * the data structure. implemented with doubly linked list.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first, end;
	private int numOfNode;
	
	private class Node {
		private Item item;
		private Node next, pre;
	
	}
	public Deque () {
		first = null;
		end = null;
		numOfNode = 0;		
	}
	public boolean isEmpty () {
		return numOfNode == 0;
	}
	
	public int size () {
		return numOfNode;
	}
	
	public void addFirst (Item item) {
		if (item == null) 
			throw new NullPointerException();
		Node node = new Node();
		node.item = item;
		
		if (isEmpty()) {
			first = node;
			end = node;
		} else {
			node.next = first;
			first.pre = node;
			first = node;
		}
		numOfNode++;
	}
	public void addLast (Item item) {
		if (item == null) 
			throw new NullPointerException();
		Node node = new Node ();
		node.item = item;
		if (isEmpty()) {
			first = node;
			end = node;
		} else {
			end.next = node;
			node.pre = end;
			end = node;
		}
		numOfNode++;
	}
	public Item removeFirst () {
		if (isEmpty()) throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		if (first == null) {
			end = null;
		} else {
			first.pre = null;
		}
		numOfNode--;
		return item;		
	}
	public Item removeLast () {
		if (isEmpty()) 
			throw new NoSuchElementException();
		Item item = end.item; // save returned item
		end = end.pre;
		if (end == null) 
			first = null;
		else
			end.next = null;	
		numOfNode--;
		return item;		
	}
	
	public Iterator<Item> iterator () {
		return new ListIterator(first);
	}
	private class ListIterator implements Iterator<Item> {
		private Node node;
		public ListIterator (Node first) {
			node = first;
		}
		@Override
		public boolean hasNext () {
			return node != null;
		}

		@Override
		public Item next () {
			if(!hasNext()) throw new NoSuchElementException("no such element");
			Item item = node.item;
			node = node.next;
			return item;
		}
		public void remove () {
			throw new UnsupportedOperationException();
		}		
	}
}
