import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of MyList with a linked list (a longer exercise would be to
 * implement the List interface as is done in the class java.util.LinkedList:
 * the source of the LinkedList class is available from Sun. Check it out).
 */

public class MyLinkedList<E> implements MyList<E> {

	// A private class to represent a Node in the linked list
	private class Node {
		public E item;

		public Node next;

		// a convenient constructor
		public Node(E o) {
			this.item = o;
			this.next = null;
		}
	}

	// The start of the linked list
	private Node head;

	// The last Node in the linked list
	private Node tail;

	// Number of elements in the list
	private int size;

	/**
	 * Creates an empty list (this constructor is not necessary)
	 */
	public MyLinkedList() {
	}

	/**
	 * Returns the number of elements in this list.
	 */
	public int size() {
		// without a size field
		// int count = 0;
		// for (Node p = head; p != null; p = p.next) {
		// count++;
		// }
		// return count;
		return size;
	}

	/**
	 * Returns true if this list contains no elements.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Appends the specified element to the end of this list
	 */
	public boolean add(E o) {
		// If this is the first element in the list
		Node n = new Node(o);
		if (isEmpty()) {
			head = tail = n;
		} else {
			// If the list is not empty, use tail
			tail.next = n;
		}
		// update tail
		tail = n;
		// update size
		size++;
	}

	/**
	 * Empties this List
	 */
	public void clear() {
		// update head, tail and size
		size = 0;
		head = tail = null;
	}

	/**
	 * Returns the element at the specified position in this list.
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index = " + index);
		}
		// Find it
		Node p = head;
		for (int i = 1; i <= index; i++) {
			p = p.next;
		}
		return p.item;
	}

	/**
	 * Returns the index of the specified element (-1 if there is no match)
	 */
	public int indexOf(Object o) {
		Node p = head;
		for (int i = 0; i < size; i++) {
			// If o is null
			if (o == null) // look for a null element in the list
			{
				if (p.item == null) {
					return i;
				}
			} else { // o is an object (use equals)
				if (o.equals(p.item)) {
					return i;
				}
			}
			p = p.next;
		}

		// if we get here, o is not in the list
		return -1;
	}

	/**
	 * Returns true if this list contains the specified element.
	 */
	public boolean contains(Object o) {
		// easy with indexOf
		int index = indexOf(o);
		return index != -1;
	}

	/**
	 * Removes the element in the List at position index
	 */
	public boolean remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index = " + index);
		}
		// Find the corresponding node
		Node prev = null, p = head;
		for (int i = 1; i <= index; i++) {
			prev = p;
			p = p.next;
		}
		// Remove it
		// Special case for the first node
		if (index == 0) {
			head = head.next;
		} else {
			prev.next = p.next;
		}
		// If the last node has been removed, update tail
		if (p == tail) {
			tail = prev;
		}
		// update size
		size--;
		return true;
	}

	/**
	 * Removes the element in the List at position index
	 */
	public boolean remove(Object o) {
		// easy with indexOf and remove (but expensive)
		int index = indexOf(o);
		if (index != -1) {
			return remove(index);
		} else {
			return false;
		}
	}

	/**
	 * Adds the specified object at the specified location
	 */
	public boolean add(int index, E o) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index = " + index);
		}
		// Find the corresponding node
		Node prev = null, p = head;
		for (int i = 1; i <= index; i++) {
			prev = p;
			p = p.next;
		}
		// Add a node between prev and p
		Node n = new Node(o);
		n.next = p;
		if (prev != null) {
			prev.next = n;
		} else {
			// Special case (first node)
			head = n;
		}
		// Update tail if necessary
		if (index == size) {
			tail = n;
		}
		// Update size
		size++;
		return true;
	}

	/**
	 * Is this List equal to the specified object?
	 */
	public boolean equals(Object o) {
		if (o instanceof MyLinkedList) {
			// o is a MyLinkedList
			MyLinkedList<E> list = (MyLinkedList<E>) o;

			// if the number of elements is not the same, this and o are not the
			// same
			if (list.size != this.size) {
				return false;
			}

			// Check the elements one by one
			for (Node p = head, q = list.head; p != null; p = p.next, q = q.next) {
				if (p.item == null) { // why not just if (p.item == null && q.item != null) {return false;}
					if (q.item != null) {
						return false;
					}
				} else {
					if (!p.item.equals(q.item)) { // caller can't be null!
						return false;
					}
				}
			}

			// At this point, the lists are equal

		} else {
			return false;
		}
	}

	/**
	 * An inner class to define the iterator
	 */
	private class MyIterator implements Iterator<E> {
		Node p = head;

		/**
		 * Create an iterator for a MyLinkedList
		 */
		public MyIterator(MyLinkedList<E> list) {

		}

		/**
		 * Any element left in the list?
		 */
		public boolean hasNext() {
			return p != null;

		}

		/**
		 * Return the current element in the list and move to the next element
		 */
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E item = p.item;
			p = p.next;
			return item;
		}

		/**
		 * Remove the object currently pointed at by the iterator
		 */
		public void remove() {
		}
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * 
	 * @return an iterator over the elements in this list in proper sequence.
	 */
	public Iterator<E> iterator() {
		return new MyIterator();
	}
}
