import java.util.*;
import java.net.*;

public class WHEEL {

    // Method to get a phrase from the keyboard
    public static String getPhrase() {
        System.out.print("Please enter a phrase with no puncuation: ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return line;
    }

    public static String decryptURL(String encURL) {
        String dcstr = "";
        for (int i = 0; i < encURL.length(); i++)
            dcstr += (char) ((int) encURL.charAt(i) + 10);
        return dcstr;
    }

    // Improved method to get a phrase from the keyboard or Internet
    public static String getPhrase2() {
        System.out.print("Type in k for input from keyboard or r for a random phrase: ");
        Scanner scanner = new Scanner(System.in);               //why new scanner?
        String line = scanner.nextLine();

        //acts upon input
        if (line.equals("k")) {
            return getPhrase();
        } else if (!line.equals("r")) {
            System.out.println("Invalid option, exiting..");
            System.exit(-1);
        }

        // get a random phrase.
        Random rng = new Random();
        String wline = "";
        try {
            String encURL1 = "^jjf0%%cbWdYjej$_d\\e%jcf%cWj'&&*%me\\f^hWi[i$jnj";
            String encURL2 = "^jjf0%%cbWdYjej$_d\\e%jcf%cWj'&&*%ied]dWc[i$jnj";
            URL url = new URL(decryptURL(encURL1));
            //URL url = new URL(decryptURL(encURL2));
            Scanner s = new Scanner(url.openStream());
            int num = Integer.parseInt(s.nextLine());
            int index = rng.nextInt(num);
            for (int i = 0; i < index; i++)
                wline = s.nextLine();
        } catch (Exception e) {
            System.out.println("Error reading online phrases file.");
            System.out.println("Exiting..");
            System.exit(-1);
        }

        return wline;
    }

    public static void main(String[] args) {

        // 1. Phase I
        String phrase = "if it aint broke dont fix it";

        // 2. Phase II
        // String phrase = getPhrase();

        // 3. Phase III.
        // Note: you must be connected to the Internet to use the random phrases.
        //String phrase = getPhrase2();

        System.out.println("");
        System.out.println("Welcome to the Wheel of Fortune! Please do not enter any entries more than once. For instance please do not enter 'o' twice.");

        // Some useful constants
        String allConsonants = "bcdfghjklmnpqrstvwxy";
        String allVowels = "aeiou";
        int maxConsonants = 8;
        int maxVowels = 4;

        int numcon = 0;
        int numvowels = 0;

        boolean[] revealed = new boolean[phrase.length()];       //arrayofbool

        boolean something = true;

        for (int i = 0; i < phrase.length(); i++) {
            if (phrase.charAt(i) == ' ') {
                revealed[i] = true;
            } else {
                revealed[i] = false;
            }
        }

        Scanner scanner = new Scanner(System.in);


        while (numcon <= maxConsonants || numvowels <= maxVowels) {
            while (numcon != maxConsonants || numvowels != maxVowels) {
                for (int i = 0; i < phrase.length(); i++) {
                    if (revealed[i] == true) {
                        System.out.print(phrase.charAt(i));     //print char at the index if true
                    } else {
                        System.out.print(".");     //print .
                    }
                }
                System.out.println("       The number of v remaining is " + (maxVowels - numvowels) + " and the number of c is " + (maxConsonants - numcon));
                System.out.println("");
                System.out.println("Enter 1 guess: ");
                char letter = scanner.next().charAt(0);

                if (allVowels.indexOf(letter) == -1) {
                    if (numcon == maxConsonants) {
                        System.out.println("Sorry you've used all your c");
                        continue;
                    } else
                        numcon++;
                } else {
                    if (numvowels == maxVowels) {
                        System.out.println(" ");
                        System.out.println("Sorry you've used all your v");
                        continue;
                    } else {
                        numvowels++;
                    }
                }


                for (int i = 0; i < phrase.length(); i++) {
                    if (phrase.charAt(i) == letter) {
                        revealed[i] = true;
                    }
                }
            }

            if (numcon == 8 && numvowels == 4){
                 System.out.println("Press enter to continue...");
                 Scanner keyboard = new Scanner(System.in);
                 keyboard.nextLine();
                 System.out.println("Thanks for playing! The phrase was: " + phrase);
                 break;
            }
            else {
                break;
            }

        }
    }
}
