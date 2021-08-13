/**
 * Created by Emily on 28/08/14.
 */
public class Square extends Rectangle {

    public int widthheight;

    public int getArea(int widthheight){
        return widthheight*widthheight;
    }

    public int getPerimiter(){
        return widthheight+widthheight;
    }

}
