package structures;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 22, 2010
 * Time: 6:05:20 PM
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Developed in IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 20, 2010
 * Time: 12:15:35 PM
 */
public class PriorityQueue<H extends Comparable<H>, T extends Comparable<T>> {
    private HashMap<H, Item<T>> stack;

    public PriorityQueue() {
        stack = new HashMap<H, Item<T>>();
    }


    public PriorityQueue(PriorityQueue<H, T> lifo) {
        this.stack = lifo == null || lifo.getStack() == null ? new HashMap<H, Item<T>>() : lifo.getStack();
    }

    public void insert(H hash, Item<T> item) {
        stack.put(hash, item);
    }

    public T remove(H hash) {
        Item<T> item = stack.remove(hash);

        return item.getItem();
    }

    public HashMap<H, Item<T>> getStack() {
        return stack;
    }

    public H getMaxKey()
    {
        H max = null;

         for(H item : this.stack.keySet())
             max = max == null || item.compareTo(max) < 0 ? item : max;

        return null;
    }

    public int empty()
    {
        stack.clear();

        return stack.size();
    }

    public boolean exchange(H oldPriority, H newPriority)
    {
        Item<T> item; boolean flag;

        if(flag = (null != (item = stack.remove(oldPriority))))
            stack.put(newPriority, item);

        return flag;
    }

    public void join(PriorityQueue<H, T> pqFirst, PriorityQueue<H, T> pqSecond)
    {
        if(pqFirst != null && pqFirst.getStack() != null) this.stack.putAll(pqFirst.getStack());
        if(pqSecond != null && pqSecond.getStack() != null) this.stack.putAll(pqSecond.getStack());
    }
}

