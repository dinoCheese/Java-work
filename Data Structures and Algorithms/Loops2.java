import java.util.*;

public class Loops2 {
    public static void main(String[] args) {

        System.out.print("Enter a positive integer: ");

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n <= 0) {
            System.out.println("Non-positive! Exiting.. :(");
            System.exit(-1);
        }

        int count = 1;
        int sum = 1;

        while (count <= n) {
            sum *= count;
            count++;
        }

        System.out.println("The sum 1 + 2 + 3 + ... + " + n + " is " + sum);

    }
}


