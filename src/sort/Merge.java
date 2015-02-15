package sort;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 19, 2010
 * Time: 11:58:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Merge<T extends Comparable<T>> implements iSort<T> {
    private T[] targetArray;

    public Merge() {
    }

    public Merge(T[] initialArray) {
        targetArray = initialArray;
    }

    public void mergeArray(T[] al, T[] ah, T[] array) {
        for (int i = 0, j = 0, k = 0; k < array.length; k++)
            array[k] = i == al.length ? ah[j++] : (j == ah.length ? al[i++]
                    : ((al[i]).compareTo(ah[j]) < 1 ? al[i++] : ah[j++]));
    }

    public T[] sort(T[] array) {
        if (1 < array.length) {
            T[] al = sort(Arrays.copyOfRange(array, 0, (array.length + 1) / 2));
            T[] ah = sort(Arrays.copyOfRange(array, (array.length + 1) / 2, array.length));
            this.mergeArray(al, ah, array);
        }
        return array;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public T[] sort() {

        T[] array = this.targetArray;
        if (1 < array.length) {
            T[] al = sort(Arrays.copyOfRange(array, 0, (array.length + 1) / 2));
            T[] ah = sort(Arrays.copyOfRange(array, (array.length + 1) / 2, array.length));
            this.mergeArray(al, ah, array);
        }
        return array;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void run() {
        if (targetArray == null) return;

        T[] array = targetArray;
        if (1 < array.length) {
            T[] al = sort(Arrays.copyOfRange(array, 0, (array.length + 1) / 2));
            T[] ah = sort(Arrays.copyOfRange(array, (array.length + 1) / 2, array.length));
            this.mergeArray(al, ah, array);
        }
    }
}
