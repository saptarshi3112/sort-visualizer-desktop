package main.sort;

import main.utility.Range;

import java.util.ArrayList;
import java.util.HashMap;

public interface SortInterface {
    HashMap<String, Object> run(ArrayList<Integer> list);
    void printArrayStates();
}
