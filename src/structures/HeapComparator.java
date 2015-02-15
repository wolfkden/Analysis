package structures;

import java.util.Comparator;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 25, 2010
 * Time: 3:16:03 PM
 */
public class HeapComparator<T extends Comparable<T>> {
    public static enum Operations { MAX, MIN }

    private Operations opState;

    {
        opState = Operations.MAX;
    }

    public HeapComparator() { }
    public HeapComparator(Operations state) { opState = state; }

    public static <T extends Comparable<T>>iComparatorPlus<Node<T>> getOperation(Operations state)
    {
        iComparatorPlus<Node<T>> comparator;

        switch (state) {
            default:
            case MAX:  comparator = new iComparatorPlus<Node<T>>()
            {
                public int compare(Node<T> t1, Node<T> t2) {
                    if(t1 == null) return t2 == null ? 0 : -1;
                    if(t2 == null) return t1 == null ? 0 : 1;
                    if(t1.equals(t2)) return 0;
                    int value = t1.item.compareTo(t2.item);
                    return value < 0 ? -1 : (0 < value ? 1 : 0);
            }
                public Operations getState() { return Operations.MAX; }
            };
                break;
            case MIN:   comparator = new iComparatorPlus<Node<T>>()
            {
                public int compare(Node<T> t1, Node<T> t2) {
                    if(t1 == null) return t2 == null ? 0 : -1;
                    if(t2 == null) return t1 == null ? 0 : 1;
                    if(t1.equals(t2)) return 0;
                    int value =  t2.item.compareTo(t1.item);
                    return value < 0 ? -1 : (0 < value ? 1 : 0);
            }
                public Operations getState() { return Operations.MIN; }
            };
                break;
        }

        return comparator;
    }
}
