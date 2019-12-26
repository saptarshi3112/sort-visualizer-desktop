package main.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import main.utility.Range;

public class QuickSort implements SortInterface {

    protected ArrayList <ArrayList <Integer>> arrayStates = new ArrayList <>();
    protected ArrayList <Range> ranges = new ArrayList <>();

    protected int partition(Integer []array, int start, int end) {
        int pivot = array[end];
        int i = start-1;
        for (int j = start; j < end; j++) {
            if (array[j] < pivot) {
                i += 1;
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }

        int temp = array[i+1];
        array[i+1] = array[end];
        array[end] = temp;

        ranges.add(new Range(start, end));
        arrayStates.add(new ArrayList<Integer>(Arrays.asList(array)));

        return i+1;
    }

    private Integer[] quickSortHelper(Integer []array, int start, int end) {
        if (start < end) {
            int pivot = partition(array, start, end);
            quickSortHelper(array, start, pivot-1);
            quickSortHelper(array, pivot+1, end);
        }

        return array;
    }

    @Override
    public HashMap <String, Object> run(ArrayList<Integer> list) {
        this.arrayStates.clear();
        this.ranges.clear();

        Integer []array = list.toArray(new Integer[list.size()]);

        Integer[]result = quickSortHelper(array, 0, array.length-1);

        // printArrayStates();

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
        for (ArrayList <Integer> stateList : this.arrayStates) {
            for (Integer itr : stateList) {
                System.out.print(itr + " ");
            }
            System.out.println();
        }

    }

}
