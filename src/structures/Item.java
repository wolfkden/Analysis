package structures;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 20, 2010
 * Time: 1:51:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class Item<T extends Comparable<T>> implements Comparable<Item<T>>  {
    private T item;
    private T max;

    protected Item() {}
    public Item(T item) {
        this.item = item;
    }

    public Item(T item, T max) {
        this.item = item;
        this.max = max;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    public int compareTo(Item<T> tItem) {
        return tItem == null ? -1 : this.getItem().compareTo(tItem.getItem());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item1 = (Item) o;

//        if (item != null ? !item.equals(item1.item) : item1.item != null) return false;

        return item != null && item.equals(item1.getItem());
    }

    @Override
    public int hashCode() {
        return item != null ? item.hashCode() : 0;
    }
}
