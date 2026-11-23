package com.happy.hot100;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class L347TopKFrequentElementsTest {

    @Test
    void returnsTheMostFrequentValues() {
        int[] result = new L347TopKFrequentElements().topKFrequent(
                new int[]{1, 1, 1, 2, 2, 3}, 2);

        assertArrayEquals(new int[]{1, 2}, result);
    }

    @Test
    void limitsTheResultToDistinctValuesAndHandlesNonPositiveK() {
        L347TopKFrequentElements solution = new L347TopKFrequentElements();

        assertArrayEquals(new int[]{7, 8}, solution.topKFrequent(new int[]{7, 8}, 5));
        assertArrayEquals(new int[0], solution.topKFrequent(new int[]{7, 8}, 0));
    }
}
