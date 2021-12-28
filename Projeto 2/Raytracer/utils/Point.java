package utils;

public class Point {
    public float x;
    public float y;
    public float z;

    public Point(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Point(){
        this(0,0,0);
    }

    public Vector distanceTo(Point end){
        float dx =  end.x - this.x;
        float dy =  end.y - this.y;
        float dz =  end.z - this.z;
        
        return new Vector(dx, dy, dz);
    }

    public Point addVector(Vector v){
        return new Point(
            this.x + v.x,
            this.y + v.y,
            this.z + v.z
        );
    }

    public Vector minus(Point p){
        Vector result = new Vector(
            this.x - p.x,
            this.y - p.y,
            this.z - p.z
        );
        return result;
    }

}
