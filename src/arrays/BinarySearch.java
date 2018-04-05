package arrays;


import java.util.Arrays;
import java.util.Scanner;

/*
Given a sorted array of integers, return the index of the given key. Return -1 if not found.
 */
public class BinarySearch {

    public static void main(String[] args) {

        int[] input = {10, 20, 30, 40, 50, 60, 71, 80, 90, 91};

        System.out.println("Enter number to search from " + Arrays.toString(input));
        Scanner s = new Scanner(System.in);
        int key = Integer.parseInt(s.nextLine());

        //solution1 time = O(log n ) memory = O(log n)
        System.out.println("Using Recursion = " + binary_search_rec(input, key, 0, input.length - 1));

        //solution2 time = O(log n ) memory = O (1)
        System.out.println("Using Iterative = " + binary_search_iterative(input, key,input.length ));

    }

    static int binary_search_rec(int[] a, int key, int low, int high) {

        if (a.length == 0 | low > high) {
            return -1;
        }

        int mid = low + ((high - low) / 2);

        if (a[mid] == key) {
            return mid;
        } else if (key < a[mid]) {
            return binary_search_rec(a, key, low, mid - 1);
        } else {
            return binary_search_rec(a, key, mid + 1, high);
        }
    }

    static int binary_search_iterative(int[] a, int key, int len) {

        if (len == 0 ) {
            return -1;
        }

        int low = 0;
        int high = len - 1;

       while (low <= high) {

           int mid = low + ((high - low) / 2);

           if(a[mid] == key) {
               return mid;
           } else if(key < a[mid]) {
               high = mid -1;
           } else {
               low = mid + 1;
           }
       }

       return -1;
    }
}
