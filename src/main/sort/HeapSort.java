package main.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import main.utility.Range;

public class HeapSort implements SortInterface {

    private ArrayList <ArrayList <Integer>> arrayStates = new ArrayList <>();
    private ArrayList <Range> ranges = new ArrayList <>();

    private void heapify(Integer arr[], int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2*i + 1; // left = 2*i + 1
        int r = 2*i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

    public Integer []HeapSortHelper(Integer arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            ranges.add(new Range(0, i));
            arrayStates.add(new ArrayList<Integer>(Arrays.asList(arr)));
            heapify(arr, i, 0);
        }

        return arr;
    }

    @Override
    public HashMap<String, Object> run(ArrayList<Integer> list) {
        Integer []array = list.toArray(new Integer[list.size()]);
        Integer[]result = HeapSortHelper(array);

        arrayStates.add(new ArrayList <Integer> (Arrays.asList(array)));
        ranges.add(new Range(-1, -1));

        HashMap <String, Object> response = new HashMap <>();

        response.put("result", new ArrayList<Integer>(Arrays.asList(result)));
        response.put("states", this.arrayStates);
        response.put("ranges", this.ranges);

        return response;
    }

    @Override
    public void printArrayStates() {
        // TODO Auto-generated method stub

    }

}
