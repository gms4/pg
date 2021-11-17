package objects;

import utils.Point;
import utils.Ray;
import utils.Vector;

public class Camera{
    public Point center;
    public Vector target;
    public int focal_distance;
    public double angle_degree;
    public double angle_rad;
    public double width;
    public double height;
    public double pixelsW;
    public double pixelsH;
    
    public Camera(int x, int y, int z, int f){
        this.center = new Point(x, y, z);
        this.focal_distance = f;
        this.angle_degree = 60;
        this.angle_rad = (angle_degree*Math.PI)/180;
        this.width = 2*Math.tan(this.angle_rad/2)*this.focal_distance;
        this.height = 2*Math.tan(this.angle_rad/2)*this.focal_distance;
    }

    public Ray castRay(float px, float py){
        Vector direction = new Vector(px, py, this.center.z+this.focal_distance);
        return new Ray(this.center, direction);
    }
}