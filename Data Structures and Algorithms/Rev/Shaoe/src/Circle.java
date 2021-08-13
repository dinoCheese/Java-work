/**
 * Created by Emily on 28/08/14.
 */
class Circle extends Shape {

    private double radius;

    Circle(double radius){
        this.radius = radius; //((int)Math.PI*radius*radius);
    }

    public double getArea(){return Math.PI*radius*radius;}
    public double getPerimiter(){return 2*Math.PI*radius;}
    public double getDiamiter(){return (1/2)*radius;}

}
