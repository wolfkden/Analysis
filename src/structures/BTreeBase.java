package structures;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 31, 2010
 * Time: 9:14:24 PM
 */
public class BTreeBase<T extends Comparable<T>> {
    private BTree<T> thisBTree;
    protected BTreeBase() {}
    protected BTreeBase(BTree<T> tree) { this.thisBTree = tree; }

    protected BTree<T> getThisBTree() {
        return thisBTree;
    }

}
