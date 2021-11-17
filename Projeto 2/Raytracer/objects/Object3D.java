package objects;

import utils.*;

public class Object3D {
    public Point center;
    public Material material;

    public Object3D(float x, float y ,float z){
        this.center = new Point(x, y, z);
        this.material = new Material();
    }

    public boolean intersects(Ray ray){
        return false;
    }
}
