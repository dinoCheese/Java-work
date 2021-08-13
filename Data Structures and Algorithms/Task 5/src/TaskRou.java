/**
 * Created by Emily on 15/05/14.
 */

import java.util.Scanner;


public class TaskRou {


    public double expectedValue(int s, int r) {

        if (r == 0) {
            if (isPrime(s)) {
                return 100;

            } else {
                return -50;
            }
        } else {
            double k = 0;
            for (int i = 1; i <= 6; i++) {
                k = k + (1 / 6) * expectedValue(s + i, r - 1);
            }
            return k;
        }
    }

    public boolean isPrime(int s) {
        for (int i = 2; i <= s; i++) {
            if (s % i == 0) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        expectedValue(0,3);
    }
}
