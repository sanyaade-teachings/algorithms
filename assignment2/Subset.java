/*
 * Subset client for algs4 class on coursera
 *
 * Author: Mark Pauley
 * Date: 9-8-2013
 */

public class Subset {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: subset <k> < file-or-stdin");
        }
        int k = Integer.parseInt(args[0]);
        int numRead = 0;
        RandomizedQueue<String> randQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String inStr = StdIn.readString();
            numRead++;
            if (numRead <= k) {
                randQueue.enqueue(inStr);
            }
            else {
                int luckyNumber = StdRandom.uniform(numRead);
                if (luckyNumber < k) {
                    // kick out another string and
                    // pick this string with a k / numRead chance.
                    // it may get kicked out by an even luckier string later.
                    // I did a quick proof-by-induction to show that
                    // this yields k / numRead chance for each string.
                    randQueue.dequeue();
                    randQueue.enqueue(inStr);
                }
            }
        }
        for (String outStr : randQueue) {
            System.out.println(outStr);
        }
    }
}