/**
 * Created by Emily on 28/08/14.
 */
public class Rectangle extends Shape {

    //public int width;
    //public int height;

    public int getArea(int width, int height){
        return width*height;
    }

    public int getPerimiter(int width, int height){
        return (width*2 +height*2);
    }

}
