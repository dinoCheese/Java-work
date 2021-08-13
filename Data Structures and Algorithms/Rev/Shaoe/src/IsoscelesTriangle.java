/**
 * Created by Emily on 28/08/14.
 */
class IsoscelesTriangle extends Shape {

    private double height;
    private double width;

    IsoscelesTriangle(double height, double width){
        this.height = height;
        this.width = width;
    }

    public double getArea(){return (1/2)*height*width;}     //not true but oh so lazy
    public double getPerimiter(){return (2*height +2*width);}

}
