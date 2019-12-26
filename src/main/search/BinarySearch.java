package main.search;

import java.util.ArrayList;
import java.util.HashMap;

public class BinarySearch implements SearchInterface {

    ArrayList <Integer> currentIndex = new ArrayList<>();

    private boolean binarySearchHelper(Integer []arr, int start, int end, int x) {
        if (start <= end) {
            int mid = (start+end)/2;
            this.currentIndex.add(mid);
            if (arr[mid] == x) {
                return true;
            } else if (arr[mid] > x) {
                return binarySearchHelper(arr, start, mid-1, x);
            } else {
                return binarySearchHelper(arr, mid+1, end, x);
            }
        }
        return false;
    }

    @Override
    public HashMap<String, Object> doSearch(ArrayList<Integer> list, int x) {

        this.currentIndex.clear();

        boolean result = this.binarySearchHelper(list.toArray(new Integer[list.size()]), 0, list.size()-1, x);
        if (!result) {
            this.currentIndex.add(-1);
        }

        HashMap <String, Object> response = new HashMap<>();
        response.put("index", currentIndex);

        return response;
    }
}
