package test;

import structures.BTree;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.TypeVariable;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 25, 2010
 * Time: 11:26:08 PM
 */
public class TestReflection {

    public static void main(String[] arguments) {
        Class classVal = BTree.class;
        TypeVariable[] type = BTree.class.getTypeParameters();
        java.lang.reflect.Constructor<?>[] constructors = classVal.getConstructors();


        for (TypeVariable v : type)
            System.out.println(v.toString());
        for (
                Constructor<?> c : constructors)
            System.out.println(c.toString());

        try {
            BTree<?> c = (BTree<?>) constructors[1].newInstance("T");
            c.getChildren();
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (
                InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
