import java.util.*;
/**
 * Created by Emily on 10/02/14.
 */
public class Assignment1 {
    public static void main (String[] args){

        System.out.print("Please enter your text: ");                //Asks for input
        Scanner input = new Scanner (System.in);                     //Make a scanner
        String imputtext = input.nextLine();                        //defines input to a variable

        System.out.println("Please enter the number of characters you would like to take from the left: ");
        int left = input.nextInt();                        //defines second variable
        System.out.println("Please enter the number of characters you would like to take from the right: ");
        int right = input.nextInt();                        //defines third variable

        System.out.println(imputtext.substring(left, imputtext.length() - right));
    }
}
