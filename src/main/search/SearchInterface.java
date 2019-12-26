package main.search;

import java.util.ArrayList;
import java.util.HashMap;

public interface SearchInterface {
    HashMap<String, Object> doSearch(ArrayList<Integer> list, int x);
}
