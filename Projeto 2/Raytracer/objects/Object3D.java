package objects;

import utils.*;

public class Object3D {
    public Point center;

    public Object3D(float x, float y ,float z){
        this.center = new Point(x, y, z);
    }

    public Material getMaterial(){
        return new Material();
    }

    public boolean intersects(Camera camera, Ray ray){
        return false;
    }
}
