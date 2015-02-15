package structures;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 20, 2010
 * Time: 12:28:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class PQItem<K extends Comparable<K>, T extends Comparable<T>>
implements Comparable<K>//, Comparable<PQItem<K, T>>
//    implements Comparable<PQItem<K, T>>
{
    private PQItem<K, T> pqItem;

    private PQItem() {}
    private PQItem(K key, T item) {}

    public int compareTo(K k) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
/*
    public int compareTo(PQItem<K, T> pqItem) {
        this.pqItem = pqItem;
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
*/
}
