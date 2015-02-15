package Algorithms;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 19, 2010
 * Time: 11:53:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Fibonacci {
    private int iterations;

    public Fibonacci(int iterations)
    {
        this.iterations = iterations;
    }
    public int run()
    {
        return algorithm(1, 1, iterations);
    }
    private int algorithm(int first, int second, int iteration)
    {
        if(iteration < 1) return iteration == 0 ? first+second : 0;
        return algorithm(second, first+second, --iteration);
    }
}

