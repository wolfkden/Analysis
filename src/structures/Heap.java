package structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;


/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 23, 2010
 * Time: 9:28:14 PM
 */
public class Heap<T extends Comparable<T>> {
    private HeapComparator.Operations opState;
    private BTree<T> node;

    public enum INCREMENT {
        ASCENDING, DESCENDING
    }

    private class BTreeListIterator<U extends T> extends BTreeIterator<T> implements ListIterator<BTree<T>> {

        public BTreeListIterator() { super(); }

        public BTreeListIterator(INCREMENT direct) { super(direct); }

        public boolean hasPrevious() {
            boolean hasNext = this.node != null && this.node.getItem() != null;

            return hasNext &&
                    null != (this.direction == INCREMENT.ASCENDING
                            ? this.node.getNextMinNode() : this.node.getNextMaxNode());
        }

        public BTree<T> previous() {
            if(this.node == null || this.node.getItem() == null) return null;

            return this.direction == INCREMENT.ASCENDING
                            ? this.node.getNextMinNode() : this.node.getNextMaxNode();
        }

        public int nextIndex() throws ArrayIndexOutOfBoundsException {
            throw new ArrayIndexOutOfBoundsException();
        }

        public int previousIndex() throws ArrayIndexOutOfBoundsException {
            throw new ArrayIndexOutOfBoundsException();
        }

        public void set(BTree<T> tbTree) {
            if(this.node == null) return;

            this.node.setNode(tbTree);
        }

        public void add(BTree<T> tbTree) {
            if(this.node == null) 
                this.node = new BTree<T>(tbTree);
            else this.node.setNode(tbTree);
        }
    }
    private class BTreeIterator<U extends T> implements Iterator<BTree<T>> {
        protected BTree<T> node;
        protected INCREMENT direction;

        {
            this.node = Heap.this.node.getMaxNode();
            direction = INCREMENT.DESCENDING;
        }

        public BTreeIterator() {
        }

        public BTreeIterator(INCREMENT direct) {
            this.direction = direct;
            switch (direct) {
                default:
                case DESCENDING: this.node = Heap.this.node.getMaxNode(); break;
                case ASCENDING: this.node = Heap.this.node.getMinNode(); break;
            }
        }

        public boolean hasNext() {

            boolean hasNext = this.node != null && this.node.getItem() != null;

            return hasNext &&
                    null != (this.direction == INCREMENT.ASCENDING
                            ? this.node.getNextMaxNode() : this.node.getNextMinNode());
        }

        public BTree<T> next() {
            if(this.node == null || this.node.getItem() == null) return null;

            return this.direction == INCREMENT.ASCENDING
                            ? this.node.getNextMaxNode() : this.node.getNextMinNode();
        }

        public void remove() {
            if(this.node == null || this.node.getItem() == null) return;
            
            this.node.removeNode();
        }

        public INCREMENT getDirection() { return this.direction; }
    }

    {
        node = new BTree<T>();
        opState = HeapComparator.Operations.MAX;
    }

    public Heap() {
        this.node = new BTree<T>(opState);
    }

    public Heap(HeapComparator.Operations state) {
        this.node = new BTree<T>(state);
    }

    public Heap(T item) {
        this.node = new BTree<T>(item, opState);
    }

    public Heap(T item, HeapComparator.Operations state) {
        this.node = new BTree<T>(item, state);
    }

    public Heap(BTree<T> node) {
        this.node = new BTree<T>(node.item, opState);
    }

    public Heap(BTree<T> node, HeapComparator.Operations state) {
        this.node = new BTree<T>(node.item, state);
    }

    public Iterator<BTree<T>> iterator()
    {
        return new BTreeIterator<T>();
    }

    public ListIterator<BTree<T>> listIterator()
    {
        return new BTreeListIterator<T>();
    }

    public Iterator<BTree<T>> iterator(INCREMENT direction)
    {
        return new BTreeIterator<T>(direction);
    }

    public ListIterator<BTree<T>> listIterator(INCREMENT direction)
    {
        return new BTreeListIterator<T>(direction);
    }

    public void add(BTree<T> node) {
        this.add(node.item);
    }

    public void add(T item) {
        this.node.add(new BTree<T>(item, opState));
        setParentNode();
    }

    protected BTree<T> findItem(T item) {
        return node.findItem(item);
    }

    protected BTree<T> findNode(BTree<T> node) {
        return node.findNode(node);
    }

    public void rotateRight() {
        this.setParentNode();
        this.node.rotateRight(node);
    }

    public void rotateLeft() {
        this.setParentNode();
        this.node.rotateLeft(node);
    }

    public void rotateRight(T item) {
        this.node.rotateRight(node.findItem(item));
    }

    public void rotateLeft(T item) {
        this.node.rotateLeft(node.findItem(item));
    }

    public BTree<T> removeRootNode() {
        this.setParentNode();
        return this.node.removeNode();
    }

    public BTree<T> removeItem(T item) {
        this.setParentNode();
        return this.node.removeNode(this.node.findItem(item));
    }

    protected BTree<T> removeNode(BTree<T> node) {
        this.setParentNode();
        return this.node.removeNode(this.node.findNode(node));
    }

    protected BTree<T> setParentNode() {
        while (this.node != null)
            if (this.node.getParent() == null) break;
            else this.node = this.node.getParent();

        return this.node;
    }

    public BTree<T> removeRoot() {
        this.setParentNode();
        return this.node.removeNode();
    }

    public void printNodes() {
        this.setParentNode();
        printNodes(this.makeList(this.node), 0);
    }

    public void printNodes(ArrayList<BTree<T>> levelList, int level) {
        if (levelList == null) return;
        System.out.printf("Level: %d ", level);
        for (BTree<T> node : levelList)
            System.out.printf("Parent: %s %d %d value: %s "
                    , node.getParent() == null ? "root" : node.getParent().getItem().toString()
                    , node.getDepth(), node.getLevels()
                    , node.getItem() == null ? null : node.getItem().toString());
        System.out.printf("%n");

        ArrayList<BTree<T>> printList = this.makeList();
        for (BTree<T> node : levelList)
            for (Node<T> child : node.getChildren())
                printList.add((BTree<T>) child);

        if (0 < printList.size())
            this.printNodes(printList, level + 1);
    }

    private ArrayList<BTree<T>> makeList() {
        return new ArrayList<BTree<T>>();
    }

    private ArrayList<BTree<T>> makeList(BTree<T> node) {
        if (node == null) return null;
        ArrayList<BTree<T>> list;
        (list = makeList()).add(node);

        return list;
    }
}
