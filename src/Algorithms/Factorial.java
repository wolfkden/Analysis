package Algorithms;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 19, 2010
 * Time: 11:52:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Factorial {
    private int iterations;
    public Factorial(int iterations) { this.iterations = iterations; }
    public int run()
    {
          return algorithm(1, iterations);
    }
    private int algorithm(int current, int iteration)
    {
        if(iteration < 1) return 1;
        return current*algorithm(current+1, iteration-1);
    }
}
