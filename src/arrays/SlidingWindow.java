package arrays;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingWindow {


    public static void main(String[] args) {
        int[] input = {1, -4, 2, -5, 3, 6};

        System.out.println("Result" + Arrays.toString(maxSlidingWindow(input, 3)));
    }

    //A simple solution to this problem would be to find maximum by scanning all elements
    // within the window w every time it slides right.
    // This approach has complexity O(nw) - Run two loops.
    // In the outer loop, take all subarrays of size k.
    // In the inner loop, get the maximum of the current subarray.


    //A slightly better solution will be to use Heap of window size w.
    // It will help us find the max quickly. One thing to note is that every time the window moves;
    // we will need to delete one of the existing elements from the heap that is not in the window anymore,
    // and we will need to add a new element.
    // Both of these operations are O(log w) operations.
    // The total runtime complexity for this approach will be O(n log w).

    //We can reduce the complexity of the previous solution by using a doubly linked list (or a dequeue)
    // where we can insert/delete at both ends.

    public static int[] maxSlidingWindow(int[] array, int window_size) {
        if(array == null || array.length < window_size || array.length <=0) {
            return array;
        }

        ArrayDeque<Integer> window = new ArrayDeque<Integer>();
        List<Integer> result = new ArrayList<>();

        //find out max for the first window
        for(int i=0 ; i<window_size ; i++) {

            while(!window.isEmpty()
                    && array[i] >= array[window.peekLast()] ) {
                window.removeLast();
            }

            window.addLast(i);
        }

        result.add(array[window.peekFirst()]);

        for(int i= window_size; i<array.length; i++) {

            //remove all numbers that are smaller than current number
            //from the tail of queue
            while(!window.isEmpty()
                    && array[i] >= array[window.peekLast()]) {
                window.removeLast();
            }

            //remove first number if it doesn't fall in the window anymore
            if(!window.isEmpty()
                    && window.peekFirst() <= i - window_size) {
                window.removeFirst();
            }

            window.addLast(i);

            result.add(array[window.peekFirst()]);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
