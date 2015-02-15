package Algorithms;

import structures.BTree;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 25, 2010
 * Time: 6:37:42 PM
 */
public class NodeFactory {

    static BTreeFactory instance;
    static class BTreeFactory<U extends Comparable<U>> extends NodeFactory
    {
       BTree<U> node;
        {
            node = new BTree<U>();
        }
       private BTreeFactory() {}

    }
    static {
        instance = null;
    }
    private NodeFactory() {}
    static <T extends Comparable<T>> BTreeFactory<T> getBTreeNode()
    {
        return instance == null ? (instance = new BTreeFactory<T>()) : instance;
    }
}
