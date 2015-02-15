package test;

import org.testng.annotations.BeforeMethod;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 19, 2010
 * Time: 11:31:48 PM
 * To change this template use File | Settings | File Templates.
 */
import structures.*;
public class Test {

     @BeforeMethod
    public void init() {}

    public static void main(String[] arguments)
    {
        Node<Item<Integer>>  node = new Node<Item<Integer>>(new Item<Integer>(4));
        LifoPlus<Integer> stack = new LifoPlus<Integer>(4);
        Integer max = stack.maxPeek();
        stack.push(33);
        stack.push(23);
        stack.push(44);
        System.out.printf("LifoPlus value: %d maximum: %d%n",  stack.pop(), stack.maxPeek());
    }
}
