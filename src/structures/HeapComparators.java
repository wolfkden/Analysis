package structures;

import java.util.Comparator;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 25, 2010
 * Time: 4:59:03 AM
 */
public enum HeapComparators
{
    MAX,
    MIN;

    public <T extends Comparable<T>> Comparator<T> getComparator()
    {
        switch(this.ordinal())
        {
            case 0: return new Comparator<T>() {    //MAX
                public int compare(T t1, T t2) {
                    return  t1 != null ? t1.compareTo(t2) : (t2 != null ? -t2.compareTo(t1) : 0);
                }
            };
            case 1: return new Comparator<T>() {         //MIN
                public int compare(T t1, T t2) {
                    return  t2 != null ? t2.compareTo(t1) : (t1 != null ? -t1.compareTo(t2) : 0);
                }
            };
        }

        return   new Comparator<T>() { public int compare(T t, T t1) { return 0; } };
    }

    HeapComparators()
    {

    }
}
