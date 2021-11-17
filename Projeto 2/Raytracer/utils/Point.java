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

        float dxy = (float)Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)); // dxy^2 = dx^2 + dy^2
        float dxyz = (float)Math.sqrt(Math.pow(dxy, 2) + Math.pow(dz, 2)); // dxyz^2 = dxy^2 + dz^2

        return new Vector(dx, dy, dz, dxyz);
    }
}
