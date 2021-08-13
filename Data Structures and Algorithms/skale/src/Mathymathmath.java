/**
 * Created by Emily on 13/02/14.
 */
import java.util.Scanner;

public class Mathymathmath {
    public static void main(String[] args) {

        System.out.println("This is the programme for finding the value of the ajasent");

        Scanner input = new Scanner (System.in);
        System.out.println("Please type in value for a (opposite angle) ");
        double opposite = input.nextDouble();
        System.out.println("Please type in value for c (hypotenouse angle) ");
        double hypotenouse = input.nextDouble();
        //Calculate the hypotunus
        double ajasent = Math.pow(((Math.pow(hypotenouse,2)) - (Math.pow(opposite,2))),0.5);
        System.out.println(ajasent);
        System.out.println();                               //makes a gap between programes
        System.out.println("This is your angle");

        double angle = Math.asin(opposite/ajasent);
        System.out.println(angle);

        System.out.println();                               //makes a gap between programes

        /*System.out.println("Please type in value for a (opposite angle ");
        double opposite1 = input.nextDouble();
        System.out.println("Please type in value for c (hypotenouse angle ");
        double hypotenouse1 = input.nextDouble();   */

        System.out.println("please enter the distance the ball has travled: ");
        double distance = input.nextDouble();
        double v = hypotenouse*Math.sin(angle);      //vert
        double b = hypotenouse*Math.cos(angle);      //hor
        double time = distance/b;

        System.out.println("This is the time taken for the ball to reach the ground " + time);

        //Calculating vert distance

        System.out.println("This is the value of Y is " + ((-(Math.pow(v,2))/-19.6)));
    }
}
