package main.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import main.utility.Range;

public class MergeSort implements SortInterface {

    private ArrayList <ArrayList <Integer>> arrayStates = new ArrayList <>();
    private ArrayList <Range> ranges = new ArrayList <>();

    private void merge(Integer arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int L[] = new int [n1];
        int R[] = new int [n2];

        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];

        int i = 0, j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }

        ranges.add(new Range(l, r));
        arrayStates.add(new ArrayList<Integer>(Arrays.asList(arr)));
    }

    private Integer[] mergeSortHelper(Integer[] array, int start, int end) {
        if (start < end) {
            int mid = (start+end)/2;
            mergeSortHelper(array, start, mid);
            mergeSortHelper(array, mid+1, end);
            merge(array, start, mid, end);
        }

        return array;
    }

    @Override
    public HashMap<String, Object> run(ArrayList<Integer> list) {
        Integer []array = list.toArray(new Integer[list.size()]);
        Integer[]result = mergeSortHelper(array, 0, array.length-1);

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
