/**
 * Created by Emily on 20/03/14.
 */

import java.util.*;

public class Occurrences {

        public static void method1() {
            System.out.println("A");
            System.out.println("B");
        }

        public static void method2() {
            System.out.println("C");
            method1();
            System.out.println("D");
        }

        public static void main(String[] args) {
            System.out.println("E");

            method1();

            System.out.println("F");

            method2();

            System.out.println("G");
        }
}

