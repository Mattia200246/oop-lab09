package it.unibo.oop.workers02;
import java.util.ArrayList;
import java.util.List;

public class MultiThreadedSumMatrix {
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
    public double sum(final double[][] matrix){

    }

    
    
}
