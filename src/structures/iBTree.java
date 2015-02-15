package structures;

import java.util.ArrayList;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 31, 2010
 * Time: 3:57:26 PM
 */
public interface iBTree<T extends Comparable<T>> {

    BTree<T> join(BTree<T> tree);
    BTree<T> removeNode();
    BTree<T> removeNode(BTree<T> node);
    void checkSwapSiblings();
    BTree<T> swimUp(BTree<T> node);
    BTree<T> swimUp();
    void add(BTree<T> node);
    void rotateRight(BTree<T> node);
    void rotateLeft(BTree<T> node);
}
