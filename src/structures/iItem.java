package structures;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 20, 2010
 * Time: 2:58:11 AM
 * To change this template use File | Settings | File Templates.
 */
public interface iItem<T> {
    public T getItem();
    void setItem(T item);
    T getMax();
    void setMax(T max);
    int compareTo(iItem<T> tItem);
}
