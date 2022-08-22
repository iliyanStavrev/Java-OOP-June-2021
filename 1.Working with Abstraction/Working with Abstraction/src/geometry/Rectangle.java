package geometry;

public class Rectangle {
    private Point2d bottomLeft;
    private Point2d topRight;

    public Rectangle(Point2d bottomLeft, Point2d topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }
    public boolean contains(Point2d point){
        return  point.getX() >= bottomLeft.getX()
                && point.getX() <= topRight.getX()
                && point.getY() >= bottomLeft.getY()
                && point.getY() <= topRight.getY();
    }
}
