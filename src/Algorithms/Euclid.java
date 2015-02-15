package Algorithms;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 19, 2010
 * Time: 11:52:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Euclid {
    static int gcd(int N, int M)
    {
        if(M == 0) return N;
        return gcd(M, M % N);
    }
}

