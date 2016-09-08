package net.igorkromin.exercises;

import net.igorkromin.Exercise;

/**
 * The Fibonacci sequence is a series of numbers where a number is found by adding up the two numbers before it.
 * Starting with 0 and 1, the sequence goes 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, and so forth.
 */
public class FibonacciSequence extends Exercise {

    int seqLen = 10;

    public void solveLoop() {
        int x1 = 0, x2 = 1;

        outl(x1);
        outl(' ');

        for (int i = 0; i < seqLen; i++) {
            outl(x2);
            outl(' ');

            int next = x1 + x2;
            x1 = x2;
            x2 = next;
        }
    }

    public void solveRecurrsion() {
        outl("0 ");

        for (int i = 1; i <= seqLen; i++) {
            int fibo = fibonacci(i);
            outl(fibo + " ");
        }
    }

    private int fibonacci(int seqLen) {
        if (seqLen <= 2) {
            return 1;
        }
        return fibonacci(seqLen - 2) + fibonacci(seqLen - 1);
    }

    public void solveDynamic() {
        outl("0 ");

        // mark all precalculated slots as 'not calculated'
        int[] preCalc = new int[seqLen];
        for (int i = 0; i < seqLen; i++) {
            preCalc[i] = -1;
        }

        // set the two known slots
        preCalc[0] = 1;
        preCalc[1] = 1;

        for (int i = 1; i <= seqLen; i++) {
            int fibo = fibonacciDynamic(i, preCalc);
            outl(fibo + " ");
        }
    }

    private int fibonacciDynamic(int seqLen, int[] preCalc) {
        if (preCalc[seqLen - 1] != -1) {
            return preCalc[seqLen - 1];
        }

        // store for future use
        preCalc[seqLen - 2] =  fibonacci(seqLen - 1);
        preCalc[seqLen - 3] =  fibonacci(seqLen - 2);

        return preCalc[seqLen - 3] + preCalc[seqLen - 2];
    }

    // -- Main method

    public static final void main(String[] args) {
        run(new FibonacciSequence());
    }

}
