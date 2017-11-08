import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of MyList with an array (a longer exercise would be to
 * implement the List interface as is done in the class java.util.ArrayList: the
 * source of the ArrayList class is available from Sun. Check it out).
 */

public class MyArrayList<E> implements MyList<E> {

	// Use an array for the implementation
	private E[] items; 

	// Default capacity of the array
	private static final int DEFAULT_CAPACITY = 10;

	// Number of elements in the array
	private int size;

	/**
	 * Constructs a MyArrayList with a specified capacity
	 */
	public MyArrayList(int initialCapacity) {
		items = (E[]) new Object[initialCapacity];
	}

	/**
	 * Constructs a MyArrayList with a default capacity
	 */
	public MyArrayList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Returns the number of elements in this list.
	 */
	public int size() {
		return this.size;
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
		// If there is no room in the array items
		// Make room for the new element
		if (size == items.length) {
			E[] temp = (E[]) new Object[items.length + DEFAULT_CAPACITY];
			// make the copy by hand
			for (int i = 0; i < items.length; i++) {
				temp[i] = items[i];
			}
			items = temp;
			
			// or use a library method of Java
			Arrays.copyOf(items, items.length + DEFAULT_CAPACITY);
			// other example
			System.arraycopy(items, 0, temp, 0, items.length);
			
		}
		// add the new element
		items[this.size] = o;
		size ++;
		return true; // must return something because Collections.
	}

	/**
	 * Empties this List
	 */
	public void clear() {
		size = 0;
		// running GC is optional
		items = (E[]) new Object[DEFAULT_CAPACITY];
	}

	/**
	 * Returns the element at the specified position in this list.
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index = " + index);
		}
		return items[index];
	}

	/**
	 * Returns the index of the specified element (-1 if there is no match)
	 */
	public int indexOf(Object o) {

		// If o is null (look for a null element in the array)
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (o == items[i]) {
					return i;
				}
			}
		} else // o is an object (use equals)
		{
			for (int i = 0; i < size; i++) {
				if (o.equals(items[i])) {
					return i;
				}
			}
		}

		// If we get here, o is not in the list
		return -1;
	}

	/**
	 * Returns true if this list contains the specified element.
	 */
	public boolean contains(Object o) {
		// easy with indexOf
		return indexOf(o) != -1;
	}

	/**
	 * Removes the element in the List at position index
	 */
	public boolean remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index = " + index);
		}
		for (int i = index; i <= size - 2; i++) {
			items[i] = items[i + 1];
		}
		size--;
		return true;

		// compact the array

		// let's gc do its work

	}

	/**
	 * Removes the element in the List at position index
	 */
	public boolean remove(Object o) {
		// easy with indexOf and remove
		// if completely ternary, you'd need to do indexOf twice...
		int index = indexOf(o);
		return index != -1 ? remove(index) : false;
	}

	/**
	 * Adds the specified object at the specified location
	 */
	public boolean add(int index, E o) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index = " + index);
		}
		// one way: add at the end and then shift the elements around
		add(o); // to use the code that makes the array bigger if needed
		for (int i = size - 1; i > index + 1 ; i--) {
			items[i] = items[i - 1];
		}
		items[index] = o;
		return true;
	}

	/**
	 * Is this List equal to the specified object?
	 */
public boolean equals(Object o)
    {
        if (o instanceof MyArrayList) {
            // o is an ArrayList
        		MyArrayList<E> list = (MyArrayList<E>) o;
            // if the number of elements is not the same, this and o are not the
			// same
        		if (list.size != size) {
        			return false;
        		}
        		
//        		boolean b = true;
//        		for (int i = 0; i < size && b; i++) {
//        			if (items[i] == null) {
//        				b &= (list.items[i] == null);
//        			} else {
//        				b &= (items[i].equals(list.items[i]));
//        			}
//        		}
//        		return b;
        		
            // Check the elements one by one
        		for (int i = 0; i < size; i++) {
        			if (items[i] == null) {
        				if (list.items[i] != null) {
        					return false;
        				}
        			} else {
        				if (!items[i].equals(list.items[i])) {
        					return false;
        				}
        			}
        		}

            // At this point, the lists are equal
        		return true;
        }
		else {
			return false;
	    }
	}
	/**
	 * An inner class to define the iterator
	 */
	private class MyIterator implements Iterator<E> {

		//index of the next element to visit
		private int index = 0;
		
		private boolean nextCalled;
		
		
		/**
		 * Create an iterator for a MyArrayList
		 */
		public MyIterator() {
			
		}

		/**
		 * Any element left in the list?
		 */
		public boolean hasNext() {
			return index < size;
		}

		/**
		 * Returns the current element in the list and move to the next element
		 */
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			nextCalled = true;
			index++;
			int lastIndex = index - 1;
			return items[lastIndex];
		}

		/**
		 * Removes the last object returned by next
		 */
		public void remove() {
			if (!nextCalled) {
				throw new IllegalStateException();
			}
		}
		
		private void m() {
			
		}
	}

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * 
	 * @return an iterator over the elements in this list in proper sequence.
	 */
	public Iterator<E> iterator() {
		MyIterator it = new MyIterator();
		it.m();
		return it;
	}
}
