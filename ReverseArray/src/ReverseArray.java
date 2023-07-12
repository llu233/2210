
// Importing Essential Libraries
import java.util.Arrays;

/**
 * Java Program to reverse array in place.
 * input: array of strings
 * output: the same array after reversed it back
 * description: This Java program aims to show various algorithms that solve the same problem
 * while highlighting their varying time complexity.
 * purpose: demonstrate how algorithmic choices affect performance in practice.
 */
public class ReverseArray {

    public static void main(String args[]){

        String[] arrOfNames = {"Dave", "Caro", "Mike", "Ahmed"};
        System.out.println("original array: " + Arrays.toString(arrOfNames) );

        // reversing array with three elements
        reverseWizOneLoop(arrOfNames);
        System.out.println("reversed array: " + Arrays.toString(arrOfNames) );

        // reversing array with three elements
        reverseWizTwoLoops(arrOfNames);
        System.out.println("original array back: " + Arrays.toString(arrOfNames) );
    }

    /**
     * Java Method to reverse String array in place
     * Time complexity is O(n)
     */
    public static void reverseWizOneLoop(String[] array) {

        // check if the array is empty of have only one element
        if (array == null || array.length < 2) {
            return;
        }

        // do the reverse
        for (int i = 0; i < array.length / 2; i++) {
            String temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }

    }

    /**
     * Java Method to reverse String array in place
     * Time complexity is O(n2)
     */
    public static void reverseWizTwoLoops(String[] arr) {

        // check if the array is empty of have only one element
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length/2; i++) {
            for (int j = arr.length-1; j > arr.length/2; j--) {
                if (i<j) {
                    String temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


}