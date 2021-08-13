/**
 * Created by Emily on 28/08/14.
 */
public class Rational {

    //create constructor which takes num and den

    private int denominator;					//initializing vars
    private int numerator;

    Rational (int denominator, int numerator){				//creating getters den
        this.denominator = denominator;
        this.numerator = numerator;
    }

    public Rational reciprocal (){
        return new Rational(denominator,numerator);
    }

    public Rational negate (){
        return new Rational (-denominator, numerator);
    }

    public Rational lcd(){
        int min = numerator;
        if (denominator<numerator){
            min = denominator;

            //try and find the largest common factor

            for (int factor = min; factor >1 ; factor--){
                if((numerator % factor == 0)&&(denominator % factor == 0)){
                    return new Rational((numerator/factor), (denominator/factor));
                }
            }
        }

        return new Rational(numerator, denominator);
    }

    public Rational add (Rational other){
        return new Rational(numerator*other.denominator, denominator*other.numerator);
    }

    public Rational subtract(Rational other){
        return add(other.negate());
    }

    public Rational times (Rational other){
        return new Rational (numerator*other.numerator,denominator*other.denominator);
    }

    public Rational dividedBy (Rational other){
        return new Rational(numerator/other.numerator, denominator/other.denominator);
    }

    public String toString(){
        return (numerator + "/" + denominator);
    }






}
