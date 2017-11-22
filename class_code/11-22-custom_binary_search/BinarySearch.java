
public class BinarySearch {

	public static int binarySearch(int[] a, int value) {
		// With a loop
		int first = 0, last = a.length - 1;
		while(first <= last) {
			int mid = (first + last) / 2;
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

}
