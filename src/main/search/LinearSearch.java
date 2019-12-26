package main.search;

import java.util.ArrayList;
import java.util.HashMap;

public class LinearSearch implements SearchInterface {

    ArrayList <Integer> currentIndex = new ArrayList<>();
    private boolean flag = false;

    private boolean linearSearchHelper(Integer []arr, int size, int x) {

        for (int i = 0; i < size; i++) {
            if (arr[i] == x) {
                flag = true;
                currentIndex.add(i);
                break;
            }

            currentIndex.add(i);
        }

        return flag;
    }

    @Override
    public HashMap<String, Object> doSearch(ArrayList<Integer> list, int x) {

        boolean result = this.linearSearchHelper(list.toArray(new Integer[list.size()]), list.size(), x);

        if (!result) {
            currentIndex.add(-1);
        }

        HashMap <String, Object> response = new HashMap<>();
        response.put("index", currentIndex);

        return response;
    }
}
