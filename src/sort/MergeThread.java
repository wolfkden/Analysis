package sort;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 20, 2010
 * Time: 12:00:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class MergeThread<T extends Comparable<T>> implements iSort<T>, Runnable {
    private T[] targetArray;

    public MergeThread() {
    }

    public MergeThread(T[] initialArray) {
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

    public void run() {
        if (targetArray == null) return;

        T[] array = targetArray;
        if (1 < array.length) {
            T[] al = Arrays.copyOfRange(array, 0, (array.length + 1) / 2);
            T[] ah = Arrays.copyOfRange(array, (array.length + 1) / 2, array.length);

            Thread sortThread_l = new Thread(new MergeThread<T>(al));
            Thread sortThread_h = new Thread(new MergeThread<T>(ah));
            sortThread_l.start();
            sortThread_h.start();
            try {
                while (sortThread_l.isAlive() || sortThread_h.isAlive()) {
                    sortThread_l.join();
                    sortThread_h.join();
                }
            } catch (InterruptedException e) {
            }

            this.mergeArray(al, ah, array);
        }
    }

    public void sort() {
        if (targetArray == null) return;

        this.run();
    }
}

