/**
 * Created by Emily on 15/05/14.
 */

import java.util.Scanner;


public class Task2 {

    private static long[] cache = new long[81]; // why 81? see the how the index is used below


    public static long fibbo(int n) {
        cache[2] = 1;
        cache[1] = 1;


        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else{
            if (cache[n] == 0){
                long k = fibbo(n-1)+ fibbo(n-2);
                cache[n] = k;
                return k;
            }
            else{
                cache[n] = cache[n-1]+ cache[n-2];
                long k = cache[n];
                return k;
            }

        }

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your nth term: ");
        int n = scanner.nextInt();
        System.out.println(fibbo(n));

    }
}


/*
Implement a recursive method that computes F(n). Test it by printing out F(6), which is 8. Once you are convinced
that it is working, try using your method to compute F(80). What is happening?
First, change int to long as the number will be larger than the maximum value representable by an integer. Create
a (global) static array in your class of size using:
*/