package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Array utils for arrays and ArrayList.
 * @author Vsevolod Ashohmin.
 * @Date: 13.12.2023
 */
public class ArrayUtils {

    /**
     * Moves array left in N positions.
     */
    public static void moveArrayLeft(String[] array, int n) {
        String[] result = Arrays.copyOf(array, n);
        System.arraycopy(array, n, array, 0, array.length - n);
        System.arraycopy(result, 0, array, array.length - n, n);
    }

    /**
     * Slice list by N index, returns right part.
     */
    public static List<String> slice(ArrayList<String> list, int n) {
        return IntStream.range(n, list.size()).mapToObj(list::get).toList();
    }
}
