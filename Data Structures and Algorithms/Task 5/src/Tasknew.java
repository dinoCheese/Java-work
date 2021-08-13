/**
 * Created by Emily on 15/05/14.
 */
import java.util.Scanner;

public class Tasknew {

    public static double power(long x, int n) {
        if (n == 0){
            return 1;
        }
        else if (n > 0){
            //double k = Math.pow(x,(n-1));
            double k = power(x,(n-1));
            k = x*k;
            return k;
        }
        return 0;
    }


    public static void main(String[] args) {
    Scanner scanner = new Scanner (System.in);
        System.out.println("Please enter your your value for x.");
        int x = scanner.nextInt();
        System.out.println("Please enter your your value for n.");
        int n = scanner.nextInt();
        //power(x,n);
        System.out.println("Your number is: " + power(x,n));
    }
}
