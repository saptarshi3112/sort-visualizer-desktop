package main.utility;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomArray {
    private static int min = 5;
    private static int max = 500;

    public static ArrayList<Integer> generateRandomArray(Integer size) {

        ArrayList <Integer> list = new ArrayList <>();

        for (int i = 0; i < size; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            list.add(randomNum);
        }

        return list;
    }
}
