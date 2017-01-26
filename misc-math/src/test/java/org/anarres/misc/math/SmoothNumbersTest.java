/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.anarres.misc.math;

import java.util.Random;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author shevek
 */
public class SmoothNumbersTest {

    private static final Logger LOG = LoggerFactory.getLogger(SmoothNumbersTest.class);

    @Test
    public void testSmoothNumbers() {
        SmoothNumbers smoothNumbers = new SmoothNumbers();
        for (int round = 0; round < 3; round++)
            for (int i = 1; i < 128; i++)
                LOG.info(i + " -> " + smoothNumbers.follow(i));
        Random r = new Random();
        for (int round = 0; round < 128; round++) {
            int i = r.nextInt(1024 * 1024);
            LOG.info(i + " -> " + smoothNumbers.follow(i));
        }
    }

}
