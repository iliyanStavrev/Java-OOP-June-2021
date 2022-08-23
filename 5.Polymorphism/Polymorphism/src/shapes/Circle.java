package shapes;

public class Circle extends Shape{
    private Double radius;

    public Circle(Double radius) {
        this.setRadius(radius);
        this.calculatePerimeter();
        this.calculateArea();
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    protected Double calculatePerimeter() {
        return Math.PI * 2 * radius;
    }

    @Override
    protected Double calculateArea() {
        return Math.PI * radius * radius;
    }

    public final Double getRadius() {
        return radius;
    }
}
