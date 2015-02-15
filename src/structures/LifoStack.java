package structures;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 20, 2010
 * Time: 1:14:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class LifoStack<T extends Comparable<T>> {
    private LinkedList<T> stack;
    protected class LinkedList<T extends Comparable<T>> extends Node<T>
    {
//        private LinkedList() {}
        public LinkedList(T item)
        {
            super(item);
        }
        public LinkedList(T item, LinkedList<T> parent)
        {
            super(item, parent);
        }

        @Override
        public void addChildren(ArrayList<Node<T>> children) {
            // super.addChildren(children);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public LinkedList<T> getParent() {
            return (LinkedList<T>)super.getParent();    //To change body of overridden methods use File | Settings | File Templates.
        }
    }

    public LifoStack() { stack = null; }
    public LifoStack(T item) { stack = new LinkedList<T>(item); }
    public LifoStack(LifoStack<T> lifo) { stack = lifo.stack; }

    public void push(T item)
    {
        stack = new LinkedList<T>(item, stack);
    }
    public T pop()
    {
        T item = this.peek();
        if(stack != null && (stack = stack.getParent()) != null) stack.removeChildren();

        return item;
    }

    public T peek()
    {
        return stack == null ? null : stack.getItem();
    }
    protected LinkedList<T> getStack() {
        return stack;
    }

    protected void setStack(LinkedList<T> stack) {
        this.stack = stack;
    }
}
