package structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 20, 2010
 * Time: 12:04:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    T item;
    Node<T> parent;
    private ArrayList<Node<T>> children;

    private iComparatorPlus<Node<T>> comparator;

    public HeapComparator.Operations getComparatorState() {
        return comparator.getState();
    }

    {
        this.item = null;
        this.parent = null;
        this.children = null;
        comparator = HeapComparator.<T>getOperation(HeapComparator.Operations.MAX);
    }

    protected Node() { this.item = null; }
    protected Node(HeapComparator.Operations state)
    {
        this.item = null;
        this.comparator = HeapComparator.<T>getOperation(state);
    }

    public Node(T item) {
        this.item = item;
    }

    public Node(Node<T> node) {
        if(node == null) { this.item = null; return; }

        this.item = node.item;
        this.parent = node.parent;
        this.children = node.children;
        this.comparator = node.comparator;
    }

    public Node(Node<T> node, Node<T> parent, ArrayList<Node<T>> children) {
        if(node == null) { this.item = null; return; }

        this.item = node.item;
        this.parent = parent;
        this.children = children;
        for(Node<T> e : this.getChildren()) e.setParent(this);
        this.comparator = node.comparator;
    }

    protected void setNode(Node<T> node)
    {
        if(node == null) { this.item = null; return; }

        this.item = node.item;
        this.parent = node.parent;
        this.children = node.children;
        this.comparator = node.comparator;
    }

    protected void setNode(Node<T> node, Node<T> parent)
    {
        if(node == null) { this.item = null; return; }

        this.item = node.item;
        this.parent = parent;
        this.children = node.children;
        for(Node<T> e : this.getChildren()) e.setParent(this);
        this.comparator = node.comparator;
    }


    protected void setNode(Node<T> node, Node<T> parent, ArrayList<Node<T>> children)
    {
        if(node == null) { this.item = null; return; }

        this.item = node.item;
        this.parent = parent;
        this.children = children;
        this.comparator = node.comparator;
    }


    public Node(T item, HeapComparator.Operations state) {
        this.item = item;
        this.comparator = HeapComparator.<T>getOperation(state);
    }

    public Node(T item, Node<T> parent) {
        this.item = item;
        this.parent = parent;
        if(this.parent != null) this.parent.addChild(this);
    }

    public Node(T item, Node<T> parent, HeapComparator.Operations state) {
        this.item = item;
        this.parent = parent;
        if(this.parent != null) this.parent.addChild(this);
        this.comparator = HeapComparator.<T>getOperation(state);
    }

    public void setComparator(HeapComparator.Operations state)
    {
        this.comparator = HeapComparator.<T>getOperation(state);
    }

    public T getItem() {
        return item;
    }

    protected void setItem(T item) {
        this.item = item;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public boolean hasChild(Node<T> target)
    {
        boolean flag = false;
        for(Node<T> node : this.getChildren())
            flag |= node == target;

        return flag;
    }

    protected boolean isChildIndexInBounds(int index)
    {
        return -1 < index && index < this.numberOfChildren();
    }

    protected iComparatorPlus<Node<T>> getComparator() {
        return comparator;
    }

    protected void setComparator(iComparatorPlus<Node<T>> comparator) {
        this.comparator = comparator;
    }

    public Node<T> getChild(int index) {
        if(!isChildIndexInBounds(index)) return null;
        return this.getChildren().get(index);
    }

    public void setChild(Node<T> child) {
        if(child == null) return;
        child.setParent(this);
        this.getChildren().add(child);
    }

    protected void swapSibling(int a, int b)
    {
        if(this.getChildren().size() < 2) return;

        Node<T> node = this.getChildren().get(a);
        this.getChildren().set(a, this.getChildren().get(b));
        this.getChildren().set(b, node);

    }

    public void setChild(int index, Node<T> child) {
        if(child == null) return;
        child.setParent(this);
        this.getChildren().add(index, child);
    }

    public int numberOfChildren() { return this.getChildren().size(); }

    public ArrayList<Node<T>>  getChildren()
    {
        return this.children == null ? (this.children = new ArrayList<Node<T>>()) : this.children;
    }

    @Override
    public String toString() {
        return String.format("Parent: %s Item: %s #children: %d"
                , this.parent == null ? "root" : this.parent.getItem()
                , this.getItem(), this.numberOfChildren()); 
    }

    private ArrayList<Node<T>>  initChildren(Node<T> child)
    {
        if(child != null) this.getChildren().add(child);
        return this.getChildren();
    }

    public void addChild(Node<T> child) {
        if(child == null) return;
        this.getChildren().add(child);
    }

    public void removeChildren() {
        this.getChildren().clear();
    }

    public void removeChild(Node<T> node) {
        if(node == null) return;
        this.getChildren().remove(node);
    }

    public void addChildren(ArrayList<Node<T>> children) {
        if(children == null) return;
        this.getChildren().addAll(children);
    }

    public void resetChildren(ArrayList<Node<T>> children) {
        this.clearChildren();

        if(children == null) return;

        for(Node<T> child : children) child.setParent(this);
        this.addChildren(children);
    }
    
    protected void clearChildren()
    {
        this.getChildren().clear();
    }

    public int compareTo(Node<T> compare) //throws NullPointerException 
    {
//        return compare == null ? 1 : this.item.compareTo(compare.getItem());
//        if(compare == null) throw new NullPointerException();
        return this.comparator.compare(this, compare);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node<T> node = (Node<T>) o;

//        if (children != null ? !children.equals(node.children) : node.children != null) return false;
//        if (item != null ? !item.equals(node.item) : node.item != null) return false;
//        if (parent != null ? !parent.equals(node.parent) : node.parent != null) return false;

        return item != null && item.equals(node.item);
    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
//        result = 31 * result + (parent != null ? parent.hashCode() : 0);
//        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }
}
