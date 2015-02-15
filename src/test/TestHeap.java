package test;

import structures.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 24, 2010
 * Time: 4:54:42 AM
 */
public class TestHeap {

    public static void main(String[] arguments) {
        Heap<String> heap = new Heap<String>(HeapComparator.Operations.MAX);
        char[] input = "ASORTING".toCharArray();


        for (Character item : input) {
            heap.add(item.toString());
            heap.printNodes();
        }

        input = "EXAMPLE".toCharArray();
        for (Character item : input) {
            heap.add(item.toString());
            heap.printNodes();
        }

/*
        heap.rotateRight();
        heap.printNodes();

        heap.rotateRight();
        heap.printNodes();

        heap.rotateRight();
        heap.printNodes();

        heap.rotateLeft();
        heap.printNodes();

        heap.rotateLeft();
        heap.printNodes();
*/

/*
        heap.printNodes();
        BTree<String> removedNode = heap.removeItem("T");
        System.out.println(removedNode.toString());
        heap.printNodes();
*/
    }
}
