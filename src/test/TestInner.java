package test;

/**
 * Developed in the IntelliJ IDEA Environment.
 * User: Wolf
 * Date: Mar 27, 2010
 * Time: 12:04:36 PM
 */
interface iOuter<T>
{
    void setVar(T var);
    void testMethod();
}
class Outer<T extends Comparable<T>> implements iOuter<T> {
    private static iOuter instance;
    protected static class Inner<U extends Comparable<U>> extends Outer<U>
    {
        private U var;

        @Override
        public void setVar(U var) {
            this.var = var;
        }

        @Override
        public void testMethod() {
            System.out.println("inner method: " + var.toString());
        }
    }
    private Outer() {}
    public static <T extends Comparable<T>>iOuter<T> getInstance()
    {
        return instance == null ? instance = new Outer<T>() : instance;
    }

    public void setVar(T var) { }

    public void testMethod() { }
}
public class TestInner {
    public static void main(String[] arguments)
    {
        iOuter<String> instance = Outer.getInstance();
        instance.setVar("Hello");
        instance.testMethod();
    }
}