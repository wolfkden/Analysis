package structures;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 23, 2010
 * Time: 9:34:32 PM
 */
public class NTree<T extends Comparable<T>> extends Node<T> {

    private T item;
    private NTree() {}
    public NTree(T item) { super(item); }

    @Override
    public int compareTo(Node<T> compare) {
        return super.compareTo(compare);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
