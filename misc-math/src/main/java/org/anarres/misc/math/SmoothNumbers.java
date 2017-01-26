/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.anarres.misc.math;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntHeapPriorityQueue;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntPriorityQueue;
import it.unimi.dsi.fastutil.ints.IntSet;
import javax.annotation.Nonnegative;

/**
 *
 * @author shevek
 */
public class SmoothNumbers {

    private final int[] factors = new int[]{2, 3, 5};
    private final IntList values = new IntArrayList();
    private final IntPriorityQueue queue = new IntHeapPriorityQueue();
    private final IntSet queueMembers = new IntOpenHashSet();

    {
        enqueue(1);
        calculate();
    }

    private void enqueue(@Nonnegative int value) {
        if (queueMembers.add(value))
            queue.enqueue(value);
    }

    private int calculate() {
        int value = queue.dequeueInt();
        values.add(value);
        queueMembers.remove(value);
        for (int factor : factors)
            enqueue(value * factor);
        return value;
    }

    @Nonnegative
    public int follow(@Nonnegative int value) {
        int low = 0;
        int high;

        for (;;) {
            high = values.size() - 1;
            int guess = values.get(high);
            if (value < guess)
                break;
            guess = calculate();
            if (value < guess)
                return value;
        }

        while (low <= high) {
            int middle = (low + high) >>> 1;
            int guess = values.get(middle);
            if (value < guess)
                high = middle - 1;
            else if (value > guess)
                low = middle + 1;
            else
                return value;
        }

        return values.get(low);
    }
}
