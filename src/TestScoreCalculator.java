package src;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestScoreCalculator {
    @Test
    public void testCalTumoScore1() {
        int[] expected = {1600, 800};
        int[] pay = ScoreCalculator.calTumoScore(false, 3, 25);
        assertArrayEquals(expected, pay);
    }

    @Test
    public void testCalTumoScore2() {
        int[] expected = {0, 1300};
        int[] pay = ScoreCalculator.calTumoScore(true, 3, 25);
        assertArrayEquals(expected, pay);
    }

    @Test
    public void testCalTumoScore3() {
        int[] expected = {1300, 700};
        int[] pay = ScoreCalculator.calTumoScore(false, 3, 20);
        assertArrayEquals(expected, pay);
    }

    @Test
    public void testCalTumoScore4() {
        int[] expected = {0, 1300};
        int[] pay = ScoreCalculator.calTumoScore(true, 3, 20);
        assertArrayEquals(expected, pay);
    }

    @Test
    public void testCalTumoScore5() {
        int[] expected = {4000, 2000};
        int[] pay = ScoreCalculator.calTumoScore(false, 5, 20);
        assertArrayEquals(expected, pay);
    }

    @Test
    public void testCalTumoScore6() {
        int[] expected = {0, 4000};
        int[] pay = ScoreCalculator.calTumoScore(true, 5, 20);
        assertArrayEquals(expected, pay);
    }

    @Test
    public void testCalRonScore1() {
        int score = ScoreCalculator.calRonScore(true, 2, 25);
        assertEquals(2400, score);
    }

    @Test
    public void testCalRonScore2() {
        int score = ScoreCalculator.calRonScore(false, 2, 25);
        assertEquals(1600, score);
    }

    @Test
    public void testCalRonScore3() {
        int score = ScoreCalculator.calRonScore(true, 3, 30);
        assertEquals(5800, score);
    }

    @Test
    public void testCalRonScore4() {
        int score = ScoreCalculator.calRonScore(false, 3, 30);
        assertEquals(3900, score);
    }

    @Test
    public void testCalRonScore5() {
        int score = ScoreCalculator.calRonScore(true, 6, 20);
        assertEquals(18000, score);
    }

    @Test
    public void testCalRonScore6() {
        int score = ScoreCalculator.calRonScore(false, 6, 20);
        assertEquals(12000, score);
    }
}
