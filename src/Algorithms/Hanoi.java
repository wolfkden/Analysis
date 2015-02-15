package Algorithms;

/**
 * Created by IntelliJ IDEA.
 * User: Wolf
 * Date: Mar 19, 2010
 * Time: 11:54:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hanoi {
    private int iterations;
    public Hanoi(int iterations) { this.iterations = iterations; }
    public int run() { return algorithm(iterations, 2); }
    private int algorithm(int disks, int peg)
    {
        int moves = 0;
        if(disks == 0) return 0;
        moves += algorithm(disks-1, (peg * 2) % 3);
        moves += shift(peg);
        moves += algorithm(disks-1, (peg * 2) % 3);


        return moves;
    }
    private int shift(int peg)  { return 1; }
}

