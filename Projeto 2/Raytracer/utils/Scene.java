package utils;

import objects.*;

public class Scene {
    public Camera camera;
    public Object3D[] objects;
    public int width;
    public int height;

    public Scene(Camera camera, Object3D[] objects, int width, int height){
        this.camera = camera;
        this.objects = objects;
        this.width = width;
        this.height = height;
    }
}
