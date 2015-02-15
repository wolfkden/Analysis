package structures;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 20, 2010
 * Time: 1:51:22 AM
 * To change this template use File | Settings | File Templates.
 */

public class LifoPlus<T extends Comparable<T>> {
    private LifoStack<Item<T>> stack;

    public LifoPlus() {
        stack = new LifoStack<Item<T>>();
    }

    public LifoPlus(T item) {
        stack = new LifoStack<Item<T>>(new Item<T>(item, item));
    }

    public LifoPlus(LifoPlus<T> lifo) {
        this.stack = lifo == null || lifo.getStack() == null ? new LifoStack<Item<T>>() : lifo.getStack();
    }

    public void push(T item) {
        Item<T> pItem = new Item<T>(item);
        pItem.setMax(this.maxPeek() == null || this.maxPeek().compareTo(item) < 1 ? item : this.maxPeek());

        stack.push(pItem);
    }

    public T pop() {

        return stack.peek() == null ? null : stack.pop().getItem();
    }

    public T peek() {
        return stack.peek() == null ? null : stack.peek().getItem();
    }

    public T maxPeek() {
        return stack.peek() == null ? null : stack.peek().getMax();
    }

    public LifoStack<Item<T>> getStack() {
        return stack;
    }
}
