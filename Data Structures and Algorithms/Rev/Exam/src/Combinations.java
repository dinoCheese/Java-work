/**
 * Created by Emily Sellman on 29/08/14.
 * Student number: i6045016
 * Exam resit August
 *
 */

import java.util.*;

public class Combinations {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter n:   ");
        int n = scanner.nextInt();
        System.out.print("Please enter r:   ");
        int r = scanner.nextInt();

        System.out.println("The number of combinations, " + n + " choose " + r + " is " + Combinations(n, r));
    }

    public static int Combinations(int n, int r) {

        try {
            if (n == r) {
                return 1;
            } else if (r == 1) {
                return n;
            } else {
                return Combinations(n - 1, r - 1) + Combinations(n - 1, r);
            }
        } catch (Exception e) {
            System.out.print("That value you have entered doesn't seem to be working.");
            System.exit(-1);
        }
        return 0;
    }
}




