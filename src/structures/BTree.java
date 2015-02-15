package structures;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 23, 2010
 * Time: 9:53:00 PM
 */
public class BTree<T extends Comparable<T>> extends Node<T> implements Cloneable, iBTree<T> {
    private long depth;

    private long levels;
    private iBTree<T> interfaceComponent;

    private enum CHILDREN {
        LEFT, RIGHT
    }

    {
        this.depth = 0;
        this.interfaceComponent = new BTreeHorizontal<T>(this);
    }

    public BTree() { super(); }
    public BTree(HeapComparator.Operations state) { super(state); }

    public BTree(T item) {
        super(item);
        this.setDepth(0);
    }

    public BTree(T item, HeapComparator.Operations state) {
        super(item, state);
        this.setDepth(0);
    }

    public BTree(T item, BTree<T> parent, HeapComparator.Operations state) {
        super(item, parent, state);
        this.setDepth(0);
        if ((this.parent = parent) == null) return;

        this.setComparator(this.getParent().getComparator());
        this.getParent().setChild(this);
    }

    public BTree(BTree<T> node) {
        super(node);
        if(node == null) return;

        this.depth = node.depth;
    }

    public BTree(BTree<T> node, BTree<T> parent, ArrayList<Node<T>> children) {
        super(node, parent, children);
        if(node == null) return;

        this.findDepth();
    }

    protected void setNode(BTree<T> node)
    {
        super.setNode(node);
        if(node == null) { this.depth = 0; return; }
        this.depth = node.depth;
    }

    protected void setNode(BTree<T> node, BTree<T> parent)
    {
        super.setNode(node, parent);
        if(node == null) { this.depth = 0; return; }
        this.depth = node.depth;
    }

    protected void setNode(BTree<T> node, BTree<T> parent, ArrayList<Node<T>> children)
    {
        super.setNode(node, parent, children);
        if(node == null) { this.depth = 0; return; }
        this.findDepth();
    }

    public BTree<T> findRoot()
    {
        BTree<T> parent = this;
        while(parent.getParent() != null) parent = parent.getParent();

        return parent;
    }

    public BTree<T> findItem(T item)
    {
        if(this.item == null || item == null
                || this.item.compareTo(item) < 0) return null;
        if(this.item.equals(item)) return this;


        BTree<T> foundNode = null;
        for(Node<T> node : this.getChildren())
            foundNode = foundNode == null ?
                    ((BTree<T>)node).findItem(item) : foundNode;

        return foundNode;
    }

    public BTree<T> findNode(BTree<T> target)
    {
        if(this.item == null || target == null
                || this.compareTo(target) < 0) return null;
        if(this.equals(target)) return this;

        BTree<T> foundNode = null;
        for(Node<T> node : this.getChildren())
            if((foundNode = ((BTree<T>)node).findNode(target)) != null) break;

        return foundNode;
    }

    public BTree<T> findNodeID(BTree<T> target)
    {
        if(this.item == null || target == null) return null;
        if(this == target) return this;

        BTree<T> foundNode = null;
        for(Node<T> node : this.getChildren())
            if((foundNode = ((BTree<T>)node).findNodeID(target)) != null) break;

        return foundNode;
    }

    public BTree<T> join(BTree<T> tbTree) { return this.interfaceComponent.join(tbTree); }

    public BTree<T> removeNode() { return this.interfaceComponent.removeNode(); }

    public BTree<T> removeNode(BTree<T> node) { return this.interfaceComponent.removeNode(node); }

    private void setNodeChildren(BTree<T> sourceNode)
    {
        if(sourceNode == null) return;
        for(Node<T> node: sourceNode.getChildren())
            this.setChild(node);
    }

    private BTree<T> getOtherChild(BTree<T> sourceNode)
    {
        if(sourceNode == null && this.numberOfChildren() == 0) return null;

        BTree<T> otherChild = null;
        for(Node<T> node : this.getChildren())
            otherChild = node == sourceNode ? otherChild : (BTree<T>)node;

        return otherChild;
    }

    protected BTree<T> getRandomChild(BTree<T> node)
    {
        if(node == null) return null;
        if(node.numberOfChildren() < 1) return null;
        if(node.numberOfChildren() == 1) return node.getLeftChild();
        Random rand = new Random(new Date().getTime());
        return (BTree<T>)node.getChild(rand.nextInt() & 1);
    }

    protected BTree<T> getRandomChild()
    {
        if(this.numberOfChildren() < 1) return null;
        if(this.numberOfChildren() == 1) return this.getLeftChild();
        Random rand = new Random(new Date().getTime());
        return (BTree<T>)this.getChild(rand.nextInt() & 1);
    }

    protected BTree<T> getMinChild()
    {
        BTree<T> fNode = null;
        for(Node<T> node : this.getChildren())
            fNode = fNode == null ? (BTree<T>)node
                    : (((BTree<T>)node).getDepth() <= fNode.getDepth() ? (BTree<T>)node : fNode);

        return fNode;
    }

    protected void setChild(BTree<T> child) {

        if(this.numberOfChildren() < 2) super.setChild(child);
        this.findDepth();
        this.checkSwapSiblings();
    }

    public void checkSwapSiblings() { this.interfaceComponent.checkSwapSiblings(); }

