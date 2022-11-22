package it.unibo.oop.workers02;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadedSumMatrix implements SumMatrix{
    int thread;
    public MultiThreadedSumMatrix(int n){
        this.thread = n;
    }

    private class Worker extends Thread{
        private final double[][] matrix;
        private final int startpos;
        private final int nelem;
        private double res;

        private Worker(double[][] matrix, int startpos, int nelem){
            super();
            this.matrix = matrix;
            this.startpos = startpos;
            this.nelem = nelem;
        }

        @Override
        public void run() {
            System.out.println("Working from position " + startpos + " to position " + (startpos + nelem - 1));
            for (int i = startpos; i < matrix.length && i < startpos + nelem; i++) {
                for(final double d : matrix[i]){
                    this.res = res + d;
                }
            }
        }

        public double getResult() {
            return this.res;
        }
    }

    @Override
    public double sum(double[][] matrix){
        final int size = matrix.length % thread + matrix.length / thread;
        /*
         * Build a list of workers
         */
        final List<Worker> workers = new ArrayList<>(thread);
        for (int start = 0; start < matrix.length; start += size) {
            workers.add(new Worker(matrix, start, size));
        }
        /*
         * Start them
         */
        for (final Worker w: workers) {
            w.start();
        }
        /*
         * Wait for every one of them to finish. This operation is _way_ better done by
         * using barriers and latches, and the whole operation would be better done with
         * futures.
         */
        long sum = 0;
        for (final Worker w: workers) {
            try {
                w.join();
                sum += w.getResult();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
        /*
         * Return the sum
         */
        return sum;
    }

    
    
}
