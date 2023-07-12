
public class BinarySearch {
    public static void main(String[] args) {

        // Get init time
        long Xmillis = System.currentTimeMillis();
        long Xseconds = Xmillis / 1000;


        // The sorted array to search through
        int[] array = {1, 2, 3, 4, 5};

        // The value to search for
        int searchValue = 4;

        // Perform the binary search
        int index = binarySearch(array, searchValue);

        // Print the result
        if (index == -1) {
            System.out.println("Value not found in array");
        } else {
            System.out.println("Value found at index " + index);
        }

        // Get end time
        long Ymillis = System.currentTimeMillis();
        long Yseconds = Ymillis / 1000;
        System.out.println("Running Time: "+ (Ymillis-Xmillis)+ "ms!");

    }

    public static int binarySearch(int[] array, int searchValue) {
        // Set the left and right indices
        int left = 0;
        int right = array.length - 1;

        // Perform the binary search
        while (left <= right) {
            // Calculate the middle index
            int middle = (left + right) / 2;

            // If the value is found at the middle, return the index
            if (array[middle] == searchValue) {
                return middle;
            }

            // If the search value is less than the value at the middle, search the left half of the array
            if (searchValue < array[middle]) {
                right = middle - 1;
            } else {
                // Otherwise, search the right half of the array
                left = middle + 1;
            }
        }

        // If the value is not found, return -1
        return -1;
    }
}
