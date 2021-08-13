/**
 * Created by Emily on 24/04/14.
 */
//import java.io.*;

import java.util.*;

class Main {
    public static void main(String[] args) {
        try {
            int myArray[] = new int[3];
            myArray[3] = 3;

            Scanner input = null;
            String line = input.nextLine();

        } catch (NullPointerException e) {
            System.out.print("NullPointerException");
        }
         catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("ArrayIndexOutOfBoundsException");

         }
    }       //e is an object which refers to the error
}



/*
In each block, print the name of the type of the exception caught.
Run the program to see what happens.
Then, switch the order of the catch cases. Run the program again.
Can you explain what is happening?

 */