    protected void setChild(int index, BTree<T> child) {
        super.setChild(index, child);

        this.findDepth();
    }

    public void removeChild(BTree<T> tNode) {
        super.removeChild(tNode);    //To change body of overridden methods use File | Settings | File Templates.

        this.findDepth();
    }

    public BTree<T> swimUp(BTree<T> node) { return this.interfaceComponent.swimUp(node); }

    private BTree<T> getRightSibling()
    {
        if(this.getParent() == null || this.isRightChild()) return null;
        return this.getParent().getRightChild();
    }

    public BTree<T> swimUp() { return this.interfaceComponent.swimUp(); }

    public void add(BTree<T> node) { this.interfaceComponent.add(node); }

    public void rotateRight(BTree<T> node) { this.interfaceComponent.rotateRight(node); }

    public void rotateLeft(BTree<T> node) { this.interfaceComponent.rotateLeft(node); }

    public static int getLeftIndex() { return BTree.CHILDREN.LEFT.ordinal(); }

    public static int getRightIndex() { return BTree.CHILDREN.RIGHT.ordinal(); }
    
    public BTree<T> getNextMaxNode()
    {
        if(this.getItem() == null || !(this.interfaceComponent instanceof BTreeHorizontal)) return null;

        return this.getRightChild() != null ? this.getRightChild().getMaxNode() :
                (this.isRightChild() && this.getParent() != null
                        ? this.getParent().getParent() : this.getParent());
    }

    public BTree<T> getNextMinNode()
    {
        if(this.getItem() == null || !(this.interfaceComponent instanceof BTreeHorizontal)) return null;

        return this.getLeftChild() != null ? this.getLeftChild().getMinNode() :
                (this.isLeftChild() && this.getParent() != null
                        ? this.getParent().getParent() : this.getParent());
    }

    public BTree<T> getMaxNode()
    {
        if(!(this.interfaceComponent instanceof BTreeHorizontal)) return null;

        return ((BTreeHorizontal<T>)this.interfaceComponent).getMaxNode(this);
    }

    public BTree<T> getMinNode()
    {
        if(!(this.interfaceComponent instanceof BTreeHorizontal)) return null;

        return ((BTreeHorizontal<T>)this.interfaceComponent).getMinNode(this);
    }

    protected boolean isLeftChild()
    {
        if(this.getParent() == null) return false;
        return this.getParent().getChildren().indexOf(this) == CHILDREN.LEFT.ordinal();
    }

    protected boolean isRightChild()
    {
        if(this.getParent() == null) return false;
        return this.getParent().getChildren().indexOf(this) == CHILDREN.RIGHT.ordinal();
    }

    protected BTree<T> getLeftChild()
    {
        if(this.numberOfChildren() < 1) return null;
        return (BTree<T>)this.getChildren().get(CHILDREN.LEFT.ordinal());
    }

    protected BTree<T> getRightChild()
    {
        if(this.numberOfChildren() < 2) return null;
        return (BTree<T>)this.getChildren().get(CHILDREN.RIGHT.ordinal());
    }

    protected void setLeftChild(BTree<T> node)
    {
        if(node == null) return;
        node.setParent(this);
        if(this.numberOfChildren() == 0)
        this.getChildren().add(CHILDREN.LEFT.ordinal(), node);
        else
        this.getChildren().set(CHILDREN.LEFT.ordinal(), node);
        this.findDepth();
    }

    protected void setRightChild(BTree<T> node)
    {
        if(node == null || this.numberOfChildren() < 1) return;
        node.setParent(this);
        if(this.numberOfChildren() < 2)
            this.getChildren().add(CHILDREN.RIGHT.ordinal(), node);
        else
            this.getChildren().set(CHILDREN.RIGHT.ordinal(), node);
        this.findDepth();
    }

    @Override
    public int compareTo(Node<T> compare) {
        return super.compareTo(compare);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return String.format("%s depth: %d %d ", super.toString(), this.depth, this.levels);
    }

    public long getDepth() {
        return this.depth;
    }

    protected void setDepth(long depth) {
        this.depth = depth;
    }

    public long getLevels() {
        return levels;
    }

    public void setLevels(long levels) {
        this.levels = levels;
    }

    @Override
    public BTree<T> getParent() {
        return (BTree<T>) super.getParent();    //To change body of overridden methods use File | Settings | File Templates.
    }

    private int compareToParent() {
        return this.parent == null ? -1 : this.compareTo(this.parent);
    }

    protected void findDepth()
    {
        Long levels = null;
        for(Node<T> node : this.getChildren())
            levels = levels == null ? ((BTree<T>)node).getLevels()
            : Math.max(((BTree<T>)node).getLevels(), levels);

        this.setLevels(levels == null ? 0 : levels + 1);

        Long depth = null;
        for(Node<T> node : this.getChildren())
            depth = depth == null ? ((BTree<T>)node).getDepth()
            : Math.min(((BTree<T>)node).getDepth(), depth);

        long mask = 1; while (depth != null && mask <= depth) mask <<= 2;
        depth = depth == null ? 0 : mask * this.getChildren().size() | depth;
        this.setDepth(depth);
    }

    protected void fixParentDepths()
    {
        this.findDepth();
        BTree<T> parent = this.getParent();
        while(parent != null) { parent.findDepth(); parent = parent.getParent(); }
    }

}
