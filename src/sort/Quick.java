package sort;

import java.util.Date;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 20, 2010
 * Time: 12:01:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class Quick<T extends Comparable<T>> implements iSort<T> {
    private T[] array;

    public void mergeArray(T[] al, T[] ah, T[] array) {
        for (int i = 0, j = 0, k = 0; k < array.length; k++)
            array[k] = i == al.length ? ah[j++] : (j == ah.length ? al[i++]
                    : ((al[i]).compareTo(ah[j]) < 1 ? al[i++] : ah[j++]));
    }

    private int lower(T[] array, int index) {
        int lower = 0;
        int upper = array.length - 1;

        return lower(array, index, lower, upper);
    }

    private int upper(T[] array, int index) {
        int lower = 0;
        int upper = array.length - 1;

        return upper(array, index, lower, upper);
    }

    private int lower(T[] array, int index, int lower, int upper) {
//        while(lower < upper) if(array[lower].compareTo(array[index]) < 0) lower++; else break;
        if (lower <= upper)
            while (array[lower].compareTo(array[index]) == -1) lower++;

        return lower;
    }

    private int upper(T[] array, int index, int lower, int upper) {
//        while(lower < upper) if(0 < array[upper].compareTo(array[index])) upper--; else break;
        if (lower <= upper)
            while (array[upper].compareTo(array[index]) == 1) upper--;

        return upper;
    }

    private int getSample(T[] array) {
        return Math.abs(new Random(new Date().getTime()).nextInt()) % array.length;
    }

    private int getSample(T[] array, int lower, int upper) {
        return upper - lower < 2 ? lower : (upper - lower < 3 ? ++lower : ++lower + Math.abs(new Random(new Date().getTime()).nextInt()) % (upper - lower));
    }

    private void swap(T[] array, int lower, int upper) {
        if (upper - lower < 1) return;
        T temp = array[lower];
        array[lower] = array[upper];
        array[upper] = temp;
    }

    public T[] sort(T[] array) {
        sort(array, 0, array.length - 1);

        return array;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public T[] sort(T[] array, int lower, int upper) {

        if (0 < upper - lower) {
            int sample = lower + (upper - lower + 1) / 2; //getSample(array, lower, upper);
            int n_lower = this.lower(array, sample, lower, upper);
            int n_upper = this.upper(array, sample, lower, upper);
            swap(array, n_lower, n_upper);
            sort(array, lower, --n_upper);
            sort(array, ++n_lower, upper);
        }

        return array;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
