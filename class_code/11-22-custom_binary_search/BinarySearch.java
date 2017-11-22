
public class BinarySearch {
	public static void main(String[] args) {
		int[] numbers = {-1, 0, 5, 11, 18, 29, 53, 57, 61, 64, 78, 82, 85, 89, 93};
		System.out.println(binarySearchWithLoop(numbers, 86));
		System.out.println(binarySearchRecursive(numbers, 86, 0, numbers.length - 1));
	}

	public static int binarySearchWithLoop(int[] a, int value) {
		// With a loop
		int first = 0, last = a.length - 1;
		while(first <= last) {
			int mid = (first + last) / 2;
			System.out.println("mid = " + mid );
			//compare a[mid] with value
			if (a[mid] < value) {
				first = mid + 1;
			} else if (a[mid] > value) {
				last = mid - 1;
			} else {
				return mid;
			}
		}
		return -(first) - 1; // -insertion point - 1
	}
	public static int binarySearchRecursive(int[] a, int value, int first, int last) {
		if (first > last) {
			return -first -1;
		} else {
			int mid = (first + last) / 2;
			System.out.println("mid = " + mid );
			if (a[mid] < value) {
				return binarySearchRecursive(a, value, mid + 1, last);
			} else if (a[mid] > value) {
				return binarySearchRecursive(a, value, first, mid - 1);
			} else {
				return mid;
			}
		}
	}

}
