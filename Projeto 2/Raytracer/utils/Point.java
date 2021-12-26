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
}
