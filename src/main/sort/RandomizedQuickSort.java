package main.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import main.utility.Range;

public class RandomizedQuickSort extends QuickSort {

    private int randomParitition(Integer []array, int start, int end) {

        int randomNum = ThreadLocalRandom.current().nextInt(start, end);

        Integer temp = array[randomNum];
        array[randomNum] = array[end];
        array[end] = temp;

        int pi = super.partition(array, start, end);
        return pi;
    }

    private Integer[] randomQuickSortHelper(Integer []array, int start, int end) {
        if (start < end) {
            int pivot = randomParitition(array, start, end);
            randomQuickSortHelper(array, start, pivot-1);
            randomQuickSortHelper(array, pivot+1, end);
        }

        return array;
    }

    @Override
    public HashMap <String, Object> run(ArrayList<Integer> list) {

        super.arrayStates.clear();
        super.ranges.clear();

        Integer []array = list.toArray(new Integer[list.size()]);

        Integer[]result = randomQuickSortHelper(array, 0, array.length-1);

        // printArrayStates();

        super.arrayStates.add(new ArrayList <Integer> (Arrays.asList(array)));
        super.ranges.add(new Range(-1, -1));

        HashMap <String, Object> response = new HashMap <>();

        response.put("result", new ArrayList<Integer>(Arrays.asList(result)));
        response.put("states", this.arrayStates);
        response.put("ranges", this.ranges);

        return response;
    }

}
