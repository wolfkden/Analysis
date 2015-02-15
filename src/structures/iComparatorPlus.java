package structures;

import java.util.Comparator;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 25, 2010
 * Time: 9:09:50 PM
 */
public interface iComparatorPlus<T> extends Comparator<T> {
    HeapComparator.Operations getState();
}
