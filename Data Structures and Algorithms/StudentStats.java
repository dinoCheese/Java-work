import java.util.*;

public class StudentStats {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of students ");
        int num = scanner.nextInt();

        int i = 1;
        int[] grades = new int[num];

        int currentmax = -1;
        int currentmin = 11;

        while (i <= num) {
            System.out.print("Please enter the grade between 0-10 for student " + i + ": ");
            grades[i-1] = scanner.nextInt();
            System.out.println("Thank you!");


            if (grades[i-1] >= currentmax){
                currentmax = grades[i-1]; }
            if (grades[i-1] <= currentmin){
                currentmin = grades[i-1]; }

            i++;
        }


        int sum = 0;
        i = 0;

        while (i < num) {
            sum += grades[i];
            i++;
        }

        double avg = (double)sum / num;
        System.out.println("The average is: " + avg);
        System.out.println("The highest value is: " + currentmax );
        System.out.println("The lowest value is: " + currentmin);
    }
}