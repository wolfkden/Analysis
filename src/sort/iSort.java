package sort;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 19, 2010
 * Time: 11:56:45 PM
 * To change this template use File | Settings | File Templates.
 */
public interface iSort<T extends Comparable> {
//    T[] getResult();

    T[] sort(T[] array);
}
