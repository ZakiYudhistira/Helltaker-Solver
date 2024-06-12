public class Point{
    public Integer x;
    public Integer y;

    public Point(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }

    public Integer getDistance(Point p){
        return getDistance(this, p);
    }

    public static Integer getDistance(Point p1, Point p2){
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    @Override
    public String toString(){
        return "(x=" + x + ",y=" + y + ")";
    }

    public boolean equals(Point p2){
        return x == p2.x && y == p2.y;
    }
}
